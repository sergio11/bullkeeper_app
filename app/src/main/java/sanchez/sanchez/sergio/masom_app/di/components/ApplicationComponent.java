package sanchez.sanchez.sergio.masom_app.di.components;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Component;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.masom_app.di.modules.ApiModule;
import sanchez.sanchez.sergio.masom_app.di.modules.ApplicationModule;
import sanchez.sanchez.sergio.masom_app.navigation.INavigator;
import sanchez.sanchez.sergio.masom_app.notification.local.ILocalSystemNotification;
import sanchez.sanchez.sergio.masom_app.ui.activity.splash.SplashScreenActivity;
import sanchez.sanchez.sergio.masom_app.ui.fragment.menu.MenuDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.question.QuestionAppDialog;
import sanchez.sanchez.sergio.masom_app.ui.notification.INotificationHelper;
import sanchez.sanchez.sergio.masom_app.utils.PreferencesManager;

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

    /**
     * Inject on Question App Dialog
     * @param questionAppDialog
     */
    void inject(final QuestionAppDialog questionAppDialog);



    //Exposed to sub-graphs.
    Context context();
    IThreadExecutor threadExecutor();
    IPostExecutionThread postExecutionThread();
    INavigator navigator();
    ILocalSystemNotification localSystemNotification();
    //IRemoteSystemNotification remoteSystemNotification();
    INotificationHelper notificationHelper();
    PreferencesManager preferencesManager();
    Retrofit retrofit();
}
