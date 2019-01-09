package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.geofences.DeleteGeofenceByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.SaveGeofenceInteract;

/**
 * Save Geofence Presenter
 */
public final class SaveGeofencePresenter extends SupportPresenter<ISaveGeofenceView> {

    /**
     * Delete Geofence By Id Interact
     */
    private final DeleteGeofenceByIdInteract deleteGeofenceByIdInteract;

    /**
     * Save Geofence Interact
     */
    private final SaveGeofenceInteract saveGeofenceInteract;

    /**
     * Save Geofence Presenter
     * @param deleteGeofenceByIdInteract
     * @param saveGeofenceInteract
     */
    @Inject
    public SaveGeofencePresenter(final DeleteGeofenceByIdInteract deleteGeofenceByIdInteract,
                                 final SaveGeofenceInteract saveGeofenceInteract) {
        this.deleteGeofenceByIdInteract = deleteGeofenceByIdInteract;
        this.saveGeofenceInteract = saveGeofenceInteract;
    }

    /**
     * On Create
     */
    @Override
    protected void onCreate() {
        super.onCreate();
    }

    /**
     * Delete Geofence By Id
     * @param kid
     * @param id
     */
    public void deleteGeofenceById(final String kid, final String id){
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");
    }

    /**
     * Delete Geofence By Id Observable
     */
    public class DeleteGeofenceByIdObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onGeofenceDeleted();
            }

        }
    }


}
