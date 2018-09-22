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
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fernandocejas.arrow.checks.Preconditions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportDialogFragment;
import timber.log.Timber;

/**
 * Search School Location Dialog
 */
public final class SearchSchoolLocationDialog extends SupportDialogFragment
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerClickListener{

    public static final String TAG = "SEARCH_SCHOOL_LOCATION_DIALOG";


    private ISearchSchoolListener searchSchoolListener;

    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;


    /**
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity) {
        final SearchSchoolLocationDialog searchSchoolDialogFragment = new SearchSchoolLocationDialog();
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

    @Override
    protected void initializeInjector() {
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Initializing googleApiClient
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
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
        googleApiClient.connect();
        super.onStart();
    }

    /**
     * On Stop
     */
    @Override
    public void onStop() {
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
    private void moveMapTo(final Location location) {
        Preconditions.checkNotNull(googleMap, "Google Map can not be null");
        Preconditions.checkNotNull(location, "Location can not be null");
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true)
                .title("Marker in India"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        googleMap.getUiSettings().setZoomControlsEnabled(true);

    }

    /**
     * On Connected
     * @param bundle
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Timber.d("Google Api Client Connected ");
        final Location currentLocation = getCurrentLocation();
        moveMapTo(currentLocation);
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Timber.d("Google Api Client Connected Failed");
    }

    /**
     * On Map Long Click
     * @param latLng
     */
    @Override
    public void onMapLongClick(LatLng latLng) {
        Timber.d("On Map Click");
        if(searchSchoolListener != null) {
            try {
                final Address address = getAddressFromLocation(latLng);
                Timber.d("Address -> %s", address.toString());
                searchSchoolListener.onSelectLocation(latLng.latitude, latLng.longitude, address.getFeatureName(),
                        address.getLocality());
                dismiss();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

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
