package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.likes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
import java.util.Locale;

import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.chart.SupportBarChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

/**
 * Likes Chart Mvp Fragment
 */
public class LikesChartMvpFragment
        extends SupportBarChartMvpFragment<LikesChartFragmentPresenter,
                ILikesChartFragmentView, IBasicActivityHandler, StatsComponent,
        List<BarEntry>>
        implements ILikesChartFragmentView {

    /**
     * Kid Identity Arg
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;


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
                return String.format(Locale.getDefault(), "%d/%d", (int)value, 17);
            }
        };
    }

    @Override
    protected void onLoadData() {

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


    @Override
    public void onValueSelected(Entry e, Highlight h) {}

    @Override
    public void onNothingSelected() { }

    @Override
    public void onDataAvaliable(List<BarEntry> chartData) {
        super.onDataAvaliable(chartData);
        setChartData(chartData);
    }
}
