package sanchez.sanchez.sergio.masom_app.di.components;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.masom_app.di.modules.ApplicationModule;
import sanchez.sanchez.sergio.masom_app.navigation.Navigator;
import sanchez.sanchez.sergio.masom_app.notification.local.ILocalSystemNotification;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {

    void inject(final SupportActivity supportActivity);

    //Exposed to sub-graphs.
    Context context();
    IThreadExecutor threadExecutor();
    IPostExecutionThread postExecutionThread();
    Navigator navigator();
    ILocalSystemNotification localSystemNotification();
    //IRemoteSystemNotification remoteSystemNotification();
}
