package sanchez.sanchez.sergio.bullkeeper.ui.services;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.NoticeEvent;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.SilentNoticeEvent;
import sanchez.sanchez.sergio.bullkeeper.core.events.visitor.INoticeEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.core.events.visitor.ISilentNoticeEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.core.notification.INotificationHelper;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportService;
import timber.log.Timber;

/**
 * Notification Handler Service
 */
public class NotificationHandlerService extends SupportService {

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

    private int silentNotificationRegisterKey, notificationRegisterKey;

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
    }

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
        getApplicationComponent().inject(this);
    }


}
