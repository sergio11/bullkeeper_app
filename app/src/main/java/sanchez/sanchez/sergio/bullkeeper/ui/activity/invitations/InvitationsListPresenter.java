package sanchez.sanchez.sergio.bullkeeper.ui.activity.invitations;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.DeleteAllSupervisedChildrenNoConfirmedInteract;
import sanchez.sanchez.sergio.domain.interactor.children.DeleteSupervisedChildrenNoConfirmedInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetSupervisedChildrenNoConfirmedInteract;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;

/**
 * Invitations List Presenter
 */
public final class InvitationsListPresenter extends SupportLCEPresenter<IInvitationsListView> {

    /**
     * Get Supervised Children No Confirmed Interact
     */
    private final GetSupervisedChildrenNoConfirmedInteract getSupervisedChildrenNoConfirmedInteract;

    /**
     * Delete All Supervised Children No Confirmed Interact
     */
    private final DeleteAllSupervisedChildrenNoConfirmedInteract deleteAllSupervisedChildrenNoConfirmedInteract;


    /**
     * Delete Supervised Children No Confirmed Interact
     */
    private final DeleteSupervisedChildrenNoConfirmedInteract deleteSupervisedChildrenNoConfirmedInteract;


    /**
     *
     * @param getSupervisedChildrenNoConfirmedInteract
     * @param deleteAllSupervisedChildrenNoConfirmedInteract
     * @param deleteSupervisedChildrenNoConfirmedInteract
     */
    @Inject
    public InvitationsListPresenter(final GetSupervisedChildrenNoConfirmedInteract getSupervisedChildrenNoConfirmedInteract,
                                    final DeleteAllSupervisedChildrenNoConfirmedInteract deleteAllSupervisedChildrenNoConfirmedInteract,
                                    final DeleteSupervisedChildrenNoConfirmedInteract deleteSupervisedChildrenNoConfirmedInteract) {
        this.getSupervisedChildrenNoConfirmedInteract = getSupervisedChildrenNoConfirmedInteract;
        this.deleteAllSupervisedChildrenNoConfirmedInteract = deleteAllSupervisedChildrenNoConfirmedInteract;
        this.deleteSupervisedChildrenNoConfirmedInteract = deleteSupervisedChildrenNoConfirmedInteract;
    }


    /**
     * Load Data
     */
    @Override
    public void loadData() {

        getSupervisedChildrenNoConfirmedInteract.execute(
                new GetSupervisedChildrenNoConfirmedObservable(GetSupervisedChildrenNoConfirmedInteract
                        .GetSupervisedChildrenNoConfirmedApiErrors.class), null);
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
    public void clearInvitations(){
        // Delete Supervised Children
        deleteAllSupervisedChildrenNoConfirmedInteract
                .execute(new ClearAllInvitationsObservable(), null);
    }


    /**
     * Delete Invitation
     * @param invitation
     */
    public void deleteInvitation(final String invitation) {
        Preconditions.checkNotNull(invitation, "Invitation Id can not be null");
        Preconditions.checkState(!invitation.isEmpty(), "Invitation can not be empty");

        deleteSupervisedChildrenNoConfirmedInteract.execute(new ClearInvitationObservable(),
                DeleteSupervisedChildrenNoConfirmedInteract.Params.create(invitation));
    }


    /**
     * Get Supervised Children No Confirmed Observable
     */
    public class GetSupervisedChildrenNoConfirmedObservable extends CommandCallBackWrapper<List<SupervisedChildrenEntity>,
            GetSupervisedChildrenNoConfirmedInteract.GetSupervisedChildrenNoConfirmedApiErrors.IGetSupervisedChildrenNoConfirmedApiErrorVisitor,
            GetSupervisedChildrenNoConfirmedInteract.GetSupervisedChildrenNoConfirmedApiErrors>
            implements GetSupervisedChildrenNoConfirmedInteract.GetSupervisedChildrenNoConfirmedApiErrors
                    .IGetSupervisedChildrenNoConfirmedApiErrorVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetSupervisedChildrenNoConfirmedObservable(Class<GetSupervisedChildrenNoConfirmedInteract.GetSupervisedChildrenNoConfirmedApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Sucess
         * @param response
         */
        @Override
        protected void onSuccess(final List<SupervisedChildrenEntity> response) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }
        }

        /**
         * Visit No Supervised Children No Confirmed Found
         * @param error
         */
        @Override
        public void visitNoSupervisedChildrenNoConfirmedFound(
                final GetSupervisedChildrenNoConfirmedInteract.GetSupervisedChildrenNoConfirmedApiErrors error) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }


    /**
     * Clear All Invitations
     */
    public class ClearAllInvitationsObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAllInvitationsCleared();
            }
        }
    }

    /**
     * Clear Invitation
     */
    public class ClearInvitationObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onInvitationCleared();
            }
        }
    }
}
