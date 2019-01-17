package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveFunTimeScheduledDTO;
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
     * Save Fun Time Scheduled Data Mapper
     */
    private final AbstractDataMapper<FunTimeScheduledEntity, SaveFunTimeScheduledDTO>
            saveFunTimeScheduledDTOAbstractDataMapper;

    /**
     * @param funTimeService
     * @param funTimeScheduledEntityAbstractDataMapper
     * @param saveFunTimeScheduledDTOAbstractDataMapper
     */
    @Inject
    public FunTimeRepositoryImpl(
            final IFunTimeService funTimeService,
            final AbstractDataMapper<FunTimeScheduledDTO, FunTimeScheduledEntity>
                    funTimeScheduledEntityAbstractDataMapper,
            final AbstractDataMapper<FunTimeScheduledEntity, SaveFunTimeScheduledDTO>
                    saveFunTimeScheduledDTOAbstractDataMapper) {
        this.funTimeService = funTimeService;
        this.funTimeScheduledEntityAbstractDataMapper = funTimeScheduledEntityAbstractDataMapper;
        this.saveFunTimeScheduledDTOAbstractDataMapper = saveFunTimeScheduledDTOAbstractDataMapper;
    }

    /**
     * Get Fun Time Scheduled By Kid
     * @param kid
     * @return
     */
    @Override
    public Observable<FunTimeScheduledEntity> getFunTimeScheduled(final String kid, final String terminal) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        return funTimeService.getFunTimeScheduled(kid, terminal)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(funTimeScheduledEntityAbstractDataMapper::transform);
    }

    /**
     * Save Fun Time Scheduled
     * @param kid
     * @param terminal
     * @param funTimeScheduledEntity
     * @return
     */
    @Override
    public Observable<FunTimeScheduledEntity> saveFunTimeScheduled(
            final String kid, final String terminal,
            final FunTimeScheduledEntity funTimeScheduledEntity) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");

        return funTimeService.saveFunTimeScheduled(kid, terminal,
                saveFunTimeScheduledDTOAbstractDataMapper.transform(funTimeScheduledEntity))
                    .map(response -> response != null && response.getData() != null ?
                            response.getData(): null)
                    .map(funTimeScheduledEntityAbstractDataMapper::transform);


    }
}
