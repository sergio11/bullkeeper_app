package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CommentDTO;
import sanchez.sanchez.sergio.data.net.services.ICommentsService;
import sanchez.sanchez.sergio.data.repository.CommentsRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.comments.GetCommentByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.comments.GetCommentsInteract;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
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
     * @param commentEntityDataMapper
     * @return
     */
    @Provides @PerActivity
    public ICommentsRepository provideCommentsRepository(final ICommentsService commentsService,
                                                         final AbstractDataMapper<CommentDTO, CommentEntity> commentEntityDataMapper) {
        return new CommentsRepositoryImpl(commentsService, commentEntityDataMapper);
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

    /**
     * Provide Get Comments By Id Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param commentsRepository
     * @return
     */
    @Provides @PerActivity
    public GetCommentByIdInteract provideGetCommentByIdInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final ICommentsRepository commentsRepository
    ){
        return new GetCommentByIdInteract(threadExecutor, postExecutionThread, commentsRepository);
    }


}
