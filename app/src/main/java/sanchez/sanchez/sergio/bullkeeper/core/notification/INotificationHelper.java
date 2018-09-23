package sanchez.sanchez.sergio.bullkeeper.core.notification;

public interface INotificationHelper {

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
     */
    void showNoticeNotification(final String title, final String body);

    /**
     * Show Silent Notification
     * @param title
     * @param body
     */
    void showSilentNotification(final String title, final String body);

}
