package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Son Entity Data Mapper
 */
public final class SonEntityDataMapper extends AbstractDataMapper<SonDTO, SonEntity> {

    private final AbstractDataMapper<SchoolDTO, SchoolEntity> schoolDataMapper;
    private final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalsDataMapper;
    private final ApiEndPointsHelper apiEndPointsHelper;
    private final IAppUtils appUtils;

    /**
     * @param schoolDataMapper
     * @param terminalsDataMapper
     * @param apiEndPointsHelper
     *
     */
    public SonEntityDataMapper(AbstractDataMapper<SchoolDTO, SchoolEntity> schoolDataMapper,
                               final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalsDataMapper,
                               ApiEndPointsHelper apiEndPointsHelper, final IAppUtils appUtils) {
        this.schoolDataMapper = schoolDataMapper;
        this.terminalsDataMapper = terminalsDataMapper;
        this.apiEndPointsHelper = apiEndPointsHelper;
        this.appUtils = appUtils;
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
        sonEntity.setProfileImage(appUtils.isValidString(originModel.getProfileImage()) ?
                apiEndPointsHelper.getSonProfileUrl(originModel.getProfileImage()) : null );
        sonEntity.setSchool(schoolDataMapper.transform(originModel.getSchoolDTO()));
        sonEntity.setAlertStatistics(originModel.getAlertStatistics());
        sonEntity.setTerminalEntities(terminalsDataMapper.transform(originModel.getTerminalDTOList()));
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
        sonDTO.setTerminalDTOList(terminalsDataMapper.transformInverse(originModel.getTerminalEntities()));
        return sonDTO;
    }
}
