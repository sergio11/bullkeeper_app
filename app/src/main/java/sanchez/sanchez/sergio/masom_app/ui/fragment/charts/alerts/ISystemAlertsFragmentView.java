package sanchez.sanchez.sergio.masom_app.ui.fragment.charts.alerts;

import com.github.mikephil.charting.data.PieEntry;
import java.util.List;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

/**
 * System Alerts Fragment View
 */
public interface ISystemAlertsFragmentView extends ISupportView {

    /**
     * On System Alerts Loaded
     * @param entries
     */
    void onSystemAlertsLoaded(final List<PieEntry> entries);

}
