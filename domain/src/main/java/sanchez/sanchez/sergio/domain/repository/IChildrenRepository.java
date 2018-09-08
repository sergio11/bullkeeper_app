package sanchez.sanchez.sergio.domain.repository;

import java.util.Date;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Children Repository
 */
public interface IChildrenRepository {

    /**
     * Get Son By Id
     * @param sonId
     * @return
     */
    Observable<SonEntity> getSonById(final String sonId);

    /**
     * Add Son To Self Parent Interact
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param school
     * @return
     */
    Observable<SonEntity> addSonToSelfParentInteract(final String firstName, final String lastName,
                                                     final Date birthdate, final String school);

    /**
     * Save Son Information
     * @param identity
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param school
     * @return
     */
    Observable<SonEntity> saveSonInformation(final String identity, final String firstName,
                                             final String lastName, final Date birthdate, final String school);


}
