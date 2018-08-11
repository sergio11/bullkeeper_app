package sanchez.sanchez.sergio.masom_app.di.components;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Component;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.masom_app.di.modules.ApplicationModule;
import sanchez.sanchez.sergio.masom_app.navigation.INavigator;
import sanchez.sanchez.sergio.masom_app.notification.local.ILocalSystemNotification;
import sanchez.sanchez.sergio.masom_app.ui.fragment.menu.MenuDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.notification.INotificationHelper;
import sanchez.sanchez.sergio.masom_app.utils.PreferencesManager;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {


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
    PreferencesManager preferencesManager();
}
