package sanchez.sanchez.sergio.bullkeeper.core.ui.chart;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import net.grandcentrix.thirtyinch.TiPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportChartDataView;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.ActivityComponent;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;

/**
 * Support Chart Mvp Fragment
 * @param <P>
 * @param <V>
 * @param <H>
 * @param <C>
 * @param <I>
 */
public abstract class SupportChartMvpFragment<P extends TiPresenter<V>, V extends ISupportChartDataView<I>,
        H extends IBasicActivityHandler, C extends ActivityComponent, I>
        extends SupportMvpFragment<P, V, H, C> implements OnChartValueSelectedListener,
        ISupportChartDataView<I>  {

    /**
     * Dependencies
     * ==================
     */

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
     * Views
     * ==================
     */

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


    /**
     * Show Chart Data Container
     */
    protected void showChartDataContainer(){
        loadingResultsView.setVisibility(View.GONE);
        noResultsFound.setVisibility(View.GONE);
        errorResultsView.setVisibility(View.GONE);
        chartDataContainerView.setVisibility(View.VISIBLE);

    }

    /**
     * Show No Results Found
     */
    protected void showNoResultsFound(){
        loadingResultsView.setVisibility(View.GONE);
        chartDataContainerView.setVisibility(View.GONE);
        noResultsFound.setVisibility(View.VISIBLE);
        errorResultsView.setVisibility(View.GONE);
    }

    /**
     * Show Loading Results
     */
    protected void showLoadingResults(){
        loadingResultsView.setVisibility(View.VISIBLE);
        chartDataContainerView.setVisibility(View.GONE);
        noResultsFound.setVisibility(View.GONE);
        errorResultsView.setVisibility(View.GONE);
    }

    /**
     * Show Error Results
     */
    protected void showErrorResults(){
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
     * Get Value Formatter
     * @return
     */
    protected abstract IValueFormatter getValueFormatter();

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
