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

        // Monday
        final DayScheduledEntity mondayDay = dayScheduledEntityAbstractDataMapper
                .transform(originModel.getMonday());
        mondayDay.setEditable(originModel.getEnabled());
        funTimeScheduledEntity.setMonday(mondayDay);

        // Tuesday
        final DayScheduledEntity tuesdayDay = dayScheduledEntityAbstractDataMapper
                .transform(originModel.getTuesday());
        tuesdayDay.setEditable(originModel.getEnabled());
        funTimeScheduledEntity.setTuesday(tuesdayDay);

        // Wednesday
        final DayScheduledEntity wednesdayDay = dayScheduledEntityAbstractDataMapper
                .transform(originModel.getWednesday());
        wednesdayDay.setEditable(originModel.getEnabled());
        funTimeScheduledEntity.setWednesday(wednesdayDay);

        // Thursday
        final DayScheduledEntity thursdayDay = dayScheduledEntityAbstractDataMapper
                .transform(originModel.getThursday());
        thursdayDay.setEditable(originModel.getEnabled());
        funTimeScheduledEntity.setThursday(thursdayDay);

        // Friday
        final DayScheduledEntity fridayDay = dayScheduledEntityAbstractDataMapper
                .transform(originModel.getFriday());
        fridayDay.setEditable(originModel.getEnabled());
        funTimeScheduledEntity.setFriday(fridayDay);

        // Saturday
        final DayScheduledEntity saturdayDay = dayScheduledEntityAbstractDataMapper
                .transform(originModel.getSaturday());
        saturdayDay.setEditable(originModel.getEnabled());
        funTimeScheduledEntity.setSaturday(saturdayDay);

        // Sunday
        final DayScheduledEntity sundayDay = dayScheduledEntityAbstractDataMapper
                .transform(originModel.getSunday());
        sundayDay.setEditable(originModel.getEnabled());
        funTimeScheduledEntity.setSunday(sundayDay);

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
