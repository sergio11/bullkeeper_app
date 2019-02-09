package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.AppInstalledRuleDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledByTerminalDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDetailDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppStatsDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.data.net.services.IAppRulesService;
import sanchez.sanchez.sergio.data.repository.AppRulesRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.apprules.ChangeAppStatusInteract;
import sanchez.sanchez.sergio.domain.interactor.apprules.GetAllAppInstalledByKidInteract;
import sanchez.sanchez.sergio.domain.interactor.apprules.GetAppInstalledDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.apprules.GetAppInstalledInteract;
import sanchez.sanchez.sergio.domain.interactor.apprules.GetStatisticsOfTheFiveMostUsedApplicationsInteract;
import sanchez.sanchez.sergio.domain.interactor.apprules.UpdateAppInstalledRulesByChildInteract;
import sanchez.sanchez.sergio.domain.interactor.apprules.UpdateSingleAppInstalledRulesByChildInteract;
import sanchez.sanchez.sergio.domain.models.AppInstalledByTerminalEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledDetailEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledRuleEntity;
import sanchez.sanchez.sergio.domain.models.AppStatsEntity;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import sanchez.sanchez.sergio.domain.repository.IAppRulesRepository;

/**
 * App Rules Module
 */
@Module
public class AppRulesModule {

    /**
     * Provide App Rules Service
     * @param retrofit
     * @return
     */
    @Provides
    @PerActivity
    public IAppRulesService provideAppRulesService(final Retrofit retrofit) {
        return retrofit.create(IAppRulesService.class);
    }

    /**
     * Provide App Rules Repository
     * @return
     */
    @Provides
    @PerActivity
    public IAppRulesRepository provideAppRulesRepository(final AbstractDataMapper<AppInstalledDTO, AppInstalledEntity> appInstalledEntityAbstractDataMapper,
                                                         IAppRulesService appRulesService,
                                                         final AbstractDataMapper<AppInstalledRuleDTO, AppInstalledRuleEntity> appInstalledRuleDTOAbstractDataMapper,
                                                         final AbstractDataMapper<AppStatsDTO, AppStatsEntity> appStatsEntityAbstractDataMapper,
                                                         final AbstractDataMapper<AppInstalledByTerminalDTO, AppInstalledByTerminalEntity> terminalEntityAbstractDataMapper,
                                                         final AbstractDataMapper<AppInstalledDetailDTO, AppInstalledDetailEntity> appInstalledDetailEntityAbstractDataMapper) {
        return new AppRulesRepositoryImpl(appInstalledEntityAbstractDataMapper, appRulesService, appInstalledRuleDTOAbstractDataMapper,
                appStatsEntityAbstractDataMapper, terminalEntityAbstractDataMapper, appInstalledDetailEntityAbstractDataMapper);
    }

    /**
     * Provide Get App Rules Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param appRulesRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetAppInstalledInteract provideGetAppRulesInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                              final IAppRulesRepository appRulesRepository){
        return new GetAppInstalledInteract(threadExecutor, postExecutionThread, appRulesRepository);
    }

    /**
     * Provide Update App Installed Rules By Child Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param appRulesRepository
     * @return
     */
    @Provides
    @PerActivity
    public UpdateAppInstalledRulesByChildInteract provideUpdateAppInstalledRulesByChildInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                                final IAppRulesRepository appRulesRepository) {
        return new UpdateAppInstalledRulesByChildInteract(threadExecutor, postExecutionThread, appRulesRepository);
    }

    /**
     * Provide Get App Installed Detail Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param appRulesRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetAppInstalledDetailInteract provideGetAppInstalledDetailInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAppRulesRepository appRulesRepository) {
        return new GetAppInstalledDetailInteract(threadExecutor, postExecutionThread, appRulesRepository);
    }

    /**
     * Provide UpdateSingleAppInstalledRulesByChildInteract
     * @return
     */
    @Provides
    @PerActivity
    public UpdateSingleAppInstalledRulesByChildInteract provideUpdateSingleAppInstalledRulesByChildInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAppRulesRepository appRulesRepository
    ){
        return new UpdateSingleAppInstalledRulesByChildInteract(threadExecutor, postExecutionThread, appRulesRepository);
    }


    /**
     * Provide Disable App Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param appRulesRepository
     * @return
     */
    @Provides
    @PerActivity
    public ChangeAppStatusInteract provideDisableAppInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAppRulesRepository appRulesRepository
    ){
        return new ChangeAppStatusInteract(threadExecutor, postExecutionThread, appRulesRepository);
    }

    /**
     * Provide Get Statistics Of The Five Most Used Applications Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param appRulesRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetStatisticsOfTheFiveMostUsedApplicationsInteract provideGetStatisticsOfTheFiveMostUsedApplicationsInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAppRulesRepository appRulesRepository
    ){
        return new GetStatisticsOfTheFiveMostUsedApplicationsInteract(threadExecutor, postExecutionThread, appRulesRepository);
    }

    /**
     * Provide Get App Installed By Kid Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param appRulesRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetAllAppInstalledByKidInteract provideGetAppInstalledByKidInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAppRulesRepository appRulesRepository
    ){
        return new GetAllAppInstalledByKidInteract(threadExecutor, postExecutionThread, appRulesRepository);
    }

}
