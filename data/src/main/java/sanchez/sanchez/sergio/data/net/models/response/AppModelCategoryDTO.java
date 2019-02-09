package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * App Model Category DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AppModelCategoryDTO implements Serializable {

    /**
     * Category
     */
    @JsonProperty("name")
    private String name;

    /**
     * Cat Key
     */
    @JsonProperty("cat_key")
    private String catKey;

    /**
     * Default Rule
     */
    @JsonProperty("default_rule")
    private String defaultAppRule;

    /**
     *
     */
    public AppModelCategoryDTO(){}

    /**
     *
     * @param name
     * @param catKey
     * @param defaultAppRule
     */
    public AppModelCategoryDTO(final String name, final String catKey,
                               final String defaultAppRule) {
        this.name = name;
        this.catKey = catKey;
        this.defaultAppRule = defaultAppRule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatKey() {
        return catKey;
    }

    public void setCatKey(String catKey) {
        this.catKey = catKey;
    }

    public String getDefaultAppRule() {
        return defaultAppRule;
    }

    public void setDefaultAppRule(String defaultAppRule) {
        this.defaultAppRule = defaultAppRule;
    }

    @Override
    public String toString() {
        return "AppModelCategoryDTO{" +
                "name='" + name + '\'' +
                ", catKey='" + catKey + '\'' +
                ", defaultAppRule='" + defaultAppRule + '\'' +
                '}';
    }
}
