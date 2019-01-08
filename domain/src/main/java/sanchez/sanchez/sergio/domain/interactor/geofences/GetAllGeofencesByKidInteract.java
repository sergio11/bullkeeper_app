package sanchez.sanchez.sergio.domain.interactor.geofences;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.repository.IGeofencesRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get All Geofences By Kid Interact
 */
public final class GetAllGeofencesByKidInteract
    extends UseCase<List<GeofenceEntity>, GetAllGeofencesByKidInteract.Params> {

    /**
     * Geofence Repository
     */
    private final IGeofencesRepository geofencesRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param geofencesRepository
     */
    public GetAllGeofencesByKidInteract(
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
    protected Observable<List<GeofenceEntity>> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        return geofencesRepository.getAllGeofencesByKidId(params.getKid());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kid;

        /**
         * Params
         * @param kid
         */
        private Params(final String kid) {
            this.kid = kid;
        }

        public String getKid() {
            return kid;
        }

        /**
         * Create
         * @param kid
         * @return
         */
        public static Params create(final String kid) {
            return new Params(kid);
        }
    }

    /**
     * Get All Geofences By kid Api Error
     */
    public enum GetAllGeofencesByIdApiErrors
            implements ISupportVisitable<GetAllGeofencesByIdApiErrors.IGetAllGeofencesByIdApiErrorsVisitor> {

        /**
         * No Geofences Found Exception
         */
        NO_GEOFENCES_FOUND_EXCEPTION(){
            @Override
            public <E> void accept(final IGetAllGeofencesByIdApiErrorsVisitor visitor, E data) {
                visitor.visitNoGeofencesFound(visitor);
            }
        };

        /**
         * Get All Geofences By Id
         */
        public interface IGetAllGeofencesByIdApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Geofences Found
             * @param apiErrorsVisitor
             */
            void visitNoGeofencesFound(final IGetAllGeofencesByIdApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
