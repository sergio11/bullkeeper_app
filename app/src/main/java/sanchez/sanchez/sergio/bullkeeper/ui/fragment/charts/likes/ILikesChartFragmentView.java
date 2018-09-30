package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.likes;

import com.github.mikephil.charting.data.BarEntry;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;

/**
 * Likes Chart Fragment View
 */
public interface ILikesChartFragmentView extends ISupportView {

    /**
     * On Likes Results Loaded
     * @param entries
     */
    void onLikesResultsLoaded(final List<BarEntry> entries);

}
