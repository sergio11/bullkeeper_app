package sanchez.sanchez.sergio.masom_app.ui.signingup;

import android.support.annotation.NonNull;

import net.grandcentrix.thirtyinch.TiPresenter;

import javax.inject.Inject;

/**
 * Home Presenter
 */
public final class SigningUpPresenter extends TiPresenter<ISigningUpView> {


    @Inject
    public SigningUpPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final ISigningUpView view) {
        super.onAttachView(view);

        view.showLongMessage("Hello World!!!");

    }
}
