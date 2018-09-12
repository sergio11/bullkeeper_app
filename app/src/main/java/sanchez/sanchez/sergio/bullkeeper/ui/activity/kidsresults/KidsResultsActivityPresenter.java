package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidsresults;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;

/**
 * Kids Results Activity
 */
public final class KidsResultsActivityPresenter extends TiPresenter<IKidsResultsView> {

    @Inject
    public KidsResultsActivityPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IKidsResultsView view) {
        super.onAttachView(view);
        view.showLongMessage("Hello World!!!");
    }


}
