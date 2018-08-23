package sanchez.sanchez.sergio.masom_app.ui.fragment.charts.dimensions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import butterknife.BindView;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.StatsComponent;
import sanchez.sanchez.sergio.masom_app.navigation.INavigator;
import sanchez.sanchez.sergio.masom_app.ui.fragment.charts.SupportBarChartMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.support.IBasicActivityHandler;

/**
 * Four Dimensions Fragment
 */
public class FourDimensionsMvpFragment
        extends SupportBarChartMvpFragment<FourDimensionsFragmentPresenter,
                        IFourDimensionsFragmentView, IBasicActivityHandler, StatsComponent>
        implements IFourDimensionsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    public static final int VIOLENCE = 0;
    public static final int DRUGS = 1;
    public static final int ADULT = 2;
    public static final int BULLYING = 3;


    /**
     * Kid Identity
     */
    private String kidIdentity;


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


    /**
     * Get Legend Label Color
     * @return
     */
    @Override
    protected int[] getLegendLabelColor() {
        return new int[] {
                ContextCompat.getColor(appContext, R.color.violence_color),
                ContextCompat.getColor(appContext, R.color.drugs_color),
                ContextCompat.getColor(appContext, R.color.sex_color),
                ContextCompat.getColor(appContext, R.color.bullying_color)
        };
    }

    /**
     * Get Legend Labels
     * @return
     */
    @Override
    protected String[] getLegendLabels() {
        return new String[] {
                getString(R.string.four_dimension_violence),
                getString(R.string.four_dimension_drugs),
                getString(R.string.four_dimension_adult),
                getString(R.string.four_dimension_bullying)
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
            public String getFormattedValue(float value, Entry entry, int dataSetIndex,
                                            ViewPortHandler viewPortHandler) {
                return String.format(Locale.getDefault(), "%d/%d", (int)value, 17);
            }
        };
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
    protected void initializeInjector(StatsComponent component) {
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
        setChartData(entries);
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
