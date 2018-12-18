package sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.alerts.GetWarningAlertsOfSonForSelfParentInteract;
import sanchez.sanchez.sergio.domain.models.AlertEntity;

/**
 * Important Alerts Fragment Presenter
 */
public final class ImportantAlertsFragmentPresenter extends SupportLCEPresenter<IImportantAlertsFragmentView> {

    public static final String SON_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Danger Alerts Of Son For Self Parent
     */
    private final GetWarningAlertsOfSonForSelfParentInteract getWarningAlertsOfSonForSelfParentInteract;

    /**
     * Is Loading Data
     */
    private boolean isLoadingData = false;


    /**
     *
     * @param getWarningAlertsOfSonForSelfParentInteract
     */
    @Inject
    public ImportantAlertsFragmentPresenter(final GetWarningAlertsOfSonForSelfParentInteract getWarningAlertsOfSonForSelfParentInteract){
        this.getWarningAlertsOfSonForSelfParentInteract = getWarningAlertsOfSonForSelfParentInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() { }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(SON_IDENTITY_ARG), "You must provide a son identity value");

        if(isLoadingData)
            return;

        isLoadingData = true;

        if (isViewAttached() && getView() != null)
            getView().onShowLoading();

        final String sonId = args.getString(SON_IDENTITY_ARG);
        getWarningAlertsOfSonForSelfParentInteract.execute(new GetWarningAlertsOfKidForSelfParentObservable(
                GetWarningAlertsOfSonForSelfParentInteract.GetWarningAlertsOfKidForSelfParentApiErrors.class),
                GetWarningAlertsOfSonForSelfParentInteract.Params.create(sonId));
    }


    /**
     * Get Warning Alerts Of Son For Self Parent Observable
     */
    public class GetWarningAlertsOfKidForSelfParentObservable extends CommandCallBackWrapper<List<AlertEntity>,
            GetWarningAlertsOfSonForSelfParentInteract.GetWarningAlertsOfKidForSelfParentApiErrors.IGetWarningAlertsOfKidForSelfParentErrorVisitor,
            GetWarningAlertsOfSonForSelfParentInteract.GetWarningAlertsOfKidForSelfParentApiErrors>
            implements GetWarningAlertsOfSonForSelfParentInteract.GetWarningAlertsOfKidForSelfParentApiErrors.IGetWarningAlertsOfKidForSelfParentErrorVisitor {


        public GetWarningAlertsOfKidForSelfParentObservable(Class<GetWarningAlertsOfSonForSelfParentInteract.GetWarningAlertsOfKidForSelfParentApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Network Error
         */
        @Override
        protected void onNetworkError() {
            super.onNetworkError();
            isLoadingData = false;
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            super.onOtherException(ex);
            isLoadingData = false;
        }

        /**
         * On Api Exception
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            super.onApiException(response);
            isLoadingData = false;
        }

        /**
         * On Success
         * @param alertEntities
         */
        @Override
        protected void onSuccess(List<AlertEntity> alertEntities) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(alertEntities);
            }
            isLoadingData = false;
        }


        /**
         * Visit Not Alerts By Son Founded
         * @param apiErrors
         */
        @Override
        public void visitNoAlertsBySonFound(GetWarningAlertsOfSonForSelfParentInteract.GetWarningAlertsOfKidForSelfParentApiErrors apiErrors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
            isLoadingData = false;
        }
    }

}
