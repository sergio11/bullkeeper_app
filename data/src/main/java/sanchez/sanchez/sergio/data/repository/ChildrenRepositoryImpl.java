package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.RegisterSonDTO;
import sanchez.sanchez.sergio.data.net.models.request.UpdateSonDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.services.IChildrenService;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;
import timber.log.Timber;

/**
 * Children Repository Impl
 */
public final class ChildrenRepositoryImpl implements IChildrenRepository {

    private final IChildrenService childrenService;
    private final AbstractDataMapper<SonDTO, SonEntity> sonDataMapper;
    private final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper;

    /**
     *
     * @param childrenService
     */
    public ChildrenRepositoryImpl(final IChildrenService childrenService,
                                  final AbstractDataMapper<SonDTO, SonEntity> sonDataMapper,
                                  final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper) {
        this.childrenService = childrenService;
        this.sonDataMapper = sonDataMapper;
        this.imageDataMapper = imageDataMapper;
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
                                                            final String birthdate, final String school) {
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
                                                    final String lastName, final String birthdate,
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

    /**
     * Upload Profile Image
     * @param profileImageUri
     * @return
     */
    @Override
    public Observable<ImageEntity> uploadProfileImage(final String sonId, final String profileImageUri) {
        Preconditions.checkNotNull(sonId, "So Id can not be null");
        Preconditions.checkNotNull(profileImageUri, "Profile Image Uri can not be null");
        final File profileImageFile = new File(profileImageUri);
        Preconditions.checkState(profileImageFile.exists()
                && profileImageFile.canRead(), "File can not be read");

        // Create Request File
        final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), profileImageFile);
        // Create Request Part
        final MultipartBody.Part requestPart = MultipartBody.Part.createFormData("profile_image", profileImageFile.getName(), requestFile);

        return childrenService.uploadProfileImage(sonId, requestPart).map(imageDTOAPIResponse ->
                imageDTOAPIResponse != null && imageDTOAPIResponse.getData() != null ? imageDTOAPIResponse.getData() : null)
                .map(imageDataMapper::transform).doOnError(throwable -> Timber.e(throwable));
    }
}
