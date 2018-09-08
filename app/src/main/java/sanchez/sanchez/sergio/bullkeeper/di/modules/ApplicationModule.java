package sanchez.sanchez.sergio.bullkeeper.di.modules;

import android.content.Context;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sanchez.sanchez.sergio.bullkeeper.BuildConfig;
import sanchez.sanchez.sergio.bullkeeper.utils.PreferencesRepositoryImpl;
import sanchez.sanchez.sergio.bullkeeper.utils.ScreenManager;
import sanchez.sanchez.sergio.data.executor.JobExecutor;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.UIThread;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.bullkeeper.navigation.impl.NavigatorImpl;
import sanchez.sanchez.sergio.bullkeeper.notification.local.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.notification.local.impl.LocalSystemNotificationImpl;
import sanchez.sanchez.sergio.bullkeeper.ui.notification.INotificationHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.notification.impl.NotificationHelperImpl;
import sanchez.sanchez.sergio.data.utils.AppUtils;
import sanchez.sanchez.sergio.domain.utils.IAuthTokenAware;

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
    IPreferenceRepository providePreferencesManager(final Context appContext) {
        return new PreferencesRepositoryImpl(appContext);
    }

    /**
     * Provide Auth Token Aware
     * @param preferencesRepositoryImpl
     * @return
     */
    @Provides @Singleton
    IAuthTokenAware provideAuthTokenAware(final PreferencesRepositoryImpl preferencesRepositoryImpl) {
        return preferencesRepositoryImpl;
    }

    /**
     * Provide App Utils
     * @param appContext
     * @return
     */
    @Provides @Singleton
    IAppUtils provideAppUtils(final Context appContext) {
        return new AppUtils(appContext);
    }

    /**
     * Provide Api End Points Helper
     * @return
     */
    @Provides @Singleton
    ApiEndPointsHelper provideApiEndPointsHelper(){
        return new ApiEndPointsHelper(BuildConfig.BASE_URL);
    }

    /**
     * Provide Screen Manager
     * @return
     */
    @Provides @Singleton
    ScreenManager provideScreenManager(final Context appContext){
        return new ScreenManager(appContext);
    }

}
