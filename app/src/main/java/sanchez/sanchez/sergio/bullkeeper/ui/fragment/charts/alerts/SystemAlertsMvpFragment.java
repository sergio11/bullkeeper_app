package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.github.mikephil.charting.data.PieEntry;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.SupportPieChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

/**
 * System Alerts Mvp Fragment
 */
public class SystemAlertsMvpFragment
        extends SupportPieChartMvpFragment<SystemAlertsFragmentPresenter,
                ISystemAlertsFragmentView, IBasicActivityHandler, StatsComponent>
        implements ISystemAlertsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


    /**
     * Kid Identity
     */
    private String kidIdentity;


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

    /**
     * On System Alerts Loaded
     * @param entries
     */
    @Override
    public void onSystemAlertsLoaded(final List<PieEntry> entries) {

        setChartData(entries, new int[] {
                R.color.cyanBrilliant,
                R.color.greenSuccess,
                R.color.moderateRed,
                R.color.softOrange
        });

    }
}
