package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import java.io.File;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.RegisterSonDTO;
import sanchez.sanchez.sergio.data.net.models.request.UpdateSonDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.DimensionsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.SentimentAnalysisStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaActivityStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.services.IChildrenService;
import sanchez.sanchez.sergio.domain.models.AlertsStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
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
    private final AbstractDataMapper<DimensionsStatisticsDTO.DimensionDTO, DimensionEntity> dimensionDataMapper;
    private final AbstractDataMapper<SocialMediaActivityStatisticsDTO, SocialMediaActivityStatisticsEntity> socialMediaStatisticsDataMapper;
    private final AbstractDataMapper<SentimentAnalysisStatisticsDTO, SentimentAnalysisStatisticsEntity> sentimentAnalysisStatisticsDataMapper;
    private final AbstractDataMapper<AlertsStatisticsDTO, AlertsStatisticsEntity> alertsStatisticsDataMapper;
    /**
     * @param childrenService
     * @param sonDataMapper
     * @param imageDataMapper
     * @param dimensionDataMapper
     * @param socialMediaStatisticsDataMapper
     * @param sentimentAnalysisStatisticsDataMapper
     */
    public ChildrenRepositoryImpl(final IChildrenService childrenService,
                                  final AbstractDataMapper<SonDTO, SonEntity> sonDataMapper,
                                  final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper,
                                  final AbstractDataMapper<DimensionsStatisticsDTO.DimensionDTO, DimensionEntity> dimensionDataMapper,
                                  final AbstractDataMapper<SocialMediaActivityStatisticsDTO, SocialMediaActivityStatisticsEntity> socialMediaStatisticsDataMapper,
                                  final AbstractDataMapper<SentimentAnalysisStatisticsDTO, SentimentAnalysisStatisticsEntity> sentimentAnalysisStatisticsDataMapper,
                                  final AbstractDataMapper<AlertsStatisticsDTO, AlertsStatisticsEntity> alertsStatisticsDataMapper) {
        this.childrenService = childrenService;
        this.sonDataMapper = sonDataMapper;
        this.imageDataMapper = imageDataMapper;
        this.dimensionDataMapper = dimensionDataMapper;
        this.socialMediaStatisticsDataMapper = socialMediaStatisticsDataMapper;
        this.sentimentAnalysisStatisticsDataMapper = sentimentAnalysisStatisticsDataMapper;
        this.alertsStatisticsDataMapper = alertsStatisticsDataMapper;
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

    /**
     * Get Dimensions Statistics By Child
     * @param sonId
     * @param  daysAgo
     * @return
     */
    @Override
    public Observable<List<DimensionEntity>> getDimensionsStatisticsByChild(final String sonId, final int daysAgo) {
        Preconditions.checkNotNull(sonId, "Son Id can not be null");
        Preconditions.checkState(!sonId.isEmpty(), "Son Id can not be null");
        Preconditions.checkState(daysAgo > 0, "Days ago must be grater than 0");

        return childrenService.getDimensionsStatistics(sonId, daysAgo)
                .map(response -> response != null && response.getData() != null ?
                    response.getData() : null)
                .map(dimensionsStatisticsDTO ->
                        dimensionDataMapper.transform(dimensionsStatisticsDTO.getDimensions()));
    }

    /**
     * Get Social Media Activity Statistics
     * @param kidIdentity
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<SocialMediaActivityStatisticsEntity> getSocialMediaActivityStatistics(
            final String kidIdentity, final int daysAgo) {
        Preconditions.checkNotNull(kidIdentity, "Kid Identity can not be null");
        Preconditions.checkState(!kidIdentity.isEmpty(), "Kid Identity can not be empty");
        Preconditions.checkState(daysAgo > 0, "Days ago must be greater than 0");

        return childrenService.getSocialMediaActivityStatistics(kidIdentity, daysAgo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData() : null)
                .map(socialMediaStatisticsDataMapper::transform);
    }

    /**
     * Get Sentiment Analysis Statistics
     * @param kidIdentity
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<SentimentAnalysisStatisticsEntity> getSentimentAnalysisStatistics(final String kidIdentity, final int daysAgo) {
        Preconditions.checkNotNull(kidIdentity, "Kid Identity can not be null");
        Preconditions.checkState(!kidIdentity.isEmpty(), "Kid Identity can not be empty");
        Preconditions.checkState(daysAgo > 0, "Days ago must be greater than 0");

        return childrenService.getSentimentAnalysisStatistics(kidIdentity, daysAgo)
                .map(response -> response != null &&
                    response.getData() != null ? response.getData(): null)
                .map(sentimentAnalysisStatisticsDataMapper::transform);
    }

    /**
     * Get Alerts Statistics
     * @param kidIdentity
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<AlertsStatisticsEntity> getAlertsStatistics(String kidIdentity, int daysAgo) {
        Preconditions.checkNotNull(kidIdentity, "Kid Identity can not be null");
        Preconditions.checkState(!kidIdentity.isEmpty(), "Kid identity can not be empty");
        Preconditions.checkState(daysAgo > 0, "Days ago must be greater than 0");

        return childrenService.getAlertsStatistics(new String[] { kidIdentity }, daysAgo)
                .map(response -> response != null && response.getData() != null ?
                    response.getData() : null)
                .map(alertsStatisticsDataMapper::transform);
    }
}
