package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveDayScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveFunTimeScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.response.DayScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.response.FunTimeScheduledDTO;
import sanchez.sanchez.sergio.data.net.services.IFunTimeService;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;
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
     * Day Scheduled Entity Mapper
     */
    private final AbstractDataMapper<DayScheduledDTO, DayScheduledEntity>
            dayScheduledEntityAbstractDataMapper;

    /**
     * @param funTimeService
     * @param funTimeScheduledEntityAbstractDataMapper
     * @param saveFunTimeScheduledDTOAbstractDataMapper
     * @param dayScheduledEntityAbstractDataMapper
     */
    @Inject
    public FunTimeRepositoryImpl(
            final IFunTimeService funTimeService,
            final AbstractDataMapper<FunTimeScheduledDTO, FunTimeScheduledEntity>
                    funTimeScheduledEntityAbstractDataMapper,
            final AbstractDataMapper<FunTimeScheduledEntity, SaveFunTimeScheduledDTO>
                    saveFunTimeScheduledDTOAbstractDataMapper,
            final AbstractDataMapper<DayScheduledDTO, DayScheduledEntity>
                    dayScheduledEntityAbstractDataMapper) {
        this.funTimeService = funTimeService;
        this.funTimeScheduledEntityAbstractDataMapper = funTimeScheduledEntityAbstractDataMapper;
        this.saveFunTimeScheduledDTOAbstractDataMapper = saveFunTimeScheduledDTOAbstractDataMapper;
        this.dayScheduledEntityAbstractDataMapper = dayScheduledEntityAbstractDataMapper;
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
     * Save Fun Time Day Scheduled
     * @param kid
     * @param terminal
     * @param day
     * @param enabled
     * @param totalHours
     * @return
     */
    @Override
    public Observable<DayScheduledEntity> saveFunTimeDayScheduled(final String kid, final String terminal,
                                                                  final String day, final boolean enabled,
                                                                  final int totalHours) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(day, "Day can not be null");
        Preconditions.checkState(!day.isEmpty(), "Day can not be empty");

        return funTimeService.saveDayScheduled(kid, terminal,
                day, new SaveDayScheduledDTO(day, enabled, totalHours))
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(dayScheduledEntityAbstractDataMapper::transform);
    }

    /**
     *
     * @param kid
     * @param terminal
     * @param day
     * @return
     */
    @Override
    public Observable<DayScheduledEntity> getFunTimeDayScheduled(final String kid,
                                                                 final String terminal,
                                                                 final String day) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(day, "Day can not be null");
        Preconditions.checkState(!day.isEmpty(), "Day can not be empty");

        return funTimeService.getDayScheduled(kid, terminal, day)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(dayScheduledEntityAbstractDataMapper::transform);
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
