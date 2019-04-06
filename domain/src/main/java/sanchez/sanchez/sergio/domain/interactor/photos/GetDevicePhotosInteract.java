package sanchez.sanchez.sergio.domain.interactor.photos;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;
import sanchez.sanchez.sergio.domain.repository.IDevicePhotosRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Device Photos Interact
 **/
public final class GetDevicePhotosInteract extends UseCase<List<DevicePhotoEntity>, GetDevicePhotosInteract.Params> {


    private final IDevicePhotosRepository devicePhotosRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param devicePhotosRepository
     */
    public GetDevicePhotosInteract(
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
    protected Observable<List<DevicePhotoEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");

        return devicePhotosRepository.getDevicePhotos(params.getKid(), params.getTerminal());
    }


    public static class Params {

        private final String kid;
        private final String terminal;

        /**
         *
         * @param kid
         * @param terminal
         */
        private Params(final String kid, final String terminal) {
            this.kid = kid;
            this.terminal = terminal;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        /**
         *
         * @param kid
         * @param terminal
         * @return
         */
        public static Params create(final String kid, final String terminal){
            return new Params(kid, terminal);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    '}';
        }
    }


    /**
     * Get Device Photos Api Errors
     */
    public enum GetDevicePhotosApiErrors
            implements ISupportVisitable<GetDevicePhotosApiErrors.IGetDevicePhotosApiErrorsVisitor> {

        /**
         * No Device Photos Found
         */
        NO_DEVICE_PHOTOS_FOUND(){
            @Override
            public <E> void accept(final IGetDevicePhotosApiErrorsVisitor visitor, E data) {
                visitor.visitNoDevicePhotosFound(visitor);
            }
        };

        /**
         * Get Device List Api Errors Visitor
         */
        public interface IGetDevicePhotosApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Device Photos Found
             * @param apiErrorsVisitor
             */
            void visitNoDevicePhotosFound(final IGetDevicePhotosApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
