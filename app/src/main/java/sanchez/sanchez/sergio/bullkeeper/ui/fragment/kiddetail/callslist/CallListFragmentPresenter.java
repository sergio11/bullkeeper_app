package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.callslist;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.domain.interactor.calls.GetCallDetailsInteract;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;

/**
 * Call List Fragment Presenter
 */
public final class CallListFragmentPresenter extends SupportLCEPresenter<ICallsListFragmentView> {

    /**
     * Args
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    public static final String TERMINALS_ARG = "TERMINALS_ARG";
    public static final String CURRENT_TERMINAL_POS_ARG = "CURRENT_TERMINAL_POS_ARG";

    /**
     * Get Calls Details Interact
     */
    private final GetCallDetailsInteract getCallDetailsInteract;

    /**
     * @param getCallDetailsInteract
     */
    @Inject
    public CallListFragmentPresenter(final GetCallDetailsInteract getCallDetailsInteract){
        this.getCallDetailsInteract = getCallDetailsInteract;
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
            getCallDetailsInteract.execute(new GetCallsListObservable(GetCallDetailsInteract.GetCallDetailsListApiErrors.class),
                    GetCallDetailsInteract.Params.create(
                            args.getString(KID_IDENTITY_ARG),
                            terminalItem.getIdentity()));
    }


    /**
     * Get Call List Observable
     */
    public class GetCallsListObservable extends CommandCallBackWrapper<List<CallDetailEntity>,
            GetCallDetailsInteract.GetCallDetailsListApiErrors.IGetCallDetailsListApiErrorsVisitor,
            GetCallDetailsInteract.GetCallDetailsListApiErrors>
            implements GetCallDetailsInteract.GetCallDetailsListApiErrors.IGetCallDetailsListApiErrorsVisitor {


        /**
         *
         * @param apiErrors
         */
        public GetCallsListObservable(final Class<GetCallDetailsInteract.GetCallDetailsListApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param calls
         */
        @Override
        protected void onSuccess(final List<CallDetailEntity> calls) {
            Preconditions.checkNotNull(calls, "Calls can not be null");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(calls);
            }
        }

        /**
         * Visit No Calls Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoCallsFound(final GetCallDetailsInteract.GetCallDetailsListApiErrors
                .IGetCallDetailsListApiErrorsVisitor apiErrorsVisitor) {
            Preconditions.checkNotNull(apiErrorsVisitor, "Api Errors Visitor can not be null");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }
}
