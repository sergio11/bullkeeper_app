package sanchez.sanchez.sergio.masom_app.ui.fragment.password;

import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Intro Presenter
 */
public final class ForgotPasswordFragmentPresenter extends TiPresenter<IForgotPasswordView> {

    @Inject
    public ForgotPasswordFragmentPresenter(){}

    /**
     * Forgot password
     * @param mail
     */
    public void forgotPassword(final String mail) {
        Timber.d("Mail: %s ", mail);
        if(isViewAttached() && getView() != null)
            getView().showShortMessage("Validation Succeeded");
    }

}
