package sanchez.sanchez.sergio.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.services.IParentsService;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IParentRepository;

/**
 * Parent Repository
 */
public final class ParentRepositoryImpl implements IParentRepository {

    /**
     * Parent Service
     */
    private final IParentsService parentsService;

    /**
     * Son Data Mapper
     */
    private final AbstractDataMapper<SonDTO, SonEntity> sonDataMapper;

    /**
     *
     * @param parentsService
     */
    @Inject
    public ParentRepositoryImpl(final IParentsService parentsService, final AbstractDataMapper<SonDTO, SonEntity> sonDataMapper) {
        this.parentsService = parentsService;
        this.sonDataMapper = sonDataMapper;
    }

    /**
     * Get Self Children
     * @return
     */
    @Override
    public Observable<List<SonEntity>> getSelfChildren() {
        return parentsService.getSelfChildren().map(listAPIResponse -> listAPIResponse != null &&
            listAPIResponse.getData() != null ? listAPIResponse.getData() : null)
                .map(sonDataMapper::transform);
    }
}
