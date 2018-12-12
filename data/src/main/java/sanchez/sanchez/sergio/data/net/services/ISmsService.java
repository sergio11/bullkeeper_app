package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.SmsDTO;

/**
 * SMS Service
 */
public interface ISmsService {

    /**
     * Get SMS List
     * @param kid
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/sms")
    Observable<APIResponse<List<SmsDTO>>> getSmsList(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal);


    /**
     * Get SMS Detail
     * @param kid
     * @param terminal
     * @param sms
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/sms/{sms}")
    Observable<APIResponse<SmsDTO>> getSmsDetail(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal,
            final @Path("sms") String sms
    );

}
