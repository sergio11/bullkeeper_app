package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.GeofenceModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.GeofencesListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.GeofencesListPresenter;

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

    GeofencesListPresenter geofencesListPresenter();
}
