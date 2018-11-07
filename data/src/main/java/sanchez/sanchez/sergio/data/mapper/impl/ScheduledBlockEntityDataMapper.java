package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ScheduledBlockDTO;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;

/**
 * Scheduled Block Entity Data Mapper
 */
public final class ScheduledBlockEntityDataMapper extends AbstractDataMapper<ScheduledBlockDTO, ScheduledBlockEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public ScheduledBlockEntity transform(final ScheduledBlockDTO originModel) {
        final ScheduledBlockEntity scheduledBlockEntity = new ScheduledBlockEntity();
        scheduledBlockEntity.setIdentity(originModel.getIdentity());
        scheduledBlockEntity.setEnable(originModel.isEnable());
        scheduledBlockEntity.setStartAt(originModel.getStartAt());
        scheduledBlockEntity.setEndAt(originModel.getEndAt());
        scheduledBlockEntity.setName(originModel.getName());
        scheduledBlockEntity.setRepeatable(originModel.isRepeatable());
        scheduledBlockEntity.setWeeklyFrequency(originModel.getWeeklyFrequency());
        return scheduledBlockEntity;

    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public ScheduledBlockDTO transformInverse(final ScheduledBlockEntity originModel) {
        final ScheduledBlockDTO scheduledBlockDTO = new ScheduledBlockDTO();
        scheduledBlockDTO.setIdentity(originModel.getIdentity());
        scheduledBlockDTO.setEnable(originModel.isEnable());
        scheduledBlockDTO.setName(originModel.getName());
        scheduledBlockDTO.setRepeatable(originModel.isRepeatable());
        scheduledBlockDTO.setWeeklyFrequency(originModel.getWeeklyFrequency());
        scheduledBlockDTO.setStartAt(originModel.getStartAt());
        scheduledBlockDTO.setEndAt(originModel.getEndAt());
        return scheduledBlockDTO;
    }
}
