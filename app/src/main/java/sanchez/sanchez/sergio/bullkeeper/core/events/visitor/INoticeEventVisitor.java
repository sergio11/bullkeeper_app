package sanchez.sanchez.sergio.bullkeeper.core.events.visitor;

import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.NoticeEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * Notice Event Visitor
 */
public interface INoticeEventVisitor extends IVisitor {

    /**
     * Visit Notice Event
     * @param noticeEvent
     */
    void visit(final NoticeEvent noticeEvent);

}
