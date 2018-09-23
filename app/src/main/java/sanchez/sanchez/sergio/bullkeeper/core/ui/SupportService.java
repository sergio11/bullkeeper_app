package sanchez.sanchez.sergio.bullkeeper.core.ui;

import android.app.Service;

import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.di.components.ApplicationComponent;

/**
 * Support Service
 */
public abstract class SupportService extends Service {

    @Override
    public void onCreate() {
        initializeInjector();
        super.onCreate();
    }

    /**
     * Initialize Injector
     */
    protected abstract void initializeInjector();

    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }
}
