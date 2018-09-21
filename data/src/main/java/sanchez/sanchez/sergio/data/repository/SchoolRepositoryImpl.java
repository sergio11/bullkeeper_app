package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
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
}
