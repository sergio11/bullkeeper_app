package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.AlertsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertdetail.AlertDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertdetail.AlertDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist.AlertListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist.AlertListPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertdetail.AlertDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertdetail.AlertDetailFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertslist.FilterAlertsDialog;

@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class, AlertsModule.class})
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
