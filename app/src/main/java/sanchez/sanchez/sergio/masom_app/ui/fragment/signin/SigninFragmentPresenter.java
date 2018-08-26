package sanchez.sanchez.sergio.masom_app.ui.fragment.signin;

import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.interactor.accounts.SigninInteract;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportPresenter;
import sanchez.sanchez.sergio.masom_app.utils.PreferencesManager;
import timber.log.Timber;

/**
 * Intro Presenter
 */
public final class SigninFragmentPresenter extends SupportPresenter<ISigninView> {

    /**
     * Signin Interact
     */
    private final SigninInteract signinInteract;

    /**
     * Preferences Manager
     */
    private final PreferencesManager preferencesManager;

    /**
     * Signin Fragment Presenter
     * @param signinInteract
     */
    @Inject
    public SigninFragmentPresenter(final SigninInteract signinInteract,
                                   final PreferencesManager preferencesManager){
        this.signinInteract = signinInteract;
        this.preferencesManager = preferencesManager;
    }

    /**
     * Init Presenter
     */
    @Override
    public void init() {
        super.init();
        this.signinInteract.attachDisposablesTo(compositeDisposable);
    }

    /**
     * Sign in
     * @param mail
     * @param password
     */
    public void signin(final String mail, final String password) {
        Preconditions.checkNotNull(mail, "Mail can not be null");
        Preconditions.checkNotNull(password, "Password can not be null");
        Timber.d("Mail: %s, password: %s", mail, password);

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.authenticating_wait);

        // Execute Signin Interact
        signinInteract.execute(new SigninObserver(SigninApiErrors.class), SigninInteract.Params.create(mail, password));

    }


    /**
     * Signin Observer
     */
    private final class SigninObserver extends CommandCallBackWrapper<String, SigninApiErrors.ISigninApiErrorVisitor,
            SigninApiErrors> implements SigninApiErrors.ISigninApiErrorVisitor {

        /**
         *
         * @param apiErrors
         */
        public SigninObserver(Class<SigninApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param authToken
         */
        @Override
        protected void onSuccess(String authToken) {
            // Save Token on preferences
            preferencesManager.setAuthToken(authToken);
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onLoginSuccess();
            }
        }

        @Override
        public void visitBadCredentials(SigninApiErrors error) {
            Timber.e("Bad Credentials Error");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onBadCredentials();
            }
        }

        @Override
        public void visitAccountDisabled(SigninApiErrors errors) {
            Timber.e("Account Disabled");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAccountDisabled();
            }
        }
    }

    /**
     * Signin Api Errors
     */
    public enum SigninApiErrors implements ISupportVisitable<SigninApiErrors.ISigninApiErrorVisitor> {

        /**
         * Bad Credentials Error
         */
        BAD_CREDENTIALS(){
            @Override
            public <E> void accept(ISigninApiErrorVisitor visitor, E data) {
                visitor.visitBadCredentials(this);
            }
        },

        ACCOUNT_DISABLED() {
            @Override
            public <E> void accept(ISigninApiErrorVisitor visitor, E data) {
                visitor.visitAccountDisabled(this);
            }
        };


        /**
         * Signin Api Error Visitor
         */
        public interface ISigninApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit Bad Credentials
             * @param error
             */
            void visitBadCredentials(final SigninApiErrors error);

            /**
             * Visit Account Disabled
             * @param errors
             */
            void visitAccountDisabled(final SigninApiErrors errors);
        }

    }


}
