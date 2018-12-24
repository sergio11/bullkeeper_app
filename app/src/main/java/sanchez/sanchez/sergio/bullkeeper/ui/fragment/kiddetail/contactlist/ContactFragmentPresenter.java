package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactlist;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportSearchLCEPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.contacts.GetContactListInteract;
import sanchez.sanchez.sergio.domain.models.ContactEntity;

/**
 * Contact Fragment Presenter
 */
public final class ContactFragmentPresenter extends SupportSearchLCEPresenter<IContactListFragmentView> {

    /**
     * Args
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    public static final String TERMINALS_ARG = "TERMINALS_ARG";
    public static final String CURRENT_TERMINAL_POS_ARG = "CURRENT_TERMINAL_POS_ARG";

    /**
     * Get Contact List Interact
     */
    private final GetContactListInteract getContactListInteract;

    /**
     * Is Loading Data
     */
    private boolean isLoadingData = false;

    /**
     *
     * @param getContactListInteract
     */
    @Inject
    public ContactFragmentPresenter(final GetContactListInteract getContactListInteract){
        this.getContactListInteract = getContactListInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() { loadContacts(null); }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) { loadContacts(null); }

    /**
     * Load Data
     * @param queryText
     */
    @Override
    public void loadData(String queryText) { loadContacts(queryText); }

    /**
     * Load Contacts
     * @param queryText
     */
    private void loadContacts(final String queryText) {

        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KID_IDENTITY_ARG), "You must provide a son identity value");
        Preconditions.checkState(args.containsKey(TERMINALS_ARG), "You must provide terminals list");
        final ArrayList<TerminalItem> terminalItems = (ArrayList<TerminalItem>) args.getSerializable(TERMINALS_ARG);
        Preconditions.checkNotNull(terminalItems, "Terminal list can not be null");
        Preconditions.checkState(!terminalItems.isEmpty(), "Terminal list can not be empty");
        Preconditions.checkState(args.containsKey(CURRENT_TERMINAL_POS_ARG), "You must provide a terminal pos");

        if (isLoadingData)
            return;

        isLoadingData = true;

        final TerminalItem terminalItem =
                terminalItems.get(args.getInt(CURRENT_TERMINAL_POS_ARG));

        if(terminalItem != null) {

            if (isViewAttached() && getView() != null)
                getView().onShowLoading();

            getContactListInteract.execute(new GetContactListObservable(GetContactListInteract.GetContactListApiErrors.class),
                    GetContactListInteract.Params.create(
                            args.getString(KID_IDENTITY_ARG),
                            terminalItem.getIdentity(), queryText));
        }
    }


    /**
     * Get Contact List Observable
     */
    public class GetContactListObservable extends CommandCallBackWrapper<List<ContactEntity>,
            GetContactListInteract.GetContactListApiErrors.IGetContactListApiErrorsVisitor,
            GetContactListInteract.GetContactListApiErrors>
            implements GetContactListInteract.GetContactListApiErrors.IGetContactListApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetContactListObservable(Class<GetContactListInteract.GetContactListApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Network Error
         */
        @Override
        protected void onNetworkError() {
            super.onNetworkError();
            isLoadingData = false;
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            super.onOtherException(ex);
            isLoadingData = false;
        }

        /**
         * On Api Exception
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            super.onApiException(response);
            isLoadingData = false;
        }

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(List<ContactEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            Preconditions.checkState(!response.isEmpty(), "Response can not be empty");

            if (isViewAttached() && getView() != null){
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }

            isLoadingData = false;

        }

        /**
         * Visit No Contacts Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoContactsFound(GetContactListInteract.GetContactListApiErrors.IGetContactListApiErrorsVisitor apiErrorsVisitor) {
            Preconditions.checkNotNull(apiErrorsVisitor, "Api Errors Visitor");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }

            isLoadingData = false;
        }
    }


}
