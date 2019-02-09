package sanchez.sanchez.sergio.domain.models;

/**
 * App Installed Detail Entity
 */
public final class AppInstalledDetailEntity extends AppInstalledEntity {

    /**
     * App Model
     */
    private AppModelEntity model;

    /**
     *
     */
    public AppInstalledDetailEntity(){}

    /**
     *
     * @param model
     */
    public AppInstalledDetailEntity(AppModelEntity model) {
        this.model = model;
    }

    /**
     *
     * @param identity
     * @param packageName
     * @param category
     * @param categoryKey
     * @param firstInstallTime
     * @param lastUpdateTime
     * @param versionName
     * @param versionCode
     * @param appName
     * @param appRuleEnum
     * @param iconEncodedString
     * @param disabled
     * @param model
     */
    public AppInstalledDetailEntity(String identity, String packageName, String category, AppModelCategoryEnum categoryKey, long firstInstallTime, long lastUpdateTime, String versionName, String versionCode, String appName, AppRuleEnum appRuleEnum, String iconEncodedString, Boolean disabled, AppModelEntity model) {
        super(identity, packageName, category, categoryKey, firstInstallTime, lastUpdateTime, versionName, versionCode, appName, appRuleEnum, iconEncodedString, disabled);
        this.model = model;
    }

    public AppModelEntity getModel() {
        return model;
    }

    public void setModel(AppModelEntity model) {
        this.model = model;
    }
}
