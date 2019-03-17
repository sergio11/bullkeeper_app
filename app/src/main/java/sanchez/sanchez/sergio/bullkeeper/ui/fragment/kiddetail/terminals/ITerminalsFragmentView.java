package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminals;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;

/**
 * Terminal Fragment View
 */
interface ITerminalsFragmentView extends ISupportLCEView<TerminalEntity> {

    /**
     * On Lock Screen Status Changed Successfully
     */
    void onLockScreenStatusChangedSuccessfully();

    /**
     * On Lock Screen Status Changed Failed
     */
    void onLockScreenStatusChangedFailed();

}
