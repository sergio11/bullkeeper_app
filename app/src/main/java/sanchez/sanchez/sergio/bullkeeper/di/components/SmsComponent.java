package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.SmsModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.smsdetail.SmsDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.smsdetail.SmsDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smsdetail.SmsDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smsdetail.SmsDetailFragmentPresenter;

/**
 * SMS Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class, SmsModule.class})
public interface SmsComponent extends ActivityComponent {

    /**
     * Inject to Sms Detail Mvp Activity
     * @param smsDetailMvpActivity
     */
    void inject(final SmsDetailMvpActivity smsDetailMvpActivity);

    /**
     * Inject into SMS Detail Activity Mvp Frament
     * @param smsDetailActivityMvpFragment
     */
    void inject(final SmsDetailActivityMvpFragment smsDetailActivityMvpFragment);

    /**
     * Presenter
     * @return
     */
    SmsDetailPresenter smsDetailPresenter();
    SmsDetailFragmentPresenter smsDetailFragmentPresenter();

}
