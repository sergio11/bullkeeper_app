package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveScheduledBlockStatusDTO;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockStatusEntity;

/**
 * Scheduled Block Status Entity Data Mapper
 */
public final class ScheduledBlockStatusEntityDataMapper extends AbstractDataMapper<SaveScheduledBlockStatusDTO,
        ScheduledBlockStatusEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public ScheduledBlockStatusEntity transform(final SaveScheduledBlockStatusDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final ScheduledBlockStatusEntity scheduledBlockStatusEntity = new ScheduledBlockStatusEntity();
        scheduledBlockStatusEntity.setEnable(originModel.isEnable());
        scheduledBlockStatusEntity.setIdentity(originModel.getIdentity());
        return scheduledBlockStatusEntity;

    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public SaveScheduledBlockStatusDTO transformInverse(final ScheduledBlockStatusEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final SaveScheduledBlockStatusDTO saveScheduledBlockStatusDTO = new SaveScheduledBlockStatusDTO();
        saveScheduledBlockStatusDTO.setIdentity(originModel.getIdentity());
        saveScheduledBlockStatusDTO.setEnable(originModel.isEnable());
        return saveScheduledBlockStatusDTO;
    }
}
