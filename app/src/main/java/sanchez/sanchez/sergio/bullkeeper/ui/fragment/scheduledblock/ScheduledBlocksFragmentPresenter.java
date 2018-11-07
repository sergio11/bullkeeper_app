package sanchez.sanchez.sergio.bullkeeper.ui.fragment.scheduledblock;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.scheduled.DeleteScheduledBlockByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.GetScheduledBlockByChildInteract;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;

/**
 * Scheduled Blocks Fragment Presenter
 */
public final class ScheduledBlocksFragmentPresenter extends SupportLCEPresenter<IScheduledBlocksFragmentView> {

    public static final String SON_IDENTITY_ARG = "SON_IDENTITY_ARG";

    /**
     * Get Scheduled Block By Child Interact
     */
    private final GetScheduledBlockByChildInteract getScheduledBlockByChildInteract;

    /**
     * Delete Scheduled Block Interact
     */
    private final DeleteScheduledBlockByIdInteract deleteScheduledBlockByIdInteract;

    /**
     * @param getScheduledBlockByChildInteract
     * @param deleteScheduledBlockByIdInteract
     */
    @Inject
    public ScheduledBlocksFragmentPresenter(final GetScheduledBlockByChildInteract getScheduledBlockByChildInteract,
                                            final DeleteScheduledBlockByIdInteract deleteScheduledBlockByIdInteract){
        this.getScheduledBlockByChildInteract = getScheduledBlockByChildInteract;
        this.deleteScheduledBlockByIdInteract = deleteScheduledBlockByIdInteract;
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


}
