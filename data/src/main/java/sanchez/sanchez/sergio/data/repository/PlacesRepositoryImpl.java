package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.PlaceSuggestionDTO;
import sanchez.sanchez.sergio.data.net.models.response.SearchPlacesResultDTO;
import sanchez.sanchez.sergio.data.net.services.IPlacesService;
import sanchez.sanchez.sergio.domain.models.PlaceSuggestionEntity;
import sanchez.sanchez.sergio.domain.repository.IPlacesRepository;

/**
 * Places Repository Impl
 **/
public final class PlacesRepositoryImpl implements IPlacesRepository {

    /**
     * Place Service
     */
    private final IPlacesService placesService;

    /**
     * Place Suggestion Entity Data Mapper
     */
    private final AbstractDataMapper<PlaceSuggestionDTO,
            PlaceSuggestionEntity> placeSuggestionEntityAbstractDataMapper;

    /**
     *
     * @param placesService
     * @param placeSuggestionEntityAbstractDataMapper
     */
    public PlacesRepositoryImpl(IPlacesService placesService,
                                AbstractDataMapper<PlaceSuggestionDTO, PlaceSuggestionEntity> placeSuggestionEntityAbstractDataMapper) {
        this.placesService = placesService;
        this.placeSuggestionEntityAbstractDataMapper = placeSuggestionEntityAbstractDataMapper;
    }


    /**
     * Autosuggest Places At
     * @param at
     * @param query
     * @return
     */
    @Override
    public Observable<List<PlaceSuggestionEntity>> autosuggestPlacesAt(final String at, final String query) {
        Preconditions.checkNotNull(at, "At can not be null");
        Preconditions.checkNotNull(query, "Query can not be null");

        return placesService.autosuggestAt(at, query, "address,place")
                .map(SearchPlacesResultDTO::getResults)
                .map(placeSuggestionEntityAbstractDataMapper::transform);
    }

    /**
     * Autosuggest Places In
     * @param in
     * @param query
     * @return
     */
    @Override
    public Observable<List<PlaceSuggestionEntity>> autosuggestPlacesIn(final String in, final String query) {
        Preconditions.checkNotNull(in, "In can not be null");
        Preconditions.checkNotNull(query, "Query can not be null");

        return placesService.autosuggestIn(in, query, "address,place")
                .map(SearchPlacesResultDTO::getResults)
                .map(placeSuggestionEntityAbstractDataMapper::transform);
    }
}
