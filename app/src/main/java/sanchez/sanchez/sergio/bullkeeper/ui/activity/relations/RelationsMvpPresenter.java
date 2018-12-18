package sanchez.sanchez.sergio.bullkeeper.ui.activity.relations;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetMostActiveFriendsInteract;
import sanchez.sanchez.sergio.domain.models.MostActiveFriendsEntity;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;

/**
 * Relations Presenter
 */
public final class RelationsMvpPresenter extends SupportLCEPresenter<IRelationsView> {

    /**
     * Args Types
     */
    public static final String KIDS_INDENTITIES_ARG = "KIDS_INDENTITIES_ARG";

    /**
     * Get Most Active Friends Interact
     */
    private final GetMostActiveFriendsInteract getMostActiveFriendsInteract;
    private final IPreferenceRepository preferenceRepository;


    /**
     *
     * @param getMostActiveFriendsInteract
     * @param preferenceRepository
     */
    @Inject
    public RelationsMvpPresenter(final GetMostActiveFriendsInteract getMostActiveFriendsInteract,
                                 final IPreferenceRepository preferenceRepository) {
        this.getMostActiveFriendsInteract = getMostActiveFriendsInteract;
        this.preferenceRepository = preferenceRepository;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(!args.isEmpty(), "Args can not be empty");
        Preconditions.checkState(args.containsKey(KIDS_INDENTITIES_ARG) &&
            appUtils.isValidString(args.getString(KIDS_INDENTITIES_ARG)), "Kid Identity can not be empty");

        final String kidIdentity = args.getString(KIDS_INDENTITIES_ARG);

        getMostActiveFriendsInteract.execute(new GetCommentObservable(GetMostActiveFriendsInteract.GetMostActiveFriendsApiErrors.class),
                GetMostActiveFriendsInteract.Params.create(kidIdentity, preferenceRepository.getAgeOfRelationsAsInt()));

    }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) { loadData(); }

    /**
     * Get Relations Observable
     */
    public class GetCommentObservable extends CommandCallBackWrapper<MostActiveFriendsEntity,
            GetMostActiveFriendsInteract.GetMostActiveFriendsApiErrors.IGetMostActiveFriendsApiErrorsVisitor,
            GetMostActiveFriendsInteract.GetMostActiveFriendsApiErrors>
            implements GetMostActiveFriendsInteract.GetMostActiveFriendsApiErrors.IGetMostActiveFriendsApiErrorsVisitor {

        /**
         * Get Comment Observable
         * @param apiErrors
         */
        public GetCommentObservable(Class<GetMostActiveFriendsInteract.GetMostActiveFriendsApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(MostActiveFriendsEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null)
                getView().onDataLoaded(response.getFriends());
        }

        /**
         * Visit No Active Friends In this period
         * @param apiErrors
         */
        @Override
        public void visitNoActiveFriendsInThisPeriod(GetMostActiveFriendsInteract.GetMostActiveFriendsApiErrors apiErrors) {
            if (isViewAttached() && getView() != null)
                getView().onNoDataFound();
        }
    }

}
