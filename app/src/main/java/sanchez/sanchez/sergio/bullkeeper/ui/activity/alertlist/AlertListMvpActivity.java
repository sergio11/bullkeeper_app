package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import timber.log.Timber;
import static sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Alert List Activity
 */
public class AlertListMvpActivity extends SupportMvpLCEActivity<AlertListPresenter, IAlertListView, AlertEntity>
        implements HasComponent<AlertsComponent>, IAlertListActivityHandler
        , IAlertListView,
        SupportItemTouchHelper.ItemTouchHelperListener {

    public enum AlertsListModeEnum { ALERTS_BY_SON, ALERTS_BY_SON_AND_LEVEL,
        ALERTS_BY_LEVEL, ALERTS_BY_PREFERENCES }


    public static final String SON_IDENTITY_ARG = "SON_IDENTITY";
    public static final String ALERT_LEVEL_ARG = "ALERT_LEVEL";
    public static final String ALERT_LIST_MODE_ARG = "ALERT_LIST_MODE";

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
     * Alerts Header Title
     */
    @BindView(R.id.alertsHeaderTitle)
    protected TextView alertsHeaderTitle;


    /**
     * State
     */

    private String sonIndentity;
    private AlertLevelEnum alertLevelEnum;
    private AlertsListModeEnum alertsListMode = AlertsListModeEnum.ALERTS_BY_PREFERENCES;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, AlertListMvpActivity.class);
    }

    /**
     * Get Calling Intent
     * @param context
     * @param alertLevel
     * @return
     */
    public static Intent getCallingIntent(final Context context, final AlertLevelEnum alertLevel) {
        Preconditions.checkNotNull(alertLevel, "Alert Level can not be null");
        final Intent callingIntent = new Intent(context, AlertListMvpActivity.class);
        callingIntent.putExtra(ALERT_LEVEL_ARG, alertLevel);
        callingIntent.putExtra(ALERT_LIST_MODE_ARG, AlertsListModeEnum.ALERTS_BY_LEVEL);
        return callingIntent;
    }

    /**
     * Get calling Intent
     * @param context
     * @param alertLevel
     * @param sonIdentity
     * @return
     */
    public static Intent getCallingIntent(final Context context, final AlertLevelEnum alertLevel, final String sonIdentity) {
        Preconditions.checkNotNull(alertLevel, "Alert Level can not be null");
        Preconditions.checkNotNull(sonIdentity, "Son Identity can not be null");
        final Intent callingIntent = new Intent(context, AlertListMvpActivity.class);
        callingIntent.putExtra(ALERT_LEVEL_ARG, alertLevel);
        callingIntent.putExtra(SON_IDENTITY_ARG, sonIdentity);
        callingIntent.putExtra(ALERT_LIST_MODE_ARG, AlertsListModeEnum.ALERTS_BY_SON_AND_LEVEL);
        return callingIntent;
    }

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String sonIdentity) {
        Preconditions.checkNotNull(sonIdentity, "Son Identity can not be null");
        final Intent callingIntent = new Intent(context, AlertListMvpActivity.class);
        callingIntent.putExtra(SON_IDENTITY_ARG, sonIdentity);
        callingIntent.putExtra(ALERT_LIST_MODE_ARG, AlertsListModeEnum.ALERTS_BY_SON);
        return callingIntent;
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
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {

        final Bundle args  = new Bundle();
        if(sonIndentity != null && !sonIndentity.isEmpty())
            args.putString(SON_IDENTITY_ARG, sonIndentity);
        if(alertLevelEnum != null)
            args.putSerializable(ALERT_LEVEL_ARG, alertLevelEnum);
        return args;
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
    public void onHeaderClick() {}

    /**
     * On Item Click
     * @param alertEntity
     */
    @Override
    public void onItemClick(AlertEntity alertEntity) {
        Timber.d("Go to Alert Detail -> %s", alertEntity.getIdentity());
        goToAlertDetail(alertEntity.getIdentity(), alertEntity.getSon().getIdentity());
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

        @StringRes
        int confirmationTitle = R.string.my_alerts_clear_all;

        switch (alertsListMode) {
            case ALERTS_BY_SON:
                confirmationTitle = R.string.deleting_alerts_for_this_child;
                break;
            case ALERTS_BY_LEVEL:
                break;
            case ALERTS_BY_SON_AND_LEVEL:
                break;
            default:
        }


        // Show Confirmation Dialog
        showConfirmationDialog(confirmationTitle, new ConfirmationDialogFragment.ConfirmationDialogListener() {

            /**
             * On Accepted
             * @param dialog
             */
            @Override
            public void onAccepted(DialogFragment dialog) {

                switch (alertsListMode) {
                    case ALERTS_BY_SON:
                        getPresenter().clearAlertsBySon(sonIndentity);
                        break;
                    default:
                        getPresenter().clearAlerts();

                }

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

        if(alertsListMode.equals(AlertsListModeEnum.ALERTS_BY_PREFERENCES)) {
            // Show Filter Alerts Dialog with Alert Category Filter Enable
            navigatorImpl.navigateToAlertsSettingsWithAlertLevelFilterEnabled();
        } else {
            // Show Filter Alerts Dialog
            navigatorImpl.navigateToAlertsSettings();
        }

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
     * @param alertId
     * @param sonId
     */
    @Override
    public void goToAlertDetail(final String alertId, final String sonId) {
        navigatorImpl.navigateToAlertDetail(alertId, sonId);
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

        filterAlertsButton.setVisibility(View.VISIBLE);

        if (getIntent().getExtras() != null) {

            final Bundle extras = getIntent().getExtras();

            if(extras.containsKey(SON_IDENTITY_ARG)){
                sonIndentity = extras.getString(SON_IDENTITY_ARG);
            }

            if(extras.containsKey(ALERT_LEVEL_ARG)) {
                alertLevelEnum = (AlertLevelEnum) extras.getSerializable(ALERT_LEVEL_ARG);
            }

            if (extras.containsKey(ALERT_LIST_MODE_ARG)) {
                alertsListMode = (AlertsListModeEnum) extras.getSerializable(ALERT_LIST_MODE_ARG);
            }

        }

    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<AlertEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);

        clearAlertsButton.setVisibility(View.VISIBLE);
        clearAlertsButton.setEnabled(true);
        alertsHeaderTitle.setText(String.format(Locale.getDefault(),
                getString(R.string.my_alerts_count),dataLoaded.size()));

    }

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        super.onNoDataFound();
        clearAlertsButton.setVisibility(View.GONE);
        clearAlertsButton.setEnabled(false);
        alertsHeaderTitle.setText(getString(R.string.my_alerts));
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

    /**
     * On Preferences Updated
     */
    @Override
    protected void onPreferencesUpdated() {
        super.onPreferencesUpdated();
        Timber.d("On Preferences Updated ...");
        // Load Data
        getPresenter().loadData();
    }
}
