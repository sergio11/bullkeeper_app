package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ScheduledBlockModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.savescheduledblock.SaveScheduledBlockMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.savescheduledblock.SaveScheduledBlockPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.appallowedbyscheduled.AppAllowedByScheduledFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.appallowedbyscheduled.AppAllowedByScheduledMvpFragment;

/**
 * Scheduled Block Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class, ScheduledBlockModule.class})
public interface ScheduledBlockComponent extends ActivityComponent {

    /**
     * Inject into Save Scheduled Block Mvp Activity
     * @param saveScheduledBlockMvpActivity
     */
    void inject(final SaveScheduledBlockMvpActivity saveScheduledBlockMvpActivity);

    /**
     * Inject into App Allowed By Scheduled Mvp Fragment
     * @param appAllowedByScheduledMvpFragment
     */
    void inject(final AppAllowedByScheduledMvpFragment appAllowedByScheduledMvpFragment);

    /**
     * Save Scheduled Block Presenter
     * @return
     */
    SaveScheduledBlockPresenter saveScheduledBlockPresenter();

    /**
     * App Allowed By Scheduled Fragment Presenter
     * @return
     */
    AppAllowedByScheduledFragmentPresenter appAllowedByScheduledFragmentPresenter();
}
