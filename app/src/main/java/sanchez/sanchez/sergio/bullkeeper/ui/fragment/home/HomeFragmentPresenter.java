package sanchez.sanchez.sergio.bullkeeper.ui.fragment.home;

import android.support.annotation.NonNull;

import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;

/**
 * Home Presenter
 */
public final class HomeFragmentPresenter extends TiPresenter<IHomeView> {

    @Inject
    public HomeFragmentPresenter(){}


    @Override
    protected void onAttachView(@NonNull IHomeView view) {
        super.onAttachView(view);

        final List<AlertEntity> lastAlerts = Arrays.asList(new AlertEntity(AlertLevelEnum.INFO), new AlertEntity(AlertLevelEnum.SUCCESS),
                new AlertEntity(AlertLevelEnum.DANGER), new AlertEntity(AlertLevelEnum.WARNING), new AlertEntity(AlertLevelEnum.DANGER), new AlertEntity(AlertLevelEnum.SUCCESS),
                new AlertEntity(AlertLevelEnum.WARNING), new AlertEntity(), new AlertEntity(), new AlertEntity(),
                new AlertEntity(AlertLevelEnum.DANGER), new AlertEntity(AlertLevelEnum.WARNING), new AlertEntity(AlertLevelEnum.INFO), new AlertEntity(AlertLevelEnum.SUCCESS),
                new AlertEntity(), new AlertEntity(AlertLevelEnum.DANGER), new AlertEntity(), new AlertEntity());

        view.onLastAlertsLoaded(lastAlerts);

    }
}
