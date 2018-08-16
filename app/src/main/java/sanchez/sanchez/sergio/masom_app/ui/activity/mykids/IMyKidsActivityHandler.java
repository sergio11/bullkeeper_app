package sanchez.sanchez.sergio.masom_app.ui.activity.mykids;

import sanchez.sanchez.sergio.masom_app.ui.support.IBasicActivityHandler;

/**
 * Home Activity Handler
 */
public interface IMyKidsActivityHandler extends IBasicActivityHandler {

    /**
     * Navigate To My Kids Profile
     * @param identity
     */
    void navigateToMyKidsProfile(final String identity);

    /**
     * Navigate To Comments
     * @param identity
     */
    void navigateToComments(final String identity);

    /**
     * Navigate To My Kid Detail
     * @param identity
     */
    void navigateToMyKidDetail(final String identity);

}
