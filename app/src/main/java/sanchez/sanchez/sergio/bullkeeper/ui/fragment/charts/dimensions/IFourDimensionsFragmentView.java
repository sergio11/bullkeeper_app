package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.dimensions;


import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;

/**
 * Four Dimensions Fragment View
 */
public interface IFourDimensionsFragmentView extends ISupportView {

    /**
     * On Dimensions Data Loaded
     * @param dimensionEntities
     */
    void onDimensionsDataLoaded(final List<DimensionEntity> dimensionEntities);

    /**
     * On No Dimensions Data Avaliable
     */
    void onNoDimensionsDataAvaliable();


}
