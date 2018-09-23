package sanchez.sanchez.sergio.bullkeeper.ui.activity.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import timber.log.Timber;

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
