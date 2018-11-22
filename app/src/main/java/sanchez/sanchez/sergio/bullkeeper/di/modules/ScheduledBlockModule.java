package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveScheduledBlockStatusDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.ScheduledBlockDTO;
import sanchez.sanchez.sergio.data.net.services.IScheduledBlockService;
import sanchez.sanchez.sergio.data.repository.ScheduledBlockRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.scheduled.DeleteAllScheduledBlockInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.DeleteAllScheduledBlocksInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.DeleteScheduledBlockByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.GetScheduledBlockByChildInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.GetScheduledBlockDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.SaveScheduledBlockInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.SaveScheduledBlockStatusInteract;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockStatusEntity;
import sanchez.sanchez.sergio.domain.repository.IScheduledBlockRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Schedule Block Module
 */
@Module
public class ScheduledBlockModule {

    /**
     * Provide Scheduled Block Service
     * @param retrofit
     * @return
     */
    @Provides
    @PerActivity
    public IScheduledBlockService provideScheduledBlockService(final Retrofit retrofit){
        return retrofit.create(IScheduledBlockService.class);
    }

    /**
     * Provide Scheduled Block Repository
     * @param scheduledBlockDataMapper
     * @param scheduledBlockService
     * @param saveScheduledBlockStatusDataMapper
     * @param imageEntityAbstractDataMapper
     * @return
     */
    @Provides
    @PerActivity
    public IScheduledBlockRepository provideScheduledBlockRepository(final AbstractDataMapper<ScheduledBlockDTO, ScheduledBlockEntity> scheduledBlockDataMapper,
                                                                     final IScheduledBlockService scheduledBlockService,
                                                                     final AbstractDataMapper<SaveScheduledBlockStatusDTO, ScheduledBlockStatusEntity> saveScheduledBlockStatusDataMapper,
                                                                     final AbstractDataMapper<ImageDTO, ImageEntity> imageEntityAbstractDataMapper){
        return new ScheduledBlockRepositoryImpl(scheduledBlockDataMapper, scheduledBlockService,
                saveScheduledBlockStatusDataMapper, imageEntityAbstractDataMapper);
    }

    /**
     * Provide Get Scheduled Block By Child Interact
     * @return
     */
    @Provides
    @PerActivity
    public GetScheduledBlockByChildInteract provideGetScheduledBlockByChildInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                    final IScheduledBlockRepository scheduledBlockRepository){
        return new GetScheduledBlockByChildInteract(threadExecutor, postExecutionThread, scheduledBlockRepository);
    }

    /**
     * Provide Delete Scheduled Block Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param scheduledBlockRepository
     * @return
     */
    @Provides
    @PerActivity
    public DeleteScheduledBlockByIdInteract provideDeleteScheduledBlockInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                final IScheduledBlockRepository scheduledBlockRepository){
        return new DeleteScheduledBlockByIdInteract(threadExecutor, postExecutionThread, scheduledBlockRepository);
    }

    /**
     * Provide Delete All Scheduled Block Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param scheduledBlockRepository
     * @return
     */
    @Provides
    @PerActivity
    public DeleteAllScheduledBlockInteract provideDeleteAllScheduledBlockInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                               final IScheduledBlockRepository scheduledBlockRepository){
        return new DeleteAllScheduledBlockInteract(threadExecutor, postExecutionThread, scheduledBlockRepository);
    }

    /**
     * Provide Save Scheduled Block Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param scheduledBlockRepository
     * @return
     */
    @Provides
    @PerActivity
    public SaveScheduledBlockInteract provideSaveScheduledBlockInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                        final IScheduledBlockRepository scheduledBlockRepository, final IAppUtils appUtils){
        return new SaveScheduledBlockInteract(threadExecutor, postExecutionThread, scheduledBlockRepository, appUtils);
    }

    /**
     * Provide Get Scheduled Block Detail Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param scheduledBlockRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetScheduledBlockDetailInteract provideGetScheduledBlockDetailInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                  final IScheduledBlockRepository scheduledBlockRepository){
        return new GetScheduledBlockDetailInteract(threadExecutor, postExecutionThread, scheduledBlockRepository);
    }

    /**
     * Provide Delete All Scheduled Blocks Interact
     * @return
     */
    @Provides
    @PerActivity
    public DeleteAllScheduledBlocksInteract provideDeleteAllScheduledBlocksInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                    final IScheduledBlockRepository scheduledBlockRepository){
        return new DeleteAllScheduledBlocksInteract(threadExecutor, postExecutionThread, scheduledBlockRepository);
    }

    /**
     * Provide Save Scheduled Block Status Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param scheduledBlockRepository
     * @return
     */
    @Provides
    @PerActivity
    public SaveScheduledBlockStatusInteract provideSaveScheduledBlockStatusInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                    final IScheduledBlockRepository scheduledBlockRepository){
        return new SaveScheduledBlockStatusInteract(threadExecutor, postExecutionThread, scheduledBlockRepository);
    }

}
