package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;

/**
 * School Entity Data Mapper
 */
public final class SchoolEntityDataMapper extends AbstractDataMapper<SchoolDTO, SchoolEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
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

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public SchoolDTO transformInverse(SchoolEntity originModel) {
        final SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setIdentity(originModel.getIdentity());
        schoolDTO.setEmail(originModel.getEmail());
        schoolDTO.setLatitude(originModel.getLatitude());
        schoolDTO.setLongitude(originModel.getLongitude());
        schoolDTO.setEmail(originModel.getEmail());
        schoolDTO.setName(originModel.getName());
        schoolDTO.setProvince(originModel.getProvince());
        schoolDTO.setResidence(originModel.getResidence());
        schoolDTO.setTfno(originModel.getTfno());
        return schoolDTO;
    }
}
