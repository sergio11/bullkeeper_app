package sanchez.sanchez.sergio.bullkeeper.ui.fragment.apprules;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;

/**
 * App Rules Fragment View
 */
interface IAppRulesFragmentView extends ISupportLCEView<AppInstalledEntity> {

    /***
     * On App Rules Updated Successfully
     */
    void onAppRulesUpdatedSuccessfully();

}
