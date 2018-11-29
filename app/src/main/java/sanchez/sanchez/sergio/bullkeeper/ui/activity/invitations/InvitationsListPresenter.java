package sanchez.sanchez.sergio.bullkeeper.ui.activity.invitations;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;

/**
 * Invitations List Presenter
 */
public final class InvitationsListPresenter extends SupportLCEPresenter<IInvitationsListView> {


    /**
     *
     * @param
     */
    @Inject
    public InvitationsListPresenter() {
        super();
    }


    /**
     * Load Data
     */
    @Override
    public void loadData() {

    }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        loadData();
    }

    /**
     * Clear Invitations
     */
    public void clearInvitations(){}


    /**
     * Delete Invitation
     * @param invitation
     */
    public void deleteInvitation(final String invitation) {
        Preconditions.checkNotNull(invitation, "Invitation Id can not be null");
        Preconditions.checkState(!invitation.isEmpty(), "Invitation can not be empty");

    }


}
