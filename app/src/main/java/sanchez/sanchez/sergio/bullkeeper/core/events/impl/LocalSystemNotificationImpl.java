package sanchez.sanchez.sergio.bullkeeper.core.events.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.SparseArray;
import com.fernandocejas.arrow.checks.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.IEvent;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.utils.IVisitor;
import timber.log.Timber;

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

    /**
     * Register Event Listener
     * @param eventClazz
     * @param eventVisitor
     * @param <T>
     * @param <E>
     * @return
     */
    @Override
    public <T extends IVisitor, E extends SupportEvent<T>> int registerEventListener(Class<E> eventClazz, T eventVisitor) {
        Preconditions.checkNotNull(eventClazz, "Event can not be null");
        Preconditions.checkNotNull(eventVisitor, "Event Visitor can not be null");
        // Create Intent Filter
        final IntentFilter intentFilter = new IntentFilter(eventClazz.getCanonicalName());
        // Create Notification Receiver
        final NotificationReceiver<T, E> notificationReceiver = new NotificationReceiver<>(eventVisitor, eventClazz);
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
    private class NotificationReceiver<T extends IVisitor, E extends SupportEvent<T>> extends BroadcastReceiver {

        private final T eventVisitor;
        private final Class<E> eventClazz;

        /**
         *
         * @param eventVisitor
         * @param eventClazz
         */
        public NotificationReceiver(final T eventVisitor, final Class<E> eventClazz) {
            this.eventVisitor = eventVisitor;
            this.eventClazz = eventClazz;
        }

        /**
         * On Receive
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {

            try {

               final Method method = eventClazz.getMethod("fromBundle", Bundle.class);
               final Object event = method.invoke(null, intent.getExtras());
                if(event instanceof IEvent) {
                    final IEvent<T> notification = (IEvent<T>) event;
                    Timber.d("On Receive Notification %s for visitor %s",
                            notification.getClass().getCanonicalName(), eventVisitor.getClass().getCanonicalName());
                    notification.accept(eventVisitor);
                }

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                Timber.e("Notification error");
                e.printStackTrace();
            }

        }
    }
}
