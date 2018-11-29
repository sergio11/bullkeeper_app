package sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids;

import android.os.Bundle;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.guardians.GetSelfChildrenInteract;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;

/**
 * My Kids Fragment Presenter
 */
public final class MyKidsFragmentPresenter extends SupportLCEPresenter<IMyKidsView> {

    private final GetSelfChildrenInteract getSelfChildrenInteract;

    /**
     * @param getSelfChildrenInteract
     */
    @Inject
    public MyKidsFragmentPresenter(final GetSelfChildrenInteract getSelfChildrenInteract) {
        this.getSelfChildrenInteract = getSelfChildrenInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {

        // Execute Get Self Children
        getSelfChildrenInteract.execute(new GetChildrenObserver(GetSelfChildrenInteract.GetChildrenApiErrors.class), null);
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
