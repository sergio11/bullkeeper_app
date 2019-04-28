package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AppAllowedByScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.response.GeofenceDTO;
import sanchez.sanchez.sergio.data.net.models.response.ScheduledBlockDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.AppAllowedByScheduledEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
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
     * Geofence Data Mapper
     */
    private final AbstractDataMapper<GeofenceDTO, GeofenceEntity> geofenceEntityAbstractDataMapper;

    /**
     * @param apiEndPointsHelper
     * @param appUtils
     * @param appAllowedByScheduledMapper
     * @param geofenceEntityAbstractDataMapper
     */
    public ScheduledBlockEntityDataMapper(final ApiEndPointsHelper apiEndPointsHelper,
                                          final IAppUtils appUtils,
                                          final AbstractDataMapper<AppAllowedByScheduledDTO, AppAllowedByScheduledEntity> appAllowedByScheduledMapper,
                                          final AbstractDataMapper<GeofenceDTO, GeofenceEntity> geofenceEntityAbstractDataMapper) {
        this.apiEndPointsHelper = apiEndPointsHelper;
        this.appUtils = appUtils;
        this.appAllowedByScheduledMapper = appAllowedByScheduledMapper;
        this.geofenceEntityAbstractDataMapper = geofenceEntityAbstractDataMapper;
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
        scheduledBlockEntity.setDescription(originModel.getDescription());
        scheduledBlockEntity.setRepeatable(originModel.isRepeatable());
        scheduledBlockEntity.setWeeklyFrequency(originModel.getWeeklyFrequency());
        scheduledBlockEntity.setAllowCalls(originModel.isAllowCalls());
        scheduledBlockEntity.setImage(appUtils.isValidString(originModel.getImage()) ?
                apiEndPointsHelper.getScheduledBlockImageUrl(originModel.getKid(),
                        originModel.getIdentity(), originModel.getImage()) : null);
        scheduledBlockEntity.setChildId(originModel.getKid());
        scheduledBlockEntity.setAppsAllowed(appAllowedByScheduledMapper
                .transform(originModel.getAppsAllowed()));

        if(originModel.getGeofence() != null)
            scheduledBlockEntity.setGeofence(
                    geofenceEntityAbstractDataMapper.transform(originModel.getGeofence()));

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
        scheduledBlockDTO.setDescription(originModel.getDescription());
        scheduledBlockDTO.setRepeatable(originModel.isRepeatable());
        scheduledBlockDTO.setWeeklyFrequency(originModel.getWeeklyFrequency());
        scheduledBlockDTO.setStartAt(originModel.getStartAt());
        scheduledBlockDTO.setEndAt(originModel.getEndAt());
        scheduledBlockDTO.setKid(originModel.getChildId());
        scheduledBlockDTO.setAppsAllowed(appAllowedByScheduledMapper
                .transformInverse(originModel.getAppsAllowed()));
        scheduledBlockDTO.setAllowCalls(originModel.isAllowCalls());
        if(originModel.getGeofence() != null)
            scheduledBlockDTO.setGeofence(geofenceEntityAbstractDataMapper
                    .transformInverse(originModel.getGeofence()));
        return scheduledBlockDTO;
    }
}
