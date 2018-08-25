package sanchez.sanchez.sergio.masom_app.ui.fragment.password;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.interactor.accounts.ResetPasswordInteract;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportPresenter;
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
     * Init
     */
    @Override
    public void init() {
        super.init();
        this.resetPasswordInteract.attachDisposablesTo(compositeDisposable);
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
        resetPasswordInteract.execute(new ResetPasswordObserver(ResetPasswordApiErrors.class), ResetPasswordInteract.Params.create(mail));
    }

    /**
     * Reset Password Observer
     */
    private final class ResetPasswordObserver extends CommandCallBackWrapper<String, ResetPasswordApiErrors.IResetPasswordApiErrorVisitor,
            ResetPasswordApiErrors> implements ResetPasswordApiErrors.IResetPasswordApiErrorVisitor {

        /**
         * Reset Password Observer
         * @param apiErrors
         */
        public ResetPasswordObserver(final Class<ResetPasswordApiErrors> apiErrors) {
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
            }
        }

        /**
         * Visit Validation Error
         * @param apiErrors
         * @param errors
         */
        @Override
        public void visitValidationError(ResetPasswordApiErrors apiErrors, LinkedHashMap<String, String> errors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                if(errors != null && !errors.isEmpty() && errors.containsKey("email")) {
                    getView().showNoticeDialog(errors.get("email"));
                } else {
                    getView().showNoticeDialog(R.string.email_is_not_valid);
                }
            }
        }

        @Override
        public void visitGenericError(ResetPasswordApiErrors apiErrors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onOtherException();
            }
        }
    }

    /**
     * Reset Password Api Errors
     */
    public enum ResetPasswordApiErrors  implements ISupportVisitable<ResetPasswordApiErrors.IResetPasswordApiErrorVisitor> {

        /**
         * Bad Credentials Error
         */
        VALIDATION_ERROR(){
            @Override
            public <E> void accept(IResetPasswordApiErrorVisitor visitor, E data) {
                visitor.visitValidationError(this, (LinkedHashMap<String, String>) data);
            }
        },

        GENERIC_ERROR() {
            @Override
            public <E> void accept(IResetPasswordApiErrorVisitor visitor, E data) {
                visitor.visitGenericError(this);
            }
        };

        /**
         * Reset Password Api Error Visitor
         */
        public interface IResetPasswordApiErrorVisitor extends SupportPresenter.ISupportVisitor {
            /**
             * Visit Bad Credentials
             * @param apiErrors
             */
            void visitValidationError(final ResetPasswordApiErrors apiErrors, final LinkedHashMap<String, String> errors);

            /**
             * Visit Generic Error
             * @param apiErrors
             */
            void visitGenericError(final ResetPasswordApiErrors apiErrors);
        }

    }

}
