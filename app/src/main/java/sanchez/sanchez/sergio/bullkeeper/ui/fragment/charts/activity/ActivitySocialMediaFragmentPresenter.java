package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.activity;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.Arrays;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetSocialMediaActivityStatisticsInteract;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;

/**
 * Activity Social Media Fragment Presenter
 */
public final class ActivitySocialMediaFragmentPresenter
        extends SupportPresenter<IActivitySocialMediaFragmentView> {

    /**
     * Days Ago Default Value
     */
    private final static int DAYS_AGO_DEFAULT_VALUE = 30;

    /**
     * Kid Identity Arg
     */
    public static final String KIDS_IDENTITY_ARG = "KIDS_IDENTITY_ARG";

    /**
     * Get Social Media Activity
     */
    private final GetSocialMediaActivityStatisticsInteract getSocialMediaActivityStatisticsInteract;

    /**
     * Activity Social Media Fragment
     * @param getSocialMediaActivityStatisticsInteract
     */
    @Inject
    public ActivitySocialMediaFragmentPresenter(final GetSocialMediaActivityStatisticsInteract getSocialMediaActivityStatisticsInteract){
        this.getSocialMediaActivityStatisticsInteract = getSocialMediaActivityStatisticsInteract;
    }

    /**
     * On Init Args
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);
        if(args != null && args.containsKey(KIDS_IDENTITY_ARG))
            loadData(args.getString(KIDS_IDENTITY_ARG));
    }

    /**
     * Load Data
     * @param sonIdentity
     */
    public void loadData(final String sonIdentity) {
        Preconditions.checkNotNull(sonIdentity, "Son Identity can not be null");
        Preconditions.checkState(!sonIdentity.isEmpty(), "Son Identity can not be null");

        // Get Social Media Activity Statistics Interact
        getSocialMediaActivityStatisticsInteract.execute(new GetSocialMediaActivityStatisticsObservable(
                GetSocialMediaActivityStatisticsInteract.GetSocialMediaActivityStatisticsApiErrors.class),
                GetSocialMediaActivityStatisticsInteract.Params.create(sonIdentity, DAYS_AGO_DEFAULT_VALUE));

    }

    /**
     * Get Social Media Activity Statistics Observable
     */
    public class GetSocialMediaActivityStatisticsObservable extends CommandCallBackWrapper<SocialMediaActivityStatisticsEntity,
            GetSocialMediaActivityStatisticsInteract.GetSocialMediaActivityStatisticsApiErrors.IGetSocialMediaActivityStatisticsApiErrorsVisitor,
            GetSocialMediaActivityStatisticsInteract.GetSocialMediaActivityStatisticsApiErrors>
            implements GetSocialMediaActivityStatisticsInteract.GetSocialMediaActivityStatisticsApiErrors.IGetSocialMediaActivityStatisticsApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetSocialMediaActivityStatisticsObservable(Class<GetSocialMediaActivityStatisticsInteract.GetSocialMediaActivityStatisticsApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(SocialMediaActivityStatisticsEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null)
                getView().onDataAvaliable(response);
        }

        /**
         * Visit Social Media Activity Statistics Not Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitSocialMediaActivityStatisticsNotFound(GetSocialMediaActivityStatisticsInteract.GetSocialMediaActivityStatisticsApiErrors apiErrorsVisitor) {

            if (isViewAttached() && getView() != null)
                getView().onNoDataAvaliable();

        }
    }
}
