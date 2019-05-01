package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Place Suggestion DTO
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PlaceSuggestionDTO implements Serializable {

    /**
     * Title
     */
    @JsonProperty("title")
    private String title;

    /**
     * Position
     */
    @JsonProperty("position")
    private Double[] position;

    /**
     * Highlighted Title
     */
    @JsonProperty("highlightedTitle")
    private String highlightedTitle;

    /**
     * Vicinity
     */
    @JsonProperty("vicinity")
    private String vicinity;

    /**
     * Category
     */
    @JsonProperty("category")
    private String category;

    /**
     * Href
     */
    @JsonProperty("href")
    private String href;

    /**
     * Type
     */
    @JsonProperty("type")
    private String type;

    public PlaceSuggestionDTO(){}

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
    public PlaceSuggestionDTO(String title, Double[] position, String highlightedTitle, String vicinity, String category, String href, String type) {
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
        return "PlaceSuggestionDTO{" +
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
