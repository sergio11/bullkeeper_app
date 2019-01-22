package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.ramotion.fluidslider.FluidSlider;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpValidationMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerGeofenceComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.GeofenceComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.services.FetchAddressIntentService;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceTransitionTypeEnum;
import timber.log.Timber;

/**
 * Save Geofence Mvp Activity
 */
public class SaveGeofenceMvpActivity extends SupportMvpValidationMvpActivity<SaveGeofencePresenter, ISaveGeofenceView>
        implements HasComponent<GeofenceComponent>,
        ISaveGeofenceView, OnMapReadyCallback, GoogleMap.OnMapClickListener,
        GoogleApiClient.ConnectionCallbacks{

    private final String CONTENT_FULL_NAME = "SAVE_GEOFENCE";
    private final String CONTENT_TYPE_NAME = "GEOFENCES";

    private final static String NAME_FIELD_NAME = "name";

    /**
     * Default Values
     */
    private static final int TARGET_ZOOM = 17;
    private static final int MIN_RADIUS_VALUE = 20;
    private static final int MAX_RADIUS_VALUE = 2000;


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
    protected double radius;

    /**
     * Type
     */
    @State
    protected GeofenceTransitionTypeEnum type;

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
     * Name Input
     */
    @BindView(R.id.nameInput)
    @NotEmpty(messageResId = R.string.name_not_empty_error)
    @Length(min = 3, max = 15)
    protected AppCompatEditText nameInput;

    /**
     * Google Map
     */
    private GoogleMap googleMap;

    /**
     * Current Circle Indicator
     */
    private Circle currentCircleIndicator;

    /**
     * Spain LatLng Bounds
     */
    private final LatLngBounds spainLatLngBounds = new LatLngBounds(
            new LatLng(-9.39288367353, 35.946850084),
            new LatLng(3.03948408368, 43.7483377142));


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

        geofenceRadiusSlider.setStartText(String.valueOf(MIN_RADIUS_VALUE));
        geofenceRadiusSlider.setEndText(String.valueOf(MAX_RADIUS_VALUE));
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
        showNoticeDialog(R.string.forms_is_not_valid);
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

        // Disable All Components
        toggleAllComponents(false);

        //getPresenter().

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
        validate();
    }


    /**
     * On Single Permission Granted
     * @param permission
     */
    @Override
    public void onSinglePermissionGranted(String permission) {
        super.onSinglePermissionGranted(permission);
    }

    /**
     * On Single Permission Rejected
     * @param permission
     */
    @Override
    public void onSinglePermissionRejected(String permission) {
        super.onSinglePermissionRejected(permission);
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
        showNoticeDialog(R.string.forms_is_not_valid);
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

        // Geofence State
        this.identity = geofenceEntity.getIdentity();
        this.kid = geofenceEntity.getKid();
        this.address = geofenceEntity.getAddress();
        this.latitude = geofenceEntity.getLat();
        this.longitude = geofenceEntity.getLog();
        this.name = geofenceEntity.getName();
        this.radius = geofenceEntity.getRadius();
        this.type = geofenceEntity.getType();

        nameInput.setText(name);
        originLocationAddressTextView.setText(address);
        // Set Visibility
        deleteGeofenceView.setVisibility(View.VISIBLE);
        // Set Radius
        geofenceRadiusSlider.setPosition((float) radius);

        // Load Map is needed
        if(googleMap == null)
            loadMapAsync();

        toggleAllComponents(true);


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
        this.googleMap.getUiSettings().setCompassEnabled(false);
        this.googleMap.getUiSettings().setMapToolbarEnabled(false);
        this.googleMap.getUiSettings().setZoomGesturesEnabled(true);
        this.googleMap.getUiSettings().setScrollGesturesEnabled(true);
        this.googleMap.setOnMapClickListener(this);

        if (appUtils.isValidString(identity)) {

            // Move Map To Current Position
            final Location location = new Location("");
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            moveMapTo(location, name);

            // Create Geofence Area
            createGeofenceArea(new LatLng(latitude, longitude), radius);

        } else {

            if(permissionManager.shouldAskPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                permissionManager.checkSinglePermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                        getString(R.string.location_permission_reason));
            } else {


            }
        }

    }


    /**
     * On Delete Geofence
     */
    @OnClick(R.id.deleteGeofence)
    protected void onDeleteGeofence(){
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
        fetchAddressForLocation(location);

        // Create Geofence Area
        createGeofenceArea(latLng, MIN_RADIUS_VALUE);

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
        getPresenter().deleteGeofenceById(kid, identity);
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
    }

    /**
     * Load Map Async
     */
    private void loadMapAsync(){
        if(getFragmentManager() != null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            if(mapFragment != null) {
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
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(false)
                .title(title));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
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
