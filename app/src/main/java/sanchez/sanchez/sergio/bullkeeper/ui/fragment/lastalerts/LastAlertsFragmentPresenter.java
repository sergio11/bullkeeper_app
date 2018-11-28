package sanchez.sanchez.sergio.bullkeeper.ui.fragment.lastalerts;


import android.os.Bundle;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.alerts.DeleteAlertOfKidInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetSelfLastAlertsInteract;
import sanchez.sanchez.sergio.domain.models.AlertsPageEntity;
import timber.log.Timber;

/**
 * Last Alerts Fragment Presenter
 */
public final class LastAlertsFragmentPresenter extends SupportLCEPresenter<ILastAlertsView> {

    /**
     * Get Self Last Alerts Interact
     */
    private final GetSelfLastAlertsInteract getSelfLastAlertsInteract;

    /**
     * Delete Alert Of Son Interact
     */
    private final DeleteAlertOfKidInteract deleteAlertOfKidInteract;

    /**
     * @param getSelfLastAlertsInteract
     */
    @Inject
    public LastAlertsFragmentPresenter(final GetSelfLastAlertsInteract getSelfLastAlertsInteract,
                                       final DeleteAlertOfKidInteract deleteAlertOfKidInteract) {
        this.getSelfLastAlertsInteract = getSelfLastAlertsInteract;
        this.deleteAlertOfKidInteract = deleteAlertOfKidInteract;
    }


    /**
     * Load Data
     */
    @Override
    public void loadData() {

        // Execute Get Self Children
        getSelfLastAlertsInteract.execute(new LoadLastAlertsObserver(GetSelfLastAlertsInteract.GetSelfLastAlertsApiErrors.class), null);
    }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        loadData();
    }

    /**
     * Delete Alert Of Son
     * @param sonIdentity
     * @param alertIdentity
     */
    public void deleteAlertOfSon(final String sonIdentity, final String alertIdentity) {
        Preconditions.checkState(!sonIdentity.isEmpty(), "Son Identity can not be null");
        Preconditions.checkState(!alertIdentity.isEmpty(), "Alert Identity can not be null");
        Timber.d("Delete Alert Of Son");
        deleteAlertOfKidInteract.execute(new DeleteAlertOfSonObservable(),
                DeleteAlertOfKidInteract.Params.create(sonIdentity, alertIdentity));
    }


    /**
     * Load Last Alerts Observer
     */
    public class LoadLastAlertsObserver extends CommandCallBackWrapper<AlertsPageEntity, GetSelfLastAlertsInteract.GetSelfLastAlertsApiErrors.IGetSelfLastAlertsApiErrorVisitor,
            GetSelfLastAlertsInteract.GetSelfLastAlertsApiErrors> implements GetSelfLastAlertsInteract.GetSelfLastAlertsApiErrors.IGetSelfLastAlertsApiErrorVisitor {

        /**
         * Load Last Alerts Observer
         * @param apiErrors
         */
        public LoadLastAlertsObserver(Class<GetSelfLastAlertsInteract.GetSelfLastAlertsApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param alertsPageEntity
         */
        @Override
        protected void onSuccess(final AlertsPageEntity alertsPageEntity) {
            if (isViewAttached() && getView() != null)
               if(alertsPageEntity != null && alertsPageEntity.getAlerts() != null
                        && !alertsPageEntity.getAlerts().isEmpty())
                    getView().onDataLoaded(alertsPageEntity.getAlerts());
                else
                    getView().onNoDataFound();
        }

        /**
         * Visit No Alerts Founded
         * @param error
         */
        @Override
        public void visitNoAlertsFounded(final GetSelfLastAlertsInteract.GetSelfLastAlertsApiErrors error) {
            if(isViewAttached() && getView() != null)
                getView().onNoDataFound();
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
            if(isViewAttached() && getView() != null)
                getView().onAlertDeleted();

        }
    }


}
