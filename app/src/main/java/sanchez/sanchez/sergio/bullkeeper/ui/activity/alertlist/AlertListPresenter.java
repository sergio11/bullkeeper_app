package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist;


import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.alerts.ClearAlertsBySonInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.ClearSelfAlertsInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.DeleteAlertOfSonInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetAlertsBySonInteract;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetSelfAlertsInteract;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import timber.log.Timber;

import static sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist.AlertListMvpActivity.ALERT_LEVEL_ARG;
import static sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist.AlertListMvpActivity.SON_IDENTITY_ARG;

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
     * Get Alerts By Son
     */
    private final GetAlertsBySonInteract getAlertsBySonInteract;

    /**
     * Clear Alerts By Son
     */
    private final ClearAlertsBySonInteract clearAlertsBySonInteract;

    /**
     *
     * @param getSelfAlertsInteract
     */
    @Inject
    public AlertListPresenter(final GetSelfAlertsInteract getSelfAlertsInteract,
                              final DeleteAlertOfSonInteract deleteAlertOfSonInteract,
                              final ClearSelfAlertsInteract clearSelfAlertsInteract,
                              final GetAlertsBySonInteract getAlertsBySonInteract,
                              final ClearAlertsBySonInteract clearAlertsBySonInteract) {
        super();
        this.getSelfAlertsInteract = getSelfAlertsInteract;
        this.deleteAlertOfSonInteract = deleteAlertOfSonInteract;
        this.clearSelfAlertsInteract = clearSelfAlertsInteract;
        this.getAlertsBySonInteract = getAlertsBySonInteract;
        this.clearAlertsBySonInteract = clearAlertsBySonInteract;
    }

    /**
     *
     */
    @Override
    public void onInit() {
        super.onInit();
        getSelfAlertsInteract.attachDisposablesTo(compositeDisposable);
        deleteAlertOfSonInteract.attachDisposablesTo(compositeDisposable);
        getAlertsBySonInteract.attachDisposablesTo(compositeDisposable);
        clearAlertsBySonInteract.attachDisposablesTo(compositeDisposable);
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {

        if (isViewAttached() && getView() != null) {
            getView().showProgressDialog(R.string.loading_alerts_please_wait);
        }

        // Load Alerts for Son and level
        if(args.containsKey(SON_IDENTITY_ARG) && args.containsKey(ALERT_LEVEL_ARG)) {
            final String sonIdentity = args.getString(SON_IDENTITY_ARG);
            final AlertLevelEnum alertLevelEnum = (AlertLevelEnum) args.getSerializable(ALERT_LEVEL_ARG);
            Timber.d("Load Alerts for -> %s and level -> %s", sonIdentity, alertLevelEnum.name());

        } else if(args.containsKey(SON_IDENTITY_ARG)) {
            final String sonIdentity = args.getString(SON_IDENTITY_ARG);
            Timber.d("Load alerts for child -> %s", sonIdentity);
            // Get Alerts By Son
            getAlertsBySonInteract.execute(new GetAlertsBySonObservable(GetAlertsBySonInteract.GetAlertsBySonApiErrors.class),
                    GetAlertsBySonInteract.Params.create(sonIdentity));

        } else if (args.containsKey(ALERT_LEVEL_ARG)){
            final AlertLevelEnum alertLevelEnum = (AlertLevelEnum) args.getSerializable(ALERT_LEVEL_ARG);
            Timber.d("Load Alerts for level -> %s", alertLevelEnum.name());

        } else {
            getSelfAlertsInteract.execute(new GetSelfAlertsObservable(GetSelfAlertsInteract.GetSelfAlertsApiErrors.class), null);
        }

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
     * Clear Alerts By Son
     * @param identity
     */
    public void clearAlertsBySon(final String identity) {
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");

        clearAlertsBySonInteract.execute(new ClearAlertsBySonObservable(), ClearAlertsBySonInteract.Params.create(identity));
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


    /**
     * Clear Self Alerts Observable
     */
    public class GetAlertsBySonObservable extends CommandCallBackWrapper<List<AlertEntity>,
            GetAlertsBySonInteract.GetAlertsBySonApiErrors.IGetAlertsBySonErrorVisitor,
            GetAlertsBySonInteract.GetAlertsBySonApiErrors>
        implements GetAlertsBySonInteract.GetAlertsBySonApiErrors.IGetAlertsBySonErrorVisitor{


        public GetAlertsBySonObservable(Class<GetAlertsBySonInteract.GetAlertsBySonApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param alertEntities
         */
        @Override
        protected void onSuccess(List<AlertEntity> alertEntities) {

            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(alertEntities);
            }

        }

        @Override
        public void visitNoAlertsBySonFounded(GetAlertsBySonInteract.GetAlertsBySonApiErrors apiErrors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }

    /**
     * Clear Alerts By Son Interact
     */
    public class ClearAlertsBySonObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAlertsCleared();
            }
        }
    }



}
