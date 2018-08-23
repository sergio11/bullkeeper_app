package sanchez.sanchez.sergio.masom_app.ui.fragment.charts.comments;

import android.support.annotation.NonNull;
import com.github.mikephil.charting.data.BarEntry;
import net.grandcentrix.thirtyinch.TiPresenter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Comments Extracted Fragment Presenter
 */
public final class CommentsExtractedFragmentPresenter extends TiPresenter<ICommentsExtractedFragmentView> {

    @Inject
    public CommentsExtractedFragmentPresenter(){}


    @Override
    protected void onAttachView(@NonNull ICommentsExtractedFragmentView view) {
        super.onAttachView(view);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 6f));
        entries.add(new BarEntry(1f, 2f));
        entries.add(new BarEntry(2f, 9f));

        view.onCommentsStatsLoaded(entries);

    }
}
