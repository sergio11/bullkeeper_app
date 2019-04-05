package sanchez.sanchez.sergio.bullkeeper.ui.activity.settings;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.UserPreferenceEntity;

/**
 * User Settings View
 */
public interface IUserSettingsView extends ISupportView {

    /**
     * on User Preferences Loaded
     * @param userPreferenceEntity
     */
    void onUserPreferencesLoaded(final UserPreferenceEntity userPreferenceEntity);

    /**
     * On User Preferences Save
     * @param userPreferenceEntity
     */
    void onUserPreferencesSaved(final UserPreferenceEntity userPreferenceEntity);

    /**
     * on Save Preferences Error
     */
    void onSavePreferencesError();


}
