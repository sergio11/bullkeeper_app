package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save;

import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.geofences.DeleteGeofenceByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.GetGeofenceByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.SaveGeofenceInteract;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceTransitionTypeEnum;

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
     * Get Geofence By Id Interact
     */
    private final GetGeofenceByIdInteract getGeofenceByIdInteract;

    /**
     * Save Geofence Presenter
     * @param deleteGeofenceByIdInteract
     * @param saveGeofenceInteract
     * @param getGeofenceByIdInteract
     */
    @Inject
    public SaveGeofencePresenter(final DeleteGeofenceByIdInteract deleteGeofenceByIdInteract,
                                 final SaveGeofenceInteract saveGeofenceInteract,
                                 final GetGeofenceByIdInteract getGeofenceByIdInteract) {
        this.deleteGeofenceByIdInteract = deleteGeofenceByIdInteract;
        this.saveGeofenceInteract = saveGeofenceInteract;
        this.getGeofenceByIdInteract = getGeofenceByIdInteract;
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

        deleteGeofenceByIdInteract.execute(new DeleteGeofenceByIdObservable(),
                DeleteGeofenceByIdInteract.Params.create(kid, id));
    }

    /**
     * Get Geofence ById
     * @param kid
     * @param id
     */
    public void getGeofenceById(final String kid, final String id){
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");

        if (isViewAttached() && getView() != null) {
            getView().showProgressDialog(R.string.generic_loading_text);
        }

        getGeofenceByIdInteract.execute(new GetGeofenceByIdObservable(),
                GetGeofenceByIdInteract.Params.create(kid, id));

    }

    /**
     *
     * @param name
     * @param transitionTypeEnum
     * @param address
     * @param lat
     * @param log
     * @param radius
     * @param type
     * @param kid
     * @param identity
     */
    public void saveGeofence(final String name, final GeofenceTransitionTypeEnum transitionTypeEnum,
                             final String address, final double lat, double log, float radius,
                             GeofenceTransitionTypeEnum type, final  String kid, final String identity) {
        Preconditions.checkNotNull(name, "Name can not be null");
        Preconditions.checkState(!name.isEmpty(), "Name can not be empty");
        Preconditions.checkNotNull(transitionTypeEnum, "Transition Type Enum");
        Preconditions.checkNotNull(address, "Address can not be null");
        Preconditions.checkState(!address.isEmpty(), "Address can not be empty");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        saveGeofenceInteract.execute(new SaveGeofenceObservable(),
                SaveGeofenceInteract.Params.create(identity, name, lat, log,
                        radius, type.name(), kid));
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

    /**
     * Get Geofence By Id Observable
     */
    public class GetGeofenceByIdObservable extends BasicCommandCallBackWrapper<GeofenceEntity> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final GeofenceEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onGeofenceLoaded(response);
            }

        }
    }

    /**
     * Save Geofence Observable
     */
    public class SaveGeofenceObservable extends BasicCommandCallBackWrapper<GeofenceEntity> {

        /**
         * On Success
         * @param geofenceEntity
         */
        @Override
        protected void onSuccess(GeofenceEntity geofenceEntity) {
            Preconditions.checkNotNull(geofenceEntity, "Geofence Entity can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onGeofenceLoaded(geofenceEntity);
            }

        }
    }


}
