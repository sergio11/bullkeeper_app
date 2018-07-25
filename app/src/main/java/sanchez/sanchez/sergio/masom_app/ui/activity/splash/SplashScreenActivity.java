package sanchez.sanchez.sergio.masom_app.ui.activity.splash;

import android.os.Bundle;
import android.support.annotation.NonNull;

import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerIntroComponent;
import sanchez.sanchez.sergio.masom_app.di.components.IntroComponent;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;

public class SplashScreenActivity extends
        SupportActivity<SplashScreenPresenter, ISplashScreenView> {


    private IntroComponent introComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initializeInjector() {
        this.introComponent = DaggerIntroComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        introComponent.inject(this);
    }

    @NonNull
    @Override
    public SplashScreenPresenter providePresenter() {
        return introComponent.splashScreenPresenter();
    }
}
