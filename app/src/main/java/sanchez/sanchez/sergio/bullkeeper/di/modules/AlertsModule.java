package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.services.IAlertService;
import sanchez.sanchez.sergio.data.repository.AlertsRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.alerts.ClearAlertsBySonInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.ClearSelfAlertsInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.DeleteAlertOfSonInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetAlertDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetAlertsBySonInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetSelfAlertsInteract;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;

/**
 * Alerts Module
 */
@Module
public class AlertsModule {

    /**
     * Provide Alerts Service
     * @return
     */
    @Provides
    @PerActivity
    public IAlertService provideAlertsService(final Retrofit retrofit){
        return retrofit.create(IAlertService.class);
    }

    /**
     * Provide Alerts Repository
     * @param alertService
     * @param alertDataMapper
     * @return
     */
    @Provides
    @PerActivity
    public IAlertsRepository provideAlertsRepository(final IAlertService alertService,
                                                     final AbstractDataMapper<AlertDTO, AlertEntity> alertDataMapper) {
        return new AlertsRepositoryImpl(alertService, alertDataMapper);
    }

    /**
     * Provide Get Self Alerts Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetSelfAlertsInteract provideGetSelfAlertsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                              final IAlertsRepository alertsRepository){
        return new GetSelfAlertsInteract(threadExecutor, postExecutionThread, alertsRepository);
    }

    /**
     * Provide Delete Alert Of Son Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @return
     */
    @Provides
    @PerActivity
    public DeleteAlertOfSonInteract provideDeleteAlertOfSonInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                    final IAlertsRepository alertsRepository){
        return new DeleteAlertOfSonInteract(threadExecutor, postExecutionThread, alertsRepository);
    }

    /**
     * Provide CLear Self Alerts Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @return
     */
    @Provides
    @PerActivity
    public ClearSelfAlertsInteract provideClearSelfAlertsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                  final IAlertsRepository alertsRepository){
        return new ClearSelfAlertsInteract(threadExecutor, postExecutionThread, alertsRepository);
    }

    /**
     * Provide Get Alerts By Son Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetAlertsBySonInteract provideGetAlertsBySonInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                final IAlertsRepository alertsRepository) {
        return new GetAlertsBySonInteract(threadExecutor, postExecutionThread, alertsRepository);
    }

    /**
     * Provide Clear Alerts By Son Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @return
     */
    @Provides
    @PerActivity
    public ClearAlertsBySonInteract provideClearAlertsBySonInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                    final IAlertsRepository alertsRepository){
        return new ClearAlertsBySonInteract(threadExecutor, postExecutionThread, alertsRepository);
    }

    /**
     * Provide Get Alert Detail Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetAlertDetailInteract provideGetAlertDetailInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                final IAlertsRepository alertsRepository) {
        return new GetAlertDetailInteract(threadExecutor, postExecutionThread, alertsRepository);
    }


}
