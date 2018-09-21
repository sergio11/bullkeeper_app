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

}
