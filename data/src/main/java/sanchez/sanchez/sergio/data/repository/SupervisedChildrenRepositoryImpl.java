package sanchez.sanchez.sergio.data.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.net.services.ISupervisedChildrenService;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;
import sanchez.sanchez.sergio.domain.repository.ISupervisedChildrenRepository;

/**
 * Supervised Children Repository
 */
public final class SupervisedChildrenRepositoryImpl implements ISupervisedChildrenRepository {

    /**
     * Supervised Children Service
     */
    private final ISupervisedChildrenService supervisedChildrenService;

    public SupervisedChildrenRepositoryImpl(ISupervisedChildrenService supervisedChildrenService) {
        this.supervisedChildrenService = supervisedChildrenService;
    }

    /**
     * Delete Supervised Children No Confirmed
     * @return
     */
    @Override
    public Observable<String> deleteSupervisedChildrenNoConfirmed() {
        return null;
    }

    /**
     * Delete Supervised Children Confirmed Iteract
     * @return
     */
    @Override
    public Observable<String> deleteSupervisedChildrenConfirmedInteract() {
        return null;
    }

    /**
     * Get Supervised Children Confirmed Detail
     * @param kid
     * @return
     */
    @Override
    public Observable<SupervisedChildrenEntity> getSupervisedChildrenConfirmedDetail(String kid) {
        return null;
    }

    /**
     * Get Supervised Children No Confirmed
     * @return
     */
    @Override
    public Observable<List<SupervisedChildrenEntity>> getSupervisedChildrenNoConfirmed() {
        return null;
    }
}
