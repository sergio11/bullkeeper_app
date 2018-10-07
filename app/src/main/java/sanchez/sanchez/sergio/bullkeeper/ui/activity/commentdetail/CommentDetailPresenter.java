package sanchez.sanchez.sergio.bullkeeper.ui.activity.commentdetail;

import android.os.Bundle;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.comments.GetCommentByIdInteract;
import sanchez.sanchez.sergio.domain.models.CommentEntity;

/**
 * Comment Detail Presenter
 */
public final class CommentDetailPresenter extends SupportPresenter<ICommentDetailView> {

    /**
     * Comment Identity
     */
    public static final String COMMENT_IDENTITY_ARG = "COMMENT_IDENTITY_ARG";

    /**
     * Get Comment By Id Interact
     */
    private final GetCommentByIdInteract getCommentByIdInteract;

    /**
     *
     * @param getCommentByIdInteract
     */
    @Inject
    public CommentDetailPresenter(final GetCommentByIdInteract getCommentByIdInteract) {
        super();
        this.getCommentByIdInteract = getCommentByIdInteract;
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(COMMENT_IDENTITY_ARG), "You must provide the comment identity");

        final String commentIdentity = args.getString(COMMENT_IDENTITY_ARG);
        loadData(commentIdentity);
    }

    /**
     * Load Data
     * @param commentIdentity
     */
    public void loadData(final String commentIdentity) {
        Preconditions.checkNotNull(commentIdentity, "Comment Identity can not be null");
        Preconditions.checkState(!commentIdentity.isEmpty(), "Comment Identity can not be empty");

        getCommentByIdInteract.execute(new GetCommentDetailObservable(GetCommentByIdInteract.GetCommentByIdApiErrors.class),
                GetCommentByIdInteract.Params.create(commentIdentity));

    }

    /**
     * Get Comment Detail Observable
     */
    public class GetCommentDetailObservable extends CommandCallBackWrapper<CommentEntity,
            GetCommentByIdInteract.GetCommentByIdApiErrors.IGetCommentByIdApiErrorsVisitor,
            GetCommentByIdInteract.GetCommentByIdApiErrors>
            implements GetCommentByIdInteract.GetCommentByIdApiErrors.IGetCommentByIdApiErrorsVisitor {

        public GetCommentDetailObservable(Class<GetCommentByIdInteract.GetCommentByIdApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(CommentEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null)
                getView().onCommentLoaded(response);

        }

        /**
         * Visit Comment Not Found
         * @param apiErrors
         */
        @Override
        public void visitCommentNotFound(GetCommentByIdInteract.GetCommentByIdApiErrors apiErrors) {

            if (isViewAttached() && getView() != null)
                getView().onCommentNotFound();
        }
    }
}
