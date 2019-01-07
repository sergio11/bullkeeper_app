package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.appstats;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.fernandocejas.arrow.checks.Preconditions;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.chart.SupportBarChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.domain.models.AppStatsEntity;
import timber.log.Timber;

/**
 * App Stats Fragment
 */
public class AppStatsMvpFragment
        extends SupportBarChartMvpFragment<AppStatsFragmentPresenter,
        IAppStatsFragmentView, IMyKidsDetailActivityHandler,
        MyKidsComponent, List<AppStatsEntity>>
        implements IAppStatsFragmentView , AdapterView.OnItemSelectedListener{

    /**
     * Args
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    /**
     * Terminals Arg
     */
    private static final String TERMINALS_ARG = "TERMINALS_ARG";


    private static final int TOTAL_APPS_TO_SHOW = 5;

    /**
     * Dependencies
     * ===========
     *
     */

    @Inject
    protected Activity activity;

    /**
     * Views
     * ============
     */

    /**
     * Terminals Spinner
     */
    @BindView(R.id.terminalsSpinner)
    protected AppCompatSpinner terminalsSpinner;

    /**
     *
     * State
     * ============
     *
     */

    /**
     * Kid Identity
     */
    @State
    protected String kid;

    /**
     * Terminal Identity
     *
     */
    @State
    protected String terminal;

    /**
     * Terminals List
     */
    @State
    protected ArrayList<TerminalItem> terminalItems = new ArrayList<>();

    /**
     * Current Terminal Pos
     */
    @State
    protected int currentTerminalPos = 0;


    /**
     * App Stats Labels
     */
    protected String[] appStatsLabel = new String[TOTAL_APPS_TO_SHOW];


    public AppStatsMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kid
     * @param terminalItems
     * @return
     */
    public static AppStatsMvpFragment newInstance(final String kid, final ArrayList<TerminalItem> terminalItems) {
        AppStatsMvpFragment fragment = new AppStatsMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kid);
        args.putSerializable(TERMINALS_ARG, terminalItems);
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
        if(getArguments() != null) {
            args.putString(AppStatsFragmentPresenter.KID_IDENTITY_ARG, kid);
            args.putSerializable(AppStatsFragmentPresenter.TERMINALS_ARG, terminalItems);
            args.putInt(AppStatsFragmentPresenter.CURRENT_TERMINAL_POS_ARG, currentTerminalPos);
        }
        return args;
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

        kid = getArguments().getString(KID_IDENTITY_ARG);

        if (getArguments() == null ||
                !getArguments().containsKey(TERMINALS_ARG))
            throw new IllegalStateException("You must provide terminals list");

        terminalItems = (ArrayList<TerminalItem>) getArguments().getSerializable(TERMINALS_ARG);

        if(terminalItems == null || terminalItems.isEmpty())
            throw new IllegalStateException("Terminals list can not be empty");

        ArrayAdapter<TerminalItem> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item,
                terminalItems);
        terminalsSpinner.setAdapter(adapter);
        terminalsSpinner.setSelection(currentTerminalPos);
        terminalsSpinner.setOnItemSelectedListener(this);
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
                ContextCompat.getColor(appContext, R.color.bullying_color),
                ContextCompat.getColor(appContext, R.color.sex_color)
        };
    }

    /**
     * Get Legend Labels
     * @return
     */
    @Override
    protected String[] getLegendLabels() {
        return new String[] {};
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
                int appStatIdx = (int) entry.getX();
                return appStatIdx < appStatsLabel.length ? appStatsLabel[appStatIdx]: "";
            }
        };
    }

    /**
     * On Load Data
     */
    @Override
    protected void onLoadData() {
        getPresenter().loadData(kid, terminal);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_app_stats;
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
    public AppStatsFragmentPresenter providePresenter() {
        return component.appStatsFragmentPresenter();
    }

    /**
     * On Value Selected
     * @param e
     * @param h
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {

        /*final int dimensionIdx = (int)e.getX();

        navigator.showFourDimensionsDialog((AppCompatActivity)getActivity(),
                dimensionIdx, appStatsLabel[dimensionIdx]);*/
    }

    /**
     * On Data Avaliable
     * @param chartData
     */
    @Override
    public void onDataAvaliable(List<AppStatsEntity> chartData) {
        Preconditions.checkNotNull(chartData, "Chart Data can not be null");

        List<BarEntry> entries = new ArrayList<>();
        for(int i = 0; i < TOTAL_APPS_TO_SHOW; i++) {
            if(chartData.size() > i) {
                final AppStatsEntity appStatsEntity = chartData.get(i);
                entries.add(new BarEntry(i, appStatsEntity.getTotalTimeInForeground()));
                appStatsLabel[i] = appStatsEntity.getPackageName();
            } else {
                entries.add(new BarEntry(i, 0));
                appStatsLabel[i] = "0";
            }
        }

        // Set Chart Data
        setChartData(entries);
    }

    /**
     * On Nothing Selected
     */
    @Override
    public void onNothingSelected() {}


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Timber.d("New Position Selected -> %d", position);
        currentTerminalPos = position;
        terminal = terminalItems.get(currentTerminalPos).getIdentity();
        getPresenter().loadData(kid, terminal);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
