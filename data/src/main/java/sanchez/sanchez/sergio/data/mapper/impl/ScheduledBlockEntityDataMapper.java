package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ScheduledBlockDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.data.utils.AppUtils;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Scheduled Block Entity Data Mapper
 */
public final class ScheduledBlockEntityDataMapper extends AbstractDataMapper<ScheduledBlockDTO, ScheduledBlockEntity> {

    private final IAppUtils appUtils;
    private final ApiEndPointsHelper apiEndPointsHelper;

    /**
     *
     * @param apiEndPointsHelper
     * @param appUtils
     */
    public ScheduledBlockEntityDataMapper(final ApiEndPointsHelper apiEndPointsHelper,
                                          final IAppUtils appUtils) {
        this.apiEndPointsHelper = apiEndPointsHelper;
        this.appUtils = appUtils;
    }

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
        scheduledBlockEntity.setImage(appUtils.isValidString(originModel.getImage()) ?
                apiEndPointsHelper.getScheduledBlockImageUrl(originModel.getChild(),
                        originModel.getIdentity(), originModel.getImage()) : null);
        scheduledBlockEntity.setChildId(originModel.getChild());
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
        scheduledBlockDTO.setChild(originModel.getChildId());
        return scheduledBlockDTO;
    }
}
