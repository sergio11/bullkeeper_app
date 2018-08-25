package sanchez.sanchez.sergio.data.repository;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.models.request.JwtAuthenticationRequestDTO;
import sanchez.sanchez.sergio.data.models.request.ResetPasswordRequestDTO;
import sanchez.sanchez.sergio.data.services.IAuthenticationService;
import sanchez.sanchez.sergio.data.services.IParentsService;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;

/**
 * Acounts Repository Impl
 */
public final class IAccountsRepositoryImpl implements IAccountsRepository {

    private final IAuthenticationService authenticationService;
    private final IParentsService parentsService;

    /**
     * @param authenticationService
     * @param parentsService
     */
    public IAccountsRepositoryImpl(final IAuthenticationService authenticationService,
                                   final IParentsService parentsService) {
        this.authenticationService = authenticationService;
        this.parentsService = parentsService;
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public Observable<String> getAuthorizationToken(final String email, final String password) {
        return authenticationService.getAuthorizationToken(new JwtAuthenticationRequestDTO(email, password))
                .map(response -> response != null
                && response.getData() != null ?
                        response.getData().getToken() : null);
    }

    /**
     * Reset Password
     * @param email
     * @return
     */
    @Override
    public Observable<String> resetPassword(final String email) {
        return parentsService.resetPassword(new ResetPasswordRequestDTO(email))
                .map(response -> response != null && response.getData() != null ? response.getData() : null);
    }
}
