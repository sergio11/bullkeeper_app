package sanchez.sanchez.sergio.bullkeeper.ui.fragment.terminaldetail;

import android.os.Bundle;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.alerts.DeleteAlertOfSonInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetAlertDetailInteract;
import sanchez.sanchez.sergio.domain.models.AlertEntity;

/**
 * Terminal Detail Presenter
 */
public final class TerminalDetailFragmentPresenter extends SupportPresenter<ITerminalDetailView> {

    /**
     *
     */
    @Inject
    public TerminalDetailFragmentPresenter(){
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_alert_detail);

        final String sonId = args.getString(TerminalDetailActivityMvpFragment.SON_ID_ARG);
        final String alertId = args.getString(TerminalDetailActivityMvpFragment.ALERT_ID_ARG);

    }


}
