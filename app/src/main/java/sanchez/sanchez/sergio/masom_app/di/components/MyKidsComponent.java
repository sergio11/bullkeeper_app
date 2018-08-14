package sanchez.sanchez.sergio.masom_app.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykids.MyKidsActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykids.MyKidsActivityPresenter;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykidsprofile.MyKidsProfileActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykidsprofile.MyKidsProfilePresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.mykids.MyKidsActivityFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.mykids.MyKidsFragmentPresenter;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface MyKidsComponent extends ActivityComponent {

    /**
     * My Kids Activity
     * @param myKidsActivity
     */
    void inject(final MyKidsActivity myKidsActivity);

    /**
     * My Kids Activity Fragment
     * @param myKidsActivityFragment
     */
    void inject(final MyKidsActivityFragment myKidsActivityFragment);

    /**
     * My Kids Profile Activity
     * @param myKidsProfileActivity
     */
    void inject(final MyKidsProfileActivity myKidsProfileActivity);


    MyKidsActivityPresenter myKidsActivityPresenter();
    MyKidsFragmentPresenter myKidsFragmentPresenter();
    MyKidsProfilePresenter myKidsProfilePresenter();
}
