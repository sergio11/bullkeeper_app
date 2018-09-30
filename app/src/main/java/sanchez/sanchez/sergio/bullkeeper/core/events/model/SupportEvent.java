package sanchez.sanchez.sergio.bullkeeper.core.events.model;

import android.content.Intent;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.NoticeEvent;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.SilentNoticeEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * Support Notification
 */
public abstract class SupportEvent<T extends IVisitor> implements IEvent<T> {

    /**
     * From Intent
     * @param intent
     * @return
     */
    public static <T extends IVisitor> IEvent<T> fromIntent(final Intent intent) {

        final String notificationAction = intent.getAction();
        IEvent notificationFromIntent = null;
        if(notificationAction != null) {
            if(notificationAction.equals(NoticeEvent.class.getCanonicalName())) {
                notificationFromIntent = NoticeEvent.fromBundle(intent.getExtras());
            } else if (notificationAction.equals(SilentNoticeEvent.class.getCanonicalName())) {
                notificationFromIntent = SilentNoticeEvent.fromBundle(intent.getExtras());
            }
        }
        return notificationFromIntent;

    }

}
