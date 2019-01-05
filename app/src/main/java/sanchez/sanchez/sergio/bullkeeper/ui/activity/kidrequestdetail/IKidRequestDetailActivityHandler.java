package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequestdetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

/**
 * Kid Request Detail Activity Handler
 */
public interface IKidRequestDetailActivityHandler extends IBasicActivityHandler {

    /**
     * Navigate To Conversation Message List
     * @param kid
     */
    void navigateToConversationMessagesList(final String kid);

}
