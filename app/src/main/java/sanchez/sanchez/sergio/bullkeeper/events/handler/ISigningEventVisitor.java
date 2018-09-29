package sanchez.sanchez.sergio.bullkeeper.events.handler;

import sanchez.sanchez.sergio.bullkeeper.events.impl.SigningEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * Signing Event Visitor
 */
public interface ISigningEventVisitor extends IVisitor {

    /**
     * Visit Signing Event
     * @param signingEvent
     */
    void visit(final SigningEvent signingEvent);

}
