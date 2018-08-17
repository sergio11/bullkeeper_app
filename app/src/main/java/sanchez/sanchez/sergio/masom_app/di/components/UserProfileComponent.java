package sanchez.sanchez.sergio.masom_app.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.userprofile.UserProfileMvpActivityMvp;
import sanchez.sanchez.sergio.masom_app.ui.activity.userprofile.UserProfilePresenter;

/**
 * User Profile Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface UserProfileComponent extends ActivityComponent {

    /**
     * Inject into User Profile Activity
     * @param userProfileActivity
     */
    void inject(final UserProfileMvpActivityMvp userProfileActivity);


    UserProfilePresenter userProfilePresenter();


}
