package sanchez.sanchez.sergio.bullkeeper.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerHomeComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.HomeComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.legal.LegalContentActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.lastalerts.LastAlertsActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.utils.ScreenManager;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import timber.log.Timber;
import static sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Home Activity
 */
public class HomeMvpActivity extends SupportMvpActivity<HomePresenter, IHomeView>
        implements HasComponent<HomeComponent>, IHomeActivityHandler
        , IHomeView, LastAlertsActivityMvpFragment.SupportLCEListener {

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
    public void goToChildDetail(String identity) {
        navigatorImpl.navigateToMyKidsDetail(identity);
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
     * On Back Pressed
     */
    @Override
    public void onBackPressed() {
        // Confirm close session
        showConfirmationDialog(R.string.confirm_close_session, new ConfirmationDialogFragment.ConfirmationDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                preferencesRepositoryImpl.setAuthToken(IPreferenceRepository.AUTH_TOKEN_DEFAULT_VALUE);
                preferencesRepositoryImpl.setPrefCurrentUserIdentity(IPreferenceRepository.CURRENT_USER_IDENTITY_DEFAULT_VALUE);
                navigatorImpl.navigateToIntro(true);
            }

            @Override
            public void onRejected(DialogFragment dialog) {}
        });

    }
}
