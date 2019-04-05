package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.ChangeUserEmailDTO;
import sanchez.sanchez.sergio.data.net.models.request.ChangeUserPasswordDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveUserPreferenceDTO;
import sanchez.sanchez.sergio.data.net.models.request.UpdateGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.ChildrenOfSelfGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.GuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.UserPreferenceDTO;
import sanchez.sanchez.sergio.data.net.services.IGuardiansService;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.models.RemoveAlertsEveryEnum;
import sanchez.sanchez.sergio.domain.models.UserPreferenceEntity;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;
import timber.log.Timber;

/**
 * Guardian Repository
 */
public final class GuardianRepositoryImpl implements IGuardianRepository {

    /**
     * Guardian Service
     */
    private final IGuardiansService guardianService;

    /**
     * Guardian Data Mapper
     */
    private final AbstractDataMapper<GuardianDTO, GuardianEntity> guardianDataMapper;

    /**
     * Image Data Mapper
     */
    private final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper;

    /**
     * Children Of Self Guardian Data Mapper
     */
    private final AbstractDataMapper<ChildrenOfSelfGuardianDTO, ChildrenOfSelfGuardianEntity>
            childrenOfSelfGuardianDataMapper;


    /**
     * Kid Guardian Data Mapper
     */
    private final AbstractDataMapper<KidGuardianDTO, KidGuardianEntity> kidGuardianEntityAbstractDataMapper;

    /**
     * User Preference Entity Data Mapper
     */
    private final AbstractDataMapper<UserPreferenceDTO, UserPreferenceEntity> userPreferenceEntityAbstractDataMapper;


    /**
     *
     * @param guardianService
     * @param guardianDataMapper
     * @param imageDataMapper
     * @param childrenOfSelfGuardianDataMapper
     * @param kidGuardianEntityAbstractDataMapper
     * @param userPreferenceEntityAbstractDataMapper
     */
    @Inject
    public GuardianRepositoryImpl(final IGuardiansService guardianService,
                                  final AbstractDataMapper<GuardianDTO, GuardianEntity> guardianDataMapper,
                                  final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper,
                                  final AbstractDataMapper<ChildrenOfSelfGuardianDTO, ChildrenOfSelfGuardianEntity>
                                    childrenOfSelfGuardianDataMapper,
                                  final AbstractDataMapper<KidGuardianDTO, KidGuardianEntity> kidGuardianEntityAbstractDataMapper,
                                  final AbstractDataMapper<UserPreferenceDTO, UserPreferenceEntity> userPreferenceEntityAbstractDataMapper) {
        this.guardianService = guardianService;
        this.guardianDataMapper = guardianDataMapper;
        this.imageDataMapper = imageDataMapper;
        this.childrenOfSelfGuardianDataMapper = childrenOfSelfGuardianDataMapper;
        this.kidGuardianEntityAbstractDataMapper = kidGuardianEntityAbstractDataMapper;
        this.userPreferenceEntityAbstractDataMapper = userPreferenceEntityAbstractDataMapper;
    }

    /**
     * Get Self Children
     * @return
     */
    @Override
    public Observable<ChildrenOfSelfGuardianEntity> getSelfChildren() {
        return guardianService.getSelfChildren().map(listAPIResponse -> listAPIResponse != null &&
            listAPIResponse.getData() != null ? listAPIResponse.getData() : null)
                .map(childrenOfSelfGuardianDataMapper::transform);
    }

    /**
     * Get Self Children
     * @param queryText
     * @return
     */
    @Override
    public Observable<ChildrenOfSelfGuardianEntity> getSelfChildren(String queryText) {
        Preconditions.checkNotNull(queryText, "Query text can not be null");
        Preconditions.checkState(!queryText.isEmpty(), "Query text can not be empty");

        return guardianService.getSelfChildren(queryText).map(listAPIResponse -> listAPIResponse != null &&
                listAPIResponse.getData() != null ? listAPIResponse.getData() : null)
                .map(childrenOfSelfGuardianDataMapper::transform);
    }

    /**
     * Get Parent Self Information
     * @return
     */
    @Override
    public Observable<GuardianEntity> getGuardianSelfInformation() {
        return guardianService.getParentSelfInformation()
                .map(listAPIResponse -> listAPIResponse != null &&
                        listAPIResponse.getData() != null ? listAPIResponse.getData() : null)
                .map(guardianDataMapper::transform);
    }

    /**
     * Update Self Information
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param telephone
     * @return
     */
    @Override
    public Observable<GuardianEntity> updateSelfInformation(final String firstName, final String lastName, final String birthdate,
                                                            final String telephone, final boolean visible) {
        return guardianService.updateSelfParent(new UpdateGuardianDTO(firstName, lastName, birthdate, telephone, visible))
                .map(listAPIResponse -> listAPIResponse != null &&
                        listAPIResponse.getData() != null ? listAPIResponse.getData() : null)
                .map(guardianDataMapper::transform);
    }


    /**
     * Upload Profile Image
     * @param profileImageUri
     * @return
     */
    @Override
    public Observable<ImageEntity> uploadProfileImage(final String profileImageUri) {
        Preconditions.checkNotNull(profileImageUri, "Profile Image Uri can not be null");

        final File profileImageFile = new File(profileImageUri);
        Preconditions.checkState(profileImageFile.exists()
                && profileImageFile.canRead(), "File can not be read");

        // Create Request File
        final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), profileImageFile);
        // Create Request Part
        final MultipartBody.Part requestPart = MultipartBody.Part.createFormData("profile_image", profileImageFile.getName(), requestFile);

        return guardianService.uploadProfileImage(requestPart).map(imageDTOAPIResponse ->
                imageDTOAPIResponse != null && imageDTOAPIResponse.getData() != null ? imageDTOAPIResponse.getData() : null)
                .map(imageDataMapper::transform).doOnError(throwable -> Timber.e(throwable));
    }

    /**
     * Delete Self Account
     * @return
     */
    @Override
    public Observable<String> deleteSelfAccount() {
        return guardianService.deleteSelfAccount().map(response ->
                response != null && response.getData() != null ? response.getData() : null);
    }

    /**
     * Search
     * @param text
     * @return
     */
    @Override
    public Observable<List<GuardianEntity>> search(String text) {
        Preconditions.checkNotNull(text, "Text can not be null");
        Preconditions.checkState(!text.isEmpty(), "Text can not be empty");
        return guardianService
                .searchGuardian(text)
                .map(response -> response != null && response.getData() != null
                        ? response.getData(): null)
                .map(guardianDataMapper::transform);
    }

    /**
     *
     * @param currentEmail
     * @param newEmail
     * @return
     */
    @Override
    public Observable<String> changeUserEmail(final String currentEmail, final String newEmail) {
        Preconditions.checkNotNull(currentEmail, "Current Email can not be null");
        Preconditions.checkNotNull(newEmail, "New Email can not be null");
        Preconditions.checkState(!currentEmail.isEmpty(), "Current email can not be empty");
        Preconditions.checkState(!newEmail.isEmpty(), "New Email can not be empty");

        return guardianService.changeUserEmail(new ChangeUserEmailDTO(
                currentEmail, newEmail
        )).map(response -> response != null && response.getData() != null
                ? response.getData(): null);
    }

    /**
     * Change User Password
     * @param newPassword
     * @param confirmNewPassword
     * @return
     */
    @Override
    public Observable<String> changeUserPassword(String newPassword, String confirmNewPassword) {
        Preconditions.checkNotNull(newPassword, "New Password can not be null");
        Preconditions.checkNotNull(confirmNewPassword, "New Password can not be null");
        Preconditions.checkState(!newPassword.isEmpty(), "New Password can not be empty");
        Preconditions.checkState(!confirmNewPassword.isEmpty(), "Confirm new password can not be empty");

        return guardianService.changePassword(new ChangeUserPasswordDTO(
                newPassword, confirmNewPassword
        )).map(response -> response != null && response.getData() != null
                ? response.getData(): null);
    }

    /**
     * Get Supervised Child Confirmed By Id
     * @param kid
     * @return
     */
    @Override
    public Observable<KidGuardianEntity> getSupervisedChildConfirmedById(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");

        return guardianService.getSupervisedChildConfirmedById(kid)
                .map(response -> response != null && response.getData() != null
                        ? response.getData(): null)
                .map(kidGuardianEntityAbstractDataMapper::transform);
    }

    /**
     * Get User Preference
     * @return
     */
    @Override
    public Observable<UserPreferenceEntity> getUserPreference() {
        return guardianService.getPreferences()
                .map(response -> response != null && response.getData() != null
                        ? response.getData(): null)
                .map(userPreferenceEntityAbstractDataMapper::transform);
    }

    /**
     * Save User Preference
     * @param pushNotificationsEnabled
     * @param removeAlertsEvery
     * @return
     */
    @Override
    public Observable<UserPreferenceEntity> saveUserPreference(final Boolean pushNotificationsEnabled, final String removeAlertsEvery) {
        Preconditions.checkNotNull(pushNotificationsEnabled, "Push notification can not be null");
        Preconditions.checkNotNull(removeAlertsEvery, "Remove Alerts can not be null");

        return guardianService.savePreferences(new SaveUserPreferenceDTO(pushNotificationsEnabled, RemoveAlertsEveryEnum.values()[Integer.parseInt(removeAlertsEvery)]))
                .map(response -> response != null && response.getData() != null
                        ? response.getData(): null)
                .map(userPreferenceEntityAbstractDataMapper::transform);
    }

}
