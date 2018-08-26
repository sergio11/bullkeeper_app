package sanchez.sanchez.sergio.bullkeeper.notification.model;

import android.content.Intent;

import sanchez.sanchez.sergio.bullkeeper.notification.local.ILocalSystemNotificationVisitor;
import sanchez.sanchez.sergio.utils.IVisitable;

public interface INotification extends IVisitable<ILocalSystemNotificationVisitor> {

    /**
     * To Intent
     * @return
     */
    Intent toIntent();

}
