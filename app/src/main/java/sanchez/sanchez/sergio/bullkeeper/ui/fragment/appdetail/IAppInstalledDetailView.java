package sanchez.sanchez.sergio.bullkeeper.ui.fragment.appdetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;

/**
 * App Installed Detail View
 */
public interface IAppInstalledDetailView extends ISupportView {

    /**
     * On App Installed Detail Loaded
     * @param appInstalledEntity
     */
    void onAppInstalledDetailLoaded(final AppInstalledEntity appInstalledEntity);


}
