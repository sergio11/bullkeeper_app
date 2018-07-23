package sanchez.sanchez.sergio.masom_app.di.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.permission.IPermissionManager;
import sanchez.sanchez.sergio.masom_app.permission.impl.PermissionManagerImpl;

/**
 * A module to wrap all the dependencies of an activity
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity provideActivity() {
        return this.activity;
    }


    /**
     * Expose the IPermissionManager to dependents in the graph.
     */
    @Provides
    @PerActivity
    IPermissionManager providePermissionManager(final Activity activity) {
        return new PermissionManagerImpl(activity);
    }
}
