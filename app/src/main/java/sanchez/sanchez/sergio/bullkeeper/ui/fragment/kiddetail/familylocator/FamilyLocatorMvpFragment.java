package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.familylocator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fernandocejas.arrow.checks.Preconditions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IKidLocationEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.events.impl.KidLocationUpdatedEvent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.services.FetchAddressIntentService;
import sanchez.sanchez.sergio.domain.models.LocationEntity;

/**
 * Family Locator Fragment
 */
public class FamilyLocatorMvpFragment extends SupportMvpFragment<FamilyLocatorFragmentPresenter,
        IFamilyLocatorFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent>
        implements IFamilyLocatorFragmentView, OnMapReadyCallback, SpeedDialView.OnActionSelectedListener {

    /**
     * Args
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    private static final String KID_NAME_ARG = "KID_NAME_ARG";
    private static final String KID_PHOTO_ARG = "KID_PHOTO_ARG";

    private static final int TARGET_ZOOM = 17;

    /**
     * Dependencies
     * =================
     */

    /**
     * App Context
     */
    @Inject
    protected Context appContext;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * Activity
     */
    @Inject
    protected Activity activity;

    /**
     * Local System Notification
     */
    @Inject
    protected ILocalSystemNotification localSystemNotification;

    /**
     * Views
     * ================
     */

    @BindView(R.id.familyLocatorMapContainer)
    protected ViewGroup familyLocatorMapContainerViewGroup;

    /**
     * Options Menu Speed Dial View
     */
    @BindView(R.id.optionsMenu)
    protected SpeedDialView optionsMenuSpeedDialView;

    /**
     * Current Location Address
     */
    @BindView(R.id.currentLocationAddress)
    protected TextView currentLocationAddressTextView;

    /**
     * State
     * ===================
     */

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;

    /**
     * Kid Name
     */
    @State
    protected String kidName;

    /**
     * Kid Photo
     */
    @State
    protected String kidPhoto;

    /**
     * Kid Location Register Key
     */
    @State
    protected int kidLocationRegisterKey;

    /**
     * Google Map
     */
    private GoogleMap googleMap;

    /**
     * Current Circle Indicator
     */
    private Circle currentCircleIndicator;

    /**
     * Curretn Marker Indicator
     */
    private Marker currentMarkerIndicator;

    /**
     * Kid Location Event Visitor
     */
    private IKidLocationEventVisitor kidLocationEventVisitor = new IKidLocationEventVisitor() {
        @Override
        public void visit(KidLocationUpdatedEvent kidLocationUpdatedEvent) {
            Preconditions.checkNotNull(kidLocationUpdatedEvent, "Kid location update can not be null");
            final LocationEntity locationEntity = new LocationEntity();
            locationEntity.setLat(kidLocationUpdatedEvent.getLatitude());
            locationEntity.setLog(kidLocationUpdatedEvent.getLongitude());
            locationEntity.setAddress(kidLocationUpdatedEvent.getAddress());
            showLocation(locationEntity);
        }
    };

    /**
     *
     */
    public FamilyLocatorMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static FamilyLocatorMvpFragment newInstance(
            final String kidIdentity,
            final String kidName,
            final String kidPhoto) {
        FamilyLocatorMvpFragment fragment = new FamilyLocatorMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        args.putString(KID_NAME_ARG, kidName);
        args.putString(KID_PHOTO_ARG, kidPhoto);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() == null ||
                !getArguments().containsKey(KID_IDENTITY_ARG))
            throw new IllegalStateException("You must provide kid identity - Illegal State");

        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);

        if(getArguments() == null ||
                !getArguments().containsKey(KID_NAME_ARG))
            throw new IllegalStateException("You must provide kid name - Illegal State");

        kidName = getArguments().getString(KID_NAME_ARG);

        // Kid Photo
        kidPhoto = getArguments().getString(KID_PHOTO_ARG);

        if(getFragmentManager() != null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            if(mapFragment != null) {
                mapFragment.getMapAsync(this);
            }
        }

        optionsMenuSpeedDialView.setOnActionSelectedListener(this);

        optionsMenuSpeedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.show_geofences, R.drawable.geofances_icon)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.cyanBrilliant, appContext.getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.commonWhite, appContext.getTheme()))
                        .setLabel(getString(R.string.family_locator_show_geofences))
                        .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.cyanBrilliant, appContext.getTheme()))
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.commonWhite, appContext.getTheme()))
                        .setLabelClickable(true)
                        .create()
        );

        optionsMenuSpeedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.add_geofences, R.drawable.add_school)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.cyanBrilliant, appContext.getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.commonWhite, appContext.getTheme()))
                        .setLabel(getString(R.string.family_locator_add_geofences))
                        .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.cyanBrilliant, appContext.getTheme()))
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.commonWhite, appContext.getTheme()))
                        .setLabelClickable(true)
                        .create()
        );

    }

    /**
     * On Destroy View
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FragmentManager fm = getFragmentManager();
        if(fm != null) {
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
        super.onStart();

        kidLocationRegisterKey = localSystemNotification
                .registerEventListener(KidLocationUpdatedEvent.class, kidLocationEventVisitor);
    }

    /**
     * On Stop
     */
    @Override
    public void onStop() {
        super.onStop();

        localSystemNotification.unregisterEventListener(kidLocationRegisterKey);
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(FamilyLocatorFragmentPresenter.KID_IDENTITY_ARG, kidIdentity);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_family_locator;
    }

    /**
     * Initialize Injector
     * @param component
     */
    @Override
    protected void initializeInjector(MyKidsComponent component) {
        component.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public FamilyLocatorFragmentPresenter providePresenter() {
        return component.familyLocatorFragmentPresenter();
    }

    /**
     * On Family Locator Info Clicked
     */
    @OnClick(R.id.familyLocatorInfo)
    protected void onFamilyLocatorInfoClicked(){
        activityHandler.showFamilyLocatorDialog();
    }

    /**
     * Create Circle
     * @param locationEntity
     * @return
     */
    private Circle createCircle(final LocationEntity locationEntity) {
        Preconditions.checkNotNull(googleMap, "Google Map can not be null");

        final CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(locationEntity.getLat(), locationEntity.getLog()))
                .radius(80)
                .strokeColor(ContextCompat.getColor(appContext, R.color.darkModerateBlue))
                .fillColor(ContextCompat.getColor(appContext, R.color.translucentCyanBrilliant));

        return googleMap.addCircle(circleOptions);
    }

    /**
     * Create Marker Indicator
     * @param viewMarker
     */
    private Marker createMarkerIndicator(final View viewMarker, final LocationEntity locationEntity, final String title) {
        Preconditions.checkNotNull(googleMap, "Google Map can not be null");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) activity).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        viewMarker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        viewMarker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        viewMarker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        viewMarker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(viewMarker.getMeasuredWidth(), viewMarker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        viewMarker.draw(canvas);

        final Marker marker =  googleMap.addMarker(new MarkerOptions().position(new LatLng(locationEntity.getLat(), locationEntity.getLog())).
                icon(BitmapDescriptorFactory.fromBitmap(bitmap)));

        marker.setTitle(title);

        return marker;
    }

    /**
     * Show Location
     * @param locationEntity
     */
    private void showLocation(final LocationEntity locationEntity) {
        Preconditions.checkNotNull(googleMap, "Google Map can not be null");

        // Fetch Address
        final Location location = new Location("");
        location.setLatitude(locationEntity.getLat());
        location.setLongitude(locationEntity.getLog());
        fetchAddressForLocation(location);

        // Load Current Position
        LatLng latLng = new LatLng(locationEntity.getLat(), locationEntity.getLog());
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, TARGET_ZOOM));

        if(currentCircleIndicator != null)
            currentCircleIndicator.remove();

        // Create Current Circle Indicator
        currentCircleIndicator = createCircle(locationEntity);

        if(currentMarkerIndicator != null)
            currentMarkerIndicator.remove();

        final LayoutInflater layoutInflater = (LayoutInflater)activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(layoutInflater != null) {
            final View viewMarker = layoutInflater.inflate(R.layout.child_marker_layout, null);
            CircleImageView childImageImageView =  viewMarker.findViewById(R.id.childImage);
            if(appUtils.isValidString(kidPhoto)) {
                picasso.load(kidPhoto)
                        .placeholder(R.drawable.kid_default_image)
                        .error(R.drawable.kid_default_image)
                        .into(childImageImageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                currentMarkerIndicator = createMarkerIndicator(viewMarker, locationEntity,
                                        kidName);
                            }

                            @Override
                            public void onError() {
                                currentMarkerIndicator = createMarkerIndicator(viewMarker,locationEntity,
                                        kidName);
                            }
                        });
            } else {
                childImageImageView.setImageResource(R.drawable.kid_default_image);
                currentMarkerIndicator =  createMarkerIndicator(viewMarker,locationEntity,
                        kidName);
            }

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
        this.googleMap.getUiSettings().setZoomControlsEnabled(false);
        this.googleMap.getUiSettings().setCompassEnabled(false);
        this.googleMap.getUiSettings().setMapToolbarEnabled(false);
        this.googleMap.getUiSettings().setZoomGesturesEnabled(false);
        this.googleMap.getUiSettings().setScrollGesturesEnabled(false);

        getPresenter().getCurrentLocation();
    }

    /**
     * On Actions Selected
     * @param actionItem
     * @return
     */
    @Override
    public boolean onActionSelected(SpeedDialActionItem actionItem) {
        switch (actionItem.getId()) {
            case R.id.show_geofences:
                activityHandler.navigateToGeofences(kidIdentity);
                return false;
            case R.id.add_geofences:
                activityHandler.navigateToSaveGeofence(kidIdentity);
                return false;
            default:
                return false;
        }
    }

    /**
     * On Current Location Loaded
     * @param locationEntity
     */
    @Override
    public void onCurrentLocationLoaded(final LocationEntity locationEntity) {
        Preconditions.checkNotNull(locationEntity, "Location Entity can not be null");
        // Show Current Location
        showLocation(locationEntity);

    }

    /**
     * No Current Location Found
     */
    @Override
    public void noCurrentLocationFound() {
        showNoticeDialog(R.string.family_locator_no_last_registered_position);
    }

    /**
     * Start Intent Service
     */
    protected void fetchAddressForLocation(final Location location) {
        Intent intent = new Intent(activity, FetchAddressIntentService.class);
        intent.putExtra(FetchAddressIntentService.RECEIVER, new AddressResultReceiver(new Handler()));
        intent.putExtra(FetchAddressIntentService.LOCATION_DATA_EXTRA, location);
        activity.startService(intent);
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
                currentLocationAddressTextView.setText(mAddressOutput);
            }

        }
    }
}

