package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.AddPhoneNumberBlockedDTO;
import sanchez.sanchez.sergio.data.net.models.response.PhoneNumberBlockedDTO;
import sanchez.sanchez.sergio.data.net.services.IPhoneNumbersBlockedService;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;
import sanchez.sanchez.sergio.domain.models.PhoneNumberNotAllowed;
import sanchez.sanchez.sergio.domain.repository.IPhoneNumbersBlockedRepository;

/**
 * Phone Numbers Blocked Repository
 */
public final class PhoneNumbersBlockedRepositoryImpl implements IPhoneNumbersBlockedRepository {

    /**
     * Phone Numbers Blocked Service
     */
    private final IPhoneNumbersBlockedService phoneNumbersBlockedService;

    /**
     * Phone Number Blocked Entity Data Mapper
     */
    private final AbstractDataMapper<PhoneNumberBlockedDTO, PhoneNumberBlockedEntity>
        phoneNumberBlockedEntityAbstractDataMapper;

    /**
     *
     * @param phoneNumbersBlockedService
     * @param phoneNumberBlockedEntityAbstractDataMapper
     */
    public PhoneNumbersBlockedRepositoryImpl(final IPhoneNumbersBlockedService phoneNumbersBlockedService,
                                             final AbstractDataMapper<PhoneNumberBlockedDTO, PhoneNumberBlockedEntity> phoneNumberBlockedEntityAbstractDataMapper) {
        this.phoneNumbersBlockedService = phoneNumbersBlockedService;
        this.phoneNumberBlockedEntityAbstractDataMapper = phoneNumberBlockedEntityAbstractDataMapper;
    }

    /**
     * Delete Phone Number Blocked
     * @param kid
     * @param terminal
     */
    @Override
    public Observable<String> deletePhoneNumberBlocked(final String kid, final String terminal) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");

        return phoneNumbersBlockedService.deletePhoneNumberBlocked(kid, terminal)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null);
    }

    /**
     * Get Phone Number Blocked
     * @param kid
     * @param terminal
     * @return
     */
    @Override
    public Observable<List<PhoneNumberBlockedEntity>> getPhoneNumberBlocked(final String kid, final String terminal) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");

        return phoneNumbersBlockedService.getPhoneNumberBlocked(kid, terminal)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(phoneNumberBlockedEntityAbstractDataMapper::transform);

    }

    /**
     * Delete Phone Number Blocked
     * @param kid
     * @param terminal
     * @param idOrPhoneNumber
     * @return
     */
    @Override
    public Observable<String> deletePhoneNumberBlocked(final String kid, final String terminal, final String idOrPhoneNumber) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(idOrPhoneNumber, "phone number can not be null");
        Preconditions.checkState(!idOrPhoneNumber.isEmpty(), "phone number can not be empty");

        return phoneNumbersBlockedService.deletePhoneNumberBlocked(kid, terminal, idOrPhoneNumber)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Delete Phone Number Blocked
     * @param kid
     * @param terminal
     * @param ids
     * @return
     */
    @Override
    public Observable<String> deletePhoneNumberBlocked(final String kid, final String terminal, final List<String> ids) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(ids, "Ids can not be null");
        Preconditions.checkState(!ids.isEmpty(), "Ids can not be empty");

        return phoneNumbersBlockedService.deletePhoneNumberBlocked(kid, terminal, ids)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Add Phone Number Blocked
     * @param kid
     * @param terminal
     * @param phoneNumberNotAllowedList
     * @return
     */
    @Override
    public Observable<List<PhoneNumberBlockedEntity>> addPhoneNumberBlocked(
            final String kid, final String terminal,
            final List<PhoneNumberNotAllowed> phoneNumberNotAllowedList) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(phoneNumberNotAllowedList, "phoneNumberNotAllowedList can not be null");
        Preconditions.checkState(!phoneNumberNotAllowedList.isEmpty(), "phoneNumberNotAllowedList can not be empty");


        final List<AddPhoneNumberBlockedDTO> addPhoneNumberBlockedDTOList = new ArrayList<>();
        for(final PhoneNumberNotAllowed phoneNumberBlockedEntity: phoneNumberNotAllowedList)
            addPhoneNumberBlockedDTOList.add(new AddPhoneNumberBlockedDTO(phoneNumberBlockedEntity.getPrefix(),
                    phoneNumberBlockedEntity.getNumber(), phoneNumberBlockedEntity.getPhoneNumber(), terminal, kid));


        return phoneNumbersBlockedService.addPhoneNumberBlocked(kid, terminal,
                addPhoneNumberBlockedDTOList )
                    .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(phoneNumberBlockedEntityAbstractDataMapper::transform);

    }
}
