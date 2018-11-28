package sanchez.sanchez.sergio.bullkeeper.ui.fragment.profile;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.guardians.GetGuardianInformationInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.GetSelfChildrenInteract;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;

/**
 * Profile Presenter
 */
public final class ProfileFragmentPresenter extends SupportPresenter<IProfileView> {

    /**
     * Get Self Information Interact
     */
    private final GetSelfChildrenInteract getSelfChildrenInteract;
    /**
     * Get Parent Information Interact
     */
    private final GetGuardianInformationInteract getGuardianInformationInteract;


    /**
     * @param getSelfChildrenInteract
     * @param getGuardianInformationInteract
     */
    @Inject
    public ProfileFragmentPresenter(final GetSelfChildrenInteract getSelfChildrenInteract,
                                    final GetGuardianInformationInteract getGuardianInformationInteract){
        this.getGuardianInformationInteract = getGuardianInformationInteract;
        this.getSelfChildrenInteract = getSelfChildrenInteract;
    }

    /**
     * Init
     */
    @Override
    public void onInit() {
        super.onInit();
        loadProfileInformation();
    }

    /**
     * Load Profile Information
     */
    public void loadProfileInformation(){

        if(isViewAttached() && getView() != null){
            getView().showProgressDialog(R.string.home_load_profile_information_progress);
        }

        getGuardianInformationInteract.execute(new LoadProfileObserver(), null);
        getSelfChildrenInteract.execute(new LoadChildrenObserver(GetSelfChildrenInteract.GetChildrenApiErrors.class), null);
    }

    /**
     * Load Profile Observer
     */
    public class LoadProfileObserver extends BasicCommandCallBackWrapper<GuardianEntity>  {

        /**
         * On Success
         * @param guardianEntity
         */
        @Override
        protected void onSuccess(GuardianEntity guardianEntity) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onUserProfileLoaded(guardianEntity);
            }
        }
    }


    /**
     * Load Children Observer
     */
    public class LoadChildrenObserver extends CommandCallBackWrapper<ChildrenOfSelfGuardianEntity, GetSelfChildrenInteract.GetChildrenApiErrors.IGetChildrenApiErrorVisitor,
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
        protected void onSuccess(ChildrenOfSelfGuardianEntity children) {
            if (isViewAttached() && getView() != null) {
                getView().onChildrenLoaded(children);
            }
        }

        /**
         * Visit No Children Found For Self Guardian
         * @param error
         */
        @Override
        public void visitNoChildrenFoundForSelfGuardian(GetSelfChildrenInteract.GetChildrenApiErrors error) {
            if (isViewAttached() && getView() != null) {
                getView().onNoChildrenFounded();
            }
        }
    }



}
