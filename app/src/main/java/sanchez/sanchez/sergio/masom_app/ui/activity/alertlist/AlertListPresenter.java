package sanchez.sanchez.sergio.masom_app.ui.activity.alertlist;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;

/**
 * Alert Detail Presenter
 */
public final class AlertListPresenter extends TiPresenter<IAlertListView> {


    @Inject
    public AlertListPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IAlertListView view) {
        super.onAttachView(view);

        final List<AlertEntity> alertsList = Arrays.asList(new AlertEntity(AlertLevelEnum.INFO), new AlertEntity(AlertLevelEnum.SUCCESS),
                new AlertEntity(AlertLevelEnum.DANGER), new AlertEntity(AlertLevelEnum.WARNING), new AlertEntity(AlertLevelEnum.DANGER), new AlertEntity(AlertLevelEnum.SUCCESS),
                new AlertEntity(AlertLevelEnum.WARNING), new AlertEntity(), new AlertEntity(), new AlertEntity(),
                new AlertEntity(AlertLevelEnum.DANGER), new AlertEntity(AlertLevelEnum.WARNING), new AlertEntity(AlertLevelEnum.INFO), new AlertEntity(AlertLevelEnum.SUCCESS),
                new AlertEntity(), new AlertEntity(AlertLevelEnum.DANGER), new AlertEntity(), new AlertEntity());

        view.onAlertsLoaded(alertsList);

    }
}
