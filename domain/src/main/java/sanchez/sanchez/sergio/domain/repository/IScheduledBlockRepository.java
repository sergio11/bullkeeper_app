package sanchez.sanchez.sergio.domain.repository;

import org.joda.time.LocalTime;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockStatusEntity;

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
     * Get Scheduled Block Details
     * @param childId
     * @param blockId
     * @return
     */
    Observable<ScheduledBlockEntity> getScheduledBlockDetail(final String childId, final String blockId);


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


    /**
     * Save Scheduled Block Status
     * @param childId
     * @param saveScheduledStatus
     * @return
     */
    Observable<String> saveScheduledBlockStatus(final String childId,
                                                final List<ScheduledBlockStatusEntity> saveScheduledStatus);


    /**
     * Upload Image
     * @param childId
     * @param blockId
     * @param imageURL
     * @return
     */
    Observable<ImageEntity> uploadImage(final String childId, final String blockId, String imageURL);
}
