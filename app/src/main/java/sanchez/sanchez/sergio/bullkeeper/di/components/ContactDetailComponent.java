package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ContactsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.PhoneNumberBlockedModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.contactdetail.ContactDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.contactdetail.ContactDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactdetail.ContactDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactdetail.ContactDetailFragmentPresenter;

/**
 * Contact Detail Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class,
                ContactsModule.class,
                PhoneNumberBlockedModule.class})
public interface ContactDetailComponent extends ActivityComponent {

    /**
     * Inject into Contact Detail Activity
     * @param contactDetailMvpActivity
     */
    void inject(final ContactDetailMvpActivity contactDetailMvpActivity);

    /**
     * Inject into Contact Detail Activity Mvp Fragment
     * @param contactDetailActivityMvpFragment
     */
    void inject(final ContactDetailActivityMvpFragment contactDetailActivityMvpFragment);

    /**
     * Presenters
     * @return
     */
    ContactDetailPresenter contactDetailPresenter();
    ContactDetailFragmentPresenter contactDetailFragmentPresenter();
}
