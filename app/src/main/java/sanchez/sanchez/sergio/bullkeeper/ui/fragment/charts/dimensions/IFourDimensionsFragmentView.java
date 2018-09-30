package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.dimensions;


import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;

/**
 * Four Dimensions Fragment View
 */
public interface IFourDimensionsFragmentView extends ISupportView {

    /**
     * On Dimensions Data Loaded
     * @param entries
     */
    void onDimensionsDataLoaded(final List<BarEntry> entries);


}
