package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.AlertEntity;

/**
 * Alerts Repository
 */
public interface IAlertsRepository {

    /**
     * Get Self Alerts
     * @return
     */
    Observable<List<AlertEntity>> getSelfAlerts();

    /**
     * Delete Alert of Son
     * @param son
     * @param alert
     * @return
     */
    Observable<String> deleteAlertOfSon(final String son, final String alert);


    /**
     * Clear Self Alerts
     * @return
     */
    Observable<String> clearSelfAlerts();

    /**
     * Get Alerts By Son
     * @param sonIdentity
     * @return
     */
    Observable<List<AlertEntity>> getAlertsBySon(final String sonIdentity);

    /**
     * Clear Alerts Of Son
     * @param sonIdentity
     * @return
     */
    Observable<String> clearAlertsOfSon(final String sonIdentity);

    /**
     * Get Alert By Id
     * @param sonId
     * @param alertId
     * @return
     */
    Observable<AlertEntity> getAlertById(final String sonId, final String alertId);
}
