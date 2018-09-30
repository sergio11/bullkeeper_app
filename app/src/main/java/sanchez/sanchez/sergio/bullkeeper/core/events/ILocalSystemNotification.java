package sanchez.sanchez.sergio.bullkeeper.core.events;

import sanchez.sanchez.sergio.bullkeeper.core.events.model.IEvent;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * Local System Notification
 */
public interface ILocalSystemNotification {


    /**
     * Send Notification
     * @param notification
     */
    void sendNotification(final IEvent notification);

    /**
     * Register Event Listener
     * @param eventClazz
     * @param eventVisitor
     * @param <T>
     */
    <T extends IVisitor, E extends SupportEvent<T>>
        int registerEventListener(final Class<E> eventClazz, final T eventVisitor);

    /**
     * Unregister visitor
     */
    void unregisterEventListener(final int keyIndex);
}