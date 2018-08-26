package sanchez.sanchez.sergio.bullkeeper.ui.activity.commentdetail;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;

/**
 * Comment Detail Presenter
 */
public final class CommentDetailPresenter extends TiPresenter<ICommentDetailView> {


    @Inject
    public CommentDetailPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final ICommentDetailView view) {
        super.onAttachView(view);

        view.showLongMessage("Hello World!!!");

    }
}
