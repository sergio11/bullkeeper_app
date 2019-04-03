package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.ArrayList;
import java.util.List;

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
        contactEntity.setPhotoEncodedString(originModel.getPhotoEncodedString());
        contactEntity.setTerminal(originModel.getTerminal());
        contactEntity.setBlocked(originModel.isBlocked());

        final List<ContactEntity.PhoneContactEntity> phoneContactEntityList = new ArrayList<>();
        for(final ContactDTO.PhoneContactDTO phoneContactDTO: originModel.getPhoneList())
            phoneContactEntityList.add(new ContactEntity.PhoneContactEntity(phoneContactDTO.getPhone()));

        contactEntity.setPhoneList(phoneContactEntityList);

        final List<ContactEntity.EmailContactEntity> emailContactEntities = new ArrayList<>();
        for(final ContactDTO.EmailContactDTO emailContactDTO: originModel.getEmailList())
            emailContactEntities.add(new ContactEntity.EmailContactEntity(emailContactDTO.getEmail()));

        contactEntity.setEmailList(emailContactEntities);

        final List<ContactEntity.PostalAddressEntity> postalAddressEntities = new ArrayList<>();
        for(final ContactDTO.PostalAddressDTO postalAddressDTO: originModel.getAddressList())
            postalAddressEntities.add(new ContactEntity.PostalAddressEntity(
                    postalAddressDTO.getCity(),
                    postalAddressDTO.getState(),
                    postalAddressDTO.getCountry()));


        contactEntity.setAddressList(postalAddressEntities);

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
        contactDTO.setTerminal(originModel.getTerminal());
        contactDTO.setPhotoEncodedString(originModel.getPhotoEncodedString());
        contactDTO.setBlocked(originModel.isBlocked());

        final List<ContactDTO.PhoneContactDTO> phoneContactEntityList = new ArrayList<>();
        for(final ContactEntity.PhoneContactEntity phoneContactEntity: originModel.getPhoneList())
            phoneContactEntityList.add(new ContactDTO.PhoneContactDTO(phoneContactEntity.getPhone()));

        contactDTO.setPhoneList(phoneContactEntityList);

        final List<ContactDTO.EmailContactDTO> emailContactEntities = new ArrayList<>();
        for(final ContactEntity.EmailContactEntity emailContactEntity: originModel.getEmailList())
            emailContactEntities.add(new ContactDTO.EmailContactDTO(emailContactEntity.getEmail()));

        contactDTO.setEmailList(emailContactEntities);

        final List<ContactDTO.PostalAddressDTO> postalAddressEntities = new ArrayList<>();
        for(final ContactEntity.PostalAddressEntity postalAddressDTO: originModel.getAddressList())
            postalAddressEntities.add(new ContactDTO.PostalAddressDTO(
                    postalAddressDTO.getCity(),
                    postalAddressDTO.getState(),
                    postalAddressDTO.getCountry()));


        contactDTO.setAddressList(postalAddressEntities);
        return contactDTO;
    }
}
