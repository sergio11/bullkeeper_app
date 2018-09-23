package sanchez.sanchez.sergio.bullkeeper.core.events.model.impl;

import android.content.Intent;
import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.bullkeeper.core.events.visitor.INoticeEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;

/**
 * Notice Event
 */
public final class NoticeEvent extends SupportEvent<INoticeEventVisitor> {

    private static String EVENT_TITLE = "TITLE";
    private static String EVENT_CONTENT = "CONTENT";

    // Event Title
    private final String title;
    // Event Content
    private final String content;

    /**
     * @param title
     * @param content
     */
    public NoticeEvent(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    /**
     * Visit Basic Notification
     * @param visitor
     */
    @Override
    public void accept(final INoticeEventVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * To Bundle
     * @return
     */
    @Override
    public Intent toIntent() {
        final Intent notificationIntent = new Intent(getClass().getCanonicalName());
        final Bundle notificationBundle = new Bundle();
        notificationBundle.putString(EVENT_TITLE, title);
        notificationBundle.putString(EVENT_CONTENT, content);
        notificationIntent.putExtras(notificationBundle);
        return notificationIntent;
    }

    /**
     * From Bundle
     * @param notificationBundle
     * @return
     */
    public static NoticeEvent fromBundle(final Bundle notificationBundle) {
        Preconditions.checkNotNull(notificationBundle, "Notification Bundle can not be null");
        Preconditions.checkState(notificationBundle.containsKey(EVENT_TITLE), "Notification Bundle can not contain notification title");
        Preconditions.checkState(notificationBundle.containsKey(EVENT_CONTENT), "Notification Bundle can not contain notification content");
        final String notificationTitle = notificationBundle.getString(EVENT_TITLE);
        final String notificationContent = notificationBundle.getString(EVENT_CONTENT);
        return new NoticeEvent(notificationTitle, notificationContent);
    }
}
