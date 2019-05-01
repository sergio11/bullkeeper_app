package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Place Suggestion Entity
 **/
public final class PlaceSuggestionEntity implements Serializable {

    /**
     * Title
     */
    private String title;

    /**
     * Position
     */
    private Double[] position;

    /**
     * Highlighted Title
     */
    private String highlightedTitle;

    /**
     * Vicinity
     */
    private String vicinity;

    /**
     * Category
     */
    private String category;

    /**
     * Href
     */
    private String href;

    /**
     * Type
     */
    private String type;


    public PlaceSuggestionEntity(){}

    /**
     *
     * @param title
     * @param position
     * @param highlightedTitle
     * @param vicinity
     * @param category
     * @param href
     * @param type
     */
    public PlaceSuggestionEntity(String title, Double[] position, String highlightedTitle, String vicinity, String category, String href, String type) {
        this.title = title;
        this.position = position;
        this.highlightedTitle = highlightedTitle;
        this.vicinity = vicinity;
        this.category = category;
        this.href = href;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double[] getPosition() {
        return position;
    }

    public void setPosition(Double[] position) {
        this.position = position;
    }

    public String getHighlightedTitle() {
        return highlightedTitle;
    }

    public void setHighlightedTitle(String highlightedTitle) {
        this.highlightedTitle = highlightedTitle;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PlaceSuggestionEntity{" +
                "title='" + title + '\'' +
                ", position=" + Arrays.toString(position) +
                ", highlightedTitle='" + highlightedTitle + '\'' +
                ", vicinity='" + vicinity + '\'' +
                ", category='" + category + '\'' +
                ", href='" + href + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
