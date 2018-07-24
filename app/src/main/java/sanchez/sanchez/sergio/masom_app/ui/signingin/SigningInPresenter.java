package sanchez.sanchez.sergio.masom_app.ui.signingin;

import android.support.annotation.NonNull;

import net.grandcentrix.thirtyinch.TiPresenter;

import javax.inject.Inject;

/**
 * Home Presenter
 */
public final class SigningInPresenter extends TiPresenter<ISigningInView> {


    @Inject
    public SigningInPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final ISigningInView view) {
        super.onAttachView(view);

        view.showLongMessage("Hello World!!!");

    }
}
