package sanchez.sanchez.sergio.bullkeeper.ui.activity.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.SigningEvent;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.bullkeeper.ui.services.NotificationHandlerService;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Splash Screen Activity
 */
public class SplashScreenActivity extends AppCompatActivity {

    /**
     * Navigator
     */
    @Inject
    protected INavigator navigator;

    /**
     * Preference Manager
     */
    @Inject
    protected IPreferenceRepository preferenceRepository;

    /**
     * App Utils
     */
    @Inject
    protected IAppUtils appUtils;


    /**
     * Local System Notification
     */
    @Inject
    protected ILocalSystemNotification localSystemNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        if(appUtils.isValidString(preferenceRepository.getAuthToken())) {
            navigator.navigateToHome(this);
        } else {
            navigator.navigateToIntro(this);
        }

        NotificationHandlerService.start(getApplicationContext());

        localSystemNotification.sendNotification(new SigningEvent(
                preferenceRepository.getPrefCurrentUserIdentity()));

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
