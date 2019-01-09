package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.list;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.geofences.DeleteAllGeofencesBykidInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.DeleteGeofenceByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.GetAllGeofencesByKidInteract;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;

/**
 * Geofences List Presenter
 */
public final class GeofencesListPresenter
        extends SupportLCEPresenter<IGeofencesListView> {

    /**
     * Args
     */
    public final static String KID_ID_ARG = "KID_ID_ARG";

    /**
     * Get All Geofences By Kid Interact
     */
    private final GetAllGeofencesByKidInteract getAllGeofencesByKidInteract;

    /**
     * Delete Geofence By Id Interact
     */
    private final DeleteGeofenceByIdInteract deleteGeofenceByIdInteract;

    /**
     * Delete All Geofences By Kid Interact
     */
    private final DeleteAllGeofencesBykidInteract deleteAllGeofencesBykidInteract;

    /**
     *
     * @param getAllGeofencesByKidInteract
     * @param deleteGeofenceByIdInteract
     * @param deleteAllGeofencesBykidInteract
     */
    @Inject
    public GeofencesListPresenter(
            final GetAllGeofencesByKidInteract getAllGeofencesByKidInteract,
            final DeleteGeofenceByIdInteract deleteGeofenceByIdInteract,
            final DeleteAllGeofencesBykidInteract deleteAllGeofencesBykidInteract
    ) {
        this.getAllGeofencesByKidInteract = getAllGeofencesByKidInteract;
        this.deleteAllGeofencesBykidInteract = deleteAllGeofencesBykidInteract;
        this.deleteGeofenceByIdInteract = deleteGeofenceByIdInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {
        Preconditions.checkState(!args.getString(KID_ID_ARG, "").isEmpty(),
                "Kid id can not be empty");

        getAllGeofencesByKidInteract.execute(new GetAllGeofencesByKidObservable(GetAllGeofencesByKidInteract.GetAllGeofencesByIdApiErrors.class),
                GetAllGeofencesByKidInteract.Params.create(args.getString(KID_ID_ARG)));

    }

    /**
     * Load Data
     */
    @Override
    public void loadData(final Bundle args) { loadData(); }

    /**
     * Delete By Id
     * @param identity
     */
    public void deleteById(final String kid, final String identity) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");

        deleteGeofenceByIdInteract.execute(new DeleteGeofenceByIdObservable(),
                DeleteGeofenceByIdInteract.Params.create(kid, identity));

    }

    /**
     * Delete All By Kid
     * @param kid
     */
    public void deleteAllByKid(final String kid) {
        Preconditions.checkNotNull(kid, "kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "kid can not be empty");

        deleteAllGeofencesBykidInteract.execute(new DeleteAllGeofenceByKidObservable(),
                DeleteAllGeofencesBykidInteract.Params.create(kid));
    }

    /**
     * Get All Geofences By Kid  Observable
     */
    public class GetAllGeofencesByKidObservable extends CommandCallBackWrapper<List<GeofenceEntity>,
            GetAllGeofencesByKidInteract.GetAllGeofencesByIdApiErrors.IGetAllGeofencesByIdApiErrorsVisitor,
            GetAllGeofencesByKidInteract.GetAllGeofencesByIdApiErrors>
            implements GetAllGeofencesByKidInteract.GetAllGeofencesByIdApiErrors.IGetAllGeofencesByIdApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetAllGeofencesByKidObservable(Class<GetAllGeofencesByKidInteract.GetAllGeofencesByIdApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Sucess
         * @param response
         */
        @Override
        protected void onSuccess(final List<GeofenceEntity> response) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }
        }

        /**
         * Visit No Geofences Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoGeofencesFound(GetAllGeofencesByKidInteract.GetAllGeofencesByIdApiErrors.IGetAllGeofencesByIdApiErrorsVisitor apiErrorsVisitor) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }


    /**
     * Delete All Geofence By kid
     */
    public class DeleteAllGeofenceByKidObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAllGeofencesDeleted();
            }
        }
    }

    /**
     * Delete Geofence By Id
     */
    public class DeleteGeofenceByIdObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onGeofenceDeleted();
            }
        }
    }


}
