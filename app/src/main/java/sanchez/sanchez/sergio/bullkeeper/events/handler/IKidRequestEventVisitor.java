package sanchez.sanchez.sergio.bullkeeper.events.handler;

import sanchez.sanchez.sergio.bullkeeper.events.impl.kidRequestCreatedEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * Kid Request Event Visitor
 */
public interface IKidRequestEventVisitor extends IVisitor {

    /**
     * Visit Kid Request Created Event
     * @param kidRequestCreatedEvent
     */
    void visit(final kidRequestCreatedEvent kidRequestCreatedEvent);


}
