package sanchez.sanchez.sergio.domain.interactor.geofences;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.repository.IGeofencesRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Geofence By Id Interact
 */
public final class GetGeofenceByIdInteract
    extends UseCase<GeofenceEntity, GetGeofenceByIdInteract.Params> {

    /**
     * Geofence Repository
     */
    private final IGeofencesRepository geofencesRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param geofencesRepository
     */
    public GetGeofenceByIdInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IGeofencesRepository geofencesRepository) {
        super(threadExecutor, postExecutionThread);
        this.geofencesRepository = geofencesRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<GeofenceEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        return geofencesRepository.getGeofenceById(params.getKid(), params.getId());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Kid
         */
        private final String kid;

        /**
         * Id
         */
        private final String id;

        /**
         * Params
         * @param kid
         * @param id
         */
        private Params(final String kid, final String id) {
            this.kid = kid;
            this.id = id;
        }

        public String getKid() {
            return kid;
        }

        public String getId() {
            return id;
        }

        /**
         * Create
         * @param kid
         * @param id
         * @return
         */
        public static Params create(final String kid, final String id) {
            return new Params(kid, id);
        }
    }

    /**
     * Get All Geofences By kid Api Error
     */
    public enum GetGeofencesByIdApiErrors
            implements ISupportVisitable<GetGeofencesByIdApiErrors.IGetGeofenceByIdApiErrorsVisitor> {

        /**
         * Geofence Not Found Exception
         */
        GEOFENCE_NOT_FOUND_EXCEPTION(){
            @Override
            public <E> void accept(final IGetGeofenceByIdApiErrorsVisitor visitor, E data) {
                visitor.visitGeofenceNotFound(visitor);
            }
        };

        /**
         * Get Geofence By Id
         */
        public interface IGetGeofenceByIdApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Geofences Found
             * @param apiErrorsVisitor
             */
            void visitGeofenceNotFound(final IGetGeofenceByIdApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
