package sanchez.sanchez.sergio.masom_app.di.components;

import android.app.Activity;

import dagger.Component;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.permission.IPermissionManager;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 */
@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
    IPermissionManager permissionManager();
}
