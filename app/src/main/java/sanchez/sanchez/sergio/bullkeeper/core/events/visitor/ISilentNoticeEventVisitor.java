package sanchez.sanchez.sergio.bullkeeper.core.events.visitor;

import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.SilentNoticeEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * Silent Notice Event Visitor
 */
public interface ISilentNoticeEventVisitor extends IVisitor {

    /**
     * Visit Silent Notice Event
     * @param noticeEvent
     */
    void visit(final SilentNoticeEvent noticeEvent);

}
