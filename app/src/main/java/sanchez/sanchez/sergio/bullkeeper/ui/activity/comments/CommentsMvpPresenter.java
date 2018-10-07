package sanchez.sanchez.sergio.bullkeeper.ui.activity.comments;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.comments.GetCommentsInteract;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;

/**
 * Comments Presenter
 */
public final class CommentsMvpPresenter extends SupportLCEPresenter<ICommentsView> {

    /**
     * Args Types
     */
    public static final String KIDS_INDENTITIES_ARG = "KIDS_INDENTITIES_ARG";
    public static final String SOCIAL_MEDIAS_TYPES_ARG = "SOCIAL_MEDIAS_ARG";

    private final static int DAYS_AGO_DEFAULT_VALUE = 30;


    /**
     * Get Comments Interact
     */
    private final GetCommentsInteract getCommentsInteract;

    /**
     * Comemnts Mvp Presenter
     * @param getCommentsInteract
     */
    @Inject
    public CommentsMvpPresenter(final GetCommentsInteract getCommentsInteract) {
        super();
        this.getCommentsInteract = getCommentsInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(!args.isEmpty(), "Args can not be empty");

        if(args.containsKey(KIDS_INDENTITIES_ARG) &&
                args.containsKey(SOCIAL_MEDIAS_TYPES_ARG)) {

            final String kidIdentity = args.getString(KIDS_INDENTITIES_ARG);
            final SocialMediaEnum socialMediaTypeEnum = (SocialMediaEnum) args.getSerializable(SOCIAL_MEDIAS_TYPES_ARG);

            if(appUtils.isValidString(kidIdentity) && socialMediaTypeEnum != null) {

                getCommentsInteract.execute(new GetCommentObservable(GetCommentsInteract.GetCommentsApiErrors.class),
                        GetCommentsInteract.KidsAndSocialMediaFilter.create(new String[]{
                                args.getString(KIDS_INDENTITIES_ARG)
                        }, DAYS_AGO_DEFAULT_VALUE, new String[] {
                                socialMediaTypeEnum.name()
                        }));

            } else {

                if (isViewAttached() && getView() != null)
                    getView().onOtherException();
            }


        } else if(args.containsKey(KIDS_INDENTITIES_ARG)) {

            final String kidIdentity = args.getString(KIDS_INDENTITIES_ARG);

            if(appUtils.isValidString(kidIdentity)) {

                getCommentsInteract.execute(new GetCommentObservable(GetCommentsInteract.GetCommentsApiErrors.class),
                        GetCommentsInteract.KidsFilter.create(new String[]{
                                args.getString(KIDS_INDENTITIES_ARG)
                        }, DAYS_AGO_DEFAULT_VALUE));

            } else {

                if (isViewAttached() && getView() != null)
                    getView().onOtherException();
            }

        } else {
            throw new IllegalArgumentException("You must provide at least the kids identities");
        }

    }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) { loadData(); }

    /**
     * Get Comments Observable
     */
    public class GetCommentObservable extends CommandCallBackWrapper<List<CommentEntity>,
            GetCommentsInteract.GetCommentsApiErrors.IGetCommentsApiErrorsVisitor,
            GetCommentsInteract.GetCommentsApiErrors>
            implements GetCommentsInteract.GetCommentsApiErrors.IGetCommentsApiErrorsVisitor {

        /**
         * Get Comment Observable
         * @param apiErrors
         */
        public GetCommentObservable(Class<GetCommentsInteract.GetCommentsApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(List<CommentEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null)
                getView().onDataLoaded(response);

        }

        /**
         * Visit Comments Not Found
         * @param apiErrors
         */
        @Override
        public void visitCommentsNotFound(GetCommentsInteract.GetCommentsApiErrors apiErrors) {

            if (isViewAttached() && getView() != null)
                getView().onNoDataFound();

        }
    }
}
