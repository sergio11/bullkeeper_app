package sanchez.sanchez.sergio.bullkeeper.notification.local.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.notification.local.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.notification.local.ILocalSystemNotificationVisitor;
import sanchez.sanchez.sergio.bullkeeper.notification.model.INotification;
import sanchez.sanchez.sergio.bullkeeper.notification.model.SupportNotification;
import sanchez.sanchez.sergio.bullkeeper.notification.model.impl.BasicNotification;

/**
 * Local System Notification Impl
 */
public final class LocalSystemNotificationImpl implements ILocalSystemNotification {


    // Context
    private final Context context;

    // Notification Receiver
    private BroadcastReceiver notificationReceiver;

    /**
     * Local System Notification Impl
     * @param context
     */
    @Inject
    public LocalSystemNotificationImpl(final Context context) {
        this.context = context;
    }

    /**
     * Send Notification
     * @param notification
     */
    @Override
    public void sendNotification(final INotification notification) {
        final Intent notificationIntent = notification.toIntent();
        Preconditions.checkNotNull(notificationIntent, "Notification Intent cannot be null");
        // Fire the broadcast with intent packaged
        LocalBroadcastManager.getInstance(context).sendBroadcast(notificationIntent);
    }

    /**
     * Register Visitor
     * @param localSystemNotificationVisitor
     * @param action
     */
    @Override
    public void registerVisitor(ILocalSystemNotificationVisitor localSystemNotificationVisitor, String action) {
        // Create Intent Filter
        IntentFilter filter = new IntentFilter(BasicNotification.ACTION_NAME);
        // Create Notification Receiver
        notificationReceiver = new NotificationReceiver(localSystemNotificationVisitor);
        // Register Receiver
        LocalBroadcastManager.getInstance(context).registerReceiver(notificationReceiver, filter);
    }

    /**
     * Register Visitor
     * @param localSystemNotificationVisitor
     */
    @Override
    public void registerVisitor(
            final ILocalSystemNotificationVisitor localSystemNotificationVisitor) {
        this.registerVisitor(localSystemNotificationVisitor, BasicNotification.ACTION_NAME);
    }

    /**
     * Unregister Visitor
     */
    @Override
    public void unregisterVisitor() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(notificationReceiver);
    }


    /**
     * Notification Receiver
     */
    private class NotificationReceiver extends BroadcastReceiver {

        private final ILocalSystemNotificationVisitor localSystemNotificationVisitor;

        public NotificationReceiver(ILocalSystemNotificationVisitor localSystemNotificationVisitor) {
            this.localSystemNotificationVisitor = localSystemNotificationVisitor;
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            final INotification notification = SupportNotification.fromIntent(intent);
            if(notification != null)
                notification.accept(localSystemNotificationVisitor);
        }
    }
}
