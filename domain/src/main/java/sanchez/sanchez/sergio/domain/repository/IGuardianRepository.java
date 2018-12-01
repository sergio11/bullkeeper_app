package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;

/**
 * Guardian Repository
 */
public interface IGuardianRepository {

    /**
     * Get Self Children
     * @return
     */
    Observable<ChildrenOfSelfGuardianEntity> getSelfChildren();

    /**
     * Get Guardian Self Information
     * @return
     */
    Observable<GuardianEntity> getGuardianSelfInformation();

    /**
     * Update Self Information
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param email
     * @param telephone
     * @return
     */
    Observable<GuardianEntity> updateSelfInformation(final String firstName, final String lastName,
                                                     final String birthdate, final String email, final String telephone);


    /**
     * Upload Profile Image
     * @return
     */
    Observable<ImageEntity> uploadProfileImage(final String profileImageUri);

    /**
     * Delete Self Account
     * @return
     */
    Observable<String> deleteSelfAccount();

    /**
     * Search
     * @param text
     * @return
     */
    Observable<List<GuardianEntity>> search(final String text);

}
