package sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationmessages;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.conversation.AddMessageInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.DeleteConversationMessagesInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.GetConversationDetailsForMembersInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.GetConversationMessagesInteract;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.models.MessageEntity;

/**
 * Conversation Message List Presenter
 */
public final class ConversationMessageListPresenter extends SupportPresenter<IConversationMessageListView> {

    /**
     * Args
     */
    public static final String CONVERSATION_MEMBER_ONE_IDENTITY_ARG = "CONVERSATION_MEMBER_ONE_IDENTITY_ARG";
    public static final String CONVERSATION_MEMBER_TWO_IDENTITY_ARG = "CONVERSATION_MEMBER_TWO_IDENTITY_ARG";

    /**
     * Get Conversation Messages Interact
     */
    private final GetConversationMessagesInteract getConversationMessagesInteract;

    /**
     * Delete Conversation Messages Interact
     */
    private final DeleteConversationMessagesInteract deleteConversationMessagesInteract;

    /**
     * Add Message Interact
     */
    private final AddMessageInteract addMessageInteract;


    /**
     * Get Conversation Details For Members Interact
     */
    private final GetConversationDetailsForMembersInteract getConversationDetailsForMembersInteract;

    /**
     *
     * @param getConversationMessagesInteract
     * @param deleteConversationMessagesInteract
     * @param addMessageInteract
     * @param getConversationDetailsForMembersInteract
     */
    @Inject
    public ConversationMessageListPresenter(
            final GetConversationMessagesInteract getConversationMessagesInteract,
            final DeleteConversationMessagesInteract deleteConversationMessagesInteract,
            final AddMessageInteract addMessageInteract,
            final GetConversationDetailsForMembersInteract getConversationDetailsForMembersInteract
    ) {
        super();
        this.getConversationMessagesInteract = getConversationMessagesInteract;
        this.deleteConversationMessagesInteract = deleteConversationMessagesInteract;
        this.addMessageInteract = addMessageInteract;
        this.getConversationDetailsForMembersInteract = getConversationDetailsForMembersInteract;
    }


    /**
     * On Init
     */
    @Override
    protected void onInit() {
        super.onInit();
        throw new IllegalStateException("You must provide conversation kid identity");
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

        if(args.isEmpty())
            throw new IllegalStateException("You must provide args");

        if(!args.containsKey(CONVERSATION_MEMBER_ONE_IDENTITY_ARG) ||
                !appUtils.isValidString(args.getString(CONVERSATION_MEMBER_ONE_IDENTITY_ARG)))
            throw new IllegalStateException("You must provide member one identity");

        if(!args.containsKey(CONVERSATION_MEMBER_TWO_IDENTITY_ARG) ||
                !appUtils.isValidString(args.getString(CONVERSATION_MEMBER_TWO_IDENTITY_ARG)))
            throw new IllegalArgumentException("You must provide member two identity");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        getConversationDetails();
    }


    /**
     * Get Conversation Details
     */
    public void getConversationDetails() {

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        final String memberOne = args.getString(CONVERSATION_MEMBER_ONE_IDENTITY_ARG);
        final String memberTwo = args.getString(CONVERSATION_MEMBER_TWO_IDENTITY_ARG);

        // Get Conversation
        getConversationDetailsForMembersInteract.execute(new GetConversationDetailsForMembersObservable(),
                GetConversationDetailsForMembersInteract.Params.create(memberOne, memberTwo));

    }

    /**
     * Load Messages
     * @param conversation
     */
    public void loadMessages(final String conversation){
        Preconditions.checkNotNull(conversation, "Conversation can not be null");
        Preconditions.checkState(!conversation.isEmpty(), "Conversation can not be empty");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        getConversationMessagesInteract.execute(new GetConversationMessagesObservable(
                GetConversationMessagesInteract.GetConversationMessagesApiErrors.class
        ), GetConversationMessagesInteract.Params.create(conversation));
    }

    /**
     * Delete Messages
     * @param conversation
     * @param messageIds
     */
    public void deleteMessages(final String conversation, final List<String> messageIds){
        Preconditions.checkNotNull(conversation, "Conversation can not be null");
        Preconditions.checkState(!conversation.isEmpty(), "Conversation can not be empty");
        Preconditions.checkNotNull(messageIds, "Message Ids can not be null");
        Preconditions.checkState(!messageIds.isEmpty(), "Message ids can not be empty");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        deleteConversationMessagesInteract.execute(new DeleteSelectedConversationMessagesObservable(messageIds),
                DeleteConversationMessagesInteract.Params.create(conversation, messageIds));
    }

    /**
     * Delete All Messages
     */
    public void deleteAllMessages(final String conversation){
        Preconditions.checkNotNull(conversation, "Conversation can not be null");
        Preconditions.checkState(!conversation.isEmpty(), "Conversation can not be empty");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        deleteConversationMessagesInteract.execute(new DeleteConversationMessagesObservable(),
                DeleteConversationMessagesInteract.Params.create(conversation));
    }

    /**
     * Add Message
     * @param conversation
     * @param text
     */
    public void addMessage(final String conversation, final String text) {
        Preconditions.checkNotNull(conversation, "Conversation can not be null");
        Preconditions.checkState(!conversation.isEmpty(), "Conversation can not be empty");
        Preconditions.checkNotNull(text, "Text can not be null");
        Preconditions.checkState(!text.isEmpty(), "Text can not be empty");

        final String from = args.getString(CONVERSATION_MEMBER_ONE_IDENTITY_ARG);
        final String to = args.getString(CONVERSATION_MEMBER_TWO_IDENTITY_ARG);

        addMessageInteract.execute(new AddMessagesObservable(),
                AddMessageInteract.Params.create(conversation, from, to, text));
    }

    /**
     * Delete Conversation Messages Observable
     */
    public class DeleteConversationMessagesObservable
            extends BasicCommandCallBackWrapper<String> {

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onConversationMessagesDeleted();
            }
        }
    }

    /**
     * Delete Selected Conversation Messages Observable
     */
    public class DeleteSelectedConversationMessagesObservable
            extends BasicCommandCallBackWrapper<String> {

        /**
         * Message Ids
         */
        private final List<String> messageIds;

        /**
         *
         * @param messageIds
         */
        public DeleteSelectedConversationMessagesObservable(final List<String> messageIds) {
            this.messageIds = messageIds;
        }

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onConversationMessagesSelectedDeleted(messageIds);
            }
        }
    }

    /**
     * Get Conversation Details For Members Observable
     */
    public class GetConversationDetailsForMembersObservable
        extends BasicCommandCallBackWrapper<ConversationEntity> {

        /**
         * On Success
         * @param conversationEntity
         */
        @Override
        protected void onSuccess(final ConversationEntity conversationEntity) {
            Preconditions.checkNotNull(conversationEntity, "Conversation can not be null");

            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onConversationLoaded(conversationEntity);
            }

        }


    }

    /**
     * Add Messages Observable
     */
    public class AddMessagesObservable
            extends BasicCommandCallBackWrapper<MessageEntity> {

        /**
         *
         * @param messageEntity
         */
        @Override
        protected void onSuccess(MessageEntity messageEntity) {
            Preconditions.checkNotNull(messageEntity, "Message can not be null");
            if (isViewAttached() && getView() != null) {
                getView().onMessageAdded(messageEntity);
            }
        }

        /**
         * On Network Error
         */
        @Override
        protected void onNetworkError() {
            if (isViewAttached() && getView() != null)
                getView().onMessageAddError();
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            if (isViewAttached() && getView() != null)
                getView().onMessageAddError();
        }

        /**
         * ON Api Exception
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            if (isViewAttached() && getView() != null)
                getView().onMessageAddError();
        }
    }



    /**
     * Get Conversation Message Observable
     */
    public class GetConversationMessagesObservable extends CommandCallBackWrapper<List<MessageEntity>,
            GetConversationMessagesInteract.GetConversationMessagesApiErrors.IGetConversationMessagesErrorVisitor,
            GetConversationMessagesInteract.GetConversationMessagesApiErrors>
            implements GetConversationMessagesInteract.GetConversationMessagesApiErrors.IGetConversationMessagesErrorVisitor {

        /**
         * Get Conversation Messages Observable
         * @param apiErrors
         */
        public GetConversationMessagesObservable(Class<GetConversationMessagesInteract.GetConversationMessagesApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(List<MessageEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onConversationMessagesLoaded(response);
            }

        }

        /**
         * Visit No Messages Found
         * @param apiErrors
         */
        @Override
        public void visitNoMessagesFound(GetConversationMessagesInteract.GetConversationMessagesApiErrors apiErrors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoConversationMessagesFound();
            }
        }
    }
}
