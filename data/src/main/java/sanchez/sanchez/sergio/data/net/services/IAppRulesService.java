package sanchez.sanchez.sergio.data.net.services;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.request.AppInstalledRuleDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDTO;

/**
 * App Rules Service
 */
public interface IAppRulesService {


    /**
     * Get App Installed
     * @param kid
     * @param terminal
     * @param query
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/apps")
    Observable<APIResponse<List<AppInstalledDTO>>> getAppInstalledByChild(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal,
            @Query("text") final String query);

    /**
     * Update App Installed Rules By Child
     * @param kid
     * @param terminal
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/apps/rules")
    Observable<APIResponse<String>> updateAppInstalledRulesByChild(
            @Path("kid") final String kid, @Path("terminal") final String terminal,
            final @Body List<AppInstalledRuleDTO> appInstalledRuleDTOs);


    /**
     * Update Single App Installed Rules By Child
     * @param kid
     * @param terminal
     * @param id
     * @param appInstalledRuleDTO
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/apps/{id}/rules")
    Observable<APIResponse<String>> updateSingleAppInstalledRulesByChild(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal,
            @Path("id") final String id,
            final @Body AppInstalledRuleDTO appInstalledRuleDTO);


    /**
     * Get App Installed Detail
     * @param kid
     * @param terminal
     * @param app
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/apps/{app}")
    Observable<APIResponse<AppInstalledDTO>> getAppInstalledDetail(
            @Path("kid") final String kid, @Path("terminal") final String terminal,
            @Path("app") final String app);

}
