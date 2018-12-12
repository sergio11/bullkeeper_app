package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminaldetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;

/**
 * Terminal Detail View
 */
public interface ITerminalDetailView extends ISupportView {

    /**
     * On Terminal Detail Loaded
     * @param terminalDetailEntity
     */
    void onTerminalDetailLoaded(final TerminalDetailEntity terminalDetailEntity);

    /**
     * On Terminal Success Deleted
     */
    void onTerminalSuccessDeleted();


}
