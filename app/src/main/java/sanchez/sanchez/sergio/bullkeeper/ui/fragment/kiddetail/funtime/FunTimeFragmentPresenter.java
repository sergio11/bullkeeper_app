package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.funtime;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.Arrays;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.funtime.GetFunTimeInteract;
import sanchez.sanchez.sergio.domain.interactor.funtime.SaveFunTimeInteract;
import sanchez.sanchez.sergio.domain.models.FunTimeScheduledEntity;

/**
 * Fun Time Fragment Presenter
 */
public final class FunTimeFragmentPresenter
        extends SupportLCEPresenter<IFunTimeFragmentView> {

    /**
     * Kid Identity
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Current Terminal
     */
    public static final String CURRENT_TERMINAL_ARG = "CURRENT_TERMINAL_ARG";

    /**
     * Get Fun Time By Child Interact
     */
    private final GetFunTimeInteract getFunTimeInteract;

    /**
     * Save Fun Time Interact
     */
    private final SaveFunTimeInteract saveFunTimeInteract;

    /**
     * Is Loading Data
     */
    private boolean isLoadingData = false;

    /**
     * @param getFunTimeInteract
     * @param saveFunTimeInteract
     */
    @Inject
    public FunTimeFragmentPresenter(
            final GetFunTimeInteract getFunTimeInteract,
            final SaveFunTimeInteract saveFunTimeInteract){
        this.getFunTimeInteract = getFunTimeInteract;
        this.saveFunTimeInteract = saveFunTimeInteract;
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
        Preconditions.checkState(args.containsKey(KID_IDENTITY_ARG), "You must provide a kid identity value");
        Preconditions.checkState(args.containsKey(CURRENT_TERMINAL_ARG), "You must provide Current Terminal");

        if (isLoadingData)
            return;

        isLoadingData = true;

        final TerminalItem terminalItem = (TerminalItem) args.getSerializable(CURRENT_TERMINAL_ARG);

        if(terminalItem != null) {

            if (isViewAttached() && getView() != null)
                getView().onShowLoading();

            getFunTimeInteract.execute(
                    new GetFunTimeObservable(GetFunTimeInteract.GetFunTimeApiErrors.class),
                    GetFunTimeInteract.Params.create(
                            args.getString(KID_IDENTITY_ARG), terminalItem.getIdentity()));

        }

    }

    /**
     * Save Fun Time
     * @param kid
     * @param terminal
     * @param funTimeScheduledToSave
     */
    public void saveFunTime(final String kid, final String terminal,
                            final FunTimeScheduledEntity funTimeScheduledToSave) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkNotNull(funTimeScheduledToSave, "Fun Time Scheduled To Save");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        saveFunTimeInteract.execute(new SaveFunTimeObservable(),
                SaveFunTimeInteract.Params.create(kid, terminal, funTimeScheduledToSave));

    }

    /**
     * Get Fun Time Allowance
     */
    public class GetFunTimeObservable extends CommandCallBackWrapper<FunTimeScheduledEntity,
            GetFunTimeInteract.GetFunTimeApiErrors.IGetFunTimeApiErrorsVisitor,
            GetFunTimeInteract.GetFunTimeApiErrors>
            implements GetFunTimeInteract.GetFunTimeApiErrors.IGetFunTimeApiErrorsVisitor {

        /**
         * Get Fun Time Observable
         * @param apiErrors
         */
        public GetFunTimeObservable(final Class<GetFunTimeInteract.GetFunTimeApiErrors> apiErrors) {
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
         * On Api Exception
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            super.onApiException(response);
            isLoadingData = false;
        }

        /**
         * @param funTimeScheduledEntity
         */
        @Override
        protected void onSuccess(final FunTimeScheduledEntity funTimeScheduledEntity) {
            Preconditions.checkNotNull(funTimeScheduledEntity, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(
                        Arrays.asList(
                                funTimeScheduledEntity.getMonday(),
                                funTimeScheduledEntity.getThursday(),
                                funTimeScheduledEntity.getWednesday(),
                                funTimeScheduledEntity.getTuesday(),
                                funTimeScheduledEntity.getFriday(),
                                funTimeScheduledEntity.getSaturday(),
                                funTimeScheduledEntity.getSunday()
                        )
                );
                getView().setFunTimeStatus(funTimeScheduledEntity.getEnabled());
            }

            isLoadingData = false;
        }

        /**
         * Visit No Fun Time Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoFunTimeFound(GetFunTimeInteract.GetFunTimeApiErrors.IGetFunTimeApiErrorsVisitor apiErrorsVisitor) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
            isLoadingData = false;
        }
    }

    /**
     * Save Fun Time Observable
     */
    public class SaveFunTimeObservable extends BasicCommandCallBackWrapper<FunTimeScheduledEntity> {

        /**
         * On Success
         * @param funTimeScheduledEntity
         */
        @Override
        protected void onSuccess(final FunTimeScheduledEntity funTimeScheduledEntity) {
            Preconditions.checkNotNull(funTimeScheduledEntity, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().showNoticeDialog(R.string.fun_time_saved_successfully);
                getView().onDataLoaded(
                        Arrays.asList(
                                funTimeScheduledEntity.getMonday(),
                                funTimeScheduledEntity.getThursday(),
                                funTimeScheduledEntity.getWednesday(),
                                funTimeScheduledEntity.getTuesday(),
                                funTimeScheduledEntity.getFriday(),
                                funTimeScheduledEntity.getSaturday(),
                                funTimeScheduledEntity.getSunday()
                        )
                );
                getView().setFunTimeStatus(funTimeScheduledEntity.getEnabled());
            }
        }
    }


}
