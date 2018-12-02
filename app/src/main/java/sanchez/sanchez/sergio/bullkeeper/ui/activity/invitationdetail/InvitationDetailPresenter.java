package sanchez.sanchez.sergio.bullkeeper.ui.activity.invitationdetail;

import android.os.Bundle;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.AcceptSupervisedChildrenNoConfirmedInteract;
import sanchez.sanchez.sergio.domain.interactor.children.DeleteSupervisedChildrenNoConfirmedInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetSupervisedChildrenNoConfirmedDetailInteract;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;

/**
 * Invitation Detail Presenter
 */
public final class InvitationDetailPresenter extends SupportPresenter<IInvitationDetailView> {

    public static final String INVITATION_ID_ARG = "INVITATION_ID_ARG";

    /**
     * Get Supervised Children
     */
    private final GetSupervisedChildrenNoConfirmedDetailInteract getSupervisedChildrenNoConfirmedDetailInteract;

    /**
     * Accept Supervised Children No Confirmed Interact
     */
    private final AcceptSupervisedChildrenNoConfirmedInteract acceptSupervisedChildrenNoConfirmedInteract;

    /**
     * Delete Supervised Children No Confirmed Iteract
     */
    private final DeleteSupervisedChildrenNoConfirmedInteract deleteSupervisedChildrenNoConfirmedInteract;

    /**
     *
     * @param getSupervisedChildrenNoConfirmedDetailInteract
     */
    @Inject
    public InvitationDetailPresenter(
            final GetSupervisedChildrenNoConfirmedDetailInteract getSupervisedChildrenNoConfirmedDetailInteract,
            final AcceptSupervisedChildrenNoConfirmedInteract acceptSupervisedChildrenNoConfirmedInteract,
            final DeleteSupervisedChildrenNoConfirmedInteract deleteSupervisedChildrenNoConfirmedInteract) {
        super();
        this.getSupervisedChildrenNoConfirmedDetailInteract = getSupervisedChildrenNoConfirmedDetailInteract;
        this.acceptSupervisedChildrenNoConfirmedInteract = acceptSupervisedChildrenNoConfirmedInteract;
        this.deleteSupervisedChildrenNoConfirmedInteract = deleteSupervisedChildrenNoConfirmedInteract;
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

        if(args.isEmpty() || !args.containsKey(INVITATION_ID_ARG) ||
                !appUtils.isValidString(args.getString(INVITATION_ID_ARG)))
            throw new IllegalArgumentException("You must provide invitation id");

        getSupervisedChildrenNoConfirmedDetailInteract.execute(new GetSupervisedChildrenNoConfirmedDetailObservable(),
                GetSupervisedChildrenNoConfirmedDetailInteract.Params.create(args.getString(INVITATION_ID_ARG)));
    }

    /**
     * Accept Invitation
     */
    public void acceptInvitation(){

        if (isViewAttached() && getView() != null) {
            getView().showProgressDialog(R.string.generic_loading_text);
        }

        acceptSupervisedChildrenNoConfirmedInteract.execute(new AcceptSupervisedChildrenNoConfirmedObservable(),
                AcceptSupervisedChildrenNoConfirmedInteract.Params.create(args.getString(INVITATION_ID_ARG)));
    }

    /**
     * Delete Invitation
     */
    public void deleteInvitation(){

        if (isViewAttached() && getView() != null) {
            getView().showProgressDialog(R.string.generic_loading_text);
        }

        deleteSupervisedChildrenNoConfirmedInteract.execute(new DeleteSupervisedChildrenNoConfirmedObservable(),
                DeleteSupervisedChildrenNoConfirmedInteract.Params.create(args.getString(INVITATION_ID_ARG)));

    }

    /**
     * Get Supervised Children No Confirmed Detail
     */
    public class GetSupervisedChildrenNoConfirmedDetailObservable extends BasicCommandCallBackWrapper<SupervisedChildrenEntity> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(SupervisedChildrenEntity response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSupervisedChildrenEntityLoaded(response);
            }
        }
    }

    /**
     * Accept Supervised Children No Confirmed Observable
     */
    public class AcceptSupervisedChildrenNoConfirmedObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onInvitationAccepted();
            }

        }
    }


    /**
     * Delete Supervised Children No Confirmed Observable
     */
    public class DeleteSupervisedChildrenNoConfirmedObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onInvitationDeleted();
            }

        }
    }

}
