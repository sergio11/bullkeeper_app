package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.Date;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.RegisterSonDTO;
import sanchez.sanchez.sergio.data.net.models.request.UpdateSonDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.services.IChildrenService;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;

/**
 * Children Repository Impl
 */
public final class ChildrenRepositoryImpl implements IChildrenRepository {

    private final IChildrenService childrenService;
    private final AbstractDataMapper<SonDTO, SonEntity> sonDataMapper;

    /**
     *
     * @param childrenService
     */
    public ChildrenRepositoryImpl(final IChildrenService childrenService,
                                  final AbstractDataMapper<SonDTO, SonEntity> sonDataMapper) {
        this.childrenService = childrenService;
        this.sonDataMapper = sonDataMapper;
    }

    /**
     * Get Son By Id
     * @param sonId
     * @return
     */
    @Override
    public Observable<SonEntity> getSonById(String sonId) {
        Preconditions.checkNotNull(sonId, "Son Id can not be null");
        Preconditions.checkState(!sonId.isEmpty(), "Son Id can not be empty");
        return childrenService.getSonById(sonId).map(response ->
            response != null && response.getData() != null ? response.getData() : null)
                .map(sonDataMapper::transform);
    }

    /**
     * Add Son To Self Parent Interact
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param school
     * @return
     */
    @Override
    public Observable<SonEntity> addSonToSelfParentInteract(final String firstName, final String lastName,
                                                            final Date birthdate, final String school) {
        Preconditions.checkNotNull(firstName, "Firstname can not be null");
        Preconditions.checkNotNull(lastName, "Lastname can not be null");
        Preconditions.checkNotNull(birthdate, "Birthdate can not be null");
        Preconditions.checkNotNull(school, "School can not be null");

        return childrenService.addSonToSelfParent(new RegisterSonDTO(firstName, lastName, birthdate, school))
                .map(response -> response != null && response.getData() != null ? response.getData() : null)
                .map(sonDataMapper::transform);
    }

    /**
     * Save Son Information
     * @param identity
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param school
     * @return
     */
    @Override
    public Observable<SonEntity> saveSonInformation(final String identity, final String firstName,
                                                    final String lastName, final Date birthdate,
                                                    final String school) {

        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkNotNull(firstName, "First name can not be null");
        Preconditions.checkNotNull(lastName, "Last name can not be null");
        Preconditions.checkNotNull(birthdate, "Birthdate can not be null");
        Preconditions.checkNotNull(school, "School can not be null");

        return childrenService.saveSonInformation(new UpdateSonDTO(identity, firstName, lastName, birthdate, school))
                .map(response -> response != null && response.getData() != null ? response.getData() : null)
                .map(sonDataMapper::transform);
    }
}
