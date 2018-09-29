package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ParentDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Parent Entity Data Mapper
 */
public final class ParentEntityDataMapper extends AbstractDataMapper<ParentDTO, ParentEntity> {

    private final ApiEndPointsHelper apiEndPointsHelper;
    private final IAppUtils appUtils;

    /**
     * Parent Entity Data Mapper
     * @param apiEndPointsHelper
     */
    public ParentEntityDataMapper(final ApiEndPointsHelper apiEndPointsHelper, final IAppUtils appUtils) {
        this.apiEndPointsHelper = apiEndPointsHelper;
        this.appUtils = appUtils;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public ParentEntity transform(final ParentDTO originModel) {
        final ParentEntity parentEntity = new ParentEntity();
        parentEntity.setIdentity(originModel.getIdentity());
        parentEntity.setAge(originModel.getAge());
        parentEntity.setBirthdate(originModel.getBirthdate());
        parentEntity.setChildren(originModel.getChildren());
        parentEntity.setEmail(originModel.getEmail());
        parentEntity.setFirstName(originModel.getFirstName());
        parentEntity.setLastName(originModel.getLastName());
        parentEntity.setLocale(originModel.getLocale());
        parentEntity.setPhoneNumber(originModel.getPhoneNumber());
        parentEntity.setPhonePrefix(originModel.getPhonePrefix());
        if (appUtils.isValidString(originModel.getProfileImage()))
            parentEntity.setProfileImage(apiEndPointsHelper
                    .getParentProfileUrl(originModel.getProfileImage()));
        return parentEntity;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public ParentDTO transformInverse(ParentEntity originModel) {
        final ParentDTO parentDTO = new ParentDTO();
        parentDTO.setIdentity(originModel.getIdentity());
        parentDTO.setAge(originModel.getAge());
        parentDTO.setBirthdate(originModel.getBirthdate());
        parentDTO.setChildren(originModel.getChildren());
        parentDTO.setEmail(originModel.getEmail());
        parentDTO.setFbId(originModel.getFbId());
        parentDTO.setFirstName(originModel.getFirstName());
        parentDTO.setLastName(originModel.getLastName());
        parentDTO.setLocale(originModel.getLocale());
        parentDTO.setPhoneNumber(originModel.getPhoneNumber());
        parentDTO.setPhonePrefix(originModel.getPhonePrefix());
        if (appUtils.isValidString(originModel.getProfileImage()))
            parentDTO.setProfileImage(originModel.getProfileImage());
        return parentDTO;
    }
}
