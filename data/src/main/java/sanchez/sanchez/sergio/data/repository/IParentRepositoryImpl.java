package sanchez.sanchez.sergio.data.repository;

import javax.inject.Inject;
import sanchez.sanchez.sergio.data.net.services.IParentsService;
import sanchez.sanchez.sergio.domain.repository.IParentRepository;

/**
 * Parent Repository
 */
public final class IParentRepositoryImpl implements IParentRepository {

    private final IParentsService parentsService;

    /**
     *
     * @param parentsService
     */
    @Inject
    public IParentRepositoryImpl(final IParentsService parentsService) {
        this.parentsService = parentsService;
    }
}
