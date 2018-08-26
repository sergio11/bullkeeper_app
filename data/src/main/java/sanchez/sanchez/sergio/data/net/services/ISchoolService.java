package sanchez.sanchez.sergio.data.net.services;

public interface ISchoolService {


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
