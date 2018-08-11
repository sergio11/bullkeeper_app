package sanchez.sanchez.sergio.masom_app.ui.fragment.signup;

import net.grandcentrix.thirtyinch.TiPresenter;

import javax.inject.Inject;

import sanchez.sanchez.sergio.masom_app.R;

/**
 * Intro Presenter
 */
public final class SignupFragmentPresenter extends TiPresenter<ISignupView> {

    @Inject
    public SignupFragmentPresenter(){}

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
                       final String email, final String password, final String confirmPassword){

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.signup_in_progress);


        if (isViewAttached() && getView() != null)
            getView().hideProgressDialog();

    }


}
