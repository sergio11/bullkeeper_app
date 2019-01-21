package sanchez.sanchez.sergio.domain.repository;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;
import sanchez.sanchez.sergio.domain.models.FunTimeScheduledEntity;

/**
 * Fun Time Repository
 */
public interface IFunTimeRepository {


    /**
     * Get Fun Time Scheduled By Kid
     * @param kid
     * @param terminal
     * @return
     */
    Observable<FunTimeScheduledEntity> getFunTimeScheduled(
            final String kid, final String terminal);


    /**
     * Save Fun Time Day Scheduled
     * @param kid
     * @param terminal
     * @param day
     * @param enabled
     * @param totalHours
     * @return
     */
    Observable<DayScheduledEntity> saveFunTimeDayScheduled(
            final String kid, final String terminal, final String day,
            final boolean enabled, final int totalHours);

    /**
     * Get Fun Time Day Scheduled By Kid
     * @param kid
     * @param terminal
     * @param day
     * @return
     */
    Observable<DayScheduledEntity> getFunTimeDayScheduled(
            final String kid, final String terminal, final String day);

    /**
     * Save Fun Time Scheduled
     * @param kid
     * @param terminal
     * @param funTimeScheduledEntity
     * @return
     */
    Observable<FunTimeScheduledEntity> saveFunTimeScheduled(
            final String kid, final String terminal,
            final FunTimeScheduledEntity funTimeScheduledEntity
    );
}
