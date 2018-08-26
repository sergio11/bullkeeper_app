package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.github.mikephil.charting.data.PieEntry;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.SupportPieChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.support.IBasicActivityHandler;

/**
 * Activity Social Media
 */
public class ActivitySocialMediaMvpFragment
        extends SupportPieChartMvpFragment<ActivitySocialMediaFragmentPresenter,
                IActivitySocialMediaFragmentView, IBasicActivityHandler, StatsComponent>
        implements IActivitySocialMediaFragmentView {

    /**
     * Kid Identity Arg
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Kid Identity
     */
    private String kidIdentity;


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
     * On Social Media Activity Loaded
     * @param entries
     */
    @Override
    public void onSocialMediaActivityLoaded(List<PieEntry> entries) {

        setChartData(entries, new int[] {
                R.color.facebook_color,
                R.color.instagram_color,
                R.color.youtube_color
        });
    }

}
