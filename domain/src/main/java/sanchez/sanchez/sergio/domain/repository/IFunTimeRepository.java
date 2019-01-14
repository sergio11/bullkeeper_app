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
     * @return
     */
    Observable<FunTimeScheduledEntity> getFunTimeScheduledByKid(final String kid);

}
