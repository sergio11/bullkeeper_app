package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.DayScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.response.FunTimeScheduledDTO;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;
import sanchez.sanchez.sergio.domain.models.FunTimeScheduledEntity;

/**
 * Fun Time Entity Data Mapper
 */
public final class FunTimeEntityDataMapper
        extends AbstractDataMapper<FunTimeScheduledDTO, FunTimeScheduledEntity> {


    /**
     * Day Scheduled Entity Data Mapper
     */
    private final AbstractDataMapper<DayScheduledDTO, DayScheduledEntity> dayScheduledEntityAbstractDataMapper;

    /**
     *
     * @param dayScheduledEntityAbstractDataMapper
     */
    public FunTimeEntityDataMapper(final AbstractDataMapper<DayScheduledDTO, DayScheduledEntity> dayScheduledEntityAbstractDataMapper) {
        this.dayScheduledEntityAbstractDataMapper = dayScheduledEntityAbstractDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public FunTimeScheduledEntity transform(final FunTimeScheduledDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final FunTimeScheduledEntity funTimeScheduledEntity = new FunTimeScheduledEntity();
        funTimeScheduledEntity.setEnabled(originModel.getEnabled());
        funTimeScheduledEntity.setMonday(dayScheduledEntityAbstractDataMapper
                .transform(originModel.getMonday()));
        funTimeScheduledEntity.setTuesday(dayScheduledEntityAbstractDataMapper
                .transform(originModel.getTuesday()));
        funTimeScheduledEntity.setWednesday(dayScheduledEntityAbstractDataMapper
                .transform(originModel.getWednesday()));
        funTimeScheduledEntity.setThursday(dayScheduledEntityAbstractDataMapper
                .transform(originModel.getThursday()));
        funTimeScheduledEntity.setFriday(dayScheduledEntityAbstractDataMapper
                .transform(originModel.getFriday()));
        funTimeScheduledEntity.setSaturday(dayScheduledEntityAbstractDataMapper
                .transform(originModel.getSaturday()));
        funTimeScheduledEntity.setSunday(dayScheduledEntityAbstractDataMapper
                .transform(originModel.getSunday()));
        return funTimeScheduledEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public FunTimeScheduledDTO transformInverse(final FunTimeScheduledEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final FunTimeScheduledDTO funTimeScheduledDTO = new FunTimeScheduledDTO();
        funTimeScheduledDTO.setEnabled(originModel.getEnabled());
        funTimeScheduledDTO.setMonday(dayScheduledEntityAbstractDataMapper
                .transformInverse(originModel.getMonday()));
        funTimeScheduledDTO.setTuesday(dayScheduledEntityAbstractDataMapper
                .transformInverse(originModel.getTuesday()));
        funTimeScheduledDTO.setWednesday(dayScheduledEntityAbstractDataMapper
                .transformInverse(originModel.getWednesday()));
        funTimeScheduledDTO.setThursday(dayScheduledEntityAbstractDataMapper
                .transformInverse(originModel.getThursday()));
        funTimeScheduledDTO.setFriday(dayScheduledEntityAbstractDataMapper
                .transformInverse(originModel.getFriday()));
        funTimeScheduledDTO.setSaturday(dayScheduledEntityAbstractDataMapper
                .transformInverse(originModel.getSaturday()));
        funTimeScheduledDTO.setSunday(dayScheduledEntityAbstractDataMapper
                .transformInverse(originModel.getSunday()));
        return funTimeScheduledDTO;

    }
}
