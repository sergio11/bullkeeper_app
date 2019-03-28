package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Dimensions Statistics Entity
 */
public final class DimensionsStatisticsEntity implements Serializable {

    /**
     * Title
     */
    private String title;

    /**
     * Subtitle
     */
    private String subtitle;

    /**
     * Dimensions
     */
    private List<DimensionEntity> dimensions;

    /**
     * Total Comments
     */
    private long totalComments;


    public DimensionsStatisticsEntity(){}

    /**
     *
     * @param title
     * @param subtitle
     * @param dimensions
     * @param totalComments
     */
    public DimensionsStatisticsEntity(final String title,
                                      final String subtitle,
                                      final List<DimensionEntity> dimensions,
                                      final long totalComments) {
        this.title = title;
        this.subtitle = subtitle;
        this.dimensions = dimensions;
        this.totalComments = totalComments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<DimensionEntity> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<DimensionEntity> dimensions) {
        this.dimensions = dimensions;
    }

    public long getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(long totalComments) {
        this.totalComments = totalComments;
    }

    @Override
    public String toString() {
        return "DimensionsStatisticsEntity{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", dimensions=" + dimensions +
                ", totalComments=" + totalComments +
                '}';
    }
}
