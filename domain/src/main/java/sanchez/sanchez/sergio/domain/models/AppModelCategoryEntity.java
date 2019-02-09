package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * App Model Category
 */
public final class AppModelCategoryEntity implements Serializable {

    /**
     * Name
     */
    private String name;

    /**
     * Category Key
     */
    private AppModelCategoryEnum catKey;

    /**
     * Default App Rule
     */
    private AppRuleEnum defaultAppRule;


    public AppModelCategoryEntity(){}

    /**
     *
     * @param name
     * @param catKey
     * @param defaultAppRule
     */
    public AppModelCategoryEntity(final String name, final AppModelCategoryEnum catKey,
                                  final AppRuleEnum defaultAppRule) {
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

    public AppModelCategoryEnum getCatKey() {
        return catKey;
    }

    public void setCatKey(AppModelCategoryEnum catKey) {
        this.catKey = catKey;
    }

    public AppRuleEnum getDefaultAppRule() {
        return defaultAppRule;
    }

    public void setDefaultAppRule(AppRuleEnum defaultAppRule) {
        this.defaultAppRule = defaultAppRule;
    }

    @Override
    public String toString() {
        return "AppModelCategoryEntity{" +
                "name='" + name + '\'' +
                ", catKey=" + catKey +
                ", defaultAppRule=" + defaultAppRule +
                '}';
    }
}
