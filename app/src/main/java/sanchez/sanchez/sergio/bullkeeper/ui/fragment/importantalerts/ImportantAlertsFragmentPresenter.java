package sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts;

import android.support.annotation.NonNull;

import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;

/**
 * Important Alerts Fragment Presenter
 */
public final class ImportantAlertsFragmentPresenter extends TiPresenter<IImportantAlertsFragmentView> {

    @Inject
    public ImportantAlertsFragmentPresenter(){}


    @Override
    protected void onAttachView(@NonNull IImportantAlertsFragmentView view) {
        super.onAttachView(view);

        final List<AlertEntity> alertEntityList = Arrays.asList(new AlertEntity(AlertLevelEnum.DANGER),
                new AlertEntity(AlertLevelEnum.DANGER), new AlertEntity(AlertLevelEnum.DANGER),
                new AlertEntity(AlertLevelEnum.DANGER), new AlertEntity(AlertLevelEnum.DANGER),
                new AlertEntity(AlertLevelEnum.DANGER), new AlertEntity(AlertLevelEnum.DANGER));


        view.showShortMessage("On Alerts Loaded");
        view.onAlertsLoaded(alertEntityList);

    }
}
