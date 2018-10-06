package sanchez.sanchez.sergio.bullkeeper.core.ui.chart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import net.grandcentrix.thirtyinch.TiPresenter;
import java.util.List;
import butterknife.BindView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportChartDataView;
import sanchez.sanchez.sergio.bullkeeper.di.components.ActivityComponent;

/**
 * Support Bar Chart Mvp Fragment
 */
public abstract class SupportBarChartMvpFragment<P extends TiPresenter<V>, V extends ISupportChartDataView<I>,
        H extends IBasicActivityHandler, C extends ActivityComponent, I>
            extends SupportChartMvpFragment<P, V, H, C, I> {

    /**
     * Views
     * ==========
     */

    /**
     * Bar Chart
     */
    @BindView(R.id.barChart)
    protected BarChart barChart;


    public SupportBarChartMvpFragment() {
        // Required empty public constructor
    }

    /**
     * Get Legend Labels
     * @return
     */
    protected abstract String[] getLegendLabels();


    /**
     * Config Chart Legend
     */
    private void configChartLegend(final int[] legendLabelColor,
                                   final  String[] legendLabels){

        final Legend legend = barChart.getLegend();
        legend.setExtra(legendLabelColor, legendLabels);
        legend.setFormSize(10f); // set the size of the legend forms/shapes
        legend.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        legend.setTextSize(12f);
        legend.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        legend.setYEntrySpace(5f); // set the space between the legend entries on the y-axis
    }

    /**
     * Config Chart Axis
     */
    private void configChartAxis(){

        // Config XAxis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);

        YAxis yLeftAxis = barChart.getAxisLeft();
        yLeftAxis.setTextSize(12f);
        yLeftAxis.setAxisMinimum(0f); // start at zero
        yLeftAxis.setAxisMaximum(17f); // the axis maximum is 100
        yLeftAxis.setTextColor(ContextCompat.getColor(appContext,
                R.color.darkModerateBlue));
        yLeftAxis.setGranularity(1f); // interval 1
        yLeftAxis.setLabelCount(6, true); // force 6 labels
        yLeftAxis.setDrawZeroLine(false);
        yLeftAxis.setDrawAxisLine(true);
        yLeftAxis.setDrawGridLines(false);

        YAxis yRightAxis = barChart.getAxisRight();
        yRightAxis.setEnabled(false);
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.animateY(9000, Easing.EasingOption.EaseOutBack);
        barChart.setOnChartValueSelectedListener(this);
        // Config Chart Legend
        configChartLegend(getLegendLabelColor(), getLegendLabels());
        // Config Chart Axis
        configChartAxis();
    }


    /**
     * Config Bar Data
     */
    private BarData createBarData(final List<BarEntry> entries){

        final int darkModerateBlue = ContextCompat.getColor(appContext,
                R.color.darkModerateBlue);
        final BarDataSet fourDimensionsDataSet = new BarDataSet(entries, null);
        fourDimensionsDataSet.setBarBorderColor(darkModerateBlue);
        fourDimensionsDataSet.setBarShadowColor(darkModerateBlue);
        fourDimensionsDataSet.setValueTextColor(darkModerateBlue);
        fourDimensionsDataSet.setValueTextSize(15f);
        fourDimensionsDataSet.setColors(getLegendLabelColor());
        fourDimensionsDataSet.setValueFormatter(getValueFormatter());
        BarData fourDimensionsBarData = new BarData(fourDimensionsDataSet);
        fourDimensionsBarData.setBarWidth(.7f); // set custom bar width
        return fourDimensionsBarData;

    }

    /**
     * Set Chart Data
     * @param entries
     */
    protected final void setChartData(final List<BarEntry> entries) {
        final BarData fourDimensionsBarData = createBarData(entries);
        barChart.setData(fourDimensionsBarData);
    }
}
