package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids;

import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.domain.models.GuardianRolesEnum;

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
    void navigateToMyKidDetail(final String identity, final GuardianRolesEnum guardianRolesEnum);

    /**
     * Navigate To Kids Results
     * @param identity
     */
    void navigateToKidsResults(final String identity);

    /**
     * Navigate To Add Child
     */
    void navigateToAddChild();

    /**
     * Navigate To Son Alerts
     * @param sonIdentity
     */
    void navigateToSonAlerts(final String sonIdentity);

    /**
     * Navigate to Relations
     * @param identity
     */
    void navigateToRelations(final String identity);

    /**
     * Navigate To Invitations
     */
    void navigateToInvitations();

    /**
     * Navigate To Conversation List
     */
    void navigateToConversationList();


}
