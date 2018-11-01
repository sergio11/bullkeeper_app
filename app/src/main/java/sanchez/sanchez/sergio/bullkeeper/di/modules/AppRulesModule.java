package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.net.services.IAppRulesService;
import sanchez.sanchez.sergio.data.repository.AppRulesRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.apprules.GetAppRulesInteract;
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
    public IAppRulesRepository provideAppRulesRepository() {
        return new AppRulesRepositoryImpl();
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
    public GetAppRulesInteract provideGetAppRulesInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                          final IAppRulesRepository appRulesRepository){
        return new GetAppRulesInteract(threadExecutor, postExecutionThread, appRulesRepository);
    }

}
