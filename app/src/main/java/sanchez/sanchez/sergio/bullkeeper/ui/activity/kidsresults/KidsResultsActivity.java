package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidsresults;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerStatsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.activity.ActivitySocialMediaMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts.SystemAlertsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.comments.CommentsExtractedBySocialMediaMvpBySocialMediaFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.dimensions.FourDimensionsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.likes.LikesChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.relations.RelationsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment.SentimentAnalysisMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Kids Results Activity
 */
public class KidsResultsActivity extends SupportMvpActivity<KidsResultsActivityPresenter, IKidsResultsView>
        implements HasComponent<StatsComponent>, KidsResultsActivityHandler
        , IKidsResultsView {

    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Sections Pager Adapter
     */
    private SectionsPagerAdapter sectionsPagerAdapter;

    /**
     * Results Component
     */
    private StatsComponent statsComponent;

    /**
     * State
     */

    @State
    protected String kidIdentity;


    /**
     * Profile Image View
     */
    @BindView(R.id.profileImage)
    protected ImageView profileImageView;

    /**
     * Kid Name
     */
    @BindView(R.id.kidName)
    protected TextView kidNameText;


    /**
     * Tabs
     */
    @BindView(R.id.tabs)
    protected TabLayout tabLayout;

    /**
     * View Pager
     */
    @BindView(R.id.viewpager)
    protected ViewPager viewPager;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * Unselected tab icons
     */
    private int[] unselectedTabIcons = {
            R.drawable.dimensions_tab_cyan,
            R.drawable.comment_tab,
            R.drawable.activity_social_media_solid,
            R.drawable.activity_social_media_solid,
            R.drawable.alerts_kids_solid,
            R.drawable.social_likes_results,
            R.drawable.most_active_friends_solid
    };

    /**
     * Selected Tab icons
     */
    private int[] selectedTabIcons = {
            R.drawable.dimensions_tab_dark_cyan,
            R.drawable.comment_tab_dark,
            R.drawable.activity_social_media_solid_dark,
            R.drawable.activity_social_media_solid_dark,
            R.drawable.alerts_kids_solid_dark,
            R.drawable.social_likes_results_dark,
            R.drawable.most_active_friends_solid_dark
    };


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity) {
        final Intent callingIntent = new Intent(context, KidsResultsActivity.class);
        callingIntent.putExtra(KID_IDENTITY_ARG, identity);
        return callingIntent;
    }

    /**
     * Toggle All Components
     * @param isEnable
     */
    private void toggleAllComponents(final boolean isEnable) {
        profileImageView.setEnabled(isEnable);
        kidNameText.setEnabled(isEnable);
        tabLayout.setEnabled(isEnable);
        viewPager.setEnabled(isEnable);
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        toggleAllComponents(false);

        if (getIntent() == null &&
                !getIntent().hasExtra(KID_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide the son id");

        kidIdentity = getIntent().getStringExtra(KID_IDENTITY_ARG);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setPageTransformer(true, new RotateUpTransformer());

        tabLayout.setupWithViewPager(viewPager);

        for(int i = 0; i < tabLayout.getTabCount(); i++) {
            final TabLayout.Tab tab = tabLayout.getTabAt(i);
            if(tab != null)
                tab.setIcon( i == 0 ? selectedTabIcons[i] : unselectedTabIcons[i]);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(selectedTabIcons[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(unselectedTabIcons[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        toggleAllComponents(true);

    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {

        this.statsComponent = DaggerStatsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

        this.statsComponent.inject(this);

    }

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_kids_results;
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public StatsComponent getComponent() {
        return statsComponent;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public KidsResultsActivityPresenter providePresenter() {
        return statsComponent.kidsResultsActivityPresenter();
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return SupportToolbarApp.RETURN_TOOLBAR;
    }

    /**
     * On Settings Clicked
     */
    @OnClick(R.id.settings)
    protected void onSettingsClicked(){
        showShortMessage("Settings");
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        if (getIntent() != null &&
                getIntent().hasExtra(KID_IDENTITY_ARG))
            args.putString(KidsResultsActivityPresenter.KID_IDENTITY_ARG,
                    kidIdentity);
        return args;
    }

    /**
     * On Son Loaded
     * @param sonEntity
     */
    @Override
    public void onSonLoaded(SonEntity sonEntity) {

        if(appUtils.isValidString(sonEntity.getProfileImage()))
            picasso.load(sonEntity.getProfileImage())
                .placeholder(R.drawable.kid_default_image)
                .error(R.drawable.kid_default_image)
                .noFade()
                .into(profileImageView);
        else
            profileImageView.setImageResource(R.drawable.kid_default_image);

        kidNameText.setText(sonEntity.getFullName());

    }

    /**
     * Sections Page Adapter
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        private final static int DIMENSIONS_TAB = 0;
        private final static int COMMENTS_EXTRACTED_TAB = 1;
        private final static int SOCIAL_MEDIA_TAB = 2;
        private final static int SENTIMENT_ANALYSIS_TAB = 3;
        private final static int ALERTS_TAB = 4;
        private final static int LIKES_TAB = 5;
        private final static int RELATIONS_TAB = 6;

        private final static int SECTION_COUNT = 7;

        /**
         * Sections Pager Adapter
         * @param fm
         */
        public SectionsPagerAdapter(FragmentManager fm) { super(fm); }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case DIMENSIONS_TAB:
                    return FourDimensionsMvpFragment.newInstance(kidIdentity);
                case COMMENTS_EXTRACTED_TAB:
                    return CommentsExtractedBySocialMediaMvpBySocialMediaFragment.newInstance(kidIdentity);
                case SOCIAL_MEDIA_TAB:
                    return ActivitySocialMediaMvpFragment.newInstance(kidIdentity);
                case SENTIMENT_ANALYSIS_TAB:
                    return SentimentAnalysisMvpFragment.newInstance(kidIdentity);
                case ALERTS_TAB:
                    return SystemAlertsMvpFragment.newInstance(kidIdentity);
                case LIKES_TAB:
                    return LikesChartMvpFragment.newInstance(kidIdentity);
                case RELATIONS_TAB:
                    return RelationsMvpFragment.newInstance(kidIdentity);
            }
            return null;
        }

        /**
         * Get Count
         * @return
         */
        @Override
        public int getCount() {
            return SECTION_COUNT;
        }

        /**
         * Get Page Title
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case DIMENSIONS_TAB:
                    return getString(R.string.dimensions_tab);
                case COMMENTS_EXTRACTED_TAB:
                    return getString(R.string.comments_extracted_tab);
                case SOCIAL_MEDIA_TAB:
                    return getString(R.string.activity_social_media_tab);
                case SENTIMENT_ANALYSIS_TAB:
                    return getString(R.string.sentiment_analysis_tab);
                case ALERTS_TAB:
                    return getString(R.string.alerts_tab);
                case LIKES_TAB:
                    return getString(R.string.likes_social_media_tab);
                case RELATIONS_TAB:
                    return getString(R.string.relations_tab);
            }
            return null;
        }
    }
}
