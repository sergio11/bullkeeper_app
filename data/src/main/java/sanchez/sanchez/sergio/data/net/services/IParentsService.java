package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import sanchez.sanchez.sergio.data.net.models.request.RegisterParentDTO;
import sanchez.sanchez.sergio.data.net.models.request.ResetPasswordRequestDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.ParentDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;

/**
 * Parent Service Interface
 */
public interface IParentsService {

    /**
     * Reset Password
     * @param resetPasswordRequestDTO
     * @return
     */
    @POST("parents/reset-password")
    Observable<APIResponse<String>> resetPassword(@Body final ResetPasswordRequestDTO resetPasswordRequestDTO);

    /**
     * Register Parent
     * @param registerParentDTO
     * @return
     */
    @POST("parents/")
    Observable<APIResponse<ParentDTO>> register(@Body final RegisterParentDTO registerParentDTO);

    /**
     * Get Self Children
     * @return
     */
    @GET("parents/self/children")
    Observable<APIResponse<List<SonDTO>>> getSelfChildren();

}
