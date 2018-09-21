package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;

/**
 * School Service
 */
public interface ISchoolService {

    @GET("schools/")
    Observable<APIResponse<List<SchoolDTO>>> findSchools(final @Query("name") String name);


    /**
     * // /schools/all/names
     IObservable<APIResponse<IList<SchoolNameDTO>>> AllNames();

     // /schools/
     IObservable<APIResponse<SchoolDTO>> CreateSchool(AddSchoolDTO school);

     // /schools/count
     IObservable<APIResponse<string>> Total();

     // /schools/
     IObservable<APIResponse<IList<SchoolDTO>>> FindSchools(string name);

     */
}
