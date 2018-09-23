package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import com.fernandocejas.arrow.checks.Preconditions;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.ActivityComponent;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;

/**
 * Support Pie Chart Mvp Fragment
 */
public abstract class SupportPieChartMvpFragment<P extends TiPresenter<V>, V extends ISupportView,
        H extends IBasicActivityHandler, C extends ActivityComponent>
            extends SupportMvpFragment<P, V, H, C> {


    /**
     * App Context
     */
    @Inject
    protected Context appContext;

    /**
     * Navigator
     */
    @Inject
    protected INavigator navigator;

    /**
     * Chart Type Face
     */
    private Typeface chartTypeFace;


    /**
     * Pie Chart
     */
    @BindView(R.id.pieChart)
    protected PieChart pieChart;

    public SupportPieChartMvpFragment() {
        // Required empty public constructor
    }


    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pieChart.getDescription().setEnabled(false);

        chartTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/VAGRoundedBT.ttf");

        pieChart.setCenterTextTypeface(chartTypeFace);
        pieChart.setCenterText(generateCenterText());
        pieChart.setCenterTextSize(9f);
        pieChart.setCenterTextTypeface(chartTypeFace);

        // radius of the center hole in percent of maximum radius
        pieChart.setHoleRadius(45f);
        pieChart.setTransparentCircleRadius(50f);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        pieChart.animateY(9000, Easing.EasingOption.EaseOutBack);


    }

    /**
     * Generate Center Text
     * @return
     */
    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString(getContext().getString(getChartTitle()));
        s.setSpan(new RelativeSizeSpan(2f), 0, 11, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 11, s.length(), 0);
        return s;
    }

    /**
     * Get Chart Title
     * @return
     */
    @StringRes
    protected abstract int getChartTitle();


    /**
     * Set Chart Data
     * @param entries
     */
    protected final void setChartData(final List<PieEntry> entries, final int[] colors) {

        Preconditions.checkNotNull(entries, "Entries can not be null");
        Preconditions.checkNotNull(colors, "Colors can not be null");

        final PieDataSet pieDataSet = new PieDataSet(entries, null);
        pieDataSet.setColors(colors, appContext);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int)value + "%";
            }
        });

        final PieData pieData = new PieData(pieDataSet);
        pieData.setValueTypeface(chartTypeFace);

        pieChart.setData(pieData);
    }

}
