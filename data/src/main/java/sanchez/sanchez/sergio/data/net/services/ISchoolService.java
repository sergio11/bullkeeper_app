package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.request.AddSchoolDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;

/**
 * School Service
 */
public interface ISchoolService {

    /**
     * Find Schools
     * @param name
     * @return
     */
    @GET("schools/")
    Observable<APIResponse<List<SchoolDTO>>> findSchools(final @Query("name") String name);

    /**
     * Get Total Schools
     * @return
     */
    @GET("schools/count")
    Observable<APIResponse<Integer>> getTotalSchools();

    /**
     * Create School
     * @param addSchoolDTO
     * @return
     */
    @POST("schools/")
    Observable<APIResponse<SchoolDTO>> createSchool(final @Body AddSchoolDTO addSchoolDTO);

}
