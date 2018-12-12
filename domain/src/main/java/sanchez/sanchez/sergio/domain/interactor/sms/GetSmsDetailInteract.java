package sanchez.sanchez.sergio.domain.interactor.sms;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SmsEntity;
import sanchez.sanchez.sergio.domain.repository.ISmsRepository;

/**
 * Get Sms Detail Interact
 */
public final class GetSmsDetailInteract extends UseCase<SmsEntity, GetSmsDetailInteract.Params> {

    /**
     * Sms Repository
     */
    private final ISmsRepository smsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetSmsDetailInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<SmsEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid id can not be null");
        Preconditions.checkNotNull(params.getTerminal(), "Params can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(),
                "Terminal Id can not be null");
        Preconditions.checkNotNull(params.getSms(), "Sms can not be null");
        Preconditions.checkState(!params.getSms().isEmpty(),
                "sms id can not be null");

        return smsRepository.getSmsDetail(params.getKid(), params.getTerminal(), params.getSms());
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
         * Sms
         */
        private final String sms;

        /**
         * Params
         * @param kid
         * @param terminal
         * @param sms
         */
        private Params(final String kid, final String terminal, final String sms) {
            this.kid = kid;
            this.terminal = terminal;
            this.sms = sms;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public String getSms() {
            return sms;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param sms
         * @return
         */
        public static Params create(
                final String kid,
                final String terminal,
                final String sms){
            return new Params(kid, terminal, sms);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    ", sms='" + sms + '\'' +
                    '}';
        }
    }
}
