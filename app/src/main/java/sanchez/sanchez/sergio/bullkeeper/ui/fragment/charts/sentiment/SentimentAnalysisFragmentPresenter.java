package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment;

import android.content.Context;
import android.support.annotation.NonNull;
import com.github.mikephil.charting.data.PieEntry;
import net.grandcentrix.thirtyinch.TiPresenter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Sentiment Analysis Fragment Presenter
 */
public final class SentimentAnalysisFragmentPresenter extends TiPresenter<ISentimentAnalysisFragmentView> {


    private final Context appContext;

    @Inject
    public SentimentAnalysisFragmentPresenter(final Context appContext){
        this.appContext = appContext;
    }

    @Override
    protected void onAttachView(@NonNull ISentimentAnalysisFragmentView view) {
        super.onAttachView(view);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(50f, appContext.getString(R.string.sentiment_analysis_positive)));
        entries.add(new PieEntry(25f, appContext.getString(R.string.sentiment_analysis_negative)));
        entries.add(new PieEntry(25f, appContext.getString(R.string.sentiment_analysis_neutro)));

        view.onSentimentResultsLoaded(entries);

    }
}
