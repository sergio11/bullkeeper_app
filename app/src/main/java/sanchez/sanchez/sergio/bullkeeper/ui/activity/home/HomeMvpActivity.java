package sanchez.sanchez.sergio.bullkeeper.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerHomeComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.HomeComponent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.LogoutEvent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.legal.LegalContentActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.lastalerts.LastAlertsActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.core.utils.ScreenManager;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.profile.ChildAlertsDetailDialog;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.GuardianRolesEnum;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import timber.log.Timber;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Home Activity
 */
public class HomeMvpActivity extends SupportMvpActivity<HomePresenter, IHomeView>
        implements HasComponent<HomeComponent>, IHomeActivityHandler
        , IHomeView, LastAlertsActivityMvpFragment.SupportLCEListener {

    private final String CONTENT_FULL_NAME = "HOME";
    private final String CONTENT_TYPE_NAME = "APP";


    private HomeComponent homeComponent;

    /**
     * Screen Manager
     */
    @Inject
    protected ScreenManager screenManager;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        final Intent intent = new Intent(context, HomeMvpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }


    /**
     * initialize Injector
     */
    @Override
    protected void initializeInjector() {
        homeComponent = DaggerHomeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        homeComponent.inject(this);
    }


    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public HomePresenter providePresenter() {
        return homeComponent.homePresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public HomeComponent getComponent() {
        return homeComponent;
    }


    /**
     * Go to My Kids
     */
    @Override
    public void goToMyKids() {
        navigatorImpl.navigateToMyKids();
    }

    /**
     * Go to Alert Detail
     * @param alertId
     * @param sonId
     */
    @Override
    public void goToAlertDetail(final String alertId, final String sonId) {
        navigatorImpl.navigateToAlertDetail(alertId, sonId);
    }

    /**
     * Go to Alerts
     */
    @Override
    public void goToAlerts() {
        navigatorImpl.navigateToAlertList();
    }

    /**
     * Go To User Profile
     */
    @Override
    public void goToUserProfile() {
        navigatorImpl.navigateToUserProfile();
    }

    /**
     * Go To Child Detail
     * @param identity
     */
    @Override
    public void goToChildDetail(final String identity, final GuardianRolesEnum role) {
        navigatorImpl.navigateToMyKidsDetail(identity, role);
    }

    /**
     * Go To Add Child
     */
    @Override
    public void goToAddChild() {
        navigatorImpl.navigateToAddKids();
    }

    /**
     * Show How Add Child Help Dialog
     */
    @Override
    public void showHowAddChildHelpDialog() {
        navigatorImpl.showAppHelpDialog(this, getString(R.string.home_how_add_child_title),
                getString(R.string.youtube_video_cue));
    }

    /**
     * Show Legal Content
     * @param legalTypeEnum
     */
    @Override
    public void showLegalContent(LegalContentActivity.LegalTypeEnum legalTypeEnum) {
        navigatorImpl.showLegalContentActivity(legalTypeEnum);
    }

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        Timber.d("On No Data Found");
        int heightInDp = screenManager.getScreenHeightInDPs() - 300;
        int widthInDp = screenManager.getScreenWidthInDPs();
        final LastAlertsActivityMvpFragment lastAlertsActivityFragment = (LastAlertsActivityMvpFragment)getSupportFragmentManager().findFragmentById(R.id.lastAlertsContainer);
        setDimensions(lastAlertsActivityFragment.getView(), widthInDp, heightInDp);
    }

    /**
     * On Data Loaded
     */
    @Override
    public void onDataLoaded() {
        Timber.d("On Data Loaded");
        final LastAlertsActivityMvpFragment lastAlertsActivityFragment = (LastAlertsActivityMvpFragment)getSupportFragmentManager().findFragmentById(R.id.lastAlertsContainer);
        setDimensionsToMatchParent(lastAlertsActivityFragment.getView());
    }

    /**
     * On Error Ocurred
     */
    @Override
    public void onErrorOcurred() {
        Timber.d("On Error Ocurred");
        int heightInDp = screenManager.getScreenHeightInDPs() - 300;
        int widthInDp = screenManager.getScreenWidthInDPs();
        final LastAlertsActivityMvpFragment lastAlertsActivityFragment = (LastAlertsActivityMvpFragment)getSupportFragmentManager().findFragmentById(R.id.lastAlertsContainer);
        setDimensions(lastAlertsActivityFragment.getView(), widthInDp, heightInDp);
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return TOOLBAR_WITH_MENU;
    }

    /**
     * Get App Icon Mode
     * @return
     */
    @Override
    protected int getAppIconMode() {
        return SupportToolbarApp.DISABLE_GO_TO_HOME;
    }


    /**
     * On Back Pressed
     */
    @Override
    public void onBackPressed() {
        // Confirm close session
        showConfirmationDialog(R.string.confirm_close_session, new ConfirmationDialogFragment.ConfirmationDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                localSystemNotification.sendNotification(new LogoutEvent(preferencesRepositoryImpl.getPrefCurrentUserIdentity()));
                preferencesRepositoryImpl.setAuthToken(IPreferenceRepository.AUTH_TOKEN_DEFAULT_VALUE);
                preferencesRepositoryImpl.setPrefCurrentUserIdentity(IPreferenceRepository.CURRENT_USER_IDENTITY_DEFAULT_VALUE);
                navigatorImpl.navigateToIntro(true);
            }

            @Override
            public void onRejected(DialogFragment dialog) {}
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
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.intro_background_cyan;
    }

    /**
     * Show Child Alerts Detail Dialog
     * @param alertLevelEnum
     * @param alertLevelValue
     * @param kidIdentityValue
     */
    @Override
    public void showChildAlertsDetailDialog(AlertLevelEnum alertLevelEnum, String alertLevelValue, String kidIdentityValue) {
        Preconditions.checkNotNull(alertLevelEnum, "Alert Level Enum can not be null");
        Preconditions.checkNotNull(alertLevelValue, "Alert Value can not be null");
        Preconditions.checkState(!alertLevelValue.isEmpty(), "Alert Value can not be empty");
        Preconditions.checkNotNull(kidIdentityValue, "Child id can not be null");
        Preconditions.checkState(!kidIdentityValue.isEmpty(), "Child Id can not be empty");

        ChildAlertsDetailDialog.show(this, alertLevelEnum, alertLevelValue, kidIdentityValue);

    }

}
