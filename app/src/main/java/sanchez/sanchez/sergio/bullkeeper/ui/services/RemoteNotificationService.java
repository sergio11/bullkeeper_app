package sanchez.sanchez.sergio.bullkeeper.ui.services;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.NoticeEvent;
import sanchez.sanchez.sergio.bullkeeper.core.notification.INotificationHelper;
import timber.log.Timber;

/**
 * Remote Notification Service
 */
public class RemoteNotificationService extends FirebaseMessagingService {

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


    @Override
    public void onCreate() {
        initializeInjector();
        super.onCreate();
    }

    /**
     * Initialize Injector
     */
    protected void initializeInjector() {
        getApplicationComponent().inject(this);
    }

    /**
     * On New Token
     * @param newToken
     */
    @Override
    public void onNewToken(String newToken) {
        Timber.d("New Token Received: -> %s", newToken);

    }

    /**
     * On Message Received
     * @param remoteMessage
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        final RemoteMessage.Notification notification = remoteMessage.getNotification();
        localSystemNotification.sendNotification(new NoticeEvent(notification.getTitle(),
                notification.getBody()));
        
    }


    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }
}
