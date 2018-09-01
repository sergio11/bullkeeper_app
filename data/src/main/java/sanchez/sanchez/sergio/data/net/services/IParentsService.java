package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import sanchez.sanchez.sergio.data.net.models.request.RegisterParentDTO;
import sanchez.sanchez.sergio.data.net.models.request.ResetPasswordRequestDTO;
import sanchez.sanchez.sergio.data.net.models.request.UpdateParentDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
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


    /**
     * Get Parent Self Information
     */
    @GET("parents/self")
    Observable<APIResponse<ParentDTO>> getParentSelfInformation();

    /**
     * Update Self Parent
     * @return
     */
    @POST("parents/self")
    Observable<APIResponse<ParentDTO>> updateSelfParent(@Body final UpdateParentDTO updateParentDTO);

    /**
     * Upload Profile Image
     * @return
     */
    @Multipart
    @POST("parents/self/image")
    Observable<APIResponse<ImageDTO>> uploadProfileImage(@Part final MultipartBody.Part image);

    /**
     * Delete Self Account
     * @return
     */
    @DELETE("parents/self/delete")
    Observable<APIResponse<String>> deleteSelfAccount();


}
