package sanchez.sanchez.sergio.masom_app.ui.activity.alertlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.AlertsComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerAlertsComponent;
import sanchez.sanchez.sergio.masom_app.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.masom_app.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.masom_app.ui.adapter.impl.AlertsAdapter;
import sanchez.sanchez.sergio.masom_app.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;

import static sanchez.sanchez.sergio.masom_app.ui.support.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Alert List Activity
 */
public class AlertListActivity extends SupportActivity<AlertListPresenter, IAlertListView>
        implements HasComponent<AlertsComponent>, IAlertListActivityHandler
        , IAlertListView, SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<AlertEntity>,
        SupportItemTouchHelper.ItemTouchHelperListener ,
        AlertsAdapter.OnAlertsViewListener {


    /**
     * Alerts Component
     */
    private AlertsComponent alertsComponent;

    /**
     * Alerts Adapter
     */
    private AlertsAdapter alertsAdapter;

    /**
     * Main Container
     */
    @BindView(R.id.mainContainer)
    protected ViewGroup mainContainer;

    /**
     * Refresh Layout
     */
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout refreshLayout;

    /**
     * Alerts List
     */
    @BindView(R.id.alertsList)
    protected RecyclerView alertsList;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, AlertListActivity.class);
    }


    /**
     * On Create
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_alert_list);
        super.onCreate(savedInstanceState);

        refreshLayout.setColorSchemeResources(R.color.commonWhite);
        refreshLayout.setProgressBackgroundColorSchemeResource(R.color.cyanBrilliant);

        alertsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        alertsList.setNestedScrollingEnabled(false);
        alertsAdapter = new AlertsAdapter(getApplicationContext(), new ArrayList<AlertEntity>());
        alertsAdapter.setOnSupportRecyclerViewListener(this);
        alertsAdapter.setOnAlertsViewListener(this);
        // Set Animator
        alertsList.setItemAnimator(new DefaultItemAnimator());
        alertsList.setAdapter(alertsAdapter);

        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<AlertsAdapter.AlertsViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(alertsList);

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
     * On Alerts Loaded
     * @param alertsList
     */
    @Override
    public void onAlertsLoaded(List<AlertEntity> alertsList) {
        alertsAdapter.setData(new ArrayList<>(alertsList));
        alertsAdapter.notifyDataSetChanged();
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
        goToAlertDetail("123456");
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
    @Override
    public void onClearAllAlerts() {

        // Show Confirmation Dialog
        showConfirmationDialog(R.string.my_alerts_clear_all, new ConfirmationDialogFragment.ConfirmationDialogListener() {

            /**
             * On Accepted
             * @param dialog
             */
            @Override
            public void onAccepted(DialogFragment dialog) {

                showNoticeDialog(R.string.my_alerts_cleared_successfully, new NoticeDialogFragment.NoticeDialogListener() {
                    @Override
                    public void onAccepted(DialogFragment dialog) {
                        closeActivity();
                    }
                });
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
    @Override
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
            final AlertEntity alertEntity = alertsAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            alertsAdapter.removeItem(deletedIndex);

            showLongSimpleSnackbar(mainContainer, getString(R.string.alert_item_removed), getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertsAdapter.restoreItem(alertEntity, deletedIndex);
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
}
