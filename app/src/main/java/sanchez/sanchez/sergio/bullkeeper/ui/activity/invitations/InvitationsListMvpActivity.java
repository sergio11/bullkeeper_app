package sanchez.sanchez.sergio.bullkeeper.ui.activity.invitations;

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
import android.widget.TextView;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerInvitationsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.InvitationsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.InvitationsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Invitations List Activity
 */
public class InvitationsListMvpActivity extends SupportMvpLCEActivity<InvitationsListPresenter, IInvitationsListView, SupervisedChildrenEntity>
        implements HasComponent<InvitationsComponent>, IInvitationsListActivityHandler
        , IInvitationsListView,
        SupportItemTouchHelper.ItemTouchHelperListener {

    private final String CONTENT_FULL_NAME = "INVITATIONS_LIST";
    private final String CONTENT_TYPE_NAME = "INVITATIONS";

    /**
     * Invitations Component
     */
    private InvitationsComponent invitationsComponent;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * Views
     * =============
     */

    /**
     * Invitations Header Title Text View
     */
    @BindView(R.id.invitationsHeaderTitle)
    protected TextView invitationsHeaderTitleTextView;

    /**
     * Clear Invitations
     */
    @BindView(R.id.clearInvitations)
    protected ImageButton clearInvitationsImageButton;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, InvitationsListMvpActivity.class);
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.invitationsComponent = DaggerInvitationsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.invitationsComponent.inject(this);
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
    public InvitationsListPresenter providePresenter() {
        return invitationsComponent.invitationsListPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public InvitationsComponent getComponent() {
        return invitationsComponent;
    }


    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * On Item Click
     * @param supervisedChildrenEntity
     */
    @Override
    public void onItemClick(SupervisedChildrenEntity supervisedChildrenEntity) {
        Preconditions.checkNotNull(supervisedChildrenEntity, "Supervised Children can not be null");
        Preconditions.checkNotNull(supervisedChildrenEntity.getIdentity(), "Supervised Children Identity can not be null");
        Preconditions.checkState(!supervisedChildrenEntity.getIdentity().isEmpty(), "Supervised Children Identity can not be empty");
        navigatorImpl.navigateToInvitationDetail(this, supervisedChildrenEntity.getIdentity());
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
        if (viewHolder instanceof InvitationsAdapter.SupervisedChildrenViewHolder) {

            final Integer deletedIndex = viewHolder.getAdapterPosition();
            final SupervisedChildrenEntity supervisedChildrenEntity =
                    recyclerViewAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            recyclerViewAdapter.removeItem(deletedIndex);

            if(!isConnectivityAvailable()) {
                showNoticeDialog(R.string.connectivity_not_available, false);
                recyclerViewAdapter.restoreItem(supervisedChildrenEntity, deletedIndex);
            } else {
                showLongSimpleSnackbar(content, getString(R.string.invitation_item_removed), getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerViewAdapter.restoreItem(supervisedChildrenEntity, deletedIndex);
                    }
                }, new Snackbar.Callback(){
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        if(event == DISMISS_EVENT_TIMEOUT || event == DISMISS_EVENT_CONSECUTIVE) {
                            // Delete Invitation
                            getPresenter().deleteInvitation(supervisedChildrenEntity.getIdentity());
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
        return R.layout.activity_invitation_list;
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
                new SupportItemTouchHelper<InvitationsAdapter.SupervisedChildrenViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<SupervisedChildrenEntity> getAdapter() {
        return new InvitationsAdapter(this, new ArrayList<SupervisedChildrenEntity>(), picasso);
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
     * On Clear Invitations
     */
    @OnClick(R.id.clearInvitations)
    protected void onClearInvitations(){

        if (!isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            showConfirmationDialog(R.string.invitations_confirm_delete_all, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    getPresenter().clearInvitations();
                }

                @Override
                public void onRejected(DialogFragment dialog) {}
            });
        }

    }

    /**
     * On All Invitations Cleared
     */
    @Override
    public void onAllInvitationsCleared() {
        showNoticeDialog(R.string.invitations_cleared_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                recyclerViewAdapter.removeAll();
                closeActivity();
            }
        });
    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<SupervisedChildrenEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);
        clearInvitationsImageButton.setVisibility(View.VISIBLE);
        clearInvitationsImageButton.setEnabled(true);
        invitationsHeaderTitleTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.invitations_title_count),dataLoaded.size()));

    }

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        super.onNoDataFound();
        clearInvitationsImageButton.setVisibility(View.GONE);
        clearInvitationsImageButton.setEnabled(false);
        invitationsHeaderTitleTextView
                .setText(getString(R.string.invitations_title_default));

        showNoticeDialog(R.string.no_pending_invitations_to_accept, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });
    }

    /**
     *
     */
    @Override
    public void onInvitationCleared() {
        if(recyclerView.getAdapter() != null) {
            final int itemCount = recyclerView.getAdapter().getItemCount();

            if(itemCount > 0)
                invitationsHeaderTitleTextView.setText(String.format(Locale.getDefault(),
                        getString(R.string.invitations_title_count), recyclerView.getAdapter().getItemCount()));
            else
                showNoticeDialog(R.string.no_pending_invitations_to_accept, new NoticeDialogFragment.NoticeDialogListener() {
                    @Override
                    public void onAccepted(DialogFragment dialog) {
                        closeActivity();
                    }
                });
        }

    }
}
