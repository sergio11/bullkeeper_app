package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.DimensionsStatisticsDTO;
import sanchez.sanchez.sergio.domain.models.DimensionCategoryEnum;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;

/**
 * Dimension Entity Data Mapper
 */
public final class DimensionsEntityDataMapper extends AbstractDataMapper<DimensionsStatisticsDTO.DimensionDTO, DimensionEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public DimensionEntity transform(DimensionsStatisticsDTO.DimensionDTO originModel) {
        final DimensionEntity dimensionEntity = new DimensionEntity();
        dimensionEntity.setDimensionCategoryEnum(DimensionCategoryEnum.valueOf(originModel.getType()));
        dimensionEntity.setLabel(originModel.getLabel());
        dimensionEntity.setValue(originModel.getValue());
        return dimensionEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public DimensionsStatisticsDTO.DimensionDTO transformInverse(DimensionEntity originModel) {
        final DimensionsStatisticsDTO.DimensionDTO dimensionDTO = new DimensionsStatisticsDTO.DimensionDTO();
        dimensionDTO.setLabel(originModel.getLabel());
        dimensionDTO.setValue(originModel.getValue());
        dimensionDTO.setType(originModel.getDimensionCategoryEnum().name());
        return dimensionDTO;
    }
}
