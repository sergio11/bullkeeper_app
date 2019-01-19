package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.callslist;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
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
    public static final String CURRENT_TERMINAL_ARG = "CURRENT_TERMINAL_ARG";

    /**
     * Get Calls Details Interact
     */
    private final GetCallDetailsInteract getCallDetailsInteract;

    /**
     * Is Loading Data
     */
    private boolean isLoadingData = false;

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
        Preconditions.checkState(args.containsKey(CURRENT_TERMINAL_ARG), "You must provide Current Terminal");

        if (isLoadingData)
            return;

        isLoadingData = true;

        final TerminalItem terminalItem = (TerminalItem) args.getSerializable(CURRENT_TERMINAL_ARG);

        if(terminalItem != null) {

            if (isViewAttached() && getView() != null)
                getView().onShowLoading();

            getCallDetailsInteract.execute(new GetCallsListObservable(GetCallDetailsInteract.GetCallDetailsListApiErrors.class),
                    GetCallDetailsInteract.Params.create(
                            args.getString(KID_IDENTITY_ARG),
                            terminalItem.getIdentity()));
        }
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
            isLoadingData = false;
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
            isLoadingData = false;
        }
    }
}
