package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Search Places Result DTO
 **/
public final class SearchPlacesResultDTO implements Serializable {

    @JsonProperty("results")
    private List<PlaceSuggestionDTO> results;

    public SearchPlacesResultDTO() {}

    /**
     *
     * @param results
     */
    public SearchPlacesResultDTO(final List<PlaceSuggestionDTO> results) {
        this.results = results;
    }

    public List<PlaceSuggestionDTO> getResults() {
        return results;
    }

    public void setResults(List<PlaceSuggestionDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "SearchPlacesResultDTO{" +
                "results=" + results +
                '}';
    }
}
