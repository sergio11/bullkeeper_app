package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts;

import android.support.annotation.NonNull;
import com.github.mikephil.charting.data.PieEntry;
import net.grandcentrix.thirtyinch.TiPresenter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;

/**
 * System Alerts Fragment Presenter
 */
public final class SystemAlertsFragmentPresenter extends
        TiPresenter<ISystemAlertsFragmentView> {

    @Inject
    public SystemAlertsFragmentPresenter(){}

    @Override
    protected void onAttachView(@NonNull ISystemAlertsFragmentView view) {
        super.onAttachView(view);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(10f, AlertLevelEnum.INFO.name()));
        entries.add(new PieEntry(40f, AlertLevelEnum.SUCCESS.name()));
        entries.add(new PieEntry(15f, AlertLevelEnum.DANGER.name()));
        entries.add(new PieEntry(35f, AlertLevelEnum.WARNING.name()));
        view.onSystemAlertsLoaded(entries);

    }
}
