package sanchez.sanchez.sergio.bullkeeper.ui.activity.savescheduledblock;

import com.fernandocejas.arrow.checks.Preconditions;
import org.joda.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.scheduled.DeleteScheduledBlockByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.GetScheduledBlockDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.SaveScheduledBlockInteract;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;

/**
 * Save Scheduled Block Presenter
 */
public final class SaveScheduledBlockPresenter extends SupportPresenter<ISaveScheduledBlockView> {

    /**
     * Args
     */
    public static final String SCHEDULED_BLOCK_IDENTITY_ARG = "SCHEDULED_BLOCK_IDENTITY";
    public static final String SON_IDENTITY_ARG = "SON_IDENTITY_ARG";

    /**
     * Delete Scheduled Block Interact
     */
    private final DeleteScheduledBlockByIdInteract deleteScheduledBlockByIdInteract;

    /**
     * Save Scheduled Block Interact
     */
    private final SaveScheduledBlockInteract saveScheduledBlockInteract;

    /**
     * Get Scheduled Block Detail Interact
     */
    private final GetScheduledBlockDetailInteract getScheduledBlockDetailInteract;


    /**
     * Save Scheduled Block
     * @param deleteScheduledBlockByIdInteract
     * @param saveScheduledBlockInteract
     * @param getScheduledBlockDetailInteract
     */
    @Inject
    public SaveScheduledBlockPresenter(final DeleteScheduledBlockByIdInteract deleteScheduledBlockByIdInteract,
                                       final SaveScheduledBlockInteract saveScheduledBlockInteract,
                                       final GetScheduledBlockDetailInteract getScheduledBlockDetailInteract) {
        this.deleteScheduledBlockByIdInteract = deleteScheduledBlockByIdInteract;
        this.saveScheduledBlockInteract = saveScheduledBlockInteract;
        this.getScheduledBlockDetailInteract = getScheduledBlockDetailInteract;
    }

    /**
     * Delete Scheduled By Id
     * @param childId
     * @param identity
     */
    public void deleteScheduledById(final String childId, final String identity) {
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");


        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.deleting_scheduled_block);

        deleteScheduledBlockByIdInteract.execute(new DeleteScheduledBlockByChildObservable(),
                DeleteScheduledBlockByIdInteract.Params.create(childId, identity));
    }

    /**
     * Save Scheduled Block
     * @param identity
     * @param name
     * @param startAt
     * @param endAt
     * @param weeklyFrequency
     * @param recurringWeeklyEnabled
     * @param currentImage
     */
    public void saveScheduledBlock(final String identity, final String name, final boolean enable, final LocalTime startAt,
                                   final LocalTime endAt, final int[] weeklyFrequency,
                                   final boolean recurringWeeklyEnabled, final String childId,
                                   final String currentImage){


        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.saving_scheduled_block_info);

        saveScheduledBlockInteract.execute(new SaveScheduledBlockObservable(SaveScheduledBlockInteract.SaveScheduledBlockApiErrors.class),
                SaveScheduledBlockInteract.Params.create(identity, name, enable, startAt, endAt,
                            weeklyFrequency, recurringWeeklyEnabled, childId, currentImage));

    }

    /**
     * Load Scheduled Block
     * @param childId
     * @param blockId
     */
    public void loadScheduledBlock(final String childId, final String blockId) {
        Preconditions.checkNotNull(childId, "Child Id can not be empty");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(blockId, "Block Id can not be empty");
        Preconditions.checkState(!blockId.isEmpty(), "Block id can not be empty");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_scheduled_block_information);

        getScheduledBlockDetailInteract.execute(new GetScheduledBlockDetailObservable(),
                GetScheduledBlockDetailInteract.Params.create(childId, blockId));
    }

    /**
     * Delete Scheduled Block By Child Observable
     */
    public class DeleteScheduledBlockByChildObservable
            extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            Preconditions.checkState(!response.isEmpty(), "Response can not be empty");

            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onScheduledBlockDeleted();
            }
        }
    }


    /**
     * Save Scheduled Block Observable
     */
    public class SaveScheduledBlockObservable  extends CommandCallBackWrapper<ScheduledBlockEntity, SaveScheduledBlockInteract.SaveScheduledBlockApiErrors.ISaveScheduledBlockVisitor,
            SaveScheduledBlockInteract.SaveScheduledBlockApiErrors> implements SaveScheduledBlockInteract.SaveScheduledBlockApiErrors.ISaveScheduledBlockVisitor {

        public SaveScheduledBlockObservable(Class<SaveScheduledBlockInteract.SaveScheduledBlockApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param scheduledBlockEntity
         */
        @Override
        protected void onSuccess(ScheduledBlockEntity scheduledBlockEntity) {
            Preconditions.checkNotNull(scheduledBlockEntity, "Scheduled can not be null");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onScheduledBlockSaved(scheduledBlockEntity);
            }

        }

        /**
         * Visit Validation Error
         * @param apiErrors
         * @param errors
         */
        @Override
        public void visitValidationError(SaveScheduledBlockInteract.SaveScheduledBlockApiErrors apiErrors, LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                if(errors != null && !errors.isEmpty() && errors.containsKey("field_errors")) {
                    getView().onValidationErrors(errors.get("field_errors"));
                } else {
                    getView().showNoticeDialog(R.string.forms_is_not_valid);
                }
            }
        }
    }

    /**
     * Get Scheduled Block Detail
     */
    public class GetScheduledBlockDetailObservable  extends BasicCommandCallBackWrapper<ScheduledBlockEntity> {

        /**
         *
         * @param scheduledBlockEntity
         */
        @Override
        protected void onSuccess(final ScheduledBlockEntity scheduledBlockEntity) {
            Preconditions.checkNotNull(scheduledBlockEntity, "Scheduled Block Entity");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onScheduledBlockLoaded(scheduledBlockEntity);
            }

        }
    }



}
