package sanchez.sanchez.sergio.bullkeeper.ui.activity.userprofile;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.parents.GetParentInformationInteract;
import sanchez.sanchez.sergio.domain.interactor.parents.UpdateSelfInformationInteract;
import sanchez.sanchez.sergio.domain.models.ParentEntity;

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
     * User Profile Presenter
     * @param getParentInformationInteract
     */
    @Inject
    public UserProfilePresenter(final GetParentInformationInteract getParentInformationInteract,
                                final UpdateSelfInformationInteract updateSelfInformationInteract) {
        this.getParentInformationInteract = getParentInformationInteract;
        this.updateSelfInformationInteract = updateSelfInformationInteract;
    }


    /**
     * Init
     */
    @Override
    public void init() {
        super.init();
        this.getParentInformationInteract.attachDisposablesTo(compositeDisposable);
        this.updateSelfInformationInteract.attachDisposablesTo(compositeDisposable);

    }

    /**
     * load Profile Info
     */
    public void loadProfileInfo(){

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_profile_information);

        getParentInformationInteract.execute(new GetSelfInformationObserver(GetSelfInformationApiErrors.class), null);
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


        updateSelfInformationInteract.execute(new UpdateSelfInformationObserver(UpdateSelfInformationApiErrors.class),
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


        updateSelfInformationInteract.execute(new UpdateSelfInformationObserver(UpdateSelfInformationApiErrors.class),
                UpdateSelfInformationInteract.Params.create(name, surname, birthday, email,
                        tfno, profileImage));

    }

    /**
     * Get Self Information Observer
     */
    private final class GetSelfInformationObserver extends CommandCallBackWrapper<ParentEntity, GetSelfInformationApiErrors.IGetSelfInformationApiErrorVisitor,
            GetSelfInformationApiErrors> implements GetSelfInformationApiErrors.IGetSelfInformationApiErrorVisitor {


        public GetSelfInformationObserver(Class<GetSelfInformationApiErrors> apiErrors) {
            super(apiErrors);
        }

        @Override
        public void visitNoChildrenFoundForSelfParent(GetSelfInformationApiErrors error) {

        }

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
     * Signin Api Errors
     */
    public enum GetSelfInformationApiErrors implements ISupportVisitable<GetSelfInformationApiErrors.IGetSelfInformationApiErrorVisitor> {

        /**
         * No Children Found For Self Parent
         */
        NO_CHILDREN_FOUND_FOR_SELF_PARENT() {
            @Override
            public <E> void accept(IGetSelfInformationApiErrorVisitor visitor, E data) {
                visitor.visitNoChildrenFoundForSelfParent(this);
            }
        };

        /**
         * Get Self Information API Error Visitor
         */
        public interface IGetSelfInformationApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit No Children Found For Self Parent
             *
             * @param error
             */
            void visitNoChildrenFoundForSelfParent(final GetSelfInformationApiErrors error);

        }
    }


    /**
     * Update Self Information Observer
     */
    private final class UpdateSelfInformationObserver extends CommandCallBackWrapper<ParentEntity, UpdateSelfInformationApiErrors.IUpdateSelfInformationApiErrorVisitor,
            UpdateSelfInformationApiErrors> implements UpdateSelfInformationApiErrors.IUpdateSelfInformationApiErrorVisitor {


        public UpdateSelfInformationObserver(Class<UpdateSelfInformationApiErrors> apiErrors) {
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
        public void visitValidationError(UpdateSelfInformationApiErrors apiErrors, LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors) {
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
        public void visitFailedToUploadImage(final UpdateSelfInformationApiErrors apiErrors) {
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
        public void visitUploadFileIsTooLarge(final UpdateSelfInformationApiErrors apiErrors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().showNoticeDialog(R.string.profile_image_is_too_large);
            }
        }
    }

    /**
     * Update Api Errors
     */
    public enum UpdateSelfInformationApiErrors implements ISupportVisitable<UpdateSelfInformationApiErrors.IUpdateSelfInformationApiErrorVisitor> {

        /**
         * Validation Errors
         */
        VALIDATION_ERROR(){
            @Override
            public <E> void accept(IUpdateSelfInformationApiErrorVisitor visitor, E data) {
                visitor.visitValidationError(this, (LinkedHashMap<String, List<LinkedHashMap<String, String>>>) data);
            }
        },
        /**
         * Failed To Upload Image
         */
        FAILED_TO_UPLOAD_IMAGE() {
            @Override
            public <E> void accept(IUpdateSelfInformationApiErrorVisitor visitor, E data) {
                visitor.visitFailedToUploadImage(this);
            }
        },

        /**
         * Upload File Is Too Large
         */
        UPLOAD_FILE_IS_TOO_LARGE(){
            @Override
            public <E> void accept(IUpdateSelfInformationApiErrorVisitor visitor, E data) {
                visitor.visitUploadFileIsTooLarge(this);
            }
        };

        /**
         * Update Self Information API Error Visitor
         */
        public interface IUpdateSelfInformationApiErrorVisitor extends ISupportVisitor {

            /**
             * Visit Validation Error
             * @param apiErrors
             * @param errors
             */
            void visitValidationError(final UpdateSelfInformationApiErrors apiErrors, final LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors);

            /**
             * Visit Failed To Upload Image
             * @param apiErrors
             */
            void visitFailedToUploadImage(final UpdateSelfInformationApiErrors apiErrors);

            /**
             * Visit Upload File Is Too Large
             * @param apiErrors
             */
            void visitUploadFileIsTooLarge(final UpdateSelfInformationApiErrors apiErrors);

        }
    }

}
