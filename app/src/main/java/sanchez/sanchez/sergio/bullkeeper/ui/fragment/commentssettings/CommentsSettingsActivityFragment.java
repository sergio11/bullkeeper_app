package sanchez.sanchez.sergio.bullkeeper.ui.fragment.commentssettings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.view.View;
import java.util.Date;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPreferenceFragment;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.commentssettings.ICommentsSettingsActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import timber.log.Timber;

/**
 * Comments Settings Activity Fragment
 */
public class CommentsSettingsActivityFragment extends
        SupportPreferenceFragment<ICommentsSettingsActivityHandler> {

    /**
     * Settings Component
     */
    protected SettingsComponent settingsComponent;

    /**
     * State
     */

    // Age Of Comments
    @State
    protected String ageOfComments;

    // Enable All Social Media Categories
    @State
    protected boolean enableAllSocialMediaCategories;

    // Enable Facebook Social Media
    @State
    protected boolean enableFacebookSocialMedia;

    // Enable Instagram Social Media
    @State
    protected boolean enableInstagramSocialMedia;

    // Enable Youtube Social Media
    @State
    protected boolean enableYoutubeSocialMedia;

    // Enable Dimensions Filter
    @State
    protected boolean enableDimensionsFilter;

    // Enable All Comments Dimension
    @State
    protected boolean enableAllCommentsDimension;

    // Enable Violence Comment Dimension
    @State
    protected boolean enableViolenceCommentDimension;

    // Enable Drugs Comment Dimmension
    @State
    protected boolean enableDrugsCommentDimension;

    // Enable Sex Comment Dimension
    @State
    protected boolean enableSexCommentDimension;

    // Enable Bullying Comment Dimension
    @State
    protected boolean enableBullyingCommentDimension;

    // Sentiment Level
    @State
    protected String sentimentLevel;

    /**
     * Get Preferences Layout
     * @return
     */
    @Override
    protected int getPreferencesLayout() {
        return R.xml.comments_preferences;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {

        settingsComponent = SettingsComponent.class
                .cast(((HasComponent<SettingsComponent>) getActivity())
                        .getComponent());

        settingsComponent.inject(this);
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Age of Comments
        final ListPreference ageOfCommentsListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_AGE_OF_COMMENTS);
        ageOfCommentsListPreference.setOnPreferenceChangeListener(this);
        // Get Current Value
        ageOfComments = ageOfCommentsListPreference.getValue();

        // Enable All Social Media Categories
        final SwitchPreferenceCompat enableAllSocialMediaCategoriesPreference = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_ALL_SOCIAL_MEDIAS_CATEGORIES);
        enableAllSocialMediaCategoriesPreference.setOnPreferenceChangeListener(this);

        // Get Current Value
        enableAllSocialMediaCategories = enableAllSocialMediaCategoriesPreference.isChecked();

        // Facebook Social Media
        enableFacebookSocialMedia = configSwitchPreferenceCompatValue(IPreferenceRepository.PREF_ENABLE_FACEBOOK_SOCIAL_MEDIA, !enableAllSocialMediaCategoriesPreference.isChecked());

        // Instagram Social Media
        enableInstagramSocialMedia = configSwitchPreferenceCompatValue(IPreferenceRepository.PREF_ENABLE_INSTAGRAM_SOCIAL_MEDIA,
                !enableAllSocialMediaCategoriesPreference.isChecked());

        // Youtube Social Media
        enableYoutubeSocialMedia = configSwitchPreferenceCompatValue(IPreferenceRepository.PREF_ENABLE_YOUTUBE_SOCIAL_MEDIA,
                !enableAllSocialMediaCategoriesPreference.isChecked());

        // Dimension Filter
        enableDimensionsFilter = configSwitchPreferenceCompatValue(IPreferenceRepository.PREF_ENABLE_DIMENSIONS_FILTER, true);

        // Enable All Comments Dimension
        enableAllCommentsDimension = configSwitchPreferenceCompatValue(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION,
                true);

        // Enable Violence Comment Dimension
        enableViolenceCommentDimension = configSwitchPreferenceCompatValue(IPreferenceRepository.PREF_ENABLE_VIOLENCE_COMMENT_DIMENSION,
                !enableAllCommentsDimension);

        // Enable Drugs Comment Dimension
        enableDrugsCommentDimension = configSwitchPreferenceCompatValue(IPreferenceRepository.PREF_ENABLE_DRUGS_COMMENTS_DIMENSION,
                !enableAllCommentsDimension);

       // Enable Adult Comment Dimension
        enableSexCommentDimension = configSwitchPreferenceCompatValue(IPreferenceRepository.PREF_ENABLE_SEX_COMMENTS_DIMENSION,
                !enableAllCommentsDimension);

        // Enable Bullying Comment Dimension
        enableBullyingCommentDimension = configSwitchPreferenceCompatValue(IPreferenceRepository.PREF_ENABLE_BULLYING_COMMENTS_DIMENSION,
                !enableAllCommentsDimension);

        if(enableDimensionsFilter) {
            showSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION);
            showSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_VIOLENCE_COMMENT_DIMENSION);
            showSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_DRUGS_COMMENTS_DIMENSION);
            showSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_SEX_COMMENTS_DIMENSION);
            showSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_BULLYING_COMMENTS_DIMENSION);
        } else {
            hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION);
            hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_VIOLENCE_COMMENT_DIMENSION);
            hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_DRUGS_COMMENTS_DIMENSION);
            hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_SEX_COMMENTS_DIMENSION);
            hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_BULLYING_COMMENTS_DIMENSION);
        }

        final ListPreference commentsSentimentLevelListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_COMMENTS_SENTIMENT_LEVEL);
        commentsSentimentLevelListPreference.setOnPreferenceChangeListener(this);

        sentimentLevel = commentsSentimentLevelListPreference.getValue();
    }


    /**
     * On Preference Change
     * @param preference
     * @param newValue
     * @return
     */
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        switch (preference.getKey()) {

            case IPreferenceRepository.PREF_ENABLE_ALL_SOCIAL_MEDIAS_CATEGORIES:

                final boolean enableAllSocialMediaCategories = (boolean)newValue;


                toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_FACEBOOK_SOCIAL_MEDIA,
                        enableAllSocialMediaCategories);

                toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_INSTAGRAM_SOCIAL_MEDIA,
                        enableAllSocialMediaCategories);

                toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_YOUTUBE_SOCIAL_MEDIA,
                        enableAllSocialMediaCategories);

                break;

            case IPreferenceRepository.PREF_ENABLE_DIMENSIONS_FILTER:

                final boolean enableDimensionFilter = (boolean)newValue;

                if(enableDimensionFilter) {
                    showSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION);
                    showSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_VIOLENCE_COMMENT_DIMENSION);
                    showSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_DRUGS_COMMENTS_DIMENSION);
                    showSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_SEX_COMMENTS_DIMENSION);
                    showSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_BULLYING_COMMENTS_DIMENSION);
                } else {
                    hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION);
                    toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION, false);
                    setSwitchPreferenceCompatChecked(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION, false);

                    hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_VIOLENCE_COMMENT_DIMENSION);
                    toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_VIOLENCE_COMMENT_DIMENSION, false);
                    setSwitchPreferenceCompatChecked(IPreferenceRepository.PREF_ENABLE_VIOLENCE_COMMENT_DIMENSION, false);

                    hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_DRUGS_COMMENTS_DIMENSION);
                    toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_DRUGS_COMMENTS_DIMENSION, false);
                    setSwitchPreferenceCompatChecked(IPreferenceRepository.PREF_ENABLE_DRUGS_COMMENTS_DIMENSION, false);

                    hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_SEX_COMMENTS_DIMENSION);
                    toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_SEX_COMMENTS_DIMENSION, false);
                    setSwitchPreferenceCompatChecked(IPreferenceRepository.PREF_ENABLE_SEX_COMMENTS_DIMENSION, false);

                    hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_BULLYING_COMMENTS_DIMENSION);
                    toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_BULLYING_COMMENTS_DIMENSION, false);
                    setSwitchPreferenceCompatChecked(IPreferenceRepository.PREF_ENABLE_BULLYING_COMMENTS_DIMENSION, false);
                }

                break;

            case IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION:

                final boolean enableAllDimension = (boolean)newValue;

                toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_VIOLENCE_COMMENT_DIMENSION,
                        enableAllDimension);

                toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_DRUGS_COMMENTS_DIMENSION,
                        enableAllDimension);

                toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_SEX_COMMENTS_DIMENSION,
                        enableAllDimension);

                toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_BULLYING_COMMENTS_DIMENSION,
                        enableAllDimension);

                break;
        }

        return true;
    }

    /**
     * On Save Preferences
     */
    @OnClick(R.id.savePreferences)
    protected void onSavePreferences(){

        preferencesRepositoryImpl.setPreferencesUpdateAt(new Date().getTime());


        activityHandler.showNoticeDialog(R.string.preferences_saved_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * Has Pending Changes
     * @return
     */
    @Override
    public Boolean hasPendingChanges() {

        // Check Age of comments
        if(!hasListPreferenceThisValue(IPreferenceRepository.PREF_AGE_OF_COMMENTS, ageOfComments))
            return true;

        if(!switchPreferenceCompatIsItInThisState(
                IPreferenceRepository.PREF_ENABLE_ALL_SOCIAL_MEDIAS_CATEGORIES, enableAllSocialMediaCategories))
            return true;

        if(!switchPreferenceCompatIsItInThisState(
                IPreferenceRepository.PREF_ENABLE_FACEBOOK_SOCIAL_MEDIA, enableFacebookSocialMedia))
            return true;

        if(!switchPreferenceCompatIsItInThisState(
                IPreferenceRepository.PREF_ENABLE_INSTAGRAM_SOCIAL_MEDIA, enableInstagramSocialMedia))
            return true;

        if(!switchPreferenceCompatIsItInThisState(
                IPreferenceRepository.PREF_ENABLE_YOUTUBE_SOCIAL_MEDIA, enableYoutubeSocialMedia))
            return true;

        if(!switchPreferenceCompatIsItInThisState(
                IPreferenceRepository.PREF_ENABLE_DIMENSIONS_FILTER, enableDimensionsFilter))
            return true;

        if(!switchPreferenceCompatIsItInThisState(
                IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION, enableAllCommentsDimension))
            return true;

        if(!switchPreferenceCompatIsItInThisState(
                IPreferenceRepository.PREF_ENABLE_VIOLENCE_COMMENT_DIMENSION, enableViolenceCommentDimension))
            return true;

        if(!switchPreferenceCompatIsItInThisState(
                IPreferenceRepository.PREF_ENABLE_DRUGS_COMMENTS_DIMENSION, enableDrugsCommentDimension))
            return true;

        if(!switchPreferenceCompatIsItInThisState(
                IPreferenceRepository.PREF_ENABLE_SEX_COMMENTS_DIMENSION, enableSexCommentDimension))
            return true;

        if(!switchPreferenceCompatIsItInThisState(
                IPreferenceRepository.PREF_ENABLE_BULLYING_COMMENTS_DIMENSION, enableBullyingCommentDimension))
            return true;

        // Check Age of comments
        if(!hasListPreferenceThisValue(IPreferenceRepository.PREF_COMMENTS_SENTIMENT_LEVEL, sentimentLevel))
            return true;

        return false;
    }

    /**
     * On Saved Pending Changes
     */
    @Override
    public void onSavedPendingChanges() {
        Timber.d("On Saved Pending Changes");
        preferencesRepositoryImpl.setPreferencesUpdateAt(new Date().getTime());
    }

    /**
     * On Discard Pending Changes
     */
    @Override
    public void onDiscardPendingChanges() {
        Timber.d("On Discard Pending Changes");

        preferencesRepositoryImpl.setPrefAgeOfComments(ageOfComments);
        preferencesRepositoryImpl.setAllSocialMediaCategoriesEnabled(enableAllSocialMediaCategories);
        preferencesRepositoryImpl.setFacebookSocialMediaEnabled(enableFacebookSocialMedia);
        preferencesRepositoryImpl.setInstagramSocialMediaEnabled(enableInstagramSocialMedia);
        preferencesRepositoryImpl.setYoutubeSocialMediaEnabled(enableYoutubeSocialMedia);
        preferencesRepositoryImpl.setDimensionFilter(enableDimensionsFilter);
        preferencesRepositoryImpl.setAllCommentsDimensionEnabled(enableAllCommentsDimension);
        preferencesRepositoryImpl.setViolenceDimensionEnabled(enableViolenceCommentDimension);
        preferencesRepositoryImpl.setDrugsDimensionEnabled(enableDrugsCommentDimension);
        preferencesRepositoryImpl.setSexDimensionEnabled(enableSexCommentDimension);
        preferencesRepositoryImpl.setBullyingDimensionEnabled(enableBullyingCommentDimension);
        preferencesRepositoryImpl.setCommentsSentimentLevel(sentimentLevel);

    }
}
