package sanchez.sanchez.sergio.bullkeeper.events.handler;

import sanchez.sanchez.sergio.bullkeeper.events.impl.AppUninstalledEvent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.NewAppInstalledEvent;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 * App Event Visitor
 */
public interface IAppEventVisitor extends IVisitor {

    /**
     * Visit New App Installed Event
     * @param newAppInstalledEvent
     */
    void visit(final NewAppInstalledEvent newAppInstalledEvent);

    /**
     * Visit App Uninstalled Event
     * @param appUninstalledEvent
     */
    void visit(final AppUninstalledEvent appUninstalledEvent);

}
