package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ScheduledBlockDTO;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
        scheduledBlockEntity.setStartAt(LocalTime.parse(originModel.getStartAt()));
        scheduledBlockEntity.setEndAt(LocalTime.parse(originModel.getEndAt()));
        scheduledBlockEntity.setName(originModel.getName());
        scheduledBlockEntity.setImage(originModel.getImage());
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
        scheduledBlockDTO.setImage(originModel.getImage());
        scheduledBlockDTO.setRepeatable(originModel.isRepeatable());
        scheduledBlockDTO.setWeeklyFrequency(originModel.getWeeklyFrequency());
        final DateTimeFormatter fmt = DateTimeFormat.forPattern("hh:mm a");
        scheduledBlockDTO.setStartAt(originModel.getStartAt().toString(fmt));
        scheduledBlockDTO.setEndAt(originModel.getEndAt().toString(fmt));
        return scheduledBlockDTO;
    }
}
