package sanchez.sanchez.sergio.masom_app.ui.fragment.charts.activity;

import android.support.annotation.NonNull;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Four Dimensions Fragment Presenter
 */
public final class ActivitySocialMediaFragmentPresenter extends TiPresenter<IActivitySocialMediaFragmentView> {

    @Inject
    public ActivitySocialMediaFragmentPresenter(){}


    @Override
    protected void onAttachView(@NonNull IActivitySocialMediaFragmentView view) {
        super.onAttachView(view);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(50f, "Facebook"));
        entries.add(new PieEntry(25f, "Instagram"));
        entries.add(new PieEntry(25f, "Youtube"));

        view.onSocialMediaActivityLoaded(entries);

    }
}
