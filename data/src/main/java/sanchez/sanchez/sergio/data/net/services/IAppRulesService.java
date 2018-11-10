package sanchez.sanchez.sergio.data.net.services;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.request.AppInstalledRuleDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDTO;

/**
 * App Rules Service
 */
public interface IAppRulesService {


    /**
     * Get App Installed
     * @param sonId
     * @param terminalId
     * @return
     */
    @GET("children/{sonId}/terminal/{terminalId}/apps")
    Observable<APIResponse<List<AppInstalledDTO>>> getAppInstalledByChild(
            @Path("sonId") final String sonId, @Path("terminalId") final String terminalId);

    /**
     * Update App Installed Rules By Child
     * @param sonId
     * @param terminalId
     * @return
     */
    @POST("children/{sonId}/terminal/{terminal}/apps/rules")
    Observable<APIResponse<String>> updateAppInstalledRulesByChild(
            @Path("sonId") final String sonId, @Path("terminalId") final String terminalId,
            final @Body List<AppInstalledRuleDTO> appInstalledRuleDTOs);
}
