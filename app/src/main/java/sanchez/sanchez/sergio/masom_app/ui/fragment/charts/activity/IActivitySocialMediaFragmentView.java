package sanchez.sanchez.sergio.masom_app.ui.fragment.charts.activity;


import com.github.mikephil.charting.data.PieEntry;
import java.util.List;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

/**
 * Activity Social Media
 */
public interface IActivitySocialMediaFragmentView extends ISupportView {

    /**
     * On Social Media Activity Loaded
     * @param entries
     */
    void onSocialMediaActivityLoaded(final List<PieEntry> entries);

}
