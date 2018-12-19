package sanchez.sanchez.sergio.domain.interactor.calls;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;
import sanchez.sanchez.sergio.domain.repository.ICallsRepository;

/**
 * Get Call Detail Interact
 */
public final class GetCallDetailInteract extends UseCase<CallDetailEntity, GetCallDetailInteract.Params> {

    /**
     * Calls Repository
     */
    private final ICallsRepository callsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param callsRepository
     */
    public GetCallDetailInteract(final IThreadExecutor threadExecutor,
                                 final IPostExecutionThread postExecutionThread,
                                 ICallsRepository callsRepository) {
        super(threadExecutor, postExecutionThread);
        this.callsRepository = callsRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<CallDetailEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getCall(), "Call can not be null");
        Preconditions.checkState(!params.getCall().isEmpty(), "Call can not be empty");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(), "Terminal can not be empty");

        return callsRepository.getCallDetail(params.getKid(), params.getTerminal(),
                params.getCall());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Kid Id
         */
        private final String kid;

        /**
         * Terminal Id
         */
        private final String terminal;

        /**
         * Call Id
         */
        private final String call;

        /**
         *
         * @param kid
         * @param terminal
         * @param call
         */
        private Params(final String kid, final String terminal, final String call) {
            this.kid = kid;
            this.terminal = terminal;
            this.call = call;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public String getCall() {
            return call;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param call
         * @return
         */
        public static Params create(final String kid, final String terminal,
                                    final String call){
            Preconditions.checkNotNull(kid, "Kid can not be null");
            Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
            Preconditions.checkNotNull(call, "Call can not be null");
            Preconditions.checkState(!call.isEmpty(), "Call can not be empty");
            Preconditions.checkNotNull(terminal, "Terminal can not be null");
            Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
            return new Params(kid, terminal, call);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    ", call='" + call + '\'' +
                    '}';
        }
    }
}
