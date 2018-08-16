package sanchez.sanchez.sergio.masom_app.ui.fragment.importantalerts;

import android.support.annotation.NonNull;

import net.grandcentrix.thirtyinch.TiPresenter;

import javax.inject.Inject;

/**
 * Important Alerts Fragment Presenter
 */
public final class ImportantAlertsFragmentPresenter extends TiPresenter<IImportantAlertsFragmentView> {

    @Inject
    public ImportantAlertsFragmentPresenter(){}


    @Override
    protected void onAttachView(@NonNull IImportantAlertsFragmentView view) {
        super.onAttachView(view);

    }
}
