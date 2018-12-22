package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;

/**
 * Phone Numbers Blocked Repository
 */
public interface IPhoneNumbersBlockedRepository {

    /**
     * Delete Phone Number Blocked
     * @param kid
     * @param terminal
     */
    Observable<String> deletePhoneNumberBlocked(final String kid, final String terminal);

    /**
     * Get Phone Number Blocked
     * @param kid
     * @param terminal
     * @return
     */
    Observable<List<PhoneNumberBlockedEntity>> getPhoneNumberBlocked(final String kid, final String terminal);

    /**
     * Delete Phone Number Blocked
     * @param kid
     * @param terminal
     * @param idOrPhoneNumber
     */
    Observable<String> deletePhoneNumberBlocked(final String kid, final String terminal, final String idOrPhoneNumber);

    /**
     * Add Phone Number Blocked
     * @param kid
     * @param terminal
     * @param phoneNumber
     * @return
     */
    Observable<PhoneNumberBlockedEntity> addPhoneNumberBlocked(final String kid, final String terminal, final String phoneNumber);

}
