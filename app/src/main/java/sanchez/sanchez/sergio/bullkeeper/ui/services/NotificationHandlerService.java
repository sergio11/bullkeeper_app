package sanchez.sanchez.sergio.bullkeeper.ui.services;

import android.app.ActivityManager;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.fernandocejas.arrow.checks.Preconditions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.NoticeEvent;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.SilentNoticeEvent;
import sanchez.sanchez.sergio.bullkeeper.core.events.visitor.INoticeEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.core.events.visitor.ISilentNoticeEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.core.notification.INotificationHelper;
import sanchez.sanchez.sergio.bullkeeper.sse.ISseEventHandler;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportService;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerServiceComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.ServiceComponent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.ILogoutEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.events.handler.ISigningEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.events.impl.LogoutEvent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.SigningEvent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.home.HomeMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.intro.IntroMvpActivity;
import sanchez.sanchez.sergio.domain.interactor.device.DeleteDeviceInteract;
import sanchez.sanchez.sergio.domain.interactor.device.SaveDeviceInteract;
import sanchez.sanchez.sergio.domain.models.DeviceEntity;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import timber.log.Timber;

/**
 * Notification Handler Service
 */
public class NotificationHandlerService extends SupportService {

    /**
     * The identifier for the notification displayed for the foreground service.
     */
    private final static int NOTIFICATION_ID = 12345678;

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
     * Preference Repository
     */
    @Inject
    protected IPreferenceRepository preferenceRepository;

    /**
     * SSE Event Handler
     */
    @Inject
    protected ISseEventHandler sseEventHandler;

    /**
     * Local Binder
     */
    private IBinder mBinder = new LocalBinder();

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
            Timber.d("NHS: SignIn Event Handler");
            sseEventHandler.open();
            saveDevice();
        }
    };

    /**
     * Logout Event Visitor
     */
    private ILogoutEventVisitor logoutEventVisitor = new ILogoutEventVisitor() {
        @Override
        public void visit(LogoutEvent logoutEvent) {
            Preconditions.checkNotNull(logoutEvent, "Logout Event");
            Timber.d("NHS: Logout Event Handler");
            notificationHelper.showNoticeNotification(getString(R.string.logout_notification_title),
                    getString(R.string.logout_notification_description), IntroMvpActivity.getCallingIntent(getApplicationContext(), false));
            sseEventHandler.close();
        }
    };

    /**
     * Registration Keys
     */
    private int silentNotificationRegisterKey,
            notificationRegisterKey, signingNotificationKey, logoutNotificationKey= -1;

    public NotificationHandlerService() { }

    /**
     * On Create
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("NHS: Notification Handler Service created");
        silentNotificationRegisterKey = localSystemNotification.registerEventListener(SilentNoticeEvent.class, silentNoticeEventVisitor);
        notificationRegisterKey = localSystemNotification.registerEventListener(NoticeEvent.class, noticeEventVisitor);
        signingNotificationKey = localSystemNotification.registerEventListener(SigningEvent.class, signingEventVisitor);
        logoutNotificationKey = localSystemNotification.registerEventListener(LogoutEvent.class, logoutEventVisitor);
        if(!preferenceRepository.getPrefCurrentUserIdentity()
                .equals(IPreferenceRepository.CURRENT_USER_IDENTITY_DEFAULT_VALUE) &&
                !sseEventHandler.isOpened())
            sseEventHandler.open();
        startForeground(NOTIFICATION_ID, getNotification());
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
        Timber.d("NHS: onStartCommand -> start id: %d", startId);
        return START_STICKY;
    }

    /**
     * Is Notification Service Running
     * @param context
     * @return
     */
    private static boolean isNotificationServiceRunning(final Context context) {
        final ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if(activityManager != null) {
            for (final ActivityManager.RunningServiceInfo serviceInfo :
                    activityManager.getRunningServices(Integer.MAX_VALUE)) {
                if (NotificationHandlerService.class.getName()
                        .equals(serviceInfo.service.getClassName()))
                    return true;
            }
        }
        return false;
    }

    /**
     * Start If Needed
     * @param context
     */
    public static void startIfNeeded(final Context context){
        if(!isNotificationServiceRunning(context))
            start(context);
    }


    /**
     * Start
     * @param context
     */
    public static void start(final Context context) {
        // Start Notification Service
        context.startService(new Intent(context, NotificationHandlerService.class));
    }

    /**
     * On Destroy
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("NHS: Notification Handler Service Destroyed");
        localSystemNotification.unregisterEventListener(silentNotificationRegisterKey);
        localSystemNotification.unregisterEventListener(notificationRegisterKey);
        localSystemNotification.unregisterEventListener(signingNotificationKey);
        localSystemNotification.unregisterEventListener(logoutNotificationKey);
    }

    /**
     * Get Notification
     * @return
     */
    private Notification getNotification(){
        return notificationHelper.createImportantNotification(
                getString(R.string.notification_background_service_title),
                getString(R.string.notification_background_service_description), HomeMvpActivity.getCallingIntent(this));
    }


    /**
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Timber.d("NTS: On Bind");
        stopForeground(true);
        return mBinder;
    }

    /**
     *
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Timber.d("NHS: On UnBind");
        if(!preferenceRepository.getPrefCurrentUserIdentity()
                .equals(IPreferenceRepository.CURRENT_USER_IDENTITY_DEFAULT_VALUE))
            startForeground(NOTIFICATION_ID, getNotification());
        else
            sseEventHandler.close();
        return true;
    }

    /**
     *
     * @param intent
     */
    @Override
    public void onRebind(Intent intent) {
        Timber.d("NHS: On Rebind");
        stopForeground(true);
        super.onRebind(intent);
    }

    /**
     * Local Binder
     */
    public class LocalBinder extends Binder {
        NotificationHandlerService getService() {
            return NotificationHandlerService.this;
        }
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
        Timber.d("NHS: Notification Handler -> Save Device");
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
