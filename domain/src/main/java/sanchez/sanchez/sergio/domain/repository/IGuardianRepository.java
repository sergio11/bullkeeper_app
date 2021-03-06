package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.models.UserPreferenceEntity;

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
     * Get Self Children
     * @param queryText
     * @return
     */
    Observable<ChildrenOfSelfGuardianEntity> getSelfChildren(final String queryText);

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
     * @param telephone
     * @param visible
     * @return
     */
    Observable<GuardianEntity> updateSelfInformation(final String firstName, final String lastName,
                                                     final String birthdate, final String telephone,
                                                     final boolean visible);


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

    /**
     * Change User Email
     * @param currentEmail
     * @param newEmail
     * @return
     */
    Observable<String> changeUserEmail(final String currentEmail, final String newEmail);

    /**
     * Change User Password
     * @param newPassword
     * @param confirmNewPassword
     * @return
     */
    Observable<String> changeUserPassword(final String newPassword, final String confirmNewPassword);

    /**
     * Get Supervised Child Confirmed By Id
     * @return
     */
    Observable<KidGuardianEntity> getSupervisedChildConfirmedById(final String kid);

    /**
     * Get User Preference
     * @return
     */
    Observable<UserPreferenceEntity> getUserPreference();

    /**
     * Save User Preference
     * @param pushNotificationsEnabled
     * @param removeAlertsEvery
     * @return
     */
    Observable<UserPreferenceEntity> saveUserPreference(final Boolean pushNotificationsEnabled,
            final String removeAlertsEvery);

}
