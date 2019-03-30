package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerMyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.appstats.AppStatsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.apprules.AppRulesMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.callslist.CallsListMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactlist.ContactListMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.funtime.FunTimeMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.kidrequest.KidRequestListMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smslist.SmsListMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.familylocator.FamilyLocatorMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts.ImportantAlertsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.scheduledblock.ScheduledBlocksMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminals.TerminalsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.GuardianRolesEnum;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;

/**
 * My Kids Detail Activity
 */
public class MyKidsDetailMvpActivity extends SupportMvpActivity<MyKidsDetailPresenter, IMyKidsDetailView>
        implements HasComponent<MyKidsComponent>, IMyKidsDetailActivityHandler
        , IMyKidsDetailView {

    /**
     * Args
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    public static final String TAB_SELECTED_ARG = "TAB_SELECTED_ARG";

    /**
     * Tabs
     */
    public final static int ALERTS_TAB = 0;
    public final static int KID_REQUEST_TAB = 1;
    public final static int TERMINALS_TAB = 2;
    public final static int SCHEDULED_BLOCKS_TAB = 3;
    public final static int APP_RULES_TAB = 4;
    public final static int APP_STATS_TAB = 5;
    public final static int SMS_LIST_TAB = 6;
    public final static int CALLS_LIST_TAB = 7;
    public final static int CONTACTS_LIST_TAB = 8;
    public final static int TIME_ALLOWANCE_TAB = 9;
    public final static int FAMILY_LOCATOR_TAB = 10;
    public final static int SECTION_COUNT = 11;

    private final String CONTENT_FULL_NAME = "MY_KIDS_DETAIL";
    private final String CONTENT_TYPE_NAME = "KIDS";

    /**
     * Sections Pager Adapter
     */
    private SectionsPagerAdapter sectionsPagerAdapter;

    /**
     * My Kids Component
     */
    private MyKidsComponent myKidsComponent;


    /**
     * Activity
     */

    @Inject
    protected Activity activity;

    /**
     * State
     */
    @State
    protected String kidIdentity;

    /**
     * Kid Name
      */
    @State
    protected String kidName;


    /**
     * Terminal Items List
     */
    @State
    protected ArrayList<TerminalItem> terminalItemsList = new ArrayList<>();

    /**
     * Section Tab Selected
     */
    @State
    protected int sectionTabSelected = 0;

    /**
     * Unselected tab icons
     */
    private int[] unselectedTabIcons = {
            R.drawable.important_alerts_tab_cyan,
            R.drawable.kid_request_cyan_tab,
            R.drawable.mobile_cyan_tab,
            R.drawable.scheduled_blocks,
            R.drawable.app_rules_tab_cyan,
            R.drawable.dimensions_tab_cyan,
            R.drawable.sms_tab_cyan,
            R.drawable.calls_tab_cyan,
            R.drawable.contacts_tab_cyan,
            R.drawable.hourglass_tab_cyan,
            R.drawable.family_locator_tab_cyan
    };

    /**
     * Selected Tab icons
     */
    private int[] selectedTabIcons = {
            R.drawable.important_alerts_tab_dark_cyan,
            R.drawable.kid_request_dark_tab,
            R.drawable.mobile_dark_tab,
            R.drawable.scheduled_blocks_dark,
            R.drawable.app_rules_tab_dark,
            R.drawable.dimensions_tab_dark_cyan,
            R.drawable.sms_tab_dark,
            R.drawable.calls_tab_dark_cyan,
            R.drawable.contacts_tab_dark_cyan,
            R.drawable.hourglass_tab_dark,
            R.drawable.family_locator_tab_dark
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
     * Terminals Text View
     */
    @BindView(R.id.terminals)
    protected TextView terminalsTextView;

    /**
     * Edit Profile Button
     */
    @BindView(R.id.editProfileBtn)
    protected FloatingActionButton editProfileBtn;

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
     * Get Calling Intent
     * @param context
     * @param identity
     * @param tabSelected
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity, final int tabSelected) {
        final Intent callingIntent = new Intent(context, MyKidsDetailMvpActivity.class);
        callingIntent.putExtra(KID_IDENTITY_ARG, identity);
        callingIntent.putExtra(TAB_SELECTED_ARG, tabSelected);
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

        if(getIntent().hasExtra(TAB_SELECTED_ARG))
            sectionTabSelected = getIntent().getIntExtra(TAB_SELECTED_ARG, 0);

        // Get Kid Identity
        kidIdentity = getIntent().getStringExtra(KID_IDENTITY_ARG);

        showProgressDialog(R.string.generic_loading_text);

        getPresenter().loadKidData(kidIdentity);
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
     * on Network Error
     */
    @Override
    public void onNetworkError() {
        showNoticeDialog(getString(R.string.network_error_ocurred), false, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });
    }

    /**
     * on Other Exception
     */
    @Override
    public void onOtherException() {
        showNoticeDialog(getString(R.string.unexpected_error_ocurred), false, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });
    }

    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
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
        navigatorImpl.navigateToMyKidsProfile(activity, kidIdentity);
    }

    /**
     * Navigate To Alerts
     */
    @Override
    public void navigateToAlerts() {
        navigatorImpl.navigateToAlertList(activity);
    }

    /**
     * Navigate To Alerts Detail
     * @param alertId
     * @param sonId
     */
    @Override
    public void navigateToAlertDetail(final String alertId, final String sonId) {
        navigatorImpl.navigateToAlertDetail(activity, alertId, sonId);
    }

    /**
     * Navigate To Warning Alerts
     * @param sonId
     */
    @Override
    public void navigateToWarningAlerts(String sonId) {
        navigatorImpl.navigateToAlertList(activity, AlertLevelEnum.WARNING, sonId);
    }

    /**
     * Navigate To Save Scheduled Block
     * @param identity
     */
    @Override
    public void navigateToSaveScheduledBlock(final String childId, final String identity) {
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");
        navigatorImpl.navigateToSaveScheduledBlockMvpActivity(activity, childId, identity);
    }

    /**
     * Navigate To Save Scheduled Block
     */
    @Override
    public void navigateToSaveScheduledBlock() {
        navigatorImpl.navigateToSaveScheduledBlockMvpActivity(activity, kidIdentity);
    }

    /**
     * Show App Rules Dialog
     */
    @Override
    public void showAppRulesDialog() {
        navigatorImpl.showAppRulesInfoDialog(this);
    }

    /**
     * Show Family Locator Dialog
     */
    @Override
    public void showFamilyLocatorDialog() {
        navigatorImpl.showFamilyLocatorInfoDialog(this);
    }

    /**
     * Navigate To Terminal Detail
     * @param childId
     * @param terminalId
     */
    @Override
    public void navigateToTerminalDetail(String childId, String terminalId) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");
        Preconditions.checkState(!terminalId.isEmpty(), "Terminal id can not be empty");

        navigatorImpl.showTerminalDetail(activity, childId, terminalId);
    }

    /**
     *
     * @param kid
     * @param terminal
     * @param app
     */
    @Override
    public void navigateToAppInstalledDetail(String kid, String terminal, String app) {
        Preconditions.checkNotNull(kid, "kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "kid can not be empty");
        Preconditions.checkNotNull(terminal, "terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "terminal can not be empty");
        Preconditions.checkNotNull(app, "sms can not be null");
        Preconditions.checkState(!app.isEmpty(), "sms can not be empty");

        navigatorImpl.navigateToAppDetailActivity(this, kid, terminal, app);
    }

    /**
     * Navigate To SMS Detail
     * @param kid
     * @param terminal
     * @param sms
     */
    @Override
    public void navigateToSmsDetail(final String kid, final String terminal, final String sms) {
        Preconditions.checkNotNull(kid, "kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "kid can not be empty");
        Preconditions.checkNotNull(terminal, "terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "terminal can not be empty");
        Preconditions.checkNotNull(sms, "sms can not be null");
        Preconditions.checkState(!sms.isEmpty(), "sms can not be empty");

        navigatorImpl.navigateToSmsDetailActivity(this, kid, terminal, sms);
    }

    /**
     * Navigate To Call Detail
     * @param kid
     * @param terminal
     * @param call
     */
    @Override
    public void navigateToCallDetail(final String kid, final String terminal, final String call) {
        Preconditions.checkNotNull(kid, "kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "kid can not be empty");
        Preconditions.checkNotNull(terminal, "terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "terminal can not be empty");
        Preconditions.checkNotNull(call, "call can not be null");
        Preconditions.checkState(!call.isEmpty(), "call can not be empty");

        navigatorImpl.navigateToCallDetailActivity(this, kid, terminal, call);
    }

    /**
     * Navigate to contact detail
     * @param kid
     * @param terminal
     * @param contact
     */
    @Override
    public void navigateToContactDetail(final String kid, final String terminal, final String contact) {
        Preconditions.checkNotNull(kid, "kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "kid can not be empty");
        Preconditions.checkNotNull(terminal, "terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "terminal can not be empty");
        Preconditions.checkNotNull(contact, "call can not be null");
        Preconditions.checkState(!contact.isEmpty(), "call can not be empty");

        navigatorImpl.navigateToContactDetailActivity(this, kid, terminal, contact);

    }

    /**
     * Navigate To Phone Numbers Black List
     * @param kid
     * @param terminal
     */
    @Override
    public void navigateToPhoneNumbersBlackList(final String kid, final String terminal) {
        Preconditions.checkNotNull(kid, "kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "kid can not be empty");
        Preconditions.checkNotNull(terminal, "terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "terminal can not be empty");

        navigatorImpl.navigateToPhoneNumbersBlockedList(this, kid, terminal);
    }

    /**
     * Navigate To Kid Request Detail
     * @param kid
     * @param identity
     */
    @Override
    public void navigateToKidRequestDetail(final String kid, final String identity) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");

        navigatorImpl.navigateToKidRequestDetail(this, kid, identity);
    }

    /**
     * Navigate To Geofences
     * @param kid
     */
    @Override
    public void navigateToGeofences(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        navigatorImpl.navigateToGeofencesList(this, kid);
    }

    /**
     * Navigate To Save Geofences
     * @param kid
     */
    @Override
    public void navigateToSaveGeofence(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        navigatorImpl.navigateToSaveGeofence(this, kid);
    }

    /**
     * Navigate To Day Scheduled Detail
     * @param kid
     * @param terminal
     */
    @Override
    public void navigateToDayScheduledDetail(final String kid, final String terminal,
                                             final String day, final boolean isFunTimeEnabled) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(day, "Day can not be null");
        Preconditions.checkState(!day.isEmpty(), "Day can not be empty");

        navigatorImpl.navigateToDayScheduledDetailActivity(this, kid, terminal, day, isFunTimeEnabled);
    }

    /**
     * Show Terminal Not Found Dialog
     */
    @Override
    public void showTerminalNotFoundDialog() {
        showNoticeDialog(String.format(Locale.getDefault(), getString(R.string.kids_results_no_terminals_linked),
                kidName), false, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });
    }

    /**
     * Configure Terminal List
     * @param terminalList
     */
    @Override
    public void configureTerminalList(final List<TerminalEntity> terminalList) {
        Preconditions.checkNotNull(terminalList, "Terminal List can not be null");
        terminalItemsList.clear();
        for(final TerminalEntity terminalEntity: terminalList) {
            final TerminalItem terminalItem = new TerminalItem(terminalEntity.getIdentity(),
                    terminalEntity.getDeviceName());
            terminalItemsList.add(terminalItem);
        }
    }

    /**
     * Setup Sections Pager Adapter
     */
    private void setupSectionsPagerAdapter(final int tabSelected, final KidEntity kidEntity){

        // Create Sections Pager Adapter
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), kidEntity);

        viewpager.setAdapter(sectionsPagerAdapter);

        // Create Tab Layout
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
                sectionTabSelected = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(unselectedTabIcons[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        viewpager.setCurrentItem(tabSelected);

    }

    /**
     *
     * @param kidGuardianEntity
     */
    @Override
    public void onKidGuardianLoaded(final KidGuardianEntity kidGuardianEntity) {

        final GuardianRolesEnum role = kidGuardianEntity.getRole();

        if(!role.equals(GuardianRolesEnum.DATA_VIEWER)) {

            if(role.equals(GuardianRolesEnum.ADMIN))
                editProfileBtn.setVisibility(View.VISIBLE);

            final KidEntity kidEntity = kidGuardianEntity.getKid();

            if(appUtils.isValidString(kidEntity.getProfileImage()))
                // Set Author Image
                picasso.load(kidEntity.getProfileImage())
                        .placeholder(R.drawable.kid_default_image)
                        .error(R.drawable.kid_default_image)
                        .noFade()
                        .into(profileImage);
            else
                profileImage.setImageResource(R.drawable.kid_default_image);

            kidName = kidEntity.getFullName();
            kidNameTextView.setText(kidEntity.getFullName());

            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());

            // Set Kid Birthday
            kidBirthdayTextView.setText(simpleDateFormat.format(kidEntity.getBirthdate()));

            kidSchoolTextView.setText(kidEntity.getSchool().getName());

            // Configure Terminal List
            configureTerminalList(kidEntity.getTerminalEntities());

            // Check Terminals linked
            if(terminalItemsList.isEmpty()) {
                showTerminalNotFoundDialog();

            } else {

                terminalsTextView.setText(String.format(Locale.getDefault(),
                        getString(R.string.kids_results_terminals_count), terminalItemsList.size()));

                // Setup Sections Pager Adapter
                setupSectionsPagerAdapter(sectionTabSelected, kidEntity);

                // Enable All Components
                toggleAllComponents(true);
            }

        } else {

            showNoticeDialog(getString(R.string.you_do_not_have_permission_to_access_this_section), false, new NoticeDialogFragment.NoticeDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    closeActivity();
                }
            });

        }
    }

    /**
     * Supervised Children Confirmed Not Found
     */
    @Override
    public void onSupervisedChildrenConfirmedNotFound() {
        showNoticeDialog(getString(R.string.you_do_not_have_permission_to_access_this_section), false, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });
    }

    /**
     * Sections Page Adapter
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        private final KidEntity kidEntity;

        /**
         * Sections Pager Adapter
         * @param fm
         */
        public SectionsPagerAdapter(FragmentManager fm, final KidEntity kidEntity) {
            super(fm);
            this.kidEntity = kidEntity;
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case ALERTS_TAB:
                    return ImportantAlertsMvpFragment.newInstance(kidIdentity);
                case KID_REQUEST_TAB:
                    return KidRequestListMvpFragment.newInstance(kidIdentity);
                case TERMINALS_TAB:
                    return TerminalsMvpFragment.newInstance(kidIdentity);
                case SCHEDULED_BLOCKS_TAB:
                    return ScheduledBlocksMvpFragment.newInstance(kidIdentity);
                case APP_RULES_TAB:
                    return AppRulesMvpFragment.newInstance(kidIdentity, terminalItemsList);
                case APP_STATS_TAB:
                    return AppStatsMvpFragment.newInstance(kidIdentity, terminalItemsList);
                case SMS_LIST_TAB:
                    return SmsListMvpFragment.newInstance(kidIdentity, terminalItemsList);
                case CALLS_LIST_TAB:
                    return CallsListMvpFragment.newInstance(kidIdentity, terminalItemsList);
                case CONTACTS_LIST_TAB:
                    return ContactListMvpFragment.newInstance(kidIdentity, terminalItemsList);
                case TIME_ALLOWANCE_TAB:
                    return FunTimeMvpFragment.newInstance(kidIdentity, terminalItemsList);
                case FAMILY_LOCATOR_TAB:
                    return FamilyLocatorMvpFragment.newInstance(kidEntity.getIdentity(),
                            kidEntity.getFullName(), kidEntity.getProfileImage());
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
                case ALERTS_TAB:
                    return getString(R.string.alerts_tab);
                case KID_REQUEST_TAB:
                    return getString(R.string.kid_request_tab);
                case TERMINALS_TAB:
                    return getString(R.string.terminals_tab);
                case SCHEDULED_BLOCKS_TAB:
                    return getString(R.string.scheduled_blocks_tab);
                case APP_RULES_TAB:
                    return getString(R.string.app_rules_title);
                case APP_STATS_TAB:
                    return getString(R.string.app_stats_tab);
                case SMS_LIST_TAB:
                    return getString(R.string.sms_list_title_tab);
                case CALLS_LIST_TAB:
                    return getString(R.string.call_list_title_tab);
                case CONTACTS_LIST_TAB:
                    return getString(R.string.contact_list_title_tab);
                case TIME_ALLOWANCE_TAB:
                    return getString(R.string.fun_time_title);
                case FAMILY_LOCATOR_TAB:
                    return getString(R.string.family_locator_title);
            }
            return null;
        }
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_2;
    }
}
