package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.calldetail;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.calls.GetCallDetailInteract;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;

/**
 * Call Detail Presenter
 */
public final class CallDetailFragmentPresenter extends SupportPresenter<ICallDetailView> {

    /**
     * Get Call Detail Interact
     */
    private final GetCallDetailInteract getCallDetailInteract;

    /**
     * Call Detail Presenter
     */
    @Inject
    public CallDetailFragmentPresenter(final GetCallDetailInteract getCallDetailInteract){
        this.getCallDetailInteract = getCallDetailInteract;
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        final String childId = args.getString(CallDetailActivityMvpFragment.CHILD_ID_ARG);
        final String terminalId = args.getString(CallDetailActivityMvpFragment.TERMINAL_ID_ARG);
        final String callId = args.getString(CallDetailActivityMvpFragment.CALL_ID_ARG);

        getCallDetailInteract.execute(new GetCallDetailObserver(),
                GetCallDetailInteract.Params.create(childId, terminalId, callId));

    }

    /**
     * Get Call Detail Observer
     */
    public class GetCallDetailObserver extends BasicCommandCallBackWrapper<CallDetailEntity> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(CallDetailEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onCallDetailLoaded(response);
            }

        }
    }

}
