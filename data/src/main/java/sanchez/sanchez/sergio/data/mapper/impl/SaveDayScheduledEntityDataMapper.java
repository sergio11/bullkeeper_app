package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveDayScheduledDTO;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;

/**
 * Save Day Scheduled Entity Data Mapper
 */
public final class SaveDayScheduledEntityDataMapper extends AbstractDataMapper<DayScheduledEntity, SaveDayScheduledDTO> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public SaveDayScheduledDTO transform(final DayScheduledEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final SaveDayScheduledDTO saveDayScheduledDTO = new SaveDayScheduledDTO();
        saveDayScheduledDTO.setDay(originModel.getDay());
        saveDayScheduledDTO.setEnabled(originModel.getEnabled());
        saveDayScheduledDTO.setTotalHours(originModel.getTotalHours());
        return saveDayScheduledDTO;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public DayScheduledEntity transformInverse(SaveDayScheduledDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final DayScheduledEntity dayScheduledEntity = new DayScheduledEntity();
        dayScheduledEntity.setDay(originModel.getDay());
        dayScheduledEntity.setEnabled(originModel.getEnabled());
        dayScheduledEntity.setTotalHours(originModel.getTotalHours());
        return dayScheduledEntity;
    }
}
