package sanchez.sanchez.sergio.bullkeeper.ui.fragment.lastalerts;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.HomeComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.home.IHomeActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.LastAlertsAdapter;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import timber.log.Timber;

/**
 * Last Alerts Activity Fragment
 */
public class LastAlertsActivityMvpFragment extends SupportMvpLCEFragment<LastAlertsFragmentPresenter,
        ILastAlertsView, IHomeActivityHandler, HomeComponent, AlertEntity> implements ILastAlertsView,
        LastAlertsAdapter.OnSupportRecyclerViewListener<AlertEntity>,SupportItemTouchHelper.ItemTouchHelperListener {

    public static String TAG = "LAST_ALERTS_ACTIVITY_MVP";

    /**
     * Last Alerts Title
     */
    @BindView(R.id.lastAlertsTitle)
    protected TextView lastAlertsTitle;

    /**
     * Show Alerts Btn
     */
    @BindView(R.id.showAlerts)
    protected ImageView showAlertsBtn;

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


    public LastAlertsActivityMvpFragment() {}

    /**
     * New Instance
     * @return
     */
    public static LastAlertsActivityMvpFragment newInstance() {
        LastAlertsActivityMvpFragment fragment = new LastAlertsActivityMvpFragment();
        return fragment;
    }

    /**
     * Toggle All Components
     * @param isEnable
     */
    private void toggleAllComponents(final boolean isEnable) {
        lastAlertsTitle.setEnabled(isEnable);
        showAlertsBtn.setEnabled(isEnable);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public LastAlertsFragmentPresenter providePresenter() {
        return component.lastAlertsFragmentPresenter();
    }


    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() { }


    /**
     * On Item Clicked
     * @param alertEntity
     */
    @Override
    public void onItemClick(AlertEntity alertEntity) {
        activityHandler.goToAlertDetail(alertEntity.getIdentity(), alertEntity.getSon().getIdentity());
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() { }


    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_last_alerts;
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(HomeComponent component) {
        component.inject(this);
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toggleAllComponents(false);

        ViewCompat.setNestedScrollingEnabled(recyclerView, false);

        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<LastAlertsAdapter.LastAlertsViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<AlertEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);

        lastAlertsTitle.setText(String.format(Locale.getDefault(),
                getString(R.string.last_alerts_title), dataLoaded.size()));

        toggleAllComponents(true);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<AlertEntity> getAdapter() {
        final LastAlertsAdapter lastAlertsAdapter = new LastAlertsAdapter(activity, new ArrayList<AlertEntity>(), picasso);
        lastAlertsAdapter.setOnSupportRecyclerViewListener(this);
        return lastAlertsAdapter;
    }

    /**
     * Go To Alerts
     */
    @OnClick(R.id.showAlerts)
    protected void onShowAlerts(){
        activityHandler.goToAlerts();
    }

    /**
     * On Swiped
     * @param viewHolder
     * @param direction
     * @param position
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof LastAlertsAdapter.LastAlertsViewHolder) {

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
                        Timber.d("Dismiss Event Timeout");
                        // Delete Alert Of Son
                        getPresenter().deleteAlertOfSon(alertEntity.getSon().getIdentity(),
                                alertEntity.getIdentity());
                    }
                }
            });

        }
    }

    /**
     * On Alert Deleted
     */
    @Override
    public void onAlertDeleted() {
        lastAlertsTitle.setText(String.format(Locale.getDefault(),
                getString(R.string.last_alerts_title), recyclerView.getAdapter().getItemCount()));
    }
}
