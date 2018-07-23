package sanchez.sanchez.sergio.masom_app.notification.remote;

import sanchez.sanchez.sergio.masom_app.notification.model.impl.BasicNotification;
import sanchez.sanchez.sergio.utils.IVisitor;

public interface IRemoteSystemNotificationVisitor extends IVisitor {

    /**
     * Basic Notification
     * @param basicNotification
     */
    void visit(final BasicNotification basicNotification);


}
