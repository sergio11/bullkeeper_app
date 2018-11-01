package sanchez.sanchez.sergio.domain.interactor.apprules;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;
import sanchez.sanchez.sergio.domain.repository.IAppRulesRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get App Rules Interact
 */
public final class GetAppRulesInteract extends UseCase<List<AppInstalledEntity>, GetAppRulesInteract.Params> {

    /**
     * App Rules Repository
     */
    private final IAppRulesRepository appRulesRepository;

    /**
     * Abstract class for a Use Case
     * @param threadExecutor
     * @param postExecutionThread
     * @param appRulesRepository
     */
    public GetAppRulesInteract(final IThreadExecutor threadExecutor,
                               final IPostExecutionThread postExecutionThread,
                               final IAppRulesRepository appRulesRepository) {
        super(threadExecutor, postExecutionThread);
        this.appRulesRepository = appRulesRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<List<AppInstalledEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");

        final List<AppInstalledEntity> appInstalledEntities = Arrays.asList(
                new AppInstalledEntity("12345","Package Name", 12131313131L,
                        12131313131L, "1.4", "124", "App Name", AppRuleEnum.NEVER_ALLOWED),
                new AppInstalledEntity("12346","Package Name", 12131313131L,
                        12131313132L, "1.4", "124", "App Name", AppRuleEnum.ALWAYS_ALLOWED),
                new AppInstalledEntity("12347","Package Name", 12131313131L,
                        12131313133L, "1.4", "124", "App Name", AppRuleEnum.PER_SCHEDULER)
        );

        return Observable.just(appInstalledEntities);
    }

    /**
     * Params
     */
    public static class Params {

        private final String childId;
        private final String deviceId;

        private Params(final String childId, final String deviceId) {
            this.childId = childId;
            this.deviceId = deviceId;
        }

        public String getChildId() {
            return childId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public static Params create(final String childId, final String deviceId) {
            Preconditions.checkNotNull(childId, "Child Id can not be null");
            Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
            Preconditions.checkNotNull(deviceId, "Device Id can not be null");
            Preconditions.checkState(!deviceId.isEmpty(), "Device Id can not empty");

            return new Params(childId, deviceId);
        }
    }

    /**
     * Get App Rules Api Errors
     */
    public enum GetAppRulesApiErrors
            implements ISupportVisitable<GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor> {

        /**
         * No App Rules
         */
        NO_APP_RULES_FOUND(){
            @Override
            public <E> void accept(final IGetAppRulesApiErrorsVisitor visitor, E data) {
                visitor.visitNoAppRulesFound(visitor);
            }
        };

        /**
         * Get App Rules Api Errors Visitor
         */
        public interface IGetAppRulesApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No App Rules Found
             * @param apiErrorsVisitor
             */
            void visitNoAppRulesFound(final IGetAppRulesApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
