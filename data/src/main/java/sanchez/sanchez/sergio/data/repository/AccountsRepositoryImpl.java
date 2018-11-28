package sanchez.sanchez.sergio.data.repository;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.JwtAuthenticationRequestDTO;
import sanchez.sanchez.sergio.data.net.models.request.JwtSocialAuthenticationRequestDTO;
import sanchez.sanchez.sergio.data.net.models.request.RegisterGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.request.ResetPasswordRequestDTO;
import sanchez.sanchez.sergio.data.net.models.response.GuardianDTO;
import sanchez.sanchez.sergio.data.net.services.IAuthenticationService;
import sanchez.sanchez.sergio.data.net.services.IGuardiansService;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;

/**
 * Accounts Repository Impl
 */
public final class AccountsRepositoryImpl implements IAccountsRepository {

    private final IAuthenticationService authenticationService;
    private final IGuardiansService parentsService;
    private final AbstractDataMapper<GuardianDTO, GuardianEntity> parentDataMapper;

    /**
     * @param authenticationService
     * @param parentsService
     */
    public AccountsRepositoryImpl(final IAuthenticationService authenticationService,
                                  final IGuardiansService parentsService,
                                  final  AbstractDataMapper<GuardianDTO, GuardianEntity> parentDataMapper) {
        this.authenticationService = authenticationService;
        this.parentsService = parentsService;
        this.parentDataMapper = parentDataMapper;
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
     * Get Authorization Token By Facebook
     * @param token
     * @return
     */
    @Override
    public Observable<String> getAuthorizationTokenByFacebook(final String token) {
        return authenticationService.getAuthorizationTokenByFacebook(new JwtSocialAuthenticationRequestDTO(token))
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
    @Override
    public Observable<GuardianEntity> registerParent(final String firstName, final String lastName, final String birthdate,
                                                     final String email, final String passwordClear, final String confirmPassword, final String locale, final String telephone) {
        return parentsService.register(new RegisterGuardianDTO(firstName, lastName, birthdate, email,
                passwordClear, confirmPassword, locale, telephone)).map(response -> response != null && response.getData() != null ? response.getData() : null)
                .map(parentDataMapper::transform);
    }
}
