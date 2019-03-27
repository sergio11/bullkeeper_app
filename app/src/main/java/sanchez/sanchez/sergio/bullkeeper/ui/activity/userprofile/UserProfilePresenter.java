package sanchez.sanchez.sergio.bullkeeper.ui.activity.userprofile;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.guardians.ChangeUserEmailInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.ChangeUserPasswordInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.DeleteAccountInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.GetGuardianInformationInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.UpdateSelfInformationInteract;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import timber.log.Timber;

/**
 * User Profile Presenter
 */
public final class UserProfilePresenter extends SupportPresenter<IUserProfileView> {

    /**
     * Get Parent Information Interact
     */
    private final GetGuardianInformationInteract getGuardianInformationInteract;

    /**
     * Update Self Information Interact
     */
    private final UpdateSelfInformationInteract updateSelfInformationInteract;

    /**
     * Delete Account Interact
     */
    private final DeleteAccountInteract deleteAccountInteract;

    /**
     * Change User Email Interact
     */
    private final ChangeUserEmailInteract changeUserEmailInteract;

    /**
     * Change User Password Interact
     */
    private final ChangeUserPasswordInteract changeUserPasswordInteract;

    /**
     * User Profile Presenter
     * @param getGuardianInformationInteract
     * @param updateSelfInformationInteract
     * @param deleteAccountInteract
     * @param changeUserEmailInteract
     */
    @Inject
    public UserProfilePresenter(final GetGuardianInformationInteract getGuardianInformationInteract,
                                final UpdateSelfInformationInteract updateSelfInformationInteract,
                                final DeleteAccountInteract deleteAccountInteract,
                                final ChangeUserEmailInteract changeUserEmailInteract,
                                final ChangeUserPasswordInteract changeUserPasswordInteract) {
        this.getGuardianInformationInteract = getGuardianInformationInteract;
        this.updateSelfInformationInteract = updateSelfInformationInteract;
        this.deleteAccountInteract = deleteAccountInteract;
        this.changeUserEmailInteract = changeUserEmailInteract;
        this.changeUserPasswordInteract = changeUserPasswordInteract;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        // Load Profile Info
        loadProfileInfo();
    }


    /**
     * load Profile Info
     */
    public void loadProfileInfo(){

        getGuardianInformationInteract.execute(new GetParentInformationObserver(), null);
    }

    /**
     * Update Profile
     * @param name
     * @param surname
     * @param birthday
     * @param tfno
     * @param visible
     */
    public void updateProfile(final String name, String surname, String birthday,
                              final String tfno, final boolean visible) {

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.updating_profile_data_progress);


        updateSelfInformationInteract.execute(new UpdateSelfInformationObserver(UpdateSelfInformationInteract.UpdateSelfInformationApiErrors.class),
                UpdateSelfInformationInteract.Params.create(name, surname, birthday,
                        tfno, visible));

    }

    /**
     * Update Profile
     * @param name
     * @param surname
     * @param birthday
     * @param tfno
     * @param visible
     * @param profileImage
     */
    public void updateProfile(final String name, String surname, String birthday,
                               final String tfno, final boolean visible, final String profileImage) {

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.updating_profile_data_progress);


        updateSelfInformationInteract.execute(new UpdateSelfInformationObserver(UpdateSelfInformationInteract.UpdateSelfInformationApiErrors.class),
                UpdateSelfInformationInteract.Params.create(name, surname, birthday,
                        tfno, visible, profileImage));

    }

    /**
     * Change User Email
     * @param currentEmail
     * @param newEmail
     */
    public void changeUserEmail(final String currentEmail, final String newEmail){
        Preconditions.checkNotNull(currentEmail, "Current Email can not be null");
        Preconditions.checkNotNull(newEmail, "New Email can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        changeUserEmailInteract.execute(new ChangeUserEmailObserver(ChangeUserEmailInteract.ChangeUserEmailApiErrors.class),
                ChangeUserEmailInteract.Params.create(currentEmail, newEmail));


    }

    /**
     * Change User Password
     * @param newPassword
     * @param repeatNewPassword
     */
    public void changeUserPassword(final String newPassword, final String repeatNewPassword){
        Preconditions.checkNotNull(newPassword, "New Password can not be null");
        Preconditions.checkNotNull(repeatNewPassword, "Repeat New Password can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        changeUserPasswordInteract.execute(new ChangeUserPasswordObserver(
                ChangeUserPasswordInteract.ChangeUserPasswordApiErrors.class
        ), ChangeUserPasswordInteract.Params.create(newPassword, repeatNewPassword));
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
    private final class GetParentInformationObserver extends BasicCommandCallBackWrapper<GuardianEntity> {

        /**
         * On Success
         * @param guardianEntity
         */
        @Override
        protected void onSuccess(GuardianEntity guardianEntity) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSelfInformationLoaded(guardianEntity);
            }

        }
    }



    /**
     * Update Self Information Observer
     */
    private final class UpdateSelfInformationObserver extends CommandCallBackWrapper<GuardianEntity, UpdateSelfInformationInteract.UpdateSelfInformationApiErrors.IUpdateSelfInformationApiErrorVisitor,
            UpdateSelfInformationInteract.UpdateSelfInformationApiErrors> implements UpdateSelfInformationInteract.UpdateSelfInformationApiErrors.IUpdateSelfInformationApiErrorVisitor {


        public UpdateSelfInformationObserver(Class<UpdateSelfInformationInteract.UpdateSelfInformationApiErrors> apiErrors) {
            super(apiErrors);
        }


        @Override
        protected void onSuccess(GuardianEntity guardianEntity) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSelfInformationUpdate(guardianEntity);
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


    /**
     * Change User Email Observer
     */
    private final class ChangeUserEmailObserver extends CommandCallBackWrapper<String, ChangeUserEmailInteract.ChangeUserEmailApiErrors.IChangeUserEmailApiErrorVisitor,
            ChangeUserEmailInteract.ChangeUserEmailApiErrors> implements ChangeUserEmailInteract.ChangeUserEmailApiErrors.IChangeUserEmailApiErrorVisitor {


        public ChangeUserEmailObserver(Class<ChangeUserEmailInteract.ChangeUserEmailApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onEmailChangedSuccessfully();
            }
        }


        /**
         *
         * @param apiErrors
         * @param errors
         */
        @Override
        public void visitValidationError(ChangeUserEmailInteract.ChangeUserEmailApiErrors apiErrors, LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                if(errors != null && !errors.isEmpty() && errors.containsKey("field_errors")) {
                    getView().onValidationErrors(errors.get("field_errors"));
                } else {
                    getView().showNoticeDialog(R.string.forms_is_not_valid);
                }
            }
        }
    }


    /**
     * Change User Password Observer
     */
    private final class ChangeUserPasswordObserver extends CommandCallBackWrapper<String, ChangeUserPasswordInteract.ChangeUserPasswordApiErrors.IChangeUserPasswordApiErrorVisitor,
            ChangeUserPasswordInteract.ChangeUserPasswordApiErrors> implements ChangeUserPasswordInteract.ChangeUserPasswordApiErrors.IChangeUserPasswordApiErrorVisitor {


        public ChangeUserPasswordObserver(Class<ChangeUserPasswordInteract.ChangeUserPasswordApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPasswordChangedSuccessfully();
            }
        }


        @Override
        public void visitValidationError(ChangeUserPasswordInteract.ChangeUserPasswordApiErrors apiErrors, LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                if(errors != null && !errors.isEmpty() && errors.containsKey("field_errors")) {
                    getView().onValidationErrors(errors.get("field_errors"));
                } else {
                    getView().showNoticeDialog(R.string.forms_is_not_valid);
                }
            }
        }
    }

}
