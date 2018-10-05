package sanchez.sanchez.sergio.bullkeeper.core.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.ActivityComponent;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
/**
 * Support Bar Chart Mvp Fragment
 */
public abstract class SupportBarChartMvpFragment<P extends TiPresenter<V>, V extends ISupportChartDataView<I>,
        H extends IBasicActivityHandler, C extends ActivityComponent, I>
            extends SupportMvpFragment<P, V, H, C>  implements OnChartValueSelectedListener,
        ISupportChartDataView<I> {


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
     * Bar Chart
     */
    @BindView(R.id.barChart)
    protected BarChart barChart;

    /**
     * Chart Data Container View
     */
    @BindView(R.id.chartDataContainer)
    protected ViewGroup chartDataContainerView;

    /**
     * No Results Found
     */
    @BindView(R.id.noResultsFound)
    protected ViewGroup noResultsFound;

    /**
     * Loading Results View
     */
    @BindView(R.id.loadingResults)
    protected ViewGroup loadingResultsView;

    /**
     * Error Results View
     */
    @BindView(R.id.errorResultsView)
    protected ViewGroup errorResultsView;


    public SupportBarChartMvpFragment() {
        // Required empty public constructor
    }

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
     * Show Chart Data Container
     */
    private void showChartDataContainer(){
        loadingResultsView.setVisibility(View.GONE);
        noResultsFound.setVisibility(View.GONE);
        errorResultsView.setVisibility(View.GONE);
        chartDataContainerView.setVisibility(View.VISIBLE);

    }

    /**
     * Show No Results Found
     */
    private void showNoResultsFound(){
        loadingResultsView.setVisibility(View.GONE);
        chartDataContainerView.setVisibility(View.GONE);
        noResultsFound.setVisibility(View.VISIBLE);
        errorResultsView.setVisibility(View.GONE);
    }

    /**
     * Show Loading Results
     */
    private void showLoadingResults(){
        loadingResultsView.setVisibility(View.VISIBLE);
        chartDataContainerView.setVisibility(View.GONE);
        noResultsFound.setVisibility(View.GONE);
        errorResultsView.setVisibility(View.GONE);
    }

    /**
     * Show Error Results
     */
    private void showErrorResults(){
        loadingResultsView.setVisibility(View.GONE);
        chartDataContainerView.setVisibility(View.GONE);
        noResultsFound.setVisibility(View.GONE);
        errorResultsView.setVisibility(View.VISIBLE);
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

        showLoadingResults();
    }

    /**
     * Refresh Chart
     */
    protected void refreshChart(){
        noResultsFound.setVisibility(View.GONE);
        errorResultsView.setVisibility(View.GONE);
        chartDataContainerView.setVisibility(View.GONE);
        loadingResultsView.setVisibility(View.VISIBLE);
        onLoadData();
    }

    /**
     * On No Results Found Clicked
     */
    @OnClick(R.id.noResultsFound)
    protected void onNoResultsFoundClicked(){
        refreshChart();
    }

    /**
     * On Error Results View Clicked
     */
    @OnClick(R.id.errorResultsView)
    protected void onErrorResultsViewClicked(){
        refreshChart();
    }

    /**
     * Get Legend Label Color
     * @return
     */
    protected abstract int[] getLegendLabelColor();

    /**
     * Get Legend Labels
     * @return
     */
    protected abstract String[] getLegendLabels();

    /**
     * Get Value Formatter
     * @return
     */
    protected abstract IValueFormatter getValueFormatter();

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

    /**
     * On Load Data
     */
    protected abstract void onLoadData();

    /**
     * On Data Avaliable
     * @param chartData
     */
    @Override
    public void onDataAvaliable(I chartData) {
        showChartDataContainer();
    }

    /**
     * On No Data Avaliable
     */
    @Override
    public void onNoDataAvaliable() {
        showNoResultsFound();
    }

    /**
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        showErrorResults();
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        showErrorResults();
    }



}
