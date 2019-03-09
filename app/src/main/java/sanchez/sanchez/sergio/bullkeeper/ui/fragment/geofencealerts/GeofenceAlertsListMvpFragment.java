package sanchez.sanchez.sergio.bullkeeper.ui.fragment.geofencealerts;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.GeofenceComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save.ISaveGeofenceActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.GeofencesAlertsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.domain.models.GeofenceAlertEntity;


/**
 * Geofence Alerts List Fragment
 */
public class GeofenceAlertsListMvpFragment extends SupportMvpLCEFragment<GeofenceAlertsListFragmentPresenter,
        IGeofenceAlertsListFragmentView, ISaveGeofenceActivityHandler, GeofenceComponent, GeofenceAlertEntity>
        implements IGeofenceAlertsListFragmentView {

    /**
     * Kid Identity Arg
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    private static final String GEOFENCE_IDENTITY_ARG = "GEOFENCE_IDENTITY_ARG";


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
     * Activity
     */
    @Inject
    protected Activity activity;

    /**
     * Views
     * =============
     */


    @BindView(R.id.deleteGeofenceAlerts)
    protected ImageView deleteGeofenceAlertsImageView;


    /**
     * State
     * ===================
     */

    /**
     * Kid Identity
     */
    @State
    protected String kid;

    /**
     * Geofence Identity
     */
    @State
    protected String geofence;


    /**
     *
     */
    public GeofenceAlertsListMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kid
     * @param geofence
     * @return
     */
    public static GeofenceAlertsListMvpFragment newInstance(final String kid, final String geofence) {
        GeofenceAlertsListMvpFragment fragment = new GeofenceAlertsListMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kid);
        args.putString(GEOFENCE_IDENTITY_ARG, geofence);
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

        kid = getArguments().getString(KID_IDENTITY_ARG);

        if(getArguments() == null ||
                !getArguments().containsKey(GEOFENCE_IDENTITY_ARG))
            throw new IllegalStateException("You must provide geofence identity - Illegal State");

        geofence = getArguments().getString(GEOFENCE_IDENTITY_ARG);

        // Enable Nested Scrolling on Recycler View
        ViewCompat.setNestedScrollingEnabled(recyclerView, true);
    }


    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<GeofenceAlertEntity> getAdapter() {
        return new GeofencesAlertsAdapter(activity, new ArrayList<GeofenceAlertEntity>());
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(GeofenceAlertsListFragmentPresenter.KID_IDENTITY_ARG, kid);
        args.putString(GeofenceAlertsListFragmentPresenter.GEOFENCE_IDENTITY_ARG, geofence);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_geofence_alerts_list;
    }

    /**
     * Initialize Injector
     * @param component
     */
    @Override
    protected void initializeInjector(GeofenceComponent component) {
        component.inject(this);
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     *
     * @param item
     */
    @Override
    public void onItemClick(GeofenceAlertEntity item) {}


    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public GeofenceAlertsListFragmentPresenter providePresenter() {
        return component.geofenceAlertsListFragmentPresenter();
    }

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        super.onNoDataFound();
        deleteGeofenceAlertsImageView.setVisibility(View.GONE);
    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<GeofenceAlertEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);
        deleteGeofenceAlertsImageView.setVisibility(View.VISIBLE);
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        super.onOtherException();
        deleteGeofenceAlertsImageView.setVisibility(View.GONE);
    }

    /**
     * On Delete Geofence Alerts
     */
    @OnClick(R.id.deleteGeofenceAlerts)
    protected void onDeleteGeofenceAlerts(){
        if(!activityHandler.isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            showConfirmationDialog(R.string.geofence_alerts_delete_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    getPresenter().deleteAll(kid, geofence);
                }

                @Override
                public void onRejected(DialogFragment dialog) {

                }
            });
        }

    }
}

