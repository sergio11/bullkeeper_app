package sanchez.sanchez.sergio.bullkeeper.ui.activity.commentdetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.CommentEntity;

/**
 * Comment Detail View
 */

interface ICommentDetailView extends ISupportView {

    /**
     * On Comment Loaded
     * @param commentEntity
     */
    void onCommentLoaded(final CommentEntity commentEntity);

    /**
     * On Comment Not Found
     */
    void onCommentNotFound();

}
