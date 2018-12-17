package sanchez.sanchez.sergio.domain.interactor.calls;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;
import sanchez.sanchez.sergio.domain.repository.ICallsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Call Details Interact
 */
public final class GetCallDetailsInteract extends
        UseCase<List<CallDetailEntity>, GetCallDetailsInteract.Params> {

    /**
     * Calls Repository
     */
    private final ICallsRepository callsRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param callsRepository
     */
    public GetCallDetailsInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final ICallsRepository callsRepository) {
        super(threadExecutor, postExecutionThread);
        this.callsRepository = callsRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<List<CallDetailEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getTerminal(), "Params can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(), "Terminal can not be empty");

        return callsRepository.getCallList(params.getKid(), params.getTerminal());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Kid
         */
        private final String kid;

        /**
         * Terminal
         */
        private final String terminal;

        /**
         *
         * @param kid
         * @param terminal
         */
        private Params(final String kid, final String terminal) {
            this.kid = kid;
            this.terminal = terminal;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @return
         */
        public static Params create(final String kid, final String terminal) {
            return new Params(kid, terminal);
        }

        /**
         * To String
         * @return
         */
        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    '}';
        }
    }

    /**
     * Get Call Details List Api Errors
     */
    public enum GetCallDetailsListApiErrors
            implements ISupportVisitable<GetCallDetailsListApiErrors.IGetCallDetailsListApiErrorsVisitor> {

        /**
         * No Call Details Found
         */
        NO_CALL_DETAILS_FOUND(){
            @Override
            public <E> void accept(final IGetCallDetailsListApiErrorsVisitor visitor, E data) {
                visitor.visitNoCallsFound(visitor);
            }
        };

        /**
         * Get Call Details List Api Errors Visitor
         */
        public interface IGetCallDetailsListApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Calls Found
             * @param apiErrorsVisitor
             */
            void visitNoCallsFound(final IGetCallDetailsListApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
