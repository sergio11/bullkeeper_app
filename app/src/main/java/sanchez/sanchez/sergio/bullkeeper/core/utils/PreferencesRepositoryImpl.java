package sanchez.sanchez.sergio.bullkeeper.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;
import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;

/**
 * Preferences Manager
 */
public class PreferencesRepositoryImpl implements IPreferenceRepository {

    private final static String TAG = "PREFERENCES_MANAGER";

    private final SharedPreferences mPref;

    /**
     * Preferences Manager
     * @param context
     */
    @Inject
    public PreferencesRepositoryImpl(final Context context) {
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
     * Get Filter Alerts Count
     * @return
     */
    @Override
    public String getFilterAlertsCount() {
        return mPref.getString(PREF_FILTER_ALERTS_COUNT, FILTER_ALERTS_COUNT_DEFAULT_VALUE);
    }

    /**
     * Set Number of alerts
     * @param value
     */
    @Override
    public void setFilterAlertsCount(final String value) {
        mPref.edit()
                .putString(PREF_FILTER_ALERTS_COUNT, value)
                .apply();
    }

    /**
     * Get Number of Alerts
     * @return
     */
    @Override
    public int getNumberOfAlerts() {
        return mPref.getInt(PREF_NUMBER_OF_ALERTS, NUMBER_OF_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Number of alerts
     * @param value
     */
    @Override
    public void setNumberOfAlerts(final String value) {
        mPref.edit()
                .putString(PREF_NUMBER_OF_ALERTS, value)
                .apply();
    }


    /**
     * Get Age of Alerts
     * @return
     */
    @Override
    public String getAgeOfAlerts() {
        return mPref.getString(PREF_AGE_OF_ALERTS, AGE_OF_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Age of alerts
     * @param value
     */
    @Override
    public void setAgeOfAlerts(final String value) {
        mPref.edit()
                .putString(PREF_AGE_OF_ALERTS, value)
                .apply();
    }

    /**
     * Get Filter Age of Alerts
     * @return
     */
    @Override
    public String getFilterAgeOfAlerts() {
        return mPref.getString(PREF_FILTER_AGE_OF_ALERTS, FILTER_AGE_OF_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Filter Age of Alerts
     * @param value
     */
    @Override
    public void setFilterAgeOfAlerts(final String value) {
        mPref.edit()
                .putString(PREF_FILTER_AGE_OF_ALERTS, value)
                .apply();
    }

    /**
     * Get Remove Alerts Every
     * @return
     */
    @Override
    public int getRemoveAlertsEvery() {
        return mPref.getInt(PREF_REMOVE_ALERTS_EVERY, REMOVE_ALERTS_EVERY_DEFAULT_VALUE);
    }

    /**
     * Set Remove Alerts Every
     * @param value
     */
    @Override
    public void setRemoveAlertsEvery(final String value) {
        mPref.edit()
                .putString(PREF_REMOVE_ALERTS_EVERY, value)
                .apply();
    }

    /**
     * Is Enable Push Notifications
     * @return
     */
    @Override
    public boolean isEnablePushNotifications() {
        return mPref.getBoolean(PREF_ENABLE_PUSH_NOTIFICATIONS,
                ENABLE_PUSH_NOTIFICATIONS_DEFAULT_VALUE);
    }

    /**
     * Set Enable Push Notification
     * @param enablePushNotification
     */
    @Override
    public void setEnablePushNotifications(boolean enablePushNotification) {
        mPref.edit()
                .putBoolean(PREF_ENABLE_PUSH_NOTIFICATIONS, enablePushNotification)
                .apply();
    }


    /**
     * Is Enable All Alert Categories
     * @return
     */
    @Override
    public boolean isEnableAllAlertCategories() {
        return mPref.getBoolean(PREF_ENABLE_ALL_ALERT_CATEGORIES,
                ENABLE_ALL_ALERT_CATEGORIES_DEFAULT_VALUE);
    }

    /**
     * Set Enable All Alert Categories
     * @param enableAllAlertCategories
     */
    @Override
    public void setEnableAllAlertCategories(boolean enableAllAlertCategories) {
        mPref.edit()
                .putBoolean(PREF_ENABLE_ALL_ALERT_CATEGORIES, enableAllAlertCategories)
                .apply();
    }

    /**
     * Is Success Alerts Enabled
     * @return
     */
    @Override
    public boolean isSuccessAlertsEnabled() {
        return mPref.getBoolean(PREF_ENABLE_SUCCESS_ALERTS,
                ENABLE_SUCCESS_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Success Alerts Enabled
     * @param enableSuccessAlerts
     */
    @Override
    public void setSuccessAlertsEnabled(boolean enableSuccessAlerts) {
        mPref.edit()
                .putBoolean(PREF_ENABLE_SUCCESS_ALERTS, enableSuccessAlerts)
                .apply();
    }

    /**
     * Is Information Alerts Enabled
     * @return
     */
    @Override
    public boolean isInformationAlertsEnabled() {
        return mPref.getBoolean(PREF_ENABLE_INFORMATION_ALERTS,
                ENABLE_INFORMATION_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Information Alerts Enabled
     * @param enableInformationAlerts
     */
    @Override
    public void setInformationAlertsEnabled(boolean enableInformationAlerts) {
        mPref.edit()
                .putBoolean(PREF_ENABLE_INFORMATION_ALERTS, enableInformationAlerts)
                .apply();
    }


    /**
     * Is Warning Alerts Enabled
     * @return
     */
    @Override
    public boolean isWarningAlertsEnabled() {
        return mPref.getBoolean(PREF_ENABLE_WARNING_ALERTS,
                ENABLE_WARNING_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Warning Alerts Enabled
     * @param enableWarningAlerts
     */
    @Override
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
    @Override
    public boolean isDangerAlertsEnabled() {
        return mPref.getBoolean(PREF_ENABLE_DANGER_ALERTS,
                ENABLE_DANGER_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Danger Alerts Enabled
     * @param enableDangerAlerts
     */
    @Override
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
    @Override
    public String getAuthToken() {
        return mPref.getString(PREF_AUTH_TOKEN, AUTH_TOKEN_DEFAULT_VALUE);
    }

    /**
     * Set Auth Token
     * @param token
     */
    @Override
    public void setAuthToken(final String token) {
        mPref.edit()
                .putString(PREF_AUTH_TOKEN, token)
                .apply();
    }

    /**
     * Get Current User Identity
     * @return
     */
    @Override
    public String getPrefCurrentUserIdentity() {
        return mPref.getString(PREF_CURRENT_USER_IDENTITY, CURRENT_USER_IDENTITY_DEFAULT_VALUE);
    }

    /**
     * Set Current User Identity
     * @param identity
     */
    @Override
    public void setPrefCurrentUserIdentity(final String identity) {
        mPref.edit()
                .putString(PREF_CURRENT_USER_IDENTITY, identity)
                .apply();
    }

    /**
     * Get Count Alerts
     * @return
     */
    @Override
    public String getPrefCountAlerts() {
        return mPref.getString(PREF_COUNT_ALERTS, COUNT_ALERTS_DEFAULT_VALUE);
    }

    /**
     * Set Count Alerts
     * @param countAlerts
     */
    @Override
    public void setPrefCountAlerts(final int countAlerts) {
        mPref.edit()
                .putInt(PREF_COUNT_ALERTS, countAlerts)
                .apply();
    }

    /**
     * Get Alerts Days Ago
     * @return
     */
    @Override
    public String getPrefAlertsDaysAgo() {
        return mPref.getString(PREF_ALERTS_DAYS_AGO, ALERTS_DAYS_AGO_DEFAULT_VALUE);
    }

    /**
     * Set Alerts Days Ago
     * @param daysAgo
     */
    @Override
    public void setPrefAlertsDaysAgo(final int daysAgo) {
        mPref.edit()
                .putInt(PREF_ALERTS_DAYS_AGO, daysAgo)
                .apply();
    }


    /**
     * Get Alerts Level
     * @return
     */
    @Override
    public String getPrefAlertsLevel() {
        return mPref.getString(PREF_ALERTS_LEVELS, ALERTS_LEVELS_DEFAULT_VALUE);
    }

    /**
     * Set Alerts Level
     * @param alertsLevel
     */
    @Override
    public void setPrefAlertsLevel(final String alertsLevel) {
        mPref.edit()
                .putString(PREF_ALERTS_LEVELS, alertsLevel)
                .apply();
    }


    /**
     * Is Filter alerts enable all categories
     * @return
     */
    @Override
    public boolean isFilterAlertsEnableAllCategories() {
        return mPref.getBoolean(PREF_FILTER_ALERTS_ENABLE_ALL_CATEGORIES,
                ENABLE_ALL_ALERT_CATEGORIES_DEFAULT_VALUE);
    }

    /**
     * Set Filter enable all alerts categories
     * @param enableAllAlertCategories
     */
    @Override
    public void setFilterEnableAllAlertCategories(boolean enableAllAlertCategories) {
        mPref.edit()
                .putBoolean(PREF_FILTER_ALERTS_ENABLE_ALL_CATEGORIES, enableAllAlertCategories)
                .apply();
    }


    /**
     * Set Filter Alerts Success Category
     * @param enableSuccessAlerts
     */
    @Override
    public void setPrefFilterAlertsEnableSuccessCategory(boolean enableSuccessAlerts) {
        mPref.edit()
                .putBoolean(PREF_FILTER_ALERTS_ENABLE_SUCCESS_CATEGORY,
                        enableSuccessAlerts)
                .apply();
    }

    /**
     * Is Filter Alerts Enable Success Category
     * @return
     */
    @Override
    public boolean isFilterAlertsEnableSuccessCategory() {
        return mPref.getBoolean(PREF_FILTER_ALERTS_ENABLE_SUCCESS_CATEGORY,
                FILTER_ALERTS_ENABLE_SUCCESS_CATEGORY_DEFAULT_VALUE);
    }

    /**
     * Is Filter Alerts Enable Information Category
     * @return
     */
    @Override
    public boolean isFilterAlertsEnableInformationCategory() {
        return mPref.getBoolean(PREF_FILTER_ALERTS_ENABLE_INFORMATION_CATEGORY,
                FILTER_ALERTS_ENABLE_INFORMATION_CATEGORY_DEFAULT_VALUE);
    }

    /**
     * Set Filter Alerts Information Category
     * @param enableInformationAlerts
     */
    @Override
    public void setPrefFilterAlertsEnableInformationCategory(boolean enableInformationAlerts) {
        mPref.edit()
                .putBoolean(PREF_FILTER_ALERTS_ENABLE_INFORMATION_CATEGORY,
                        enableInformationAlerts)
                .apply();
    }

    /**
     * Is Filter Alerts Enable Warning Category
     * @return
     */
    @Override
    public boolean isFilterAlertsEnableWarningCategory() {
        return mPref.getBoolean(PREF_FILTER_ALERTS_ENABLE_WARNING_CATEGORY,
                FILTER_ALERTS_ENABLE_WARNING_CATEGORY_DEFAULT_VALUE);
    }

    /**
     * Set Filter Alerts Warning Category
     * @param enableWarningAlerts
     */
    @Override
    public void setPrefFilterAlertsEnableWarningCategory(boolean enableWarningAlerts) {
        mPref.edit()
                .putBoolean(PREF_FILTER_ALERTS_ENABLE_WARNING_CATEGORY,
                        enableWarningAlerts)
                .apply();
    }

    /**
     * Is Filter Alerts Enable Danger Category
     * @return
     */
    @Override
    public boolean isFilterAlertsEnableDangerCategory() {
        return mPref.getBoolean(PREF_FILTER_ALERTS_ENABLE_DANGER_CATEGORY,
                FILTER_ALERTS_ENABLE_DANGER_CATEGORY_DEFAULT_VALUE);
    }

    /**
     * Set Filter Alerts Danger Category
     * @param enableDangerAlerts
     */
    @Override
    public void setPrefFilterAlertsEnableDangerCategory(boolean enableDangerAlerts) {
        mPref.edit()
                .putBoolean(PREF_FILTER_ALERTS_ENABLE_DANGER_CATEGORY,
                        enableDangerAlerts)
                .apply();
    }

    /**
     * Set Preferences Update At
     * @param updateAt
     */
    @Override
    public void setPreferencesUpdateAt(final long updateAt) {
        mPref.edit()
                .putLong(PREF_PREFERENCES_UPDATE_AT, updateAt)
                .apply();
    }

    /**
     * Get Preferences Update At
     * @return
     */
    @Override
    public long getPreferencesUpdateAt() {
        return mPref.getLong(PREF_PREFERENCES_UPDATE_AT, PREF_PREFERENCES_UPDATE_AT_DEFAULT_VALUE);
    }

    /**
     * Set Pref Age Of Results
     * @param ageOfResults
     */
    @Override
    public void setPrefAgeOfResults(final String ageOfResults) {

        mPref.edit()
                .putString(PREF_AGE_OF_RESULTS, ageOfResults)
                .apply();
    }


    @Override
    public String getAgeOfResults() {
        return mPref.getString(PREF_AGE_OF_RESULTS, PREF_AGE_OF_RESULTS_DEFAULT_VALUE);
    }

    /**
     * Get Age Of Results
     * @return
     */
    @Override
    public int getAgeOfResultsAsInt() {
        int ageOfResults;
        try {
            ageOfResults = Integer.parseInt(mPref.getString(PREF_AGE_OF_RESULTS,
                    PREF_AGE_OF_RESULTS_DEFAULT_VALUE));
        } catch (final Exception ex) {
            ageOfResults = Integer.parseInt(PREF_AGE_OF_RESULTS_DEFAULT_VALUE);
        }
        return ageOfResults;
    }

}
