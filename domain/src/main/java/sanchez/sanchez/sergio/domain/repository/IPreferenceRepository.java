package sanchez.sanchez.sergio.domain.repository;

import sanchez.sanchez.sergio.domain.utils.IAuthTokenAware;

/**
 * Preference Repository
 */
public interface IPreferenceRepository extends IAuthTokenAware {

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
    int COUNT_ALERTS_DEFAULT_VALUE = 20;

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

    /**
     * Get Filter Alerts Count
     * @return
     */
    String getFilterAlertsCount();

    /**
     * Set Filter Alerts Count
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
    int getPrefCountAlerts();

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

    void setPrefFilterAlertsEnableWarningCategory(boolean enableWarningAlerts);

    boolean isFilterAlertsEnableDangerCategory();

    void setPrefFilterAlertsEnableDangerCategory(boolean enableDangerAlerts);


}
