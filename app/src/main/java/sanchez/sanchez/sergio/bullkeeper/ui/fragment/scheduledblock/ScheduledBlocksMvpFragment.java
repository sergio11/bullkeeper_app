package sanchez.sanchez.sergio.bullkeeper.ui.fragment.scheduledblock;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.ScheduledBlocksAdapter;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import timber.log.Timber;

/**
 * Scheduled Blocks Fragment
 */
public class ScheduledBlocksMvpFragment extends SupportMvpLCEFragment<ScheduledBlocksFragmentPresenter,
        IScheduledBlocksFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, ScheduledBlockEntity>
        implements IScheduledBlocksFragmentView, SupportItemTouchHelper.ItemTouchHelperListener {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


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
     * State
     * ===================
     */

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;

    /**
     * Activity
     */
    @Inject
    protected Activity activity;


    /**
     *
     */
    public ScheduledBlocksMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static ScheduledBlocksMvpFragment newInstance(final String kidIdentity) {
        ScheduledBlocksMvpFragment fragment = new ScheduledBlocksMvpFragment();
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

        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<ScheduledBlocksAdapter.ScheduledBlocksViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<ScheduledBlockEntity> getAdapter() {
        final ScheduledBlocksAdapter scheduledBlocksAdapter =
                new ScheduledBlocksAdapter(activity, new ArrayList<ScheduledBlockEntity>(), picasso);
        scheduledBlocksAdapter.setOnSupportRecyclerViewListener(this);
        return scheduledBlocksAdapter;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(ScheduledBlocksFragmentPresenter.SON_IDENTITY_ARG, kidIdentity);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_scheduled_blocks;
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
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * On Item Click
     * @param scheduledBlockEntity
     */
    @Override
    public void onItemClick(ScheduledBlockEntity scheduledBlockEntity) {
        Timber.d("Scheduled Block Clicked");
        activityHandler.navigateToSaveScheduledBlock(scheduledBlockEntity.getIdentity());
    }

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
    public ScheduledBlocksFragmentPresenter providePresenter() {
        return component.scheduledBlocksFragmentPresenter();
    }


    /**
     * On Add Scheduled Block
     */
    @OnClick(R.id.addScheduledBlock)
    protected void onAddScheduledBlock(){
        activityHandler.navigateToSaveScheduledBlock();
    }


    /**
     * On Swiped
     * @param viewHolder
     * @param direction
     * @param position
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ScheduledBlocksAdapter.ScheduledBlocksViewHolder) {

            final Integer deletedIndex = viewHolder.getAdapterPosition();
            final ScheduledBlockEntity scheduledBlockEntity =
                    recyclerViewAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            recyclerViewAdapter.removeItem(deletedIndex);

            showLongSimpleSnackbar(content, getString(R.string.scheduled_block_deleted_success), getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewAdapter.restoreItem(scheduledBlockEntity, deletedIndex);
                }
            }, new Snackbar.Callback(){
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    if(event == DISMISS_EVENT_TIMEOUT) {
                        Timber.d("Dismiss Event Timeout");
                        // Delete Scheduled Block
                        getPresenter().deleteScheduledBlockById(scheduledBlockEntity.getIdentity());
                    }
                }
            });

        }
    }

    /**
     * On Scheduled Block Deleted
     */
    @Override
    public void onScheduledBlockDeleted() {
        Timber.d("Scheduled Block Deleted");
    }
}

