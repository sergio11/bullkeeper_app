package sanchez.sanchez.sergio.masom_app.notification.local;

import sanchez.sanchez.sergio.masom_app.notification.model.impl.BasicNotification;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 *
 */
public interface ILocalSystemNotificationVisitor extends IVisitor {

    /**
     * Visit Basic Notification
     * @param basicNotification
     */
    void visit(final BasicNotification basicNotification);

}
