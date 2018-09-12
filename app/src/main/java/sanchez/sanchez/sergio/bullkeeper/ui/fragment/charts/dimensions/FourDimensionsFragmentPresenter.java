package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.dimensions;

import android.support.annotation.NonNull;

import com.github.mikephil.charting.data.BarEntry;

import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Four Dimensions Fragment Presenter
 */
public final class FourDimensionsFragmentPresenter extends TiPresenter<IFourDimensionsFragmentView> {

    @Inject
    public FourDimensionsFragmentPresenter(){}


    @Override
    protected void onAttachView(@NonNull IFourDimensionsFragmentView view) {
        super.onAttachView(view);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 1f));
        entries.add(new BarEntry(1f, 3f));
        entries.add(new BarEntry(2f, 6f));
        entries.add(new BarEntry(3f, 7f));

        view.onDimensionsDataLoaded(entries);

    }
}
