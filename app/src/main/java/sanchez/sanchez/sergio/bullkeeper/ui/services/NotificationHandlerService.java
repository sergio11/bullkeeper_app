package sanchez.sanchez.sergio.bullkeeper.ui.services;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.fernandocejas.arrow.checks.Preconditions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.here.oksse.OkSse;
import com.here.oksse.ServerSentEvent;
import javax.inject.Inject;
import okhttp3.Request;
import okhttp3.Response;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.NoticeEvent;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.SilentNoticeEvent;
import sanchez.sanchez.sergio.bullkeeper.core.events.visitor.INoticeEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.core.events.visitor.ISilentNoticeEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.core.notification.INotificationHelper;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportService;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerServiceComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.ServiceComponent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.ILogoutEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.events.handler.ISigningEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.events.impl.LogoutEvent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.SigningEvent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.intro.IntroMvpActivity;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.interactor.device.DeleteDeviceInteract;
import sanchez.sanchez.sergio.domain.interactor.device.SaveDeviceInteract;
import sanchez.sanchez.sergio.domain.models.DeviceEntity;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import timber.log.Timber;

/**
 * Notification Handler Service
 */
public class NotificationHandlerService extends SupportService
        implements ServerSentEvent.Listener {

    /**
     * Local System Notification
     */
    @Inject
    protected ILocalSystemNotification localSystemNotification;

    /**
     * Notification Helper
     */
    @Inject
    protected INotificationHelper notificationHelper;

    /**
     * Save Device Interact
     */
    @Inject
    protected SaveDeviceInteract saveDeviceInteract;

    /**
     * Delete Device Interact
     */
    @Inject
    protected DeleteDeviceInteract deleteDeviceInteract;

    /**
     * App Utils
     */
    @Inject
    protected IAppUtils appUtils;

    /**
     * Api End Points Helper
     */
    @Inject
    protected ApiEndPointsHelper apiEndPointsHelper;

    /**
     * OK SSE Client
     */
    @Inject
    protected OkSse okSse;

    /**
     * Preference Repository
     */
    @Inject
    protected IPreferenceRepository preferenceRepository;


    /**
     * State
     * ==============
     */

    protected ServerSentEvent serverSentEvent;


    /**
     * Silent Notice Event Visitor
     */
    private ISilentNoticeEventVisitor silentNoticeEventVisitor = new ISilentNoticeEventVisitor() {
        @Override
        public void visit(SilentNoticeEvent silentNoticeEvent) {
            Preconditions.checkNotNull(silentNoticeEvent, "Silent Notice Event can not be null");
            notificationHelper.showSilentNotification(silentNoticeEvent.getTitle(),
                    silentNoticeEvent.getContent());
        }
    };

    /**
     * Notice Event Visitor
     */
    private INoticeEventVisitor noticeEventVisitor = new INoticeEventVisitor() {
        @Override
        public void visit(NoticeEvent noticeEvent) {
            Preconditions.checkNotNull(noticeEvent, "Notice Event can not be null");
            notificationHelper.showNoticeNotification(noticeEvent.getTitle(),
                    noticeEvent.getContent());
        }
    };


    /**
     * Signing Event Visitor
     */
    private ISigningEventVisitor signingEventVisitor = new ISigningEventVisitor() {
        @Override
        public void visit(SigningEvent signingEvent) {
            Preconditions.checkNotNull(signingEvent, "Signing Event can not be null");
            saveDevice();
            startListenSse();
        }
    };

    /**
     * Logout Event Visitor
     */
    private ILogoutEventVisitor logoutEventVisitor = new ILogoutEventVisitor() {
        @Override
        public void visit(LogoutEvent logoutEvent) {
            Preconditions.checkNotNull(logoutEvent, "Logout Event");
            stopListenSse();
            notificationHelper.showNoticeNotification(getString(R.string.logout_notification_title),
                    getString(R.string.logout_notification_description), IntroMvpActivity.getCallingIntent(getApplicationContext(), false));
        }
    };

    /**
     *
     */
    private int silentNotificationRegisterKey,
            notificationRegisterKey, signingNotificationKey, logoutNotificationKey;

    public NotificationHandlerService() { }

    /**
     * On Create
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("Notification Handler Service created");
        silentNotificationRegisterKey = localSystemNotification.registerEventListener(SilentNoticeEvent.class, silentNoticeEventVisitor);
        notificationRegisterKey = localSystemNotification.registerEventListener(NoticeEvent.class, noticeEventVisitor);
        signingNotificationKey = localSystemNotification.registerEventListener(SigningEvent.class, signingEventVisitor);
        logoutNotificationKey = localSystemNotification.registerEventListener(LogoutEvent.class, logoutEventVisitor);
    }

    /**
     * On Start Command
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * On Destroy
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("Notification Handler Service Destroyed");
        localSystemNotification.unregisterEventListener(silentNotificationRegisterKey);
        localSystemNotification.unregisterEventListener(notificationRegisterKey);
        localSystemNotification.unregisterEventListener(signingNotificationKey);
        localSystemNotification.unregisterEventListener(logoutNotificationKey);
    }

    /**
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {

        ServiceComponent serviceComponent = DaggerServiceComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();

        serviceComponent.inject(this);
    }


    /**
     * Save Device
     */
    protected void saveDevice() {
        Timber.d("Notification Handler -> Save Device");
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String registrationToken = instanceIdResult.getToken();
                saveDeviceInteract.execute(new SaveDeviceObservable(),
                        SaveDeviceInteract.Params.create(appUtils.getDeviceId(), registrationToken));
            }
        });

    }

    /**
     * Start Listen SSE
     */
    protected void startListenSse(){

        if (serverSentEvent != null) {
            serverSentEvent.close();
            serverSentEvent = null;
        }

        if(!preferenceRepository.getPrefCurrentUserIdentity()
                .equals(IPreferenceRepository.CURRENT_USER_IDENTITY_DEFAULT_VALUE)) {

            Timber.d("Start Listen SSE");
            final Request eventSubscriptionRequest = new Request.Builder()
                    .url(apiEndPointsHelper.getEventSubscriptionUrl(preferenceRepository
                            .getPrefCurrentUserIdentity()))
                    .build();
            serverSentEvent = okSse.newServerSentEvent(eventSubscriptionRequest, this);
        }

    }

    /**
     * Stop Listen Sse
     */
    protected void stopListenSse(){
        if(serverSentEvent != null) {
            serverSentEvent.close();
            serverSentEvent = null;
        }
    }

    /**
     *
     * @param sse
     * @param response
     */
    @Override
    public void onOpen(ServerSentEvent sse, Response response) {
        Timber.d("ServerSentEventHandler: On Open");
    }

    /**
     *
     * @param sse
     * @param id
     * @param event
     * @param message
     */
    @Override
    public void onMessage(ServerSentEvent sse, String id, String event, String message) {
        Timber.d("ServerSentEventHandler: On Message -> %s", message);
    }

    /**
     *
     * @param sse
     * @param comment
     */
    @Override
    public void onComment(ServerSentEvent sse, String comment) {
        Timber.d("ServerSentEventHandler: On Comment -> %s", comment);

    }

    /**
     *
     * @param sse
     * @param milliseconds
     * @return
     */
    @Override
    public boolean onRetryTime(ServerSentEvent sse, long milliseconds) {
        Timber.d("ServerSentEventHandler: On Retry Time");
        return false;
    }

    /**
     *
     * @param sse
     * @param throwable
     * @param response
     * @return
     */
    @Override
    public boolean onRetryError(ServerSentEvent sse, Throwable throwable, Response response) {
        Timber.d("ServerSentEventHandler: On Retry Error");
        return false;
    }

    /**
     *
     * @param sse
     */
    @Override
    public void onClosed(ServerSentEvent sse) {
        Timber.d("ServerSentEventHandler: On Closed");
        if(!preferenceRepository.getPrefCurrentUserIdentity()
                .equals(IPreferenceRepository.CURRENT_USER_IDENTITY_DEFAULT_VALUE))
            startListenSse();
    }

    /**
     *
     * @param sse
     * @param originalRequest
     * @return
     */
    @Override
    public Request onPreRetry(ServerSentEvent sse, Request originalRequest) {
        Timber.d("ServerSentEventHandler: On Pre Retry");
        return null;
    }

    /**
     * Save Device Observable
     */
    protected class SaveDeviceObservable extends BasicCommandCallBackWrapper<DeviceEntity> {

        /**
         * On Success
         * @param deviceEntity
         */
        @Override
        protected void onSuccess(DeviceEntity deviceEntity) {
            Preconditions.checkNotNull(deviceEntity, "Device entity can not be null");

            Timber.d("Device Registrered");
        }

        /**
         * On Unexpected Exception
         */
        @Override
        protected void onUnexpectedException() {
            Timber.e("Save Device - Unexpected exception");
        }

        /**
         * On Network Error
         */
        @Override
        protected void onNetworkError() {
            Timber.d("On Network Error");
        }
    }
}
