package sanchez.sanchez.sergio.masom_app.ui.fragment.signin;

import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Intro Presenter
 */
public final class SigninFragmentPresenter extends TiPresenter<ISigninView> {

    @Inject
    public SigninFragmentPresenter(){}

    /**
     * Sign in
     * @param mail
     * @param password
     */
    public void signin(final String mail, final String password) {
        Timber.d("Mail: %s, password: %s", mail, password);
        if(isViewAttached() && getView() != null) {
            getView().showShortMessage("Validation Succeeded");
            getView().onLoginSuccess();
        }
    }

}
