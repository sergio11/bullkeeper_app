package sanchez.sanchez.sergio.domain.interactor.geofences;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IGeofencesRepository;

/**
 * Delete Geofence By Id Interact
 */
public final class DeleteGeofenceByIdInteract extends UseCase<String, DeleteGeofenceByIdInteract.Params> {

    /**
     * Geofence Repository
     */
    private final IGeofencesRepository geofencesRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteGeofenceByIdInteract(
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
    protected Observable<String> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getId(), "Id can not be null");
        Preconditions.checkState(!params.getId().isEmpty(), "Id can not be empty");

        // Delete Geofence By Id
        return geofencesRepository.deleteGeofenceById(params.getKid(),
                params.getId());
    }

    /**
     * Params
     */
    public static class Params {

        // Kid
        private final String kid;
        // Id
        private final String id;

        /**
         *
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

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }
}
