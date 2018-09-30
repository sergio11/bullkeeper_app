package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;

/**
 * School Repository
 */
public interface ISchoolRepository {

    /**
     * Search School
     * @param query
     * @return
     */
    Observable<List<SchoolEntity>> searchSchool(final String query);

    /**
     * Get Total Schools
     * @return
     */
    Observable<Integer> getTotalSchools();

    /**
     * Create School
     * @param name
     * @param residence
     * @param province
     * @param latitude
     * @param longitude
     * @param tfno
     * @param email
     * @return
     */
    Observable<SchoolEntity> createSchool(final String name, final String residence, final String province,
                                          final Double latitude, final Double longitude, final String tfno,
                                          final String email);


}
