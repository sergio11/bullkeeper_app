package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smslist;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.sms.GetSmsListInteract;
import sanchez.sanchez.sergio.domain.models.SmsEntity;

/**
 * SMS List Fragment Presenter
 */
public final class SmsListFragmentPresenter extends SupportLCEPresenter<ISmsListFragmentView> {

    /**
     * Args
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    public static final String CURRENT_TERMINAL_ARG = "CURRENT_TERMINAL_ARG";

    /**
     * Get Sms List Interact
     */
    private final GetSmsListInteract getSmsListInteract;

    /**
     * Is Loading Data
     */
    private boolean isLoadingData = false;

    /**
     *
     * @param getSmsListInteract
     */
    @Inject
    public SmsListFragmentPresenter(final GetSmsListInteract getSmsListInteract){
        this.getSmsListInteract = getSmsListInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() { throw  new IllegalStateException("Args can not be empty"); }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KID_IDENTITY_ARG), "You must provide a kid identity value");
        Preconditions.checkState(args.containsKey(CURRENT_TERMINAL_ARG), "You must provide Current Terminal");

        if(isLoadingData)
            return;

        isLoadingData = true;

        final TerminalItem terminalItem = (TerminalItem) args.getSerializable(CURRENT_TERMINAL_ARG);

        if(terminalItem != null) {

            if (isViewAttached() && getView() != null)
                getView().onShowLoading();

            getSmsListInteract.execute(new GetSmsListObservable(GetSmsListInteract.GetSmsListApiErrors.class),
                    GetSmsListInteract.Params.create(
                                args.getString(KID_IDENTITY_ARG),
                                terminalItem.getIdentity()));
        }


    }


    /**
     * Get SMS List Observable
     */
    public class GetSmsListObservable extends CommandCallBackWrapper<List<SmsEntity>,
            GetSmsListInteract.GetSmsListApiErrors.IGetSmsListApiErrorsVisitor,
            GetSmsListInteract.GetSmsListApiErrors>
            implements GetSmsListInteract.GetSmsListApiErrors.IGetSmsListApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetSmsListObservable(Class<GetSmsListInteract.GetSmsListApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Network Error
         */
        @Override
        protected void onNetworkError() {
            super.onNetworkError();
            isLoadingData = false;
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            super.onOtherException(ex);
            isLoadingData = false;
        }

        /**
         * On API Exception
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            super.onApiException(response);
            isLoadingData = false;
        }

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(List<SmsEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            Preconditions.checkState(!response.isEmpty(), "Response can not be empty");
            if (isViewAttached() && getView() != null){
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }
            isLoadingData = false;
        }

        /**
         * Visit No SMS Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoSmsFound(GetSmsListInteract.GetSmsListApiErrors.IGetSmsListApiErrorsVisitor apiErrorsVisitor) {
            Preconditions.checkNotNull(apiErrorsVisitor, "Api Errors Visitor");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
            isLoadingData = false;
        }
    }


}
