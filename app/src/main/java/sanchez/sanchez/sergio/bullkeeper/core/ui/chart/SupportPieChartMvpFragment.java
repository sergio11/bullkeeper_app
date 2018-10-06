package sanchez.sanchez.sergio.bullkeeper.core.ui.chart;

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
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import net.grandcentrix.thirtyinch.TiPresenter;
import java.util.List;
import butterknife.BindView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportChartDataView;
import sanchez.sanchez.sergio.bullkeeper.di.components.ActivityComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

/**
 * Support Pie Chart Mvp Fragment
 */
public abstract class SupportPieChartMvpFragment<P extends TiPresenter<V>, V extends ISupportChartDataView<I>,
        H extends IBasicActivityHandler, C extends ActivityComponent, I>
            extends SupportChartMvpFragment<P, V, H, C, I> {


    /**
     * Chart Type Face
     */
    private Typeface chartTypeFace;


    /**
     * Views
     * ===========
     */

    /**
     * Pie Chart
     */
    @BindView(R.id.pieChart)
    protected PieChart pieChart;



    public SupportPieChartMvpFragment() {
        // Required empty public constructor
    }

    /**
     * Config Chart Legend
     */
    private void configChartLegend(){

        final Legend legend = pieChart.getLegend();
        legend.setFormSize(10f); // set the size of the legend forms/shapes
        legend.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        legend.setTextSize(12f);
        legend.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        legend.setYEntrySpace(5f); // set the space between the legend entries on the y-axis
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

        chartTypeFace = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/VAGRoundedBT.ttf");

        pieChart.setCenterTextTypeface(chartTypeFace);
        pieChart.setCenterText(generateCenterText());
        pieChart.setCenterTextSize(9f);
        pieChart.setCenterTextTypeface(chartTypeFace);
        pieChart.setOnChartValueSelectedListener(this);
        pieChart.setTouchEnabled(true);

        // radius of the center hole in percent of maximum radius
        pieChart.setHoleRadius(45f);
        pieChart.setTransparentCircleRadius(50f);

        configChartLegend();
    }

    /**
     * Generate Center Text
     * @return
     */
    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString(getContext().getString(getChartTitle()));
        s.setSpan(new RelativeSizeSpan(2f), 0, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 0, s.length(), 0);
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
    protected final void setChartData(final List<PieEntry> entries) {
        Preconditions.checkNotNull(entries, "Entries can not be null");

        final PieDataSet pieDataSet = new PieDataSet(entries, null);
        pieDataSet.setColors(getLegendLabelColor(), appContext);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setValueFormatter(getValueFormatter());
        final PieData pieData = new PieData(pieDataSet);
        pieData.setValueTypeface(chartTypeFace);
        pieChart.setData(pieData);
        // Animate Result
        pieChart.animateY(3000, Easing.EasingOption.EaseInBounce);
    }

}
