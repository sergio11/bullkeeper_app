package sanchez.sanchez.sergio.masom_app.notification.model;

import android.content.Intent;

import sanchez.sanchez.sergio.masom_app.notification.local.ILocalSystemNotificationVisitor;
import sanchez.sanchez.sergio.utils.IVisitable;

public interface INotification extends IVisitable<ILocalSystemNotificationVisitor> {

    /**
     * To Intent
     * @return
     */
    Intent toIntent();

}
