package sanchez.sanchez.sergio.masom_app.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.alertdetail.AlertDetailActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.alertdetail.AlertDetailPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.alertdetail.AlertDetailActivityFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.alertdetail.AlertDetailFragmentPresenter;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface AlertsComponent extends ActivityComponent {

    /**
     * Alert Detail Activity
     * @param alertDetailActivity
     */
    void inject(final AlertDetailActivity alertDetailActivity);

    /**
     * Alert Detail Activity Fragment
     * @return
     */
    void inject(final AlertDetailActivityFragment alertDetailActivityFragment);

    AlertDetailPresenter alertDetailPresenter();
    AlertDetailFragmentPresenter alertDetailFragmentPresenter();
}
