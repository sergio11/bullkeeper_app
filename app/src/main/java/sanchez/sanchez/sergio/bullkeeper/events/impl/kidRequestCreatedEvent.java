package sanchez.sanchez.sergio.bullkeeper.events.impl;

import android.content.Intent;
import android.os.Bundle;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IKidRequestEventVisitor;

/**
 * Kid Request Created Event
 */
public final class kidRequestCreatedEvent  extends SupportEvent<IKidRequestEventVisitor> {

    /**
     * Args
     */
    private static final String IDENTITY_ARG = "IDENTITY_ARG";
    private static final String TERMINAL_ARG = "TERMINAL_ARG";
    private static final String TYPE_ARG = "TYPE_ARG";

    /**
     *
     */
    private final String identity;
    private final String terminal;
    private final String type;

    /**
     * @param identity
     * @param terminal
     * @param type
     */
    public kidRequestCreatedEvent(final String identity, final String terminal, final String type) {
        this.identity = identity;
        this.terminal = terminal;
        this.type = type;
    }

    public String getIdentity() {
        return identity;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getType() {
        return type;
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
        bundle.putString(TERMINAL_ARG, terminal);
        bundle.putString(TYPE_ARG, type);
        intent.putExtras(bundle);
        return intent;
    }

    /**
     * Accept
     * @param visitor
     */
    @Override
    public void accept(IKidRequestEventVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * From Bundle
     * @param bundle
     * @return
     */
    public static kidRequestCreatedEvent fromBundle(final Bundle bundle) {
        Preconditions.checkNotNull(bundle, "Bundle can not be null");
        final String identity = bundle.getString(IDENTITY_ARG, "");
        final String terminal = bundle.getString(TERMINAL_ARG, "");
        final String type = bundle.getString(TYPE_ARG, "");
        return new kidRequestCreatedEvent(identity, terminal, type);
    }
}
