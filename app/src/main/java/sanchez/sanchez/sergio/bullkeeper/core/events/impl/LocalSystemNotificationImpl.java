package sanchez.sanchez.sergio.bullkeeper.core.events.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.SparseArray;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.IEvent;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * Local System Notification Impl
 */
public final class LocalSystemNotificationImpl implements ILocalSystemNotification {

    /**
     * Context
     */
    private final Context context;

    /**
     * Listeners
     */
    private SparseArray<BroadcastReceiver> listeners;

    /**
     * Key Index
     */
    private int keyIndex = 0;

    /**
     * Local System Notification Impl
     * @param context
     */
    @Inject
    public LocalSystemNotificationImpl(final Context context) {
        this.context = context;
        this.listeners = new SparseArray<>();
    }

    /**
     * Send Notification
     * @param notification
     */
    @Override
    public void sendNotification(final IEvent notification) {
        Preconditions.checkNotNull(notification, "Notification can not be null");
        Preconditions.checkNotNull(notification.toIntent(),
                "Notification Intent cannot be null");
        final Intent notificationIntent = notification.toIntent();
        // Fire the broadcast with intent packaged
        LocalBroadcastManager.getInstance(context).sendBroadcast(notificationIntent);
    }

    @Override
    public <T extends IVisitor, E extends SupportEvent<T>> int registerEventListener(Class<E> eventClazz, T eventVisitor) {
        Preconditions.checkNotNull(eventClazz, "Event can not be null");
        Preconditions.checkNotNull(eventVisitor, "Event Visitor can not be null");
        // Create Intent Filter
        final IntentFilter intentFilter = new IntentFilter(eventClazz.getCanonicalName());
        // Create Notification Receiver
        final NotificationReceiver<T> notificationReceiver = new NotificationReceiver<>(eventVisitor);
        int keyToIndex = keyIndex++;
        listeners.append(keyToIndex, notificationReceiver);
        // Register Receiver
        LocalBroadcastManager.getInstance(context).registerReceiver(notificationReceiver, intentFilter);
        return keyToIndex;
    }


    /**
     * Unregister Visitor
     */
    @Override
    public void unregisterEventListener(final int keyIndex) {
        final BroadcastReceiver notificationReceiver = listeners.get(keyIndex, null);
        if(notificationReceiver != null) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(notificationReceiver);
            listeners.delete(keyIndex);
        }
    }


    /**
     * Notification Receiver
     */
    private class NotificationReceiver<T extends IVisitor> extends BroadcastReceiver {

        private final T eventVisitor;

        public NotificationReceiver(T eventVisitor) {
            this.eventVisitor = eventVisitor;
        }

        /**
         * On Receive
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            final IEvent<T> notification = SupportEvent.fromIntent(intent);
            if(notification != null)
                notification.accept(eventVisitor);
        }
    }
}
