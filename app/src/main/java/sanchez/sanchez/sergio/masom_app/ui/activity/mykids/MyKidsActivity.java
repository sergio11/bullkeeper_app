package sanchez.sanchez.sergio.masom_app.ui.activity.mykids;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerMyKidsComponent;

/**
 * My Kids Activity
 */
public class MyKidsActivity extends SupportActivity<MyKidsActivityPresenter, IMyKidsActivityView>
        implements HasComponent<MyKidsComponent>, IMyKidsActivityHandler
        , IMyKidsActivityView {


    private MyKidsComponent myKidsComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        final Intent intent = new Intent(context, MyKidsActivity.class);
        return intent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        myKidsComponent = DaggerMyKidsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        myKidsComponent.inject(this);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_kids;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public MyKidsActivityPresenter providePresenter() {
        return myKidsComponent.myKidsActivityPresenter();
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
     * Navigate To My Kids Profile
     * @param identity
     */
    @Override
    public void navigateToMyKidsProfile(String identity) {
        navigatorImpl.navigateToMyKidsProfile(identity);
    }

    /**
     * Navigate To Comments
     * @param identity
     */
    @Override
    public void navigateToComments(String identity) {
        navigatorImpl.navigateToComments(identity);
    }
}
