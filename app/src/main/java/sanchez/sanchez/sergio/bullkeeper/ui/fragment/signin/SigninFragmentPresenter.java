package sanchez.sanchez.sergio.bullkeeper.ui.fragment.signin;

import com.facebook.AccessToken;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.interactor.accounts.SigninFacebookInteract;
import sanchez.sanchez.sergio.domain.interactor.accounts.SigninInteract;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPresenter;
import sanchez.sanchez.sergio.bullkeeper.utils.PreferencesManager;
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
     * Signin Facebook Interact
     */
    private final SigninFacebookInteract signinFacebookInteract;

    /**
     * Signin Fragment Presenter
     * @param signinInteract
     */
    @Inject
    public SigninFragmentPresenter(final SigninInteract signinInteract, final PreferencesManager preferencesManager,
                                   final SigninFacebookInteract signinFacebookInteract){
        this.signinInteract = signinInteract;
        this.preferencesManager = preferencesManager;
        this.signinFacebookInteract = signinFacebookInteract;
    }

    /**
     * Init Presenter
     */
    @Override
    public void init() {
        super.init();
        this.signinInteract.attachDisposablesTo(compositeDisposable);
        this.signinFacebookInteract.attachDisposablesTo(compositeDisposable);
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
        signinInteract.execute(new SigninObserver(SigninInteract.SigninApiErrors.class), SigninInteract.Params.create(mail, password));

    }

    /**
     * Signin
     * @param accessToken
     */
    public void signin(final AccessToken accessToken) {
        Preconditions.checkNotNull(accessToken, "AccessToken can not be null");
        Preconditions.checkNotNull(accessToken.getToken(), "Token can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.authenticating_wait);

        // Execute Signin Facebook
        signinFacebookInteract.execute(new SigninObserver(SigninInteract.SigninApiErrors.class), SigninFacebookInteract.Params.create(accessToken.getToken()));
    }


    /**
     * Signin Observer
     */
    private final class SigninObserver extends CommandCallBackWrapper<String, SigninInteract.SigninApiErrors.ISigninApiErrorVisitor,
            SigninInteract.SigninApiErrors> implements SigninInteract.SigninApiErrors.ISigninApiErrorVisitor {

        /**
         *
         * @param apiErrors
         */
        public SigninObserver(Class<SigninInteract.SigninApiErrors> apiErrors) {
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
        public void visitBadCredentials(SigninInteract.SigninApiErrors error) {
            Timber.e("Bad Credentials Error");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onBadCredentials();
            }
        }

        @Override
        public void visitAccountDisabled(SigninInteract.SigninApiErrors errors) {
            Timber.e("Account Disabled");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAccountDisabled();
            }
        }

        @Override
        public void visitAccountPendingToBeRemove(SigninInteract.SigninApiErrors error) {
            Timber.e("Account Pending To Be Remove");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().showNoticeDialog(R.string.account_pending_to_be_deleted);
            }
        }
    }


}
