package sanchez.sanchez.sergio.domain.repository;

import io.reactivex.Observable;
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
