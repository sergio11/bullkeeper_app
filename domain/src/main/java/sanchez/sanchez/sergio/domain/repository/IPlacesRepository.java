package sanchez.sanchez.sergio.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.PlaceSuggestionEntity;

/**
 * Place Repository
 **/
public interface IPlacesRepository {

    /**
     * Autosuggest Places At
     * @param at
     * @param query
     * @return
     */
    Observable<List<PlaceSuggestionEntity>> autosuggestPlacesAt(final String at, final String query);

    /**
     * Autosuggest Places In
     * @param in
     * @param query
     * @return
     */
    Observable<List<PlaceSuggestionEntity>> autosuggestPlacesIn(final String in, final String query);

}
