package sanchez.sanchez.sergio.domain.repository;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.ParentEntity;

/**
 * Accounts Repository
 */
public interface IAccountsRepository {


    /**
     * Get Authorization Token
     * @param email
     * @param password
     * @return
     */
    Observable<String> getAuthorizationToken(final String email, final String password);

    /**
     * Get Authorization Token By Facebook
     * @param token
     * @return
     */
    Observable<String> getAuthorizationTokenByFacebook(final String token);

    /**
     * Reset Password
     * @param email
     * @return
     */
    Observable<String> resetPassword(final String email);

    /**
     * Register Parent
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param email
     * @param passwordClear
     * @param confirmPassword
     * @param locale
     * @param telephone
     * @return
     */
    Observable<ParentEntity> registerParent(final String firstName, final String lastName, final String birthdate,
                                            final String email, final String passwordClear, final String confirmPassword,
                                            final String locale, final String telephone);

}
