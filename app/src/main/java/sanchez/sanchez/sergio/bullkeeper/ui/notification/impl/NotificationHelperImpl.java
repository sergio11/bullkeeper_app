package sanchez.sanchez.sergio.bullkeeper.ui.notification.impl;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.notification.INotificationHelper;

/**
 * Notification Helper
 */
public final class NotificationHelperImpl implements INotificationHelper {

    private final static String LOW_IMPORTANCE_CHANNEL = "Low Importance";

    private final static Integer DEFAULT_NOTIFICATION_ID = 1000;

    private final Context context;

    /**
     * Notification Helper
     * @param context
     */
    @Inject
    public NotificationHelperImpl(final Context context) {
        this.context = context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create Low Importance notification channel
            createLowNotificationChannel();
        }

    }

    /**
     * Create Low importance Notification Channel
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private void createLowNotificationChannel() {

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        CharSequence channelName = "LOW IMPORTANCE";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel notificationChannel = new NotificationChannel(LOW_IMPORTANCE_CHANNEL, channelName, importance);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
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
     * Create Basic Notification
     * @param title
     * @param body
     */
    @Override
    public void createBasicNotification(String title, String body) {

        // Get default ringtone
        final Uri alertSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(title)
                .setContentText(body)
                .setOngoing(true)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(alertSound);


        // Set the Channel ID for Android O.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(LOW_IMPORTANCE_CHANNEL);
        }

        vibrate(1000);

        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(DEFAULT_NOTIFICATION_ID, notificationBuilder.build());


    }
}
