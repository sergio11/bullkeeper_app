package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.request.AddPhoneNumberBlockedDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.PhoneNumberBlockedDTO;

/**
 * Phone Numbers Blocked Service
 * DELETE /api/v1/children/{kid}/terminal/{terminal}/phonenumbers-blocked DELETE_PHONE_NUMBER_BLOCKED
 * GET /api/v1/children/{kid}/terminal/{terminal}/phonenumbers-blocked GET_PHONE_NUMBER_BLOCKED
 * POST /api/v1/children/{kid}/terminal/{terminal}/phonenumbers-blocked ADD_PHONE_NUMBER_BLOCKED
 * DELETE /api/v1/children/{kid}/terminal/{terminal}/phonenumbers-blocked/{id} DELETE_PHONE_NUMBER_BLOCKED_BY_ID
 */
public interface IPhoneNumbersBlockedService {

    /**
     * Delete Phone Number Blocked
     * @param kid
     * @param terminal
     * @return
     */
    @DELETE("/children/{kid}/terminal/{terminal}/phonenumbers-blocked")
    Observable<APIResponse<String>> deletePhoneNumberBlocked(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal
    );

    /**
     * Get Phone Number Blocked
     * @param kid
     * @param terminal
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/phonenumbers-blocked")
    Observable<APIResponse<List<PhoneNumberBlockedDTO>>> getPhoneNumberBlocked(
             @Path("kid") final String kid,
             @Path("terminal") final String terminal);


    /**
     * Delete Phone Number Blocked
     * @param kid
     * @param terminal
     * @return
     */
    @DELETE("/children/{kid}/terminal/{terminal}/phonenumbers-blocked/{id}")
    Observable<APIResponse<String>> deletePhoneNumberBlocked(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal,
            @Path("id") final String id
    );

    /**
     * Add Phone Number Blocked
     * @param kid
     * @param terminal
     * @param addPhoneNumberBlockedDTO
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/phonenumbers-blocked")
    Observable<APIResponse<PhoneNumberBlockedDTO>> addPhoneNumberBlocked(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal,
            @Body final AddPhoneNumberBlockedDTO addPhoneNumberBlockedDTO);

}
