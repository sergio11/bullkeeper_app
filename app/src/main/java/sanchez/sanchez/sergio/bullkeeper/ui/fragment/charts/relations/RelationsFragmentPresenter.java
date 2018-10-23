package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.relations;

import android.support.annotation.NonNull;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;

/**
 * Relations Fragment Presenter
 */
public final class RelationsFragmentPresenter extends
        SupportPresenter<IRelationsFragmentView> {

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


    }
}
