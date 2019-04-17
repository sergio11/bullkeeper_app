package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerGeofenceComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.GeofenceComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.savescheduledblock.SaveScheduledBlockMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.GeofencesAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.GeofencesAlertsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;
import static sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save.SaveGeofenceMvpActivity.GEOFENCE_ADDED_ARG;

/**
 * Geofences List Activity
 */
public class GeofencesListMvpActivity extends SupportMvpLCEActivity<GeofencesListPresenter,
        IGeofencesListView, GeofenceEntity>
        implements HasComponent<GeofenceComponent>, IGeofencesListActivityHandler
        , IGeofencesListView,
        SupportItemTouchHelper.ItemTouchHelperListener, GeofencesAdapter.IGeofenceStatusListener {

    /**
     *
     */
    private final String CONTENT_FULL_NAME = "GEOFENCES_LIST";
    private final String CONTENT_TYPE_NAME = "GEOFENCES";

    /**
     * Add Geofence Request Code
     */
    private final static int ADD_GEOFENCE_REQUEST_CODE = 237;

    /**
     * Args
     */
    private final static String KID_ID_ARG = "KID_ID_ARG";

    /**
     * Result Args
     */
    public final static String GEOFENCE_SELECTED_ARG = "GEOFENCE_SELECTED_ARG";

    /**
     * Geofence Component
     */
    private GeofenceComponent geofenceComponent;

    /**
     *
     * Dependencies
     * ===============
     *
     */

    /**
     * State
     * ================
     *
     */

    /**
     * Kid
     */
    @State
    protected String kid;

    /**
     * Views
     * =============
     */

    /**
     * Geofence Title Text View
     */
    @BindView(R.id.geofenceTitle)
    protected TextView geofenceTitleTextView;

    /**
     * Delete All Geofences
     */
    @BindView(R.id.deleteAllGeofences)
    protected ImageView deleteAllGeofencesImageView;

    /**
     * Add Geofences
     */
    @BindView(R.id.addGeofences)
    protected ImageView addGeofencesImageView;


    /**
     * Geofences Adapter
     */
    private GeofencesAdapter geofencesAdapter;


    /**
     * Get Calling Intent
     * @param context
     * @param kid
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid) {
        final Intent intent = new Intent(context, GeofencesListMvpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(KID_ID_ARG, kid);
        return intent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.geofenceComponent = DaggerGeofenceComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.geofenceComponent.inject(this);
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
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public GeofencesListPresenter providePresenter() {
        return geofenceComponent.geofencesListPresenter();
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
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        super.getArgs();
        final Bundle args = new Bundle();
        args.putString(GeofencesListPresenter.KID_ID_ARG, kid);
        return args;
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * On Item Clicked
     * @param geofenceEntity
     */
    @Override
    public void onItemClick(final GeofenceEntity geofenceEntity) {
        Preconditions.checkNotNull(geofenceEntity, "Geofence Entity can not be null");
        Preconditions.checkNotNull(geofenceEntity.getIdentity(), "Geofence Identity can not be null");
        Preconditions.checkState(!geofenceEntity.getIdentity().isEmpty(), "Geofence Identity can not be empty");
        Preconditions.checkNotNull(geofenceEntity.getKid(), "Geofence Kid can not be null");
        Preconditions.checkState(!geofenceEntity.getKid().isEmpty(), "Geofence Kid can not be empty");

        if(shouldReturnResult()){

            final Intent geofenceSelectedIntentResult = new Intent();
            geofenceSelectedIntentResult.putExtra(GEOFENCE_SELECTED_ARG, geofenceEntity);
            onResultOk(geofenceSelectedIntentResult);
            finish();

        } else {

            navigatorImpl.navigateToSaveGeofence(this, geofenceEntity.getKid(),
                    geofenceEntity.getIdentity());
        }
    }


    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}

    /**
     * On Swiped
     * @param viewHolder
     * @param direction
     * @param position
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (viewHolder instanceof GeofencesAdapter.GeofenceViewHolder) {

            final Integer deletedIndex = viewHolder.getAdapterPosition();
            final GeofenceEntity geofenceEntity =
                    recyclerViewAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            recyclerViewAdapter.removeItem(deletedIndex);

            if(!isConnectivityAvailable()) {
                showNoticeDialog(R.string.connectivity_not_available, false);
                recyclerViewAdapter.restoreItem(geofenceEntity, deletedIndex);
            } else {
                showLongSimpleSnackbar(content, getString(R.string.geofence_item_removed), getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerViewAdapter.restoreItem(geofenceEntity, deletedIndex);
                    }
                }, new Snackbar.Callback(){
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        if(event == DISMISS_EVENT_TIMEOUT || event == DISMISS_EVENT_CONSECUTIVE) {
                            // Delete Invitation
                            getPresenter().deleteById(kid, geofenceEntity.getIdentity());
                        }
                    }
                });
            }
        }
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
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_geofence_list;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);
        prepareView(getIntent());
    }

    /**
     * On New Intent
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        prepareView(getIntent());
    }

    /**
     * Prepare View
     * @param intent
     */
    protected void prepareView(final Intent intent) {

        if (intent.getExtras() != null) {
            final Bundle extras = intent.getExtras();
            if (!extras.containsKey(KID_ID_ARG))
                throw new IllegalArgumentException("You must provide kid id");
            kid = extras.getString(KID_ID_ARG);
        } else {
            throw new IllegalArgumentException("You must provide args");
        }

        if(!shouldReturnResult()) {

            deleteAllGeofencesImageView.setVisibility(View.VISIBLE);
            deleteAllGeofencesImageView.setEnabled(true);


            // adding item touch helper
            ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                    new SupportItemTouchHelper<GeofencesAdapter.GeofenceViewHolder>(0, ItemTouchHelper.LEFT, this);
            new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        } else {

            deleteAllGeofencesImageView.setVisibility(View.GONE);
            deleteAllGeofencesImageView.setEnabled(false);

        }

    }


    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<GeofenceEntity> getAdapter() {
        geofencesAdapter = new GeofencesAdapter(this, new ArrayList<GeofenceEntity>());
        geofencesAdapter.setListener(this);
        return geofencesAdapter;

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
     * On All Geofences Deleted
     */
    @Override
    public void onAllGeofencesDeleted() {
        showNoticeDialog(R.string.geofences_cleared_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                recyclerViewAdapter.removeAll();
                closeActivity();
            }
        });
    }

    /**
     * On Geofence Deleted
     */
    @Override
    public void onGeofenceDeleted() {
        if(recyclerView.getAdapter() != null) {
            final int itemCount = recyclerView.getAdapter().getItemCount();

            if(itemCount > 0)
                geofenceTitleTextView.setText(String.format(Locale.getDefault(),
                        getString(R.string.geofences_title_count), recyclerView.getAdapter().getItemCount()));
            else
                onNoDataFound();
        }

    }

    /**
     *
     * @param geofence
     * @param newStatus
     */
    @Override
    public void onGeofenceStatusChangedSuccessfully(final String geofence, boolean newStatus) {
        showNoticeDialog(R.string.geofence_status_successfully_updated);
    }

    /**
     *
     * @param geofence
     * @param newStatus
     */
    @Override
    public void onGeofenceStatusChangedFailed(final String geofence, boolean newStatus) {
        Preconditions.checkNotNull(geofence, "Geofence can not be null");
        Preconditions.checkState(!geofence.isEmpty(), "Geofence can not be null");
        showNoticeDialog(R.string.geofence_status_updated_error, false);
        geofencesAdapter.updateStatus(geofence, !newStatus);
    }



    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<GeofenceEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);

        if(!shouldReturnResult()) {
            deleteAllGeofencesImageView.setVisibility(View.VISIBLE);
            deleteAllGeofencesImageView.setEnabled(true);
        }

        geofenceTitleTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.geofences_title_count),dataLoaded.size()));

    }

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        super.onNoDataFound();
        deleteAllGeofencesImageView.setVisibility(View.GONE);
        deleteAllGeofencesImageView.setEnabled(false);
        geofenceTitleTextView
                .setText(getString(R.string.geofences_title_default));

    }

    /**
     * On Delete All Geofences Clicked
     */
    @OnClick(R.id.deleteAllGeofences)
    protected void onDeleteAllGeofencesClicked(){

        if(!isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            showConfirmationDialog(R.string.geofences_confirm_delete_all, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    getPresenter().deleteAllByKid(kid);
                }

                @Override
                public void onRejected(DialogFragment dialog) {}
            });
        }
    }

    /**
     * On Add Geofences
     */
    @OnClick(R.id.addGeofences)
    protected void onAddGeofences(){
        if(!shouldReturnResult())
            navigatorImpl.navigateToSaveGeofence(this, kid);
        else
            navigatorImpl.navigateToSaveGeofence(this, kid, ADD_GEOFENCE_REQUEST_CODE);
    }


    /**
     * On Activity Result
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_GEOFENCE_REQUEST_CODE) {

            if(Activity.RESULT_OK == resultCode) {

                final Bundle args = data.getExtras();

                if(args != null && args.containsKey(GEOFENCE_ADDED_ARG)) {
                    final Intent geofenceSelectedIntentResult = new Intent();
                    geofenceSelectedIntentResult.putExtra(GEOFENCE_SELECTED_ARG, args.getSerializable(GEOFENCE_ADDED_ARG));
                    onResultOk(geofenceSelectedIntentResult);
                    finish();
                }

            }

        }

    }

    /**
     * On Back Pressed
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(shouldReturnResult()) {
            onResultCanceled();
            finish();
        }
    }

    /**
     * Should Return Result
     * @return
     */
    private boolean shouldReturnResult(){
        return getCallingActivity() != null && getCallingActivity().getClassName()
                .equals(SaveScheduledBlockMvpActivity.class.getName());
    }

    /**
     * @param identity
     * @param newStatus
     */
    @Override
    public void onGeofenceStatusChanged(final String identity, final boolean newStatus) {
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be null");

        if(!isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {

            if (newStatus) {

                showConfirmationDialog(R.string.geofence_confirm_enable, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                    @Override
                    public void onAccepted(DialogFragment dialog) {
                        getPresenter().enableGeofence(kid, identity);
                    }

                    @Override
                    public void onRejected(DialogFragment dialog) {
                        geofencesAdapter.updateStatus(identity, false);
                    }
                });

            } else {

                showConfirmationDialog(R.string.geofence_confirm_disable, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                    @Override
                    public void onAccepted(DialogFragment dialog) {
                        getPresenter().disableGeofence(kid, identity);
                    }

                    @Override
                    public void onRejected(DialogFragment dialog) {
                        geofencesAdapter.updateStatus(identity, true);
                    }
                });
            }
        }
    }
}
