package sanchez.sanchez.sergio.bullkeeper.core.notification;

import android.content.Intent;

public interface INotificationHelper {

    /**
     * Show Important Notification
     * @param title
     * @param body
     * @param intent
     */
    void showImportantNotification(final String title, final String body, final Intent intent);

    /**
     * Show Important Notification
     * @param title
     * @param body
     */
    void showImportantNotification(final String title, final String body);

    /**
     * Show Notice Notification
     * @param title
     * @param body
     * @param intent
     */
    void showNoticeNotification(final String title, final String body, final Intent intent);

    /**
     * Show Notice Notification
     * @param title
     * @param body
     */
    void showNoticeNotification(final String title, final String body);

    /**
     * Show Silent Notification
     * @param title
     * @param body
     * @param intent
     */
    void showSilentNotification(final String title, final String body, final Intent intent);

    /**
     * Show Silent Notification
     * @param title
     * @param body
     */
    void showSilentNotification(final String title, final String body);

}
