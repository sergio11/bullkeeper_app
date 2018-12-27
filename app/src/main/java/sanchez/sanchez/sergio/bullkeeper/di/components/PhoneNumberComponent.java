package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.PhoneNumberBlockedModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.phonenumbersblocked.PhoneNumbersBlockedListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.phonenumbersblocked.PhoneNumbersBlockedListPresenter;

/**
 * Phone Number Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class,
                PhoneNumberBlockedModule.class })
public interface PhoneNumberComponent extends ActivityComponent {

    /**
     * Inject into Phone Numbers Blocked Mvp Activity
     * @param phoneNumbersBlockedListMvpActivity
     */
    void inject(final PhoneNumbersBlockedListMvpActivity phoneNumbersBlockedListMvpActivity);

    /**
     *
     * @return
     */
    PhoneNumbersBlockedListPresenter phoneNumbersBlockedListPresenter();

}
