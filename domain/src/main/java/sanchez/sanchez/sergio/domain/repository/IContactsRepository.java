package sanchez.sanchez.sergio.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.ContactEntity;

/**
 * Contacts Repository
 */
public interface IContactsRepository {

    /**
     * Get Call List
     * @param kid
     * @param terminal
     * @return
     */
    Observable<List<ContactEntity>> getContactsList(final String kid, final String terminal);

    /**
     * Get Contact Detail
     * @param kid
     * @param terminal
     * @param contact
     * @return
     */
    Observable<ContactEntity> getContactDetail(final String kid, final String terminal, final String contact);
}
