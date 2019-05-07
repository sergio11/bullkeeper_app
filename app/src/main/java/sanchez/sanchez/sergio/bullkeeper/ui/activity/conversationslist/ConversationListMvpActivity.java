package sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
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
import sanchez.sanchez.sergio.bullkeeper.di.components.ConversationComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerConversationComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.searchguardian.SearchGuardiansMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.ConversationsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Conversation Activity
 */
public class ConversationListMvpActivity extends SupportMvpLCEActivity<ConversationListPresenter, IConversationListView, ConversationEntity>
        implements HasComponent<ConversationComponent>, IConversationListActivityHandler
        , IConversationListView, SupportItemTouchHelper.ItemTouchHelperListener {

    private final String CONTENT_FULL_NAME = "CONVERSATION_LIST";
    private final String CONTENT_TYPE_NAME = "CONVERSATION";

    /**
     * Request Code
     */
    public final static int SELECT_GUARDIAN_REQUEST_CODE = 267;

    /**
     * Dependencies
     * =======================
     */

    /**
     * Activity
     */
    @Inject
    protected Activity activity;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * Views
     * ========
     */

    /**
     * Delete All Conversations Image View
     */
    @BindView(R.id.deleteAllConversations)
    protected ImageView deleteAllConversationsImageView;

    /**
     * Conversation Header Title
     */
    @BindView(R.id.conversationHeaderTitle)
    protected TextView conversationHeaderTitleTextView;


    /**
     * Conversation Component
     */
    private ConversationComponent conversationComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Activity context) {
        return new Intent(context, ConversationListMvpActivity.class);
    }


    /**
     * initialize Injector
     */
    @Override
    protected void initializeInjector() {
        conversationComponent = DaggerConversationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        conversationComponent.inject(this);
    }


    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_conversation_list;
    }

    /**
     * On Activity Result
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(SELECT_GUARDIAN_REQUEST_CODE == requestCode &&
                resultCode == Activity.RESULT_OK && data != null) {
            final GuardianEntity guardianEntitySelected =
                    (GuardianEntity) data.getSerializableExtra(SearchGuardiansMvpActivity.GUARDIAN_SELECTED_ARG);
            final String selfUserId = preferencesRepositoryImpl.getPrefCurrentUserIdentity();
            navigatorImpl.navigateToConversationMessageList(this, selfUserId,
                    guardianEntitySelected.getIdentity());
        }
    }

    /**
     * on View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<ConversationsAdapter.ConversationViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public ConversationListPresenter providePresenter() {
        return conversationComponent.conversationPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public ConversationComponent getComponent() {
        return conversationComponent;
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
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.intro_background_cyan;
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<ConversationEntity> getAdapter() {
        final String selfUserId = preferencesRepositoryImpl.getPrefCurrentUserIdentity();
        final ConversationsAdapter conversationsAdapter = new ConversationsAdapter(
                activity, new ArrayList<ConversationEntity>(), picasso, selfUserId);
        conversationsAdapter.setOnSupportRecyclerViewListener(this);
        return conversationsAdapter;
    }

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        super.onNoDataFound();
        deleteAllConversationsImageView.setVisibility(View.GONE);
        deleteAllConversationsImageView.setEnabled(false);
        conversationHeaderTitleTextView.setText(getString(R.string.conversation_list_title_default));
    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(final List<ConversationEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);
        deleteAllConversationsImageView.setVisibility(View.VISIBLE);
        deleteAllConversationsImageView.setEnabled(true);
        conversationHeaderTitleTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.conversation_list_title),dataLoaded.size()));
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * On Item click
     * @param conversationEntity
     */
    @Override
    public void onItemClick(final ConversationEntity conversationEntity) {
        Preconditions.checkNotNull(conversationEntity, "Conversation Entity");
        Preconditions.checkNotNull(conversationEntity.getIdentity(), "Conversation Id can not be null");
        Preconditions.checkState(!conversationEntity.getIdentity().isEmpty(), "Id can not be empty");
        navigatorImpl.navigateToConversationMessageList(this, conversationEntity.getIdentity());
    }

    @Override
    public void onFooterClick() {}

    /**
     * On Add Conversation Clicked
     */
    @OnClick(R.id.addConversation)
    protected void onAddConversationClicked(){
        navigatorImpl.navigateToSearchGuardianActivity(this,
                SELECT_GUARDIAN_REQUEST_CODE);
    }

    /**
     * On Delete All Conversations
     */
    @OnClick(R.id.deleteAllConversations)
    protected void onDeleteAllConversations(){

        if(!isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            showConfirmationDialog(R.string.delete_all_conversations_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    getPresenter().deleteAll();
                }
                @Override
                public void onRejected(DialogFragment dialog) {
                }
            });
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
        if (viewHolder instanceof ConversationsAdapter.ConversationViewHolder) {

            final int deletedIndex = viewHolder.getAdapterPosition();
            final ConversationEntity conversationEntity =
                    recyclerViewAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            recyclerViewAdapter.removeItem(deletedIndex);

            if(!isConnectivityAvailable()) {
                showNoticeDialog(R.string.connectivity_not_available, false);
                recyclerViewAdapter.restoreItem(conversationEntity, deletedIndex);
            } else {
                showLongSimpleSnackbar(content, getString(R.string.alert_item_removed), getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerViewAdapter.restoreItem(conversationEntity, deletedIndex);
                    }
                }, new Snackbar.Callback(){
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        if(event == DISMISS_EVENT_TIMEOUT || event == DISMISS_EVENT_CONSECUTIVE) {
                            getPresenter().deleteById(conversationEntity.getIdentity());
                        }
                    }
                });
            }

        }
    }

    /**
     *
     */
    @Override
    public void onConversationDeletedSuccessfully() {

        if(recyclerViewAdapter.getData().isEmpty())
            onNoDataFound();
        else
            conversationHeaderTitleTextView.setText(String.format(Locale.getDefault(),
                    getString(R.string.conversation_list_title),
                    recyclerViewAdapter.getData().size()));


    }
}
