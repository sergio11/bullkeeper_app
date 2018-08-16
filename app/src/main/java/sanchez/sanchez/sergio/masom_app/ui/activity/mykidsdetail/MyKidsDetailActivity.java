package sanchez.sanchez.sergio.masom_app.ui.activity.mykidsdetail;

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

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerMyKidsComponent;
import sanchez.sanchez.sergio.masom_app.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.masom_app.ui.fragment.dimensions.FourDimensionsFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.importantalerts.ImportantAlertsFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.relations.KidRelationsFragment;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportToolbarApp;

/**
 * My Kids Detail Activity
 */
public class MyKidsDetailActivity extends SupportActivity<MyKidsDetailPresenter, IMyKidsDetailView>
        implements HasComponent<MyKidsComponent>, IMyKidsDetailActivityHandler
        , IMyKidsDetailView {

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
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity) {
        final Intent callingIntent = new Intent(context, MyKidsDetailActivity.class);
        callingIntent.putExtra(KID_IDENTITY_ARG, identity);
        return callingIntent;
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

        if(getIntent() != null && getIntent().hasExtra(KID_IDENTITY_ARG)) {
            // Get Kid Identity
            kidIdentity = getIntent().getStringExtra(KID_IDENTITY_ARG);
        }

        // Set Author Image
        Picasso.with(getApplicationContext()).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                .placeholder(R.drawable.user_default)
                .error(R.drawable.user_default)
                .noFade()
                .into(profileImage);

        kidNameTextView.setText("Sergio Sánchez Sánchez");

        // Set Kid Birthday
        kidBirthdayTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.kid_detail_birthday), "23 Mayo, 2005"));

        kidSchoolTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.kid_detail_school), "C.E.I.P Fernando Gavilán"));


        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
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

    /***
     * Navigate To Alerts Detail
     * @param identity
     */
    @Override
    public void navigateToAlertDetail(String identity) {
        navigatorImpl.navigateToAlertDetail(identity);
    }

    /**
     * Sections Page Adapter
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final static int DIMENSIONS_TAB = 0;
        private final static int ALERTS_TAB = 1;
        private final static int RELATIONS_TAB = 2;
        private final static int SECTION_COUNT = 3;

        /**
         * Sections Pager Adapter
         * @param fm
         */
        public SectionsPagerAdapter(FragmentManager fm) { super(fm); }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case DIMENSIONS_TAB:
                    return FourDimensionsFragment.newInstance(kidIdentity);
                case ALERTS_TAB:
                    return ImportantAlertsFragment.newInstance(kidIdentity);
                case RELATIONS_TAB:
                    return KidRelationsFragment.newInstance(kidIdentity);
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
                    return getString(R.string.kid_detail_dimensions_tab);
                case ALERTS_TAB:
                    return getString(R.string.kid_detail_alerts_tab);
                case RELATIONS_TAB:
                    return getString(R.string.kid_detail_relations_tab);
            }
            return null;
        }
    }
}
