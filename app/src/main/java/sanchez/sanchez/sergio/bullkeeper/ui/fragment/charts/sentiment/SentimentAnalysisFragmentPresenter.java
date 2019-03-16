package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.statistics.GetSentimentAnalysisStatisticsInteract;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;

/**
 * Sentiment Analysis Fragment Presenter
 */
public final class SentimentAnalysisFragmentPresenter extends SupportPresenter<ISentimentAnalysisFragmentView> {

    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


    /**
     * Get Sentiment Analysis Statistics Interact
     */
    private final GetSentimentAnalysisStatisticsInteract getSentimentAnalysisStatisticsInteract;

    /**
     * Preference Repository
     */
    private final IPreferenceRepository preferenceRepository;


    /**
     * @param getSentimentAnalysisStatisticsInteract
     * @param preferenceRepository
     */
    @Inject
    public SentimentAnalysisFragmentPresenter(final GetSentimentAnalysisStatisticsInteract getSentimentAnalysisStatisticsInteract,
                                              final IPreferenceRepository preferenceRepository){
        this.getSentimentAnalysisStatisticsInteract = getSentimentAnalysisStatisticsInteract;
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

        getSentimentAnalysisStatisticsInteract.execute(new GetSentimentAnalysisFragmentObservable(GetSentimentAnalysisStatisticsInteract.GetSentimentAnalysisStatisticsApiErrors.class),
                GetSentimentAnalysisStatisticsInteract.Params.create(kidIdentity, preferenceRepository.getAgeOfResultsAsInt()));

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
