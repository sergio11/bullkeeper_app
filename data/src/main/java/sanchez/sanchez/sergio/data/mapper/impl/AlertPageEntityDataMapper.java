package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertsPageDTO;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertsPageEntity;

/**
 * Alert Page Entity Data Mapper
 */
public final class AlertPageEntityDataMapper extends AbstractDataMapper<AlertsPageDTO, AlertsPageEntity> {

    /**
     * Alert Data Mapper
     */
    private final AbstractDataMapper<AlertDTO, AlertEntity> alertDataMapper;

    /**
     *
     * @param alertDataMapper
     */
    public AlertPageEntityDataMapper(AbstractDataMapper<AlertDTO, AlertEntity> alertDataMapper) {
        this.alertDataMapper = alertDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public AlertsPageEntity transform(AlertsPageDTO originModel) {
        final AlertsPageEntity alertsPageEntity = new AlertsPageEntity();
        alertsPageEntity.setAlerts(alertDataMapper.transform(originModel.getAlerts()));
        alertsPageEntity.setLastQuery(originModel.getLastQuery());
        alertsPageEntity.setRemaining(originModel.getRemaining());
        alertsPageEntity.setReturned(originModel.getReturned());
        alertsPageEntity.setTotal(originModel.getTotal());
        return alertsPageEntity;
    }
}
