package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.comments;

import com.github.mikephil.charting.data.BarEntry;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;

/**
 * Comments Extracted Fragment View
 */
public interface ICommentsExtractedFragmentView extends ISupportView {

    /**
     * On Comments Stats Loaded
     * @param entries
     */
    void onCommentsStatsLoaded(final List<BarEntry> entries);


}
