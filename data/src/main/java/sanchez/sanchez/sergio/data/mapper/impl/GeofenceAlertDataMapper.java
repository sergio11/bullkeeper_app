package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.GeofenceAlertDTO;
import sanchez.sanchez.sergio.domain.models.GeofenceAlertEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceTransitionTypeEnum;

/**
 * Geofence Alert Data Mapper
 */
public final class GeofenceAlertDataMapper
        extends AbstractDataMapper<GeofenceAlertDTO, GeofenceAlertEntity> {

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public GeofenceAlertEntity transform(final GeofenceAlertDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final GeofenceAlertEntity geofenceAlertEntity = new GeofenceAlertEntity();
        geofenceAlertEntity.setTitle(originModel.getTitle());
        geofenceAlertEntity.setMessage(originModel.getMessage());
        geofenceAlertEntity.setDate(originModel.getDate());
        try {
            geofenceAlertEntity.setType(GeofenceTransitionTypeEnum.valueOf(originModel.getType()));
        } catch (final Exception ex) {
            geofenceAlertEntity.setType(GeofenceTransitionTypeEnum.TRANSITION_EXIT);
        }
        return geofenceAlertEntity;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public GeofenceAlertDTO transformInverse(GeofenceAlertEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final GeofenceAlertDTO geofenceAlertDTO = new GeofenceAlertDTO();
        geofenceAlertDTO.setTitle(originModel.getTitle());
        geofenceAlertDTO.setMessage(originModel.getMessage());
        geofenceAlertDTO.setDate(originModel.getDate());
        geofenceAlertDTO.setType(originModel.getType().name());
        return geofenceAlertDTO;
    }
}
