package sanchez.sanchez.sergio.domain.repository;

import io.reactivex.Observable;

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

}
