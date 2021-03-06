package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.geofences.DeleteGeofenceByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.GetGeofenceByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.geofences.SaveGeofenceInteract;
import sanchez.sanchez.sergio.domain.interactor.places.SearchPlacesInteract;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceTransitionTypeEnum;
import sanchez.sanchez.sergio.domain.models.PlaceSuggestionEntity;

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
     * Search Places Interact
     */
    private final SearchPlacesInteract searchPlacesInteract;

    /**
     * Save Geofence Presenter
     * @param deleteGeofenceByIdInteract
     * @param saveGeofenceInteract
     * @param getGeofenceByIdInteract
     * @param searchPlacesInteract
     */
    @Inject
    public SaveGeofencePresenter(final DeleteGeofenceByIdInteract deleteGeofenceByIdInteract,
                                 final SaveGeofenceInteract saveGeofenceInteract,
                                 final GetGeofenceByIdInteract getGeofenceByIdInteract,
                                 final SearchPlacesInteract searchPlacesInteract) {
        this.deleteGeofenceByIdInteract = deleteGeofenceByIdInteract;
        this.saveGeofenceInteract = saveGeofenceInteract;
        this.getGeofenceByIdInteract = getGeofenceByIdInteract;
        this.searchPlacesInteract = searchPlacesInteract;
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

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

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
     * @param isEnabled
     * @param kid
     * @param identity
     */
    public void saveGeofence(final String name, final GeofenceTransitionTypeEnum transitionTypeEnum,
                             final String address, final double lat, double log, float radius,
                             GeofenceTransitionTypeEnum type, final boolean isEnabled, final  String kid, final String identity) {
        Preconditions.checkNotNull(name, "Name can not be null");
        Preconditions.checkState(!name.isEmpty(), "Name can not be empty");
        Preconditions.checkNotNull(transitionTypeEnum, "Transition Type Enum");
        Preconditions.checkNotNull(address, "Address can not be null");
        Preconditions.checkState(!address.isEmpty(), "Address can not be empty");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        saveGeofenceInteract.execute(new SaveGeofenceObservable(SaveGeofenceInteract.SaveGeofenceApiErrors.class),
                SaveGeofenceInteract.Params.create(identity, name, lat, log,
                        radius, address, type.name(), isEnabled, kid));
    }

    /**
     * Search Places
     * @param query
     * @param latitude
     * @param longitude
     */
    public void searchPlaces(final String query, final double latitude, final double longitude, final String radius) {
        Preconditions.checkNotNull(query, "Query can not be null");
        Preconditions.checkState(!query.isEmpty(), "Query can not be empty");

        searchPlacesInteract.execute(new SearchPlacesObservable(),
                SearchPlacesInteract.Params.create(latitude, longitude, query, radius));
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
     * Search Places Observable
     */
    public class SearchPlacesObservable extends BasicCommandCallBackWrapper<List<PlaceSuggestionEntity>> {

        @Override
        protected void onNetworkError() {
            super.onNetworkError();
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoSuggestedPlacesFound();
            }
        }

        @Override
        protected void onOtherException(Throwable ex) {
            super.onOtherException(ex);
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoSuggestedPlacesFound();
            }
        }

        @Override
        protected void onApiException(APIResponse response) {
            super.onApiException(response);
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoSuggestedPlacesFound();
            }
        }

        /**
         *
         * @param placesList
         */
        @Override
        protected void onSuccess(List<PlaceSuggestionEntity> placesList) {
            Preconditions.checkNotNull(placesList, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                if(!placesList.isEmpty())
                    getView().onSuggestedPlacesLoaded(placesList);
                else
                    getView().onNoSuggestedPlacesFound();
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
    public class SaveGeofenceObservable extends CommandCallBackWrapper<GeofenceEntity, SaveGeofenceInteract.SaveGeofenceApiErrors.ISaveGeofenceApiErrorVisitor,
            SaveGeofenceInteract.SaveGeofenceApiErrors> implements SaveGeofenceInteract.SaveGeofenceApiErrors.ISaveGeofenceApiErrorVisitor {


        public SaveGeofenceObservable(Class<SaveGeofenceInteract.SaveGeofenceApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param geofenceEntity
         */
        @Override
        protected void onSuccess(GeofenceEntity geofenceEntity) {
            Preconditions.checkNotNull(geofenceEntity, "Geofence Entity can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().showNoticeDialog(R.string.geofence_saved_successfully);
                getView().onGeofenceLoaded(geofenceEntity);
            }
        }

        /**
         * Visit Validation Error
         * @param apiErrors
         * @param errors
         */
        @Override
        public void visitValidationError(final SaveGeofenceInteract.SaveGeofenceApiErrors apiErrors,
                                         final LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                if(errors != null && !errors.isEmpty() && errors.containsKey("field_errors")) {
                    getView().onValidationErrors(errors.get("field_errors"));
                } else {
                    getView().showNoticeDialog(R.string.forms_is_not_valid);
                }
            }
        }
    }




}
