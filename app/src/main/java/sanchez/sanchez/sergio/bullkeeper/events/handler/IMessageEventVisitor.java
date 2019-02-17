package sanchez.sanchez.sergio.bullkeeper.events.handler;

import sanchez.sanchez.sergio.bullkeeper.events.impl.AllConversationDeletedEvent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.AllMessagesDeletedEvent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.DeletedConversationEvent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.DeletedMessagesEvent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.MessageSavedEvent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.SetMessagesAsViewedEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * Message Event Visitor
 */
public interface IMessageEventVisitor extends IVisitor {

    /**
     * Visit Message Saved Event
     * @param messageSavedEvent
     */
    void visit(final MessageSavedEvent messageSavedEvent);

    /**
     * Visit Deleted Conversation Event
     * @param deletedConversationEvent
     */
    void visit(final DeletedConversationEvent deletedConversationEvent);

    /**
     * Visit All Conversation Deleted Event
     * @param allConversationDeletedEvent
     */
    void visit(final AllConversationDeletedEvent allConversationDeletedEvent);

    /**
     * Visit Deleted Messages Event
     * @param deletedMessagesEvent
     */
    void visit(final DeletedMessagesEvent deletedMessagesEvent);

    /**
     * Visit All Messages Deleted Event
     * @param allMessagesDeletedEvent
     */
    void visit(final AllMessagesDeletedEvent allMessagesDeletedEvent);

    /**
     * Visit Set Messages As Viewed Event
     * @param setMessagesAsViewedEvent
     */
    void visit(final SetMessagesAsViewedEvent setMessagesAsViewedEvent);
}
