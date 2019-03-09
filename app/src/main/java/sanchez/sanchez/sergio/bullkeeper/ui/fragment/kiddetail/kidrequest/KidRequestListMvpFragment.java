package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.kidrequest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IKidRequestEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.events.impl.kidRequestCreatedEvent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.KidRequestAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;

/**
 * Kid Request List Fragment
 */
public class KidRequestListMvpFragment extends SupportMvpLCEFragment<KidRequestListFragmentPresenter,
        IKidRequestListFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, KidRequestEntity>
        implements IKidRequestListFragmentView, SupportItemTouchHelper.ItemTouchHelperListener {

    /**
     * Kid Identity Arg
     */
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
     * Activity
     */
    @Inject
    protected Activity activity;

    /**
     * Local System Notification
     */
    @Inject
    protected ILocalSystemNotification localSystemNotification;


    /**
     * Views
     * ===================
     */

    /**
     * Delete All Kid Request Image View
     */
    @BindView(R.id.deleteAllKidRequest)
    protected ImageView deleteAllKidRequestImageView;

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
     * Kid Request Event Register Key
     */
    @State
    protected int kidRequestEventRegisterKey;

    /**
     * kidRequestVisitor
     */
    private IKidRequestEventVisitor kidRequestVisitor = new IKidRequestEventVisitor(){
        /**
         *
         * @param kidRequestCreatedEvent
         */
        @Override
        public void visit(final kidRequestCreatedEvent kidRequestCreatedEvent) {
            loadData();
            showNoticeDialog(R.string.new_kid_request_created);
        }
    };


    /**
     *
     */
    public KidRequestListMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static KidRequestListMvpFragment newInstance(final String kidIdentity) {
        KidRequestListMvpFragment fragment = new KidRequestListMvpFragment();
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

        // Enable Nested Scrolling on Recycler View
        ViewCompat.setNestedScrollingEnabled(recyclerView, true);

        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<KidRequestAdapter.KidRequestViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    /**
     * On Start
     */
    @Override
    public void onStart() {
        super.onStart();

        kidRequestEventRegisterKey = localSystemNotification.registerEventListener(
                kidRequestCreatedEvent.class, kidRequestVisitor);
    }

    /**
     * On Stop
     */
    @Override
    public void onStop() {
        super.onStop();

        localSystemNotification.unregisterEventListener(kidRequestEventRegisterKey);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<KidRequestEntity> getAdapter() {
        final KidRequestAdapter kidRequestAdapter = new KidRequestAdapter(activity, new ArrayList<KidRequestEntity>());
        kidRequestAdapter.setOnSupportRecyclerViewListener(this);
        return kidRequestAdapter;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(KidRequestListFragmentPresenter.KID_IDENTITY_ARG, kidIdentity);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_kid_request_list;
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
     * @param kidRequestEntity
     */
    @Override
    public void onItemClick(KidRequestEntity kidRequestEntity) {
        Preconditions.checkNotNull(kidRequestEntity, "Kid Request Entity can not be null");
        Preconditions.checkNotNull(kidRequestEntity.getIdentity(), "Identity can not be null");
        Preconditions.checkState(!kidRequestEntity.getIdentity().isEmpty(), "Identity can not be empty");
        activityHandler.navigateToKidRequestDetail(kidIdentity,
                kidRequestEntity.getIdentity());
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
    public KidRequestListFragmentPresenter providePresenter() {
        return component.kidRequestListFragmentPresenter();
    }

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        super.onNoDataFound();
        deleteAllKidRequestImageView.setVisibility(View.GONE);
        deleteAllKidRequestImageView.setEnabled(false);
    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<KidRequestEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);
        deleteAllKidRequestImageView.setVisibility(View.VISIBLE);
        deleteAllKidRequestImageView.setEnabled(true);
    }


    /**
     * On Kid Request Deleted
     */
    @Override
    public void onKidRequestDeleted() {
        if(getAdapter().getData().isEmpty())
            onNoDataFound();
        showNoticeDialog(R.string.kid_request_removed);
    }

    /**
     *
     * @param viewHolder
     * @param direction
     * @param position
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof KidRequestAdapter.KidRequestViewHolder) {

            final Integer deletedIndex = viewHolder.getAdapterPosition();
            final KidRequestEntity kidRequestEntity =
                    recyclerViewAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            recyclerViewAdapter.removeItem(deletedIndex);

            if(!activityHandler.isConnectivityAvailable()) {
                showNoticeDialog(R.string.connectivity_not_available, false);
                recyclerViewAdapter.restoreItem(kidRequestEntity, deletedIndex);
            } else {
                showLongSimpleSnackbar(content, getString(R.string.kid_request_removed), getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerViewAdapter.restoreItem(kidRequestEntity, deletedIndex);
                    }
                }, new Snackbar.Callback(){
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        if(event == DISMISS_EVENT_TIMEOUT) {
                            // Delete Kid Request
                            getPresenter().deleteKidRequest(
                                    kidRequestEntity.getKid().getIdentity(),
                                    kidRequestEntity.getIdentity()
                            );
                        }
                    }
                });
            }

        }
    }

    /**
     * On Delete All Kid Request Clicked
     */
    @OnClick(R.id.deleteAllKidRequest)
    protected void onDeleteAllKidRequestClicked(){

        if(!activityHandler.isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            showConfirmationDialog(R.string.delete_all_kid_request_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    getPresenter().deleteAllKidRequestForKid(kidIdentity);
                }

                @Override
                public void onRejected(DialogFragment dialog) {

                }
            });
        }
    }
}

