package sanchez.sanchez.sergio.masom_app.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerHomeComponent;
import sanchez.sanchez.sergio.masom_app.di.components.HomeComponent;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;

/**
 * Home Activity
 */
public class HomeActivity extends SupportActivity<HomePresenter, IHomeView>
        implements HasComponent<HomeComponent>, IHomeActivityHandler
        , IHomeView {

    private HomeComponent homeComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        final Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
}
