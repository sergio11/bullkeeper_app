package sanchez.sanchez.sergio.bullkeeper.ui.activity.comments;

import java.util.List;

import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportView;


/**
 * Comments View
 */
public interface ICommentsView extends ISupportView {

    /**
     * On Comments Loaded
     * @param commentsList
     */
    void onCommentsLoaded(final List<CommentEntity> commentsList);

}
