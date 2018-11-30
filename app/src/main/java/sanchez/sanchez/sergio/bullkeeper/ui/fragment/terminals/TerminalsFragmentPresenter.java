package sanchez.sanchez.sergio.bullkeeper.ui.fragment.terminals;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.terminal.GetMonitoredTerminalsInteract;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;

/**
 * Terminals Fragment Presenter
 */
public final class TerminalsFragmentPresenter extends SupportLCEPresenter<ITerminalsFragmentView> {

    public static final String SON_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Monitored Terminals Interact
     */
    private final GetMonitoredTerminalsInteract getMonitoredTerminalsInteract;


    /**
     *
     * @param getMonitoredTerminalsInteract
     */
    @Inject
    public TerminalsFragmentPresenter(GetMonitoredTerminalsInteract getMonitoredTerminalsInteract){
        this.getMonitoredTerminalsInteract = getMonitoredTerminalsInteract;
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

        getMonitoredTerminalsInteract.execute(new GetMonitoredTerminalsObservable(GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors.class),
                GetMonitoredTerminalsInteract.Params.create(args.getString(SON_IDENTITY_ARG)));
    }


    /**
     * Get Monitored Terminals Observable
     */
    public class GetMonitoredTerminalsObservable extends CommandCallBackWrapper<List<TerminalEntity>,
            GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors.IGetMonitoredTerminalsApiErrorsVisitor,
            GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors>
            implements GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors.IGetMonitoredTerminalsApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetMonitoredTerminalsObservable(Class<GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         *
         * @param terminals
         */
        @Override
        protected void onSuccess(List<TerminalEntity> terminals) {
            Preconditions.checkNotNull(terminals, "Terminals can not be null");

            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(terminals);
            }

        }

        /**
         *
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoMonitoredTerminalsFound(GetMonitoredTerminalsInteract.GetMonitoredTerminalsApiErrors.IGetMonitoredTerminalsApiErrorsVisitor apiErrorsVisitor) {

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }
}
