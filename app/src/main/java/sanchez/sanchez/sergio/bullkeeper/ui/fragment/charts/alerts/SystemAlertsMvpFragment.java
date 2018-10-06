package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.chart.SupportPieChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

/**
 * System Alerts Mvp Fragment
 */
public class SystemAlertsMvpFragment
        extends SupportPieChartMvpFragment<SystemAlertsFragmentPresenter,
                ISystemAlertsFragmentView, IBasicActivityHandler, StatsComponent, Void >
        implements ISystemAlertsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


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

    @Override
    protected int[] getLegendLabelColor() {
        return new int[] {
                R.color.cyanBrilliant,
                R.color.greenSuccess,
                R.color.moderateRed,
                R.color.softOrange
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
