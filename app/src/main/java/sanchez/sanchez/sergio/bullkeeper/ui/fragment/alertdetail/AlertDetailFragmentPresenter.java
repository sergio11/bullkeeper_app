package sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertdetail;

import android.os.Bundle;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.alerts.DeleteAlertOfKidInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetAlertDetailInteract;
import sanchez.sanchez.sergio.domain.models.AlertEntity;

/**
 * Alert Detail Presenter
 */
public final class AlertDetailFragmentPresenter extends SupportPresenter<IAlertDetailView> {

    /**
     * Get Alert Detail Interact
     */
    private final GetAlertDetailInteract getAlertDetailInteract;

    /**
     * Delete Alert Of Son Interact
     */
    private final DeleteAlertOfKidInteract deleteAlertOfKidInteract;

    /**
     *
     * @param getAlertDetailInteract
     * @param deleteAlertOfKidInteract
     */
    @Inject
    public AlertDetailFragmentPresenter(final GetAlertDetailInteract getAlertDetailInteract,
                                        final DeleteAlertOfKidInteract deleteAlertOfKidInteract){
        this.getAlertDetailInteract = getAlertDetailInteract;
        this.deleteAlertOfKidInteract = deleteAlertOfKidInteract;
    }

    /**
     *
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_alert_detail);

        final String sonId = args.getString(AlertDetailActivityMvpFragment.SON_ID_ARG);
        final String alertId = args.getString(AlertDetailActivityMvpFragment.ALERT_ID_ARG);

        getAlertDetailInteract.execute(new GetAlertDetailObserver(GetAlertDetailInteract.GetAlertDetailApiErrors.class),
                GetAlertDetailInteract.Params.create(sonId, alertId));
    }


    /**
     * Delete Alert
     */
    public void deleteAlert() {

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.deleting_alert_in_progress);

        final String sonId = args.getString(AlertDetailActivityMvpFragment.SON_ID_ARG);
        final String alertId = args.getString(AlertDetailActivityMvpFragment.ALERT_ID_ARG);

        deleteAlertOfKidInteract.execute(new DeleteAlertOfSonObserver() , DeleteAlertOfKidInteract.Params.create(sonId, alertId));

    }

    /**
     * Get Alert Detail Observer
     */
    public class GetAlertDetailObserver extends CommandCallBackWrapper<AlertEntity,
            GetAlertDetailInteract.GetAlertDetailApiErrors.IGetAlertDetailApiErrorVisitor,
            GetAlertDetailInteract.GetAlertDetailApiErrors> implements  GetAlertDetailInteract.GetAlertDetailApiErrors.IGetAlertDetailApiErrorVisitor{

        public GetAlertDetailObserver(Class<GetAlertDetailInteract.GetAlertDetailApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param alertEntity
         */
        @Override
        protected void onSuccess(AlertEntity alertEntity) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAlertInfoLoaded(alertEntity);
            }
        }

        /**
         * Visit Alert Not Found
         * @param error
         */
        @Override
        public void visitAlertNotFound(GetAlertDetailInteract.GetAlertDetailApiErrors error) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAlertNotFound();
            }
        }
    }

    /**
     * Delete Alert Of Son Interact
     */
    public class DeleteAlertOfSonObserver extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAlertDeleted();
            }
        }
    }
}
