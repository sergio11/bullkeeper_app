package sanchez.sanchez.sergio.bullkeeper.ui.activity.invitations;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerInvitationsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.InvitationsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.AlertsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.InvitationsAdapter;
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
        if (viewHolder instanceof AlertsAdapter.AlertsViewHolder) {

            final Integer deletedIndex = viewHolder.getAdapterPosition();
            final SupervisedChildrenEntity supervisedChildrenEntity =
                    recyclerViewAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            recyclerViewAdapter.removeItem(deletedIndex);

            showLongSimpleSnackbar(content, getString(R.string.invitation_item_removed), getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewAdapter.restoreItem(supervisedChildrenEntity, deletedIndex);
                }
            }, new Snackbar.Callback(){
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    if(event == DISMISS_EVENT_TIMEOUT) {
                        // Delete Invitation
                        getPresenter().deleteInvitation(supervisedChildrenEntity.getIdentity());
                    }
                }
            });

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
        getPresenter().clearInvitations();
    }

}
