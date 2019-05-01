package sanchez.sanchez.sergio.data.net.services;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.response.SearchPlacesResultDTO;

/**
 * Places Service
 **/
public interface IPlacesService {

    /**
     * Autosuggest Places In
     * @param in
     * @param q (Text for search)
     * @return
     */
    @GET("autosuggest")
    Observable<SearchPlacesResultDTO> autosuggestIn(
            final @Query("in") String in,
            final @Query("q") String q,
            final @Query("result_types") String resultTypes);

    /**
     * Autosuggest Places At
     * @param at (Latitude and longitude)
     * @param q (Text for search)
     * @return
     */
    @GET("autosuggest")
    Observable<SearchPlacesResultDTO> autosuggestAt(
            final @Query("at") String at,
            final @Query("q") String q,
            final @Query("result_types") String resultTypes);
}
