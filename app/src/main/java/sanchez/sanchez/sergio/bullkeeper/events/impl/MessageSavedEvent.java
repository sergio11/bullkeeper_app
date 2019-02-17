package sanchez.sanchez.sergio.bullkeeper.events.impl;

import android.content.Intent;
import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.Date;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IMessageEventVisitor;
import sanchez.sanchez.sergio.data.net.models.response.PersonDTO;

/**
 * Message Saved Event
 */
public final class MessageSavedEvent extends SupportEvent<IMessageEventVisitor> {

    /**
     * Args
     */
    private static final String IDENTITY_ARG = "IDENTITY_ARG";
    private static final String TEXT_ARG = "TEXT_ARG";
    private static final String CREATE_AT_ARG = "CREATE_AT_ARG";
    private static final String CONVERSATION_ARG = "CONVERSATION_ARG";
    private static final String FROM_ARG = "FROM_ARG";
    private static final String TO_ARG = "TO_ARG";
    private static final String VIEWED_ARG = "VIEWED_ARG";

    /**
     *
     */
    private String identity;
    private String text;
    private Date createAt;
    private String conversation;
    private PersonDTO from;
    private PersonDTO to;
    private boolean viewed;

    /**
     *
     * @param identity
     * @param text
     * @param createAt
     * @param conversation
     * @param from
     * @param to
     * @param viewed
     */
    public MessageSavedEvent(final String identity, final String text, final Date createAt,
                             final String conversation, final PersonDTO from, final PersonDTO to,
                             boolean viewed) {
        this.identity = identity;
        this.text = text;
        this.createAt = createAt;
        this.conversation = conversation;
        this.from = from;
        this.to = to;
        this.viewed = viewed;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public PersonDTO getFrom() {
        return from;
    }

    public void setFrom(PersonDTO from) {
        this.from = from;
    }

    public PersonDTO getTo() {
        return to;
    }

    public void setTo(PersonDTO to) {
        this.to = to;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    /**
     * To Intent
     * @return
     */
    @Override
    public Intent toIntent() {
        final Intent intent = new Intent(getClass().getCanonicalName());
        final Bundle bundle = new Bundle();
        bundle.putString(IDENTITY_ARG, identity);
        bundle.putString(TEXT_ARG, text);
        bundle.putSerializable(CREATE_AT_ARG, createAt);
        bundle.putString(CONVERSATION_ARG, conversation);
        bundle.putSerializable(FROM_ARG, from);
        bundle.putSerializable(TO_ARG, to);
        bundle.putBoolean(VIEWED_ARG, viewed);
        intent.putExtras(bundle);
        return intent;
    }

    /**
     * Accept
     * @param visitor
     */
    @Override
    public void accept(final IMessageEventVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * From Bundle
     * @param bundle
     * @return
     */
    public static MessageSavedEvent fromBundle(final Bundle bundle) {
        Preconditions.checkNotNull(bundle, "Bundle can not be null");
        final String identity = bundle.getString(IDENTITY_ARG, "");
        final String text = bundle.getString(TEXT_ARG, "");
        final Date createAt = (Date) bundle.getSerializable(CREATE_AT_ARG);
        final String conversation = bundle.getString(CONVERSATION_ARG);
        final PersonDTO from = (PersonDTO) bundle.getSerializable(FROM_ARG);
        final PersonDTO to = (PersonDTO) bundle.getSerializable(TO_ARG);
        final boolean viewed = bundle.getBoolean(VIEWED_ARG);
        return new MessageSavedEvent(identity, text, createAt, conversation,
                from, to, viewed);
    }
}
