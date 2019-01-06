package sanchez.sanchez.sergio.bullkeeper.core.notification;

import android.app.Notification;
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


    /**
     * Create Important Notification
     * @param title
     * @param body
     * @param intent
     * @return
     */
    Notification createImportantNotification(final String title, final String body, final Intent intent);

    /**
     * Create Important Notification
     * @param title
     * @param body
     */
    Notification createImportantNotification(final String title, final String body);

    /**
     * Create Notice Notification
     * @param title
     * @param body
     * @param intent
     */
    Notification createNoticeNotification(final String title, final String body, final Intent intent);

    /**
     * Create Notice Notification
     * @param title
     * @param body
     */
    Notification createNoticeNotification(final String title, final String body);

    /**
     * Create Silent Notification
     * @param title
     * @param body
     * @param intent
     */
    Notification createSilentNotification(final String title, final String body, final Intent intent);

    /**
     * Create Silent Notification
     * @param title
     * @param body
     */
    Notification createSilentNotification(final String title, final String body);


}
