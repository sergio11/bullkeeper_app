package sanchez.sanchez.sergio.domain.interactor.alerts;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Self Alerts By level Interact
 */
public final class GetSelfAlertsByLevelInteract extends UseCase<List<AlertEntity>,
        GetSelfAlertsByLevelInteract.Params> {

    /**
     * Alerts Repository
     */
    private final IAlertsRepository alertsRepository;

    /**
     * Preference Repository
     */
    private final IPreferenceRepository preferenceRepository;

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param alertsRepository
     * @param preferenceRepository
     */
    public GetSelfAlertsByLevelInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                        final IAlertsRepository alertsRepository, final IPreferenceRepository preferenceRepository) {
        super(threadExecutor, postExecutionThread);
        this.alertsRepository = alertsRepository;
        this.preferenceRepository = preferenceRepository;
    }

    /**
     * Build Use Case Observable
     * @param params
     * @return
     */
    @Override
    protected Observable<List<AlertEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        return alertsRepository.getSelfAlertsByLevel(preferenceRepository.getFilterAlertsCount(),
                preferenceRepository.getFilterAgeOfAlerts(), params.getAlertLevel());
    }


    /**
     * Get Self Alerts API Error
     */
    public enum GetSelfAlertsByLevelApiErrors implements ISupportVisitable<GetSelfAlertsByLevelApiErrors.IGetSelfAlertsByLevelApiErrorVisitor> {

        /**
         * No Alerts Found
         */
        NO_ALERTS_FOUND() {
            @Override
            public <E> void accept(IGetSelfAlertsByLevelApiErrorVisitor visitor, E data) {
                visitor.visitNoAlertsFounded(this);
            }
        };

        /**
         * Get Self Alerts By Level Api Error Visitor
         */
        public interface IGetSelfAlertsByLevelApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit No Alerts Founded
             *
             * @param error
             */
            void visitNoAlertsFounded(final GetSelfAlertsByLevelApiErrors error);

        }
    }

    /**
     * Params
     */
    public static class Params {

        private final AlertLevelEnum alertLevel;

        public Params(AlertLevelEnum alertLevel) {
            this.alertLevel = alertLevel;
        }

        public AlertLevelEnum getAlertLevel() {
            return alertLevel;
        }

        /**
         * Create
         * @param alertLevel
         * @return
         */
        public static Params create(final AlertLevelEnum alertLevel) {
            return new Params(alertLevel);
        }
    }


}
