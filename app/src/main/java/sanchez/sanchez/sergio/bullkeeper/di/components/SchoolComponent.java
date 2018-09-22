package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.SchoolModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create.AddSchoolMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create.AddSchoolPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search.SearchSchoolMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search.SearchSchoolActivityPresenter;

/**
 * School Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class,
                SchoolModule.class })
public interface SchoolComponent {

    /**
     * Search School Activity
     * @param searchSchoolMvpActivity
     */
    void inject(final SearchSchoolMvpActivity searchSchoolMvpActivity);

    /**
     * Add School Mvp Activity
     * @param addSchoolMvpActivity
     */
    void inject(final AddSchoolMvpActivity addSchoolMvpActivity);


    SearchSchoolActivityPresenter searchSchoolActivityPresenter();
    AddSchoolPresenter addSchoolPresenter();

}
