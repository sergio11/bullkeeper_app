package sanchez.sanchez.sergio.domain.interactor.geofences;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IGeofencesRepository;

/**
 * Delete All Geofences By Kid Interact
 */
public final class DeleteAllGeofencesBykidInteract extends
        UseCase<String, DeleteAllGeofencesBykidInteract.Params> {

    /**
     * Geofence Repository
     */
    private final IGeofencesRepository geofencesRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param geofencesRepository
     */
    public DeleteAllGeofencesBykidInteract(
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
    protected Observable<String> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");

        return geofencesRepository.deleteAllGeofencesBykidId(params.getKid());
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
         *
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
}
