package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.FunTimeScheduledDTO;
import sanchez.sanchez.sergio.data.net.services.IFunTimeService;
import sanchez.sanchez.sergio.domain.models.FunTimeScheduledEntity;
import sanchez.sanchez.sergio.domain.repository.IFunTimeRepository;

/**
 * Fun Time Repository
 */
public final class FunTimeRepositoryImpl implements IFunTimeRepository {

    /**
     * Fun Time Service
     */
    private final IFunTimeService funTimeService;

    /**
     * Fun Time Scheduled Data Mapper
     */
    private final AbstractDataMapper<FunTimeScheduledDTO, FunTimeScheduledEntity> funTimeScheduledEntityAbstractDataMapper;

    /**
     * @param funTimeService
     * @param funTimeScheduledEntityAbstractDataMapper
     */
    @Inject
    public FunTimeRepositoryImpl(
            final IFunTimeService funTimeService,
            final AbstractDataMapper<FunTimeScheduledDTO, FunTimeScheduledEntity> funTimeScheduledEntityAbstractDataMapper) {
        this.funTimeService = funTimeService;
        this.funTimeScheduledEntityAbstractDataMapper = funTimeScheduledEntityAbstractDataMapper;
    }

    /**
     * Get Fun Time Scheduled By Kid
     * @param kid
     * @return
     */
    @Override
    public Observable<FunTimeScheduledEntity> getFunTimeScheduledByKid(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        return funTimeService.getFunTimeScheduled(kid)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(funTimeScheduledEntityAbstractDataMapper::transform);
    }
}
