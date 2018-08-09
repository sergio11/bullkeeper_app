package sanchez.sanchez.sergio.masom_app.ui.fragment.alertdetail;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;

import sanchez.sanchez.sergio.domain.models.AlertEntity;

/**
 * Home Presenter
 */
public final class AlertDetailFragmentPresenter extends TiPresenter<IAlertDetailView> {

    @Inject
    public AlertDetailFragmentPresenter(){}


    @Override
    protected void onAttachView(@NonNull IAlertDetailView view) {
        super.onAttachView(view);

        view.onAlertInfoLoaded(new AlertEntity());

    }
}
