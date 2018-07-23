package sanchez.sanchez.sergio.masom_app.notification.remote;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.masom_app.notification.model.INotification;

/**
 * Remote System Notification
 */
public interface IRemoteSystemNotification {
    Observable<INotification> subscribeTo(final String topicKey);
}
