package sanchez.sanchez.sergio.bullkeeper.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import timber.log.Timber;

/**
 * Remote Notification Service
 */
public class RemoteNotificationService extends FirebaseMessagingService {


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
        Timber.d("On Message Received");
    }
}
