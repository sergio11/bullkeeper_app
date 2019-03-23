package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidrequestdetail;

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
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.KidRequestComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequestdetail.IKidRequestDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.services.FetchAddressIntentService;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;
import sanchez.sanchez.sergio.domain.models.LocationEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Kid Request Activity Fragment
 */
public class KidRequestDetailActivityMvpFragment extends SupportMvpFragment<KidRequestDetailFragmentPresenter,
        IKidRequestDetailView, IKidRequestDetailActivityHandler, KidRequestComponent>
        implements IKidRequestDetailView, OnMapReadyCallback {

    private static final int TARGET_ZOOM = 17;

    /**
     * Args
     */
    public static String CHILD_ID_ARG = "KID_ID_ARG";
    public static String ID_ARG = "ID_ARG";

    /**
     * Views
     * =============
     */

    /**
     * Kid Request At Text View
     */
    @BindView(R.id.kidRequestAt)
    protected TextView kidRequestAtTextView;

    /**
     * Kid Request Device Name Text View
     */
    @BindView(R.id.kidRequestDeviceName)
    protected TextView kidRequestDeviceNameTextView;

    /**
     * Kid Request Type
     */
    @BindView(R.id.kidRequestType)
    protected ImageView kidRequestTypeImageView;

    /**
     * Kid Request Type Title
     */
    @BindView(R.id.kidRequestTypeTitle)
    protected TextView kidRequestTypeTitleTextView;

    /**
     * Kid Request Location Address Text View
     */
    @BindView(R.id.kidRequestLocationAddress)
    protected TextView kidRequestLocationAddressTextView;

    /**
     * Location Available View
     */
    @BindView(R.id.locationAvailable)
    protected View locationAvailableView;

    /**
     * Location Not Available
     */
    @BindView(R.id.locationNotAvailable)
    protected View locationNotAvailableView;

    /**
     * Dependencies
     * ===============
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
     * State
     * =============
     */

    /**
     * Child ID
     */
    @State
    protected String childId;

    /**
     * ID
     */
    @State
    protected String id;

    /**
     * Kid Request Entity
     */
    @State
    protected KidRequestEntity kidRequestEntity;


    /**
     * Google Map
     */
    private GoogleMap googleMap;

    /**
     * Current Circle Indicator
     */
    private Circle currentCircleIndicator;

    /**
     * Current Marker Indicator
     */
    private Marker currentMarkerIndicator;

    public KidRequestDetailActivityMvpFragment() { }

    /**
     * New Instance
     * @param kid
     * @param id
     */
    public static KidRequestDetailActivityMvpFragment newInstance(final String kid, final String id) {
        final KidRequestDetailActivityMvpFragment kidRequestDetailActivityFragment =
                new KidRequestDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(CHILD_ID_ARG, kid);
        args.putString(ID_ARG, id);
        kidRequestDetailActivityFragment.setArguments(args);
        return kidRequestDetailActivityFragment;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        return getArguments();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get Child Id
        if(!getArgs().containsKey(CHILD_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(CHILD_ID_ARG)))
            throw new IllegalArgumentException("You must provide a child id");

        childId = getArgs().getString(CHILD_ID_ARG);

        // Get Id
        if(!getArgs().containsKey(ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(ID_ARG)))
            throw new IllegalArgumentException("You must provide a id");

        id = getArgs().getString(ID_ARG);


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
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_kid_request_detail;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(KidRequestComponent component) {
        component.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public KidRequestDetailFragmentPresenter providePresenter() {
        return component.kidRequestDetailFragmentPresenter();
    }


    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return RETURN_TOOLBAR;
    }

    /**
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        activityHandler.showNoticeDialog(R.string.network_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        activityHandler.showNoticeDialog(R.string.unexpected_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Kid Request Loaded
     * @param kidRequestEntity
     */
    @Override
    public void onKidRequestLoaded(final KidRequestEntity kidRequestEntity) {
        Preconditions.checkNotNull(kidRequestEntity, "Kid Request can not be null");

        this.kidRequestEntity = kidRequestEntity;

        switch (kidRequestEntity.getType()) {
            case PICKMEUP:
                kidRequestTypeImageView.setImageResource(R.drawable.car_cyan_solid);
                kidRequestTypeTitleTextView.setText(R.string.kid_request_pickmeup_event_type_name);
                break;
            case SOS:
                kidRequestTypeImageView.setImageResource(R.drawable.sos_cyan_icon);
                kidRequestTypeTitleTextView.setText(R.string.kid_request_sos_event_type_name);
                break;
        }

        final SimpleDateFormat sdf  = new SimpleDateFormat(getString(R.string.date_time_format),
                Locale.getDefault());

        kidRequestAtTextView.setText(String.format(
                Locale.getDefault(), "%s (%s)", sdf.format(kidRequestEntity.getRequestAt()),
                kidRequestEntity.getSince()
        ));

        // Set Device Name
        kidRequestDeviceNameTextView.setText(
                String.format(Locale.getDefault(),
                        "%s - %s", kidRequestEntity.getTerminal().getDeviceName(),
                        kidRequestEntity.getTerminal().getModel()));


        if(kidRequestEntity.getLocation() != null) {

            // The latitude must be a number between -90 and 90 and the longitude between -180 and 180.

            if(kidRequestEntity.getLocation().getLat() >= -90 &&
                    kidRequestEntity.getLocation().getLat() <= 90 &&
                    kidRequestEntity.getLocation().getLog() >= -180 &&
                    kidRequestEntity.getLocation().getLog() <= 180) {

                locationAvailableView.setVisibility(View.VISIBLE);
                locationNotAvailableView.setVisibility(View.GONE);


                if(appUtils.isValidString(kidRequestEntity.getLocation().getAddress())) {
                    kidRequestLocationAddressTextView.setText(
                            kidRequestEntity.getLocation().getAddress()
                    );
                } else {
                    final Location location = new Location("");
                    location.setLatitude(kidRequestEntity.getLocation().getLat());
                    location.setLongitude(kidRequestEntity.getLocation().getLog());
                    fetchAddressForLocation(location);
                }

                if(getFragmentManager() != null) {
                    SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                    if(mapFragment != null) {
                        mapFragment.getMapAsync(this);
                    }
                }

            } else {
                locationAvailableView.setVisibility(View.GONE);
                locationNotAvailableView.setVisibility(View.VISIBLE);
            }

        }  else {
            locationAvailableView.setVisibility(View.GONE);
            locationNotAvailableView.setVisibility(View.VISIBLE);
        }

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
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
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
     * @param kidName
     * @param kidPhoto
     * @param locationEntity
     */
    private void showLocation(final String kidName, final String kidPhoto, final LocationEntity locationEntity) {
        Preconditions.checkNotNull(googleMap, "Google Map can not be null");

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
     * Start Intent Service
     */
    protected void fetchAddressForLocation(final Location location) {
        Intent intent = new Intent(activity, FetchAddressIntentService.class);
        intent.putExtra(FetchAddressIntentService.RECEIVER, new AddressResultReceiver(new Handler()));
        intent.putExtra(FetchAddressIntentService.LOCATION_DATA_EXTRA, location);
        activity.startService(intent);
    }

    /**
     * On Kid Request Deleted
     */
    @Override
    public void onKidRequestDeleted() {
        showNoticeDialog(R.string.kid_request_successfully_deleted, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Kid Request Not Found
     */
    @Override
    public void onKidRequestNotFound() {
        activityHandler.showNoticeDialog(R.string.kid_request_not_found, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
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

        final String kidName = kidRequestEntity.getKid().getFullName();
        final String kidPhoto = kidRequestEntity.getKid().getProfileImage();
        final LocationEntity locationEntity = kidRequestEntity.getLocation();
        showLocation(kidName, kidPhoto, locationEntity);
    }

    /**
     * On Delete Kid Request Clicked
     */
    @OnClick(R.id.deleteKidRequest)
    protected void onDeleteKidRequestClicked(){

        if (!activityHandler.isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            showConfirmationDialog(R.string.kid_request_delete_request_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {

                @Override
                public void onAccepted(DialogFragment dialog) {
                    getPresenter().deleteKidRequest(childId, id);
                }

                @Override
                public void onRejected(DialogFragment dialog) {}
            });
        }


    }

    /**
     * Open Conversation
     */
    @OnClick(R.id.openConversation)
    protected void onOpenConversation(){
        activityHandler.navigateToConversationMessagesList(childId);
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
                kidRequestLocationAddressTextView.setText(mAddressOutput);
            } else {
                kidRequestLocationAddressTextView.setText(getString(R.string.no_address_found));
            }

        }
    }
}
