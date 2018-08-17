package sanchez.sanchez.sergio.masom_app.ui.fragment.dimensions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.masom_app.navigation.INavigator;
import sanchez.sanchez.sergio.masom_app.ui.support.IBasicActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportMvpFragment;

/**
 * Four Dimensions Fragment
 */
public class FourDimensionsMvpFragment
        extends SupportMvpFragment<FourDimensionsFragmentPresenter,
                IFourDimensionsFragmentView, IBasicActivityHandler, MyKidsComponent>
        implements IFourDimensionsFragmentView, OnChartValueSelectedListener {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    public static final int VIOLENCE = 0;
    public static final int DRUGS = 1;
    public static final int ADULT = 2;
    public static final int BULLYING = 3;

    @Inject
    protected Context appContext;

    /**
     * Navigator
     */
    @Inject
    protected INavigator navigator;

    /**
     * Kid Identity
     */
    private String kidIdentity;


    /**
     * Four Dimension Chart
     */
    @BindView(R.id.fourDimensionChart)
    protected BarChart fourDimensionChart;

    /**
     * Legend Labels
     */
    private String[] legendLabels;

    /**
     * Legend Label Colors
     */
    private int[] legendLabelColor;

    /**
     * Four Dimensions Data set
     */
    private BarDataSet fourDimensionsDataSet;

    /**
     * On Four Dimensions Listener
     */
    private OnFourDimensionsListener listener;


    public FourDimensionsMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static FourDimensionsMvpFragment newInstance(final String kidIdentity) {
        FourDimensionsMvpFragment fragment = new FourDimensionsMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Config Chart Legend
     */
    private void configChartLegend(){

        final Legend legend = fourDimensionChart.getLegend();
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
        XAxis xAxis = fourDimensionChart.getXAxis();
        xAxis.setEnabled(false);

        YAxis yLeftAxis = fourDimensionChart.getAxisLeft();
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

        YAxis yRightAxis = fourDimensionChart.getAxisRight();
        yRightAxis.setEnabled(false);
    }

    /**
     * Config Bar Data
     */
    private BarData createBarData(final List<BarEntry> entries){

        final int darkModerateBlue = ContextCompat.getColor(appContext,
                R.color.darkModerateBlue);

        fourDimensionsDataSet = new BarDataSet(entries, null);
        fourDimensionsDataSet.setBarBorderColor(darkModerateBlue);
        fourDimensionsDataSet.setBarShadowColor(darkModerateBlue);
        fourDimensionsDataSet.setValueTextColor(darkModerateBlue);
        fourDimensionsDataSet.setValueTextSize(15f);
        fourDimensionsDataSet.setColors(legendLabelColor);
        fourDimensionsDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex,
                                            ViewPortHandler viewPortHandler) {
                return String.format(Locale.getDefault(), "%d/%d", (int)value, 17);
            }
        });

        BarData fourDimensionsBarData = new BarData(fourDimensionsDataSet);
        fourDimensionsBarData.setBarWidth(.7f); // set custom bar width

        return fourDimensionsBarData;

    }

    /**
     * On Attach
     * @param context
     */
    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        try {
            listener = (OnFourDimensionsListener) context;
        } catch (ClassCastException e) {
            listener = null;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        legendLabels = new String[] {
                getString(R.string.four_dimension_violence),
                getString(R.string.four_dimension_drugs),
                getString(R.string.four_dimension_adult),
                getString(R.string.four_dimension_bullying)
        };

        legendLabelColor = new int[] {
                ContextCompat.getColor(appContext, R.color.violence_color),
                ContextCompat.getColor(appContext, R.color.drugs_color),
                ContextCompat.getColor(appContext, R.color.sex_color),
                ContextCompat.getColor(appContext, R.color.bullying_color)
        };
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fourDimensionChart.setDrawBarShadow(false);
        fourDimensionChart.setDrawValueAboveBar(true);
        fourDimensionChart.getDescription().setEnabled(false);
        fourDimensionChart.setPinchZoom(false);
        fourDimensionChart.setDoubleTapToZoomEnabled(false);
        fourDimensionChart.setDrawGridBackground(false);
        fourDimensionChart.animateY(9000, Easing.EasingOption.EaseOutBack);
        fourDimensionChart.setOnChartValueSelectedListener(this);
        // Config Chart Legend
        configChartLegend();
        // Config Chart Axis
        configChartAxis();

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_four_dimensions;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(MyKidsComponent component) {
        component.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public FourDimensionsFragmentPresenter providePresenter() {
        return component.fourDimensionsFragmentPresenter();
    }

    /**
     * On Dimensions Data Loaded
     * @param entries
     */
    @Override
    public void onDimensionsDataLoaded(List<BarEntry> entries) {
        final BarData fourDimensionsBarData = createBarData(entries);
        fourDimensionChart.setData(fourDimensionsBarData);
    }

    /**
     * On Value Selected
     * @param e
     * @param h
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if(listener != null)
            listener.onDimensionsSelected((int)e.getX(), (int)e.getY(), 17);
        navigator.showFourDimensionsDialog((AppCompatActivity)getActivity(),
                (int)e.getX(), (int)e.getY(), 17);
    }

    @Override
    public void onNothingSelected() {

    }

    /**
     * On Four Dimensions Listener
     */
    public interface OnFourDimensionsListener {

        /**
         * On Dimensions Selected
         * @param dimensionIdx
         * @param value
         * @param total
         */
        void onDimensionsSelected(int dimensionIdx, int value, int total);

    }


}
