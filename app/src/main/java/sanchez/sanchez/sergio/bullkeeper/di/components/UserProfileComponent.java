package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.GuardianModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.userprofile.UserProfileMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.userprofile.UserProfilePresenter;

/**
 * User Profile Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class,
                 GuardianModule.class })
public interface UserProfileComponent extends ActivityComponent {

    /**
     * Inject into User Profile Activity
     * @param userProfileActivity
     */
    void inject(final UserProfileMvpActivity userProfileActivity);


    UserProfilePresenter userProfilePresenter();
}
