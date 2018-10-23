package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.fernandocejas.arrow.checks.Preconditions;
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
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import timber.log.Timber;

/**
 * Activity Social Media Chart
 */
public class ActivitySocialMediaMvpFragment
        extends SupportPieChartMvpFragment<ActivitySocialMediaFragmentPresenter,
                IActivitySocialMediaFragmentView, IBasicActivityHandler, StatsComponent, SocialMediaActivityStatisticsEntity>
        implements IActivitySocialMediaFragmentView {

    /**
     * Kid Identity Arg
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Social Media Enum
     */
    private enum SocialMediaTypesEnum { FACEBOOK, INSTAGRAM, YOUTUBE }

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;


    public ActivitySocialMediaMvpFragment() {
        // Required empty public constructor
    }

    /**
     * Get Chart Title
     * @return
     */
    @Override
    protected int getChartTitle() {
        return R.string.activity_in_social_media_title;
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static ActivitySocialMediaMvpFragment newInstance(final String kidIdentity) {
        ActivitySocialMediaMvpFragment fragment = new ActivitySocialMediaMvpFragment();
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
                getArguments().containsKey(KID_IDENTITY_ARG)) {
            args.putString(ActivitySocialMediaFragmentPresenter.KIDS_IDENTITY_ARG, kidIdentity);
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

        if (getArguments() == null ||
                !getArguments().containsKey(KID_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide the kid identity");

        // Get Kid Identity
        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_activity_social_media;
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
    public ActivitySocialMediaFragmentPresenter providePresenter() {
        return component.activitySocialMediaFragmentPresenter();
    }

    /**
     * Get Legend Label Color
     * @return
     */
    @Override
    protected int[] getLegendLabelColor() {
        return new int[] {
                R.color.facebook_color,
                R.color.instagram_color,
                R.color.youtube_color
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
        final PieEntry pieEntry = (PieEntry) e;
        Timber.d("Pie Entry selected -> %s - %f (%f)",
                pieEntry.getLabel(), pieEntry.getValue(), pieEntry.getY());

        navigator.showSocialActivityDialog((AppCompatActivity) getActivity(),
                SocialMediaEnum.valueOf(pieEntry.getLabel()),
                (int)pieEntry.getValue() + "%");
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
    public void onDataAvaliable(SocialMediaActivityStatisticsEntity chartData) {
        super.onDataAvaliable(chartData);

        Preconditions.checkNotNull(chartData, "Chart Data can not be null");

        List<PieEntry> entries = new ArrayList<>();
        for(int i = 0; i < SocialMediaTypesEnum.values().length; i++ ) {
            final SocialMediaTypesEnum socialMediaTypesEnum = SocialMediaTypesEnum.values()[i];
            int j = 0;
            for(; j < chartData.getActivities().size(); j++) {
                final SocialMediaActivityStatisticsEntity.ActivityEntity activityEntity
                        = chartData.getActivities().get(j);
                if(activityEntity.getSocialMediaEnum().name().equals(socialMediaTypesEnum.name())) {
                    entries.add(new PieEntry(activityEntity.getValue(), socialMediaTypesEnum.name()));
                    break;
                }
            }
            if(j == chartData.getActivities().size()){
                entries.add(new PieEntry( 0));
            }
        }

        // Set Chart Data
        setChartData(entries);
    }

    /**
     * On Refresh Data Clicked
     */
    @OnClick(R.id.refreshData)
    protected void onRefreshDataClicked() {
        refreshChart();
    }
}
