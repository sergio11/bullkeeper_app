package sanchez.sanchez.sergio.bullkeeper.ui.fragment.signup;

import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.interactor.accounts.RegisterParentInteract;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPresenter;

/**
 * Intro Presenter
 */
public final class SignupFragmentPresenter extends SupportPresenter<ISignupView> {

    private final RegisterParentInteract registerParentInteract;

    @Inject
    public SignupFragmentPresenter(final RegisterParentInteract registerParentInteract){
        this.registerParentInteract = registerParentInteract;
    }

    @Override
    public void init() {
        super.init();
        this.registerParentInteract.attachDisposablesTo(compositeDisposable);
    }

    /**
     *
     * @param name
     * @param surname
     * @param birthday
     * @param email
     * @param password
     * @param confirmPassword
     */
    public void signup(final String name, String surname, String birthday,
                       final String email, final String password, final String confirmPassword,
                       final String telephone){

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.signup_in_progress);

        registerParentInteract.execute(new SignupObserver(SignupApiErrors.class),
                RegisterParentInteract.Params.create(name, surname, birthday, email, password, confirmPassword,
                        telephone));

    }

    /**
     * Signup Observer
     */
    private final class SignupObserver extends CommandCallBackWrapper<ParentEntity, SignupApiErrors.ISignupApiErrorVisitor,
            SignupApiErrors> implements SignupApiErrors.ISignupApiErrorVisitor {

        /**
         *
         * @param apiErrors
         */
        public SignupObserver(Class<SignupApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final ParentEntity response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSignupSuccess(response);
            }
        }

        /**
         * Visit Validation Error
         * @param apiErrors
         * @param errors
         */
        @Override
        public void visitValidationError(SignupApiErrors apiErrors, LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors) {
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
     * Signup Api Errors
     */
    public enum SignupApiErrors implements ISupportVisitable<SignupApiErrors.ISignupApiErrorVisitor> {

        /**
         * Validation Errors
         */
        VALIDATION_ERROR(){
            @Override
            public <E> void accept(ISignupApiErrorVisitor visitor, E data) {
                visitor.visitValidationError(this, (LinkedHashMap<String, List<LinkedHashMap<String, String>>>) data);
            }
        };


        /**
         * Signup Api Error Visitor
         */
        public interface ISignupApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit Validation Errors
             * @param apiErrors
             */
            void visitValidationError(final SignupApiErrors apiErrors, final LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors);
        }

    }


}
