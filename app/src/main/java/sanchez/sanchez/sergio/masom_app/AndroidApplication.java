package sanchez.sanchez.sergio.masom_app;

import android.app.Application;

import sanchez.sanchez.sergio.masom_app.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.masom_app.di.modules.ApplicationModule;

/**
 * Base Application
 */
public final class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
