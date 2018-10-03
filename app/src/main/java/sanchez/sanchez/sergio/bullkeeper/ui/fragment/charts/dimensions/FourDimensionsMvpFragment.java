package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.dimensions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import com.fernandocejas.arrow.checks.Preconditions;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.SupportBarChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;

/**
 * Four Dimensions Fragment
 */
public class FourDimensionsMvpFragment
        extends SupportBarChartMvpFragment<FourDimensionsFragmentPresenter,
                        IFourDimensionsFragmentView, IBasicActivityHandler, StatsComponent>
        implements IFourDimensionsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


    /**
     * Dimensions Category Enum
     */
    public enum DimensionCategoryEnum {
        VIOLENCE, DRUGS, ADULT, BULLYING
    }

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;

    /**
     * Dimensions Labels
     */
    protected String[] dimensionsLabel = new String[4];

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
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        if(getArguments() != null &&
                getArguments().containsKey(KID_IDENTITY_ARG))
            args.putString(FourDimensionsFragmentPresenter.KIDS_IDENTITY_ARG,
                    getArguments().getString(KID_IDENTITY_ARG));
        return args;
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
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() == null || !getArguments().containsKey(KID_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide the Kid Identity");

        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);

        noResultsFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noResultsFound.setVisibility(View.GONE);
                loadingResultsView.setVisibility(View.VISIBLE);
                chartDataContainerView.setVisibility(View.GONE);
                getPresenter().loadData(kidIdentity);
            }
        });
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
                int dimensionIdx = (int) entry.getX();
                return dimensionIdx < dimensionsLabel.length ? dimensionsLabel[dimensionIdx]: "";
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
     * @param dimensionEntities
     */
    @Override
    public void onDimensionsDataLoaded(List<DimensionEntity> dimensionEntities) {
        Preconditions.checkNotNull(dimensionEntities, "Dimensions Entities can not be null");

        List<BarEntry> entries = new ArrayList<>();
        for(int i = 0; i < DimensionCategoryEnum.values().length; i++ ) {
            final DimensionCategoryEnum dimensionCategoryEnum = DimensionCategoryEnum.values()[i];
            int j = 0;
            for(; j < dimensionEntities.size(); j++) {
                final DimensionEntity dimensionEntity  = dimensionEntities.get(j);
                if(dimensionEntity.getDimensionCategoryEnum().name().equals(dimensionCategoryEnum.name())) {
                    entries.add(new BarEntry(i, dimensionEntity.getValue()));
                    dimensionsLabel[i] = dimensionEntity.getLabel();
                    break;
                }
            }
            if(j == dimensionEntities.size()){
                entries.add(new BarEntry(i, 0));
                dimensionsLabel[i] = "0";
            }
        }

        // Set Chart Data
        setChartData(entries);

        loadingResultsView.setVisibility(View.GONE);
        noResultsFound.setVisibility(View.GONE);
        chartDataContainerView.setVisibility(View.VISIBLE);
    }

    /**
     * On NO Dimensions Data Avaliable
     */
    @Override
    public void onNoDimensionsDataAvaliable() {
        loadingResultsView.setVisibility(View.GONE);
        chartDataContainerView.setVisibility(View.GONE);
        noResultsFound.setVisibility(View.VISIBLE);
    }

    /**
     * On Value Selected
     * @param e
     * @param h
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {

        final int dimensionIdx = (int)e.getX();

        if(listener != null)
            listener.onDimensionsSelected(dimensionIdx, dimensionsLabel[dimensionIdx]);

        navigator.showFourDimensionsDialog((AppCompatActivity)getActivity(),
                dimensionIdx, dimensionsLabel[dimensionIdx]);
    }

    /**
     * On Nothing Selected
     */
    @Override
    public void onNothingSelected() {}

    /**
     * On Four Dimensions Listener
     */
    public interface OnFourDimensionsListener {

        /**
         * On Dimensions Selected
         * @param dimensionIdx
         * @param dimensionValue
         */
        void onDimensionsSelected(int dimensionIdx, final String dimensionValue);

    }


}
