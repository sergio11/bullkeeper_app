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
}
