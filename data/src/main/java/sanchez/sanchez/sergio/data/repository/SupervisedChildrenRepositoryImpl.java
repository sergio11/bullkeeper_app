package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SupervisedChildrenDTO;
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

    /**
     * Supevised Children Entity
     */
    private final AbstractDataMapper<SupervisedChildrenDTO, SupervisedChildrenEntity>
            supervisedChildrenEntityAbstractDataMapper;

    /**
     * @param supervisedChildrenService
     * @param supervisedChildrenEntityAbstractDataMapper
     */
    public SupervisedChildrenRepositoryImpl(final ISupervisedChildrenService supervisedChildrenService,
                                            final AbstractDataMapper<SupervisedChildrenDTO, SupervisedChildrenEntity> supervisedChildrenEntityAbstractDataMapper) {
        this.supervisedChildrenService = supervisedChildrenService;
        this.supervisedChildrenEntityAbstractDataMapper = supervisedChildrenEntityAbstractDataMapper;
    }

    /**
     * Delete Supervised Children No Confirmed
     * @return
     */
    @Override
    public Observable<String> deleteSupervisedChildrenNoConfirmed() {
        return supervisedChildrenService.deleteSupervisedChildrenNoConfirmed()
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Delete Supervised Children No Confirmed
     * @param kid
     * @return
     */
    @Override
    public Observable<String> deleteSupervisedChildrenNoConfirmed(String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        return supervisedChildrenService.deleteSupervisedChildrenNoConfirmed(kid)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Delete Supervised Children Confirmed Iteract
     * @return
     */
    @Override
    public Observable<String> deleteSupervisedChildrenConfirmed(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        return supervisedChildrenService.deleteSupervisedChildrenConfirmed(kid)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null);
    }

    /**
     * Get Supervised Children Confirmed Detail
     * @param kid
     * @return
     */
    @Override
    public Observable<SupervisedChildrenEntity> getSupervisedChildrenConfirmedDetail(String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        return supervisedChildrenService.getSupervisedChildrenConfirmedDetail(kid)
                .map(response -> response != null
                        && response.getData() != null ? response.getData(): null)
                .map(supervisedChildrenEntityAbstractDataMapper::transform);
    }

    /**
     * Get Supervised Children No Confirmed
     * @return
     */
    @Override
    public Observable<List<SupervisedChildrenEntity>> getSupervisedChildrenNoConfirmed() {
        return supervisedChildrenService.getSupervisedChildrenNoConfirmed()
                .map(response -> response != null
                        && response.getData() != null ? response.getData(): null)
                .map(supervisedChildrenEntityAbstractDataMapper::transform);
    }
}
