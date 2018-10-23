package sanchez.sanchez.sergio.bullkeeper.ui.activity.comments;

import android.os.Bundle;
import android.support.design.internal.ParcelableSparseArray;
import android.util.SparseArray;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.comments.GetCommentsInteract;
import sanchez.sanchez.sergio.domain.models.AdultLevelEnum;
import sanchez.sanchez.sergio.domain.models.BullyingLevelEnum;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.DimensionCategoryEnum;
import sanchez.sanchez.sergio.domain.models.DrugsLevelEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import sanchez.sanchez.sergio.domain.models.ViolenceLevelEnum;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;

/**
 * Comments Presenter
 */
public final class CommentsMvpPresenter extends SupportLCEPresenter<ICommentsView> {

    /**
     * Args Types
     */
    public static final String KIDS_INDENTITIES_ARG = "KIDS_INDENTITIES_ARG";
    public static final String SOCIAL_MEDIAS_TYPES_ARG = "SOCIAL_MEDIAS_ARG";
    public static final String DIMENSION_TYPES_ARG = "DIMENSION_ARG";


    /**
     * Get Comments Interact
     */
    private final GetCommentsInteract getCommentsInteract;
    private final IPreferenceRepository preferenceRepository;

    /**
     * Comemnts Mvp Presenter
     * @param getCommentsInteract
     * @param preferenceRepository
     */
    @Inject
    public CommentsMvpPresenter(final GetCommentsInteract getCommentsInteract,
                                final IPreferenceRepository preferenceRepository) {
        super();
        this.getCommentsInteract = getCommentsInteract;
        this.preferenceRepository = preferenceRepository;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(!args.isEmpty(), "Args can not be empty");

        if(!args.containsKey(KIDS_INDENTITIES_ARG))
            throw new IllegalArgumentException("You must provide at least the kids identities");


        final String kidIdentity = args.getString(KIDS_INDENTITIES_ARG);

        if(appUtils.isValidString(kidIdentity)) {

            final int ageOfComments = preferenceRepository.getAgeOfCommentsAsInt();

            String[] socialMedias;

            if(args.containsKey(SOCIAL_MEDIAS_TYPES_ARG)) {
                final SocialMediaEnum socialMediaTypeEnum = (SocialMediaEnum) args.getSerializable(SOCIAL_MEDIAS_TYPES_ARG);
                if(socialMediaTypeEnum != null)
                    socialMedias = new String[]{socialMediaTypeEnum.name()};
                else
                    socialMedias = preferenceRepository.getSocialMediaEnabled();
            } else {
                socialMedias = preferenceRepository.getSocialMediaEnabled();
            }

            ViolenceLevelEnum violenceLevelEnum = ViolenceLevelEnum.UNKNOWN;
            AdultLevelEnum adultLevelEnum = AdultLevelEnum.UNKNOWN;
            DrugsLevelEnum drugsLevelEnum = DrugsLevelEnum.UNKNOWN;
            BullyingLevelEnum bullyingLevelEnum = BullyingLevelEnum.UNKNOWN;

            if(args.containsKey(DIMENSION_TYPES_ARG)) {

                final DimensionCategoryEnum dimensionCategoryEnum = (DimensionCategoryEnum)
                        args.getSerializable(DIMENSION_TYPES_ARG);

                if(dimensionCategoryEnum != null) {

                    violenceLevelEnum = ViolenceLevelEnum.NEGATIVE;
                    adultLevelEnum = AdultLevelEnum.NEGATIVE;
                    drugsLevelEnum = DrugsLevelEnum.NEGATIVE;
                    bullyingLevelEnum = BullyingLevelEnum.NEGATIVE;

                    switch (dimensionCategoryEnum) {

                        case ADULT:
                            adultLevelEnum = AdultLevelEnum.POSITIVE;
                            break;

                        case DRUGS:
                            drugsLevelEnum = DrugsLevelEnum.POSITIVE;
                            break;

                        case BULLYING:
                            bullyingLevelEnum = BullyingLevelEnum.POSITIVE;
                            break;

                        case VIOLENCE:
                            violenceLevelEnum = ViolenceLevelEnum.POSITIVE;
                            break;
                    }

                }

            } else {


                if(preferenceRepository.isDimensionFilterEnabled()) {

                    violenceLevelEnum = ViolenceLevelEnum.POSITIVE;
                    adultLevelEnum = AdultLevelEnum.POSITIVE;
                    drugsLevelEnum = DrugsLevelEnum.POSITIVE;
                    bullyingLevelEnum = BullyingLevelEnum.POSITIVE;

                    if (!preferenceRepository.isAllCommentsDimensionEnabled()) {

                        if (!preferenceRepository.isViolenceDimensionEnabled())
                            violenceLevelEnum = ViolenceLevelEnum.NEGATIVE;

                        if (!preferenceRepository.isDrugsDimensionEnabled())
                            drugsLevelEnum = DrugsLevelEnum.NEGATIVE;

                        if (!preferenceRepository.isSexDimensionEnabled())
                            adultLevelEnum = AdultLevelEnum.NEGATIVE;

                        if (!preferenceRepository.isBullyingDimensionEnabled())
                            bullyingLevelEnum = BullyingLevelEnum.NEGATIVE;

                    }

                }

            }

             final GetCommentsInteract.IParameterFilter parameterFilter = GetCommentsInteract.KidsAndSocialMediaAndDimensionLevelFilter.create(
                     new String[] { kidIdentity }, ageOfComments, socialMedias, violenceLevelEnum, drugsLevelEnum,
                     bullyingLevelEnum, adultLevelEnum );


            getCommentsInteract.execute(new GetCommentObservable(GetCommentsInteract.GetCommentsApiErrors.class),
                    parameterFilter);

        } else {

            if (isViewAttached() && getView() != null)
                getView().onOtherException();
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
