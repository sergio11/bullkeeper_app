package sanchez.sanchez.sergio.masom_app.di.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;

/**
 * A module to wrap the Activity state and expose it to the graph.
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
}
