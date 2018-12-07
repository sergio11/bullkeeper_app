package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.PersonDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.PersonEntity;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Person Entity Data Mapper
 */
public final class PersonEntityDataMapper extends AbstractDataMapper<PersonDTO, PersonEntity> {

    private final ApiEndPointsHelper apiEndPointsHelper;
    private final IAppUtils appUtils;

    /**
     *
     * @param apiEndPointsHelper
     * @param appUtils
     */
    public PersonEntityDataMapper(ApiEndPointsHelper apiEndPointsHelper, IAppUtils appUtils) {
        this.apiEndPointsHelper = apiEndPointsHelper;
        this.appUtils = appUtils;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public PersonEntity transform(PersonDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final PersonEntity personEntity = new PersonEntity();
        personEntity.setIdentity(originModel.getIdentity());
        personEntity.setFirstName(originModel.getFirstName());
        personEntity.setLastName(originModel.getLastName());
        if(appUtils.isValidString(originModel.getProfileImage()))
            personEntity.setProfileImage(originModel.getProfileImage());
        return personEntity;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public PersonDTO transformInverse(PersonEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final PersonDTO personDTO = new PersonDTO();
        personDTO.setIdentity(originModel.getIdentity());
        personDTO.setFirstName(originModel.getFirstName());
        personDTO.setLastName(originModel.getLastName());
        if(appUtils.isValidString(originModel.getProfileImage()))
            personDTO.setProfileImage(originModel.getProfileImage());
        return personDTO;
    }
}
