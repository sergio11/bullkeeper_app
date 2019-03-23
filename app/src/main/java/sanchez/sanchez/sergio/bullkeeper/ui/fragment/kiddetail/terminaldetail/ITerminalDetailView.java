package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminaldetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import sanchez.sanchez.sergio.domain.models.TerminalHeartbeatEntity;

/**
 * Terminal Detail View
 */
public interface ITerminalDetailView extends ISupportView {

    /**
     * On Terminal Detail Loaded
     * @param terminalDetailEntity
     */
    void onTerminalDetailLoaded(final TerminalDetailEntity terminalDetailEntity);

    /**
     * On Terminal Success Deleted
     */
    void onTerminalSuccessDeleted();

    /**
     * On Bed Time Status Changed Successfully
     */
    void onBedTimeStatusChangedSuccessfully();

    /**
     * On Bed Time Status Changed Failed
     */
    void onBedTimeStatusChangedFailed();

    /**
     * On Lock Screen Status Changed Successfully
     */
    void onLockScreenStatusChangedSuccessfully();

    /**
     * On Lock Screen Status Changed Failed
     */
    void onLockScreenStatusChangedFailed();

    /**
     * On Lock Camera Status Changed Successfully
     */
    void onLockCameraStatusChangedSuccessfully();

    /**
     * On Lock Screen Status Changed Failed
     */
    void onLockCameraStatusChangedFailed();

    /**
     * On Settings Screen Status Changed Successfully
     */
    void onSettingsScreenStatusChangedSuccessfully();

    /**
     * On Settings Screen Status Changed Failed
     */
    void onSettingsScreenStatusChangedFailed();

    /**
     * On Phone Calls Status Changed Successfully
     */
    void onPhoneCallsStatusChangedSuccessfully();

    /**
     * On Phone Calls Status Changed Failed
     */
    void onPhoneCallsStatusChangedFailed();

    /**
     * On Heart Beat Saved Successfully
     */
    void onHeartBeatSavedSuccessfully(final TerminalHeartbeatEntity terminalHeartbeatEntity);

    /**
     * On Heart Beat Saved Failed
     */
    void onHeartBeatSavedFailed();


}
