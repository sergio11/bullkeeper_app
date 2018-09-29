package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DeviceGroupModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerService;
import sanchez.sanchez.sergio.bullkeeper.ui.services.NotificationHandlerService;

/**
 * Service Component
 */
@PerService
@Component(
        dependencies = ApplicationComponent.class,
        modules = { DeviceGroupModule.class } )
public interface ServiceComponent {

    /**
     * Inject into Notification Handler Service
     * @param notificationHandlerService
     */
    void inject(final NotificationHandlerService notificationHandlerService);

}
