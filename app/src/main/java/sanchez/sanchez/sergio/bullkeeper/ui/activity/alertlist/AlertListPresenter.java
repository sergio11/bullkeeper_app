package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.alerts.ClearSelfAlertsInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.DeleteAlertOfSonInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetSelfAlertsInteract;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import timber.log.Timber;

/**
 * Alert List Presenter
 */
public final class AlertListPresenter extends SupportLCEPresenter<IAlertListView> {

    /**
     * Get Self Alerts Interact
     */
    private final GetSelfAlertsInteract getSelfAlertsInteract;

    /**
     * Delete Alert Of Son Interact
     */
    private final DeleteAlertOfSonInteract deleteAlertOfSonInteract;

    /**
     * Clear Self Alerts
     */
    private final ClearSelfAlertsInteract clearSelfAlertsInteract;

    /**
     *
     * @param getSelfAlertsInteract
     */
    @Inject
    public AlertListPresenter(final GetSelfAlertsInteract getSelfAlertsInteract,
                              final DeleteAlertOfSonInteract deleteAlertOfSonInteract,
                              final ClearSelfAlertsInteract clearSelfAlertsInteract) {
        super();
        this.getSelfAlertsInteract = getSelfAlertsInteract;
        this.deleteAlertOfSonInteract = deleteAlertOfSonInteract;
        this.clearSelfAlertsInteract = clearSelfAlertsInteract;
    }

    /**
     *
     */
    @Override
    public void onInit() {
        super.onInit();
        this.getSelfAlertsInteract.attachDisposablesTo(compositeDisposable);
        this.deleteAlertOfSonInteract.attachDisposablesTo(compositeDisposable);
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {

        if (isViewAttached() && getView() != null) {
            getView().showProgressDialog(R.string.loading_alerts_please_wait);
        }

        getSelfAlertsInteract.execute(new GetSelfAlertsObservable(GetSelfAlertsInteract.GetSelfAlertsApiErrors.class), null);
    }

    /**
     * Clear Alerts
     */
    public void clearAlerts(){

        if (isViewAttached() && getView() != null) {
            getView().showProgressDialog(R.string.deleting_alerts_please_wait);
        }

        clearSelfAlertsInteract.execute(new ClearSelfAlertsObservable(), null);
    }

    /**
     * Delete Alert Of Son
     * @param sonIdentity
     * @param alertIdentity
     */
    public void deleteAlertOfSon(final String sonIdentity, final String alertIdentity) {
        Preconditions.checkState(!sonIdentity.isEmpty(), "Son Identity can not be null");
        Preconditions.checkState(!alertIdentity.isEmpty(), "Alert Identity can not be null");

        deleteAlertOfSonInteract.execute(new DeleteAlertOfSonObservable(), DeleteAlertOfSonInteract.Params.create(sonIdentity, alertIdentity));
    }


    /**
     * Get Self Alerts Observable
     */
    public class GetSelfAlertsObservable extends CommandCallBackWrapper<List<AlertEntity>, GetSelfAlertsInteract.GetSelfAlertsApiErrors.IGetSelfAlertsApiErrorVisitor, GetSelfAlertsInteract.GetSelfAlertsApiErrors>
        implements GetSelfAlertsInteract.GetSelfAlertsApiErrors.IGetSelfAlertsApiErrorVisitor {


        public GetSelfAlertsObservable(Class<GetSelfAlertsInteract.GetSelfAlertsApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param alertsList
         */
        @Override
        protected void onSuccess(final List<AlertEntity> alertsList) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(alertsList);
            }
        }

        /**
         * Visit No Alerts Founded
         * @param error
         */
        @Override
        public void visitNoAlertsFounded(final GetSelfAlertsInteract.GetSelfAlertsApiErrors error) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }

    /**
     * Delete Alert Of Son Observable
     */
    public class DeleteAlertOfSonObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Timber.d("Response -> %s", response);
        }
    }

    /**
     * Clear Self Alerts Observable
     */
    public class ClearSelfAlertsObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Timber.d("Response -> %s", response);
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAlertsCleared();
            }
        }
    }






}
