package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;

/**
 * Scheduled Block Repository
 */
public interface IScheduledBlockRepository {

    /**
     * Get Scheduled Block By Child Id
     * @param childId
     * @return
     */
    Observable<List<ScheduledBlockEntity>> getScheduledBlockByChildId(final String childId);

    /**
     * Delete Scheduled Block By Id
     * @param childId
     * @param blockId
     * @return
     */
    Observable<String> deleteScheduledBlockById(final String childId, final String blockId);
}
