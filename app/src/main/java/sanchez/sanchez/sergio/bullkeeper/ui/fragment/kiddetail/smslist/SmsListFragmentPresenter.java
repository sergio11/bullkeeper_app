package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smslist;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
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
    public static final String TERMINALS_ARG = "TERMINALS_ARG";
    public static final String CURRENT_TERMINAL_POS_ARG = "CURRENT_TERMINAL_POS_ARG";

    /**
     * Get Sms List Interact
     */
    private final GetSmsListInteract getSmsListInteract;

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
    public void loadData() { }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KID_IDENTITY_ARG), "You must provide a son identity value");
        Preconditions.checkState(args.containsKey(TERMINALS_ARG), "You must provide terminals list");
        final ArrayList<TerminalItem> terminalItems = (ArrayList<TerminalItem>) args.getSerializable(TERMINALS_ARG);
        Preconditions.checkNotNull(terminalItems, "Terminal list can not be null");
        Preconditions.checkState(!terminalItems.isEmpty(), "Terminal list can not be empty");
        Preconditions.checkState(args.containsKey(CURRENT_TERMINAL_POS_ARG), "You must provide a terminal pos");

        final TerminalItem terminalItem =
                terminalItems.get(args.getInt(CURRENT_TERMINAL_POS_ARG));

        if(terminalItem != null)
            getSmsListInteract.execute(new GetSmsListObservable(GetSmsListInteract.GetSmsListApiErrors.class),
                    GetSmsListInteract.Params.create(
                            args.getString(KID_IDENTITY_ARG),
                            args.getString(TERMINALS_ARG)));
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
        }
    }


}
