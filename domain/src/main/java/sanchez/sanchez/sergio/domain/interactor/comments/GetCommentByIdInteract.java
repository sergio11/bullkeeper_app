package sanchez.sanchez.sergio.domain.interactor.comments;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.repository.ICommentsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Comment By Id Interact
 */
public final class GetCommentByIdInteract extends UseCase<CommentEntity, GetCommentByIdInteract.Params> {

    /**
     * Comment Repository
     */
    private final ICommentsRepository commentsRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param commentsRepository
     */
    public GetCommentByIdInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                  final ICommentsRepository commentsRepository) {
        super(threadExecutor, postExecutionThread);
        this.commentsRepository = commentsRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<CommentEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getCommentId(), "Comment Id can not be null");
        Preconditions.checkState(!params.getCommentId().isEmpty(), "Comment Id can not be empty");
        return commentsRepository.getCommentById(params.getCommentId());
    }


    /**
     * Params
     */
    public static class Params {

        private final String commentId;

        /**
         * Params
         * @param commentId
         */
        private Params(final String commentId) {
            this.commentId = commentId;
        }

        public String getCommentId() {
            return commentId;
        }

        /**
         * Create
         * @param commentId
         * @return
         */
        public static Params create(final String commentId) {
            return new Params(commentId);
        }
    }

    /**
     * Error Handling
     * =======================
     */

    /**
     * Get Comment By Id Api Error
     */
    public enum GetCommentByIdApiErrors implements ISupportVisitable<GetCommentByIdApiErrors.IGetCommentByIdApiErrorsVisitor> {

        /**
         * Comment Not Found
         */
        COMMENT_NOT_FOUND(){
            @Override
            public <E> void accept(IGetCommentByIdApiErrorsVisitor visitor, E data) {
                visitor.visitCommentNotFound(this);
            }
        };

        /**
         * Get Comment By Id Api Errors Visitor
         */
        public interface IGetCommentByIdApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit Comment Not Found
             * @param apiErrors
             */
            void visitCommentNotFound(final GetCommentByIdApiErrors apiErrors);
        }
    }
}
