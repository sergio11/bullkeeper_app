package sanchez.sanchez.sergio.bullkeeper.events.impl;

import android.content.Intent;
import android.os.Bundle;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IAppEventVisitor;

/**
 * New App Installed Event
 */
public final class NewAppInstalledEvent  extends SupportEvent<IAppEventVisitor> {

    /**
     * ARGS
     */

    private static final String IDENTITY_ARG = "IDENTITY_ARG";
    private static final String PACKAGE_NAME_ARG = "PACKAGE_NAME_ARG";
    private static final String FIRST_INSTALL_TIME_ARG = "FIRST_INSTALL_TIME_ARG";
    private static final String LAST_UPDATE_TIME_ARG = "LAST_UPDATE_TIME_ARG";
    private static final String VERSION_NAME_ARG = "VERSION_NAME_ARG";
    private static final String VERSION_CODE_ARG = "VERSION_CODE_ARG";
    private static final String APP_NAME_ARG = "APP_NAME_ARG";
    private static final String APP_RULE_ARG = "APP_RULE_ARG";
    private static final String ICON_ENCODED_ARG = "ICON_ENCODED_ARG";
    private static final String DISABLED_ARG = "DISABLED_ARG";
    private static final String TERMINAL_ARG = "TERMINAL_ARG";


    /**
     * Date
     */
    private String identity;
    private String packageName;
    private long firstInstallTime;
    private long lastUpdateTime;
    private String versionName;
    private String versionCode;
    private String appName;
    private String appRule;
    private String iconEncodedString;
    private Boolean disabled;
    private String terminal;

    /**
     *
     * @param identity
     * @param packageName
     * @param firstInstallTime
     * @param lastUpdateTime
     * @param versionName
     * @param versionCode
     * @param appName
     * @param appRule
     * @param iconEncodedString
     * @param disabled
     * @param terminal
     */
    public NewAppInstalledEvent(String identity, String packageName, long firstInstallTime, long lastUpdateTime,
                               String versionName, String versionCode,
                               String appName, String appRule, String iconEncodedString,
                                Boolean disabled, String terminal) {
        this.identity = identity;
        this.packageName = packageName;
        this.firstInstallTime = firstInstallTime;
        this.lastUpdateTime = lastUpdateTime;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.appName = appName;
        this.appRule = appRule;
        this.iconEncodedString = iconEncodedString;
        this.disabled = disabled;
        this.terminal = terminal;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getFirstInstallTime() {
        return firstInstallTime;
    }

    public void setFirstInstallTime(long firstInstallTime) {
        this.firstInstallTime = firstInstallTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppRule() {
        return appRule;
    }

    public void setAppRule(String appRule) {
        this.appRule = appRule;
    }

    public String getIconEncodedString() {
        return iconEncodedString;
    }

    public void setIconEncodedString(String iconEncodedString) {
        this.iconEncodedString = iconEncodedString;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    /**
     * To Intent
     * @return
     */
    @Override
    public Intent toIntent() {
        final Intent intent = new Intent(getClass().getCanonicalName());
        final Bundle bundle = new Bundle();
        bundle.putString(IDENTITY_ARG, identity);
        bundle.putString(PACKAGE_NAME_ARG, packageName);
        bundle.putLong(FIRST_INSTALL_TIME_ARG, firstInstallTime);
        bundle.putLong(LAST_UPDATE_TIME_ARG, lastUpdateTime);
        bundle.putString(VERSION_NAME_ARG, versionName);
        bundle.putString(VERSION_CODE_ARG, versionCode);
        bundle.putString(APP_NAME_ARG, appName);
        bundle.putString(APP_RULE_ARG, appRule);
        bundle.putString(ICON_ENCODED_ARG, iconEncodedString);
        bundle.putBoolean(DISABLED_ARG, disabled);
        bundle.putString(TERMINAL_ARG, terminal);
        return intent;
    }

    /**
     * Accept
     * @param visitor
     */
    @Override
    public void accept(IAppEventVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * From Bundle
     * @param bundle
     * @return
     */
    public static NewAppInstalledEvent fromBundle(final Bundle bundle) {
        Preconditions.checkNotNull(bundle, "Bundle can not be null");
        final String identity = bundle.getString(IDENTITY_ARG, "");
        final String packageName = bundle.getString(PACKAGE_NAME_ARG, "");
        final long firstInstallTime = bundle.getLong(FIRST_INSTALL_TIME_ARG);
        final long lastUpdateTime = bundle.getLong(LAST_UPDATE_TIME_ARG);
        final String versionName = bundle.getString(VERSION_NAME_ARG, "");
        final String versionCode = bundle.getString(VERSION_CODE_ARG, "");
        final String appName = bundle.getString(APP_NAME_ARG, "");
        final String appRule = bundle.getString(APP_RULE_ARG, "");
        final String iconEncodedString = bundle.getString(ICON_ENCODED_ARG, "");
        final Boolean disabled = bundle.getBoolean(DISABLED_ARG);
        final String terminal = bundle.getString(TERMINAL_ARG);
        return new NewAppInstalledEvent(identity, packageName,
                firstInstallTime, lastUpdateTime, versionName,
                versionCode, appName, appRule, iconEncodedString, disabled, terminal);
    }
}
