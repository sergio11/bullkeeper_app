package sanchez.sanchez.sergio.bullkeeper.sse;

import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Sse Event Type Enum
 */
public enum SseEventTypeEnum implements ISupportVisitable<SseEventTypeEnum.ISseEventsVisitor> {

    // Kid Request Created Event
    KID_REQUEST_CREATED_EVENT(){
        @Override
        public <E> void accept(ISseEventsVisitor visitor, E data) {
            visitor.visitKidRequestCreatedEvent(this, (String) data);
        }
    },
    // Current Location Update Event
    CURRENT_LOCATION_UPDATE_EVENT(){
        @Override
        public <E> void accept(ISseEventsVisitor visitor, E data) {
            visitor.visitCurrentLocationUpdateEvent(this, (String) data);
        }
    },

    // New App Installed Event
    NEW_APP_INSTALLED_EVENT(){
        @Override
        public <E> void accept(ISseEventsVisitor visitor, E data) {
            visitor.visitNewAppInstalledEvent(this, (String) data);
        }
    },

    // Uninstall App Event
    UNINSTALL_APP_EVENT(){
        @Override
        public <E> void accept(ISseEventsVisitor visitor, E data) {
            visitor.visitUninstallAppEvent(this, (String) data);
        }
    },

    // Message Saved Event
    MESSAGE_SAVED_EVENT(){
        @Override
        public <E> void accept(ISseEventsVisitor visitor, E data) {
            visitor.visitMessageSavedEvent(this, (String) data);
        }
    },

    // Deleted Messages Event
    DELETED_MESSAGES_EVENT() {
        @Override
        public <E> void accept(ISseEventsVisitor visitor, E data) {
            visitor.visitDeletedMessagesEvent(this, (String) data);
        }
    },

    // Deleted Conversation Event
    DELETED_CONVERSATION_EVENT(){
        @Override
        public <E> void accept(ISseEventsVisitor visitor, E data) {
            visitor.visitDeletedConversationEvent(this, (String) data);
        }
    },

    // All Conversation Deleted Event
    ALL_CONVERSATION_DELETED_EVENT(){
        @Override
        public <E> void accept(ISseEventsVisitor visitor, E data) {
            visitor.visitAllConversationDeletedEvent(this, (String) data);
        }
    },
    // All Messages Deleted Event
    ALL_MESSAGES_DELETED_EVENT(){
        @Override
        public <E> void accept(ISseEventsVisitor visitor, E data) {
            visitor.visitAllMessagesDeletedEvent(this, (String) data);
        }
    },
    // Set Messages As Viewed Event
    SET_MESSAGES_AS_VIEWED_EVENT() {
        @Override
        public <E> void accept(ISseEventsVisitor visitor, E data) {
            visitor.visitSetMessagesAsViewedEvent(this, (String) data);
        }
    },

    // Terminal Status Changed
    TERMINAL_STATUS_CHANGED() {
        @Override
        public <E> void accept(ISseEventsVisitor visitor, E data) {
            visitor.visitTerminalStatusChangedEvent(this, (String) data);
        }
    };

    /**
     * Sse Events Visitor
     */
    public interface ISseEventsVisitor extends ISupportVisitor {

        /**
         * Visit Kid Request Created Event
         * @param sseEventTypeEnum
         */
        void visitKidRequestCreatedEvent(final SseEventTypeEnum sseEventTypeEnum, final String message);

        /**
         * Visit Current Location Update Event
         * @param sseEventTypeEnum
         * @param message
         */
        void visitCurrentLocationUpdateEvent(final SseEventTypeEnum sseEventTypeEnum, final String message);

        /**
         * Visit New App Installed Event
         * @param sseEventTypeEnum
         * @param message
         */
        void visitNewAppInstalledEvent(final SseEventTypeEnum sseEventTypeEnum, final String message);

        /**
         * Visit Uninstall App Event
         * @param sseEventTypeEnum
         * @param message
         */
        void visitUninstallAppEvent(final SseEventTypeEnum sseEventTypeEnum, final String message);

        /**
         * Visit Message Saved Event
         * @param sseEventTypeEnum
         * @param message
         */
        void visitMessageSavedEvent(final SseEventTypeEnum sseEventTypeEnum, final String message);

        /**
         * Visit Deleted Messages Event
         * @param sseEventTypeEnum
         * @param message
         */
        void visitDeletedMessagesEvent(final SseEventTypeEnum sseEventTypeEnum, final String message);

        /**
         * Visit All Conversation Deleted Event
         * @param sseEventTypeEnum
         * @param message
         */
        void visitAllConversationDeletedEvent(final SseEventTypeEnum sseEventTypeEnum, final String message);

        /**
         * Visit Deleted Conversation Event
         * @param sseEventTypeEnum
         * @param message
         */
        void visitDeletedConversationEvent(final SseEventTypeEnum sseEventTypeEnum, final String message);

        /**
         * Visit All Message Deleted Event
         * @param sseEventTypeEnum
         * @param message
         */
        void visitAllMessagesDeletedEvent(final SseEventTypeEnum sseEventTypeEnum, final String message);

        /**
         * Visit Set Messages As Viewed Event
         * @param sseEventTypeEnum
         * @param message
         */
        void visitSetMessagesAsViewedEvent(final SseEventTypeEnum sseEventTypeEnum, final String message);

        /**
         * Visit Terminal Status Changed Event
         * @param sseEventTypeEnum
         * @param message
         */
        void visitTerminalStatusChangedEvent(final SseEventTypeEnum sseEventTypeEnum, final String message);
    }

}
