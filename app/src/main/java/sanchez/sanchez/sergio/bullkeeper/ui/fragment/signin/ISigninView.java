package sanchez.sanchez.sergio.bullkeeper.ui.fragment.signin;

import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportView;

public interface ISigninView extends ISupportView {

    /**
     * On Login Success
     */
    void onLoginSuccess();

    /**
     * On Login Failed
     */
    void onLoginFailed();

    /**
     * On Bad Credentials
     */
    void onBadCredentials();

    /**
     * On Account Disabled
     */
    void onAccountDisabled();

}
