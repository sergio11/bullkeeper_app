package sanchez.sanchez.sergio.masom_app;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import sanchez.sanchez.sergio.masom_app.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerApplicationComponent;
import sanchez.sanchez.sergio.masom_app.di.modules.ApplicationModule;

/**
 * Base Application
 */
public final class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

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

    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
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
