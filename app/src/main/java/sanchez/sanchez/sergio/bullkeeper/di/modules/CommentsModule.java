package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsBySocialMediaDTO;
import sanchez.sanchez.sergio.data.net.services.ICommentsService;
import sanchez.sanchez.sergio.data.repository.CommentsRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.comments.GetCommentsStatisticsBySocialMediaInteract;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.repository.ICommentsRepository;

/**
 * Comments Module
 */
@Module
public class CommentsModule {

    /**
     * Provide Comments Service
     * @param retrofit
     * @return
     */
    @Provides @PerActivity
    public ICommentsService provideCommentsService(final Retrofit retrofit){
        return retrofit.create(ICommentsService.class);
    }

    /**
     * Provide Comments Repository
     * @param commentsService
     * @param commentsStatisticsDataMapper
     * @return
     */
    @Provides @PerActivity
    public ICommentsRepository provideCommentsRepository(final ICommentsService commentsService,
                                                         final AbstractDataMapper<CommentsStatisticsBySocialMediaDTO,
                                                                 CommentsStatisticsBySocialMediaEntity> commentsStatisticsDataMapper ) {
        return new CommentsRepositoryImpl(commentsService, commentsStatisticsDataMapper);
    }

    /**
     * Provide Get Comments Statistics By Social Media Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param commentsRepository
     * @return
     */
    @Provides @PerActivity
    public GetCommentsStatisticsBySocialMediaInteract provideGetCommentsStatisticsBySocialMediaInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final ICommentsRepository commentsRepository
    ){
        return new GetCommentsStatisticsBySocialMediaInteract(threadExecutor, postExecutionThread, commentsRepository);
    }

}
