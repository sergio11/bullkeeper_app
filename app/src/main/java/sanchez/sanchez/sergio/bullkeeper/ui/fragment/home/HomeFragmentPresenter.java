package sanchez.sanchez.sergio.bullkeeper.ui.fragment.home;

import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.parents.GetParentInformationInteract;
import sanchez.sanchez.sergio.domain.interactor.parents.GetSelfChildrenInteract;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Home Presenter
 */
public final class HomeFragmentPresenter extends SupportPresenter<IHomeView> {

    /**
     * Get Self Information Interact
     */
    private final GetSelfChildrenInteract getSelfChildrenInteract;
    /**
     * Get Parent Information Interact
     */
    private final GetParentInformationInteract getParentInformationInteract;

    /**
     * @param getSelfChildrenInteract
     * @param getParentInformationInteract
     */
    @Inject
    public HomeFragmentPresenter(final GetSelfChildrenInteract getSelfChildrenInteract,
                                 final GetParentInformationInteract getParentInformationInteract){
        this.getParentInformationInteract = getParentInformationInteract;
        this.getSelfChildrenInteract = getSelfChildrenInteract;
    }

    /**
     * Init
     */
    @Override
    public void init() {
        super.init();
        this.getParentInformationInteract.attachDisposablesTo(compositeDisposable);
        this.getSelfChildrenInteract.attachDisposablesTo(compositeDisposable);
    }

    /**
     * Load Profile Information
     */
    public void loadProfileInformation(){

        if(isViewAttached() && getView() != null){
            getView().showProgressDialog(R.string.home_load_profile_information_progress);
        }

        getParentInformationInteract.execute(new LoadProfileObserver(), null);
        getSelfChildrenInteract.execute(new LoadChildrenObserver(GetSelfChildrenInteract.GetChildrenApiErrors.class), null);
    }

    /**
     * Load Profile Observer
     */
    public class LoadProfileObserver extends BasicCommandCallBackWrapper<ParentEntity>  {

        /**
         * On Success
         * @param parentEntity
         */
        @Override
        protected void onSuccess(ParentEntity parentEntity) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onUserProfileLoaded(parentEntity);
            }
        }
    }


    /**
     * Load Children Observer
     */
    public class LoadChildrenObserver extends CommandCallBackWrapper<List<SonEntity>, GetSelfChildrenInteract.GetChildrenApiErrors.IGetChildrenApiErrorVisitor,
            GetSelfChildrenInteract.GetChildrenApiErrors> implements GetSelfChildrenInteract.GetChildrenApiErrors.IGetChildrenApiErrorVisitor {

        /**
         * Load Children Observer
         * @param apiErrors
         */
        public LoadChildrenObserver(Class<GetSelfChildrenInteract.GetChildrenApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param children
         */
        @Override
        protected void onSuccess(List<SonEntity> children) {
            if (isViewAttached() && getView() != null) {
                getView().onChildrenLoaded(children);
            }
        }

        /**
         * Visit No Children Found For Self Parent
         * @param error
         */
        @Override
        public void visitNoChildrenFoundForSelfParent(GetSelfChildrenInteract.GetChildrenApiErrors error) {
            if (isViewAttached() && getView() != null) {
                getView().onNoChildrenFounded();
            }
        }
    }


}
