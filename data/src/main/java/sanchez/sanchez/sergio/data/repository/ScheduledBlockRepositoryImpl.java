package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveScheduledBlockDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveScheduledBlockStatusDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.ScheduledBlockDTO;
import sanchez.sanchez.sergio.data.net.services.IScheduledBlockService;
import sanchez.sanchez.sergio.domain.models.AppAllowedByScheduledEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockStatusEntity;
import sanchez.sanchez.sergio.domain.repository.IScheduledBlockRepository;
import timber.log.Timber;

/**
 * Scheduled Block Repository Impl
 */
public final class ScheduledBlockRepositoryImpl implements IScheduledBlockRepository {

    /**
     * Data Mapper
     */
    private final AbstractDataMapper<ScheduledBlockDTO, ScheduledBlockEntity> scheduledBlockDataMapper;
    private final IScheduledBlockService scheduledBlockService;
    private final AbstractDataMapper<SaveScheduledBlockStatusDTO,
            ScheduledBlockStatusEntity> saveSchedulesBlockStatusMapper;
    private final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper;

    /**
     *
     * @param scheduledBlockDataMapper
     * @param scheduledBlockService
     * @param saveSchedulesBlockStatusMapper
     */
    public ScheduledBlockRepositoryImpl(final AbstractDataMapper<ScheduledBlockDTO, ScheduledBlockEntity> scheduledBlockDataMapper,
                                        final IScheduledBlockService scheduledBlockService,
                                        final AbstractDataMapper<SaveScheduledBlockStatusDTO,
                                                ScheduledBlockStatusEntity> saveSchedulesBlockStatusMapper,
                                        final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper) {
        this.scheduledBlockDataMapper = scheduledBlockDataMapper;
        this.scheduledBlockService = scheduledBlockService;
        this.saveSchedulesBlockStatusMapper = saveSchedulesBlockStatusMapper;
        this.imageDataMapper = imageDataMapper;
    }

    /**
     * Get Scheduled Blok By Child
     * @param childId
     * @return
     */
    @Override
    public Observable<List<ScheduledBlockEntity>> getScheduledBlockByChildId(final String childId) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");

        return scheduledBlockService.getScheduledBlockByChildId(childId).map(response ->
                response != null && response.getData() != null ? response.getData() : null)
                .map(scheduledBlockDataMapper::transform);
    }

    /**
     * Get Scheduled Block Detail
     * @param childId
     * @param blockId
     * @return
     */
    @Override
    public Observable<ScheduledBlockEntity> getScheduledBlockDetail(final String childId, final String blockId) {
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(blockId, "Block Id can not be null");
        Preconditions.checkState(!blockId.isEmpty(), "Block Id can not be empty");

        return scheduledBlockService.getScheduledBlockDetail(childId, blockId)
                .map(response -> response != null && response.getData() != null
                        ? response.getData() : null)
                .map(scheduledBlockDataMapper::transform);

    }

    /**
     * Delete Scheduled Block By Id
     * @param childId
     * @param blockId
     * @return
     */
    @Override
    public Observable<String> deleteScheduledBlockById(final String childId, final String blockId) {
        Preconditions.checkNotNull(childId, "Child Id can not be empty");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(blockId, "Block Id can not be empty");
        Preconditions.checkState(!blockId.isEmpty(), "Block Id can not be empty");

        return scheduledBlockService.deleteScheduledBlock(childId, blockId)
                .map(response -> response != null && response.getData() != null ? response.getData(): null);
    }

    /**
     * Delete All Scheduled Block By Child Id
     * @param childId
     * @return
     */
    @Override
    public Observable<String> deleteAllScheduledBlockByChildId(final String childId) {
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");

        return scheduledBlockService.deleteScheduledBlockByChildId(childId)
                .map(response -> response != null && response.getData() != null ? response.getData(): null);
    }

    /**
     * Save Scheduled Block
     * @param identity
     * @param name
     * @param enable
     * @param startAt
     * @param endAt
     * @param weeklyFrequency
     * @param recurringWeeklyEnabled
     * @param childId
     * @return
     */
    @Override
    public Observable<ScheduledBlockEntity> saveScheduledBlock(final String identity, final String name, final boolean enable,
                                                               final LocalTime startAt, final LocalTime endAt, final int[] weeklyFrequency,
                                                               final boolean recurringWeeklyEnabled, final String childId,
                                                               final String description, final boolean allowCalls,
                                                               final List<AppAllowedByScheduledEntity> appsAllowedList,
                                                               final String geofence) {
        Preconditions.checkNotNull(startAt, "Start At can not be null");
        Preconditions.checkNotNull(endAt, "End at can not be null");
        Preconditions.checkNotNull(weeklyFrequency, "Weekly Frequency can not be null");
        Preconditions.checkState(weeklyFrequency.length == 7, "Weekly Frequency should have 7 elements");
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(appsAllowedList, "Apps Allowed can not be null");

        final DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss.SSS");

        final String startAtTimeString = startAt.toString(fmt);
        final String endAtTimeString = endAt.toString(fmt);

        final List<SaveScheduledBlockDTO.SaveAppAllowedByScheduledDTO> appAllowedByScheduledDTOS = new ArrayList<>();
        for(final AppAllowedByScheduledEntity appAllowedByScheduledEntity: appsAllowedList) {
            final SaveScheduledBlockDTO.SaveAppAllowedByScheduledDTO saveAppAllowedByScheduledDTO =
                    new SaveScheduledBlockDTO.SaveAppAllowedByScheduledDTO(
                            appAllowedByScheduledEntity.getApp().getIdentity(),
                            appAllowedByScheduledEntity.getTerminal().getIdentity()
                    );
            appAllowedByScheduledDTOS.add(saveAppAllowedByScheduledDTO);
        }

        /**
         * Save Scheduled Block
         */
        return scheduledBlockService.saveScheduledBlock(childId, new SaveScheduledBlockDTO(identity == null ?  "" : identity , name, enable, recurringWeeklyEnabled,
                startAtTimeString, endAtTimeString, weeklyFrequency, childId,
                description, allowCalls, appAllowedByScheduledDTOS, geofence))
                    .map(response -> response != null && response.getData() != null ? response.getData(): null)
                    .map(scheduledBlockDataMapper::transform);

    }

    /**
     * Save Scheduled Block Status
     * @param childId
     * @param saveScheduledStatus
     * @return
     */
    @Override
    public Observable<String> saveScheduledBlockStatus(final String childId, final List<ScheduledBlockStatusEntity> saveScheduledStatus) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(saveScheduledStatus, "Save Scheduled Status");

        return scheduledBlockService.saveScheduledBlockStatus(childId,
                saveSchedulesBlockStatusMapper.transformInverse(saveScheduledStatus))
                .map(response -> response != null && response.getData() != null
                        ? response.getData(): null);
    }

    /**
     * Upload Image
     * @param imageURL
     * @return
     */
    @Override
    public Observable<ImageEntity> uploadImage(final String childId, final String blockId, String imageURL) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(blockId, "Child id can not be null");
        Preconditions.checkState(!blockId.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(imageURL, "Child id can not be null");
        Preconditions.checkState(!imageURL.isEmpty(), "Child id can not be empty");


        final File imageFile = new File(imageURL);
        Preconditions.checkState(imageFile.exists()
                && imageFile.canRead(), "File can not be read");

        // Create Request File
        final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),
                imageFile);
        // Create Request Part
        final MultipartBody.Part requestPart = MultipartBody.Part.createFormData("scheduledBlockImage",
                imageFile.getName(), requestFile);

        return scheduledBlockService.uploadScheduledBlockImage(childId, blockId, requestPart)
                .map(imageDTOAPIResponse ->
                imageDTOAPIResponse != null && imageDTOAPIResponse.getData() != null
                        ? imageDTOAPIResponse.getData() : null)
                .map(imageDataMapper::transform).doOnError(throwable -> Timber.e(throwable));
    }
}
