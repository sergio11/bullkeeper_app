package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ScheduledBlockDTO;
import sanchez.sanchez.sergio.data.net.services.IScheduledBlockService;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import sanchez.sanchez.sergio.domain.repository.IScheduledBlockRepository;

/**
 * Scheduled Block Repository Impl
 */
public final class ScheduledBlockRepositoryImpl implements IScheduledBlockRepository {

    /**
     * Data Mapper
     */
    private final AbstractDataMapper<ScheduledBlockDTO, ScheduledBlockEntity> scheduledBlockDataMapper;
    private final IScheduledBlockService scheduledBlockService;

    /**
     *
     * @param scheduledBlockDataMapper
     * @param scheduledBlockService
     */
    public ScheduledBlockRepositoryImpl(final AbstractDataMapper<ScheduledBlockDTO, ScheduledBlockEntity> scheduledBlockDataMapper,
                                        final IScheduledBlockService scheduledBlockService) {
        this.scheduledBlockDataMapper = scheduledBlockDataMapper;
        this.scheduledBlockService = scheduledBlockService;
    }

    /**
     * Get Scheduled Blok By Child
     * @param childId
     * @return
     */
    @Override
    public Observable<List<ScheduledBlockEntity>> getScheduledBlockByChildId(final String childId) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");

        return scheduledBlockService.getScheduledBlockByChildId(childId).map(response ->
                response != null && response.getData() != null ? response.getData() : null)
                .map(scheduledBlockDataMapper::transform);
    }

    /**
     * Delete Scheduled Block By Id
     * @param childId
     * @param blockId
     * @return
     */
    @Override
    public Observable<String> deleteScheduledBlockById(final String childId, final String blockId) {
        Preconditions.checkNotNull(childId, "Child Id can not be empty");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(blockId, "Block Id can not be empty");
        Preconditions.checkState(!blockId.isEmpty(), "Block Id can not be empty");

        return scheduledBlockService.deleteScheduledBlock(childId, blockId)
                .map(response -> response != null && response.getData() != null ? response.getData(): null);
    }
}
