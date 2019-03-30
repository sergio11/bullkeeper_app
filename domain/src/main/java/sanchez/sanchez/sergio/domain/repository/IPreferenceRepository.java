package sanchez.sanchez.sergio.domain.repository;

import sanchez.sanchez.sergio.domain.utils.IAuthTokenAware;

/**
 * Preference Repository
 */
public interface IPreferenceRepository extends IAuthTokenAware {

    // Home Show Case Completed
    String PREF_HOME_SHOWCASE_COMPLETE = "home_showcase_completed";
    boolean HOME_SHOW_CASE_COMPLETED = false;

    // NUMBER OF ALERTS
    String PREF_NUMBER_OF_ALERTS = "number_of_alerts";
    int NUMBER_OF_ALERTS_DEFAULT_VALUE = 20;

    // FILTER ALERTS COUNT
    String PREF_FILTER_ALERTS_COUNT = "filter_alerts_count";
    String FILTER_ALERTS_COUNT_DEFAULT_VALUE = "20";

    // AGE OF ALERTS
    String PREF_AGE_OF_ALERTS = "age_of_alerts";
    String AGE_OF_ALERTS_DEFAULT_VALUE = "30";

    // FILTER AGE OF ALERTS
    String PREF_FILTER_AGE_OF_ALERTS = "filter_age_of_alerts";
    String FILTER_AGE_OF_ALERTS_DEFAULT_VALUE = "30";

    // REMOVE ALERTS EVERY
    String PREF_REMOVE_ALERTS_EVERY = "remove_alerts_every";
    int REMOVE_ALERTS_EVERY_DEFAULT_VALUE = 0;

    // Enable Push Notifications
    String PREF_ENABLE_PUSH_NOTIFICATIONS = "enable_push_notifications";
    boolean ENABLE_PUSH_NOTIFICATIONS_DEFAULT_VALUE = true;

    // Enable All Alert Categories
    String PREF_ENABLE_ALL_ALERT_CATEGORIES = "enable_all_alert_categories";
    boolean ENABLE_ALL_ALERT_CATEGORIES_DEFAULT_VALUE = true;

    // Enable Success alerts
    String PREF_ENABLE_SUCCESS_ALERTS = "enable_success_alerts";
    boolean ENABLE_SUCCESS_ALERTS_DEFAULT_VALUE = true;

    // Enable Information Alerts
    String PREF_ENABLE_INFORMATION_ALERTS = "enable_information_alerts";
    boolean ENABLE_INFORMATION_ALERTS_DEFAULT_VALUE = true;

    // Enable Warning Alerts
    String PREF_ENABLE_WARNING_ALERTS = "enable_warning_alerts";
    boolean ENABLE_WARNING_ALERTS_DEFAULT_VALUE = true;

    // Enable Danger Alerts
    String PREF_ENABLE_DANGER_ALERTS = "enable_danger_alerts";
    boolean ENABLE_DANGER_ALERTS_DEFAULT_VALUE = true;

    // Auth Token
    String PREF_AUTH_TOKEN = "auth_token";
    String AUTH_TOKEN_DEFAULT_VALUE = "";

    // Current User Identity
    String PREF_CURRENT_USER_IDENTITY = "identity";
    String CURRENT_USER_IDENTITY_DEFAULT_VALUE = "";

    // Count Alerts
    String PREF_COUNT_ALERTS = "count_alerts";
    String COUNT_ALERTS_DEFAULT_VALUE = "20";

    // Alerts Days Ago
    String PREF_ALERTS_DAYS_AGO = "alerts_days_ago";
    String ALERTS_DAYS_AGO_DEFAULT_VALUE = "1";

    // Alerts Levels
    String PREF_ALERTS_LEVELS = "alerts_levels";
    String ALERTS_LEVELS_DEFAULT_VALUE = "";

    // Filter alerts enable All Alert Categories
    String PREF_FILTER_ALERTS_ENABLE_ALL_CATEGORIES = "filter_alerts_enable_all_categories";
    boolean FILTER_ALERTS_ENABLE_ALL_CATEGORIES_DEFAULT_VALUE = true;

    // Filter alerts Enable Success category
    String PREF_FILTER_ALERTS_ENABLE_SUCCESS_CATEGORY = "filter_alerts_enable_success_category";
    boolean FILTER_ALERTS_ENABLE_SUCCESS_CATEGORY_DEFAULT_VALUE = true;

    // Filter alerts enable information category
    String PREF_FILTER_ALERTS_ENABLE_INFORMATION_CATEGORY = "filter_alerts_enable_information_category";
    boolean FILTER_ALERTS_ENABLE_INFORMATION_CATEGORY_DEFAULT_VALUE = true;

    // Enable Warning Alerts
    String PREF_FILTER_ALERTS_ENABLE_WARNING_CATEGORY = "filter_alerts_enable_warning_category";
    boolean FILTER_ALERTS_ENABLE_WARNING_CATEGORY_DEFAULT_VALUE = true;

    // Enable Danger Alerts
    String PREF_FILTER_ALERTS_ENABLE_DANGER_CATEGORY = "filter_alerts_enable_danger_category";
    boolean FILTER_ALERTS_ENABLE_DANGER_CATEGORY_DEFAULT_VALUE = true;

    String PREF_PREFERENCES_UPDATE_AT = "preferences_update_at";
    long PREF_PREFERENCES_UPDATE_AT_DEFAULT_VALUE = 0L;


    String ALERTS_CATEGORY_GROUP_KEY = "alertsCategoryGroup";

    String ALERTS_SETTINGS_PREFERENCE_SCREEN = "alertsSettingsPreferenceScreen";

    // Age of Results (Kid Results Preference Screen)
    String PREF_AGE_OF_RESULTS = "age_of_results";
    String PREF_AGE_OF_RESULTS_DEFAULT_VALUE = "30";

    /**
     * Comments Settings
     */

    // Age Of Comments (Comments Settings Preference Screen)
    String PREF_AGE_OF_COMMENTS = "age_of_comments";
    String PREF_AGE_OF_COMMENTS_DEFAULT_VALUE = "90";

    // Enable All Social Media
    String PREF_ENABLE_ALL_SOCIAL_MEDIAS_CATEGORIES = "enable_all_social_medias_categories";
    boolean ENABLE_ALL_SOCIAL_MEDIAS_CATEGORIES_DEFAULT_VALUE = true;

    // Enable Facebook
    String PREF_ENABLE_FACEBOOK_SOCIAL_MEDIA = "enable_facebook_social_media";
    boolean ENABLE_FACEBOOK_SOCIAL_MEDIA_DEFAULT_VALUE = true;

    // Enable Instagram
    String PREF_ENABLE_INSTAGRAM_SOCIAL_MEDIA = "enable_instagram_social_media";
    boolean ENABLE_INSTAGRAM_SOCIAL_MEDIA_DEFAULT_VALUE = true;

    // Enable Youtube
    String PREF_ENABLE_YOUTUBE_SOCIAL_MEDIA = "enable_youtube_social_media";
    boolean ENABLE_YOUTUBE_SOCIAL_MEDIA_DEFAULT_VALUE = true;

    // Enable Dimension Filter
    String PREF_ENABLE_DIMENSIONS_FILTER = "enable_dimensions_filter";
    boolean ENABLE_DIMENSIONS_FILTER_DEFAULT_VALUE = false;

    // Enable all comments dimension filter
    String PREF_ENABLE_ALL_COMMENTS_DIMENSION = "enable_all_comments_dimension";
    boolean ENABLE_ALL_COMMENTS_DIMENSION_DEFAULT_VALUE = false;

    // Enable Violence comment dimension
    String PREF_ENABLE_VIOLENCE_COMMENT_DIMENSION = "enable_violence_comment_dimension";
    boolean ENABLE_VIOLENCE_COMMENT_DIMENSION_DEFAULT_VALUE = false;

    // Enable Drugs comments dimension filter
    String PREF_ENABLE_DRUGS_COMMENTS_DIMENSION = "enable_drugs_comment_dimension";
    boolean ENABLE_DRUGS_COMMENTS_DIMENSION_DEFAULT_VALUE = false;

    // Enable Sex comments dimension filter
    String PREF_ENABLE_SEX_COMMENTS_DIMENSION = "enable_sex_comment_dimension";
    boolean ENABLE_SEX_COMMENTS_DIMENSION_DEFAULT_VALUE = false;

    // Enable Bullying Comment Dimension
    String PREF_ENABLE_BULLYING_COMMENTS_DIMENSION = "enable_bullying_comment_dimension";
    boolean ENABLE_BULLYING_COMMENTS_DIMENSION_DEFAULT_VALUE = false;

    // Comments Sentiment Level
    String PREF_COMMENTS_SENTIMENT_LEVEL = "comments_sentiment_level";
    String PREF_COMMENTS_SENTIMENT_LEVEL_DEFAULT_VALUE = "UNKNOWN";

    // Age of Relations (Relations Preference Screen)
    String PREF_AGE_OF_RELATIONS = "age_of_relations";
    String PREF_AGE_OF_RELATIONS_DEFAULT_VALUE = "30";


    /**
     * is Home Showcase completed
     * @return
     */
    boolean isHomeShowCaseCompleted();

    /**
     * Set Home showcase completed
     * @param isCompleted
     */
    void setHomeShowcaseCompleted(final boolean isCompleted);

    /**
     * Get Filter Alerts Count
     * @return
     */
    String getFilterAlertsCount();

    /**
     * Set Filter Alerts CountN
     * @param value
     */
    void setFilterAlertsCount(final String value);

    /**
     * Get Numbre Of Alerts
     * @return
     */
    int getNumberOfAlerts();

    /**
     * Set Number of Alerts
     * @param value
     */
    void setNumberOfAlerts(final String value);

    /**
     * Get Age of Alerts
     * @return
     */
    String getAgeOfAlerts();

    /**
     * Set Age Of Alerts
     * @param value
     */
    void setAgeOfAlerts(final String value);

    /**
     * Get Filter Age of Alerts
     * @return
     */
    String getFilterAgeOfAlerts();

    /**
     * Set Filter Age Of Alerts
     * @param value
     */
    void setFilterAgeOfAlerts(final String value);

    /**
     * Get Remove Alerts Every
     * @return
     */
    int getRemoveAlertsEvery();

    /**
     * Set Remove Alerts Every
     * @param value
     */
    void setRemoveAlertsEvery(final String value);

    /**
     * Is Enable Push Notifications
     * @return
     */
    boolean isEnablePushNotifications();

    /**
     * Set Enable Push Notifications
     * @param enablePushNotification
     */
    void setEnablePushNotifications(boolean enablePushNotification);

    /**
     * Is Enable All Alerts Categories
     * @return
     */
    boolean isEnableAllAlertCategories();

    /**
     * Set Enable All ALerts Categories
     * @param enableAllAlertCategories
     */
    void setEnableAllAlertCategories(boolean enableAllAlertCategories);

    /**
     * Is Success Alerts Enabled
     * @return
     */
    boolean isSuccessAlertsEnabled();

    /**
     * Set Success Alerts Enabled
     * @param enableSuccessAlerts
     */
    void setSuccessAlertsEnabled(boolean enableSuccessAlerts);

    /**
     * Is Information Alerts Enabled
     * @return
     */
    boolean isInformationAlertsEnabled();

    /**
     * Set Information Alerts Enabled
     * @param enableInformationAlerts
     */
    void setInformationAlertsEnabled(boolean enableInformationAlerts);

    /**
     * Is Warning Alerts Enabled
     * @return
     */
    boolean isWarningAlertsEnabled();

    /**
     * Set Warning Alerts Enabled
     * @param enableWarningAlerts
     */
    void setWarningAlertsEnabled(boolean enableWarningAlerts);

    /**
     * Is Danger Alerts Enabled
     * @return
     */
    boolean isDangerAlertsEnabled();

    /**
     * Set Danger Alerts Enabled
     * @param enableDangerAlerts
     */
    void setDangerAlertsEnabled(boolean enableDangerAlerts);

    /**
     * Get Pref Current User Identity
     * @return
     */
    String getPrefCurrentUserIdentity();

    /**
     * Set Pref Current Usre Identity
     * @param identity
     */
    void setPrefCurrentUserIdentity(final String identity);

    /**
     * Get Pref Count Alerts
     * @return
     */
    String getPrefCountAlerts();

    /**
     * Set Pref Count Alerts
     * @param countAlerts
     */
    void setPrefCountAlerts(final int countAlerts);

    /**
     * Get Pref Alerts Days Ago
     * @return
     */
    String getPrefAlertsDaysAgo();

    /**
     * Set Pref Alerts Days Ago
     * @param daysAgo
     */
    void setPrefAlertsDaysAgo(final int daysAgo);

    /**
     * Get Pref Alerts Level
     * @return
     */
    String getPrefAlertsLevel();

    /**
     * Set Pref Alerts Level
     * @param alertsLevel
     */
    void setPrefAlertsLevel(final String alertsLevel);

    /**
     * Is Filter Alerts Enable All Categories
     * @return
     */
    boolean isFilterAlertsEnableAllCategories();

    /**
     * Set Filter Enable All Alerts Categories
     * @param enableAllAlertCategories
     */
    void setFilterEnableAllAlertCategories(boolean enableAllAlertCategories);

    /**
     * Set Pref Filter Alerts Enable Succes Category
     * @param enableSuccessAlerts
     */
    void setPrefFilterAlertsEnableSuccessCategory(boolean enableSuccessAlerts);

    /**
     * Is Filter Alerts Enable Success Category
     * @return
     */
    boolean isFilterAlertsEnableSuccessCategory();

    /**
     * Is Filter Alerts Enable Information Category
     * @return
     */
    boolean isFilterAlertsEnableInformationCategory();

    /**
     * Set Pref Filter Alerts Enable Information Category
     * @param enableInformationAlerts
     */
    void setPrefFilterAlertsEnableInformationCategory(boolean enableInformationAlerts);

    /**
     * Is Filter Alerts Enable Warning Category
     * @return
     */
    boolean isFilterAlertsEnableWarningCategory();

    /**
     * Set Pref Filter Alerts Enable Warning Category
     * @param enableWarningAlerts
     */
    void setPrefFilterAlertsEnableWarningCategory(boolean enableWarningAlerts);

    /**
     * Is Filter Alerts Enable Danger Category
     * @return
     */
    boolean isFilterAlertsEnableDangerCategory();

    /**
     * Set Pref Filter Alerts Enable Danger Category
     * @param enableDangerAlerts
     */
    void setPrefFilterAlertsEnableDangerCategory(boolean enableDangerAlerts);

    /**
     * Set Preferences Update At
     * @param updateAt
     */
    void setPreferencesUpdateAt(final long updateAt);

    /**
     * Get Preferences Update At
     * @return
     */
    long getPreferencesUpdateAt();


    /**
     * Set Pref Age Of Results
     * @param ageOfResults
     */
    void setPrefAgeOfResults(final String ageOfResults);

    /**
     * Get Age Of Results
     * @return
     */
    String getAgeOfResults();

    /**
     * Get Age Of Results As Int
     * @return
     */
    int getAgeOfResultsAsInt();


    /**
     * Set Pref Age Of Comments
     * @param ageOfComments
     */
    void setPrefAgeOfComments(final String ageOfComments);

    /**
     * Get Age Of Comments
     * @return
     */
    String getAgeOfComments();

    /**
     * Get Age Of Comments As Int
     * @return
     */
    int getAgeOfCommentsAsInt();


    /**
     * Is All Social Media Categories Enabled
     * @return
     */
    boolean isAllSocialMediaCategoriesEnabled();

    /**
     * Set All Social Media Categories Enabled
     * @param allSocialMediaCategoriesEnabled
     */
    void setAllSocialMediaCategoriesEnabled(boolean allSocialMediaCategoriesEnabled);


    /**
     * Is Facebook Social Media Enabled
     * @return
     */
    boolean isFacebookSocialMediaEnabled();

    /**
     * Set Facebook Social Media Categories Enabled
     * @param facebookSocialMediaEnabled
     */
    void setFacebookSocialMediaEnabled(boolean facebookSocialMediaEnabled);

    /**
     * Is Instagram Social Media Enabled
     * @return
     */
    boolean isInstagramSocialMediaEnabled();

    /**
     * Set Instagram Social Media Categories Enabled
     * @param instagramSocialMediaEnabled
     */
    void setInstagramSocialMediaEnabled(boolean instagramSocialMediaEnabled);

    /**
     * Is Youtube Social Media Enabled
     * @return
     */
    boolean isYoutubeSocialMediaEnabled();

    /**
     * Set Enable Youtube Social Media Categories
     * @param youtubeSocialMediaEnabled
     */
    void setYoutubeSocialMediaEnabled(boolean youtubeSocialMediaEnabled);

    /**
     * Get Social Media Enabled
     * @return
     */
    String[] getSocialMediaEnabled();

    /**
     * Is Dimension Filter Enabled
     * @return
     */
    boolean isDimensionFilterEnabled();

    /**
     * Set Dimension Filter
     * @param dimensionFilterEnabled
     */
    void setDimensionFilter(boolean dimensionFilterEnabled);

    /**
     * Is All Comments Dimension Enabled
     * @return
     */
    boolean isAllCommentsDimensionEnabled();

    /**
     * Set All Comments Dimension Enabled
     * @param allCommentsDimensionEnabled
     */
    void setAllCommentsDimensionEnabled(boolean allCommentsDimensionEnabled);


    /**
     * Is Violence Dimension Enabled
     * @return
     */
    boolean isViolenceDimensionEnabled();

    /**
     * Set Violence Dimension Enabled
     * @param violenceDimensionEnabled
     */
    void setViolenceDimensionEnabled(boolean violenceDimensionEnabled);

    /**
     * Is Drugs Dimension Enabled
     * @return
     */
    boolean isDrugsDimensionEnabled();

    /**
     * Set Drugs Dimension Enabled
     * @param drugsDimensionEnabled
     */
    void setDrugsDimensionEnabled(boolean drugsDimensionEnabled);

    /**
     * Is Sex Dimension Enabled
     * @return
     */
    boolean isSexDimensionEnabled();

    /**
     * Set Sex Dimension Enabled
     * @param sexDimensionEnabled
     */
    void setSexDimensionEnabled(boolean sexDimensionEnabled);

    /**
     * Is Sex Dimension Enabled
     * @return
     */
    boolean isBullyingDimensionEnabled();

    /**
     * Set Bullying Dimension Enabled
     * @param bullyingDimensionEnabled
     */
    void setBullyingDimensionEnabled(boolean bullyingDimensionEnabled);


    /**
     * Set Pref Age Of Relations
     * @param ageOfRelations
     */
    void setPrefAgeOfRelations(final String ageOfRelations);

    /**
     * Get Age Of Relations
     * @return
     */
    String getAgeOfRelations();

    /**
     * Get Age Of Relations As Int
     * @return
     */
    int getAgeOfRelationsAsInt();


    /**
     * Get Comments Sentiment Level
     * @return
     */
    String getCommentsSentimentLevel();

    /**
     * Set Comments Sentiment Level
     * @param level
     */
    void setCommentsSentimentLevel(final String level);


}
