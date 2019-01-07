package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ChildrenModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.CommentsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidsresults.KidsResultsActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidsresults.KidsResultsActivityPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.activity.ActivitySocialMediaFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.activity.ActivitySocialMediaMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts.SystemAlertsFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts.SystemAlertsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.comments.CommentsExtractedBySocialMediaFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.comments.CommentsExtractedBySocialMediaFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.dimensions.FourDimensionsFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.dimensions.FourDimensionsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.likes.LikesChartFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.likes.LikesChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.relations.RelationsFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.relations.RelationsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment.SentimentAnalysisFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment.SentimentAnalysisMvpFragment;

/**
 * Results Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, ChildrenModule.class, DataMapperModule.class,
                CommentsModule.class })
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
     * Inject into Comments Extracted Mvp Fragment
     * @param commentsExtractedBySocialMediaMvpFragment
     */
    void inject(final CommentsExtractedBySocialMediaFragment commentsExtractedBySocialMediaMvpFragment);

    /**
     * Inject into Relations Mvp Fragment
     * @param relationsMvpFragment
     */
    void inject(final RelationsMvpFragment relationsMvpFragment);

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
    CommentsExtractedBySocialMediaFragmentPresenter commentsExtractedFragmentPresenter();
    RelationsFragmentPresenter relationsFragmentPresenter();

}
