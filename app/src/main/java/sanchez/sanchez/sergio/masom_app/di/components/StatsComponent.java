package sanchez.sanchez.sergio.masom_app.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.kidsresults.KidsResultsActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.kidsresults.KidsResultsActivityPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.charts.activity.ActivitySocialMediaFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.charts.activity.ActivitySocialMediaMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.charts.alerts.SystemAlertsFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.charts.alerts.SystemAlertsMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.charts.dimensions.FourDimensionsFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.charts.dimensions.FourDimensionsMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.charts.likes.LikesChartFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.charts.likes.LikesChartMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.charts.sentiment.SentimentAnalysisFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.charts.sentiment.SentimentAnalysisMvpFragment;

/**
 * Results Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface StatsComponent extends ActivityComponent {

    /**
     * Kids Results Activity
     * @param kidsResultsActivity
     */
    void inject(final KidsResultsActivity kidsResultsActivity);

    /**
     * Four Dimensions Mvp Fragment
     * @param fourDimensionsMvpFragment
     */
    void inject(final FourDimensionsMvpFragment fourDimensionsMvpFragment);

    /**
     * Activity Social Media Mvp Fragment
     * @param activitySocialMediaMvpFragment
     */
    void inject(final ActivitySocialMediaMvpFragment activitySocialMediaMvpFragment);

    /**
     * Sentiment Analysis Mvp Fragment
     * @param sentimentAnalysisMvpFragment
     */
    void inject(final SentimentAnalysisMvpFragment sentimentAnalysisMvpFragment);

    /**
     * Inject into System Alerts Mvp Fragment
     * @param systemAlertsMvpFragment
     */
    void inject(final SystemAlertsMvpFragment systemAlertsMvpFragment);

    /**
     * Inject into Likes Chart Mvp Fragment
     * @param likesChartMvpFragment
     */
    void inject(final LikesChartMvpFragment likesChartMvpFragment);

    /**
     * kids Results Activity Presenter
     * @return
     */
    KidsResultsActivityPresenter kidsResultsActivityPresenter();
    FourDimensionsFragmentPresenter fourDimensionsFragmentPresenter();
    ActivitySocialMediaFragmentPresenter activitySocialMediaFragmentPresenter();
    SentimentAnalysisFragmentPresenter sentimentAnalysisFragmentPresenter();
    SystemAlertsFragmentPresenter systemAlertsFragmentPresenter();
    LikesChartFragmentPresenter likesChartFragmentPresenter();

}
