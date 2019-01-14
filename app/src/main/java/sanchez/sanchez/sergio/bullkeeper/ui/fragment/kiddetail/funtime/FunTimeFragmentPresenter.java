package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.funtime;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.Arrays;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.funtime.GetFunTimeByChildIdInteract;
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
     * Get Fun Time By Child Interact
     */
    private final GetFunTimeByChildIdInteract getFunTimeByChildIdInteract;

    /**
     * @param getFunTimeByChildIdInteract
     */
    @Inject
    public FunTimeFragmentPresenter(final GetFunTimeByChildIdInteract getFunTimeByChildIdInteract){
        this.getFunTimeByChildIdInteract = getFunTimeByChildIdInteract;
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

        if (isViewAttached() && getView() != null)
            getView().onShowLoading();

        getFunTimeByChildIdInteract.execute(
                new GetFunTimeObservable(GetFunTimeByChildIdInteract.GetFunTimeApiErrors.class),
                GetFunTimeByChildIdInteract.Params.create(args.getString(KID_IDENTITY_ARG)));
    }

    /**
     * Get Fun Time Allowance
     */
    public class GetFunTimeObservable extends CommandCallBackWrapper<FunTimeScheduledEntity,
            GetFunTimeByChildIdInteract.GetFunTimeApiErrors.IGetFunTimeApiErrorsVisitor,
            GetFunTimeByChildIdInteract.GetFunTimeApiErrors>
            implements GetFunTimeByChildIdInteract.GetFunTimeApiErrors.IGetFunTimeApiErrorsVisitor {

        /**
         * Get Fun Time Observable
         * @param apiErrors
         */
        public GetFunTimeObservable(final Class<GetFunTimeByChildIdInteract.GetFunTimeApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * @param funTimeScheduledEntity
         */
        @Override
        protected void onSuccess(final FunTimeScheduledEntity funTimeScheduledEntity) {
            Preconditions.checkNotNull(funTimeScheduledEntity, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().setFunTimeStatus(funTimeScheduledEntity.getEnabled());
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
            }
        }

        /**
         * Visit No Fun Time Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoFunTimeFound(GetFunTimeByChildIdInteract.GetFunTimeApiErrors.IGetFunTimeApiErrorsVisitor apiErrorsVisitor) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }


}
