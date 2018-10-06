package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.relations;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;

import java.util.List;

import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.chart.SupportPieChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

/**
 * Relations Mvp Fragment
 */
public class RelationsMvpFragment
        extends SupportPieChartMvpFragment<RelationsFragmentPresenter,
        IRelationsFragmentView, IBasicActivityHandler, StatsComponent, String>
        implements IRelationsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;


    public RelationsMvpFragment() {
        // Required empty public constructor
    }

    /**
     * Get Chart Title
     * @return
     */
    @Override
    protected int getChartTitle() {
        return R.string.relations_title;
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static RelationsMvpFragment newInstance(final String kidIdentity) {
        RelationsMvpFragment fragment = new RelationsMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_relations;
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
    public RelationsFragmentPresenter providePresenter() {
        return component.relationsFragmentPresenter();
    }

    @Override
    protected int[] getLegendLabelColor() {
        return new int[] {
                R.color.softOrange,
                R.color.cyanBrilliant,
                R.color.darkModerateBlue,
                R.color.moderateRed
        };
    }

    @Override
    protected IValueFormatter getValueFormatter() {
        return null;
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
