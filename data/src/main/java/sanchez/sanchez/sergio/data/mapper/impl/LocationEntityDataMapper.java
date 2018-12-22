package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.LocationDTO;
import sanchez.sanchez.sergio.domain.models.LocationEntity;

/**
 * Location Entity Data Mapper
 */
public final class LocationEntityDataMapper extends AbstractDataMapper<LocationDTO, LocationEntity> {

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public LocationEntity transform(LocationDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final LocationEntity locationEntity = new LocationEntity();
        locationEntity.setLat(originModel.getLat());
        locationEntity.setLog(originModel.getLog());
        locationEntity.setAddress(originModel.getAddress());
        return locationEntity;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public LocationDTO transformInverse(LocationEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLat(originModel.getLat());
        locationDTO.setLog(originModel.getLog());
        locationDTO.setAddress(originModel.getAddress());
        return locationDTO;
    }
}
