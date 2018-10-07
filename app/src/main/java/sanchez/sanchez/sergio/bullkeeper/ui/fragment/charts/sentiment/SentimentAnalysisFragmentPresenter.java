package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetSentimentAnalysisStatisticsInteract;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;

/**
 * Sentiment Analysis Fragment Presenter
 */
public final class SentimentAnalysisFragmentPresenter extends SupportPresenter<ISentimentAnalysisFragmentView> {

    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Days Ago Default Value
     */
    private final static int DAYS_AGO_DEFAULT_VALUE = 30;

    /**
     * Get Sentiment Analysis Statistics Interact
     */
    private final GetSentimentAnalysisStatisticsInteract getSentimentAnalysisStatisticsInteract;

    /**
     * @param getSentimentAnalysisStatisticsInteract
     */
    @Inject
    public SentimentAnalysisFragmentPresenter(final GetSentimentAnalysisStatisticsInteract getSentimentAnalysisStatisticsInteract){
        this.getSentimentAnalysisStatisticsInteract = getSentimentAnalysisStatisticsInteract;
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

        getSentimentAnalysisStatisticsInteract.execute(new GetSentimentAnalysisFragmentObservable(GetSentimentAnalysisStatisticsInteract.GetSentimentAnalysisStatisticsApiErrors.class),
                GetSentimentAnalysisStatisticsInteract.Params.create(kidIdentity, DAYS_AGO_DEFAULT_VALUE));

    }

    /**
     * Get Sentiment Analysis Fragment Observable
     */
    public class GetSentimentAnalysisFragmentObservable extends CommandCallBackWrapper<SentimentAnalysisStatisticsEntity,
            GetSentimentAnalysisStatisticsInteract.GetSentimentAnalysisStatisticsApiErrors.IGetSentimentAnalysisStatisticsApiErrorsVisitor,
            GetSentimentAnalysisStatisticsInteract.GetSentimentAnalysisStatisticsApiErrors>
            implements GetSentimentAnalysisStatisticsInteract.GetSentimentAnalysisStatisticsApiErrors.IGetSentimentAnalysisStatisticsApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetSentimentAnalysisFragmentObservable(Class<GetSentimentAnalysisStatisticsInteract.GetSentimentAnalysisStatisticsApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(SentimentAnalysisStatisticsEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null)
                getView().onDataAvaliable(response);

        }

        /**
         * Visit No Sentiment Analysis Statistics For This Period
         * @param apiErrors
         */
        @Override
        public void visitNoSentimentAnalysisStatisticsForThisPeriod(GetSentimentAnalysisStatisticsInteract.GetSentimentAnalysisStatisticsApiErrors apiErrors) {

            if (isViewAttached() && getView() != null)
                getView().onNoDataAvaliable();
        }
    }
}
