package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.scheduledblock;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.scheduled.DeleteAllScheduledBlocksInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.DeleteScheduledBlockByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.GetScheduledBlockByChildInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.SaveScheduledBlockStatusInteract;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockStatusEntity;

/**
 * Scheduled Blocks Fragment Presenter
 */
public final class ScheduledBlocksFragmentPresenter extends SupportLCEPresenter<IScheduledBlocksFragmentView> {

    public static final String SON_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Scheduled Block By Child Interact
     */
    private final GetScheduledBlockByChildInteract getScheduledBlockByChildInteract;

    /**
     * Delete Scheduled Block Interact
     */
    private final DeleteScheduledBlockByIdInteract deleteScheduledBlockByIdInteract;

    /**
     * Delete All Scheduled Blocks Interact
     */
    private final DeleteAllScheduledBlocksInteract deleteAllScheduledBlocksInteract;

    /**
     * Save Scheduled Block Status Interact
     */
    private final SaveScheduledBlockStatusInteract saveScheduledBlockStatusInteract;

    /**
     * @param getScheduledBlockByChildInteract
     * @param deleteScheduledBlockByIdInteract
     * @param deleteAllScheduledBlocksInteract
     * @param saveScheduledBlockStatusInteract
     */
    @Inject
    public ScheduledBlocksFragmentPresenter(final GetScheduledBlockByChildInteract getScheduledBlockByChildInteract,
                                            final DeleteScheduledBlockByIdInteract deleteScheduledBlockByIdInteract,
                                            final DeleteAllScheduledBlocksInteract deleteAllScheduledBlocksInteract,
                                            final SaveScheduledBlockStatusInteract saveScheduledBlockStatusInteract){
        this.getScheduledBlockByChildInteract = getScheduledBlockByChildInteract;
        this.deleteScheduledBlockByIdInteract = deleteScheduledBlockByIdInteract;
        this.deleteAllScheduledBlocksInteract = deleteAllScheduledBlocksInteract;
        this.saveScheduledBlockStatusInteract = saveScheduledBlockStatusInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() { }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(SON_IDENTITY_ARG), "You must provide a son identity value");

        getScheduledBlockByChildInteract.execute(new GetScheduledBlockByChildObservable(GetScheduledBlockByChildInteract.GetScheduledBlockByChildApiErrors.class),
                GetScheduledBlockByChildInteract.Params.create(args.getString(SON_IDENTITY_ARG)));

    }

    /**
     * Delete Scheduled Block By Id
     * @param childId
     * @param identity
     */
    public void deleteScheduledBlockById(final String childId, final String identity) {
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");

        deleteScheduledBlockByIdInteract.execute(new DeleteScheduledBlockByChildObservable(),
                DeleteScheduledBlockByIdInteract.Params.create(childId, identity));
    }

    /**
     * Delete All Scheduled Block By Child Id
     * @param childId
     */
    public void deleteAllScheduledBlockByChildId(final String childId) {
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");

        deleteAllScheduledBlocksInteract.execute(new DeleteAllScheduledBlockByChildObservable(),
                DeleteAllScheduledBlocksInteract.Params.create(childId));

    }

    /**
     * Save Scheduled Block Status
     * @param childId
     */
    public void saveScheduledBlockStatus(final String childId, final List<ScheduledBlockStatusEntity> scheduledBlockStatusEntities) {
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(scheduledBlockStatusEntities, "Scheduled Block status can not be empty");


        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.saving_scheduled_block_info);

        saveScheduledBlockStatusInteract.execute(new SaveScheduledBlockStatusObservable(),
                SaveScheduledBlockStatusInteract.Params.create(childId, scheduledBlockStatusEntities));

    }


    /**
     * Get Scheduled Block By Child Observable
     */
    public class GetScheduledBlockByChildObservable extends CommandCallBackWrapper<List<ScheduledBlockEntity>,
            GetScheduledBlockByChildInteract.GetScheduledBlockByChildApiErrors.IGetScheduledBlockByChildApiErrorsVisitor,
            GetScheduledBlockByChildInteract.GetScheduledBlockByChildApiErrors>
            implements GetScheduledBlockByChildInteract.GetScheduledBlockByChildApiErrors.IGetScheduledBlockByChildApiErrorsVisitor {

        /**
         * Get Scheduled Block
         * @param apiErrors
         */
        public GetScheduledBlockByChildObservable(Class<GetScheduledBlockByChildInteract.GetScheduledBlockByChildApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(List<ScheduledBlockEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            Preconditions.checkState(!response.isEmpty(), "Response can not be empty");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }

        }

        /**
         * Visit No Scheduled Block Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoScheduledBlockFound(GetScheduledBlockByChildInteract.GetScheduledBlockByChildApiErrors.IGetScheduledBlockByChildApiErrorsVisitor apiErrorsVisitor) {

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }


    /**
     * Delete Scheduled Block By Child Observable
     */
    public class DeleteScheduledBlockByChildObservable extends BasicCommandCallBackWrapper<String> {

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
     * Delete All Scheduled Block By Child Observable
     */
    public class DeleteAllScheduledBlockByChildObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            Preconditions.checkState(!response.isEmpty(), "Response can not be empty");

            if (isViewAttached() && getView() != null) {
                getView().onAllScheduledBlockDeleted();
            }
        }
    }

    /**
     * Save Scheduled Block Status Observable
     */
    public class SaveScheduledBlockStatusObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Sucess
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onScheduledBlockStatusSaved();
            }
        }
    }


}
