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
import sanchez.sanchez.sergio.domain.interactor.conversation.GetConversationMessagesInteract;
import sanchez.sanchez.sergio.domain.models.MessageEntity;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;

/**
 * Conversation Message List Presenter
 */
public final class ConversationMessageListPresenter extends SupportPresenter<IConversationMessageListView> {

    /**
     * Args
     */
    public static final String CONVERSATION_KID_IDENTITY_ARG = "CONVERSATION_KID_IDENTITY_ARG";

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
     * Preference Repository
     */
    private final IPreferenceRepository preferenceRepository;

    /**
     *
     * @param getConversationMessagesInteract
     * @param deleteConversationMessagesInteract
     * @param addMessageInteract
     */
    @Inject
    public ConversationMessageListPresenter(
            final GetConversationMessagesInteract getConversationMessagesInteract,
            final DeleteConversationMessagesInteract deleteConversationMessagesInteract,
            final AddMessageInteract addMessageInteract,
            final IPreferenceRepository preferenceRepository
    ) {
        super();
        this.getConversationMessagesInteract = getConversationMessagesInteract;
        this.deleteConversationMessagesInteract = deleteConversationMessagesInteract;
        this.addMessageInteract = addMessageInteract;
        this.preferenceRepository = preferenceRepository;
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

        if(args.isEmpty() || !args.containsKey(CONVERSATION_KID_IDENTITY_ARG) ||
                !appUtils.isValidString(args.getString(CONVERSATION_KID_IDENTITY_ARG)))
            throw new IllegalStateException("You must provide conversation kid identity");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        loadMessages();
    }

    /**
     * Load Messages
     */
    public void loadMessages(){
        getConversationMessagesInteract.execute(new GetConversationMessagesObservable(
                GetConversationMessagesInteract.GetConversationMessagesApiErrors.class
        ), GetConversationMessagesInteract.Params.create(args.getString(CONVERSATION_KID_IDENTITY_ARG)));
    }

    /**
     * Delete All Messages
     */
    public void deleteAllMessages(){
        deleteConversationMessagesInteract.execute(new DeleteConversationMessagesObservable(),
                DeleteConversationMessagesInteract.Params.create(args.getString(CONVERSATION_KID_IDENTITY_ARG)));
    }

    /**
     * Add Message
     * @param text
     */
    public void addMessage(final String text) {
        Preconditions.checkNotNull(text, "Text can not be null");
        Preconditions.checkState(!text.isEmpty(), "Text can not be empty");

        final String from = preferenceRepository.getPrefCurrentUserIdentity();
        final String kid = args.getString(CONVERSATION_KID_IDENTITY_ARG);

        addMessageInteract.execute(new AddMessagesObservable(),
                AddMessageInteract.Params.create(kid, from, kid, text));
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
                getView().onConversationMessagesDeleted();
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
