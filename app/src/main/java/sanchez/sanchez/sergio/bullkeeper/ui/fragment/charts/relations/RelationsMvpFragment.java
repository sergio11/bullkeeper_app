package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.relations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.fernandocejas.arrow.checks.Preconditions;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.chart.SupportPieChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.domain.models.MostActiveFriendsBySocialMediaStatisticsEntity;

/**
 * Relations Mvp Fragment
 */
public class RelationsMvpFragment
        extends SupportPieChartMvpFragment<RelationsFragmentPresenter,
        IRelationsFragmentView, IBasicActivityHandler, StatsComponent, MostActiveFriendsBySocialMediaStatisticsEntity>
        implements IRelationsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    private static final int MAX_FIENDS_TO_SHOW = 4;


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
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() == null ||
                !getArguments().containsKey(KID_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide kid identity arg");

        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);
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

    /**
     * Get Legend Label Color
     * @return
     */
    @Override
    protected int[] getLegendLabelColor() {
        return new int[] {
                R.color.softOrange,
                R.color.cyanBrilliant,
                R.color.darkModerateBlue,
                R.color.moderateRed
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
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value > 0.0f ? (int)value + "%" : "";
            }
        };
    }

    /**
     * On Load Data
     */
    @Override
    protected void onLoadData() {
        getPresenter().loadData(kidIdentity);
    }

    /**
     * On Value Selected
     * @param e
     * @param h
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    /**
     * On Nothing Selected
     */
    @Override
    public void onNothingSelected() {}

    /**
     * On Data Avaliable
     * @param chartData
     */
    @Override
    public void onDataAvaliable(MostActiveFriendsBySocialMediaStatisticsEntity chartData) {
        Preconditions.checkNotNull(chartData, "Chart Data can not be null");
        Preconditions.checkState(chartData.getFriends().size() > 0, "Fiends list can not be empty");
        super.onDataAvaliable(chartData);

        final List<MostActiveFriendsBySocialMediaStatisticsEntity.FriendEntity> friends = chartData.getFriends();

        // Entries list
        List<PieEntry> entries = new ArrayList<>();
        for(int i = 0; i < MAX_FIENDS_TO_SHOW && i < friends.size() ; i++ ) {
            final MostActiveFriendsBySocialMediaStatisticsEntity.FriendEntity friendEntity
                    = friends.get(i);
            entries.add(new PieEntry(friendEntity.getValue(), friendEntity.getName()));
        }

        // Set Chart Data
        setChartData(entries);
    }
}
