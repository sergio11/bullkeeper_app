package sanchez.sanchez.sergio.bullkeeper.ui.activity.savescheduledblock;

import com.fernandocejas.arrow.checks.Preconditions;

import org.joda.time.LocalDateTime;

import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.scheduled.DeleteScheduledBlockInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.SaveScheduledBlockInteract;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;

/**
 * Save Scheduled Block Presenter
 */
public final class SaveScheduledBlockPresenter extends SupportPresenter<ISaveScheduledBlockView> {

    public static final String SCHEDULED_BLOCK_IDENTITY_ARG = "SCHEDULED_BLOCK_IDENTITY";

    /**
     * Delete Scheduled Block Interact
     */
    private final DeleteScheduledBlockInteract deleteScheduledBlockInteract;

    /**
     * Save Scheduled Block Interact
     */
    private final SaveScheduledBlockInteract saveScheduledBlockInteract;


    /**
     * Save Scheduled Block
     * @param deleteScheduledBlockInteract
     * @param saveScheduledBlockInteract
     */
    @Inject
    public SaveScheduledBlockPresenter(final DeleteScheduledBlockInteract deleteScheduledBlockInteract,
                                       final SaveScheduledBlockInteract saveScheduledBlockInteract) {
        this.deleteScheduledBlockInteract = deleteScheduledBlockInteract;
        this.saveScheduledBlockInteract = saveScheduledBlockInteract;
    }

    /**
     * Delete Scheduled By Id
     * @param identity
     */
    public void deleteScheduledById(final String identity) {
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");
        deleteScheduledBlockInteract.execute(new DeleteScheduledBlockByChildObservable(),
                DeleteScheduledBlockInteract.Params.create(identity));
    }

    /**
     * Save Scheduled Block
     * @param identity
     * @param name
     * @param startAt
     * @param endAt
     * @param weeklyFrequency
     * @param recurringWeeklyEnabled
     */
    public void saveScheduledBlock(final String identity, final String name, final LocalDateTime startAt,
                                   final LocalDateTime endAt, final int[] weeklyFrequency,
                                   final boolean recurringWeeklyEnabled){

        saveScheduledBlockInteract.execute(new SaveScheduledBlockObservable(SaveScheduledBlockInteract.SaveScheduledBlockApiErrors.class),
                SaveScheduledBlockInteract.Params.create(identity, name, startAt, endAt, weeklyFrequency, recurringWeeklyEnabled));

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
         * @param response
         */
        @Override
        protected void onSuccess(ScheduledBlockEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

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

}
