package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.devicephotos;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportSearchLCEPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminaldetail.TerminalDetailFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.contacts.GetContactListInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.SwitchLockCameraStatusInteract;
import sanchez.sanchez.sergio.domain.models.ContactEntity;

/**
 * Device Photos Fragment Presenter
 */
public final class DevicePhotosFragmentPresenter extends SupportSearchLCEPresenter<IDevicePhotosListFragmentView> {

    /**
     * Args
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    public static final String CURRENT_TERMINAL_ARG = "CURRENT_TERMINAL_ARG";

    /**
     * Get Contact List Interact
     */
    private final GetContactListInteract getContactListInteract;

    /**
     * Switch Lock Camera Status Interact
     */
    private final SwitchLockCameraStatusInteract switchLockCameraStatusInteract;

    /**
     * Is Loading Data
     */
    private boolean isLoadingData = false;

    /**
     * @param getContactListInteract
     * @param switchLockCameraStatusInteract
     */
    @Inject
    public DevicePhotosFragmentPresenter(final GetContactListInteract getContactListInteract,
                                         final SwitchLockCameraStatusInteract switchLockCameraStatusInteract){
        this.getContactListInteract = getContactListInteract;
        this.switchLockCameraStatusInteract = switchLockCameraStatusInteract;
    }

    /**
     * Load Date
     * @param args
     * @param queryText
     */
    @Override
    public void loadData(Bundle args, String queryText) {
        super.loadData(args, queryText);

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

            getContactListInteract.execute(new GetContactListObservable(GetContactListInteract.GetContactListApiErrors.class),
                    GetContactListInteract.Params.create(
                            args.getString(KID_IDENTITY_ARG),
                            terminalItem.getIdentity(), queryText));
        }
    }

    /**
     * Switch Lock Camera Status
     * @param kid
     * @param terminal
     * @param status
     */
    public void switchLockCameraStatus(final String kid, final String terminal, final boolean status) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);


        switchLockCameraStatusInteract.execute(new SwitchLockCameraStatusObserver(),
                SwitchLockCameraStatusInteract.Params.create(kid, terminal, status));
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


    /**
     * Switch Lock Camera Status Observer
     */
    public class SwitchLockCameraStatusObserver extends BasicCommandCallBackWrapper<String> {

        /**
         *
         */
        @Override
        protected void onNetworkError() {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onLockCameraStatusChangedFailed();
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
                getView().onLockCameraStatusChangedFailed();
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
                getView().onLockCameraStatusChangedFailed();
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
                getView().onLockCameraStatusChangedSuccessfully();
            }
        }
    }

}
