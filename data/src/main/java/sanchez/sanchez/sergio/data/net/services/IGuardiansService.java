package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.request.ChangeUserEmailDTO;
import sanchez.sanchez.sergio.data.net.models.request.ChangeUserPasswordDTO;
import sanchez.sanchez.sergio.data.net.models.request.RegisterGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.request.ResetPasswordRequestDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveUserPreferenceDTO;
import sanchez.sanchez.sergio.data.net.models.request.UpdateGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.ChildrenOfSelfGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.GuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.UserPreferenceDTO;

/**
 * Guardians Service Interface
 *
 * GET /api/v1/guardians/self/preferences GET_SELF_PREFERENCES
 * POST /api/v1/guardians/self/preferences SAVE_PREFERENCES
 */
public interface IGuardiansService {

    /**
     * Reset Password
     * @param resetPasswordRequestDTO
     * @return
     */
    @POST("guardians/reset-password")
    Observable<APIResponse<String>> resetPassword(@Body final ResetPasswordRequestDTO resetPasswordRequestDTO);

    /**
     * Register Parent
     * @param registerGuardianDTO
     * @return
     */
    @POST("guardians/")
    Observable<APIResponse<GuardianDTO>> register(@Body final RegisterGuardianDTO registerGuardianDTO);

    /**
     * Get Self Children
     * @return
     */
    @GET("guardians/self/children")
    Observable<APIResponse<ChildrenOfSelfGuardianDTO>> getSelfChildren();

    /**
     * Get Self Children
     * @return
     */
    @GET("guardians/self/children")
    Observable<APIResponse<ChildrenOfSelfGuardianDTO>> getSelfChildren(
            final @Query("text") String text
    );


    /**
     * Get Guardian Self Information
     */
    @GET("guardians/self")
    Observable<APIResponse<GuardianDTO>> getParentSelfInformation();

    /**
     * Update Self Guardian
     * @return
     */
    @POST("guardians/self")
    Observable<APIResponse<GuardianDTO>> updateSelfParent(@Body final UpdateGuardianDTO updateGuardianDTO);

    /**
     * Upload Profile Image
     * @return
     */
    @Multipart
    @POST("guardians/self/image")
    Observable<APIResponse<ImageDTO>> uploadProfileImage(@Part final MultipartBody.Part image);

    /**
     * Delete Self Account
     * @return
     */
    @DELETE("guardians/self/delete")
    Observable<APIResponse<String>> deleteSelfAccount();

    /**
     * Search Guardian
     * @return
     */
    @GET("guardians/search")
    Observable<APIResponse<List<GuardianDTO>>> searchGuardian(
            final @Query("text") String text);


    /**
     *
     * @param changeUserEmailDTO
     * @return
     */
    @POST("guardians/self/change-email")
    Observable<APIResponse<String>> changeUserEmail(
            @Body final ChangeUserEmailDTO changeUserEmailDTO);

    /**
     * Change Password
     * @param changeUserPasswordDTO
     * @return
     */
    @POST("guardians/self/change-password")
    Observable<APIResponse<String>> changePassword(
            @Body final ChangeUserPasswordDTO changeUserPasswordDTO);


    /**
     * Get Supervised Child Confirmed By Id
     * @param id
     * @return
     */
    @GET("guardians/self/children/{id}/confirmed")
    Observable<APIResponse<KidGuardianDTO>> getSupervisedChildConfirmedById(
            @Path("id") final String id
    );


    /**
     * Get Preferences
     * @return
     */
    @GET("guardians/self/preferences")
    Observable<APIResponse<UserPreferenceDTO>> getPreferences();

    /**
     * Save Preferences
     * @return
     */
    @POST("guardians/self/preferences")
    Observable<APIResponse<UserPreferenceDTO>> savePreferences(
            @Body final SaveUserPreferenceDTO saveUserPreferenceDTO);
}
