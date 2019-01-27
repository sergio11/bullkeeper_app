package sanchez.sanchez.sergio.bullkeeper.ui.fragment.geofencealerts;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.geofences.DeleteGeofenceAlertsInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.GetGeofencesAlertsInteract;
import sanchez.sanchez.sergio.domain.models.GeofenceAlertEntity;

/**
 * Geofence Alerts List Fragment Presenter
 */
public final class GeofenceAlertsListFragmentPresenter
        extends SupportLCEPresenter<IGeofenceAlertsListFragmentView> {

    /**
     * Args
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    public static final String GEOFENCE_IDENTITY_ARG = "GEOFENCE_IDENTITY_ARG";


    /**
     * Get Geofences Alerts Interact
     */
    private final GetGeofencesAlertsInteract getGeofencesAlertsInteract;

    /**
     * Delete Geofence Alerts Interact
     */
    private final DeleteGeofenceAlertsInteract deleteGeofenceAlertsInteract;

    /**
     *
     * @param getGeofencesAlertsInteract
     * @param deleteGeofenceAlertsInteract
     */
    @Inject
    public GeofenceAlertsListFragmentPresenter(
            final GetGeofencesAlertsInteract getGeofencesAlertsInteract,
            final DeleteGeofenceAlertsInteract deleteGeofenceAlertsInteract
    ){
        this.getGeofencesAlertsInteract = getGeofencesAlertsInteract;
        this.deleteGeofenceAlertsInteract = deleteGeofenceAlertsInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() { throw  new IllegalStateException("Args can not be empty"); }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KID_IDENTITY_ARG), "You must provide a kid identity value");
        Preconditions.checkState(args.containsKey(GEOFENCE_IDENTITY_ARG), "You must provide a geofence identity value");

        final String kid = args.getString(KID_IDENTITY_ARG);
        final String geofence = args.getString(GEOFENCE_IDENTITY_ARG);

        if (isViewAttached() && getView() != null)
            getView().onShowLoading();

        getGeofencesAlertsInteract.execute(new GetGeofenceAlertsObservable(
                GetGeofencesAlertsInteract.GetGeofencesAlertsApiErrors.class),
                GetGeofencesAlertsInteract.Params.create(kid, geofence));

    }

    /**
     * Delete All
     * @param kid
     * @param geofence
     */
    public void deleteAll(final String kid, final String geofence) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(geofence, "Geofence can not be null");
        Preconditions.checkState(!geofence.isEmpty(), "Geofence can not be empty");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        deleteGeofenceAlertsInteract.execute(new DeleteGeofenceAlertsObservable(),
                DeleteGeofenceAlertsInteract.Params.create(kid, geofence));

    }

    /**
     * Get Geofence Alerts Observable
     */
    public class GetGeofenceAlertsObservable extends CommandCallBackWrapper<List<GeofenceAlertEntity>,
            GetGeofencesAlertsInteract.GetGeofencesAlertsApiErrors.IGetGeofencesAlertsApiErrorsVisitor,
            GetGeofencesAlertsInteract.GetGeofencesAlertsApiErrors>
            implements GetGeofencesAlertsInteract.GetGeofencesAlertsApiErrors.IGetGeofencesAlertsApiErrorsVisitor {


        /**
         *
         * @param apiErrors
         */
        public GetGeofenceAlertsObservable(Class<GetGeofencesAlertsInteract.GetGeofencesAlertsApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final List<GeofenceAlertEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }
        }

        /**
         * Visit No Geofences Alerts Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoGeofencesAlertsFound(final GetGeofencesAlertsInteract.GetGeofencesAlertsApiErrors.IGetGeofencesAlertsApiErrorsVisitor apiErrorsVisitor) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }

    /**
     * Delete Geofence Alerts
     */
    public class DeleteGeofenceAlertsObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().showNoticeDialog(R.string.geofence_alerts_deleted_successfully);
                getView().onNoDataFound();
            }
        }
    }

}
