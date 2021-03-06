package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminaldetail;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.terminal.DeleteTerminalInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.GetTerminalDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.SaveHeartbeatConfigurationInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.SwitchBedTimeStatusInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.SwitchLockCameraStatusInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.SwitchLockScreenStatusInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.SwitchSettingsScreenStatusInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.SwitchTerminalPhoneCallsStatusInteract;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import sanchez.sanchez.sergio.domain.models.TerminalHeartbeatEntity;

/**
 * Terminal Detail Presenter
 */
public final class TerminalDetailFragmentPresenter
        extends SupportPresenter<ITerminalDetailView> {


    /**
     * Get Terminal Detail Interact
     */
    private final GetTerminalDetailInteract getTerminalDetailInteract;


    /**
     * Delete Terminal Interact
     */
    private DeleteTerminalInteract deleteTerminalInteract;

    /**
     * Switch Lock Screen Status Interact
     */
    private final SwitchLockScreenStatusInteract switchLockScreenStatusInteract;

    /**
     * Switch Lock Camera Status Interact
     */
    private final SwitchLockCameraStatusInteract switchLockCameraStatusInteract;

    /**
     * Switch Bed Time Status Interact
     */
    private final SwitchBedTimeStatusInteract switchBedTimeStatusInteract;

    /**
     * Switch Settings Screen Status Interact
     */
    private final SwitchSettingsScreenStatusInteract switchSettingsScreenStatusInteract;

    /**
     * Save Heartbeat Configuration Interact
     */
    private final SaveHeartbeatConfigurationInteract saveHeartbeatConfigurationInteract;

    /**
     * Switch Terminal Phone Calls Status Interact
     */
    private final SwitchTerminalPhoneCallsStatusInteract switchTerminalPhoneCallsStatusInteract;

    /**
     * @param getTerminalDetailInteract
     * @param deleteTerminalInteract
     * @param switchLockScreenStatusInteract
     * @param switchLockCameraStatusInteract
     * @param switchBedTimeStatusInteract
     * @param switchSettingsScreenStatusInteract
     * @param saveHeartbeatConfigurationInteract
     * @param switchTerminalPhoneCallsStatusInteract
     */
    @Inject
    public TerminalDetailFragmentPresenter(
            final GetTerminalDetailInteract getTerminalDetailInteract,
            final DeleteTerminalInteract deleteTerminalInteract,
            final SwitchLockScreenStatusInteract switchLockScreenStatusInteract,
            final SwitchLockCameraStatusInteract switchLockCameraStatusInteract,
            final SwitchBedTimeStatusInteract switchBedTimeStatusInteract,
            final SwitchSettingsScreenStatusInteract switchSettingsScreenStatusInteract,
            final SaveHeartbeatConfigurationInteract saveHeartbeatConfigurationInteract,
            final SwitchTerminalPhoneCallsStatusInteract switchTerminalPhoneCallsStatusInteract){
        this.getTerminalDetailInteract = getTerminalDetailInteract;
        this.deleteTerminalInteract = deleteTerminalInteract;
        this.switchLockScreenStatusInteract = switchLockScreenStatusInteract;
        this.switchLockCameraStatusInteract = switchLockCameraStatusInteract;
        this.switchBedTimeStatusInteract = switchBedTimeStatusInteract;
        this.switchSettingsScreenStatusInteract = switchSettingsScreenStatusInteract;
        this.saveHeartbeatConfigurationInteract = saveHeartbeatConfigurationInteract;
        this.switchTerminalPhoneCallsStatusInteract = switchTerminalPhoneCallsStatusInteract;
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_terminal_detail);

        final String childId = args.getString(TerminalDetailActivityMvpFragment.CHILD_ID_ARG);
        final String terminalId = args.getString(TerminalDetailActivityMvpFragment.TERMINAL_ID_ARG);

        getTerminalDetailInteract.execute(new GetTerminalDetailObserver(),
                GetTerminalDetailInteract.Params.create(childId, terminalId));

    }

    /**
     * Delete Terminal
     * @param childId
     * @param terminalId
     */
    public void deleteTerminal(final String childId, final String terminalId) {
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.deleting_terminal_in_progress);

        deleteTerminalInteract.execute(new DeleteTerminalObserver(),
                DeleteTerminalInteract.Params.create(childId, terminalId));

    }

    /**
     * Switch Bed Time Status
     * @param kid
     * @param terminal
     * @param status
     */
    public void switchBedTimeStatus(final String kid, final String terminal, final boolean status) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        switchBedTimeStatusInteract.execute(new SwitchBedTimeStatusObserver(),
                SwitchBedTimeStatusInteract.Params.create(kid, terminal, status));
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
     * Switch Lock Screen Status
     * @param kid
     * @param terminal
     * @param status
     */
    public void switchLockScreenStatus(final String kid, final String terminal, final boolean status) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        switchLockScreenStatusInteract.execute(new SwitchLockScreenStatusObserver(),
                SwitchLockScreenStatusInteract.Params.create(kid, terminal, status));
    }

    /**
     * Switch Settings Screen Status
     * @param kid
     * @param terminal
     * @param status
     */
    public void switchSettingsScreenStatus(final String kid, final String terminal, final boolean status) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        switchSettingsScreenStatusInteract.execute(new SwitchSettingsStatusObserver(),
                SwitchSettingsScreenStatusInteract.Params.create(kid, terminal, status));
    }

    /**
     * Switch Phone Calls Status
     * @param kid
     * @param terminal
     * @param status
     */
    public void switchPhoneCallsStatus(final String kid, final String terminal, final boolean status) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        switchTerminalPhoneCallsStatusInteract.execute(new SwitchPhoneCallsStatusObserver(),
                SwitchTerminalPhoneCallsStatusInteract.Params.create(kid, terminal, status));
    }

    /**
     *
     * @param childId
     * @param terminalId
     * @param alertThresholdInMinutes
     * @param isAlertModeEnabled
     */
    public void saveHeartBeatConfiguration(final String childId, final String terminalId,
                                           final int alertThresholdInMinutes, final boolean isAlertModeEnabled){

        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkNotNull(terminalId, "Terminal can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);


        saveHeartbeatConfigurationInteract.execute(new SaveHeartbeatConfigurationObserver(),
                SaveHeartbeatConfigurationInteract.Params.create(childId, terminalId, alertThresholdInMinutes, isAlertModeEnabled));

    }

    /**
     * Save Heartbeat Configuration Observer
     */
    public class SaveHeartbeatConfigurationObserver extends BasicCommandCallBackWrapper<TerminalHeartbeatEntity> {

        /**
         *
         */
        @Override
        protected void onNetworkError() {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onHeartBeatSavedFailed();
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
                getView().onHeartBeatSavedFailed();
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
                getView().onHeartBeatSavedFailed();
            }
        }


        /**
         * On Success
         * @param terminalHeartbeatEntity
         */
        @Override
        protected void onSuccess(TerminalHeartbeatEntity terminalHeartbeatEntity) {
            Preconditions.checkNotNull(terminalHeartbeatEntity, "Response can not be null");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onHeartBeatSavedSuccessfully(terminalHeartbeatEntity);
            }
        }
    }

    /**
     * Delete Terminal
     */
    public class DeleteTerminalObserver extends BasicCommandCallBackWrapper<String> {

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
                getView().onTerminalSuccessDeleted();
            }
        }
    }

    /**
     * Get Terminal Detail Observer
     */
    public class GetTerminalDetailObserver extends BasicCommandCallBackWrapper<TerminalDetailEntity> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(TerminalDetailEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onTerminalDetailLoaded(response);
            }

        }
    }


    /**
     * Switch Bed Time Status Observer
     */
    public class SwitchBedTimeStatusObserver extends BasicCommandCallBackWrapper<String> {

        /**
         *
         */
        @Override
        protected void onNetworkError() {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onBedTimeStatusChangedFailed();
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
                getView().onBedTimeStatusChangedFailed();
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
                getView().onBedTimeStatusChangedFailed();
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
                getView().onBedTimeStatusChangedSuccessfully();
            }
        }
    }


    /**
     * Switch Lock Screen Status Observer
     */
    public class SwitchLockScreenStatusObserver extends BasicCommandCallBackWrapper<String> {

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

    /**
     * Switch Settings Status Observer
     */
    public class SwitchSettingsStatusObserver extends BasicCommandCallBackWrapper<String> {

        /**
         *
         */
        @Override
        protected void onNetworkError() {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSettingsScreenStatusChangedFailed();
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
                getView().onSettingsScreenStatusChangedFailed();
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
                getView().onSettingsScreenStatusChangedFailed();
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
                getView().onSettingsScreenStatusChangedSuccessfully();
            }
        }
    }


    /**
     * Switch Phone Calls Status Observer
     */
    public class SwitchPhoneCallsStatusObserver extends BasicCommandCallBackWrapper<String> {

        /**
         *
         */
        @Override
        protected void onNetworkError() {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPhoneCallsStatusChangedFailed();
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
                getView().onPhoneCallsStatusChangedFailed();
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
                getView().onPhoneCallsStatusChangedFailed();
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
                getView().onPhoneCallsStatusChangedSuccessfully();
            }
        }
    }


}
