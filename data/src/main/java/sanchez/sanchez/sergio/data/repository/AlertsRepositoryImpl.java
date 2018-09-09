package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertsPageDTO;
import sanchez.sanchez.sergio.data.net.services.IAlertService;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
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
     * Clear Self Alerts By Level
     * @return
     */
    @Override
    public Observable<String> clearSelfAlertsByLevel(final AlertLevelEnum alertLevelEnum) {
        Preconditions.checkNotNull(alertLevelEnum, "Alert Level enum can not be null");

        Observable<APIResponse<String>> observable;

        switch (alertLevelEnum) {
            case DANGER:
                observable = alertService.clearSelfDangerAlerts();
                break;
            case SUCCESS:
                observable = alertService.clearSelfSuccessAlerts();
                break;
            case WARNING:
                observable = alertService.clearSelfWarningAlerts();
                break;
            default:
                observable = alertService.clearSelfInformationAlerts();
        }

        return observable.map(response -> response != null
                && response.getData() != null ? response.getData() : null);
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
     * Clear Alerts Of Son By Level
     * @param sonIdentity
     * @param alertLevelEnum
     * @return
     */
    @Override
    public Observable<String> clearAlertsOfSonByLevel(final String sonIdentity, final AlertLevelEnum alertLevelEnum) {
        Preconditions.checkNotNull(sonIdentity, "Identity can not be null");
        Preconditions.checkState(!sonIdentity.isEmpty(), "Identity can not be empty");
        Preconditions.checkNotNull(alertLevelEnum, "Alert Level can not be empty");

        Observable<APIResponse<String>> observable;

        switch (alertLevelEnum) {
            case WARNING:
                observable = alertService.clearWarningAlertsOfSonForSelfParent(sonIdentity);
                break;
            case DANGER:
                observable = alertService.clearDangerAlertsOfSonForSelfParent(sonIdentity);
                break;
            case SUCCESS:
                observable = alertService.clearSuccessAlertsOfSonForSelfParent(sonIdentity);
                break;
                default:
                    observable = alertService.clearInfoAlertsOfSonForSelfParent(sonIdentity);

        }

        return observable.map(response -> response != null
                && response.getData() != null ? response.getData(): null);


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

    /**
     * Get Self Alerts By Level
     * @param count
     * @param lastMinutes
     * @param alertLevelEnum
     * @return
     */
    @Override
    public Observable<List<AlertEntity>> getSelfAlertsByLevel(String count, String lastMinutes, AlertLevelEnum alertLevelEnum) {
        Preconditions.checkNotNull(count, "Count can not be null");
        Preconditions.checkState(!count.isEmpty(), "Count can not be empty");
        Preconditions.checkNotNull(lastMinutes, "Last Minutes can not be null");
        Preconditions.checkState(!lastMinutes.isEmpty(), "Last Minutes can not be empty");
        Preconditions.checkNotNull(alertLevelEnum, "Alert level enum can not be null");

        Observable<APIResponse<List<AlertDTO>>> observable;

        switch (alertLevelEnum) {
            case WARNING:
                observable = alertService.getWarningAlertsForSelfParent(count, lastMinutes);
                break;
            case SUCCESS:
                observable = alertService.getSuccessAlertsForSelfParent(count, lastMinutes);
                break;
            case DANGER:
                observable = alertService.getDangerAlertsForSelfParent(count, lastMinutes);
                break;
            default:
                observable = alertService.getInformationAlertsForSelfParent(count, lastMinutes);

        }

        return observable.map(response -> response != null
                && response.getData() != null ? response.getData() : null)
                .map(alertDataMapper::transform);


    }

    /**
     * Get Self Alerts Of Son By Level
     * @param count
     * @param lastMinutes
     * @param sonId
     * @param alertLevelEnum
     * @return
     */
    @Override
    public Observable<List<AlertEntity>> getSelfAlertsOfSonByLevel(String count, String lastMinutes, String sonId, AlertLevelEnum alertLevelEnum) {
        Preconditions.checkNotNull(count, "Count can not be null");
        Preconditions.checkState(!count.isEmpty(), "Count can not be empty");
        Preconditions.checkNotNull(lastMinutes, "Last Minutes can not be null");
        Preconditions.checkState(!lastMinutes.isEmpty(), "Last Minutes can not be empty");
        Preconditions.checkNotNull(sonId, "Son Id can not be null");
        Preconditions.checkState(!sonId.isEmpty(), "Son Id can not be empty");
        Preconditions.checkNotNull(alertLevelEnum, "Alert level enum can not be null");

        Observable<APIResponse<List<AlertDTO>>> observable;

        switch (alertLevelEnum) {
            case WARNING:
                observable = alertService.getWarningAlertsOfSonForSelfParent(count, lastMinutes, sonId);
                break;
            case SUCCESS:
                observable = alertService.getSuccessAlertsOfSonForSelfParent(count, lastMinutes, sonId);
                break;
            case DANGER:
                observable = alertService.getDangerAlertsOfSonForSelfParent(count, lastMinutes, sonId);
                break;
            default:
                observable = alertService.getInfoAlertsOfSonForSelfParent(count, lastMinutes, sonId);
                break;
        }

        return observable.map(response -> response != null
                && response.getData() != null ? response.getData() : null)
                .map(alertDataMapper::transform);
    }


}
