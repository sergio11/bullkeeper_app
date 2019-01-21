package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.FunTimeModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.dayscheduleddetail.DayScheduledMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.dayscheduleddetail.DayScheduledPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.dayscheduleddetail.DayScheduledDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.dayscheduleddetail.DayScheduledDetailFragmentPresenter;


/**
 * Fun Time Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, DataMapperModule.class, FunTimeModule.class})
public interface FunTimeComponent extends ActivityComponent {

    /**
     * Inject into Day Scheduled
     * @param dayScheduledMvpActivity
     */
    void inject(final DayScheduledMvpActivity dayScheduledMvpActivity);

    /**
     * Inject into Day Scheduled
     * @param dayScheduledDetailActivityMvpFragment
     */
    void inject(final DayScheduledDetailActivityMvpFragment dayScheduledDetailActivityMvpFragment);


    /**
     * Presenters
     * @return
     */
    DayScheduledPresenter dayScheduledPresenter();
    DayScheduledDetailFragmentPresenter dayScheduledDetailFragmentPresenter();
}
