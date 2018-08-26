package sanchez.sanchez.sergio.bullkeeper.notification.remote;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.bullkeeper.notification.model.INotification;

/**
 * Remote System Notification
 */
public interface IRemoteSystemNotification {
    Observable<INotification> subscribeTo(final String topicKey);
}
