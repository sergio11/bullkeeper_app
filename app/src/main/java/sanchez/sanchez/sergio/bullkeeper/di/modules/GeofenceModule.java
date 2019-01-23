package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.GeofenceDTO;
import sanchez.sanchez.sergio.data.net.services.IGeofenceService;
import sanchez.sanchez.sergio.data.repository.GeofenceRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.geofences.DeleteAllGeofencesBykidInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.DeleteGeofenceByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.GetAllGeofencesByKidInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.GetGeofenceByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.SaveGeofenceInteract;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.repository.IGeofencesRepository;

/**
 * Geofence Module
 */
@Module
public class GeofenceModule {

    /**
     * Provide Geofence Service
     * @param retrofit
     * @return
     */
    @Provides
    @PerActivity
    public IGeofenceService provideGeofenceService(final Retrofit retrofit) {
        return retrofit.create(IGeofenceService.class);
    }

    /**
     * Provide Geofence Repository
     * @param geofenceEntityAbstractDataMapper
     * @param geofenceService
     * @return
     */
    @Provides
    @PerActivity
    public IGeofencesRepository provideGeofenceRepository(
            final AbstractDataMapper<GeofenceDTO, GeofenceEntity> geofenceEntityAbstractDataMapper,
            final IGeofenceService geofenceService) {
        return new GeofenceRepositoryImpl(geofenceEntityAbstractDataMapper,
                geofenceService);
    }

    /**
     * Provide Delete All Geofence By Kid Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param geofencesRepository
     * @return
     */
    @Provides
    @PerActivity
    public DeleteAllGeofencesBykidInteract provideDeleteAllGeofencesBykidInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IGeofencesRepository geofencesRepository
    ){
        return new DeleteAllGeofencesBykidInteract(threadExecutor, postExecutionThread, geofencesRepository);
    }

    /**
     * Delete Geofence By Id Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param geofencesRepository
     * @return
     */
    @Provides
    @PerActivity
    public DeleteGeofenceByIdInteract provideDeleteGeofenceByIdInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IGeofencesRepository geofencesRepository
    ){
        return new DeleteGeofenceByIdInteract(threadExecutor, postExecutionThread, geofencesRepository);
    }

    /**
     * Get All Geofences By Kid Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param geofencesRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetAllGeofencesByKidInteract provideGetAllGeofencesByKidInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IGeofencesRepository geofencesRepository
    ){
        return new GetAllGeofencesByKidInteract(threadExecutor, postExecutionThread, geofencesRepository);
    }

    /**
     * Provide Save Geofence Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param geofencesRepository
     * @return
     */
    @Provides
    @PerActivity
    public SaveGeofenceInteract provideSaveGeofenceInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IGeofencesRepository geofencesRepository
    ){
        return new SaveGeofenceInteract(threadExecutor, postExecutionThread, geofencesRepository);
    }

    /**
     * Provide Get Geofence By Id
     * @param threadExecutor
     * @param postExecutionThread
     * @param geofencesRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetGeofenceByIdInteract provideGetGeofenceByIdInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IGeofencesRepository geofencesRepository
    ) {
        return new GetGeofenceByIdInteract(threadExecutor, postExecutionThread, geofencesRepository);
    }

}
