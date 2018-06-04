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
}
