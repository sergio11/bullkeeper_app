package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminals;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.terminal.DeleteAllTerminalsForKidInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.GetMonitoredTerminalsInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.SwitchLockScreenStatusForAllTerminalsOfKidInteract;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;

/**
 * Terminals Fragment Presenter
 */
public final class TerminalsFragmentPresenter extends SupportLCEPresenter<ITerminalsFragmentView> {

    public static final String SON_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Monitored Terminals Interact
     */
    private final GetMonitoredTerminalsInteract getMonitoredTerminalsInteract;

    /**
     * Delete All Terminals For Kid Interact
     */
    private final DeleteAllTerminalsForKidInteract deleteAllTerminalsForKidInteract;

    /**
     * Switch Lock Screen Status For All Terminals Of Kid Interact
     */
    private final SwitchLockScreenStatusForAllTerminalsOfKidInteract switchLockScreenStatusForAllTerminalsOfKidInteract;

    /**
     * Is Loading Data
     */
    private boolean isLoadingData = false;


    /**
     *
     * @param getMonitoredTerminalsInteract
     * @param deleteAllTerminalsForKidInteract
     * @param switchLockScreenStatusForAllTerminalsOfKidInteract
     */
    @Inject
    public TerminalsFragmentPresenter(
            final GetMonitoredTerminalsInteract getMonitoredTerminalsInteract,
            final DeleteAllTerminalsForKidInteract deleteAllTerminalsForKidInteract,
            final SwitchLockScreenStatusForAllTerminalsOfKidInteract switchLockScreenStatusForAllTerminalsOfKidInteract){
        this.getMonitoredTerminalsInteract = getMonitoredTerminalsInteract;
        this.deleteAllTerminalsForKidInteract = deleteAllTerminalsForKidInteract;
        this.switchLockScreenStatusForAllTerminalsOfKidInteract = switchLockScreenStatusForAllTerminalsOfKidInteract;
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
        Preconditions.checkState(args.containsKey(SON_IDENTITY_ARG), "You must provide a son identity value");

        if(isLoadingData)
            return;

        isLoadingData = true;

        if (isViewAttached() && getView() != null)
            getView().onShowLoading();

        getMonitoredTerminalsInteract.execute(new GetMonitoredTerminalsObservable(GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors.class),
                GetMonitoredTerminalsInteract.Params.create(args.getString(SON_IDENTITY_ARG)));
    }

    /**
     * Delete All
     * @param kid
     */
    public void deleteAll(final String kid){
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        deleteAllTerminalsForKidInteract.execute(new DeleteAllKidTerminalsObservable(),
                DeleteAllTerminalsForKidInteract.Params.create(kid));
    }

    /**
     * Switch Lock Screen Status
     * @param kid
     * @param status
     */
    public void switchLockScreenStatus(final String kid, final boolean status) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        switchLockScreenStatusForAllTerminalsOfKidInteract.execute(new SwitchLockScreenStatusForAllTerminalsObservable(),
                SwitchLockScreenStatusForAllTerminalsOfKidInteract.Params.create(kid, status));
    }


    /**
     * Get Monitored Terminals Observable
     */
    public class GetMonitoredTerminalsObservable extends CommandCallBackWrapper<List<TerminalEntity>,
            GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors.IGetMonitoredTerminalsApiErrorsVisitor,
            GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors>
            implements GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors.IGetMonitoredTerminalsApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetMonitoredTerminalsObservable(Class<GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors> apiErrors) {
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
         * @param terminals
         */
        @Override
        protected void onSuccess(List<TerminalEntity> terminals) {
            Preconditions.checkNotNull(terminals, "Terminals can not be null");

            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(terminals);
            }

            isLoadingData = false;

        }

        /**
         *
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoMonitoredTerminalsFound(GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors.IGetMonitoredTerminalsApiErrorsVisitor apiErrorsVisitor) {

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }

            isLoadingData = false;
        }
    }

    /**
     * Delete All Kid Terminals Observable
     */
    public class DeleteAllKidTerminalsObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * on Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }

    /**
     * Switch Lock Screen Status For All Terminals Observable
     */
    public class SwitchLockScreenStatusForAllTerminalsObservable extends BasicCommandCallBackWrapper<String> {

        /**
         *
         */
        @Override
        protected void onNetworkError() {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onLockScreenStatusChangedFailed();
            }
        }

        /**
         *
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onLockScreenStatusChangedFailed();
            }
        }

        /**
         *
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onLockScreenStatusChangedFailed();
            }
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            Preconditions.checkState(!response.isEmpty(), "Response can not be empty");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onLockScreenStatusChangedSuccessfully();
            }
        }
    }
}
