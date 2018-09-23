package sanchez.sanchez.sergio.bullkeeper.ui.fragment.signup;

import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.interactor.accounts.RegisterParentInteract;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;

/**
 * Intro Presenter
 */
public final class SignupFragmentPresenter extends SupportPresenter<ISignupView> {

    private final RegisterParentInteract registerParentInteract;

    @Inject
    public SignupFragmentPresenter(final RegisterParentInteract registerParentInteract){
        this.registerParentInteract = registerParentInteract;
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

        registerParentInteract.execute(new SignupObserver(RegisterParentInteract.SignupApiErrors.class),
                RegisterParentInteract.Params.create(name, surname, birthday, email, password, confirmPassword,
                        telephone));

    }

    /**
     * Signup Observer
     */
    private final class SignupObserver extends CommandCallBackWrapper<ParentEntity, RegisterParentInteract.SignupApiErrors.ISignupApiErrorVisitor,
            RegisterParentInteract.SignupApiErrors> implements RegisterParentInteract.SignupApiErrors.ISignupApiErrorVisitor {

        /**
         *
         * @param apiErrors
         */
        public SignupObserver(Class<RegisterParentInteract.SignupApiErrors> apiErrors) {
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
        public void visitValidationError(RegisterParentInteract.SignupApiErrors apiErrors, LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors) {
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
