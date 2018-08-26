package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.relations;

import android.support.annotation.NonNull;
import com.github.mikephil.charting.data.PieEntry;
import net.grandcentrix.thirtyinch.TiPresenter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;

/**
 * Relations Fragment Presenter
 */
public final class RelationsFragmentPresenter extends
        TiPresenter<IRelationsFragmentView> {

    @Inject
    public RelationsFragmentPresenter(){}

    @Override
    protected void onAttachView(@NonNull IRelationsFragmentView view) {
        super.onAttachView(view);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(25f, "Marta Muñoz"));
        entries.add(new PieEntry(25f, "David Perez"));
        entries.add(new PieEntry(25f, "Sergio Martín"));
        entries.add(new PieEntry(25f, "Pedro Sánchez"));

        view.onRelationsLoaded(entries);

    }
}
