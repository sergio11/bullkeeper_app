package sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationslist;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.conversation.DeleteAllConversationForSelfUserInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.DeleteConversationByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.GetConversationsForSelfUserInteract;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;

/**
 * Conversation Presenter
 */
public final class ConversationListPresenter
        extends SupportLCEPresenter<IConversationListView> {

    /**
     * Get Conversations For Self User Interact
     */
    private final GetConversationsForSelfUserInteract getConversationsForSelfUserInteract;

    /**
     * Delete All Conversation For Self User
     */
    private final DeleteAllConversationForSelfUserInteract deleteAllConversationForSelfUserInteract;

    /**
     * Delete Conversation By Id Interact
     */
    private final DeleteConversationByIdInteract deleteConversationByIdInteract;


    /**
     *
     * @param getConversationsForSelfUserInteract
     * @param deleteAllConversationForSelfUserInteract
     * @param deleteConversationByIdInteract
     */
    @Inject
    public ConversationListPresenter(final GetConversationsForSelfUserInteract getConversationsForSelfUserInteract,
                                     final DeleteAllConversationForSelfUserInteract deleteAllConversationForSelfUserInteract,
                                     final DeleteConversationByIdInteract deleteConversationByIdInteract) {
        this.getConversationsForSelfUserInteract = getConversationsForSelfUserInteract;
        this.deleteAllConversationForSelfUserInteract = deleteAllConversationForSelfUserInteract;
        this.deleteConversationByIdInteract = deleteConversationByIdInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {

        getConversationsForSelfUserInteract.execute(new GetConversationListObservable(
                GetConversationsForSelfUserInteract.GetConversationsForSelfUserApiErrors.class),
                null);

    }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(final Bundle args) { loadData(); }


    /**
     * Delete All
     */
    public void deleteAll(){

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        deleteAllConversationForSelfUserInteract.execute(new DeleteAllConversationObservable(),
                null);

    }

    /**
     * Delete By Id
     * @param id
     */
    public void deleteById(final String id) {
        Preconditions.checkNotNull(id, "Id can not be null");

        deleteConversationByIdInteract.execute(new DeleteConversationByIdObservable(),
                DeleteConversationByIdInteract.Params.create(id));

    }

    /**
     * Get Conversation List Observable
     */
    public class GetConversationListObservable extends CommandCallBackWrapper<List<ConversationEntity>,
            GetConversationsForSelfUserInteract.GetConversationsForSelfUserApiErrors.IGetConversationsForSelfUserErrorVisitor,
            GetConversationsForSelfUserInteract.GetConversationsForSelfUserApiErrors>
            implements GetConversationsForSelfUserInteract.GetConversationsForSelfUserApiErrors.IGetConversationsForSelfUserErrorVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetConversationListObservable(final Class<GetConversationsForSelfUserInteract.GetConversationsForSelfUserApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final List<ConversationEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }
        }

        /**
         *
         * @param apiErrors
         */
        @Override
        public void visitNoConversationsFound(GetConversationsForSelfUserInteract.GetConversationsForSelfUserApiErrors apiErrors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }

    /**
     * Delete All Conversation Observable
     */
    public class DeleteAllConversationObservable extends BasicCommandCallBackWrapper<String> {

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }

    /**
     * Delete Conversation By Id Observable
     */
    public class DeleteConversationByIdObservable extends BasicCommandCallBackWrapper<String> {

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onConversationDeletedSuccessfully();
            }
        }
    }
}
