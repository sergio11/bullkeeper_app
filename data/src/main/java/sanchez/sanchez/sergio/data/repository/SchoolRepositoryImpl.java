package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.AddSchoolDTO;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.data.net.services.ISchoolService;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.repository.ISchoolRepository;

/**
 * School Repository Impl
 */
public final class SchoolRepositoryImpl implements ISchoolRepository {

    private final ISchoolService schoolService;
    private final AbstractDataMapper<SchoolDTO, SchoolEntity> schoolDataMapper;

    /**
     * School Repository Impl
     * @param schoolService
     * @param schoolDataMapper
     */
    public SchoolRepositoryImpl(final ISchoolService schoolService, final AbstractDataMapper<SchoolDTO, SchoolEntity> schoolDataMapper) {
        this.schoolService = schoolService;
        this.schoolDataMapper = schoolDataMapper;
    }

    /**
     * Search School
     * @param query
     * @return
     */
    @Override
    public Observable<List<SchoolEntity>> searchSchool(final String query) {
        Preconditions.checkNotNull(query, "Query can not be null");
        Preconditions.checkState(!query.isEmpty(), "Query can not be empty");
        return schoolService.findSchools(query).map(listAPIResponse -> listAPIResponse != null
                && listAPIResponse.getData() != null ? listAPIResponse.getData(): null)
                .map(schoolDataMapper::transform);
    }

    /**
     * Get Total School
     * @return
     */
    @Override
    public Observable<Integer> getTotalSchools() {
        return schoolService.getTotalSchools().map(response -> response != null
                && response.getData() != null ? response.getData(): 0);
    }

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
    @Override
    public Observable<SchoolEntity> createSchool(String name, String residence, String province, Double latitude, Double longitude, String tfno, String email) {
        return schoolService.createSchool(new AddSchoolDTO(name, residence, province, latitude, longitude, tfno, email))
                .map(response -> response != null && response.getData() != null ? response.getData(): null)
                .map(schoolDataMapper::transform);
    }
}
