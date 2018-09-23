package sanchez.sanchez.sergio.bullkeeper.core.events.model;

import android.content.Intent;
import sanchez.sanchez.sergio.utils.IVisitable;
import sanchez.sanchez.sergio.utils.IVisitor;

public interface IEvent<T extends IVisitor> extends IVisitable<T> {

    /**
     * To Intent
     * @return
     */
    Intent toIntent();
}
