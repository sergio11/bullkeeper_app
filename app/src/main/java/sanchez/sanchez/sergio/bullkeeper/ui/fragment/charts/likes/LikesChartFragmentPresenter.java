package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.likes;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;


import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.comments.GetSocialMediaLikesStatisticsInteract;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;

/**
 * Likes Chart Fragment
 */
public final class LikesChartFragmentPresenter extends SupportPresenter<ILikesChartFragmentView> {

    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    private final static int DAYS_AGO_DEFAULT_VALUE = 30;

    /**
     * Get Social Media Likes Statisctics Interact
     */
    private final GetSocialMediaLikesStatisticsInteract getSocialMediaLikesStatisticsInteract;

    /**
     *
     * @param getSocialMediaLikesStatisticsInteract
     */
    @Inject
    public LikesChartFragmentPresenter(final GetSocialMediaLikesStatisticsInteract getSocialMediaLikesStatisticsInteract){
        this.getSocialMediaLikesStatisticsInteract = getSocialMediaLikesStatisticsInteract;
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
     * @param kidIdentity
     */
    public void loadData(final String kidIdentity){
        Preconditions.checkNotNull(kidIdentity, "Kid identity can not be null");
        Preconditions.checkState(!kidIdentity.isEmpty(), "Kid identity can not empty");

        getSocialMediaLikesStatisticsInteract.execute(new GetSocialMediaLikesStatisticsObservable(GetSocialMediaLikesStatisticsInteract.GetSocialMediaLikesStatisticsApiErrors.class),
                GetSocialMediaLikesStatisticsInteract.Params.create(kidIdentity, DAYS_AGO_DEFAULT_VALUE));
    }

    /**
     * Get Social Media Likes Statistics Observable
     */
    public class GetSocialMediaLikesStatisticsObservable extends CommandCallBackWrapper<SocialMediaLikesStatisticsEntity,
            GetSocialMediaLikesStatisticsInteract.GetSocialMediaLikesStatisticsApiErrors.IGetSocialMediaLikesStatisticsApiErrorsVisitor,
            GetSocialMediaLikesStatisticsInteract.GetSocialMediaLikesStatisticsApiErrors>
            implements GetSocialMediaLikesStatisticsInteract.GetSocialMediaLikesStatisticsApiErrors.IGetSocialMediaLikesStatisticsApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetSocialMediaLikesStatisticsObservable(Class<GetSocialMediaLikesStatisticsInteract.GetSocialMediaLikesStatisticsApiErrors> apiErrors) {
            super(apiErrors);
        }


        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final SocialMediaLikesStatisticsEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null)
                getView().onDataAvaliable(response);

        }

        /**
         * Visit No Likes Found In This Period
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoLikesFoundInThisPeriod(GetSocialMediaLikesStatisticsInteract.GetSocialMediaLikesStatisticsApiErrors apiErrorsVisitor) {

            if (isViewAttached() && getView() != null)
                getView().onNoDataAvaliable();
        }
    }
}
