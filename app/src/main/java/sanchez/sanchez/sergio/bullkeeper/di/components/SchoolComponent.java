package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search.SearchSchoolActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search.SearchSchoolActivityPresenter;

/**
 * School Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class })
public interface SchoolComponent {

    /**
     * Search School Activity
     * @param searchSchoolActivity
     */
    void inject(final SearchSchoolActivity searchSchoolActivity);


    SearchSchoolActivityPresenter searchSchoolActivityPresenter();

}
