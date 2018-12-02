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
     * @param kid
     * @return
     */
    Observable<SupervisedChildrenEntity> getSupervisedChildrenConfirmedDetail(final String kid);


    /**
     * Get Supervised Children No Confirmed
     * @return
     */
     Observable<List<SupervisedChildrenEntity>> getSupervisedChildrenNoConfirmed();

    /**
     * Get Supervised Children No Confirmed Detail
     * @param kid
     * @return
     */
    Observable<SupervisedChildrenEntity> getSupervisedChildrenNoConfirmedDetail(final String kid);

    /**
     * Accept Supervised Children No Confirmed
     * @param kid
     * @return
     */
    Observable<String> acceptSupervisedChildrenNoConfirmed(final String kid);

}
