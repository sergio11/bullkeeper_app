package sanchez.sanchez.sergio.bullkeeper.ui.activity.appdetail;

import android.os.Bundle;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;

/**
 * App Detail Presenter
 */
public final class AppDetailPresenter extends SupportPresenter<IAppDetailView> {

    @Inject
    public AppDetailPresenter() {
        super();
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

    }

}
