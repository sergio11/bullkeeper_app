package sanchez.sanchez.sergio.domain.interactor.sms;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SmsEntity;
import sanchez.sanchez.sergio.domain.repository.ISmsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Sms List Interact
 */
public final class GetSmsListInteract extends UseCase<List<SmsEntity>, GetSmsListInteract.Params> {

    /**
     * Sms Repository
     */
    private final ISmsRepository smsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetSmsListInteract(final IThreadExecutor threadExecutor,
                              final IPostExecutionThread postExecutionThread,
                              final ISmsRepository smsRepository) {
        super(threadExecutor, postExecutionThread);
        this.smsRepository = smsRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    protected Observable<List<SmsEntity>> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid id can not be null");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(),
                "Terminal can not be null");

        return smsRepository.getSmsList(params.getKid(), params.getTerminal());
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
        public static Params create(final String kid, final String terminal){
            return new Params(kid, terminal);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    '}';
        }
    }

    /**
     * Get Sms List Api Errors
     */
    public enum GetSmsListApiErrors
            implements ISupportVisitable<GetSmsListApiErrors.IGetSmsListApiErrorsVisitor> {

        /**
         * No SMS Found
         */
        NO_SMS_FOUND(){
            @Override
            public <E> void accept(final IGetSmsListApiErrorsVisitor visitor, E data) {
                visitor.visitNoSmsFound(visitor);
            }
        };

        /**
         * Get SMS List Api Errors Visitor
         */
        public interface IGetSmsListApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No App Rules Found
             * @param apiErrorsVisitor
             */
            void visitNoSmsFound(final IGetSmsListApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
