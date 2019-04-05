package sanchez.sanchez.sergio.bullkeeper.ui.fragment.commentssettings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.view.View;
import android.widget.Button;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPreferenceFragment;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.commentssettings.ICommentsSettingsActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.AdultLevelEnum;
import sanchez.sanchez.sergio.domain.models.BullyingLevelEnum;
import sanchez.sanchez.sergio.domain.models.DrugsLevelEnum;
import sanchez.sanchez.sergio.domain.models.ViolenceLevelEnum;
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

    // Violence Dimension Level
    @State
    protected String violenceDimensionLevel;

    // Drugs Dimension Level
    @State
    protected String drugsDimensionLevel;

    // Sex Dimension Level
    @State
    protected String sexDimensionLevel;

    // Bullying Dimension Level
    @State
    protected String bullyingDimensionLevel;

    // Sentiment Level
    @State
    protected String sentimentLevel;

    @BindView(R.id.savePreferences)
    protected Button savePreferencesButton;


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


        if(enableDimensionsFilter) {
            showSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION);
        } else {
            hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION);
        }

        // Violence Dimension List Preference
        final ListPreference violenceCommentDimensionListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_VIOLENCE_COMMENT_DIMENSION_LEVEL);
        violenceCommentDimensionListPreference.setOnPreferenceChangeListener(this);
        violenceCommentDimensionListPreference.setVisible(enableDimensionsFilter);

        if(enableAllCommentsDimension)
            violenceCommentDimensionListPreference.setValue(ViolenceLevelEnum.POSITIVE.name());

        violenceDimensionLevel = violenceCommentDimensionListPreference.getValue();

        // Drugs Dimension List Preference
        final ListPreference drugsCommentDimensionListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_DRUGS_COMMENTS_DIMENSION_LEVEL);
        drugsCommentDimensionListPreference.setOnPreferenceChangeListener(this);
        drugsCommentDimensionListPreference.setVisible(enableDimensionsFilter);
        if(enableAllCommentsDimension)
            drugsCommentDimensionListPreference.setValue(DrugsLevelEnum.POSITIVE.name());

        drugsDimensionLevel = drugsCommentDimensionListPreference.getValue();

        // Sex Dimension List Preference
        final ListPreference sexCommentDimensionListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_SEX_COMMENTS_DIMENSION);
        sexCommentDimensionListPreference.setOnPreferenceChangeListener(this);
        sexCommentDimensionListPreference.setVisible(enableDimensionsFilter);
        if(enableAllCommentsDimension)
            sexCommentDimensionListPreference.setValue(AdultLevelEnum.POSITIVE.name());

        sexDimensionLevel = sexCommentDimensionListPreference.getValue();

        // Bullying Dimension List Preference
        final ListPreference bullyingCommentDimensionListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_BULLYING_COMMENTS_DIMENSION);
        bullyingCommentDimensionListPreference.setOnPreferenceChangeListener(this);
        bullyingCommentDimensionListPreference.setVisible(enableDimensionsFilter);
        if(enableAllCommentsDimension)
            bullyingCommentDimensionListPreference.setValue(BullyingLevelEnum.POSITIVE.name());

        bullyingDimensionLevel = bullyingCommentDimensionListPreference.getValue();


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
                    showListPreference(IPreferenceRepository.PREF_VIOLENCE_COMMENT_DIMENSION_LEVEL);
                    showListPreference(IPreferenceRepository.PREF_DRUGS_COMMENTS_DIMENSION_LEVEL);
                    showListPreference(IPreferenceRepository.PREF_SEX_COMMENTS_DIMENSION);
                    showListPreference(IPreferenceRepository.PREF_BULLYING_COMMENTS_DIMENSION);
                } else {
                    hideSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION);
                    toggleSwitchPreferenceCompat(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION, false);
                    setSwitchPreferenceCompatChecked(IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION, false);

                    hideListPreference(IPreferenceRepository.PREF_VIOLENCE_COMMENT_DIMENSION_LEVEL);
                    setListPreferenceValue(IPreferenceRepository.PREF_VIOLENCE_COMMENT_DIMENSION_LEVEL, ViolenceLevelEnum.UNKNOWN.name());

                    hideListPreference(IPreferenceRepository.PREF_DRUGS_COMMENTS_DIMENSION_LEVEL);
                    setListPreferenceValue(IPreferenceRepository.PREF_DRUGS_COMMENTS_DIMENSION_LEVEL, DrugsLevelEnum.UNKNOWN.name());

                    hideListPreference(IPreferenceRepository.PREF_SEX_COMMENTS_DIMENSION);
                    setListPreferenceValue(IPreferenceRepository.PREF_SEX_COMMENTS_DIMENSION, AdultLevelEnum.UNKNOWN.name());

                    hideListPreference(IPreferenceRepository.PREF_BULLYING_COMMENTS_DIMENSION);
                    setListPreferenceValue(IPreferenceRepository.PREF_BULLYING_COMMENTS_DIMENSION, BullyingLevelEnum.UNKNOWN.name());

                }

                break;

            case IPreferenceRepository.PREF_ENABLE_ALL_COMMENTS_DIMENSION:

                final boolean enableAllDimension = (boolean)newValue;

                setListPreferenceValue(IPreferenceRepository.PREF_VIOLENCE_COMMENT_DIMENSION_LEVEL,
                        enableAllDimension ? ViolenceLevelEnum.POSITIVE.name() : ViolenceLevelEnum.UNKNOWN.name());
                setListPreferenceValue(IPreferenceRepository.PREF_DRUGS_COMMENTS_DIMENSION_LEVEL,
                        enableAllDimension ? DrugsLevelEnum.POSITIVE.name(): DrugsLevelEnum.UNKNOWN.name());
                setListPreferenceValue(IPreferenceRepository.PREF_SEX_COMMENTS_DIMENSION,
                        enableAllDimension ? AdultLevelEnum.POSITIVE.name(): AdultLevelEnum.UNKNOWN.name());
                setListPreferenceValue(IPreferenceRepository.PREF_BULLYING_COMMENTS_DIMENSION,
                        enableAllDimension ? BullyingLevelEnum.POSITIVE.name(): BullyingLevelEnum.UNKNOWN.name());

                break;
        }

        return true;
    }

    /**
     * On Save Preferences
     */
    @OnClick(R.id.savePreferences)
    protected void onSavePreferences(){

        uiUtils.startBounceAnimationForView(savePreferencesButton);

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

        if(!hasListPreferenceThisValue(
                IPreferenceRepository.PREF_VIOLENCE_COMMENT_DIMENSION_LEVEL, violenceDimensionLevel))
            return true;

        if(!hasListPreferenceThisValue(
                IPreferenceRepository.PREF_DRUGS_COMMENTS_DIMENSION_LEVEL, drugsDimensionLevel))
            return true;

        if(!hasListPreferenceThisValue(
                IPreferenceRepository.PREF_SEX_COMMENTS_DIMENSION, sexDimensionLevel))
            return true;

        if(!hasListPreferenceThisValue(
                IPreferenceRepository.PREF_BULLYING_COMMENTS_DIMENSION, bullyingDimensionLevel))
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
        activityHandler.closeActivity();
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
        preferencesRepositoryImpl.setViolenceDimensionLevel(violenceDimensionLevel);
        preferencesRepositoryImpl.setDrugsDimensionLevel(drugsDimensionLevel);
        preferencesRepositoryImpl.setSexDimensionLevel(sexDimensionLevel);
        preferencesRepositoryImpl.setBullyingDimensionLevel(bullyingDimensionLevel);
        preferencesRepositoryImpl.setCommentsSentimentLevel(sentimentLevel);

    }
}
