package sanchez.sanchez.sergio.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Parent Repository
 */
public interface IParentRepository {

    /**
     * Get Self Children
     * @return
     */
    Observable<List<SonEntity>> getSelfChildren();

    /**
     * Get Parent Self Information
     * @return
     */
    Observable<ParentEntity> getParentSelfInformation();

    /**
     * Update Self Information
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param email
     * @param telephone
     * @return
     */
    Observable<ParentEntity> updateSelfInformation(final String firstName, final String lastName,
                                                   final String birthdate, final String email, final String telephone);


    /**
     * Upload Profile Image
     * @return
     */
    Observable<ImageEntity> uploadProfileImage(final String profileImageUri);


}
