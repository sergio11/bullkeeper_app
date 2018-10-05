package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.likes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.data.BarEntry;
import net.grandcentrix.thirtyinch.TiPresenter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Likes Chart Fragment
 */
public final class LikesChartFragmentPresenter extends TiPresenter<ILikesChartFragmentView> {

    private final Context context;

    @Inject
    public LikesChartFragmentPresenter(final Context context){
        this.context = context;
    }

    @Override
    protected void onAttachView(@NonNull ILikesChartFragmentView view) {
        super.onAttachView(view);

        List<BarEntry> entries = new ArrayList<>();

        final BarEntry instagramBarEntry = new BarEntry(0f, 5f);
        instagramBarEntry.setIcon(ContextCompat.getDrawable(context, R.drawable.likes_instagram));
        final BarEntry facebookBarEntry = new BarEntry(1f, 10f);
        facebookBarEntry.setIcon(ContextCompat.getDrawable(context, R.drawable.likes_facebook));

        final BarEntry youtubeBarEntry = new BarEntry(2f, 2f);
        youtubeBarEntry.setIcon(ContextCompat.getDrawable(context, R.drawable.likes_youtube));

        entries.add(instagramBarEntry);
        entries.add(facebookBarEntry);
        entries.add(youtubeBarEntry);

        view.onDataAvaliable(entries);

    }
}
