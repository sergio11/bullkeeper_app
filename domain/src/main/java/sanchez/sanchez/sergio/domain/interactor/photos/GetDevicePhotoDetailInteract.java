package sanchez.sanchez.sergio.domain.interactor.photos;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;
import sanchez.sanchez.sergio.domain.repository.IDevicePhotosRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Device Photo Detail Interact
 **/
public final class GetDevicePhotoDetailInteract extends UseCase<DevicePhotoEntity, GetDevicePhotoDetailInteract.Params> {


    private final IDevicePhotosRepository devicePhotosRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param devicePhotosRepository
     */
    public GetDevicePhotoDetailInteract(
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
    protected Observable<DevicePhotoEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkNotNull(params.getPhoto(), "Photo can not be null");

        return devicePhotosRepository.getDevicePhotoDetail(params.getKid(), params.getTerminal(),
                params.getPhoto());
    }


    /**
     * Params
     */
    public static class Params {

        private final String kid;
        private final String terminal;
        private final String photo;

        /**
         *
         * @param kid
         * @param terminal
         */
        private Params(final String kid, final String terminal, final String photo) {
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
        public static Params create(final String kid, final String terminal, final String photo){
            return new Params(kid, terminal, photo);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    ", photo='" + photo + '\'' +
                    '}';
        }
    }


    /**
     * Get Device Photo Detail Api Errors
     */
    public enum GetDevicePhotoDetailApiErrors
            implements ISupportVisitable<GetDevicePhotoDetailApiErrors.IGetDevicePhotoDetailApiErrorsVisitor> {

        /**
         * Device Photo Detail Not Found
         */
        DEVICE_PHOTO_DETAIL_NOT_FOUND(){
            @Override
            public <E> void accept(final IGetDevicePhotoDetailApiErrorsVisitor visitor, E data) {
                visitor.visitDevicePhotoDetailNotFound(visitor);
            }
        };

        /**
         * Get Device Photo Detail Errors Visitor
         */
        public interface IGetDevicePhotoDetailApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Device Photos Found
             * @param apiErrorsVisitor
             */
            void visitDevicePhotoDetailNotFound(final IGetDevicePhotoDetailApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
