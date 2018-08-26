package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertdetail;

import android.support.annotation.NonNull;

import net.grandcentrix.thirtyinch.TiPresenter;

import javax.inject.Inject;

/**
 * Alert Detail Presenter
 */
public final class AlertDetailPresenter extends TiPresenter<IAlertDetailView> {


    @Inject
    public AlertDetailPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IAlertDetailView view) {
        super.onAttachView(view);

        view.showLongMessage("Hello World!!!");

    }
}
