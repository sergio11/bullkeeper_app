package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ContactDTO;
import sanchez.sanchez.sergio.data.net.services.IContactsService;
import sanchez.sanchez.sergio.domain.models.ContactEntity;
import sanchez.sanchez.sergio.domain.repository.IContactsRepository;

/**
 * Contact Repository Impl
 */
public final class ContactsRepositoryImpl implements IContactsRepository {

    /**
     * Contact Service
     */
    private IContactsService contactsService;

    /**
     * Contact Entity Data Mapper
     */
    private AbstractDataMapper<ContactDTO, ContactEntity> contactEntityDataMapper;

    /**
     * @param contactsService
     * @param contactEntityDataMapper
     */
    public ContactsRepositoryImpl(final IContactsService contactsService,
                                  final AbstractDataMapper<ContactDTO, ContactEntity> contactEntityDataMapper) {
        this.contactsService = contactsService;
        this.contactEntityDataMapper = contactEntityDataMapper;
    }

    /**
     * Get Contact List
     * @param kid
     * @param terminal
     * @return
     */
    @Override
    public Observable<List<ContactEntity>> getContactsList(final String kid, final String terminal) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");

        return contactsService.getAllContacts(kid, terminal)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(contactEntityDataMapper::transform);
    }

    /**
     * Get Contacts List
     * @param kid
     * @param terminal
     * @param query
     * @return
     */
    @Override
    public Observable<List<ContactEntity>> getContactsList(final String kid, final String terminal, final String query) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(query, "Query can not be null");

        return contactsService.getAllContacts(kid, terminal, query)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(contactEntityDataMapper::transform);
    }

    /**
     * Get Contact Detail
     * @param kid
     * @param terminal
     * @param contact
     * @return
     */
    @Override
    public Observable<ContactEntity> getContactDetail(final String kid, final String terminal, final String contact) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(contact, "Contact can not be null");
        Preconditions.checkState(!contact.isEmpty(), "Contact can not be empty");

        return contactsService.getContactDetail(kid, terminal, contact)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(contactEntityDataMapper::transform);
    }
}
