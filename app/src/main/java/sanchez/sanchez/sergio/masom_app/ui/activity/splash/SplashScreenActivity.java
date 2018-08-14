package sanchez.sanchez.sergio.masom_app.ui.activity.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import sanchez.sanchez.sergio.masom_app.AndroidApplication;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.masom_app.navigation.INavigator;

/**
 * Splash Screen Activity
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Inject
    protected INavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        navigator.navigateToIntro();
        finish();
    }

    /**
     * Initialize Injector
     */
    protected void initializeInjector() {
        final ApplicationComponent applicationComponent = AndroidApplication.getInstance()
                .getApplicationComponent();
        applicationComponent.inject(this);
    }

}
