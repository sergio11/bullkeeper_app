package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.chart.SupportPieChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;

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
     * Kid Identity
     */
    @State
    protected String kidIdentity;


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
                return (int)value + "%";
            }
        };
    }

    /**
     * On Load Data
     */
    @Override
    protected void onLoadData() {
        // TODO call presenter to refresh data
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
