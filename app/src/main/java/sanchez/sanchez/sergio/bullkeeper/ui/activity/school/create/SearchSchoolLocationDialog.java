package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import io.reactivex.observers.DisposableObserver;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.decoration.ItemOffsetDecoration;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.PlaceSuggestionsAdapter;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;
import sanchez.sanchez.sergio.domain.interactor.places.SearchPlacesInteract;
import sanchez.sanchez.sergio.domain.models.PlaceSuggestionEntity;
import timber.log.Timber;

/**
 * Search School Location Dialog
 */
public final class SearchSchoolLocationDialog extends SupportDialogFragment
        implements OnMapReadyCallback,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerClickListener, SearchView.OnQueryTextListener,
        SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<PlaceSuggestionEntity>, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final String TAG = "SEARCH_SCHOOL_LOCATION_DIALOG";

    private final static String SHOW_CURRENT_LOCATION_ARG = "SHOW_CURRENT_LOCATION_ARG";
    private final static String CURRENT_LATITUDE_ARG = "CURRENT_LATITUDE_ARG";
    private final static String CURRENT_LONGITUDE_ARG = "CURRENT_LONGITUDE_ARG";
    private static final String SEARCH_PLACES_RADIUS = "10500";

    /**
     * Search School Listener
     */
    private ISearchSchoolListener searchSchoolListener;

    /**
     * Google Map
     */
    private GoogleMap googleMap;

    /**
     * Google API Client
     */
    private GoogleApiClient googleApiClient;

    /**
     * Current Location
     */
    private Location currentLocation = null;

    /**
     * Current Marker Position
     */
    private Marker currentMarkerPosition;

    /**
     * Spain LatLng Bounds
     * -9.39288367353, 35.946850084, 3.03948408368, 43.7483377142
     */
    private final LatLngBounds spainLatLngBounds = new LatLngBounds(
        new LatLng(-9.39288367353, 35.946850084),
        new LatLng(3.03948408368, 43.7483377142));


    /**
     * Place Autocomplete Search View
     */
    @BindView(R.id.placeAutocomplete)
    protected SearchView placeAutocompleteSearchView;

    /**
     * Places Suggestions Container View
     */
    @BindView(R.id.placesSuggestionsContainer)
    protected View placesSuggestionsContainerView;

    /**
     * Recycler View
     */
    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    /**
     * Place Autocomplete Title Text View
     */
    @BindView(R.id.placeAutocompleteTitle)
    protected TextView placeAutocompleteTitleTextView;

    /**
     * Recycler View Adapter
     */
    protected SupportRecyclerViewAdapter<PlaceSuggestionEntity> recyclerViewAdapter;


    /**
     * Dependencies
     * =================
     */


    /**
     * Search Places Interact
     */
    @Inject
    public SearchPlacesInteract searchPlacesInteract;

    /**
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity, final boolean showCurrentLocation) {
        final SearchSchoolLocationDialog searchSchoolDialogFragment = new SearchSchoolLocationDialog();
        final Bundle args = new Bundle();
        args.putBoolean(SHOW_CURRENT_LOCATION_ARG, showCurrentLocation);
        searchSchoolDialogFragment.setArguments(args);
        searchSchoolDialogFragment.setStyle(SearchSchoolLocationDialog.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        searchSchoolDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    /**
     * Show Dialog
     * @param appCompatActivity
     * @param latitude
     * @param longitude
     */
    public static void show(final AppCompatActivity appCompatActivity, final double latitude, final double longitude){
        final SearchSchoolLocationDialog searchSchoolDialogFragment = new SearchSchoolLocationDialog();
        final Bundle args = new Bundle();
        args.putDouble(CURRENT_LATITUDE_ARG, latitude);
        args.putDouble(CURRENT_LONGITUDE_ARG, longitude);
        searchSchoolDialogFragment.setArguments(args);
        searchSchoolDialogFragment.setStyle(SearchSchoolLocationDialog.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        searchSchoolDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    /**
     * On Attach
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            searchSchoolListener = (ISearchSchoolListener) context;
        } catch (ClassCastException exception) {
            throw new RuntimeException("Context must be implement ISearchSchoolListener");
        }
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.search_school_dialog_fragment_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        getApplicationComponent().inject(this);
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        if(mapFragment != null)
            mapFragment.getMapAsync(this);


        // Configure Recycler view for Suggested places
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter = new PlaceSuggestionsAdapter(getActivity(), new ArrayList<PlaceSuggestionEntity>());
        recyclerView.setAdapter(recyclerViewAdapter);
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemOffsetDecoration);
        recyclerViewAdapter.setOnSupportRecyclerViewListener(this);

        // Customize Search View
        placeAutocompleteSearchView.setSubmitButtonEnabled(false);
        // Set Hint and Text Color
        final EditText txtSearch = placeAutocompleteSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        txtSearch.setHint(R.string.search_school_location_query_hint);
        txtSearch.setHintTextColor(ContextCompat.getColor(getContext(), R.color.cyanBrilliant));
        txtSearch.setTextColor(getResources().getColor(R.color.cyanBrilliant));
        placeAutocompleteSearchView.setOnQueryTextListener(this);

        // On Search Click Listener
        placeAutocompleteSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeAutocompleteTitleTextView.setVisibility(View.GONE);
                placeAutocompleteSearchView.setBackgroundResource(R.drawable.searchbar_background);
                placeAutocompleteSearchView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            }
        });

        // On Close Listener
        placeAutocompleteSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                hideKeyboard();
                clearPlaceSuggestions();
                placeAutocompleteTitleTextView.setVisibility(View.VISIBLE);
                placeAutocompleteSearchView.setBackgroundResource(android.R.color.transparent);
                placeAutocompleteSearchView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                return false;
            }
        });

        final Bundle args = getArguments();
        if(args != null) {
            if (args.containsKey(SHOW_CURRENT_LOCATION_ARG)) {
                initializeGoogleApiClient();
            } else if (args.containsKey(CURRENT_LATITUDE_ARG)
                    && args.containsKey(CURRENT_LONGITUDE_ARG)) {
                currentLocation = new Location("");
                currentLocation.setLatitude(args.getDouble(CURRENT_LATITUDE_ARG));
                currentLocation.setLongitude(args.getDouble(CURRENT_LONGITUDE_ARG));
            }
        }
    }

    /**
     * On Destroy View
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FragmentManager fm = getFragmentManager();
        if (fm != null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
            if (mapFragment != null) {
                fm.beginTransaction().remove(mapFragment).commit();
                mapFragment.onDestroyView();
            }
        }
    }

    /**
     * On Start
     */
    @Override
    public void onStart() {
        if(googleApiClient != null)
            googleApiClient.connect();
        super.onStart();
    }

    /**
     * On Stop
     */
    @Override
    public void onStop() {
        if(googleApiClient != null)
            googleApiClient.disconnect();
        super.onStop();
    }

    /**
     * On Map Ready
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Timber.d("On Map Ready");
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.setOnMapLongClickListener(this);

        if(currentLocation != null)
            moveMapTo(currentLocation, getString(R.string.current_location_selected));
    }

    /**
     * Initialize Google API Client
     */
    private void initializeGoogleApiClient(){

        //Initializing googleApiClient
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    /**
     * Get Address From Location
     * @param latLng
     * @return
     * @throws IOException
     */
    private Address getAddressFromLocation(final LatLng latLng) throws IOException {
        Geocoder geoCoder = new Geocoder(getContext());
        List<Address> matches = geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
        return (matches.isEmpty() ? null : matches.get(0));
    }

    /**
     * Get Current Location
     */
    private Location getCurrentLocation() {
        googleMap.clear();
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    }

    /**
     * Move Map To
     * @param location
     */
    private void moveMapTo(final Location location, final String title) {
        Preconditions.checkNotNull(googleMap, "Google Map can not be null");
        Preconditions.checkNotNull(location, "Location can not be null");
        Preconditions.checkNotNull(title, "Title can not be null");
        Preconditions.checkState(!title.isEmpty(), "Title can not be empty");
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        if(currentMarkerPosition != null)
            currentMarkerPosition.remove();

        currentMarkerPosition = googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(false)
                .title(title));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        googleMap.getUiSettings().setZoomControlsEnabled(true);

    }

    /**
     * Notify Position Selected
     * @param latLng
     */
    private void notifyPositionSelected(final LatLng latLng) {
        if(searchSchoolListener != null) {
            try {
                final Address address = getAddressFromLocation(latLng);
                if(address != null) {
                    final String residence = String.format("%s %s - %s", address.getThoroughfare(), address.getSubThoroughfare(),
                            address.getPostalCode());
                    searchSchoolListener.onSelectLocation(latLng.latitude, latLng.longitude, residence, address.getLocality());
                    dismiss();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Clear Place Suggestion
     */
    private void clearPlaceSuggestions(){
        recyclerViewAdapter.setData(new ArrayList<PlaceSuggestionEntity>());
        recyclerViewAdapter.notifyDataSetChanged();
        placesSuggestionsContainerView.setVisibility(View.GONE);
    }

    /**
     * On Connected
     * @param bundle
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Timber.d("Google Api Client Connected ");
        currentLocation = getCurrentLocation();
        if(currentLocation != null)
            moveMapTo(currentLocation, getString(R.string.user_current_location));
    }

    /**
     * On Connection Suspended
     * @param i
     */
    @Override
    public void onConnectionSuspended(int i) {}


    /**
     * On Map Long Click
     * @param latLng
     */
    @Override
    public void onMapLongClick(LatLng latLng) {
        Timber.d("On Map Click");
        notifyPositionSelected(latLng);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        notifyPositionSelected(marker.getPosition());
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {}

    @Override
    public void onMarkerDrag(Marker marker) {}

    @Override
    public void onMarkerDragEnd(Marker marker) {}

    /**
     * On Query Text Submit
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * On Query Text Change
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        Preconditions.checkNotNull(newText, "New Text can not be null");

        if(!newText.isEmpty()) {


            if(currentLocation != null)
                searchPlacesInteract.execute(new DisposableObserver<List<PlaceSuggestionEntity>>() {
                    @Override
                    public void onNext(List<PlaceSuggestionEntity> suggestionEntityList) {
                        Preconditions.checkNotNull(suggestionEntityList, "Suggestion Entity List can not be null");

                        if(!suggestionEntityList.isEmpty()) {
                            recyclerViewAdapter.setData(suggestionEntityList);
                            recyclerViewAdapter.notifyDataSetChanged();
                            placesSuggestionsContainerView.setVisibility(View.VISIBLE);
                            final Context context = recyclerView.getContext();
                            final LayoutAnimationController controller =
                                    AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
                            recyclerView.setLayoutAnimation(controller);
                            recyclerView.scheduleLayoutAnimation();
                        } else {
                            clearPlaceSuggestions();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        clearPlaceSuggestions();
                    }

                    @Override
                    public void onComplete() {

                    }
                }, SearchPlacesInteract.Params.create(currentLocation.getLatitude(),
                        currentLocation.getLongitude(), newText, SEARCH_PLACES_RADIUS));

            recyclerViewAdapter.setHighlightText(newText);

        } else {
            clearPlaceSuggestions();
        }
        return true;
    }


    @Override
    public void onHeaderClick() { }

    /**
     * On Suggested Place Clicked
     * @param placeSuggestionEntity
     */
    @Override
    public void onItemClick(PlaceSuggestionEntity placeSuggestionEntity) {
        Preconditions.checkNotNull(placeSuggestionEntity, "Suggested Place can not be null");

        placesSuggestionsContainerView.setVisibility(View.GONE);

        // Hide Keyboard
        hideKeyboard();

        if(placeSuggestionEntity.getPosition() != null && placeSuggestionEntity.getPosition().length > 0) {
            final Location placeLocation = new Location("");
            placeLocation.setLatitude(placeSuggestionEntity.getPosition()[0]);
            placeLocation.setLongitude(placeSuggestionEntity.getPosition()[1]);
            moveMapTo(placeLocation, placeSuggestionEntity.getVicinity());
        }


    }

    @Override
    public void onFooterClick() {}

    /**
     *
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Timber.d("On Connection Failed");
    }

    /**
     * Search School Listener
     */
    public interface ISearchSchoolListener {

        /**
         * On Select Location
         * @param latitude
         * @param longitude
         * @param residence
         * @param locality
         */
        void onSelectLocation(final double latitude, final double longitude, final String residence, final String locality);

    }
}
