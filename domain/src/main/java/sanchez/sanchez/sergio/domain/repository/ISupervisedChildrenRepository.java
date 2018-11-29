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
     * Delete Supervised Children Confirmed Interact
     * @return
     */
    Observable<String> deleteSupervisedChildrenConfirmedInteract();

    /**
     * Get Supervised Children Confirmed Detail
     * @return
     */
    Observable<SupervisedChildrenEntity> getSupervisedChildrenConfirmedDetail(final String kid);


    /**
     *
     * @return
     */
     Observable<List<SupervisedChildrenEntity>> getSupervisedChildrenNoConfirmed();

}
