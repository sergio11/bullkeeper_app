package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.GuardianModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.summarymykidsresults.SummaryMyKidsResultsActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.summarymykidsresults.SummaryMyKidsResultsPresenter;

/**
 * Summary My Kids Results Component
 */
@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class, GuardianModule.class })
public interface SummaryMyKidsResultsComponent extends ActivityComponent {

    /**
     * Inject into Summary My Kids Results Activity
     * @param summaryMyKidsResultsActivity
     */
    void inject(final SummaryMyKidsResultsActivity summaryMyKidsResultsActivity);

    /**
     * Summary My Kids Results Presenter
     * @return
     */
    SummaryMyKidsResultsPresenter summaryMyKidsResultsPresenter();

}
