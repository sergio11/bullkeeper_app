package sanchez.sanchez.sergio.bullkeeper.events.handler;

import sanchez.sanchez.sergio.bullkeeper.events.impl.KidLocationUpdatedEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * Kid Location Event Visitor
 */
public interface IKidLocationEventVisitor extends IVisitor {

    /**
     * Visit Kid Location Updated Event
     * @param kidLocationUpdatedEvent
     */
    void visit(final KidLocationUpdatedEvent kidLocationUpdatedEvent);

}
