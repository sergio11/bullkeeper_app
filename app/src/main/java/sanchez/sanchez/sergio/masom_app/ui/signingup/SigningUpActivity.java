package sanchez.sanchez.sergio.masom_app.ui.signingup;

import android.os.Bundle;
import android.support.annotation.NonNull;

import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;

/**
 * Signing Up Activity
 */
public class SigningUpActivity extends
        SupportActivity<SigningUpPresenter, ISigningUpView> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing_up);
    }

    @Override
    protected void initializeInjector() {

    }

    @NonNull
    @Override
    public SigningUpPresenter providePresenter() {
        return null;
    }
}
