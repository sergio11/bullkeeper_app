package sanchez.sanchez.sergio.data.services;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sanchez.sanchez.sergio.data.models.request.ResetPasswordRequestDTO;
import sanchez.sanchez.sergio.data.models.response.APIResponse;

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

}
