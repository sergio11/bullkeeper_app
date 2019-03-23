package sanchez.sanchez.sergio.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import sanchez.sanchez.sergio.domain.models.TerminalHeartbeatEntity;

/**
 * Terminal Repository
 */
public interface ITerminalRepository {

    /**
     * Get Monitored Terminals
     * @param childId
     * @return
     */
    Observable<List<TerminalEntity>> getMonitoredTerminals(final String childId);


    /**
     * Get terminal Detail
     * @param childId
     * @param terminalId
     * @return
     */
    Observable<TerminalDetailEntity> getTerminalDetail(final String childId, final String terminalId);

    /**
     * Delete Terminal
     * @param childId
     * @param terminalId
     * @return
     */
    Observable<String> deleteTerminal(final String childId, final String terminalId);


    /**
     * Delete All Terminal for kid
     * @param kid
     * @return
     */
    Observable<String> delete(final String kid);

    /**
     * Switch Bed Time Status
     * @param kid
     * @param terminal
     * @param status
     * @return
     */
    Observable<String> switchBedTimeStatus(final String kid, final String terminal, final Boolean status);

    /**
     * Switch Lock Screen Status
     * @param kid
     * @param terminal
     * @param status
     * @return
     */
    Observable<String> switchLockScreenStatus(final String kid, final String terminal, final Boolean status);

    /**
     * Switch Lock Screen Status For All Kid Terminal
     * @param kid
     * @param status
     * @return
     */
    Observable<String> switchLockScreenStatus(final String kid, final Boolean status);


    /**
     * Switch Lock Camera Status
     * @param kid
     * @param terminal
     * @param status
     * @return
     */
    Observable<String> switchLockCameraStatus(final String kid, final String terminal, final Boolean status);

    /**
     * Switch Settings Status
     * @param kid
     * @param terminal
     * @param status
     * @return
     */
    Observable<String> switchSettingsScreenStatus(final String kid, final String terminal, final Boolean status);


    /**
     * Switch Terminal Phone Calls Status
     * @param kid
     * @param terminal
     * @param status
     * @return
     */
    Observable<String> switchTerminalPhoneCallsStatus(final String kid, final String terminal, final Boolean status);

    /**
     * Save Heart Beat Configuration
     * @param kid
     * @param terminal
     * @param alertThresholdInMinutes
     * @param isAlertModeEnabled
     * @return
     */
    Observable<TerminalHeartbeatEntity> saveHeartbeatConfiguration(final String kid, final String terminal,
                                                                   final int alertThresholdInMinutes, final boolean isAlertModeEnabled);

}
