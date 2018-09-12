package sanchez.sanchez.sergio.bullkeeper.ui.activity.comments;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;

/**
 * Comments Presenter
 */
public final class CommentsPresenter extends TiPresenter<ICommentsView> {

    @Inject
    public CommentsPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final ICommentsView view) {
        super.onAttachView(view);

        final List<CommentEntity> comments = Arrays.asList(new CommentEntity(SocialMediaEnum.FACEBOOK),
                new CommentEntity(SocialMediaEnum.INSTAGRAM), new CommentEntity(SocialMediaEnum.YOUTUBE),
                new CommentEntity(SocialMediaEnum.FACEBOOK), new CommentEntity(SocialMediaEnum.YOUTUBE),
                new CommentEntity(SocialMediaEnum.INSTAGRAM), new CommentEntity(SocialMediaEnum.FACEBOOK),
                new CommentEntity(SocialMediaEnum.YOUTUBE), new CommentEntity(SocialMediaEnum.INSTAGRAM),
                new CommentEntity(SocialMediaEnum.YOUTUBE), new CommentEntity(SocialMediaEnum.FACEBOOK));

        view.onCommentsLoaded(comments);
    }


}
