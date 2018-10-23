package sanchez.sanchez.sergio.domain.interactor.comments;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.repository.ICommentsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Comments Statistics By Social Media Interact
 */
public final class GetCommentsStatisticsBySocialMediaInteract extends UseCase<CommentsStatisticsBySocialMediaEntity, GetCommentsStatisticsBySocialMediaInteract.Params> {

    /**
     * Comments Repository
     */
    private final ICommentsRepository commentsRepository;

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param commentsRepository
     */
    public GetCommentsStatisticsBySocialMediaInteract(final IThreadExecutor threadExecutor,
                                                      final IPostExecutionThread postExecutionThread,
                                                      final ICommentsRepository commentsRepository) {
        super(threadExecutor, postExecutionThread);
        this.commentsRepository = commentsRepository;
    }

    /**
     * Build Use Case Observable
     * @param params
     * @return
     */
    @Override
    protected Observable<CommentsStatisticsBySocialMediaEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkState(!params.getSonId().isEmpty(), "Son Id can not be empty");

        return commentsRepository.getCommentsStatisticsBySocialMedia(params.getSonId(), params.getDaysAgo());
    }

    /**
     * Params
     */
    public static class Params {

        private final String sonId;
        private final int daysAgo;

        /**
         *
         * @param sonId
         * @param daysAgo
         */
        private Params(final String sonId, final int daysAgo) {
            this.sonId = sonId;
            this.daysAgo = daysAgo;
        }

        public String getSonId() {
            return sonId;
        }

        public int getDaysAgo() {
            return daysAgo;
        }

        /**
         * Create
         * @param sonId
         * @param daysAgo
         * @return
         */
        public static Params create(final String sonId, final int daysAgo) {
            return new Params(sonId, daysAgo);
        }
    }

    /**
     * Get Comments Statistics By Social Media API Errors
     */
    public enum GetCommentsStatisticsBySocialMediaApiErrors
            implements ISupportVisitable<GetCommentsStatisticsBySocialMediaApiErrors.IGetCommentsStatisticsBySocialMediaApiErrorsVisitor> {

        /**
         * No Comments Extracted
         */
        NO_COMMENTS_EXTRACTED(){
            @Override
            public <E> void accept(IGetCommentsStatisticsBySocialMediaApiErrorsVisitor visitor, E data) {
                visitor.visitNoCommentsExtractedError(visitor);
            }
        };

        /**
         * Get Comments Statistics By Social Media Api Errors
         */
        public interface IGetCommentsStatisticsBySocialMediaApiErrorsVisitor extends ISupportVisitor {


            /**
             * Visit No Comments Extracted Error
             * @param apiErrorsVisitor
             */
            void visitNoCommentsExtractedError(final IGetCommentsStatisticsBySocialMediaApiErrorsVisitor apiErrorsVisitor);
        }
    }

}
