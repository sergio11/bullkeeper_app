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
import sanchez.sanchez.sergio.data.net.models.request.UpdateGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.ChildrenOfSelfGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.GuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.data.net.services.IGuardiansService;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.models.KidEntity;
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
     * Kid Data Mapper
     */
    private final AbstractDataMapper<KidDTO, KidEntity> kidDataMapper;

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
     *
     * @param guardianService
     */
    @Inject
    public GuardianRepositoryImpl(final IGuardiansService guardianService,
                                  final AbstractDataMapper<KidDTO, KidEntity> kidDataMapper,
                                  final AbstractDataMapper<GuardianDTO, GuardianEntity> guardianDataMapper,
                                  final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper,
                                  final AbstractDataMapper<ChildrenOfSelfGuardianDTO, ChildrenOfSelfGuardianEntity>
                                    childrenOfSelfGuardianDataMapper) {
        this.guardianService = guardianService;
        this.kidDataMapper = kidDataMapper;
        this.guardianDataMapper = guardianDataMapper;
        this.imageDataMapper = imageDataMapper;
        this.childrenOfSelfGuardianDataMapper = childrenOfSelfGuardianDataMapper;
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
     * @param email
     * @param telephone
     * @return
     */
    @Override
    public Observable<GuardianEntity> updateSelfInformation(final String firstName, final String lastName, final String birthdate,
                                                            final String email, final String telephone,
                                                            final boolean visible) {
        return guardianService.updateSelfParent(new UpdateGuardianDTO(firstName, lastName, birthdate, email, telephone, visible))
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


}
