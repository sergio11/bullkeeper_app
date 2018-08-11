package sanchez.sanchez.sergio.masom_app.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sanchez.sanchez.sergio.data.executor.JobExecutor;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.masom_app.AndroidApplication;
import sanchez.sanchez.sergio.masom_app.UIThread;
import sanchez.sanchez.sergio.masom_app.navigation.INavigator;
import sanchez.sanchez.sergio.masom_app.navigation.impl.NavigatorImpl;
import sanchez.sanchez.sergio.masom_app.notification.local.ILocalSystemNotification;
import sanchez.sanchez.sergio.masom_app.notification.local.impl.LocalSystemNotificationImpl;
import sanchez.sanchez.sergio.masom_app.ui.notification.INotificationHelper;
import sanchez.sanchez.sergio.masom_app.ui.notification.impl.NotificationHelperImpl;
import sanchez.sanchez.sergio.masom_app.utils.PreferencesManager;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

    private final AndroidApplication application;

    public ApplicationModule(final AndroidApplication application) {
        this.application = application;
    }

    /**
     * Provide Application Context
     * @return
     */
    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }


    /**
     * Provide Thread Executor
     * @param jobExecutor
     * @return
     */
    @Provides @Singleton
    IThreadExecutor provideThreadExecutor(final JobExecutor jobExecutor) {
        return jobExecutor;
    }


    /**
     * Provide Post Execution Thread
     * @param uiThread
     * @return
     */
    @Provides @Singleton
    IPostExecutionThread providePostExecutionThread(final UIThread uiThread) {
        return uiThread;
    }

    /**
     * Provide NavigatorImpl
     * @param context
     * @return
     */
    @Provides @Singleton
    INavigator provideNavigator(final Context context) {
        return new NavigatorImpl(context);
    }

    /**
     * Provide Local System Notification Aware
     * @param context
     * @return
     */
    @Provides @Singleton
    ILocalSystemNotification provideLocalSystemNotificationAware(final Context context) {
        return new LocalSystemNotificationImpl(context);
    }

    /**
     * Provide Notification Helper
     * @param appContext
     * @return
     */
    @Provides @Singleton
    INotificationHelper provideNotificationHelper(final Context appContext) {
        return new NotificationHelperImpl(appContext);
    }

    /**
     * Provide Preferences Manager
     * @param appContext
     * @return
     */
    @Provides @Singleton
    PreferencesManager providePreferencesManager(final Context appContext) {
        return new PreferencesManager(appContext);
    }

}
