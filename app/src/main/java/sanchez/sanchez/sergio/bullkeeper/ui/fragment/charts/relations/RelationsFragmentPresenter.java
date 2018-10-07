package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.relations;

import android.os.Bundle;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.Arrays;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.comments.MostActiveFriendsBySocialMediaStatisticsInteract;
import sanchez.sanchez.sergio.domain.models.MostActiveFriendsBySocialMediaStatisticsEntity;

/**
 * Relations Fragment Presenter
 */
public final class RelationsFragmentPresenter extends
        SupportPresenter<IRelationsFragmentView> {

    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    private final static int DAYS_AGO_DEFAULT_VALUE = 30;

    /**
     *
     */
    private final MostActiveFriendsBySocialMediaStatisticsInteract mostActiveFriendsBySocialMediaStatisticsInteract;

    /**
     *
     * @param mostActiveFriendsBySocialMediaStatisticsInteract
     */
    @Inject
    public RelationsFragmentPresenter(final MostActiveFriendsBySocialMediaStatisticsInteract mostActiveFriendsBySocialMediaStatisticsInteract){
        this.mostActiveFriendsBySocialMediaStatisticsInteract = mostActiveFriendsBySocialMediaStatisticsInteract;
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

        mostActiveFriendsBySocialMediaStatisticsInteract.execute(new GetMostActiveFriendsBySocialMediaStatisticsObservable(
                MostActiveFriendsBySocialMediaStatisticsInteract.MostActiveFriendsBySocialMediaStatisticsApiErrors.class),
                MostActiveFriendsBySocialMediaStatisticsInteract.Params.create(kidIdentity, DAYS_AGO_DEFAULT_VALUE));

    }

    /**
     * Get Most Active Friends By Social Media Statistics Observable
     */
    public class GetMostActiveFriendsBySocialMediaStatisticsObservable extends CommandCallBackWrapper<MostActiveFriendsBySocialMediaStatisticsEntity,
            MostActiveFriendsBySocialMediaStatisticsInteract.MostActiveFriendsBySocialMediaStatisticsApiErrors.IMostActiveFriendsBySocialMediaStatisticsApiErrorsVisitor,
            MostActiveFriendsBySocialMediaStatisticsInteract.MostActiveFriendsBySocialMediaStatisticsApiErrors>
            implements MostActiveFriendsBySocialMediaStatisticsInteract.MostActiveFriendsBySocialMediaStatisticsApiErrors.IMostActiveFriendsBySocialMediaStatisticsApiErrorsVisitor {


        public GetMostActiveFriendsBySocialMediaStatisticsObservable(Class<MostActiveFriendsBySocialMediaStatisticsInteract.MostActiveFriendsBySocialMediaStatisticsApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(MostActiveFriendsBySocialMediaStatisticsEntity response) {

            if (isViewAttached() && getView() != null)
                getView().onDataAvaliable(response);

        }

        /**
         * Visit No Active Found In This Period
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoActiveFoundInThisPeriod(MostActiveFriendsBySocialMediaStatisticsInteract.MostActiveFriendsBySocialMediaStatisticsApiErrors apiErrorsVisitor) {

            if (isViewAttached() && getView() != null)
                getView().onNoDataAvaliable();
        }

        @Override
        protected void onApiException(APIResponse response) {
            //super.onApiException(response);

            final MostActiveFriendsBySocialMediaStatisticsEntity test = new MostActiveFriendsBySocialMediaStatisticsEntity();
            test.setTitle("Title");
            test.setFriends(Arrays.asList(
                    new MostActiveFriendsBySocialMediaStatisticsEntity.FriendEntity("Sergio Sánchez", 50l),
                    new MostActiveFriendsBySocialMediaStatisticsEntity.FriendEntity("David Martón", 50l)
            ));

            if (isViewAttached() && getView() != null)
                getView().onDataAvaliable(test);
        }
    }

}
