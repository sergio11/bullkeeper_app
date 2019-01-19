package sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids;



import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportSearchLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.guardians.GetSelfChildrenInteract;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;

/**
 * My Kids Fragment Presenter
 */
public final class MyKidsFragmentPresenter extends SupportSearchLCEPresenter<IMyKidsView> {

    private final GetSelfChildrenInteract getSelfChildrenInteract;

    /**
     * @param getSelfChildrenInteract
     */
    @Inject
    public MyKidsFragmentPresenter(final GetSelfChildrenInteract getSelfChildrenInteract) {
        this.getSelfChildrenInteract = getSelfChildrenInteract;
    }

    /**
     * Load Date
     * @param queryText
     */
    @Override
    public void loadData(final String queryText) {
        Preconditions.checkNotNull(queryText, "Query Text can not be null");

        getSelfChildrenInteract.execute(new GetChildrenObserver(GetSelfChildrenInteract.GetChildrenApiErrors.class), GetSelfChildrenInteract.Params.create(queryText));

    }

    /**
     * Get Children Observer
     */
    private final class GetChildrenObserver extends CommandCallBackWrapper<ChildrenOfSelfGuardianEntity, GetSelfChildrenInteract.GetChildrenApiErrors.IGetChildrenApiErrorVisitor,
            GetSelfChildrenInteract.GetChildrenApiErrors> implements GetSelfChildrenInteract.GetChildrenApiErrors.IGetChildrenApiErrorVisitor {

        /**
         * @param apiErrors
         */
        public GetChildrenObserver(Class<GetSelfChildrenInteract.GetChildrenApiErrors> apiErrors) {
            super(apiErrors);
        }

        @Override
        protected void onSuccess(final ChildrenOfSelfGuardianEntity myKids) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();

                if(myKids.getConfirmed() > 0)
                    getView().onDataLoaded(myKids.getSupervisedChildrenEntities());
                else
                    getView().onNoDataFound();


                if(myKids.getNoConfirmed() > 0)
                    getView().onPendingRequestsAvailable(myKids.getNoConfirmed());
                else
                    getView().onNoPendingRequestsAvailable();

            }
        }

        /**
         * Visit No Children Found For Self Parent
         *
         * @param error
         */
        @Override
        public void visitNoChildrenFoundForSelfGuardian(GetSelfChildrenInteract.GetChildrenApiErrors error) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }


}
