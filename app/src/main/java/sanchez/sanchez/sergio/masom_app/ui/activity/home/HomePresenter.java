package sanchez.sanchez.sergio.masom_app.ui.activity.home;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;

/**
 * Home Presenter
 */
public final class HomePresenter extends TiPresenter<IHomeView> {


    @Inject
    public HomePresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IHomeView view) {
        super.onAttachView(view);

        view.showLongMessage("Hello World!!!");

    }
}
