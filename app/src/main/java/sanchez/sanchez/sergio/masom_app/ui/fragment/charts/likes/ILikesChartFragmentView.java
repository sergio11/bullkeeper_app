package sanchez.sanchez.sergio.masom_app.ui.fragment.charts.likes;

import com.github.mikephil.charting.data.BarEntry;
import java.util.List;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

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
