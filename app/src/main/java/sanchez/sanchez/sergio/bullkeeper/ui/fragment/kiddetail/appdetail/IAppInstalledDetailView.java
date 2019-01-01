package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.appdetail;

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

    /**
     * On App Rule Update Successfully
     */
    void onAppRuleUpdateSuccessfully();

    /**
     * On Update App Rule Fail
     */
    void onUpdateAppRuleFail();

    /**
     * On App Status Changed Successfully
     */
    void onAppStatusChangedSuccessfully();

    /**
     * On App Status Changed Failed
     */
    void onAppStatusChangedFailed();

}
