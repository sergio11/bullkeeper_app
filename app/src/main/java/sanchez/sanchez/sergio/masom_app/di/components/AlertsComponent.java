package sanchez.sanchez.sergio.masom_app.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.alertdetail.AlertDetailMvpActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.alertdetail.AlertDetailPresenter;
import sanchez.sanchez.sergio.masom_app.ui.activity.alertlist.AlertListMvpActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.alertlist.AlertListPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.alertdetail.AlertDetailActivityMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.alertdetail.AlertDetailFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.alertslist.FilterAlertsDialog;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface AlertsComponent extends ActivityComponent {

    /**
     * Alert Detail Activity
     * @param alertDetailActivity
     */
    void inject(final AlertDetailMvpActivity alertDetailActivity);

    /**
     * Alert Detail Activity Fragment
     * @return
     */
    void inject(final AlertDetailActivityMvpFragment alertDetailActivityFragment);

    /**
     * Alert List Activity
     * @param alertListActivity
     */
    void inject(final AlertListMvpActivity alertListActivity);

    /**
     * Inject on Filter Alerts Dialog
     * @param FilterAlertsDialog
     */
    void inject(final FilterAlertsDialog FilterAlertsDialog);

    AlertDetailPresenter alertDetailPresenter();
    AlertDetailFragmentPresenter alertDetailFragmentPresenter();
    AlertListPresenter alertListPresenter();
}
