package sanchez.sanchez.sergio.data.repository;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import sanchez.sanchez.sergio.data.models.request.JwtAuthenticationRequestDTO;
import sanchez.sanchez.sergio.data.models.response.APIResponse;
import sanchez.sanchez.sergio.data.models.response.JwtAuthenticationResponseDTO;
import sanchez.sanchez.sergio.data.services.IAuthenticationService;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;

/**
 * Acounts Repository Impl
 */
public final class IAccountsRepositoryImpl implements IAccountsRepository {

    private final IAuthenticationService authenticationService;

    /**
     *
     * @param authenticationService
     */
    public IAccountsRepositoryImpl(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public Observable<String> getAuthorizationToken(final String email, final String password) {
        return authenticationService.getAuthorizationToken(new JwtAuthenticationRequestDTO(email, password)).map(new Function<APIResponse<JwtAuthenticationResponseDTO>, String>() {
            @Override
            public String apply(APIResponse<JwtAuthenticationResponseDTO> jwtAuthenticationResponseDTOAPIResponse) throws Exception {
                return jwtAuthenticationResponseDTOAPIResponse != null
                        && jwtAuthenticationResponseDTOAPIResponse.getData() != null ?
                            jwtAuthenticationResponseDTOAPIResponse.getData().getToken() : null;
            }
        });
    }
}
