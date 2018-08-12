package sanchez.sanchez.sergio.masom_app;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import net.grandcentrix.thirtyinch.TiConfiguration;
import net.grandcentrix.thirtyinch.TiPresenter;
import cat.ereza.customactivityoncrash.config.CaocConfig;
import sanchez.sanchez.sergio.masom_app.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerApplicationComponent;
import sanchez.sanchez.sergio.masom_app.di.modules.ApplicationModule;
import timber.log.Timber;

/**
 * Base Application
 */
public final class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static final TiConfiguration COMMON_PRESENTER_CONFIG =
            new TiConfiguration.Builder()
                    .setRetainPresenterEnabled(true)
                    .setCallOnMainThreadInterceptorEnabled(true)
                    .setDistinctUntilChangedInterceptorEnabled(true)
                    .build();

    private static AndroidApplication INSTANCE = null;


    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();

        // Apply Common Config
        onCommonConfig();

        if (BuildConfig.DEBUG) {
            onDebugConfig();
        } else {
            onReleaseConfig();
        }

        INSTANCE = this;

    }

    /**
     * Initialize Injector
     */
    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    /**
     * Get Instance
     * @return
     */
    public static AndroidApplication getInstance() {
        return INSTANCE;
    }

    /**
     * Get Application Component
     * @return
     */
    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    /**
     * On Common Config
     */
    protected void onCommonConfig(){
        // Chrash Screen
        CaocConfig.Builder.create().apply();
        // Iconify
        Iconify.with(new FontAwesomeModule());
        // Mvp
        TiPresenter.setDefaultConfig(COMMON_PRESENTER_CONFIG);
    }


    /**
     * On Debug Config
     */
    protected void onDebugConfig(){

        Timber.plant(new Timber.DebugTree());

        Stetho.initializeWithDefaults(this);
    }


    /**
     * On Release Config
     */
    protected void onReleaseConfig(){
        Timber.plant(new CrashReportingTree());
    }


    /** A tree which logs important information for crash reporting. */
    private static final class CrashReportingTree extends Timber.Tree {


        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            //FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    //FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    //FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }

}
