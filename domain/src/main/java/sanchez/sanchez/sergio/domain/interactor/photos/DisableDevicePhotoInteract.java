package sanchez.sanchez.sergio.domain.interactor.photos;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IDevicePhotosRepository;

/**
 * Disable Device Photo Interact
 **/
public final class DisableDevicePhotoInteract extends UseCase<String, DisableDevicePhotoInteract.Params> {

    private final IDevicePhotosRepository devicePhotosRepository;


    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DisableDevicePhotoInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IDevicePhotosRepository devicePhotosRepository) {
        super(threadExecutor, postExecutionThread);
        this.devicePhotosRepository = devicePhotosRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        return devicePhotosRepository.disableDevicePhoto(params.getKid(),
                params.getTerminal(), params.getPhoto());
    }

    public static class Params {


        private final String kid;
        private final String terminal;
        private final String photo;

        private Params(String kid, String terminal, String photo) {
            this.kid = kid;
            this.terminal = terminal;
            this.photo = photo;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public String getPhoto() {
            return photo;
        }

        /**
         *
         * @param kid
         * @param terminal
         * @param photo
         * @return
         */
        public static Params create(final String kid, final String terminal, final String photo) {
            return new Params(kid, terminal, photo);
        }
    }
}
