package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertsPageDTO;
import sanchez.sanchez.sergio.data.net.services.IAlertService;
import sanchez.sanchez.sergio.data.repository.AlertsRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.alerts.ClearAlertsByKidInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.ClearAlertsByLevelInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.ClearAlertsOfKidByLevelInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.ClearSelfAlertsInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.DeleteAlertOfKidInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetAlertDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetAlertsByKidInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetDangerAlertsOfSonForSelfParentInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetSelfAlertsByLevelInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetSelfAlertsInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetSelfAlertsOfKidByLevelInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetSelfLastAlertsInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetWarningAlertsOfSonForSelfParentInteract;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertsPageEntity;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;

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
                                                     final AbstractDataMapper<AlertDTO, AlertEntity> alertDataMapper,
                                                     final AbstractDataMapper<AlertsPageDTO, AlertsPageEntity> alertsPageDataMapper) {
        return new AlertsRepositoryImpl(alertService, alertDataMapper, alertsPageDataMapper);
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
                                                              final IAlertsRepository alertsRepository, final IPreferenceRepository preferenceRepository){
        return new GetSelfAlertsInteract(threadExecutor, postExecutionThread, alertsRepository, preferenceRepository);
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
    public DeleteAlertOfKidInteract provideDeleteAlertOfSonInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                    final IAlertsRepository alertsRepository){
        return new DeleteAlertOfKidInteract(threadExecutor, postExecutionThread, alertsRepository);
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
    public GetAlertsByKidInteract provideGetAlertsBySonInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                final IAlertsRepository alertsRepository) {
        return new GetAlertsByKidInteract(threadExecutor, postExecutionThread, alertsRepository);
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
    public ClearAlertsByKidInteract provideClearAlertsBySonInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                    final IAlertsRepository alertsRepository){
        return new ClearAlertsByKidInteract(threadExecutor, postExecutionThread, alertsRepository);
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

    /**
     * Provide Get Self Last Alerts Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetSelfLastAlertsInteract provideGetSelfLastAlertsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                      final IAlertsRepository alertsRepository, final IPreferenceRepository preferenceRepository){
        return new GetSelfLastAlertsInteract(threadExecutor, postExecutionThread, alertsRepository, preferenceRepository);
    }

    /**
     * Provide Get Self Alerts By Level Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @param preferenceRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetSelfAlertsByLevelInteract provideGetSelfAlertsByLevelInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                            final IAlertsRepository alertsRepository, final IPreferenceRepository preferenceRepository){
        return new GetSelfAlertsByLevelInteract(threadExecutor, postExecutionThread, alertsRepository, preferenceRepository);
    }

    /**
     * Provide Clear Alerts By Level Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @return
     */
    @Provides
    @PerActivity
    public ClearAlertsByLevelInteract provideClearAlertsByLevelInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                        final IAlertsRepository alertsRepository){
        return new ClearAlertsByLevelInteract(threadExecutor, postExecutionThread, alertsRepository);
    }

    /**
     * Provide Get Self Alerts of Son By Level Interact
     * @return
     */
    @Provides @PerActivity
    public GetSelfAlertsOfKidByLevelInteract provideGetSelfAlertsOfSonByLevelInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                      final IAlertsRepository alertsRepository, final IPreferenceRepository preferenceRepository){
        return new GetSelfAlertsOfKidByLevelInteract(threadExecutor, postExecutionThread, alertsRepository, preferenceRepository);
    }

    /**
     * Provide Clear Alerts Of Son By Level Interact
     * @return
     */
    @Provides @PerActivity
    public ClearAlertsOfKidByLevelInteract provideClearAlertsOfSonByLevelInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                  final IAlertsRepository alertsRepository){
        return new ClearAlertsOfKidByLevelInteract(threadExecutor, postExecutionThread, alertsRepository);
    }

    /**
     * Provide Get Danger Alerts Of Son For Self Parent
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @return
     */
    @Provides @PerActivity
    GetDangerAlertsOfSonForSelfParentInteract provideGetDangerAlertsOfSonForSelfParent(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                       final IAlertsRepository alertsRepository){
        return new GetDangerAlertsOfSonForSelfParentInteract(threadExecutor, postExecutionThread, alertsRepository);
    }

    /**
     * Provide Get Warning Alerts Of Son for self Parent
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @return
     */
    @Provides @PerActivity
    GetWarningAlertsOfSonForSelfParentInteract provideGetWarningAlertsOfSonForSelfParentInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                                 final IAlertsRepository alertsRepository){
        return new GetWarningAlertsOfSonForSelfParentInteract(threadExecutor, postExecutionThread, alertsRepository);
    }



}
