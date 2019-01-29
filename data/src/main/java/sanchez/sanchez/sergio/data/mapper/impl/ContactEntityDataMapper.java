package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ContactDTO;
import sanchez.sanchez.sergio.domain.models.ContactEntity;

/**
 * Contact Entity Data Mapper
 */
public final class ContactEntityDataMapper extends AbstractDataMapper<ContactDTO, ContactEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public ContactEntity transform(final ContactDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final ContactEntity contactEntity = new ContactEntity();
        contactEntity.setIdentity(originModel.getIdentity());
        contactEntity.setKid(originModel.getKid());
        contactEntity.setLocalId(originModel.getLocalId());
        contactEntity.setName(originModel.getName());
        contactEntity.setPhoneNumber(originModel.getPhoneNumber());
        contactEntity.setPhotoEncodedString(originModel.getPhotoEncodedString());
        contactEntity.setTerminal(originModel.getTerminal());
        contactEntity.setBlocked(originModel.isBlocked());
        return contactEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public ContactDTO transformInverse(ContactEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setIdentity(originModel.getIdentity());
        contactDTO.setKid(originModel.getKid());
        contactDTO.setLocalId(originModel.getLocalId());
        contactDTO.setName(originModel.getName());
        contactDTO.setPhoneNumber(originModel.getPhoneNumber());
        contactDTO.setTerminal(originModel.getTerminal());
        contactDTO.setPhotoEncodedString(originModel.getPhotoEncodedString());
        contactDTO.setBlocked(originModel.isBlocked());
        return contactDTO;
    }
}
