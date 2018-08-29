package sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids;

import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.parents.GetSelfChildrenInteract;
import sanchez.sanchez.sergio.domain.models.SonEntity;

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

    @Override
    public void init() {
        super.init();
        this.getSelfChildrenInteract.attachDisposablesTo(compositeDisposable);
    }


    /**
     * Load Data
     */
    @Override
    public void loadData() {

        if (isViewAttached() && getView() != null) {
            getView().showProgressDialog(R.string.loading_information_of_children);
        }
        // Execute Get Self Children
        getSelfChildrenInteract.execute(new GetChildrenObserver(GetChildrenApiErrors.class), null);
    }


    /**
     * Get Children Observer
     */
    private final class GetChildrenObserver extends CommandCallBackWrapper<List<SonEntity>, GetChildrenApiErrors.IGetChildrenApiErrorVisitor,
            GetChildrenApiErrors> implements GetChildrenApiErrors.IGetChildrenApiErrorVisitor {

        /**
         * @param apiErrors
         */
        public GetChildrenObserver(Class<GetChildrenApiErrors> apiErrors) {
            super(apiErrors);
        }

        @Override
        protected void onSuccess(List<SonEntity> myKids) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                if(myKids != null && !myKids.isEmpty())
                    getView().onDataLoaded(myKids);
                else
                    getView().onNoDataFound();
            }
        }

        /**
         * Visit No Children Found For Self Parent
         *
         * @param error
         */
        @Override
        public void visitNoChildrenFoundForSelfParent(GetChildrenApiErrors error) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }

    /**
     * Signin Api Errors
     */
    public enum GetChildrenApiErrors implements ISupportVisitable<GetChildrenApiErrors.IGetChildrenApiErrorVisitor> {

        /**
         * No Children Found For Self Parent
         */
        NO_CHILDREN_FOUND_FOR_SELF_PARENT() {
            @Override
            public <E> void accept(IGetChildrenApiErrorVisitor visitor, E data) {
                visitor.visitNoChildrenFoundForSelfParent(this);
            }
        };

        /**
         * Get Children Api Error Visitor
         */
        public interface IGetChildrenApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit No Children Found For Self Parent
             *
             * @param error
             */
            void visitNoChildrenFoundForSelfParent(final GetChildrenApiErrors error);

        }
    }

}
