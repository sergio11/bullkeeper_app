package sanchez.sanchez.sergio.domain.interactor.alerts;

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
 * Get Self Alerts Interact
 */
public final class GetSelfAlertsInteract extends UseCase<List<AlertEntity>, Void> {

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
    public GetSelfAlertsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                 final IAlertsRepository alertsRepository, final IPreferenceRepository preferenceRepository) {
        super(threadExecutor, postExecutionThread);
        this.alertsRepository = alertsRepository;
        this.preferenceRepository = preferenceRepository;
    }

    /**
     * Get Levels CSV
     * @return
     */
    private String getLevelsCsv(){

        StringBuilder stringBuilder = new StringBuilder();

        if(preferenceRepository.isFilterAlertsEnableAllCategories()) {

            stringBuilder.append(AlertLevelEnum.SUCCESS).append(",");
            stringBuilder.append(AlertLevelEnum.DANGER).append(",");
            stringBuilder.append(AlertLevelEnum.WARNING).append(",");
            stringBuilder.append(AlertLevelEnum.INFO);

        } else {

            if(preferenceRepository.isFilterAlertsEnableSuccessCategory()) {
                stringBuilder.append(AlertLevelEnum.SUCCESS).append(",");
            }

            if (preferenceRepository.isFilterAlertsEnableInformationCategory()) {
                stringBuilder.append(AlertLevelEnum.INFO).append(",");
            }

            if(preferenceRepository.isFilterAlertsEnableWarningCategory()) {
                stringBuilder.append(AlertLevelEnum.WARNING).append(",");
            }

            if(preferenceRepository.isFilterAlertsEnableDangerCategory()) {
                stringBuilder.append(AlertLevelEnum.DANGER).append(",");
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Build User Case Observable
     * @param aVoid
     * @return
     */
    @Override
    protected Observable<List<AlertEntity>> buildUseCaseObservable(Void aVoid) {
        return alertsRepository.getSelfAlerts(preferenceRepository.getFilterAlertsCount(),
                preferenceRepository.getPrefAlertsDaysAgo(), getLevelsCsv());
    }

    /**
     * Get Self Alerts API Error
     */
    public enum GetSelfAlertsApiErrors implements ISupportVisitable<GetSelfAlertsApiErrors.IGetSelfAlertsApiErrorVisitor> {

        /**
         * No Alerts Found
         */
        NO_ALERTS_FOUND() {
            @Override
            public <E> void accept(IGetSelfAlertsApiErrorVisitor visitor, E data) {
                visitor.visitNoAlertsFounded(this);
            }
        };

        /**
         * Get Self Alerts Api Error Visitor
         */
        public interface IGetSelfAlertsApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit No Alerts Founded
             *
             * @param error
             */
            void visitNoAlertsFounded(final GetSelfAlertsApiErrors error);

        }
    }


}
