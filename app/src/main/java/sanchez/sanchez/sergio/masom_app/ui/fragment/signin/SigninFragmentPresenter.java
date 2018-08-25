package sanchez.sanchez.sergio.masom_app.ui.fragment.signin;

import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.interactor.DefaultObserver;
import sanchez.sanchez.sergio.domain.interactor.accounts.SigninInteract;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportPresenter;
import sanchez.sanchez.sergio.masom_app.utils.PreferencesManager;
import timber.log.Timber;

/**
 * Intro Presenter
 */
public final class SigninFragmentPresenter extends SupportPresenter<ISigninView> {

    private final SigninInteract signinInteract;
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

        signinInteract.execute(new SigninObserver(), SigninInteract.Params.create(mail, password));

    }


    /**
     * Signin Observer
     */
    private final class SigninObserver extends DefaultObserver<String> {

        @Override public void onComplete() {


        }

        @Override public void onError(Throwable e) {

            Timber.e(e);

            if(isViewAttached() && getView() != null) {
                getView().onLoginFailed();
            }
        }

        @Override public void onNext(final String authToken) {

            // Save Token on preferences
            preferencesManager.setAuthToken(authToken);

            if(isViewAttached() && getView() != null) {
                getView().onLoginSuccess();
            }
        }
    }

}
