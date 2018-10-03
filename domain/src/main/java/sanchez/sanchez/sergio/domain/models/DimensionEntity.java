package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Dimension Entity
 */
public final class DimensionEntity implements Serializable {

    private DimensionCategoryEnum dimensionCategoryEnum;
    private String label;
    private Integer value;

    public DimensionEntity(){}

    public DimensionEntity(DimensionCategoryEnum dimensionCategoryEnum, String label, Integer value) {
        this.dimensionCategoryEnum = dimensionCategoryEnum;
        this.label = label;
        this.value = value;
    }

    public DimensionCategoryEnum getDimensionCategoryEnum() {
        return dimensionCategoryEnum;
    }

    public void setDimensionCategoryEnum(DimensionCategoryEnum dimensionCategoryEnum) {
        this.dimensionCategoryEnum = dimensionCategoryEnum;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DimensionEntity{" +
                "dimensionCategoryEnum=" + dimensionCategoryEnum +
                ", label='" + label + '\'' +
                ", value=" + value +
                '}';
    }
}
