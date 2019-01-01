package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;

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
     * Switch Lock Camera Status
     * @param kid
     * @param terminal
     * @param status
     * @return
     */
    Observable<String> switchLockCameraStatus(final String kid, final String terminal, final Boolean status);

}
