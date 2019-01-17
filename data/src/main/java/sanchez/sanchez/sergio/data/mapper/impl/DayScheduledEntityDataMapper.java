package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.DayScheduledDTO;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;

/**
 * Day Scheduled Entity Data Mapper
 */
public final class DayScheduledEntityDataMapper
        extends AbstractDataMapper<DayScheduledDTO, DayScheduledEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public DayScheduledEntity transform(final DayScheduledDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final DayScheduledEntity dayScheduledEntity = new DayScheduledEntity();
        dayScheduledEntity.setDay(originModel.getDay());
        dayScheduledEntity.setEnabled(originModel.getEnabled());
        dayScheduledEntity.setTotalHours(originModel.getTotalHours());
        return dayScheduledEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public DayScheduledDTO transformInverse(final DayScheduledEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final DayScheduledDTO dayScheduledDTO = new DayScheduledDTO();
        dayScheduledDTO.setDay(originModel.getDay());
        dayScheduledDTO.setEnabled(originModel.getEnabled());
        dayScheduledDTO.setTotalHours(originModel.getTotalHours());
        return dayScheduledDTO;
    }
}
