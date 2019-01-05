package sanchez.sanchez.sergio.bullkeeper.core.notification.impl;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.notification.INotificationHelper;

/**
 * Notification Helper
 */
public final class NotificationHelperImpl implements INotificationHelper {

    public enum NotificationChannels { SILENT_CHANNEL, COMMON_CHANNEL, IMPORTANT_CHANNEL };

    private final static Integer DEFAULT_NOTIFICATION_ID = 1000;

    private final Context context;

    // Get default ringtone
    private final Uri alertSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    /**
     * Notification Helper
     * @param context
     */
    @Inject
    public NotificationHelperImpl(final Context context) {
        this.context = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for(final NotificationChannels notificationChannels: NotificationChannels.values())
                createNotificationChannel(notificationChannels);
        }

    }

    /**
     * Create Low importance Notification Channel
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(final NotificationChannels notificationChannels) {
        Preconditions.checkNotNull(notificationChannels, "Notification channel can not be null");

        CharSequence channelName = notificationChannels.name();
        int importance = NotificationManager.IMPORTANCE_LOW;

        NotificationChannel notificationChannel = new NotificationChannel(notificationChannels.name(),
                channelName, importance);

        final AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                .build();

        if(notificationChannels.equals(NotificationChannels.COMMON_CHANNEL)) {
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            notificationChannel.setSound(alertSound, audioAttributes);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        } else if(notificationChannels.equals(NotificationChannels.IMPORTANT_CHANNEL)) {
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setSound(alertSound, audioAttributes);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        } else {
            notificationChannel.enableLights(false);
        }

        final NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null)
            notificationManager.createNotificationChannel(notificationChannel);
    }

    /**
     * Vibrate
     * @param milliseconds
     */
    private void vibrate(long milliseconds) {
        final Vibrator vibratorService = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if(vibratorService != null)
            vibratorService.vibrate(milliseconds);
    }

    /**
     * Show Notification
     * @param notification
     */
    private void showNotification(final Notification notification){
        Preconditions.checkNotNull(notification, "Notification can not be null");

        vibrate(1000);

        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        if(mNotificationManager != null)
            mNotificationManager.notify(DEFAULT_NOTIFICATION_ID, notification);
    }

    /**
     * Show Important Notification
     * @param title
     * @param body
     */
    @Override
    public void showImportantNotification(final String title, final String body, final Intent intent) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stat_bk)
                .setContentTitle(title)
                .setContentText(body)
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setSound(alertSound);

        if(intent != null) {
            final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentIntent(pendingIntent);
        }


        // Set the Channel ID for Android O.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(NotificationChannels.IMPORTANT_CHANNEL.name());
        }

        showNotification(notificationBuilder.build());
    }

    /**
     * Show Important Notification
     * @param title
     * @param body
     */
    @Override
    public void showImportantNotification(String title, String body) {
        showImportantNotification(title, body, null);
    }

    /**
     * Show Notice Notification
     * @param title
     * @param body
     */
    @Override
    public void showNoticeNotification(final String title, final String body, final Intent intent) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stat_bk)
                .setContentTitle(title)
                .setContentText(body)
                .setOngoing(false)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(alertSound);

        if(intent != null) {
            final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentIntent(pendingIntent);
        }

        // Set the Channel ID for Android O.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(NotificationChannels.COMMON_CHANNEL.name());
        }

        showNotification(notificationBuilder.build());

    }

    /**
     * Show Notice Notification
     * @param title
     * @param body
     */
    @Override
    public void showNoticeNotification(String title, String body) {
        showNoticeNotification(title, body, null);
    }

    /**
     * Show Silent Notification
     * @param title
     * @param body
     */
    @Override
    public void showSilentNotification(final String title, final String body, final Intent intent) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stat_bk)
                .setContentTitle(title)
                .setContentText(body)
                .setOngoing(false)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setSound(alertSound);

        if(intent != null) {
            final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentIntent(pendingIntent);
        }

        // Set the Channel ID for Android O.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(NotificationChannels.SILENT_CHANNEL.name());
        }

        showNotification(notificationBuilder.build());
    }

    /**
     * Show Silent Notification
     * @param title
     * @param body
     */
    @Override
    public void showSilentNotification(String title, String body) {
        showSilentNotification(title, body, null);
    }
}
