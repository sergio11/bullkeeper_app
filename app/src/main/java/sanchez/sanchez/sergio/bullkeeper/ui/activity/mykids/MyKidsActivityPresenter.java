package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;

/**
 * My Kids Activity Presenter
 */
public final class MyKidsActivityPresenter
        extends TiPresenter<IMyKidsActivityView> {

    @Inject
    public MyKidsActivityPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IMyKidsActivityView view) {
        super.onAttachView(view);

        view.showLongMessage("Hello World!!!");

    }
}
