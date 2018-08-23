package sanchez.sanchez.sergio.masom_app.ui.fragment.charts.sentiment;

import com.github.mikephil.charting.data.PieEntry;
import java.util.List;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

/**
 * Sentiment Analysis Fragment View
 */
public interface ISentimentAnalysisFragmentView extends ISupportView {

    /**
     * On Sentiment Results Loaded
     * @param entries
     */
    void onSentimentResultsLoaded(final List<PieEntry> entries);

}
