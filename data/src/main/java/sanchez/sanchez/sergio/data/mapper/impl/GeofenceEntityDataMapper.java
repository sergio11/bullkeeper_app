package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.GeofenceDTO;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceTransitionTypeEnum;

/**
 * Geofence Entity Data Mapper
 */
public final class GeofenceEntityDataMapper extends AbstractDataMapper<GeofenceDTO, GeofenceEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public GeofenceEntity transform(GeofenceDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final GeofenceEntity geofenceEntity = new GeofenceEntity();
        geofenceEntity.setIdentity(originModel.getIdentity());
        geofenceEntity.setKid(originModel.getKid());
        geofenceEntity.setLat(originModel.getLat());
        geofenceEntity.setLog(originModel.getLog());
        geofenceEntity.setName(originModel.getName());
        geofenceEntity.setAddress(originModel.getAddress());
        geofenceEntity.setRadius(originModel.getRadius());
        try {
            geofenceEntity.setType(GeofenceTransitionTypeEnum.valueOf(originModel.getType()));
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return geofenceEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public GeofenceDTO transformInverse(GeofenceEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final GeofenceDTO geofenceDTO = new GeofenceDTO();
        geofenceDTO.setIdentity(originModel.getIdentity());
        geofenceDTO.setKid(originModel.getKid());
        geofenceDTO.setLat(originModel.getLat());
        geofenceDTO.setLog(originModel.getLog());
        geofenceDTO.setName(originModel.getName());
        geofenceDTO.setAddress(originModel.getAddress());
        geofenceDTO.setRadius(originModel.getRadius());
        geofenceDTO.setType(originModel.getType().name());
        return geofenceDTO;
    }
}
