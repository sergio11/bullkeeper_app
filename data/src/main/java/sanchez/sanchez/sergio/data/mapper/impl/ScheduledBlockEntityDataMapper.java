package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AppAllowedByScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.response.ScheduledBlockDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.AppAllowedByScheduledEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Scheduled Block Entity Data Mapper
 */
public final class ScheduledBlockEntityDataMapper extends AbstractDataMapper<ScheduledBlockDTO, ScheduledBlockEntity> {

    /**
     * App Utils
     */
    private final IAppUtils appUtils;

    /**
     * Api End Points Helper
     */
    private final ApiEndPointsHelper apiEndPointsHelper;

    /**
     * App Allowed Data Mapper
     */
    private final AbstractDataMapper<AppAllowedByScheduledDTO, AppAllowedByScheduledEntity> appAllowedByScheduledMapper;

    /**
     *
     * @param apiEndPointsHelper
     * @param appUtils
     * @param appAllowedByScheduledMapper
     */
    public ScheduledBlockEntityDataMapper(final ApiEndPointsHelper apiEndPointsHelper,
                                          final IAppUtils appUtils,
                                          final AbstractDataMapper<AppAllowedByScheduledDTO, AppAllowedByScheduledEntity> appAllowedByScheduledMapper) {
        this.apiEndPointsHelper = apiEndPointsHelper;
        this.appUtils = appUtils;
        this.appAllowedByScheduledMapper = appAllowedByScheduledMapper;
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
                apiEndPointsHelper.getScheduledBlockImageUrl(originModel.getKid(),
                        originModel.getIdentity(), originModel.getImage()) : null);
        scheduledBlockEntity.setChildId(originModel.getKid());
        scheduledBlockEntity.setAppsAllowed(appAllowedByScheduledMapper
                .transform(originModel.getAppsAllowed()));
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
        scheduledBlockDTO.setKid(originModel.getChildId());
        scheduledBlockDTO.setAppsAllowed(appAllowedByScheduledMapper
                .transformInverse(originModel.getAppsAllowed()));
        return scheduledBlockDTO;
    }
}
