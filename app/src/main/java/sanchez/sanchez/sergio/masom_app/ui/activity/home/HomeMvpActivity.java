package sanchez.sanchez.sergio.masom_app.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerHomeComponent;
import sanchez.sanchez.sergio.masom_app.di.components.HomeComponent;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportMvpActivity;

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
     * @param identity
     */
    @Override
    public void goToAlertDetail(String identity) {
        navigatorImpl.navigateToAlertDetail(identity);
    }

    /**
     * Go to Alerts
     */
    @Override
    public void goToAlerts() {
        navigatorImpl.navigateToAlertList();
    }
}
