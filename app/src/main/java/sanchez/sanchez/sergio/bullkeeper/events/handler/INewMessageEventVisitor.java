package sanchez.sanchez.sergio.bullkeeper.events.handler;

import sanchez.sanchez.sergio.bullkeeper.events.impl.NewConversationMessageEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * New Message Event Visitor
 */
public interface INewMessageEventVisitor extends IVisitor {

    /**
     * Visit New Message Event
     * @param newConversationMessageEvent
     */
    void visit(final NewConversationMessageEvent newConversationMessageEvent);

}
