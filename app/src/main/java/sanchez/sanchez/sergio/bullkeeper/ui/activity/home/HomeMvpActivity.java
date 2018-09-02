package sanchez.sanchez.sergio.bullkeeper.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerHomeComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.HomeComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpActivity;

/**
 * Home Activity
 */
public class HomeMvpActivity extends SupportMvpActivity<HomePresenter, IHomeView>
        implements HasComponent<HomeComponent>, IHomeActivityHandler
        , IHomeView {

    private HomeComponent homeComponent;

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
}
