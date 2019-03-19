package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.DimensionsStatisticsDTO;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;
import sanchez.sanchez.sergio.domain.models.DimensionsStatisticsEntity;

/**
 * Dimension Statistics Entity Data Mapper
 */
public final class DimensionsStatisticsEntityDataMapper extends AbstractDataMapper<DimensionsStatisticsDTO, DimensionsStatisticsEntity> {

    /**
     * Dimension Entity Abstract Data Mapper
     */
    private final AbstractDataMapper<DimensionsStatisticsDTO.DimensionDTO, DimensionEntity> dimensionEntityAbstractDataMapper;

    /**
     *
     * @param dimensionEntityAbstractDataMapper
     */
    public DimensionsStatisticsEntityDataMapper(AbstractDataMapper<DimensionsStatisticsDTO.DimensionDTO, DimensionEntity> dimensionEntityAbstractDataMapper) {
        this.dimensionEntityAbstractDataMapper = dimensionEntityAbstractDataMapper;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public DimensionsStatisticsEntity transform(final DimensionsStatisticsDTO originModel) {
        Preconditions.checkNotNull(originModel, "Model can not be null");

        final DimensionsStatisticsEntity dimensionsStatisticsEntity = new DimensionsStatisticsEntity();
        dimensionsStatisticsEntity.setTitle(originModel.getTitle());
        dimensionsStatisticsEntity.setSubtitle(originModel.getSubtitle());
        dimensionsStatisticsEntity.setDimensions(dimensionEntityAbstractDataMapper
                .transform(originModel.getDimensions()));

        return dimensionsStatisticsEntity;

    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public DimensionsStatisticsDTO transformInverse(final DimensionsStatisticsEntity originModel) {
        Preconditions.checkNotNull(originModel, "Model can not be null");

        final DimensionsStatisticsDTO dimensionsStatisticsDTO =
                new DimensionsStatisticsDTO();
        dimensionsStatisticsDTO.setTitle(originModel.getTitle());
        dimensionsStatisticsDTO.setSubtitle(originModel.getSubtitle());
        dimensionsStatisticsDTO.setDimensions(
                dimensionEntityAbstractDataMapper.transformInverse(originModel.getDimensions())
        );

        return dimensionsStatisticsDTO;
    }
}
