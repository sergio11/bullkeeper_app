package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;

/**
 * School Entity Data Mapper
 */
public final class SchoolEntityDataMapper extends AbstractDataMapper<SchoolDTO, SchoolEntity> {
    @Override
    public SchoolEntity transform(SchoolDTO originModel) {
        final SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setIdentity(originModel.getIdentity());
        schoolEntity.setEmail(originModel.getEmail());
        schoolEntity.setLatitude(originModel.getLatitude());
        schoolEntity.setLongitude(originModel.getLongitude());
        schoolEntity.setName(originModel.getName());
        schoolEntity.setProvince(originModel.getProvince());
        schoolEntity.setResidence(originModel.getResidence());
        schoolEntity.setTfno(originModel.getTfno());
        return schoolEntity;
    }
}
