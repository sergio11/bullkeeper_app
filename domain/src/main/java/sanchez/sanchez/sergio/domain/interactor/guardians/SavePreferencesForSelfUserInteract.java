package sanchez.sanchez.sergio.domain.interactor.guardians;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.UserPreferenceEntity;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;

/**
 * Save Preferences For Self User Interact
 **/
public final class SavePreferencesForSelfUserInteract extends UseCase<UserPreferenceEntity, SavePreferencesForSelfUserInteract.Params> {

    /**
     * Guardian Repository
     */
    private final IGuardianRepository guardianRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     */
    public SavePreferencesForSelfUserInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IGuardianRepository guardianRepository) {
        super(threadExecutor, postExecutionThread);
        this.guardianRepository = guardianRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<UserPreferenceEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        return guardianRepository.saveUserPreference(
                params.getPushNotificationsEnabled(), params.getRemoveAlertsEvery()
        );
    }


    /**
     * Params
     */
    public static class Params{

        private Boolean pushNotificationsEnabled;
        private String removeAlertsEvery;

        private Params(final Boolean pushNotificationsEnabled, final String removeAlertsEvery) {
            this.pushNotificationsEnabled = pushNotificationsEnabled;
            this.removeAlertsEvery = removeAlertsEvery;
        }

        public Boolean getPushNotificationsEnabled() {
            return pushNotificationsEnabled;
        }

        public void setPushNotificationsEnabled(Boolean pushNotificationsEnabled) {
            this.pushNotificationsEnabled = pushNotificationsEnabled;
        }

        public String getRemoveAlertsEvery() {
            return removeAlertsEvery;
        }

        public void setRemoveAlertsEvery(String removeAlertsEvery) {
            this.removeAlertsEvery = removeAlertsEvery;
        }

        /**
         * Create
         * @param pushNotificationsEnabled
         * @param removeAlertsEvery
         * @return
         */
        public static Params create(final Boolean pushNotificationsEnabled, final String removeAlertsEvery){
            return new Params(pushNotificationsEnabled, removeAlertsEvery);
        }
    }
}
