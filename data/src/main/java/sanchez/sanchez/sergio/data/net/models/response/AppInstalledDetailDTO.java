package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * App Installed Detail DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AppInstalledDetailDTO extends AppInstalledDTO {

    /**
     * Model
     */
    @JsonProperty("model")
    private AppModelDTO model;

    /**
     *
     */
    public AppInstalledDetailDTO(){}

    /**
     *
     * @param model
     */
    public AppInstalledDetailDTO(final AppModelDTO model) {
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
     * @param appRule
     * @param iconEncodedString
     * @param disabled
     * @param model
     */
    public AppInstalledDetailDTO(String identity, String packageName, String category, String categoryKey, long firstInstallTime, long lastUpdateTime, String versionName, String versionCode, String appName, String appRule, String iconEncodedString, Boolean disabled, AppModelDTO model) {
        super(identity, packageName, category, categoryKey, firstInstallTime, lastUpdateTime, versionName, versionCode, appName, appRule, iconEncodedString, disabled);
        this.model = model;
    }

    public AppModelDTO getModel() {
        return model;
    }

    public void setModel(AppModelDTO model) {
        this.model = model;
    }
}
