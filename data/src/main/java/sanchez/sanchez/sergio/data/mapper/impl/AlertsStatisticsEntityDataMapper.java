package sanchez.sanchez.sergio.data.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AlertsStatisticsDTO;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.AlertsStatisticsEntity;
import timber.log.Timber;

/**
 * Alerts Statitics Entity Data Mapper
 */
public final class AlertsStatisticsEntityDataMapper extends
        AbstractDataMapper<AlertsStatisticsDTO, AlertsStatisticsEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public AlertsStatisticsEntity transform(AlertsStatisticsDTO originModel) {
        final AlertsStatisticsEntity alertsStatisticsEntity = new AlertsStatisticsEntity();
        alertsStatisticsEntity.setTitle(originModel.getTitle());
        final List<AlertsStatisticsEntity.AlertLevelEntity> alertLevelEntities = new ArrayList<>();
        for(final AlertsStatisticsDTO.AlertLevelDTO alertLevelDTO: originModel.getData()) {
            final AlertsStatisticsEntity.AlertLevelEntity alertLevelEntity = new AlertsStatisticsEntity.AlertLevelEntity();
            alertLevelEntity.setLabel(alertLevelDTO.getLabel());
            alertLevelEntity.setTotal(alertLevelDTO.getTotal());
            try {
                alertLevelEntity.setLevel(AlertLevelEnum.valueOf(alertLevelDTO.getLevel()));
            } catch (IllegalArgumentException ex) {
                Timber.e("Alert Level type not valid");
                alertLevelEntity.setLevel(null);
            }
            alertLevelEntities.add(alertLevelEntity);
        }
        alertsStatisticsEntity.setData(alertLevelEntities);
        return alertsStatisticsEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public AlertsStatisticsDTO transformInverse(AlertsStatisticsEntity originModel) {
        throw new UnsupportedOperationException();
    }
}
