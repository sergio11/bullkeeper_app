package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertsPageDTO;
import sanchez.sanchez.sergio.data.net.services.IAlertService;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertsPageEntity;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;

/**
 * Alerts Repository Impl
 */
public final class AlertsRepositoryImpl implements IAlertsRepository {

    private final IAlertService alertService;
    private final AbstractDataMapper<AlertDTO, AlertEntity> alertDataMapper;
    private final AbstractDataMapper<AlertsPageDTO, AlertsPageEntity> alertsPageEntityDataMapper;

    /**
     *
     * @param alertService
     * @param alertDataMapper
     * @param alertsPageEntityDataMapper
     */
    public AlertsRepositoryImpl(IAlertService alertService,
                                AbstractDataMapper<AlertDTO, AlertEntity> alertDataMapper,
                                final AbstractDataMapper<AlertsPageDTO, AlertsPageEntity> alertsPageEntityDataMapper) {
        this.alertService = alertService;
        this.alertDataMapper = alertDataMapper;
        this.alertsPageEntityDataMapper = alertsPageEntityDataMapper;
    }

    /**
     * Get Self Alerts
     * @return
     */
    @Override
    public Observable<List<AlertEntity>> getSelfAlerts() {
        return alertService.getSelfAlerts().map(listAPIResponse ->
        listAPIResponse != null && listAPIResponse.getData() != null ? listAPIResponse.getData() : null)
                .map(alertDataMapper::transform);
    }

    /**
     * Get Self Alerts
     * @param count
     * @param daysAgo
     * @param levelsCsv
     * @return
     */
    @Override
    public Observable<List<AlertEntity>> getSelfAlerts(final String count, final String daysAgo, final String levelsCsv) {

        Observable<List<AlertEntity>> observable;

        try {
            observable = alertService.getSelfAlerts(count, daysAgo, URLDecoder.decode(levelsCsv, "UTF-8")).map(listAPIResponse ->
                    listAPIResponse != null && listAPIResponse.getData() != null ? listAPIResponse.getData() : null)
                    .map(alertDataMapper::transform);
        } catch (UnsupportedEncodingException e) {
            observable = Observable.empty();
        }

        return observable;
    }

    /**
     * Delete Alert Of Son
     * @param son
     * @param alert
     * @return
     */
    @Override
    public Observable<String> deleteAlertOfSon(final String son, final String alert) {
        return alertService.deleteAlertOfSon(son, alert).map(response ->
                response != null && response.getData() != null ? response.getData() : null);
    }

    /**
     * Clear Self Alerts
     * @return
     */
    @Override
    public Observable<String> clearSelfAlerts() {
        return alertService.clearSelfAlerts().map(response ->
            response != null && response.getData() != null ? response.getData() : null);
    }

    /**
     * Get Alerts By Son
     * @param sonIdentity
     * @return
     */
    @Override
    public Observable<List<AlertEntity>> getAlertsBySon(final String sonIdentity) {
        Preconditions.checkNotNull(sonIdentity, "Son Identity can not be null");
        return alertService.getAlertsBySon(sonIdentity).map(response ->
        response != null && response.getData() != null ? response.getData() : null)
                .map(alertDataMapper::transform);
    }

    /**
     * Clear Alerts Of Son
     * @param sonIdentity
     * @return
     */
    @Override
    public Observable<String> clearAlertsOfSon(final String sonIdentity) {
        Preconditions.checkNotNull(sonIdentity, "Identity can not be null");
        Preconditions.checkState(!sonIdentity.isEmpty(), "Identity can not be empty");

        return alertService.clearAlertsOfSon(sonIdentity).map(response ->
            response != null && response.getData() != null ? response.getData() : null);
    }

    /**
     * Get Alert By Id
     * @param sonId
     * @param alertId
     * @return
     */
    @Override
    public Observable<AlertEntity> getAlertById(String sonId, String alertId) {
        Preconditions.checkNotNull(sonId, "Son Id can not be null");
        Preconditions.checkState(!sonId.isEmpty(), "Son id can no be empty");
        Preconditions.checkNotNull(alertId, "Son Id can not be null");
        Preconditions.checkState(!alertId.isEmpty(), "Son id can no be empty");
        return alertService.getAlertById(sonId, alertId).map(response ->
            response != null && response.getData() != null ? response.getData() : null)
                .map(alertDataMapper::transform);
    }

    /**
     * Get Self Alerts Last
     * @return
     */
    @Override
    public Observable<AlertsPageEntity> getSelfAlertsLast() {
        return alertService.getSelfAlertsLast().map(response ->
                response != null && response.getData() != null ? response.getData() : null)
                .map(alertsPageEntityDataMapper::transform);
    }

    /**
     * Get Self Alerts Last
     * @param count
     * @param lastMinutes
     * @param levels
     * @return
     */
    @Override
    public Observable<AlertsPageEntity> getSelfAlertsLast(String count, String lastMinutes, String levels) {
        Preconditions.checkNotNull(count, "Count can not be null");
        Preconditions.checkState(!count.isEmpty(), "Count can not be empty");
        Preconditions.checkNotNull(lastMinutes, "Last Minutes can not be null");
        Preconditions.checkState(!lastMinutes.isEmpty(), "Last Minutes can not be empty");
        Preconditions.checkNotNull(levels, "Levels can not be null");
        Preconditions.checkState(!levels.isEmpty(), "Levels can not be empty");
        return alertService.getSelfAlertsLast(count, lastMinutes, levels).map(response ->
                response != null && response.getData() != null ? response.getData() : null)
                .map(alertsPageEntityDataMapper::transform);
    }


}
