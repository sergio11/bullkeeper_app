package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.DevicePhotoDTO;
import sanchez.sanchez.sergio.data.net.services.IDevicePhotosService;
import sanchez.sanchez.sergio.data.repository.DevicePhotosRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.photos.GetDevicePhotosInteract;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;
import sanchez.sanchez.sergio.domain.repository.IDevicePhotosRepository;

/**
 * Device Photos Module
 **/
@Module
public class DevicePhotosModule {

    /**
     * Provide Device Photos Service
     * @param retrofit
     * @return
     */
    @Provides @PerActivity
    public IDevicePhotosService provideDevicePhotosService(final Retrofit retrofit){
        return retrofit.create(IDevicePhotosService.class);
    }

    /**
     *
     * @param devicePhotosService
     * @param devicePhotoEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public IDevicePhotosRepository provideDevicePhotosRepository(
            final IDevicePhotosService devicePhotosService,
            final AbstractDataMapper<DevicePhotoDTO, DevicePhotoEntity> devicePhotoEntityAbstractDataMapper
    ){
        return new DevicePhotosRepositoryImpl(devicePhotoEntityAbstractDataMapper, devicePhotosService);
    }

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param devicePhotosRepository
     * @return
     */
    @Provides @PerActivity
    public GetDevicePhotosInteract provideGetDevicePhotosInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IDevicePhotosRepository devicePhotosRepository
    ){
        return new GetDevicePhotosInteract(threadExecutor, postExecutionThread, devicePhotosRepository);
    }

}
