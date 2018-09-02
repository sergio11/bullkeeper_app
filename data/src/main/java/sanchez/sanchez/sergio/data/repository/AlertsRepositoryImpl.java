package sanchez.sanchez.sergio.data.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.services.IAlertService;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;

/**
 * Alerts Repository Impl
 */
public final class AlertsRepositoryImpl implements IAlertsRepository {

    private final IAlertService alertService;
    private final AbstractDataMapper<AlertDTO, AlertEntity> alertDataMapper;

    /**
     *
     * @param alertService
     * @param alertDataMapper
     */
    public AlertsRepositoryImpl(IAlertService alertService, AbstractDataMapper<AlertDTO, AlertEntity> alertDataMapper) {
        this.alertService = alertService;
        this.alertDataMapper = alertDataMapper;
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
}
