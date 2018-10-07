package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CommentDTO;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsBySocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaLikesStatisticsDTO;
import sanchez.sanchez.sergio.data.net.services.ICommentsService;
import sanchez.sanchez.sergio.data.repository.CommentsRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.comments.GetCommentsInteract;
import sanchez.sanchez.sergio.domain.interactor.comments.GetCommentsStatisticsBySocialMediaInteract;
import sanchez.sanchez.sergio.domain.interactor.comments.GetSocialMediaLikesStatisticsInteract;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;
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
     * @param socialMediaLikesStatisticsDataMapper
     * @param  commentEntityDataMapper
     * @return
     */
    @Provides @PerActivity
    public ICommentsRepository provideCommentsRepository(final ICommentsService commentsService,
                                                         final AbstractDataMapper<CommentsStatisticsBySocialMediaDTO,
                                                                 CommentsStatisticsBySocialMediaEntity> commentsStatisticsDataMapper,
                                                         final AbstractDataMapper<SocialMediaLikesStatisticsDTO, SocialMediaLikesStatisticsEntity>
                                                                     socialMediaLikesStatisticsDataMapper,
                                                         final AbstractDataMapper<CommentDTO, CommentEntity> commentEntityDataMapper) {
        return new CommentsRepositoryImpl(commentsService, commentsStatisticsDataMapper,
                socialMediaLikesStatisticsDataMapper, commentEntityDataMapper);
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

    /**
     * Provide Get Social Media Likes Statistics Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param commentsRepository
     * @return
     */
    @Provides @PerActivity
    public GetSocialMediaLikesStatisticsInteract provideGetSocialMediaLikesStatisticsInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final ICommentsRepository commentsRepository
    ){
        return new GetSocialMediaLikesStatisticsInteract(threadExecutor, postExecutionThread, commentsRepository);
    }

    /**
     * Provide Get Comments Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param commentsRepository
     * @return
     */
    @Provides @PerActivity
    public GetCommentsInteract provideGetCommentsInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final ICommentsRepository commentsRepository
    ){
        return new GetCommentsInteract(threadExecutor, postExecutionThread, commentsRepository);
    }

}
