package sanchez.sanchez.sergio.masom_app.ui.fragment.dimensions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.masom_app.ui.support.IBasicActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportFragment;

/**
 * Four Dimensions Fragment
 */
public class FourDimensionsFragment
        extends SupportFragment<FourDimensionsFragmentPresenter,
        IFourDimensionsFragmentView, IBasicActivityHandler, MyKidsComponent> implements IFourDimensionsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    @Inject
    protected Context appContext;

    /**
     * Kid Identity
     */
    private String kidIdentity;


    /**
     * Four Dimension Chart
     */
    @BindView(R.id.fourDimensionChart)
    protected BarChart fourDimensionChart;


    public FourDimensionsFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static FourDimensionsFragment newInstance(final String kidIdentity) {
        FourDimensionsFragment fragment = new FourDimensionsFragment();
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

        fourDimensionChart.setDrawBarShadow(false);
        fourDimensionChart.setDrawValueAboveBar(true);

        fourDimensionChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        fourDimensionChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        fourDimensionChart.setPinchZoom(false);
        fourDimensionChart.setDoubleTapToZoomEnabled(false);

        fourDimensionChart.setDrawGridBackground(false);

        setData(12, 50);

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

    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = (int) start; i < start + count + 1; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);

            yVals1.add(new BarEntry(i, val));
        }

        BarDataSet set1;

        if (fourDimensionChart.getData() != null &&
                fourDimensionChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) fourDimensionChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            fourDimensionChart.getData().notifyDataChanged();
            fourDimensionChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "The year 2017");

            set1.setDrawIcons(false);


            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            fourDimensionChart.setData(data);
        }
    }
}
