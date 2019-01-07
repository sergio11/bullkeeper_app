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
    }
    ;

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
    }

}
