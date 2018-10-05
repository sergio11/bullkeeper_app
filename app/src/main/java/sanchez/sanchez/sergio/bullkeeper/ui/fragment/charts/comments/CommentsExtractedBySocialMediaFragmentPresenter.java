package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.comments;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.comments.GetCommentsStatisticsBySocialMediaInteract;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;

/**
 * Comments Extracted Fragment Presenter
 */
public final class CommentsExtractedBySocialMediaFragmentPresenter
        extends SupportPresenter<ICommentsExtractedBySocialMediaFragmentView> {

    public final static String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    private final static int DAYS_AGO_DEFAULT_VALUE = 30;

    /**
     * Get Comments Statistics By Social Media Interact
     */
    private final GetCommentsStatisticsBySocialMediaInteract getCommentsStatisticsBySocialMediaInteract;

    /**
     *
     * @param getCommentsStatisticsBySocialMediaInteract
     */
    @Inject
    public CommentsExtractedBySocialMediaFragmentPresenter(final GetCommentsStatisticsBySocialMediaInteract getCommentsStatisticsBySocialMediaInteract){
        this.getCommentsStatisticsBySocialMediaInteract = getCommentsStatisticsBySocialMediaInteract;
    }

    /**
     * On Init Args
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);
        if(args != null && args.containsKey(KID_IDENTITY_ARG))
            loadData(args.getString(KID_IDENTITY_ARG));
    }

    /**
     * Load Data
     * @param sonIdentity
     */
    public void loadData(final String sonIdentity) {
        Preconditions.checkNotNull(sonIdentity, "Son Identity can not be null");
        Preconditions.checkState(!sonIdentity.isEmpty(), "Son Identity can not be null");

        // Get Comments Statistics
        getCommentsStatisticsBySocialMediaInteract.execute(new GetCommentsExtractedBySocialMediaObservable(GetCommentsStatisticsBySocialMediaInteract
                        .GetCommentsStatisticsBySocialMediaApiErrors.class),
                GetCommentsStatisticsBySocialMediaInteract.Params.create(sonIdentity, DAYS_AGO_DEFAULT_VALUE));

    }

    /**
     * Get Four Dimensions Statistics By Child Observable
     */
    public class GetCommentsExtractedBySocialMediaObservable extends CommandCallBackWrapper<CommentsStatisticsBySocialMediaEntity,
            GetCommentsStatisticsBySocialMediaInteract.GetCommentsStatisticsBySocialMediaApiErrors.IGetCommentsStatisticsBySocialMediaApiErrorsVisitor,
            GetCommentsStatisticsBySocialMediaInteract.GetCommentsStatisticsBySocialMediaApiErrors>
            implements GetCommentsStatisticsBySocialMediaInteract.GetCommentsStatisticsBySocialMediaApiErrors.IGetCommentsStatisticsBySocialMediaApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetCommentsExtractedBySocialMediaObservable(Class<GetCommentsStatisticsBySocialMediaInteract.GetCommentsStatisticsBySocialMediaApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(CommentsStatisticsBySocialMediaEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null)
                getView().onDataAvaliable(response);

        }

        /**
         * Visit No Comments Extracted Error
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoCommentsExtractedError(GetCommentsStatisticsBySocialMediaInteract.GetCommentsStatisticsBySocialMediaApiErrors.IGetCommentsStatisticsBySocialMediaApiErrorsVisitor apiErrorsVisitor) {

           if (isViewAttached() && getView() != null)
                getView().onNoDataAvaliable();

        }

    }

}
