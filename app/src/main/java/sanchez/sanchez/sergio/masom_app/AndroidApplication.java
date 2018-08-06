package sanchez.sanchez.sergio.masom_app;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import net.grandcentrix.thirtyinch.TiConfiguration;
import net.grandcentrix.thirtyinch.TiPresenter;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import sanchez.sanchez.sergio.masom_app.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerApplicationComponent;
import sanchez.sanchez.sergio.masom_app.di.modules.ApplicationModule;

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

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static AndroidApplication getInstance() {
        return INSTANCE;
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    /**
     * On Common Config
     */
    protected void onCommonConfig(){
        CaocConfig.Builder.create().apply();

        Iconify
                .with(new FontAwesomeModule());

        TiPresenter.setDefaultConfig(COMMON_PRESENTER_CONFIG);
    }


    /**
     * On Debug Config
     */
    protected void onDebugConfig(){

    }


    /**
     * On Release Config
     */
    protected void onReleaseConfig(){


    }

}
