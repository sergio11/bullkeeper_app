package sanchez.sanchez.sergio.bullkeeper.ui.activity.userprofile;

import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.parents.DeleteAccountInteract;
import sanchez.sanchez.sergio.domain.interactor.parents.GetParentInformationInteract;
import sanchez.sanchez.sergio.domain.interactor.parents.UpdateSelfInformationInteract;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import timber.log.Timber;

/**
 * User Profile Presenter
 */
public final class UserProfilePresenter extends SupportPresenter<IUserProfileView> {

    /**
     * Get Parent Information Interact
     */
    private final GetParentInformationInteract getParentInformationInteract;

    /**
     * Update Self Information Interact
     */
    private final UpdateSelfInformationInteract updateSelfInformationInteract;

    /**
     * Delete Account Interact
     */
    private final DeleteAccountInteract deleteAccountInteract;

    /**
     * User Profile Presenter
     * @param getParentInformationInteract
     */
    @Inject
    public UserProfilePresenter(final GetParentInformationInteract getParentInformationInteract,
                                final UpdateSelfInformationInteract updateSelfInformationInteract,
                                final DeleteAccountInteract deleteAccountInteract) {
        this.getParentInformationInteract = getParentInformationInteract;
        this.updateSelfInformationInteract = updateSelfInformationInteract;
        this.deleteAccountInteract = deleteAccountInteract;
    }


    /**
     * Init
     */
    @Override
    public void onInit() {
        super.onInit();
        this.getParentInformationInteract.attachDisposablesTo(compositeDisposable);
        this.updateSelfInformationInteract.attachDisposablesTo(compositeDisposable);
        this.deleteAccountInteract.attachDisposablesTo(compositeDisposable);

    }

    /**
     * load Profile Info
     */
    public void loadProfileInfo(){

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_profile_information);

        getParentInformationInteract.execute(new GetParentInformationObserver(), null);
    }

    /**
     * Update Profile
     * @param name
     * @param surname
     * @param birthday
     * @param email
     * @param tfno
     */
    public void updateProfile(final String name, String surname, String birthday,
                              final String email, final String tfno) {

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.updating_profile_data_progress);


        updateSelfInformationInteract.execute(new UpdateSelfInformationObserver(UpdateSelfInformationInteract.UpdateSelfInformationApiErrors.class),
                UpdateSelfInformationInteract.Params.create(name, surname, birthday, email,
                        tfno));

    }

    /**
     * Update Profile
     * @param name
     * @param surname
     * @param birthday
     * @param email
     * @param tfno
     * @param profileImage
     */
    public void updateProfile(final String name, String surname, String birthday,
                              final String email, final String tfno, final String profileImage) {

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.updating_profile_data_progress);


        updateSelfInformationInteract.execute(new UpdateSelfInformationObserver(UpdateSelfInformationInteract.UpdateSelfInformationApiErrors.class),
                UpdateSelfInformationInteract.Params.create(name, surname, birthday, email,
                        tfno, profileImage));

    }

    /**
     * Delete Account
     */
    public void deleteAccount(){

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.delete_account_in_progress);


        deleteAccountInteract.execute(new DeleteAccountObserver(), null);

    }

    /**
     * Get Self Information Observer
     */
    private final class GetParentInformationObserver extends BasicCommandCallBackWrapper<ParentEntity> {

        /**
         * On Success
         * @param parentEntity
         */
        @Override
        protected void onSuccess(ParentEntity parentEntity) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSelfInformationLoaded(parentEntity);
            }

        }
    }



    /**
     * Update Self Information Observer
     */
    private final class UpdateSelfInformationObserver extends CommandCallBackWrapper<ParentEntity, UpdateSelfInformationInteract.UpdateSelfInformationApiErrors.IUpdateSelfInformationApiErrorVisitor,
            UpdateSelfInformationInteract.UpdateSelfInformationApiErrors> implements UpdateSelfInformationInteract.UpdateSelfInformationApiErrors.IUpdateSelfInformationApiErrorVisitor {


        public UpdateSelfInformationObserver(Class<UpdateSelfInformationInteract.UpdateSelfInformationApiErrors> apiErrors) {
            super(apiErrors);
        }


        @Override
        protected void onSuccess(ParentEntity parentEntity) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSelfInformationUpdate(parentEntity);
            }
        }

        /**
         * Visit Validation Error
         * @param apiErrors
         * @param errors
         */
        @Override
        public void visitValidationError(UpdateSelfInformationInteract.UpdateSelfInformationApiErrors apiErrors, LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                if(errors != null && !errors.isEmpty() && errors.containsKey("field_errors")) {
                    getView().onValidationErrors(errors.get("field_errors"));
                } else {
                    getView().showNoticeDialog(R.string.forms_is_not_valid);
                }
            }
        }

        /**
         * Visit Failed To Upload Image
         * @param apiErrors
         */
        @Override
        public void visitFailedToUploadImage(final UpdateSelfInformationInteract.UpdateSelfInformationApiErrors apiErrors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().showNoticeDialog(R.string.upload_profile_image_error);
            }
        }

        /**
         * Visit Upload File Is Too Large
         * @param apiErrors
         */
        @Override
        public void visitUploadFileIsTooLarge(final UpdateSelfInformationInteract.UpdateSelfInformationApiErrors apiErrors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().showNoticeDialog(R.string.profile_image_is_too_large);
            }
        }
    }


    /**
     * Delete Account Observer
     */
    private final class DeleteAccountObserver extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param message
         */
        @Override
        protected void onSuccess(String message) {
            Timber.d("Message -> %s", message);
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAccountDeleted();
            }

        }
    }



}
