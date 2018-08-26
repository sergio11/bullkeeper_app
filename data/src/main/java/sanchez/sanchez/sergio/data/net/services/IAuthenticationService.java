package sanchez.sanchez.sergio.data.net.services;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sanchez.sanchez.sergio.data.net.models.request.JwtAuthenticationRequestDTO;
import sanchez.sanchez.sergio.data.net.models.request.JwtSocialAuthenticationRequestDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.JwtAuthenticationResponseDTO;

/**
 * Authentication Service
 */
public interface IAuthenticationService {

    /**
     * Get Authorization Token
     * @param authorizationRequest
     * @return
     */
    @POST("parents/auth/")
    Observable<APIResponse<JwtAuthenticationResponseDTO>> getAuthorizationToken(
            @Body JwtAuthenticationRequestDTO authorizationRequest);


    /**
     * Get Authorization Token By Facebook
     * @param authorizationRequest
     * @return
     */
    @POST("parents/auth/facebook")
    Observable<APIResponse<JwtAuthenticationResponseDTO>> getAuthorizationTokenByFacebook(
            @Body JwtSocialAuthenticationRequestDTO authorizationRequest);

    /**
     * Get Authorization Token By Google
     * @param authorizationRequest
     * @return
     */
    @POST("parents/auth/google")
    Observable<APIResponse<JwtAuthenticationResponseDTO>> getAuthorizationTokenByGoogle(
            @Body JwtSocialAuthenticationRequestDTO authorizationRequest);

}
