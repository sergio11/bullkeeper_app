package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail;

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

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerMyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.dimensions.FourDimensionsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts.ImportantAlertsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.relations.KidRelationsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * My Kids Detail Activity
 */
public class MyKidsDetailMvpActivity extends SupportMvpActivity<MyKidsDetailPresenter, IMyKidsDetailView>
        implements HasComponent<MyKidsComponent>, IMyKidsDetailActivityHandler
        , IMyKidsDetailView, FourDimensionsMvpFragment.OnFourDimensionsListener {

    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    private String kidIdentity;

    /**
     * Sections Pager Adapter
     */
    private SectionsPagerAdapter sectionsPagerAdapter;

    /**
     * My Kids Component
     */
    private MyKidsComponent myKidsComponent;


    /**
     * Unselected tab icons
     */
    private int[] unselectedTabIcons = {
            R.drawable.dimensions_tab_cyan,
            R.drawable.important_alerts_tab_cyan,
            R.drawable.relations_tab_cyan
    };

    /**
     * Selected Tab icons
     */
    private int[] selectedTabIcons = {
            R.drawable.dimensions_tab_dark_cyan,
            R.drawable.important_alerts_tab_dark_cyan,
            R.drawable.relations_tab_dark_cyan
    };

    /**
     * Profile Image
     */
    @BindView(R.id.profileImage)
    protected ImageView profileImage;

    /**
     * View Pager
     */
    @BindView(R.id.viewpager)
    protected ViewPager viewpager;

    /**
     * Tab Layout
     */
    @BindView(R.id.tabs)
    protected TabLayout tabLayout;

    /**
     * Kid Name Text View
     */
    @BindView(R.id.kidName)
    protected TextView kidNameTextView;


    /**
     * Kid Birthday Text View
     */
    @BindView(R.id.kidBirthday)
    protected TextView kidBirthdayTextView;

    /**
     * Kid School Text View
     */
    @BindView(R.id.kidSchool)
    protected TextView kidSchoolTextView;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity) {
        final Intent callingIntent = new Intent(context, MyKidsDetailMvpActivity.class);
        callingIntent.putExtra(KID_IDENTITY_ARG, identity);
        return callingIntent;
    }

    /**
     * Toggle All Components
     * @param isEnable
     */
    private void toggleAllComponents(final boolean isEnable) {
        profileImage.setEnabled(isEnable);
        viewpager.setEnabled(isEnable);
        tabLayout.setEnabled(isEnable);
        kidNameTextView.setEnabled(isEnable);
        kidBirthdayTextView.setEnabled(isEnable);
        kidSchoolTextView.setEnabled(isEnable);
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {

        this.myKidsComponent = DaggerMyKidsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

        this.myKidsComponent.inject(this);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_kids_detail_activity;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        toggleAllComponents(false);

        if(!getIntent().hasExtra(KID_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide a child identifier");

        // Get Kid Identity
        kidIdentity = getIntent().getStringExtra(KID_IDENTITY_ARG);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), kidIdentity);
        viewpager.setAdapter(sectionsPagerAdapter);

        tabLayout.setupWithViewPager(viewpager);

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
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(MyKidsDetailPresenter.KID_IDENTITY_ARG, kidIdentity);
        return args;
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public MyKidsComponent getComponent() {
        return myKidsComponent;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public MyKidsDetailPresenter providePresenter() {
        return myKidsComponent.myKidsDetailPresenter();
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
     * On Edit Profile
     */
    @OnClick(R.id.editProfileBtn)
    protected void onEditProfileBtn(){
        navigatorImpl.navigateToMyKidsProfile(kidIdentity);
    }

    /**
     * Navigate To Alerts
     */
    @Override
    public void navigateToAlerts() {
        navigatorImpl.navigateToAlertList();
    }

    /**
     * Navigate To Alerts Detail
     * @param alertId
     * @param sonId
     */
    @Override
    public void navigateToAlertDetail(final String alertId, final String sonId) {
        navigatorImpl.navigateToAlertDetail(alertId, sonId);
    }

    /**
     * Navigate To Warning Alerts
     * @param sonId
     */
    @Override
    public void navigateToWarningAlerts(String sonId) {
        navigatorImpl.navigateToAlertList(AlertLevelEnum.WARNING, sonId);
    }

    /**
     * On Dimensions Selected
     * @param dimensionIdx
     * @param value
     * @param total
     */
    @Override
    public void onDimensionsSelected(int dimensionIdx, int value, int total) {

        showShortMessage("Dimensions " + dimensionIdx + " -> " + value + "/"+ total);

    }

    /**
     * On Son Loaded
     * @param sonEntity
     */
    @Override
    public void onSonLoaded(SonEntity sonEntity) {

        if(appUtils.isValidString(sonEntity.getProfileImage()))
            // Set Author Image
            picasso.load(sonEntity.getProfileImage())
                    .placeholder(R.drawable.kid_default_image)
                    .error(R.drawable.kid_default_image)
                    .noFade()
                    .into(profileImage);
        else
            profileImage.setImageResource(R.drawable.kid_default_image);

        kidNameTextView.setText(sonEntity.getFullName());


        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getString(R.string.date_format_server_response));

        // Set Kid Birthday
        kidBirthdayTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.kid_detail_birthday), simpleDateFormat.format(sonEntity.getBirthdate())));


        kidSchoolTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.kid_detail_school), sonEntity.getSchool().getName()));


        toggleAllComponents(true);

    }

    /**
     * Sections Page Adapter
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final static int DIMENSIONS_TAB = 0;
        private final static int ALERTS_TAB = 1;
        private final static int RELATIONS_TAB = 2;
        private final static int SECTION_COUNT = 3;

        private final String kidIdentity;

        /**
         * Sections Pager Adapter
         * @param fm
         */
        public SectionsPagerAdapter(FragmentManager fm, final String kidIdentity) {
            super(fm);
            this.kidIdentity = kidIdentity;
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case DIMENSIONS_TAB:
                    return FourDimensionsMvpFragment.newInstance(kidIdentity);
                case ALERTS_TAB:
                    return ImportantAlertsMvpFragment.newInstance(kidIdentity);
                case RELATIONS_TAB:
                    return KidRelationsMvpFragment.newInstance(kidIdentity);
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
                case ALERTS_TAB:
                    return getString(R.string.alerts_tab);
                case RELATIONS_TAB:
                    return getString(R.string.relations_tab);
            }
            return null;
        }
    }
}
