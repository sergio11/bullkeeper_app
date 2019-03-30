package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.guardians.GetSupervisedChildConfirmedByIdInteract;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;

/**
 * My Kids Detail Presenter
 */
public final class MyKidsDetailPresenter extends SupportPresenter<IMyKidsDetailView> {

    public final static String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Supervised Child Confirmed By Id Interact
     */
    private final GetSupervisedChildConfirmedByIdInteract getSupervisedChildConfirmedByIdInteract;

    /**
     *
     * @param getSupervisedChildConfirmedByIdInteract
     */
    @Inject
    public MyKidsDetailPresenter(final GetSupervisedChildConfirmedByIdInteract getSupervisedChildConfirmedByIdInteract) {
        this.getSupervisedChildConfirmedByIdInteract = getSupervisedChildConfirmedByIdInteract;
    }

    /**
     * Load Son Data
     */
    public void loadKidData(final String kid){

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        getSupervisedChildConfirmedByIdInteract.execute(new GetSupervisedChildConfirmedByIdObservable(GetSupervisedChildConfirmedByIdInteract.GetSupervisedChildConfirmedByIdApiErrors.class),
                GetSupervisedChildConfirmedByIdInteract.Params.create(kid));
    }


    /**
     * Get Supervised Child Confirmed By Id Observable
     */
    public class GetSupervisedChildConfirmedByIdObservable extends CommandCallBackWrapper<KidGuardianEntity,
            GetSupervisedChildConfirmedByIdInteract.GetSupervisedChildConfirmedByIdApiErrors.IGetSupervisedChildConfirmedByIdApiErrorVisitor,
            GetSupervisedChildConfirmedByIdInteract.GetSupervisedChildConfirmedByIdApiErrors>
            implements GetSupervisedChildConfirmedByIdInteract.GetSupervisedChildConfirmedByIdApiErrors.IGetSupervisedChildConfirmedByIdApiErrorVisitor {


        /**
         *
         * @param apiErrors
         */
        public GetSupervisedChildConfirmedByIdObservable(Class<GetSupervisedChildConfirmedByIdInteract.GetSupervisedChildConfirmedByIdApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(KidGuardianEntity response) {
            if (isViewAttached() && getView() != null){
                getView().hideProgressDialog();
                getView().onKidGuardianLoaded(response);
            }
        }

        /**
         *
         * @param error
         */
        @Override
        public void visitSupervisedChildrenConfirmedNotFound(final GetSupervisedChildConfirmedByIdInteract.GetSupervisedChildConfirmedByIdApiErrors error) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSupervisedChildrenConfirmedNotFound();
            }
        }
    }

}
