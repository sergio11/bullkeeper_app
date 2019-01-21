package sanchez.sanchez.sergio.domain.interactor.funtime;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;
import sanchez.sanchez.sergio.domain.repository.IFunTimeRepository;

/**
 * Save Fun Time Day Scheduled Interact
 */
public final class SaveFunTimeDayScheduledInteract
        extends UseCase<DayScheduledEntity, SaveFunTimeDayScheduledInteract.Params> {

    /**
     * Fun Time Repository
     */
    private final IFunTimeRepository funTimeRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param funTimeRepository
     */
    public SaveFunTimeDayScheduledInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IFunTimeRepository funTimeRepository) {
        super(threadExecutor, postExecutionThread);
        this.funTimeRepository = funTimeRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    protected Observable<DayScheduledEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(),
                "Kid can not be empty");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(),
                "Terminal can not be empty");
        Preconditions.checkNotNull(params.getDay(), "Day can not be null");
        Preconditions.checkState(!params.getDay().isEmpty(),
                "Day can not be empty");
        Preconditions.checkNotNull(params.getEnabled(), "Enabled can not be null");
        Preconditions.checkNotNull(params.getTotalHours(), "Total Hours can not be null");

        // Save Fun Time Scheduled
        return funTimeRepository
                .saveFunTimeDayScheduled(
                        params.getKid(),
                        params.getTerminal(),
                        params.getDay(),
                        params.getEnabled(),
                        params.getTotalHours());
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
         * Day
         */
        private String day;

        /**
         * Enabled
         */
        private Boolean enabled;

        /**
         * Total Hours
         */
        private Integer totalHours;

        /**
         *
         * @param kid
         * @param terminal
         * @param day
         * @param enabled
         * @param totalHours
         */
        private Params(
                final String kid,
                final String terminal,
                final String day,
                final Boolean enabled,
                final Integer totalHours) {
            this.kid = kid;
            this.terminal = terminal;
            this.day = day;
            this.enabled = enabled;
            this.totalHours = totalHours;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Integer getTotalHours() {
            return totalHours;
        }

        public void setTotalHours(Integer totalHours) {
            this.totalHours = totalHours;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param day
         * @param enabled
         * @param totalHours
         * @return
         */
        public static Params create(
                final String kid,
                final String terminal,
                final String day,
                final Boolean enabled,
                final Integer totalHours) {
            return new Params(kid, terminal, day, enabled, totalHours);
        }
    }

}
