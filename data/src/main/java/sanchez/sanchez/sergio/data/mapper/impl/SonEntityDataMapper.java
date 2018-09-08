package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Son Entity Data Mapper
 */
public final class SonEntityDataMapper extends AbstractDataMapper<SonDTO, SonEntity> {

    private final AbstractDataMapper<SchoolDTO, SchoolEntity> schoolDataMapper;
    private final ApiEndPointsHelper apiEndPointsHelper;

    /**
     *
     * @param schoolDataMapper
     * @param apiEndPointsHelper
     */
    public SonEntityDataMapper(AbstractDataMapper<SchoolDTO, SchoolEntity> schoolDataMapper,
                               ApiEndPointsHelper apiEndPointsHelper) {
        this.schoolDataMapper = schoolDataMapper;
        this.apiEndPointsHelper = apiEndPointsHelper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public SonEntity transform(SonDTO originModel) {
        final SonEntity sonEntity = new SonEntity();
        sonEntity.setIdentity(originModel.getIdentity());
        sonEntity.setAge(originModel.getAge());
        sonEntity.setFirstName(originModel.getFirstName());
        sonEntity.setLastName(originModel.getLastName());
        sonEntity.setBirthdate(originModel.getBirthdate());
        sonEntity.setProfileImage(apiEndPointsHelper.getSonProfileUrl(originModel.getProfileImage()));
        sonEntity.setSchool(schoolDataMapper.transform(originModel.getSchoolDTO()));
        sonEntity.setAlertStatistics(originModel.getAlertStatistics());
        return sonEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public SonDTO transformInverse(SonEntity originModel) {
        final SonDTO sonDTO = new SonDTO();
        sonDTO.setIdentity(originModel.getIdentity());
        sonDTO.setAge(originModel.getAge());
        sonDTO.setBirthdate(originModel.getBirthdate());
        sonDTO.setFirstName(originModel.getFirstName());
        sonDTO.setLastName(originModel.getLastName());
        sonDTO.setProfileImage(originModel.getProfileImage());
        sonDTO.setSchoolDTO(schoolDataMapper.transformInverse(originModel.getSchool()));
        sonDTO.setAlertStatistics(originModel.getAlertStatistics());
        return sonDTO;
    }
}
