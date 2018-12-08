package sanchez.sanchez.sergio.data.repository;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.JwtAuthenticationRequestDTO;
import sanchez.sanchez.sergio.data.net.models.request.JwtSocialAuthenticationRequestDTO;
import sanchez.sanchez.sergio.data.net.models.request.RegisterGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.request.ResetPasswordRequestDTO;
import sanchez.sanchez.sergio.data.net.models.response.GuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.JwtAuthenticationResponseDTO;
import sanchez.sanchez.sergio.data.net.services.IAuthenticationService;
import sanchez.sanchez.sergio.data.net.services.IGuardiansService;
import sanchez.sanchez.sergio.domain.models.AuthenticationResponseEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;

/**
 * Accounts Repository Impl
 */
public final class AccountsRepositoryImpl implements IAccountsRepository {

    /**
     * Authentication Service
     */
    private final IAuthenticationService authenticationService;

    /**
     * Guardians Service
     */
    private final IGuardiansService guardiansService;

    /**
     * Guardians Data Mapper
     */
    private final AbstractDataMapper<GuardianDTO, GuardianEntity> guardiansDataMapper;

    /**
     * Authentication Response
     */
    private final AbstractDataMapper<JwtAuthenticationResponseDTO, AuthenticationResponseEntity>
            authenticationResponseDataMapper;

    /**
     * @param authenticationService
     * @param guardiansService
     * @param authenticationResponseDataMapper
     */
    public AccountsRepositoryImpl(final IAuthenticationService authenticationService,
                                  final IGuardiansService guardiansService,
                                  final  AbstractDataMapper<GuardianDTO, GuardianEntity>
                                          guardiansDataMapper,
                                  final AbstractDataMapper<JwtAuthenticationResponseDTO, AuthenticationResponseEntity>
                                          authenticationResponseDataMapper) {
        this.authenticationService = authenticationService;
        this.guardiansService = guardiansService;
        this.guardiansDataMapper = guardiansDataMapper;
        this.authenticationResponseDataMapper = authenticationResponseDataMapper;
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public Observable<AuthenticationResponseEntity> getAuthorizationToken(final String email, final String password) {
        return authenticationService.getAuthorizationToken(new JwtAuthenticationRequestDTO(email, password))
                .map(response -> response != null
                && response.getData() != null ?
                        response.getData() : null)
                .map(authenticationResponseDataMapper::transform);
    }

    /**
     * Get Authorization Token By Facebook
     * @param token
     * @return
     */
    @Override
    public Observable<AuthenticationResponseEntity> getAuthorizationTokenByFacebook(final String token) {
        return authenticationService.getAuthorizationTokenByFacebook(new JwtSocialAuthenticationRequestDTO(token))
                .map(response -> response != null
                        && response.getData() != null ?
                        response.getData() : null)
                .map(authenticationResponseDataMapper::transform);
    }

    /**
     * Reset Password
     * @param email
     * @return
     */
    @Override
    public Observable<String> resetPassword(final String email) {
        return guardiansService.resetPassword(new ResetPasswordRequestDTO(email))
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
        return guardiansService.register(new RegisterGuardianDTO(firstName, lastName, birthdate, email,
                passwordClear, confirmPassword, locale, telephone)).map(response -> response != null && response.getData() != null ? response.getData() : null)
                .map(guardiansDataMapper::transform);
    }
}
