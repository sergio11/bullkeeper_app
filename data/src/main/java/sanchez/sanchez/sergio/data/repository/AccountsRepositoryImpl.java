package sanchez.sanchez.sergio.data.repository;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.JwtAuthenticationRequestDTO;
import sanchez.sanchez.sergio.data.net.models.request.RegisterParentDTO;
import sanchez.sanchez.sergio.data.net.models.request.ResetPasswordRequestDTO;
import sanchez.sanchez.sergio.data.net.models.response.ParentDTO;
import sanchez.sanchez.sergio.data.net.services.IAuthenticationService;
import sanchez.sanchez.sergio.data.net.services.IParentsService;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;

/**
 * Accounts Repository Impl
 */
public final class AccountsRepositoryImpl implements IAccountsRepository {

    private final IAuthenticationService authenticationService;
    private final IParentsService parentsService;
    private final AbstractDataMapper<ParentDTO, ParentEntity> parentDataMapper;

    /**
     * @param authenticationService
     * @param parentsService
     */
    public AccountsRepositoryImpl(final IAuthenticationService authenticationService,
                                  final IParentsService parentsService,
                                  final  AbstractDataMapper<ParentDTO, ParentEntity> parentDataMapper) {
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
    public Observable<ParentEntity> registerParent(final String firstName, final String lastName, final String birthdate,
                                                   final String email, final String passwordClear, final String confirmPassword, final String locale, final String telephone) {
        return parentsService.register(new RegisterParentDTO(firstName, lastName, birthdate, email,
                passwordClear, confirmPassword, locale, telephone)).map(response -> response != null && response.getData() != null ? response.getData() : null)
                .map(parentDataMapper::transform);
    }
}
