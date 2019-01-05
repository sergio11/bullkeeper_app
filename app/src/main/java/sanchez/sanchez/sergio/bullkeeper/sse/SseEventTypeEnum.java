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
    }

}
