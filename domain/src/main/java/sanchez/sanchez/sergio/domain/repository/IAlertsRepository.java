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
}
