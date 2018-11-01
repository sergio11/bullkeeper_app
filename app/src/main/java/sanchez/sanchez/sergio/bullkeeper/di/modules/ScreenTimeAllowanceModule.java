package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.net.services.IScreenTimeAllowanceService;
import sanchez.sanchez.sergio.data.repository.ScreenTimeAllowanceRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.timeallowance.GetScreenTimeAllowanceByChildIdInteract;
import sanchez.sanchez.sergio.domain.repository.IScreenTimeAllowanceRepository;

/**
 * Screen Time Allowance Module
 */

@Module
public class ScreenTimeAllowanceModule {

    /**
     * Provide Screen Time Allowance Service
     * @param retrofit
     * @return
     */
    @Provides
    @PerActivity
    public IScreenTimeAllowanceService provideScreenTimeAllowanceService(final Retrofit retrofit) {
        return retrofit.create(IScreenTimeAllowanceService.class);
    }

    /**
     * Provide Screen Time Allowance Repository
     * @param screenTimeAllowanceService
     * @return
     */
    @Provides
    @PerActivity
    public IScreenTimeAllowanceRepository provideIScreenTimeAllowanceRepository(final IScreenTimeAllowanceService screenTimeAllowanceService) {
        return new ScreenTimeAllowanceRepositoryImpl(screenTimeAllowanceService);
    }


    /**
     * Provide Get Screen Time Allowance By Child Id Interact
     * @return
     */
    @Provides
    @PerActivity
    public GetScreenTimeAllowanceByChildIdInteract proviceGetScreenTimeAllowanceByChildIdInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                                  final IScreenTimeAllowanceRepository screenTimeAllowanceRepository) {
        return new GetScreenTimeAllowanceByChildIdInteract(threadExecutor, postExecutionThread, screenTimeAllowanceRepository);
    }
}
