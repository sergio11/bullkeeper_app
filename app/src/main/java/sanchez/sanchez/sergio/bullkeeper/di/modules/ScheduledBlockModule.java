package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ScheduledBlockDTO;
import sanchez.sanchez.sergio.data.net.services.IScheduledBlockService;
import sanchez.sanchez.sergio.data.repository.ScheduledBlockRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.scheduled.DeleteScheduledBlockInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.GetScheduledBlockByChildInteract;
import sanchez.sanchez.sergio.domain.interactor.scheduled.SaveScheduledBlockInteract;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import sanchez.sanchez.sergio.domain.repository.IScheduledBlockRepository;

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
     * @return
     */
    @Provides
    @PerActivity
    public IScheduledBlockRepository provideScheduledBlockRepository(final AbstractDataMapper<ScheduledBlockDTO, ScheduledBlockEntity> scheduledBlockDataMapper,
                                                                     final IScheduledBlockService scheduledBlockService){
        return new ScheduledBlockRepositoryImpl(scheduledBlockDataMapper, scheduledBlockService);
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
    public DeleteScheduledBlockInteract provideDeleteScheduledBlockInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                            final IScheduledBlockRepository scheduledBlockRepository){
        return new DeleteScheduledBlockInteract(threadExecutor, postExecutionThread, scheduledBlockRepository);
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
                                                                        final IScheduledBlockRepository scheduledBlockRepository){
        return new SaveScheduledBlockInteract(threadExecutor, postExecutionThread, scheduledBlockRepository);
    }

}
