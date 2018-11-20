package sanchez.sanchez.sergio.bullkeeper.ui.fragment.terminaldetail;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.terminal.DeleteTerminalInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.GetTerminalDetailInteract;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import timber.log.Timber;

/**
 * Terminal Detail Presenter
 */
public final class TerminalDetailFragmentPresenter extends SupportPresenter<ITerminalDetailView> {


    /**
     * Get Terminal Detail Interact
     */
    private final GetTerminalDetailInteract getTerminalDetailInteract;


    /**
     * Delete Terminal Interact
     */
    private DeleteTerminalInteract deleteTerminalInteract;

    /**
     * @param getTerminalDetailInteract
     * @param deleteTerminalInteract
     */
    @Inject
    public TerminalDetailFragmentPresenter(final GetTerminalDetailInteract getTerminalDetailInteract,
                                           final DeleteTerminalInteract deleteTerminalInteract){
        this.getTerminalDetailInteract = getTerminalDetailInteract;
        this.deleteTerminalInteract = deleteTerminalInteract;
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_terminal_detail);

        final String childId = args.getString(TerminalDetailActivityMvpFragment.CHILD_ID_ARG);
        final String terminalId = args.getString(TerminalDetailActivityMvpFragment.TERMINAL_ID_ARG);

        getTerminalDetailInteract.execute(new GetTerminalDetailObserver(),
                GetTerminalDetailInteract.Params.create(childId, terminalId));

    }

    /**
     * Delete Terminal
     * @param childId
     * @param terminalId
     */
    public void deleteTerminal(final String childId, final String terminalId) {
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.deleting_terminal_in_progress);

        deleteTerminalInteract.execute(new DeleteTerminalObserver(),
                DeleteTerminalInteract.Params.create(childId, terminalId));

    }

    /**
     * Delete Terminal
     */
    public class DeleteTerminalObserver extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            Preconditions.checkState(!response.isEmpty(), "Response can not be empty");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onTerminalSuccessDeleted();
            }
        }
    }

    /**
     * Get Terminal Detail Observer
     */
    public class GetTerminalDetailObserver extends BasicCommandCallBackWrapper<TerminalDetailEntity> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(TerminalDetailEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onTerminalDetailLoaded(response);
            }

        }
    }


}
