package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveDayScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveFunTimeScheduledDTO;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;
import sanchez.sanchez.sergio.domain.models.FunTimeScheduledEntity;

/**
 * Save Fun Time Scheduled Data Mapper
 */
public final class SaveFunTimeScheduledDataMapper
    extends AbstractDataMapper<FunTimeScheduledEntity, SaveFunTimeScheduledDTO> {

    /**
     * Day Scheduled Data Mapper
     */
    private final AbstractDataMapper<DayScheduledEntity, SaveDayScheduledDTO>
            dayScheduledDTOAbstractDataMapper;

    /**
     *
     * @param dayScheduledDTOAbstractDataMapper
     */
    public SaveFunTimeScheduledDataMapper(final AbstractDataMapper<DayScheduledEntity, SaveDayScheduledDTO> dayScheduledDTOAbstractDataMapper) {
        this.dayScheduledDTOAbstractDataMapper = dayScheduledDTOAbstractDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public SaveFunTimeScheduledDTO transform(final FunTimeScheduledEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final SaveFunTimeScheduledDTO saveFunTimeScheduledDTO = new SaveFunTimeScheduledDTO();
        saveFunTimeScheduledDTO.setEnabled(originModel.getEnabled());
        saveFunTimeScheduledDTO.setMonday(dayScheduledDTOAbstractDataMapper.transform(originModel.getMonday()));
        saveFunTimeScheduledDTO.setTuesday(dayScheduledDTOAbstractDataMapper.transform(originModel.getTuesday()));
        saveFunTimeScheduledDTO.setWednesday(dayScheduledDTOAbstractDataMapper.transform(originModel.getWednesday()));
        saveFunTimeScheduledDTO.setThursday(dayScheduledDTOAbstractDataMapper.transform(originModel.getThursday()));
        saveFunTimeScheduledDTO.setFriday(dayScheduledDTOAbstractDataMapper.transform(originModel.getFriday()));
        saveFunTimeScheduledDTO.setSaturday(dayScheduledDTOAbstractDataMapper.transform(originModel.getSaturday()));
        saveFunTimeScheduledDTO.setSunday(dayScheduledDTOAbstractDataMapper.transform(originModel.getSunday()));
        return saveFunTimeScheduledDTO;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public FunTimeScheduledEntity transformInverse(SaveFunTimeScheduledDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final FunTimeScheduledEntity funTimeScheduledEntity = new FunTimeScheduledEntity();
        funTimeScheduledEntity.setEnabled(originModel.getEnabled());
        funTimeScheduledEntity.setMonday(dayScheduledDTOAbstractDataMapper
                .transformInverse(originModel.getMonday()));
        funTimeScheduledEntity.setThursday(dayScheduledDTOAbstractDataMapper
                .transformInverse(originModel.getThursday()));
        funTimeScheduledEntity.setWednesday(dayScheduledDTOAbstractDataMapper
                .transformInverse(originModel.getWednesday()));
        funTimeScheduledEntity.setTuesday(dayScheduledDTOAbstractDataMapper
                .transformInverse(originModel.getTuesday()));
        funTimeScheduledEntity.setFriday(dayScheduledDTOAbstractDataMapper
                .transformInverse(originModel.getFriday()));

        return funTimeScheduledEntity;
    }
}
