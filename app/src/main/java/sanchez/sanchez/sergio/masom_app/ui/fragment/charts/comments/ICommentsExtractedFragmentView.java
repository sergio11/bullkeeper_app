package sanchez.sanchez.sergio.masom_app.ui.fragment.charts.comments;

import com.github.mikephil.charting.data.BarEntry;
import java.util.List;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

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
