package sanchez.sanchez.sergio.bullkeeper.ui.fragment.familylocator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
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
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import timber.log.Timber;

/**
 * Family Locator Fragment
 */
public class FamilyLocatorMvpFragment extends SupportMvpFragment<FamilyLocatorFragmentPresenter,
        IFamilyLocatorFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent>
        implements IFamilyLocatorFragmentView, OnMapReadyCallback, SpeedDialView.OnActionSelectedListener {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

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
     * State
     * ===================
     */

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;

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
    public static FamilyLocatorMvpFragment newInstance(final String kidIdentity) {
        FamilyLocatorMvpFragment fragment = new FamilyLocatorMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
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
            throw new IllegalStateException("You must provide son identity - Illegal State");

        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);

        if(getFragmentManager() != null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            if(mapFragment != null) {
                mapFragment.getMapAsync(this);
            }
        }

        optionsMenuSpeedDialView.setOnActionSelectedListener(this);

        optionsMenuSpeedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.config_geofences, R.drawable.geofances_icon)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.cyanBrilliant, appContext.getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.commonWhite, appContext.getTheme()))
                        .setLabel(getString(R.string.family_locator_add_geofences))
                        .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.cyanBrilliant, appContext.getTheme()))
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.commonWhite, appContext.getTheme()))
                        .setLabelClickable(false)
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
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(FamilyLocatorFragmentPresenter.SON_IDENTITY_ARG, kidIdentity);
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


    private void addMarker(final GoogleMap googleMap, final View viewMarker) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) activity).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        viewMarker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        viewMarker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        viewMarker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        viewMarker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(viewMarker.getMeasuredWidth(), viewMarker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        viewMarker.draw(canvas);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(39.074860d, -0.514380)).
                icon(BitmapDescriptorFactory.fromBitmap(bitmap)))
                .setTitle("Acha Khao Acha Khilao");

    }

    /**
     * Create Child Marker
     * @param context
     * @param image
     * @param name
     * @return
     */
    private void createChildMarker(Context context, final String image, String name, final GoogleMap googleMap) {

        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(layoutInflater != null) {
            final View viewMarker = layoutInflater.inflate(R.layout.child_marker_layout, null);
            CircleImageView childImageImageView =  viewMarker.findViewById(R.id.childImage);
            picasso.load(image)
                    .placeholder(R.drawable.kid_default_image)
                    .error(R.drawable.kid_default_image)
                    .into(childImageImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            addMarker(googleMap, viewMarker);
                        }

                        @Override
                        public void onError() {
                            addMarker(googleMap, viewMarker);
                        }


                    });

        }
    }

    /**
     * On Map Ready
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        Timber.d("Google Map Ready...");
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.setIndoorEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setCompassEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        LatLng latLng = new LatLng(39.074860d, -0.514380);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, TARGET_ZOOM));

        googleMap.addCircle(new CircleOptions()
                .center(new LatLng(39.074860d, -0.514380))
                .radius(80)
                .strokeColor(ContextCompat.getColor(appContext, R.color.darkModerateBlue))
                .fillColor(ContextCompat.getColor(appContext, R.color.translucentCyanBrilliant)));


        createChildMarker(activity, "https://avatars1.githubusercontent.com/u/6996211?s=88&v=4",
                "Sergio Sánchez", googleMap);


        showProgressDialog(R.string.family_locator_loading_child_position);

    }

    /**
     * On Actions Selected
     * @param actionItem
     * @return
     */
    @Override
    public boolean onActionSelected(SpeedDialActionItem actionItem) {
        switch (actionItem.getId()) {
            case R.id.config_geofences:
                showLongMessage("Config Geofences Clicked");
                return false;
            default:
                return false;
        }
    }
}

