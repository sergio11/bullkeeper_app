package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertsPageEntity;

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
     * Get Self Alerts
     * @param count
     * @param daysAgo
     * @param levelsCsv
     * @return
     */
    Observable<List<AlertEntity>> getSelfAlerts(String count, String daysAgo, final String levelsCsv);

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

    /**
     * Get Self Alerts Last
     * @return
     */
    Observable<AlertsPageEntity> getSelfAlertsLast();

    /**
     * Get Self Alerts
     * @param count
     * @param lastMinutes
     * @param levels
     * @return
     */
    Observable<AlertsPageEntity> getSelfAlertsLast(final String count,
                                                   final String lastMinutes,
                                                   final String levels);
}
