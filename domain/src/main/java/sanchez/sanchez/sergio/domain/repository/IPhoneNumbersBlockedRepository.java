package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;
import sanchez.sanchez.sergio.domain.models.PhoneNumberNotAllowed;

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
     * Delete Phone Number Blocked
     * @param kid
     * @param terminal
     * @param ids
     */
    Observable<String> deletePhoneNumberBlocked(final String kid, final String terminal,
                                                final List<String> ids);

    /**
     * Add Phone Number Blocked
     * @param kid
     * @param terminal
     * @param phoneNumberNotAllowedList
     * @return
     */
    Observable<List<PhoneNumberBlockedEntity>> addPhoneNumberBlocked(final String kid, final String terminal,
                                                               final List<PhoneNumberNotAllowed> phoneNumberNotAllowedList);

}
