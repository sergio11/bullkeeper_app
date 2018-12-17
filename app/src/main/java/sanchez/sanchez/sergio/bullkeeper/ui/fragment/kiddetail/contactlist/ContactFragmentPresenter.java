package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactlist;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.domain.interactor.contacts.GetContactListInteract;
import sanchez.sanchez.sergio.domain.models.ContactEntity;

/**
 * Contact Fragment Presenter
 */
public final class ContactFragmentPresenter extends SupportLCEPresenter<IContactListFragmentView> {

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
    public void loadData() { }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KID_IDENTITY_ARG), "You must provide a son identity value");
        Preconditions.checkState(args.containsKey(TERMINALS_ARG), "You must provide terminals list");
        final ArrayList<TerminalItem> terminalItems = (ArrayList<TerminalItem>) args.getSerializable(TERMINALS_ARG);
        Preconditions.checkNotNull(terminalItems, "Terminal list can not be null");
        Preconditions.checkState(!terminalItems.isEmpty(), "Terminal list can not be empty");
        Preconditions.checkState(args.containsKey(CURRENT_TERMINAL_POS_ARG), "You must provide a terminal pos");

        final TerminalItem terminalItem =
                terminalItems.get(args.getInt(CURRENT_TERMINAL_POS_ARG));

        if(terminalItem != null)
            getContactListInteract.execute(new GetContactListObservable(GetContactListInteract.GetContactListApiErrors.class),
                    GetContactListInteract.Params.create(
                            args.getString(KID_IDENTITY_ARG),
                            terminalItem.getIdentity()));
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
        }
    }


}
