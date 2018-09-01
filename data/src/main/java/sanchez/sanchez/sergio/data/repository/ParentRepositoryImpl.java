package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.io.File;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.UpdateParentDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.ParentDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.services.IParentsService;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IParentRepository;
import timber.log.Timber;

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
     * Parent Data Mapper
     */
    private final AbstractDataMapper<ParentDTO, ParentEntity> parentDataMapper;

    /**
     * Image Data Mapper
     */
    private final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper;

    /**
     *
     * @param parentsService
     */
    @Inject
    public ParentRepositoryImpl(final IParentsService parentsService,
                                final AbstractDataMapper<SonDTO, SonEntity> sonDataMapper,
                                final AbstractDataMapper<ParentDTO, ParentEntity> parentDataMapper,
                                final  AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper) {
        this.parentsService = parentsService;
        this.sonDataMapper = sonDataMapper;
        this.parentDataMapper = parentDataMapper;
        this.imageDataMapper = imageDataMapper;
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

    /**
     * Get Parent Self Information
     * @return
     */
    @Override
    public Observable<ParentEntity> getParentSelfInformation() {
        return parentsService.getParentSelfInformation()
                .map(listAPIResponse -> listAPIResponse != null &&
                        listAPIResponse.getData() != null ? listAPIResponse.getData() : null)
                .map(parentDataMapper::transform);
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
    public Observable<ParentEntity> updateSelfInformation(final String firstName, final String lastName, final String birthdate,
                                                          final String email, final String telephone) {
        return parentsService.updateSelfParent(new UpdateParentDTO(firstName, lastName, birthdate, email, telephone))
                .map(listAPIResponse -> listAPIResponse != null &&
                        listAPIResponse.getData() != null ? listAPIResponse.getData() : null)
                .map(parentDataMapper::transform);
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

        return parentsService.uploadProfileImage(requestPart).map(imageDTOAPIResponse ->
                imageDTOAPIResponse != null && imageDTOAPIResponse.getData() != null ? imageDTOAPIResponse.getData() : null)
                .map(imageDataMapper::transform).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable);
                    }
                });
    }
}
