package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.chart.SupportPieChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.AlertsStatisticsEntity;

/**
 * System Alerts Mvp Fragment
 */
public class SystemAlertsMvpFragment
        extends SupportPieChartMvpFragment<SystemAlertsFragmentPresenter,
                ISystemAlertsFragmentView, IBasicActivityHandler, StatsComponent, AlertsStatisticsEntity>
        implements ISystemAlertsFragmentView {


    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Alert Level Tyoe Enum
     */
    public enum AlertLevelTypeEnum { INFO, SUCCESS, DANGER ,WARNING }

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;


    public SystemAlertsMvpFragment() {
        // Required empty public constructor
    }

    /**
     * Get Chart Title
     * @return
     */
    @Override
    protected int getChartTitle() {
        return R.string.system_alerts_chart_title;
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static SystemAlertsMvpFragment newInstance(final String kidIdentity) {
        SystemAlertsMvpFragment fragment = new SystemAlertsMvpFragment();
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
            throw new IllegalArgumentException("You must provide the kid identity");

        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);

    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        if(appUtils.isValidString(kidIdentity))
            args.putString(SystemAlertsFragmentPresenter.KID_IDENTITY_ARG,
                    kidIdentity);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_system_alerts;
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
    public SystemAlertsFragmentPresenter providePresenter() {
        return component.systemAlertsFragmentPresenter();
    }

    /**
     * Get Legend Label Color
     * @return
     */
    @Override
    protected int[] getLegendLabelColor() {
        return new int[] {
                R.color.cyanBrilliant,
                R.color.greenSuccess,
                R.color.moderateRed,
                R.color.yellowWarning
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
        final PieEntry pieEntry = (PieEntry)e;
        navigator.showAlertLevelDialog((AppCompatActivity)getActivity(),
                AlertLevelEnum.valueOf(pieEntry.getLabel()), (int)pieEntry.getValue() + "%",
                kidIdentity);

    }

    /**
     * On Nothing Selected
     */
    @Override
    public void onNothingSelected() { }


    /**
     * On Data Avaliable
     * @param chartData
     */
    @Override
    public void onDataAvaliable(AlertsStatisticsEntity chartData) {
        super.onDataAvaliable(chartData);

        final List<PieEntry> entries = new ArrayList<>();
        for(int i = 0; i < AlertLevelTypeEnum.values().length; i++ ) {
            final AlertLevelTypeEnum alertLevelType = AlertLevelTypeEnum.values()[i];
            int j = 0;
            for(; j < chartData.getData().size(); j++) {

                final AlertsStatisticsEntity.AlertLevelEntity alertLevelEntity
                        = chartData.getData().get(j);

                if(alertLevelEntity.getLevel().name().equals(alertLevelType.name())) {
                    entries.add(new PieEntry(alertLevelEntity.getTotal(), alertLevelType.name()));
                    break;
                }
            }

            if(j == chartData.getData().size()){
                entries.add(new PieEntry(0));
            }
        }
        setChartData(entries);
    }

    /**
     * Refresh Data
     */
    @OnClick(R.id.refreshData)
    protected void onRefreshData(){
        refreshChart();
    }

    /**
     * On Show All Alerts
     */
    @OnClick(R.id.showAllAlerts)
    protected void onShowAllAlerts(){
        navigator.navigateToAlertList(kidIdentity);
    }
}
