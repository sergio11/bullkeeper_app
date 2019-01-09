package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.GeofenceModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.list.GeofencesListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.list.GeofencesListPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save.SaveGeofenceMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save.SaveGeofencePresenter;

/**
 * Geofence Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, DataMapperModule.class, GeofenceModule.class})
public interface GeofenceComponent extends ActivityComponent {


    /**
     * Inject into Geofences List Mvp Activity
     * @param geofencesListMvpActivity
     */
    void inject(final GeofencesListMvpActivity geofencesListMvpActivity);

    /**
     * Inject into Save Geofence Mvp Activity
     * @param saveGeofenceMvpActivity
     */
    void inject(final SaveGeofenceMvpActivity saveGeofenceMvpActivity);

    /**
     * Geofences List Presenter
     * @return
     */
    GeofencesListPresenter geofencesListPresenter();

    /**
     * Save Geofence Presenter
     * @return
     */
    SaveGeofencePresenter saveGeofencePresenter();
}
