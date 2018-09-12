package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
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
     * Clear Self Alerts By Level
     * @return
     */
    Observable<String> clearSelfAlertsByLevel(final AlertLevelEnum alertLevelEnum);

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
     * Clear Alerts Of Son by level
     * @param sonIdentity
     * @return
     */
    Observable<String> clearAlertsOfSonByLevel(final String sonIdentity, final AlertLevelEnum alertLevelEnum);

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

    /**
     * Get Self Alerts By level
     * @param count
     * @param lastMinutes
     * @param alertLevelEnum
     * @return
     */
    Observable<List<AlertEntity>> getSelfAlertsByLevel(final String count, final String lastMinutes,
                                                       final AlertLevelEnum alertLevelEnum);


    /**
     * Get Self Alerts of Son By level
     * @param count
     * @param lastMinutes
     * @param alertLevelEnum
     * @return
     */
    Observable<List<AlertEntity>> getSelfAlertsOfSonByLevel(final String count, final String lastMinutes,
                                                       final String sonId, final AlertLevelEnum alertLevelEnum);
}
