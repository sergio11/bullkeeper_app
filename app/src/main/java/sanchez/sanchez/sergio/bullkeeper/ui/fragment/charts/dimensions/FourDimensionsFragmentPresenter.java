package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.dimensions;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.statistics.GetFourDimensionsStatisticsByChildInteract;
import sanchez.sanchez.sergio.domain.models.DimensionsStatisticsEntity;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import timber.log.Timber;

/**
 * Four Dimensions Fragment Presenter
 */
public final class FourDimensionsFragmentPresenter extends SupportPresenter<IFourDimensionsFragmentView> {

    public static final String KIDS_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Four Dimensions Statistics By Child Interact
     */
    private final GetFourDimensionsStatisticsByChildInteract getFourDimensionsStatisticsByChildInteract;

    /**
     * Preference Repository
     */
    private final IPreferenceRepository preferenceRepository;

    /**
     *
     * @param getFourDimensionsStatisticsByChildInteract
     * @param preferenceRepository
     */
    @Inject
    public FourDimensionsFragmentPresenter(final GetFourDimensionsStatisticsByChildInteract getFourDimensionsStatisticsByChildInteract,
                                           final IPreferenceRepository preferenceRepository){
        this.getFourDimensionsStatisticsByChildInteract = getFourDimensionsStatisticsByChildInteract;
        this.preferenceRepository = preferenceRepository;
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
     * @param kid
     */
    public void loadData(final String kid){
        Preconditions.checkNotNull(kid, "kid Id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "kid Id can not empty");

        getFourDimensionsStatisticsByChildInteract.execute(new GetFourDimensionsStatisticsByChildObservable(GetFourDimensionsStatisticsByChildInteract.GetFourDimensionsStatisticsApiErrors.class),
                GetFourDimensionsStatisticsByChildInteract.Params.create(kid, preferenceRepository.getAgeOfResultsAsInt()));
    }

    /**
     * Get Four Dimensions Statistics By Child Observable
     */
    public class GetFourDimensionsStatisticsByChildObservable extends CommandCallBackWrapper<DimensionsStatisticsEntity,
        GetFourDimensionsStatisticsByChildInteract.GetFourDimensionsStatisticsApiErrors.IGetFourDimensionsStatisticsApiErrorsVisitor,
        GetFourDimensionsStatisticsByChildInteract.GetFourDimensionsStatisticsApiErrors>
            implements GetFourDimensionsStatisticsByChildInteract.GetFourDimensionsStatisticsApiErrors.IGetFourDimensionsStatisticsApiErrorsVisitor {


        public GetFourDimensionsStatisticsByChildObservable(final Class<GetFourDimensionsStatisticsByChildInteract.GetFourDimensionsStatisticsApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param dimensionEntities
         */
        @Override
        protected void onSuccess(DimensionsStatisticsEntity dimensionEntities) {
            Preconditions.checkNotNull(dimensionEntities, "Dimensions can nto be null");
            if(isViewAttached() && getView() != null)
                getView().onDataAvaliable(dimensionEntities);

        }

        /**
         * Visit No Dimensions Statistics For This Period Error
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoDimensionsStatisticsForThisPeriodError(final GetFourDimensionsStatisticsByChildInteract.GetFourDimensionsStatisticsApiErrors
                .IGetFourDimensionsStatisticsApiErrorsVisitor apiErrorsVisitor) {

            Timber.d("No Dimensions Statistics Avaliable");

            if(isViewAttached() && getView() != null)
                getView().onNoDataAvaliable();

        }
    }
}
