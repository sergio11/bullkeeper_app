package sanchez.sanchez.sergio.bullkeeper.ui.activity.summarymykidsresults;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.statistics.GetStatisticsSummaryInteract;
import sanchez.sanchez.sergio.domain.models.SummaryMyKidResultEntity;

/**
 * Summary My Kids Results Presenter
 */
public final class SummaryMyKidsResultsPresenter
        extends SupportLCEPresenter<ISummaryMyKidsResultsView> {

    /**
     * Get Statistics Summary Interact
     */
    private final GetStatisticsSummaryInteract getStatisticsSummaryInteract;


    /**
     *
     * @param getStatisticsSummaryInteract
     */
    @Inject
    public SummaryMyKidsResultsPresenter(final GetStatisticsSummaryInteract getStatisticsSummaryInteract) {
        this.getStatisticsSummaryInteract = getStatisticsSummaryInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {

        getStatisticsSummaryInteract.execute(new GetStatisticsSummaryObservable(
                        GetStatisticsSummaryInteract.GetStatisticsSummaryApiErrors.class),
                null);
    }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(final Bundle args) { loadData(); }

    /**
     * Get Statistics Summary Observable
     */
    public class GetStatisticsSummaryObservable extends CommandCallBackWrapper<List<SummaryMyKidResultEntity>,
            GetStatisticsSummaryInteract.GetStatisticsSummaryApiErrors.IGetStatisticsSummaryApiErrorsVisitor,
            GetStatisticsSummaryInteract.GetStatisticsSummaryApiErrors>
            implements GetStatisticsSummaryInteract.GetStatisticsSummaryApiErrors.IGetStatisticsSummaryApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetStatisticsSummaryObservable(Class<GetStatisticsSummaryInteract.GetStatisticsSummaryApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(final List<SummaryMyKidResultEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }
        }

        /**
         * No Statistics Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoStatisticsFound(GetStatisticsSummaryInteract.GetStatisticsSummaryApiErrors apiErrorsVisitor) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }
}
