package sanchez.sanchez.sergio.bullkeeper.ui.fragment.lastalerts;


import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetSelfLastAlertsInteract;
import sanchez.sanchez.sergio.domain.models.AlertsPageEntity;

/**
 * Last Alerts Fragment Presenter
 */
public final class LastAlertsFragmentPresenter extends SupportLCEPresenter<ILastAlertsView> {

    /**
     * Get Self Last Alerts Interact
     */
    private final GetSelfLastAlertsInteract getSelfLastAlertsInteract;

    /**
     * @param getSelfLastAlertsInteract
     */
    @Inject
    public LastAlertsFragmentPresenter(final GetSelfLastAlertsInteract getSelfLastAlertsInteract) {
        this.getSelfLastAlertsInteract = getSelfLastAlertsInteract;
    }

    /**
     * Init
     */
    @Override
    public void onInit() {
        super.onInit();
        this.getSelfLastAlertsInteract.attachDisposablesTo(compositeDisposable);
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


}
