package sanchez.sanchez.sergio.bullkeeper.ui.activity.settings;

import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.guardians.GetPreferencesForSelfUserInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.SavePreferencesForSelfUserInteract;
import sanchez.sanchez.sergio.domain.models.UserPreferenceEntity;

/**
 * User Settings Activity Presenter
 */
public final class UserSettingsActivityPresenter
        extends SupportPresenter<IUserSettingsView> {

    /**
     * Get Preference For Self Interact
     */
    private final GetPreferencesForSelfUserInteract getPreferencesForSelfUserInteract;

    /**
     * Save Preference For Self User Interact
     */
    private final SavePreferencesForSelfUserInteract savePreferencesForSelfUserInteract;

    /**
     *
     * @param getPreferencesForSelfUserInteract
     * @param savePreferencesForSelfUserInteract
     */
    @Inject
    public UserSettingsActivityPresenter(GetPreferencesForSelfUserInteract getPreferencesForSelfUserInteract, SavePreferencesForSelfUserInteract savePreferencesForSelfUserInteract) {
        this.getPreferencesForSelfUserInteract = getPreferencesForSelfUserInteract;
        this.savePreferencesForSelfUserInteract = savePreferencesForSelfUserInteract;
    }

    /**
     *
     */
    @Override
    protected void onInit() {
        super.onInit();

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        getPreferencesForSelfUserInteract.execute(new GetPreferencesForSelfUserObservable(), null);

    }

    /**
     *
     * @param pushNotificationsEnabled
     * @param removeAlertsEvery
     */
    public void savePreferences(final Boolean pushNotificationsEnabled, final String removeAlertsEvery){
        Preconditions.checkNotNull(pushNotificationsEnabled, "Push Notification Enabled");
        Preconditions.checkNotNull(removeAlertsEvery, "Remove Alerts Every");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        savePreferencesForSelfUserInteract.execute(new SavePreferencesForSelfUserObservable(), SavePreferencesForSelfUserInteract
                .Params.create(pushNotificationsEnabled, removeAlertsEvery));
    }

    /**
     * Get Preferences For Self User Observable
     */
    public class GetPreferencesForSelfUserObservable extends BasicCommandCallBackWrapper<UserPreferenceEntity> {

        /**
         *
         * @param userPreferenceEntity
         */
        @Override
        protected void onSuccess(final UserPreferenceEntity userPreferenceEntity) {
            Preconditions.checkNotNull(userPreferenceEntity, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onUserPreferencesLoaded(userPreferenceEntity);
            }
        }
    }


    /**
     * Save Preferences For Self User Observable
     */
    public class SavePreferencesForSelfUserObservable extends BasicCommandCallBackWrapper<UserPreferenceEntity> {

        /**
         *
         * @param userPreferenceEntity
         */
        @Override
        protected void onSuccess(final UserPreferenceEntity userPreferenceEntity) {
            Preconditions.checkNotNull(userPreferenceEntity, "Response can not be null");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onUserPreferencesSaved(userPreferenceEntity);
            }
        }
    }
}
