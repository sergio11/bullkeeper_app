package sanchez.sanchez.sergio.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;

/**
 * Supervised Children Repository
 */
public interface ISupervisedChildrenRepository {

    /**
     * Delete Supervised Children No Confirmed
     */
    Observable<String> deleteSupervisedChildrenNoConfirmed();

    /**
     * Delete Supervised Children No Confirmed
     */
    Observable<String> deleteSupervisedChildrenNoConfirmed(final String kid);

    /**
     * Delete Supervised Children Confirmed
     * @return
     */
    Observable<String> deleteSupervisedChildrenConfirmed(final String kid);

    /**
     * Get Supervised Children Confirmed Detail
     * @return
     */
    Observable<SupervisedChildrenEntity> getSupervisedChildrenConfirmedDetail(final String kid);


    /**
     * Get Supervised Children No Confirmed
     * @return
     */
     Observable<List<SupervisedChildrenEntity>> getSupervisedChildrenNoConfirmed();

}
