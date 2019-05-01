package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.PlaceSuggestionDTO;
import sanchez.sanchez.sergio.domain.models.PlaceSuggestionEntity;

/**
 * Place Suggestion Entity Data Mapper
 **/
public final class PlaceSuggestionEntityDataMapper extends AbstractDataMapper<PlaceSuggestionDTO, PlaceSuggestionEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public PlaceSuggestionEntity transform(final PlaceSuggestionDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final PlaceSuggestionEntity placeSuggestionEntity = new PlaceSuggestionEntity();
        placeSuggestionEntity.setTitle(originModel.getTitle());
        placeSuggestionEntity.setCategory(originModel.getCategory());
        placeSuggestionEntity.setHighlightedTitle(originModel.getHighlightedTitle());
        placeSuggestionEntity.setHref(originModel.getHref());
        placeSuggestionEntity.setPosition(originModel.getPosition());
        placeSuggestionEntity.setType(originModel.getType());
        placeSuggestionEntity.setVicinity(originModel.getVicinity());
        return placeSuggestionEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public PlaceSuggestionDTO transformInverse(final PlaceSuggestionEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final PlaceSuggestionDTO placeSuggestionDTO = new PlaceSuggestionDTO();
        placeSuggestionDTO.setTitle(originModel.getTitle());
        placeSuggestionDTO.setCategory(originModel.getCategory());
        placeSuggestionDTO.setHighlightedTitle(originModel.getHighlightedTitle());
        placeSuggestionDTO.setHref(originModel.getHref());
        placeSuggestionDTO.setPosition(originModel.getPosition());
        placeSuggestionDTO.setType(originModel.getType());
        placeSuggestionDTO.setVicinity(originModel.getVicinity());
        return placeSuggestionDTO;
    }
}
