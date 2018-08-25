package sanchez.sanchez.sergio.masom_app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

/**
 * Preferences Manager
 */
public class PreferencesManager {

    private final static String TAG = "PREFERENCES_MANAGER";

    // NUMBER OF ALERTS
    public static final String PREF_NUMBER_OF_ALERTS = "number_of_alerts";
    public static final int NUMBER_OF_ALERTS_DEFAULT_VALUE = 20;

    // AGE OF ALERTS
    public static final String PREF_AGE_OF_ALERTS = "age_of_alerts";
    public static final int AGE_OF_ALERTS_DEFAULT_VALUE = 30;

    // REMOVE ALERTS EVERY
    public static final String PREF_REMOVE_ALERTS_EVERY = "remove_alerts_every";
    public static final int REMOVE_ALERTS_EVERY_DEFAULT_VALUE = 0;

    // Enable Push Notifications
    public static final String PREF_ENABLE_PUSH_NOTIFICATIONS = "enable_push_notifications";
    public static final boolean ENABLE_PUSH_NOTIFICATIONS_DEFAULT_VALUE = true;

    // Enable All Alert Categories
    public static final String PREF_ENABLE_ALL_ALERT_CATEGORIES = "enable_all_alert_categories";
    public static final boolean ENABLE_ALL_ALERT_CATEGORIES_DEFAULT_VALUE = true;

    // Enable Success alerts
    public static final String PREF_ENABLE_SUCCESS_ALERTS = "enable_success_alerts";
    public static final boolean ENABLE_SUCCESS_ALERTS_DEFAULT_VALUE = true;

    // Enable Information Alerts
    public static final String PREF_ENABLE_INFORMATION_ALERTS = "enable_information_alerts";
    public static final boolean ENABLE_INFORMATION_ALERTS_DEFAULT_VALUE = true;

    // Enable Warning Alerts
    public static final String PREF_ENABLE_WARNING_ALERTS = "enable_warning_alerts";
    public static final boolean ENABLE_WARNING_ALERTS_DEFAULT_VALUE = true;

    // Enable Danger Alerts
    public static final String PREF_ENABLE_DANGER_ALERTS = "enable_danger_alerts";
    public static final boolean ENABLE_DANGER_ALERTS_DEFAULT_VALUE = true;

    // Auth Token
    public static final String PREF_AUTH_TOKEN = "auth_token";
    public static final String AUTH_TOKEN_DEFAULT_VALUE = "";

    private final SharedPreferences mPref;

    /**
     * Preferences Manager
     * @param context
     */
    public PreferencesManager(final Context context) {
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Get Default Shared Preferences Name
     * @param context
     * @return
     */
    public static String getDefaultSharedPreferencesName(Context context) {
        return context.getPackageName() + "_preferences";
    }

    /**
     * Get Number of Alerts
     * @return
     */
    public int getNumberOfAlerts() {
        return mPref.getInt(PREF_NUMBER_OF_ALERTS, NUMBER_OF_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Number of alerts
     * @param value
     */
    public void setNumberOfAlerts(final String value) {
        mPref.edit()
                .putString(PREF_NUMBER_OF_ALERTS, value)
                .apply();
    }


    /**
     * Get Age of Alerts
     * @return
     */
    public int getAgeOfAlerts() {
        return mPref.getInt(PREF_AGE_OF_ALERTS, AGE_OF_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Age of alerts
     * @param value
     */
    public void setAgeOfAlerts(final String value) {
        mPref.edit()
                .putString(PREF_AGE_OF_ALERTS, value)
                .apply();
    }

    /**
     * Get Remove Alerts Every
     * @return
     */
    public int getRemoveAlertsEvery() {
        return mPref.getInt(PREF_REMOVE_ALERTS_EVERY, REMOVE_ALERTS_EVERY_DEFAULT_VALUE);
    }

    /**
     * Set Remove Alerts Every
     * @param value
     */
    public void setRemoveAlertsEvery(final String value) {
        mPref.edit()
                .putString(PREF_REMOVE_ALERTS_EVERY, value)
                .apply();
    }

    /**
     * Is Enable Push Notifications
     * @return
     */
    public boolean isEnablePushNotifications() {
        return mPref.getBoolean(PREF_ENABLE_PUSH_NOTIFICATIONS,
                ENABLE_PUSH_NOTIFICATIONS_DEFAULT_VALUE);
    }

    /**
     * Set Enable Push Notification
     * @param enablePushNotification
     */
    public void setEnablePushNotifications(boolean enablePushNotification) {
        mPref.edit()
                .putBoolean(PREF_ENABLE_PUSH_NOTIFICATIONS, enablePushNotification)
                .apply();
    }


    /**
     * Is Enable All Alert Categories
     * @return
     */
    public boolean isEnableAllAlertCategories() {
        return mPref.getBoolean(PREF_ENABLE_ALL_ALERT_CATEGORIES,
                ENABLE_ALL_ALERT_CATEGORIES_DEFAULT_VALUE);
    }

    /**
     * Set Enable All Alert Categories
     * @param enableAllAlertCategories
     */
    public void setEnableAllAlertCategories(boolean enableAllAlertCategories) {
        mPref.edit()
                .putBoolean(PREF_ENABLE_ALL_ALERT_CATEGORIES, enableAllAlertCategories)
                .apply();
    }

    /**
     * Is Success Alerts Enabled
     * @return
     */
    public boolean isSuccessAlertsEnabled() {
        return mPref.getBoolean(PREF_ENABLE_SUCCESS_ALERTS,
                ENABLE_SUCCESS_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Success Alerts Enabled
     * @param enableSuccessAlerts
     */
    public void setSuccessAlertsEnabled(boolean enableSuccessAlerts) {
        mPref.edit()
                .putBoolean(PREF_ENABLE_SUCCESS_ALERTS, enableSuccessAlerts)
                .apply();
    }

    /**
     * Is Information Alerts Enabled
     * @return
     */
    public boolean isInformationAlertsEnabled() {
        return mPref.getBoolean(PREF_ENABLE_INFORMATION_ALERTS,
                ENABLE_INFORMATION_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Information Alerts Enabled
     * @param enableInformationAlerts
     */
    public void setInformationAlertsEnabled(boolean enableInformationAlerts) {
        mPref.edit()
                .putBoolean(PREF_ENABLE_INFORMATION_ALERTS, enableInformationAlerts)
                .apply();
    }


    /**
     * Is Warning Alerts Enabled
     * @return
     */
    public boolean isWarningAlertsEnabled() {
        return mPref.getBoolean(PREF_ENABLE_WARNING_ALERTS,
                ENABLE_WARNING_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Warning Alerts Enabled
     * @param enableWarningAlerts
     */
    public void setWarningAlertsEnabled(boolean enableWarningAlerts) {
        mPref.edit()
                .putBoolean(PREF_ENABLE_WARNING_ALERTS,
                        enableWarningAlerts)
                .apply();
    }

    /**
     * Is Danger Alerts Enabled
     * @return
     */
    public boolean isDangerAlertsEnabled() {
        return mPref.getBoolean(PREF_ENABLE_DANGER_ALERTS,
                ENABLE_DANGER_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Danger Alerts Enabled
     * @param enableDangerAlerts
     */
    public void setDangerAlertsEnabled(boolean enableDangerAlerts) {
        mPref.edit()
                .putBoolean(PREF_ENABLE_DANGER_ALERTS,
                        enableDangerAlerts)
                .apply();
    }

    /**
     * Get Auth Token
     * @return
     */
    public String getAuthToken() {
        return mPref.getString(PREF_AUTH_TOKEN, AUTH_TOKEN_DEFAULT_VALUE);
    }

    /**
     * Set Auth Token
     * @param token
     */
    public void setAuthToken(final String token) {
        mPref.edit()
                .putString(PREF_AUTH_TOKEN, token)
                .apply();
    }
}
