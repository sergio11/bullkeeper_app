package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.appstats;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.apprules.GetStatisticsOfTheFiveMostUsedApplicationsInteract;
import sanchez.sanchez.sergio.domain.models.AppStatsEntity;

/**
 * App Stats Fragment Presenter
 */
public final class AppStatsFragmentPresenter extends SupportPresenter<IAppStatsFragmentView> {

    /**
     * Args
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    public static final String TERMINALS_ARG = "TERMINALS_ARG";
    public static final String CURRENT_TERMINAL_POS_ARG = "CURRENT_TERMINAL_POS_ARG";

    /**
     * Get Statistics Of The Five Most Used Applications
     */
    private final GetStatisticsOfTheFiveMostUsedApplicationsInteract
            getStatisticsOfTheFiveMostUsedApplicationsInteract;

    /**
     * Is Loading Data
     */
    private boolean isLoadingData = false;

    /**
     *
     * @param getStatisticsOfTheFiveMostUsedApplicationsInteract
     */
    @Inject
    public AppStatsFragmentPresenter(final GetStatisticsOfTheFiveMostUsedApplicationsInteract
                                                 getStatisticsOfTheFiveMostUsedApplicationsInteract){
        this.getStatisticsOfTheFiveMostUsedApplicationsInteract = getStatisticsOfTheFiveMostUsedApplicationsInteract;
    }


    /**
     * Load Data
     * @param kid
     * @param terminal
     */
    public void loadData(final String kid, final String terminal){
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not empty");
        Preconditions.checkNotNull(terminal, "terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "terminal can not empty");

        if(isLoadingData)
            return;

        isLoadingData = true;

        getStatisticsOfTheFiveMostUsedApplicationsInteract.execute(new GetStatisticsOfTheFiveMostUsedApplicationsObservable(
                        GetStatisticsOfTheFiveMostUsedApplicationsInteract.GetStatisticsOfTheFiveMostUsedApplicationsApiErrors.class
                ), GetStatisticsOfTheFiveMostUsedApplicationsInteract.Params.create(kid, terminal));

    }

    /**
     * Get Statistics Of The Five Most Used Applications Observable
     */
    public class GetStatisticsOfTheFiveMostUsedApplicationsObservable extends CommandCallBackWrapper<List<AppStatsEntity>,
            GetStatisticsOfTheFiveMostUsedApplicationsInteract.GetStatisticsOfTheFiveMostUsedApplicationsApiErrors.IGetStatisticsOfTheFiveMostUsedApplicationsApiErrorsApiErrorsVisitor,
            GetStatisticsOfTheFiveMostUsedApplicationsInteract.GetStatisticsOfTheFiveMostUsedApplicationsApiErrors>
            implements GetStatisticsOfTheFiveMostUsedApplicationsInteract.GetStatisticsOfTheFiveMostUsedApplicationsApiErrors
                .IGetStatisticsOfTheFiveMostUsedApplicationsApiErrorsApiErrorsVisitor {


        public GetStatisticsOfTheFiveMostUsedApplicationsObservable(Class<GetStatisticsOfTheFiveMostUsedApplicationsInteract.GetStatisticsOfTheFiveMostUsedApplicationsApiErrors> apiErrors) {
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
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(List<AppStatsEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null)
                getView().onDataAvaliable(response);
            isLoadingData = false;
        }

        /**
         * Visit No App Stats Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoAppStatsFound(GetStatisticsOfTheFiveMostUsedApplicationsInteract.GetStatisticsOfTheFiveMostUsedApplicationsApiErrors.IGetStatisticsOfTheFiveMostUsedApplicationsApiErrorsApiErrorsVisitor apiErrorsVisitor) {
            if (isViewAttached() && getView() != null)
                getView().onNoDataAvaliable();
            isLoadingData = false;
        }
    }
}
