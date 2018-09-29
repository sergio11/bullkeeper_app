package sanchez.sanchez.sergio.domain.interactor.device;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.DeviceEntity;
import sanchez.sanchez.sergio.domain.repository.IDeviceGroupRepository;

/**
 * Save Device Interact
 */
public final class SaveDeviceInteract extends UseCase<DeviceEntity, SaveDeviceInteract.Params> {

    private final IDeviceGroupRepository deviceGroupRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    @Inject
    public SaveDeviceInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                              final IDeviceGroupRepository deviceGroupRepository) {
        super(threadExecutor, postExecutionThread);
        this.deviceGroupRepository = deviceGroupRepository;
    }

    @Override
    protected Observable<DeviceEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");

        return deviceGroupRepository.save(params.getDeviceId(), params.getRegistrationToken());
    }

    /**
     * Params
     */
    public static class Params {

        private final String deviceId;
        private final String registrationToken;

        private Params(String deviceId, String registrationToken) {
            this.deviceId = deviceId;
            this.registrationToken = registrationToken;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public String getRegistrationToken() {
            return registrationToken;
        }

        /**
         * Create
         * @param deviceId
         * @param registrationToken
         * @return
         */
        public static Params create(final String deviceId, final String registrationToken) {
            return new Params(deviceId, registrationToken);
        }
    }
}
