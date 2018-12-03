package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.GuardianDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Guardian Entity Data Mapper
 */
public final class GuardianEntityDataMapper extends AbstractDataMapper<GuardianDTO, GuardianEntity> {

    private final ApiEndPointsHelper apiEndPointsHelper;
    private final IAppUtils appUtils;

    /**
     * Guardian Entity Data Mapper
     * @param apiEndPointsHelper
     */
    public GuardianEntityDataMapper(final ApiEndPointsHelper apiEndPointsHelper, final IAppUtils appUtils) {
        this.apiEndPointsHelper = apiEndPointsHelper;
        this.appUtils = appUtils;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public GuardianEntity transform(final GuardianDTO originModel) {
        final GuardianEntity guardianEntity = new GuardianEntity();
        guardianEntity.setIdentity(originModel.getIdentity());
        guardianEntity.setAge(originModel.getAge());
        guardianEntity.setBirthdate(originModel.getBirthdate());
        guardianEntity.setChildren(originModel.getChildren());
        guardianEntity.setEmail(originModel.getEmail());
        guardianEntity.setFirstName(originModel.getFirstName());
        guardianEntity.setLastName(originModel.getLastName());
        guardianEntity.setLocale(originModel.getLocale());
        guardianEntity.setPhoneNumber(originModel.getPhoneNumber());
        guardianEntity.setPhonePrefix(originModel.getPhonePrefix());
        guardianEntity.setVisible(originModel.isVisible());
        if (appUtils.isValidString(originModel.getProfileImage()))
            guardianEntity.setProfileImage(apiEndPointsHelper
                    .getGuardianProfileUrl(originModel.getProfileImage()));
        return guardianEntity;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public GuardianDTO transformInverse(GuardianEntity originModel) {
        final GuardianDTO guardianDTO = new GuardianDTO();
        guardianDTO.setIdentity(originModel.getIdentity());
        guardianDTO.setAge(originModel.getAge());
        guardianDTO.setBirthdate(originModel.getBirthdate());
        guardianDTO.setChildren(originModel.getChildren());
        guardianDTO.setEmail(originModel.getEmail());
        guardianDTO.setFbId(originModel.getFbId());
        guardianDTO.setFirstName(originModel.getFirstName());
        guardianDTO.setLastName(originModel.getLastName());
        guardianDTO.setLocale(originModel.getLocale());
        guardianDTO.setPhoneNumber(originModel.getPhoneNumber());
        guardianDTO.setPhonePrefix(originModel.getPhonePrefix());
        guardianDTO.setVisible(originModel.isVisible());
        if (appUtils.isValidString(originModel.getProfileImage()))
            guardianDTO.setProfileImage(originModel.getProfileImage());
        return guardianDTO;
    }
}
