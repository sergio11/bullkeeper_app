package sanchez.sanchez.sergio.masom_app.ui.activity.intro;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;

import javax.inject.Inject;

/**
 * Home Presenter
 */
public final class IntroPresenter extends TiPresenter<IIntroView> {


    @Inject
    public IntroPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IIntroView view) {
        super.onAttachView(view);

        view.showLongMessage("Hello World!!!");

    }
}
