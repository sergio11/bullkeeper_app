package sanchez.sanchez.sergio.bullkeeper.events.handler;

import sanchez.sanchez.sergio.bullkeeper.events.impl.LogoutEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * Logout Event Visitor
 */
public interface ILogoutEventVisitor extends IVisitor {

    /**
     * Visit Logout Event
     * @param signingEvent
     */
    void visit(final LogoutEvent signingEvent);

}
