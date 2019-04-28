package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SwitchCompat;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.ramotion.fluidslider.FluidSlider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpValidationMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportTouchableMapFragment;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerGeofenceComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.GeofenceComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.list.GeofencesListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.decoration.ItemOffsetDecoration;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.PlaceSuggestionsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.geofencealerts.GeofenceAlertsListMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.services.FetchAddressIntentService;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceTransitionTypeEnum;
import sanchez.sanchez.sergio.domain.models.SuggestedPlaceEntity;
import timber.log.Timber;

/**
 * Save Geofence Mvp Activity
 */
public class SaveGeofenceMvpActivity extends SupportMvpValidationMvpActivity<SaveGeofencePresenter, ISaveGeofenceView>
        implements HasComponent<GeofenceComponent>, ISaveGeofenceView, OnMapReadyCallback, GoogleMap.OnMapClickListener,
        GoogleApiClient.ConnectionCallbacks, AdapterView.OnItemSelectedListener,
        SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<SuggestedPlaceEntity>,
        SearchView.OnQueryTextListener, OnSuccessListener<AutocompletePredictionBufferResponse>,
        OnFailureListener, ISaveGeofenceActivityHandler {

    /**
     * Geofence Added Arg
     */
    public static final String GEOFENCE_ADDED_ARG = "GEOFENCE_ADDED_ARG";

    private final String CONTENT_FULL_NAME = "SAVE_GEOFENCE";
    private final String CONTENT_TYPE_NAME = "GEOFENCES";

    private final static String NAME_FIELD_NAME = "name";

    /**
     * Default Values
     */
    private static final int TARGET_ZOOM = 19;
    private static final int MIN_RADIUS_VALUE = 20;
    private static final int MAX_RADIUS_VALUE = 200;


    /**
     * Args
     */
    private final static String KID_ID_ARG = "KID_ID_ARG";
    private final static String ID_ARG = "ID_ARG";


    /**
     * Geofence Component
     */
    private GeofenceComponent geofenceComponent;

    /**
     * STATE
     * ===============
     */

    /**
     * Kid
     */
    @State
    protected String kid;

    /**
     * Identity
     */
    @State
    protected String identity;

    /**
     * Address
     */
    @State
    protected String address;

    /**
     * Latitude
     */
    @State
    protected double latitude;

    /**
     * Longitude
     */
    @State
    protected double longitude;

    /**
     * Name
     */
    @State
    protected String name;

    /**
     * Radius
     */
    @State
    protected double radius = MIN_RADIUS_VALUE;

    /**
     * Type
     */
    @State
    protected GeofenceTransitionTypeEnum type;

    /**
     * Geofence Transition Type Selected
     */
    @State
    protected int geofenceTransitionTypeSelected = 0;

    /**
     * Is Enabled
     */
    @State
    protected Boolean isEnabled;

    /**
     *
     * Views
     * ==================
     *
     */

    /**
     * Origin Location Address Text View
     */
    @BindView(R.id.originLocationAddress)
    protected TextView originLocationAddressTextView;

    /**
     * Delete Geofence
     */
    @BindView(R.id.deleteGeofence)
    protected View deleteGeofenceView;

    /**
     * Save Changes View
     */
    @BindView(R.id.saveChanges)
    protected View saveChangesView;


    /**
     * Name Input Layout
     */
    @BindView(R.id.nameInputLayout)
    protected TextInputLayout nameInputLayout;

    /**
     * Geofence Radius Slider
     */
    @BindView(R.id.geofenceRadiusSlider)
    protected FluidSlider geofenceRadiusSlider;

    /**
     * Geofence Alerts Container
     */
    @BindView(R.id.geofenceAlertsContainer)
    protected FrameLayout geofenceAlertsContainer;

    /**
     * Name Input
     */
    @BindView(R.id.nameInput)
    @NotEmpty(messageResId = R.string.geofence_invalid_name)
    @Length(min = 3, max = 30)
    protected AppCompatEditText nameInput;

    /**
     * Nested Scroll View
     */
    @BindView(R.id.content)
    protected NestedScrollView nestedScrollView;

    /**
     * Geofences Transition Type
     */
    @BindView(R.id.geofencesTransitionType)
    protected AppCompatSpinner geofencesTransitionTypeSpinner;

    /**
     * Is Enable Switch
     */
    @BindView(R.id.enableSwitch)
    protected SwitchCompat enableSwitch;


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
    protected SupportRecyclerViewAdapter<SuggestedPlaceEntity> recyclerViewAdapter;

    /**
     * Google Map
     */
    private GoogleMap googleMap;

    /**
     * Current Circle Indicator
     */
    private Circle currentCircleIndicator;

    /**
     * Current Marker Position
     */
    private Marker currentMarkerPosition;

    /**
     * Autocomplete Filter
     */
    private AutocompleteFilter autocompleteFilter;

    /**
     * Geo Data Client
     */
    private GeoDataClient mGeoDataClient;


    /**
     * Spain LatLng Bounds
     */
    private final LatLngBounds spainLatLngBounds = new LatLngBounds(
            new LatLng(-9.39288367353, 35.946850084),
            new LatLng(3.03948408368, 43.7483377142));

    /**
     * Geofence Alerts List Fragment
     */
    private GeofenceAlertsListMvpFragment geofenceAlertsListMvpFragment;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        final Intent intent =  new Intent(context, SaveGeofenceMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        return intent;
    }

    /**
     * Get Calling Intent
     * @param context
     * @param kid
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid, final String id) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");
        final Intent intent = new Intent(context, SaveGeofenceMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        intent.putExtra(ID_ARG, id);
        return intent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        geofenceComponent = DaggerGeofenceComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        geofenceComponent.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public SaveGeofencePresenter providePresenter() {
        return geofenceComponent.saveGeofencePresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public GeofenceComponent getComponent() {
        return geofenceComponent;
    }


    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return SupportToolbarApp.RETURN_TOOLBAR;
    }

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_save_geofence;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        if(savedInstanceState == null) {
            if (!getIntent().hasExtra(KID_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an kid identifier");
            kid = getIntent().getStringExtra(KID_ID_ARG);

            if (getIntent().hasExtra(ID_ARG)) {
                identity = getIntent().getStringExtra(ID_ARG);
                // disable all components
                toggleAllComponents(false);
                getPresenter().getGeofenceById(kid, identity);
            } else {
                loadMapAsync();
            }
        }

        geofenceRadiusSlider.setStartText(String.format(Locale.getDefault(),
                getString(R.string.radius_meters_value), MIN_RADIUS_VALUE));
        geofenceRadiusSlider.setEndText(String.format(Locale.getDefault(),
                getString(R.string.radius_meters_value), MAX_RADIUS_VALUE));
        geofenceRadiusSlider.setPositionListener(new Function1<Float, Unit>() {
            @Override
            public Unit invoke(Float pos ) {
                Timber.d("Radius Selected -> %f", radius);
                final int total = MAX_RADIUS_VALUE - MIN_RADIUS_VALUE;
                final int valueSelected = (int)(MIN_RADIUS_VALUE + total * pos);
                geofenceRadiusSlider.setBubbleText(String.valueOf(valueSelected));
                radius = valueSelected;
                if(currentCircleIndicator != null)
                    currentCircleIndicator.setRadius(valueSelected);
                return null;
            }
        });

        final int total = MAX_RADIUS_VALUE - MIN_RADIUS_VALUE;
        // (r - min) / t = pos
        float pos = (float)(radius - MIN_RADIUS_VALUE) / total;
        geofenceRadiusSlider.setPosition(pos);

        // Init Geofence Transition Types Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.common_spinner_text_view,
                getResources().getStringArray(R.array.geofences_transition_types));
        geofencesTransitionTypeSpinner.setAdapter(adapter);
        geofencesTransitionTypeSpinner.setSelection(geofenceTransitionTypeSelected);
        geofencesTransitionTypeSpinner.setOnItemSelectedListener(this);


        // create Geo Data Client
        mGeoDataClient = Places.getGeoDataClient(this);

        // Build Autocomplete filter (only Address)
        autocompleteFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();

        // Configure Recycler view for Suggested places
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new PlaceSuggestionsAdapter(this, new ArrayList<SuggestedPlaceEntity>());
        recyclerView.setAdapter(recyclerViewAdapter);
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemOffsetDecoration);
        recyclerViewAdapter.setOnSupportRecyclerViewListener(this);

        // Customize Search View
        placeAutocompleteSearchView.setSubmitButtonEnabled(false);
        // Set Hint and Text Color
        final EditText txtSearch = placeAutocompleteSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        txtSearch.setHint(R.string.search_locations_query_hint);
        txtSearch.setHintTextColor(ContextCompat.getColor(this, R.color.cyanBrilliant));
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
                clearPlaceSuggestions();
                placeAutocompleteTitleTextView.setVisibility(View.VISIBLE);
                placeAutocompleteSearchView.setBackgroundResource(android.R.color.transparent);
                placeAutocompleteSearchView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                return false;
            }
        });


    }

    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_4;
    }

    /**
     * On Validation Failed
     */
    @Override
    protected void onValidationFailed() {
        showNoticeDialog(R.string.forms_is_not_valid, false);
    }

    /**
     * On Field Invalid
     * @param viewId
     * @param message
     */
    @Override
    protected void onFieldInvalid(Integer viewId, String message) {

        if (viewId.equals(R.id.nameInput)) {
            nameInputLayout.setError(message);
        }
    }

    /**
     * On Validation Succeded
     */
    @Override
    public void onValidationSucceeded() {
        super.onValidationSucceeded();

        final String name = nameInput.getText().toString();
        final String address = originLocationAddressTextView.getText().toString();
        final double latitude = currentMarkerPosition.getPosition().latitude;
        final double longitude = currentMarkerPosition.getPosition().longitude;
        final double radius = currentCircleIndicator.getRadius();
        final GeofenceTransitionTypeEnum geofenceTransitionTypeEnum =
                GeofenceTransitionTypeEnum.values()[geofenceTransitionTypeSelected];
        final boolean isEnabled = enableSwitch.isChecked();

        // Disable All Components
        toggleAllComponents(false);

        // Save Geofence
        getPresenter().saveGeofence(name, geofenceTransitionTypeEnum, address, latitude,
                longitude, (float) radius, geofenceTransitionTypeEnum, isEnabled, kid, appUtils.isValidString(identity) ?
                        identity : "");

    }

    /**
     * On Reset Fields
     */
    @Override
    protected void onResetFields() {
        super.onResetFields();
        nameInput.setText(name);
        originLocationAddressTextView.setText(address);
    }

    /**
     * On Reset Errors
     */
    @Override
    protected void onResetErrors() {
        nameInputLayout.setError("");

    }

    /**
     * On Save Changes
     */
    @OnClick(R.id.saveChanges)
    protected void onSaveGeofence(){
        if(!isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            validate();
        }
    }


    /**
     * On Single Permission Granted
     * @param permission
     */
    @Override
    public void onSinglePermissionGranted(String permission) {
        super.onSinglePermissionGranted(permission);
        if(permission.equals(Manifest.permission.ACCESS_FINE_LOCATION))
            showCurrentLocation();
    }

    /**
     * On Single Permission Rejected
     * @param permission
     */
    @Override
    public void onSinglePermissionRejected(String permission) {
        super.onSinglePermissionRejected(permission);
        if(permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
            final Location location = new Location("");
            location.setLatitude(spainLatLngBounds.getCenter().latitude);
            location.setLongitude(spainLatLngBounds.getCenter().longitude);
            showGeofenceForLocation(location, appUtils.isValidString(name) ?
                    name: getString(R.string.current_location), radius);
        }
    }


    /**
     * On Validations Errors
     * @param errors
     */
    @Override
    public void onValidationErrors(List<LinkedHashMap<String, String>> errors) {
        for (LinkedHashMap<String, String> error : errors) {
            switch (Objects.requireNonNull(error.get("field"))) {
                case NAME_FIELD_NAME:
                    nameInputLayout.setError(error.get("message"));
                    break;
            }
        }
        showNoticeDialog(R.string.forms_is_not_valid, false);
        toggleAllComponents(true);
    }

    /**
     * On Geofence Deleted
     */
    @Override
    public void onGeofenceDeleted() {
        showNoticeDialog(R.string.geofence_deleted_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });
    }

    /**
     * On Geofence Loaded
     * @param geofenceEntity
     */
    @Override
    public void onGeofenceLoaded(final GeofenceEntity geofenceEntity) {
        Preconditions.checkNotNull(geofenceEntity, "Geofence Entity");

        if(!shouldReturnResult()) {

            // Geofence State
            this.identity = geofenceEntity.getIdentity();
            this.kid = geofenceEntity.getKid();
            this.address = geofenceEntity.getAddress();
            this.latitude = geofenceEntity.getLat();
            this.longitude = geofenceEntity.getLog();
            this.name = geofenceEntity.getName();
            this.radius = geofenceEntity.getRadius();
            this.type = geofenceEntity.getType();
            this.geofenceTransitionTypeSelected = geofenceEntity.getType().ordinal();
            this.isEnabled = geofenceEntity.isEnabled();

            // Set Name
            nameInput.setText(name);

            originLocationAddressTextView.setText(address);
            // Set Visibility
            deleteGeofenceView.setVisibility(View.VISIBLE);

            final int total = MAX_RADIUS_VALUE - MIN_RADIUS_VALUE;
            // (r - min) / t = pos
            float pos = (float)(radius - MIN_RADIUS_VALUE) / total;

            geofenceRadiusSlider.setPosition(pos);

            // Enable Switch
            enableSwitch.setChecked(isEnabled);

            // Set Geofence Transition Type
            geofencesTransitionTypeSpinner.setSelection(geofenceTransitionTypeSelected);

            // Load Map is needed
            if(googleMap == null)
                loadMapAsync();


            if(geofenceAlertsListMvpFragment == null) {
                geofenceAlertsContainer.setVisibility(View.VISIBLE);
                geofenceAlertsListMvpFragment = GeofenceAlertsListMvpFragment.newInstance(kid, identity);
                // Add Geofences Alerts Fragment
                addFragment(R.id.geofenceAlertsContainer,
                        geofenceAlertsListMvpFragment, false,
                        GeofenceAlertsListMvpFragment.TAG);
            }

            toggleAllComponents(true);

        } else {

            final Intent geofenceSavedIntent = new Intent();
            geofenceSavedIntent.putExtra(GEOFENCE_ADDED_ARG, geofenceEntity);
            onResultOk(geofenceSavedIntent);
            finish();
        }

    }

    /**
     * On Map Ready
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        this.googleMap.setIndoorEnabled(false);
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);
        this.googleMap.getUiSettings().setCompassEnabled(true);
        this.googleMap.getUiSettings().setMapToolbarEnabled(true);
        this.googleMap.getUiSettings().setZoomGesturesEnabled(true);
        this.googleMap.getUiSettings().setScrollGesturesEnabled(true);
        this.googleMap.setOnMapClickListener(this);

        if (appUtils.isValidString(identity)) {

            // Move Map To Current Position
            final Location location = new Location("");
            location.setLatitude(latitude);
            location.setLongitude(longitude);

            showGeofenceForLocation(location, appUtils.isValidString(name) ?
                    name: getString(R.string.current_location), radius);

        } else {

            if(permissionManager.shouldAskPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                permissionManager.checkSinglePermission(Manifest.permission.ACCESS_FINE_LOCATION,
                        getString(R.string.location_permission_reason));
            } else {
                showCurrentLocation();
            }
        }
    }

    /**
     * On Delete Geofence
     */
    @OnClick(R.id.deleteGeofence)
    protected void onDeleteGeofence(){

        if(!isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            Timber.d("On Delete Geofence");
            showConfirmationDialog(R.string.geofence_confirm_delete, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    getPresenter().deleteGeofenceById(kid, identity);
                }

                @Override
                public void onRejected(DialogFragment dialog) {}
            });
        }
    }

    /**
     *
     * @param latLng
     */
    @Override
    public void onMapClick(final LatLng latLng) {
        Preconditions.checkNotNull(latLng, "LatLng can not be null");

        // Set Address
        final Location location = new Location("");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);

        // Show Geofence For Location
        showGeofenceForLocation(location, appUtils.isValidString(nameInput.getText().toString()) ?
                nameInput.getText().toString(): getString(R.string.current_location), radius);

    }

    /**
     * Start Intent Service
     */
    protected void fetchAddressForLocation(final Location location) {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(FetchAddressIntentService.RECEIVER, new AddressResultReceiver(new Handler()));
        intent.putExtra(FetchAddressIntentService.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }

    /**
     * On Connectec
     * @param bundle
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    /**
     * On Connection Suspended
     * @param i
     */
    @Override
    public void onConnectionSuspended(int i) {

    }

    /**
     * On Delete Geofence Clicked
     */
    @OnClick(R.id.deleteGeofence)
    protected void onDeleteGeofenceClicked(){
        if(!isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            showConfirmationDialog(R.string.geofence_confirm_delete, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                /**
                 * On Accepted
                 * @param dialog
                 */
                @Override
                public void onAccepted(DialogFragment dialog) {
                    getPresenter().deleteGeofenceById(kid, identity);
                }

                @Override
                public void onRejected(DialogFragment dialog) {}
            });
        }
    }

    /**
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        super.onNetworkError();
        toggleAllComponents(true);
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        super.onOtherException();
        toggleAllComponents(true);
    }

    /**
     *
     *  Private Methods
     *  =====================
     */

    /**
     * Create Geofence Area
     * @param latLng
     * @param radius
     */
    private void createGeofenceArea(final LatLng latLng, final double radius){
        if(currentCircleIndicator != null)
            currentCircleIndicator.remove();

        final CircleOptions circleOptions = new CircleOptions()
                .center(latLng)
                .radius(radius)
                .strokeColor(ContextCompat.getColor(this, R.color.darkModerateBlue))
                .fillColor(ContextCompat.getColor(this, R.color.translucentCyanBrilliant));

        // Create Current Circle Indicator
        currentCircleIndicator = googleMap.addCircle(circleOptions);
    }

    /**
     * Toggle All Components
     */
    private void toggleAllComponents(final boolean isEnable){
        saveChangesView.setEnabled(isEnable);
        deleteGeofenceView.setEnabled(isEnable);
        originLocationAddressTextView.setEnabled(isEnable);
        nameInputLayout.setEnabled(isEnable);
        nameInput.setEnabled(isEnable);
        geofenceRadiusSlider.setEnabled(isEnable);
        geofenceRadiusSlider.setActivated(isEnable);
        geofencesTransitionTypeSpinner.setEnabled(isEnable);
        enableSwitch.setEnabled(isEnable);
    }

    /**
     * Load Map Async
     */
    private void loadMapAsync(){
        if(getFragmentManager() != null) {
            SupportTouchableMapFragment mapFragment = (SupportTouchableMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            if(mapFragment != null) {
                mapFragment.setListener(new SupportTouchableMapFragment.OnTouchListener() {
                    @Override
                    public void onTouch() {
                        nestedScrollView.requestDisallowInterceptTouchEvent(true);
                    }
                });
                mapFragment.getMapAsync(this);
            }
        }
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

        final LatLng latLng = new LatLng(location.getLatitude(),
                location.getLongitude());

        if(currentMarkerPosition != null)
            currentMarkerPosition.remove();

        currentMarkerPosition = googleMap.addMarker(
                new MarkerOptions()
                        .position(latLng)
                        .draggable(false)
                        .title(title));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(TARGET_ZOOM));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }


    /**
     * Show Current Location
     */
    @SuppressLint("MissingPermission")
    private void showCurrentLocation(){
        Preconditions.checkNotNull(googleMap, "Google Map can not be null");

        final FusedLocationProviderClient locationProviderClient =
                LocationServices.getFusedLocationProviderClient(this);

        // Get Last Location from Fused Provider
        locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null)
                    showGeofenceForLocation(location, getString(R.string.current_location), radius);
            }
        });
    }

    /**
     * Show Geofence For Location
     * @param location
     * @param name
     */
    private void showGeofenceForLocation(final Location location, final String name, final double radius){
        // Move Map To Current Location
        moveMapTo(location, name);
        // Fetch address for location
        fetchAddressForLocation(location);
        // Create Geofence Area
        createGeofenceArea(new LatLng(location.getLatitude(), location.getLongitude()),
                radius);
    }

    /**
     * On Item Selected
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        geofenceTransitionTypeSelected = position;
    }

    /**
     * On Nothing Selected
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        geofenceTransitionTypeSelected = 0;
    }


    /**
     * Clear Place Suggestion
     */
    private void clearPlaceSuggestions(){
        recyclerViewAdapter.setData(new ArrayList<SuggestedPlaceEntity>());
        recyclerViewAdapter.notifyDataSetChanged();
        placesSuggestionsContainerView.setVisibility(View.GONE);
    }

    /**
     * Should Return Result
     * @return
     */
    private boolean shouldReturnResult(){
        return getCallingActivity() != null && getCallingActivity().getClassName()
                .equals(GeofencesListMvpActivity.class.getName());
    }

    @Override
    public void onHeaderClick() {}

    @Override
    public void onFooterClick() {}

    /**
     * On Item Click
     * @param suggestedPlaceEntity
     */
    @Override
    public void onItemClick(SuggestedPlaceEntity suggestedPlaceEntity) {
        Preconditions.checkNotNull(suggestedPlaceEntity, "Suggested Place can not be null");

        placesSuggestionsContainerView.setVisibility(View.GONE);


        mGeoDataClient.getPlaceById(suggestedPlaceEntity.getPlaceId())
                .addOnSuccessListener(new OnSuccessListener<PlaceBufferResponse>() {
                    @Override
                    public void onSuccess(PlaceBufferResponse places) {
                        final Place placeSelected = places.get(0);
                        LatLng queriedLocation = placeSelected.getLatLng();
                        final Location placeLocation = new Location("");
                        placeLocation.setLatitude(queriedLocation.latitude);
                        placeLocation.setLongitude(queriedLocation.longitude);
                        // Show Geofence for location selected
                        showGeofenceForLocation(placeLocation, appUtils.isValidString(name) ?
                                name: getString(R.string.current_location), currentCircleIndicator.getRadius());
                        places.release();
                    }
                });
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
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

            Task<AutocompletePredictionBufferResponse> results =
                    mGeoDataClient.getAutocompletePredictions(newText, spainLatLngBounds,
                            autocompleteFilter);
            results.addOnSuccessListener(this);
            results.addOnFailureListener(this);

            recyclerViewAdapter.setHighlightText(newText);

        } else {
            clearPlaceSuggestions();
        }
        return true;
    }

    /**
     * On Success
     * @param autocompletePredictions
     */
    @Override
    public void onSuccess(AutocompletePredictionBufferResponse autocompletePredictions) {
        Iterator<AutocompletePrediction> iterator = autocompletePredictions.iterator();
        final List<SuggestedPlaceEntity> resultList = new ArrayList<>(autocompletePredictions.getCount());
        final CharacterStyle STYLE_BOLD = new StyleSpan(Typeface.BOLD);
        while (iterator.hasNext()) {
            AutocompletePrediction prediction = iterator.next();
            resultList.add(new SuggestedPlaceEntity(prediction.getPlaceId(), prediction.getPrimaryText(STYLE_BOLD).toString(),
                    prediction.getSecondaryText(STYLE_BOLD).toString()));
        }
        autocompletePredictions.release();

        // Notify Results to Recycler View Adapter
        recyclerViewAdapter.setData(resultList);
        recyclerViewAdapter.notifyDataSetChanged();
        placesSuggestionsContainerView.setVisibility(View.VISIBLE);
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }

    /**
     * On Failure
     * @param e
     */
    @Override
    public void onFailure(@NonNull Exception e) {
        Timber.e(e);
    }

    /**
     * Address Result Receiver
     */
    class AddressResultReceiver extends ResultReceiver {

        /**
         *
         * @param handler
         */
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultData == null) {
                return;
            }

            // Display the address string
            // or an error message sent from the intent service.
            String mAddressOutput = resultData.getString(FetchAddressIntentService.RESULT_DATA_KEY);
            if (mAddressOutput == null) {
                mAddressOutput = "";
            }

            // Show a toast message if an address was found.
            if (resultCode == FetchAddressIntentService.SUCCESS_RESULT) {
                originLocationAddressTextView.setText(mAddressOutput);
            }

        }
    }

}
