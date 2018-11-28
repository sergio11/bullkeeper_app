package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Kid Entity Data Mapper
 */
public final class KidEntityDataMapper extends AbstractDataMapper<KidDTO, KidEntity> {

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
    public KidEntityDataMapper(AbstractDataMapper<SchoolDTO, SchoolEntity> schoolDataMapper,
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
    public KidEntity transform(KidDTO originModel) {
        final KidEntity kidEntity = new KidEntity();
        kidEntity.setIdentity(originModel.getIdentity());
        kidEntity.setAge(originModel.getAge());
        kidEntity.setFirstName(originModel.getFirstName());
        kidEntity.setLastName(originModel.getLastName());
        kidEntity.setBirthdate(originModel.getBirthdate());
        kidEntity.setProfileImage(appUtils.isValidString(originModel.getProfileImage()) ?
                apiEndPointsHelper.getKidProfileUrl(originModel.getProfileImage()) : null );
        kidEntity.setSchool(schoolDataMapper.transform(originModel.getSchoolDTO()));
        kidEntity.setAlertStatistics(originModel.getAlertStatistics());
        kidEntity.setTerminalEntities(terminalsDataMapper.transform(originModel.getTerminalDTOList()));
        return kidEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public KidDTO transformInverse(KidEntity originModel) {
        final KidDTO kidDTO = new KidDTO();
        kidDTO.setIdentity(originModel.getIdentity());
        kidDTO.setAge(originModel.getAge());
        kidDTO.setBirthdate(originModel.getBirthdate());
        kidDTO.setFirstName(originModel.getFirstName());
        kidDTO.setLastName(originModel.getLastName());
        kidDTO.setProfileImage(originModel.getProfileImage());
        kidDTO.setSchoolDTO(schoolDataMapper.transformInverse(originModel.getSchool()));
        kidDTO.setAlertStatistics(originModel.getAlertStatistics());
        kidDTO.setTerminalDTOList(terminalsDataMapper.transformInverse(originModel.getTerminalEntities()));
        return kidDTO;
    }
}
