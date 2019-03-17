package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.fernandocejas.arrow.checks.Preconditions;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.chart.SupportPieChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SentimentLevelEnum;

/**
 * Sentiment Analysis Mvp Fragment
 */
public class SentimentAnalysisMvpFragment
        extends SupportPieChartMvpFragment<SentimentAnalysisFragmentPresenter,
                ISentimentAnalysisFragmentView, IBasicActivityHandler, StatsComponent,
        SentimentAnalysisStatisticsEntity>
        implements ISentimentAnalysisFragmentView {

    /**
     * Kid Identity Arg
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Sentiment Types Enum
     */
    public enum SentimentTypesEnum { POSITIVE, NEGATIVE, NEUTRAL}

    /**
     * Activity
     */
    @Inject
    protected Activity activity;

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;

    /**
     * Sentiment Analysis Mvp Fragment
     */
    public SentimentAnalysisMvpFragment() {
        // Required empty public constructor
    }

    /**
     * Get Chart Title
     * @return
     */
    @Override
    protected int getChartTitle() {
        return R.string.sentiment_analysis_chart_title;
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static SentimentAnalysisMvpFragment newInstance(final String kidIdentity) {
        SentimentAnalysisMvpFragment fragment = new SentimentAnalysisMvpFragment();
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
            throw new IllegalArgumentException("You must provide the Kid Identity");

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
            args.putString(SentimentAnalysisFragmentPresenter.KID_IDENTITY_ARG,
                    kidIdentity);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_sentiment_analysis;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(StatsComponent component) {
        component.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public SentimentAnalysisFragmentPresenter providePresenter() {
        return component.sentimentAnalysisFragmentPresenter();
    }

    /**
     * Get Legend Label Color
     * @return
     */
    @Override
    protected int[] getLegendLabelColor() {
        return new int[] {
                R.color.greenSuccess,
                R.color.redDanger,
                R.color.silver_color
        };
    }


    /**
     * Get Value Formatter
     * @return
     */
    @Override
    protected IValueFormatter getValueFormatter() {
        return new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value > 0.0f ? (int)value + "%" : "";
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
     * On Value Selected
     * @param e
     * @param h
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        final PieEntry pieEntry = (PieEntry)e;
        navigator.showSentimentAnalysisDialog((AppCompatActivity) getActivity(),
                SentimentLevelEnum.valueOf(pieEntry.getLabel()),
                (int)pieEntry.getValue()+"%");

    }

    /**
     * On Nothing Selected
     */
    @Override
    public void onNothingSelected() {}

    /**
     * On Data Avaliable
     * @param chartData
     */
    @Override
    public void onDataAvaliable(SentimentAnalysisStatisticsEntity chartData) {
        Preconditions.checkNotNull(chartData, "Chart Data can not be null");
        super.onDataAvaliable(chartData);

        if(chartTitleTextView != null)
            chartTitleTextView.setText(chartData.getTitle());

        if (chartSubTitleTextView != null)
            chartSubTitleTextView.setText(chartData.getSubtitle());

        // Entries list
        List<PieEntry> entries = new ArrayList<>();

        for(int i = 0; i < SentimentTypesEnum.values().length; i++ ) {
            final SentimentTypesEnum sentimentType = SentimentTypesEnum.values()[i];
            int j = 0;
            for(; j < chartData.getSentimentData().size(); j++) {

                final SentimentAnalysisStatisticsEntity.SentimentEntity sentimentEntity
                        = chartData.getSentimentData().get(j);

                if(sentimentEntity.getSentimentLevelEnum().name().equals(sentimentType.name())) {
                    entries.add(new PieEntry(sentimentEntity.getScore(), sentimentType.name()));
                    break;
                }
            }
            if(j == chartData.getSentimentData().size()){
                entries.add(new PieEntry(0));
            }
        }

        // Set Chart Data
        setChartData(entries);
    }

    /**
     * On Refresh Data
     */
    @OnClick(R.id.refreshData)
    protected void onRefreshData(){
        if (!activityHandler.isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            refreshChart();
        }
    }

    /**
     * On Show All Comments Extracted
     */
    @OnClick(R.id.showAllCommentsExtracted)
    protected void onShowAllCommentsExtractedClicked(){
        navigator.navigateToComments(activity, kidIdentity);
    }
}
