package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerService;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.DeviceEntityDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.DeviceDTO;
import sanchez.sanchez.sergio.data.net.services.IDeviceGroupsService;
import sanchez.sanchez.sergio.data.repository.DeviceGroupRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.device.DeleteDeviceInteract;
import sanchez.sanchez.sergio.domain.interactor.device.SaveDeviceInteract;
import sanchez.sanchez.sergio.domain.models.DeviceEntity;
import sanchez.sanchez.sergio.domain.repository.IDeviceGroupRepository;

/**
 * Device Group Module
 */
@Module
public class DeviceGroupModule {

    /**
     * Provide Device Data Mapper
     * @return
     */
    @Provides @PerService
    public AbstractDataMapper<DeviceDTO, DeviceEntity> provideDeviceDataMapper(){
        return new DeviceEntityDataMapper();
    }

    /**
     * Provide Device Group Service
     * @param retrofit
     * @return
     */
    @Provides @PerService
    public IDeviceGroupsService provideDeviceGroupService(final Retrofit retrofit) {
        return retrofit.create(IDeviceGroupsService.class);
    }

    /**
     * Provide Device Group Repository
     * @param deviceDataMapper
     * @param deviceGroupsService
     * @return
     */
    @Provides @PerService
    public IDeviceGroupRepository provideDeviceGroupRepository(final AbstractDataMapper<DeviceDTO, DeviceEntity> deviceDataMapper,
                                                               final IDeviceGroupsService deviceGroupsService) {
        return new DeviceGroupRepositoryImpl(deviceDataMapper, deviceGroupsService);
    }

    /**
     * Provide Save Device Interact
     * @return
     */
    @Provides @PerService
    public SaveDeviceInteract provideSaveDeviceInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                        final IDeviceGroupRepository deviceGroupRepository){
        return new SaveDeviceInteract(threadExecutor, postExecutionThread, deviceGroupRepository);
    }

    /**
     * Provide Delete Device Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param deviceGroupRepository
     * @return
     */
    @Provides @PerService
    public DeleteDeviceInteract provideDeleteDeviceInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                            final IDeviceGroupRepository deviceGroupRepository) {
        return new DeleteDeviceInteract(threadExecutor, postExecutionThread, deviceGroupRepository);
    }
}
