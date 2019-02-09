package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.appallowedbyscheduled;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.fernandocejas.arrow.checks.Preconditions;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.ScheduledBlockComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.savescheduledblock.ISaveScheduledBlockActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.AppAllowedByScheduledAdapter;
import sanchez.sanchez.sergio.domain.models.AppAllowedByScheduledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledByTerminalEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;
import timber.log.Timber;

/**
 * App Allowed By Scheduled Mvp Fragment
 */
public class AppAllowedByScheduledMvpFragment extends SupportMvpLCEFragment<AppAllowedByScheduledFragmentPresenter,
        IAppAllowedByScheduledFragmentView, ISaveScheduledBlockActivityHandler, ScheduledBlockComponent, AppAllowedByScheduledEntity>
        implements IAppAllowedByScheduledFragmentView, SupportItemTouchHelper.ItemTouchHelperListener  {

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
     *
     */
    public AppAllowedByScheduledMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @return
     */
    public static AppAllowedByScheduledMvpFragment newInstance() {
        return new AppAllowedByScheduledMvpFragment();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<AppAllowedByScheduledAdapter.AppAllowedByScheduledViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


        noDataFoundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddAppAllowed();
            }
        });

        onNoDataFound();
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_app_allowed_by_scheduled;
    }

    /**
     * Initialize Injector
     * @param component
     */
    @Override
    protected void initializeInjector(ScheduledBlockComponent component) {
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
    public void onItemClick(AppAllowedByScheduledEntity item) { }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<AppAllowedByScheduledEntity> getAdapter() {
        final AppAllowedByScheduledAdapter adapter =  new AppAllowedByScheduledAdapter(activity, new ArrayList<AppAllowedByScheduledEntity>());
        adapter.setOnSupportRecyclerViewListener(this);
        return adapter;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public AppAllowedByScheduledFragmentPresenter providePresenter() {
        return component.appAllowedByScheduledFragmentPresenter();
    }

    /**
     * Get App Allowed By Scheduled Entities
     * @return
     */
    public List<AppAllowedByScheduledEntity> getAppAllowedByScheduledEntities() {
        return recyclerViewAdapter.getData();
    }

    /**
     * Set App Allowed By Scheduled Entities
     * @param appAllowedByScheduledEntities
     */
    public void setAppAllowedByScheduledEntities(List<AppAllowedByScheduledEntity> appAllowedByScheduledEntities) {
        Preconditions.checkNotNull(appAllowedByScheduledEntities, "App Allowed can not be null");

        Timber.d("APP_ALLOWED: App Allowed By Scheduled Entities");
        if(!appAllowedByScheduledEntities.isEmpty())
            onDataLoaded(appAllowedByScheduledEntities);
        else
            onNoDataFound();


    }

    /**
     * Get Not Found Text
     * @return
     */
    @Override
    protected int getNotFoundText() {
        return R.string.scheduled_block_no_apps_allowed;
    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<AppAllowedByScheduledEntity> dataLoaded) {
        final List<AppAllowedByScheduledEntity> currentAppAllowedList = recyclerViewAdapter.getData();
        for(final AppAllowedByScheduledEntity appAllowedByScheduledEntity: dataLoaded) {
            if(!currentAppAllowedList.contains(appAllowedByScheduledEntity))
                currentAppAllowedList.add(appAllowedByScheduledEntity);
        }
        super.onDataLoaded(currentAppAllowedList);
    }

    /**
     * Add App Selected
     * @param appInstalledByTerminalEntity
     */
    public void addAppSelected(final AppInstalledByTerminalEntity appInstalledByTerminalEntity) {
        boolean alreadyAdded = false;

        final List<AppAllowedByScheduledEntity> appAllowedByScheduledEntities = recyclerViewAdapter.getData();

        for(final AppAllowedByScheduledEntity appAllowedByScheduled: appAllowedByScheduledEntities) {
            if (appAllowedByScheduled.getApp().getIdentity()
                    .equals(appInstalledByTerminalEntity.getIdentity())) {
                alreadyAdded = true;
                break;
            }
        }

        if (!alreadyAdded) {

            if(!appInstalledByTerminalEntity.getDisabled() && appInstalledByTerminalEntity
                    .getAppRuleEnum().equals(AppRuleEnum.PER_SCHEDULER)) {

                final AppAllowedByScheduledEntity appAllowedByScheduledEntity = new AppAllowedByScheduledEntity();
                appAllowedByScheduledEntity.setTerminal(appInstalledByTerminalEntity.getTerminal());
                appAllowedByScheduledEntity.setApp(new AppInstalledEntity(
                        appInstalledByTerminalEntity.getIdentity(),
                        appInstalledByTerminalEntity.getPackageName(),
                        appInstalledByTerminalEntity.getCategory(),
                        appInstalledByTerminalEntity.getCategoryKey(),
                        appInstalledByTerminalEntity.getFirstInstallTime(),
                        appInstalledByTerminalEntity.getLastUpdateTime(),
                        appInstalledByTerminalEntity.getVersionName(),
                        appInstalledByTerminalEntity.getVersionCode(),
                        appInstalledByTerminalEntity.getAppName(),
                        appInstalledByTerminalEntity.getAppRuleEnum(),
                        appInstalledByTerminalEntity.getIconEncodedString(),
                        appInstalledByTerminalEntity.getDisabled()
                ));
                appAllowedByScheduledEntities.add(appAllowedByScheduledEntity);
                onDataLoaded(appAllowedByScheduledEntities);

            } else {


                showNoticeDialog(R.string.scheduled_block_only_apps_with_valid_rule, false);

            }


        } else {
            showNoticeDialog(R.string.scheduled_block_app_already_selected, false);
        }

    }

    /**
     * On Swipe
     * @param viewHolder
     * @param direction
     * @param position
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof AppAllowedByScheduledAdapter.AppAllowedByScheduledViewHolder) {

            final Integer deletedIndex = viewHolder.getAdapterPosition();
            final AppAllowedByScheduledEntity appAllowedByScheduledEntity =
                    recyclerViewAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            recyclerViewAdapter.removeItem(deletedIndex);

            showLongSimpleSnackbar(content, getString(R.string.scheduled_block_app_allowed_eliminated),
                    getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewAdapter.restoreItem(appAllowedByScheduledEntity, deletedIndex);
                }
            }, new Snackbar.Callback(){
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    if(recyclerViewAdapter.getData().isEmpty())
                        onNoDataFound();
                    super.onDismissed(transientBottomBar, event);
                }
            });
        }
    }

    /**
     * On Add App Allowed
     */
    @OnClick(R.id.addAppAllowed)
    protected void onAddAppAllowed(){
        activityHandler.navigateToAppSearch();
    }
}

