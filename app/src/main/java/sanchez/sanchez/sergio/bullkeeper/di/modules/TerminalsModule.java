package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDetailDTO;
import sanchez.sanchez.sergio.data.net.services.ITerminalService;
import sanchez.sanchez.sergio.data.repository.TerminalRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.terminal.DeleteTerminalInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.GetMonitoredTerminalsInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.GetTerminalDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.SwitchBedTimeStatusInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.SwitchLockCameraStatusInteract;
import sanchez.sanchez.sergio.domain.interactor.terminal.SwitchLockScreenStatusInteract;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import sanchez.sanchez.sergio.domain.repository.ITerminalRepository;

/**
 * Terminals Module
 */
@Module
public class TerminalsModule {


    /**
     * Provide Terminal Service
     * @param retrofit
     * @return
     */
    @Provides
    @PerActivity
    protected ITerminalService provideTerminalService(final Retrofit retrofit) {
        return retrofit.create(ITerminalService.class);
    }

    /**
     * Provide Terminal Repository
     * @param terminalService
     * @param terminalEntityAbstractDataMapper
     * @return
     */
    @Provides
    @PerActivity
    protected ITerminalRepository provideTerminalRepository(final ITerminalService terminalService,
                                                            final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalEntityAbstractDataMapper,
                                                            final AbstractDataMapper<TerminalDetailDTO, TerminalDetailEntity> terminalDetailEntityAbstractDataMapper) {
        return new TerminalRepositoryImpl(terminalService, terminalEntityAbstractDataMapper, terminalDetailEntityAbstractDataMapper);
    }

    /**
     * Provide Get Monitored Terminals Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param terminalRepository
     * @return
     */
    @Provides
    @PerActivity
    protected GetMonitoredTerminalsInteract provideGetMonitoredTerminalsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                 final ITerminalRepository terminalRepository){
        return new GetMonitoredTerminalsInteract(threadExecutor, postExecutionThread, terminalRepository);
    }


    /**
     * Provide Get Terminal Detail Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param terminalRepository
     * @return
     */
    @Provides
    @PerActivity
    protected GetTerminalDetailInteract provideGetTerminalDetailInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                         final ITerminalRepository terminalRepository){
        return new GetTerminalDetailInteract(threadExecutor, postExecutionThread, terminalRepository);
    }

    /**
     * Provide Delete Terminal Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param terminalRepository
     * @return
     */
    @Provides
    @PerActivity
    protected DeleteTerminalInteract provideDeleteTerminalInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                   final ITerminalRepository terminalRepository){
        return new DeleteTerminalInteract(threadExecutor, postExecutionThread, terminalRepository);
    }

    /**
     * Provide Switch Bed Time Status Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param terminalRepository
     * @return
     */
    @Provides
    @PerActivity
    public SwitchBedTimeStatusInteract provideSwitchBedTimeStatusInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                          final ITerminalRepository terminalRepository){
        return new SwitchBedTimeStatusInteract(threadExecutor, postExecutionThread, terminalRepository);
    }

    /**
     * Provide Switch Lock Camera Status Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param terminalRepository
     * @return
     */
    @Provides
    @PerActivity
    public SwitchLockCameraStatusInteract provideSwitchLockCameraStatusInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                final ITerminalRepository terminalRepository){
        return new SwitchLockCameraStatusInteract(threadExecutor, postExecutionThread, terminalRepository);
    }

    /**
     * Provide Switch Lock Screen Status Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param terminalRepository
     * @return
     */
    @Provides
    @PerActivity
    public SwitchLockScreenStatusInteract provideSwitchLockScreenStatusInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                final ITerminalRepository terminalRepository){
        return new SwitchLockScreenStatusInteract(threadExecutor, postExecutionThread, terminalRepository);
    }

}
