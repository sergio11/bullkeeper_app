package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertdetail;

import android.os.Bundle;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPresenter;
/**
 * Alert Detail Presenter
 */
public final class AlertDetailPresenter extends SupportPresenter<IAlertDetailView> {

    @Inject
    public AlertDetailPresenter() {
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
