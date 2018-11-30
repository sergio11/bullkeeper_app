package sanchez.sanchez.sergio.bullkeeper.ui.fragment.timeallowance;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.timeallowance.GetScreenTimeAllowanceByChildIdInteract;
import sanchez.sanchez.sergio.domain.models.ScreenTimeAllowanceEntity;

/**
 * Time Allowance Fragment Presenter
 */
public final class TimeAllowanceFragmentPresenter extends SupportLCEPresenter<ITimeAllowanceFragmentView> {

    public static final String SON_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Screen Time Allowance By Child Interact
     */
    private final GetScreenTimeAllowanceByChildIdInteract getScreenTimeAllowanceByChildIdInteract;

    /**
     * @param getScreenTimeAllowanceByChildIdInteract
     */
    @Inject
    public TimeAllowanceFragmentPresenter(final GetScreenTimeAllowanceByChildIdInteract getScreenTimeAllowanceByChildIdInteract){
        this.getScreenTimeAllowanceByChildIdInteract = getScreenTimeAllowanceByChildIdInteract;
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

        getScreenTimeAllowanceByChildIdInteract.execute(
                new GetScreenTimeAllowanceObservable(GetScreenTimeAllowanceByChildIdInteract.GetScreenTimeAllowanceApiErrors.class),
                GetScreenTimeAllowanceByChildIdInteract.Params.create(args.getString(SON_IDENTITY_ARG)));

    }


    /**
     * Get Screen Time Allowance
     */
    public class GetScreenTimeAllowanceObservable extends CommandCallBackWrapper<ScreenTimeAllowanceEntity,
            GetScreenTimeAllowanceByChildIdInteract.GetScreenTimeAllowanceApiErrors.IGetScreenTimeAllowanceApiErrorsVisitor,
            GetScreenTimeAllowanceByChildIdInteract.GetScreenTimeAllowanceApiErrors>
            implements GetScreenTimeAllowanceByChildIdInteract.GetScreenTimeAllowanceApiErrors.IGetScreenTimeAllowanceApiErrorsVisitor {

        /**
         * Get Scheduled Block
         * @param apiErrors
         */
        public GetScreenTimeAllowanceObservable(Class<GetScreenTimeAllowanceByChildIdInteract.GetScreenTimeAllowanceApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(final ScreenTimeAllowanceEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().setRemainingTime(response.getRemainingTime());
                getView().onDataLoaded(response.getTimeAllowancePerDay());
            }

        }

        /**
         * No Screen Allowance Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoScreenAllowanceFound(final GetScreenTimeAllowanceByChildIdInteract.GetScreenTimeAllowanceApiErrors.IGetScreenTimeAllowanceApiErrorsVisitor apiErrorsVisitor) {

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }

        }
    }


}
