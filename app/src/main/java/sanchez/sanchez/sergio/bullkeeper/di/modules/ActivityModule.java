package sanchez.sanchez.sergio.bullkeeper.di.modules;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.bullkeeper.core.permission.IPermissionManager;
import sanchez.sanchez.sergio.bullkeeper.core.permission.impl.PermissionManagerImpl;

/**
 * A module to wrap all the dependencies of an activity
 */
@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    AppCompatActivity provideActivityCompat() {
        return this.activity;
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
    IPermissionManager providePermissionManager(final AppCompatActivity activity, final INavigator navigator) {
        return new PermissionManagerImpl(activity, navigator);
    }
}
