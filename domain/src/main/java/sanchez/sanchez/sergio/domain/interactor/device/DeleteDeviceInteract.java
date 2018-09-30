package sanchez.sanchez.sergio.domain.interactor.device;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IDeviceGroupRepository;

/**
 * Delete Device Interact
 */
public final class DeleteDeviceInteract extends UseCase<String, DeleteDeviceInteract.Params> {

    private final IDeviceGroupRepository deviceGroupRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteDeviceInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                final IDeviceGroupRepository deviceGroupRepository) {
        super(threadExecutor, postExecutionThread);
        this.deviceGroupRepository = deviceGroupRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        return deviceGroupRepository.delete(params.getDeviceId());
    }

    /**
     * Params
     */
    public static class Params {

        private final String deviceId;

        private Params(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        /**
         * Create
         * @param deviceId
         * @return
         */
        public static Params create(final String deviceId) {
            return new Params(deviceId);
        }
    }
}
