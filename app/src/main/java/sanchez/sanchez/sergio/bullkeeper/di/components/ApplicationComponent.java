package sanchez.sanchez.sergio.bullkeeper.di.components;

import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;
import dagger.Component;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.utils.ScreenManager;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ApiModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ApplicationModule;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.bullkeeper.notification.local.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.splash.SplashScreenActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.menu.MenuDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.notification.INotificationHelper;
import sanchez.sanchez.sergio.domain.utils.IAuthTokenAware;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = { ApplicationModule.class, ApiModule.class })
public interface ApplicationComponent {

    /**
     * Inject into Splash Screen Activity
     * @param splashScreenActivity
     */
    void inject(final SplashScreenActivity splashScreenActivity);

    /**
     * Menu Dialog Fragment
     * @param menuDialogFragment
     */
    void inject(MenuDialogFragment menuDialogFragment);


    //Exposed to sub-graphs.
    Context context();
    IThreadExecutor threadExecutor();
    IPostExecutionThread postExecutionThread();
    INavigator navigator();
    ILocalSystemNotification localSystemNotification();
    //IRemoteSystemNotification remoteSystemNotification();
    INotificationHelper notificationHelper();
    IPreferenceRepository preferencesManager();
    Retrofit retrofit();
    IAppUtils appUtils();
    IAuthTokenAware authTokenAware();
    ApiEndPointsHelper apiEndPointsHelper();
    Picasso picasso();
    ScreenManager screenManager();
}
