package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.likes;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fernandocejas.arrow.checks.Preconditions;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.chart.SupportBarChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;

/**
 * Likes Chart Mvp Fragment
 */
public class LikesChartMvpFragment
        extends SupportBarChartMvpFragment<LikesChartFragmentPresenter,
                ILikesChartFragmentView, IBasicActivityHandler, StatsComponent,
        SocialMediaLikesStatisticsEntity>
        implements ILikesChartFragmentView {

    /**
     * Kid Identity Arg
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Social Media Type Enum
     */
    private enum SocialMediaTypeEnum { INSTAGRAM, FACEBOOK, YOUTUBE }

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;

    /**
     * Total Likes
     */
    @State
    protected int totalLikes;

    /**
     * Activity
     */
    @Inject
    protected Activity activity;

    /**
     * Social Media Labels
     */
    private String[] socialMediaLabels = new String[SocialMediaTypeEnum.values().length];


    public LikesChartMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static LikesChartMvpFragment newInstance(final String kidIdentity) {
        LikesChartMvpFragment fragment = new LikesChartMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() == null ||
                !getArguments().containsKey(KID_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide the kid identity");

        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);

    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        if(appUtils.isValidString(kidIdentity))
            args.putString(LikesChartFragmentPresenter.KID_IDENTITY_ARG, kidIdentity);
        return args;
    }

    /**
     * Get Legend Label Color
     * @return
     */
    @Override
    protected int[] getLegendLabelColor() {
        return new int[]{
                ContextCompat.getColor(appContext, R.color.instagram_color),
                ContextCompat.getColor(appContext, R.color.facebook_color),
                ContextCompat.getColor(appContext, R.color.youtube_color)
        };
    }

    /**
     * Get Legend Labels
     * @return
     */
    @Override
    protected String[] getLegendLabels() {
        return new String[] {
                getString(R.string.instagram_brand_title),
                getString(R.string.facebook_brand_title),
                getString(R.string.youtube_brand_title)
        };
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_likes;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(StatsComponent component) {
        component.inject(this);
    }

    /**
     * Get Value Formatter
     * @return
     */
    @Override
    protected IValueFormatter getValueFormatter() {
        return new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex,
                                            ViewPortHandler viewPortHandler) {
                return String.format(Locale.getDefault(), "%d/%d", (int)value, totalLikes);
            }
        };
    }

    /**
     * On Load Data
     */
    @Override
    protected void onLoadData() {
        getPresenter().loadData(kidIdentity);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public LikesChartFragmentPresenter providePresenter() {
        return component.likesChartFragmentPresenter();
    }

    /**
     * On Value Selected
     * @param e
     * @param h
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        int selectedIdx = (int)e.getX();
        navigator.showLikesBySocialMediaDialog((AppCompatActivity) getActivity(),
                selectedIdx, socialMediaLabels[selectedIdx]);
    }

    /**
     * On Nothing Selected
     */
    @Override
    public void onNothingSelected() { }

    /**
     * On Data Avaliable
     * @param socialMediaLikesStatisticsEntity
     */
    @Override
    public void onDataAvaliable(final SocialMediaLikesStatisticsEntity socialMediaLikesStatisticsEntity) {
        super.onDataAvaliable(socialMediaLikesStatisticsEntity);
        Preconditions.checkNotNull(socialMediaLikesStatisticsEntity, "Chart Data can not be null");

        if(chartTitleTextView != null)
            chartTitleTextView.setText(socialMediaLikesStatisticsEntity.getTitle());

        if(chartSubTitleTextView != null)
            chartSubTitleTextView.setText(socialMediaLikesStatisticsEntity.getSubtitle());

        totalLikes = socialMediaLikesStatisticsEntity.getTotalLikes();
        List<BarEntry> entries = new ArrayList<>();
        for(int i = 0; i < SocialMediaTypeEnum.values().length; i++ ) {
            final SocialMediaTypeEnum socialMediaTypeEnum = SocialMediaTypeEnum.values()[i];
            int j = 0;
            for(; j < socialMediaLikesStatisticsEntity.getSocialMediaLikesEntities().size(); j++) {
                final SocialMediaLikesStatisticsEntity.SocialMediaLikesEntity socialMediaLikesEntity
                        = socialMediaLikesStatisticsEntity.getSocialMediaLikesEntities().get(j);
                if(socialMediaLikesEntity.getType().name().equals(socialMediaTypeEnum.name())) {
                    entries.add(new BarEntry(i, socialMediaLikesEntity.getLikes()));
                    socialMediaLabels[i] = socialMediaLikesEntity.getLabel();
                    break;
                }
            }
            if(j == socialMediaLikesStatisticsEntity.getSocialMediaLikesEntities().size()){
                entries.add(new BarEntry(i, 0));
                socialMediaLabels[i] = "0";
            }
        }

        // Set Chart Data
        setChartData(entries, totalLikes);
    }

    /**
     * On Show All Comments Extracted
     */
    @OnClick(R.id.showAllCommentsExtracted)
    protected void onShowAllCommentsExtracted(){
        navigator.navigateToComments(activity, kidIdentity);
    }

    /**
     * On Refresh Data
     */
    @OnClick(R.id.refreshData)
    protected void onRefreshData(){
        if(!activityHandler.isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            refreshChart();
        }
    }
}
