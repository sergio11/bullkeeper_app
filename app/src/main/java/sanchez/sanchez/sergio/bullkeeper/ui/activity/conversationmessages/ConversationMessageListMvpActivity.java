package sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationmessages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageButton;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.stfalcon.chatkit.utils.DateFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.sounds.ISoundManager;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.ConversationComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerConversationComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.models.ConversationMessage;
import sanchez.sanchez.sergio.bullkeeper.ui.models.ConversationMessageUser;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.models.MessageEntity;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import timber.log.Timber;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Conversation Message List Activity
 */
public class ConversationMessageListMvpActivity extends SupportMvpActivity<ConversationMessageListPresenter, IConversationMessageListView>
        implements HasComponent<ConversationComponent>, IConversationMessageListActivityHandler
        , IConversationMessageListView, MessageInput.InputListener, MessagesListAdapter.SelectionListener, MessagesListAdapter.OnLoadMoreListener, DateFormatter.Formatter, SwipeRefreshLayout.OnRefreshListener {

    private final String CONTENT_FULL_NAME = "CONVERSATION_MESSAGE_LIST";
    private final String CONTENT_TYPE_NAME = "CONVERSATON";

    /**
     * Args
     */
    public static final String CONVERSATION_MEMBER_ONE_IDENTITY_ARG = "CONVERSATION_MEMBER_ONE_IDENTITY_ARG";
    public static final String CONVERSATION_MEMBER_TWO_IDENTITY_ARG = "CONVERSATION_MEMBER_TWO_IDENTITY_ARG";
    public static final String CONVERSATION_IDENTITY_ARG = "CONVERSATION_IDENTITY_ARG";

    private static final String SENDING_MESSAGE_ID = "5645678";

    /**
     * Conversation Component
     */
    private ConversationComponent conversationComponent;

    /**
     * Views
     * =================
     */

    /**
     * Message List
     */
    @BindView(R.id.messagesList)
    protected MessagesList messagesList;

    /**
     * Message Input
     */
    @BindView(R.id.input)
    protected MessageInput messageInput;

    /**
     * Clear Message
     */
    @BindView(R.id.clearMessage)
    protected ImageButton clearMessageImageButton;

    /**
     * Swipe Refresh Layout
     */
    @BindView(R.id.content)
    protected SwipeRefreshLayout swipeRefreshLayout;

    /**
     * State
     * =====================
     */

    /**
     * Member One
     */
    @State
    protected String memberOne;

    /**
     * Member Two
     */
    @State
    protected String memberTwo;

    /**
     * Selection Count
     */
    @State
    protected int selectionCount;

    /**
     * Last Loaded Date
     */
    @State
    protected Date lastLoadedDate;

    /**
     * Conversation Id
     */
    @State
    protected String conversationId;

    /**
     * Dependencies
     * ==================
     */

    /**
     * Image Loader
     */
    @Inject
    protected ImageLoader imageLoader;

    /**
     * Preferences Repository
     */
    @Inject
    protected IPreferenceRepository preferenceRepository;

    /**
     * Sound Manager
     */
    @Inject
    protected ISoundManager soundManager;


    /**
     * Message Adapter
     */
    private MessagesListAdapter<ConversationMessage> messagesAdapter;


    /**
     * Get Calling Intent
     * @param context
     * @param memberOne
     * @param memberTwo
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String memberOne, final String memberTwo) {
        Preconditions.checkNotNull(context, "Context can not be null");
        Preconditions.checkNotNull(memberOne, "Member one can not be null");
        Preconditions.checkNotNull(memberTwo, "Member Two can not be null");
        final Intent callingIntent =  new Intent(context, ConversationMessageListMvpActivity.class);
        callingIntent.putExtra(CONVERSATION_MEMBER_ONE_IDENTITY_ARG, memberOne);
        callingIntent.putExtra(CONVERSATION_MEMBER_TWO_IDENTITY_ARG, memberTwo);
        return callingIntent;
    }

    /**
     * Get Calling Intent
     * @param context
     * @param id
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String id) {
        Preconditions.checkNotNull(context, "Context can not be null");
        Preconditions.checkNotNull(id, "Id one can not be null");
        final Intent callingIntent =  new Intent(context, ConversationMessageListMvpActivity.class);
        callingIntent.putExtra(CONVERSATION_IDENTITY_ARG, id);
        return callingIntent;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        if(appUtils.isValidString(conversationId)) {
            args.putString(ConversationMessageListPresenter.CONVERSATION_IDENTITY_ARG, conversationId);
        } else {
            args.putString(ConversationMessageListPresenter.CONVERSATION_MEMBER_ONE_IDENTITY_ARG, memberOne);
            args.putString(ConversationMessageListPresenter.CONVERSATION_MEMBER_TWO_IDENTITY_ARG, memberTwo);
        }
        return args;
    }

    /**
     * Init Adapter
     */
    private void initAdapter() {
        messagesAdapter = new MessagesListAdapter<>(preferenceRepository.getPrefCurrentUserIdentity(),
                imageLoader);
        messagesAdapter.enableSelectionMode(this);
        messagesAdapter.setLoadMoreListener(this);
        messagesAdapter.setDateHeadersFormatter(this);
        messagesList.setAdapter(messagesAdapter);
    }

    /**
     * Get Sending Conversation Message
     * @return
     */
    private ConversationMessage getSendingConversationMessage(){
        return new ConversationMessage(SENDING_MESSAGE_ID,
                        new ConversationMessageUser(
                                preferenceRepository.getPrefCurrentUserIdentity(), "Sergio",
                                "https://avatars3.githubusercontent.com/u/6996211?s=460&v=4", true),
                        getString(R.string.sending_message_wait), new Date());
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        if(getIntent().hasExtra(CONVERSATION_IDENTITY_ARG)) {

            if(!appUtils.isValidString(getIntent().getStringExtra(CONVERSATION_IDENTITY_ARG)))
                throw new IllegalArgumentException("You must provide conversation identity");

            conversationId = getIntent().getStringExtra(CONVERSATION_IDENTITY_ARG);

        } else if(getIntent().hasExtra(CONVERSATION_MEMBER_ONE_IDENTITY_ARG) &&
                getIntent().hasExtra(CONVERSATION_MEMBER_TWO_IDENTITY_ARG)) {

            if(!appUtils.isValidString(getIntent().getStringExtra(CONVERSATION_MEMBER_ONE_IDENTITY_ARG)))
                throw new IllegalArgumentException("You must provide member one identity");

            if(!appUtils.isValidString(getIntent().getStringExtra(CONVERSATION_MEMBER_TWO_IDENTITY_ARG)))
                throw new IllegalArgumentException("You must provide member two identity");

            memberOne = getIntent().getStringExtra(CONVERSATION_MEMBER_ONE_IDENTITY_ARG);
            memberTwo = getIntent().getStringExtra(CONVERSATION_MEMBER_TWO_IDENTITY_ARG);

        } else {
            throw new IllegalArgumentException("You must provide args");
        }


        messageInput.setInputListener(this);
        initAdapter();
        swipeRefreshLayout.setOnRefreshListener(this);


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
        return R.layout.activity_conversation_message_list;
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public ConversationMessageListPresenter providePresenter() {
        return conversationComponent.conversationMessageListPresenter();
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
        return R.drawable.background_cyan_4;
    }


    /**
     * On Submit
     * @param input
     * @return
     */
    @Override
    public boolean onSubmit(CharSequence input) {
        messageInput.setEnabled(false);
        messageInput.setSaveEnabled(false);
        messagesAdapter.addToStart(getSendingConversationMessage(), true);
        final String currentUserId = preferenceRepository.getPrefCurrentUserIdentity();
        final String target = currentUserId.equals(memberOne) ? memberTwo : memberOne;
        getPresenter().addMessage(conversationId, input.toString(), currentUserId, target);
        return true;
    }

    /**
     * On Back Pressed
     */
    @Override
    public void onBackPressed() {
        if (selectionCount == 0) {
            super.onBackPressed();
        } else {
            messagesAdapter.unselectAllItems();
        }
    }

    /**
     * On Selection Changed
     * @param count
     */
    @Override
    public void onSelectionChanged(int count) {
        selectionCount = count;
    }


    /**
     * On Load More
     * @param page
     * @param totalItemsCount
     */
    @Override
    public void onLoadMore(int page, int totalItemsCount) {

    }

    /**
     * Format
     * @param date
     * @return
     */
    @Override
    public String format(Date date) {
        return null;
    }

    /**
     * On Clear Message
     */
    @OnClick(R.id.clearMessage)
    protected void onClearMessage(){

        if(selectionCount > 0) {

            showConfirmationDialog(R.string.delete_selected_messages_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    final List<ConversationMessage> conversationMessages = messagesAdapter.getSelectedMessages();
                    final List<String> messageIds = new ArrayList<>();
                    for(final ConversationMessage conversationMessage: conversationMessages) {
                        messageIds.add(conversationMessage.getId());
                    }
                    getPresenter().deleteMessages(conversationId, messageIds);
                }

                @Override
                public void onRejected(DialogFragment dialog) { }
            });

        } else {

            showConfirmationDialog(R.string.clear_messages_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    getPresenter().deleteAllMessages(conversationId);
                }

                @Override
                public void onRejected(DialogFragment dialog) {
                }
            });
        }

    }

    /**
     * On Conversation Messages Deleted
     */
    @Override
    public void onConversationMessagesDeleted() {
        clearMessageImageButton.setVisibility(View.GONE);
        messagesAdapter.clear(true);
        showNoticeDialog(R.string.all_messages_deleted);
    }

    /**
     * On Conversation Message Selected Deleted
     * @param messageIds
     */
    @Override
    public void onConversationMessagesSelectedDeleted(final List<String> messageIds) {
        Preconditions.checkNotNull(messageIds, "Message Ids can not be null");
        messagesAdapter.deleteSelectedMessages();
        if(messagesAdapter.isEmpty())
            clearMessageImageButton.setVisibility(View.GONE);
        showNoticeDialog(R.string.messages_selected_deleted);
    }

    /**
     * On Conversation Messages Loaded
     * @param messageEntities
     */
    @Override
    public void onConversationMessagesLoaded(List<MessageEntity> messageEntities) {
        Preconditions.checkNotNull(messageEntities, "Messages can not be null");
        Preconditions.checkState(!messageEntities.isEmpty(), "Messages can not be empty");

        swipeRefreshLayout.setRefreshing(false);
        clearMessageImageButton.setVisibility(View.VISIBLE);

        final List<ConversationMessage> conversationMessagesList = new ArrayList<>();
        for(final MessageEntity messageEntity: messageEntities) {

            final ConversationMessageUser userMessage = new ConversationMessageUser(
                    messageEntity.getFrom().getIdentity(), messageEntity.getFrom().getFullName(),
                    messageEntity.getFrom().getProfileImage(), true);

            final ConversationMessage conversationMessage =
                    new ConversationMessage(messageEntity.getIdentity(), userMessage, messageEntity.getText(), messageEntity.getCreateAt());

            conversationMessagesList.add(conversationMessage);
        }

        messagesAdapter.clear(true);
        messagesAdapter.addToEnd(conversationMessagesList, false);

    }

    /**
     * On No Conversation Messages Found
     */
    @Override
    public void onNoConversationMessagesFound() {
        Timber.d("No Conversation Messages Found");
        swipeRefreshLayout.setRefreshing(false);
        clearMessageImageButton.setVisibility(View.GONE);
    }

    /**
     * On Message Added
     * @param messageEntity
     */
    @Override
    public void onMessageAdded(MessageEntity messageEntity) {
        Preconditions.checkNotNull(messageEntity, "Message Entity");

        soundManager.playSound(ISoundManager.SEND_MESSAGE_SUCCESS);
        clearMessageImageButton.setVisibility(View.VISIBLE);
        messagesAdapter.deleteById(SENDING_MESSAGE_ID);
        messagesAdapter.addToStart(
                new ConversationMessage(messageEntity.getIdentity(), new ConversationMessageUser(
                        messageEntity.getFrom().getIdentity(), messageEntity.getFrom().getFullName(),
                        messageEntity.getFrom().getProfileImage(), true), messageEntity.getText()) , true);
    }

    /**
     * On Message Add Error
     */
    @Override
    public void onMessageAddError() {
        Timber.d("On Message Add Error");
        messageInput.setEnabled(true);
        messageInput.setSaveEnabled(true);
        messagesAdapter.deleteById(SENDING_MESSAGE_ID);
        showNoticeDialog(R.string.message_add_error);
    }

    /**
     * On Conversation Loaded
     * @param conversationEntity
     */
    @Override
    public void onConversationLoaded(final ConversationEntity conversationEntity) {
        Preconditions.checkNotNull(conversationEntity, "Conversation Entity can not be null");
        conversationId = conversationEntity.getIdentity();
        // Load Messages
        getPresenter().loadMessages(conversationId);
    }

    /**
     * On Refresh
     */
    @Override
    public void onRefresh() {
        getPresenter().loadMessages(conversationId);
    }


}
