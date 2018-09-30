package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.relations;

import com.github.mikephil.charting.data.PieEntry;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;

/**
 * Relations Fragment View
 */
public interface IRelationsFragmentView extends ISupportView {

    /**
     * On Relations Loaded
     * @param entries
     */
    void onRelationsLoaded(final List<PieEntry> entries);

}
