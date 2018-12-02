package sanchez.sanchez.sergio.bullkeeper.ui.activity.invitationdetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;

/**
 * Invitation Detail View
 */
public interface IInvitationDetailView extends ISupportView {

    /**
     * On Supervised Children Entity loaded
     * @param supervisedChildrenEntity
     */
    void onSupervisedChildrenEntityLoaded(final SupervisedChildrenEntity supervisedChildrenEntity);

    /**
     * On Invitation Accepted
     */
    void onInvitationAccepted();

    /**
     * On Invitation Deleted
     */
    void onInvitationDeleted();
}
