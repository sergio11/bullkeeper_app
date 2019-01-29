package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.PhoneNumberBlockedDTO;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;

/**
 * Phone Number Blocked Data Mapper
 */
public final class PhoneNumberBlockedEntityDataMapper extends
        AbstractDataMapper<PhoneNumberBlockedDTO, PhoneNumberBlockedEntity> {

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public PhoneNumberBlockedEntity transform(PhoneNumberBlockedDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final PhoneNumberBlockedEntity phoneNumberBlockedEntity = new PhoneNumberBlockedEntity();
        phoneNumberBlockedEntity.setIdentity(originModel.getIdentity());
        phoneNumberBlockedEntity.setKid(originModel.getKid());
        phoneNumberBlockedEntity.setTerminal(originModel.getTerminal());
        phoneNumberBlockedEntity.setPrefix(originModel.getPrefix());
        phoneNumberBlockedEntity.setNumber(originModel.getNumber());
        phoneNumberBlockedEntity.setPhoneNumber(originModel.getPhoneNumber());
        phoneNumberBlockedEntity.setBlockedAt(originModel.getBlockedAt());
        return phoneNumberBlockedEntity;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public PhoneNumberBlockedDTO transformInverse(PhoneNumberBlockedEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final PhoneNumberBlockedDTO phoneNumberBlockedDTO = new PhoneNumberBlockedDTO();
        phoneNumberBlockedDTO.setIdentity(originModel.getIdentity());
        phoneNumberBlockedDTO.setKid(originModel.getKid());
        phoneNumberBlockedDTO.setTerminal(originModel.getTerminal());
        phoneNumberBlockedDTO.setPrefix(originModel.getPrefix());
        phoneNumberBlockedDTO.setNumber(originModel.getNumber());
        phoneNumberBlockedDTO.setPhoneNumber(originModel.getPhoneNumber());
        phoneNumberBlockedDTO.setBlockedAt(originModel.getBlockedAt());
        return phoneNumberBlockedDTO;
    }
}
