package sanchez.sanchez.sergio.masom_app.ui.activity.home;

import sanchez.sanchez.sergio.masom_app.ui.support.IBasicActivityHandler;

/**
 * Home Activity Handler
 */
public interface IHomeActivityHandler extends IBasicActivityHandler {

    /**
     * Go to My Kids
     */
    void goToMyKids();


    /**
     * Go to Alert Detail
     * @param identity
     */
    void goToAlertDetail(final String identity);

}
