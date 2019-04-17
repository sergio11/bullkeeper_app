package sanchez.sanchez.sergio.bullkeeper.ui.fragment.password;

import java.util.LinkedHashMap;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.interactor.accounts.ResetPasswordInteract;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import timber.log.Timber;

/**
 * Forgot Password Presenter
 */
public final class ForgotPasswordFragmentPresenter extends SupportPresenter<IForgotPasswordView> {

    private final ResetPasswordInteract resetPasswordInteract;

    @Inject
    public ForgotPasswordFragmentPresenter(final ResetPasswordInteract resetPasswordInteract){
        this.resetPasswordInteract = resetPasswordInteract;
    }


    /**
     * Forgot password
     * @param mail
     */
    public void forgotPassword(final String mail) {
        Timber.d("Mail: %s ", mail);
        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.resetting_password_wait);
        // Execute Reset Password Interact
        resetPasswordInteract.execute(new ResetPasswordObserver(ResetPasswordInteract.ResetPasswordApiErrors.class), ResetPasswordInteract.Params.create(mail));
    }

    /**
     * Reset Password Observer
     */
    private final class ResetPasswordObserver extends CommandCallBackWrapper<String, ResetPasswordInteract.ResetPasswordApiErrors.IResetPasswordApiErrorVisitor,
            ResetPasswordInteract.ResetPasswordApiErrors> implements ResetPasswordInteract.ResetPasswordApiErrors.IResetPasswordApiErrorVisitor {

        /**
         * Reset Password Observer
         * @param apiErrors
         */
        public ResetPasswordObserver(final Class<ResetPasswordInteract.ResetPasswordApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPasswordReset();
            }
        }

        /**
         * Visit Validation Error
         * @param apiErrors
         * @param errors
         */
        @Override
        public void visitValidationError(ResetPasswordInteract.ResetPasswordApiErrors apiErrors, LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors) {
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
