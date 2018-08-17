package sanchez.sanchez.sergio.masom_app.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.home.HomeMvpActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.home.HomePresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.home.HomeMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.home.HomeFragmentPresenter;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class })
public interface HomeComponent extends ActivityComponent {

    /**
     * Inject into Home Activity
     * @param homeActivity
     */
    void inject(final HomeMvpActivity homeActivity);

    /**
     * Inject into Home Fragment
     * @param homeFragment
     */
    void inject(final HomeMvpFragment homeFragment);


    HomePresenter homePresenter();
    HomeFragmentPresenter homeFragmentPresenter();

}
