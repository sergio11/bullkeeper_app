package sanchez.sanchez.sergio.bullkeeper.ui.activity.terminaldetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

/**
 * Terminal Detail Activity Handler
 */
public interface ITerminalDetailActivityHandler extends IBasicActivityHandler {

    /**
     * Make Phone Call
     * @param phoneNumber
     */
    void makePhoneCall(final String phoneNumber);

}
