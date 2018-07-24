package sanchez.sanchez.sergio.masom_app.ui.signingin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;

/**
 * Signing In Activity
 */
public class SigningInActivity
        extends SupportActivity<SigningInPresenter, ISigningInView> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing_in);
    }

    @Override
    protected void initializeInjector() {

    }

    @NonNull
    @Override
    public SigningInPresenter providePresenter() {
        return null;
    }
}
