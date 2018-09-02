package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageButton;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpLCEActivity;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.AlertsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerAlertsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.AlertsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import timber.log.Timber;

import static sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Alert List Activity
 */
public class AlertListMvpActivity extends SupportMvpLCEActivity<AlertListPresenter, IAlertListView, AlertEntity>
        implements HasComponent<AlertsComponent>, IAlertListActivityHandler
        , IAlertListView,
        SupportItemTouchHelper.ItemTouchHelperListener {

    /**
     * Alerts Component
     */
    private AlertsComponent alertsComponent;

    /**
     * Clear Alerts Button
     */
    @BindView(R.id.clearAlerts)
    protected ImageButton clearAlertsButton;

    /**
     * Filter Alerts Button
     */
    @BindView(R.id.filterAlerts)
    protected ImageButton filterAlertsButton;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, AlertListMvpActivity.class);
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.alertsComponent = DaggerAlertsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.alertsComponent.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public AlertListPresenter providePresenter() {
        return alertsComponent.alertListPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public AlertsComponent getComponent() {
        return alertsComponent;
    }


    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {

    }

    /**
     * On Item Click
     * @param alertEntity
     */
    @Override
    public void onItemClick(AlertEntity alertEntity) {
        Timber.d("Go to Alert Detail -> %s", alertEntity.getIdentity());
        goToAlertDetail(alertEntity.getIdentity());
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {

    }

    /**
     * On Clear All Alerts
     */
    @OnClick(R.id.clearAlerts)
    public void onClearAllAlerts() {


        // Show Confirmation Dialog
        showConfirmationDialog(R.string.my_alerts_clear_all, new ConfirmationDialogFragment.ConfirmationDialogListener() {

            /**
             * On Accepted
             * @param dialog
             */
            @Override
            public void onAccepted(DialogFragment dialog) {
                getPresenter().clearAlerts();
            }

            /**
             * On Rejected
             * @param dialog
             */
            @Override
            public void onRejected(DialogFragment dialog) {
                showShortMessage(getString(R.string.operation_cancelled));
            }
        });

    }

    /**
     * On Filter Alerts
     */
    @OnClick(R.id.filterAlerts)
    public void onFilterAlerts() {
        // Show Filter Alerts Dialog
        navigatorImpl.showFilterAlertsDialog(this);
    }

    /**
     * On Swiped
     * @param viewHolder
     * @param direction
     * @param position
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof AlertsAdapter.AlertsViewHolder) {

            final Integer deletedIndex = viewHolder.getAdapterPosition();
            final AlertEntity alertEntity = recyclerViewAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            recyclerViewAdapter.removeItem(deletedIndex);

            showLongSimpleSnackbar(content, getString(R.string.alert_item_removed), getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewAdapter.restoreItem(alertEntity, deletedIndex);
                }
            }, new Snackbar.Callback(){
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    if(event == DISMISS_EVENT_TIMEOUT) {
                        // Delete Alert Of Son
                        getPresenter().deleteAlertOfSon(alertEntity.getSon().getIdentity(),
                                alertEntity.getIdentity());
                    }
                }
            });

        }
    }

    /**
     * Go to Alert Detail
     * @param identity
     */
    @Override
    public void goToAlertDetail(String identity) {
        navigatorImpl.navigateToAlertDetail(identity);
    }

    /**
     * On Apply Changes
     */
    @Override
    public void onApplyChanges() {
        showShortMessage("Apply CHanges");
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return TOOLBAR_WITH_MENU;
    }

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_alert_list;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<AlertsAdapter.AlertsViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<AlertEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);

        if(!dataLoaded.isEmpty()) {
            filterAlertsButton.setVisibility(View.VISIBLE);
            filterAlertsButton.setEnabled(true);
            clearAlertsButton.setVisibility(View.VISIBLE);
            clearAlertsButton.setEnabled(true);
        } else {
            filterAlertsButton.setVisibility(View.GONE);
            filterAlertsButton.setEnabled(false);
            clearAlertsButton.setVisibility(View.GONE);
            clearAlertsButton.setEnabled(false);
        }
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<AlertEntity> getAdapter() {
        return new AlertsAdapter(getApplicationContext(), new ArrayList<AlertEntity>());
    }

    /**
     * On Alerts Cleared
     */
    @Override
    public void onAlertsCleared() {
        showNoticeDialog(R.string.my_alerts_cleared_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                recyclerViewAdapter.removeAll();
                closeActivity();
            }
        });
    }
}
