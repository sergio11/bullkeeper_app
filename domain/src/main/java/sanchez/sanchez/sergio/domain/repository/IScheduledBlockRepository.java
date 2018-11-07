package sanchez.sanchez.sergio.domain.repository;

import org.joda.time.LocalTime;
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


    /**
     * Delete All Scheduled Block By Child Id
     * @param childId
     * @return
     */
    Observable<String> deleteAllScheduledBlockByChildId(final String childId);


    /**
     * Save Scheduled Block
     * @param identity
     * @param name
     * @param enable
     * @param startAt
     * @param endAt
     * @param weeklyFrequency
     * @param recurringWeeklyEnabled
     * @param childId
     * @return
     */
    Observable<ScheduledBlockEntity> saveScheduledBlock(final String identity, final String name, final boolean enable,
                                                        final LocalTime startAt, final LocalTime endAt, final int[] weeklyFrequency,
                                                        final boolean recurringWeeklyEnabled, final String childId);
}
