package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetAlertsStatisticsInteract;
import sanchez.sanchez.sergio.domain.models.AlertsStatisticsEntity;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;

/**
 * System Alerts Fragment Presenter
 */
public final class SystemAlertsFragmentPresenter extends
        SupportPresenter<ISystemAlertsFragmentView> {

    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Alerts Statistics Interact
     */
    private final GetAlertsStatisticsInteract getAlertsStatisticsInteract;

    /**
     * Preference Repository
     */
    private final IPreferenceRepository preferenceRepository;

    /**
     *
     * @param getAlertsStatisticsInteract
     */
    @Inject
    public SystemAlertsFragmentPresenter(final GetAlertsStatisticsInteract getAlertsStatisticsInteract,
                                         final IPreferenceRepository preferenceRepository){
        this.getAlertsStatisticsInteract = getAlertsStatisticsInteract;
        this.preferenceRepository = preferenceRepository;
    }

    /**
     * On Init Args
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);
        if(args != null && args.containsKey(KID_IDENTITY_ARG))
            loadData(args.getString(KID_IDENTITY_ARG));
    }

    /**
     * Load Data
     * @param kidIdentity
     */
    public void loadData(final String kidIdentity) {
        Preconditions.checkNotNull(kidIdentity, "Son Identity can not be null");
        Preconditions.checkState(!kidIdentity.isEmpty(), "Son Identity can not be null");


        getAlertsStatisticsInteract.execute(new GetAlertsStatisticsObservable(GetAlertsStatisticsInteract.GetAlertsStatisticsApiErrors.class),
                GetAlertsStatisticsInteract.Params.create(kidIdentity, preferenceRepository.getAgeOfResultsAsInt()));
    }

    /**
     * Get Alerts Statistics Observable
     */
    public class GetAlertsStatisticsObservable extends CommandCallBackWrapper<AlertsStatisticsEntity,
            GetAlertsStatisticsInteract.GetAlertsStatisticsApiErrors.IGetAlertsStatisticsApiErrorsVisitor,
            GetAlertsStatisticsInteract.GetAlertsStatisticsApiErrors>
            implements GetAlertsStatisticsInteract.GetAlertsStatisticsApiErrors.IGetAlertsStatisticsApiErrorsVisitor {


        public GetAlertsStatisticsObservable(Class<GetAlertsStatisticsInteract.GetAlertsStatisticsApiErrors> apiErrors) {
            super(apiErrors);
        }


        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(AlertsStatisticsEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null)
                getView().onDataAvaliable(response);
        }


        /**
         * Visit No Alerts Statistics For This Period
         * @param apiErrors
         */
        @Override
        public void visitNoAlertsStatisticsForThisPeriod(GetAlertsStatisticsInteract.GetAlertsStatisticsApiErrors apiErrors) {

            if (isViewAttached() && getView() != null)
                getView().onNoDataAvaliable();

        }
    }
}
