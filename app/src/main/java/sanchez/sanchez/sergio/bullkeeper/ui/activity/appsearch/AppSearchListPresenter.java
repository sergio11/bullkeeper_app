package sanchez.sanchez.sergio.bullkeeper.ui.activity.appsearch;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportSearchLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.apprules.GetAllAppInstalledByKidInteract;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;

/**
 * App Search List Presenter
 */
public final class AppSearchListPresenter extends SupportSearchLCEPresenter<IAppSearchListView> {

    /**
     * Args
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get All App Installed Interact
     */
    private final GetAllAppInstalledByKidInteract getAllAppInstalledByKidInteract;

    /**
     *
     * @param getAllAppInstalledByKidInteract
     */
    @Inject
    public AppSearchListPresenter(final GetAllAppInstalledByKidInteract getAllAppInstalledByKidInteract) {
        this.getAllAppInstalledByKidInteract = getAllAppInstalledByKidInteract;
    }

    /**
     * Load Date
     */
    @Override
    public void loadData() {
        loadApps("");
    }

    /**
     *
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        loadApps("");
    }

    /**
     * Load Data
     * @param queryText
     */
    @Override
    public void loadData(String queryText) {
        loadApps(queryText);
    }

    /**
     * Load Apps
     * @param queryText
     */
    private void loadApps(final String queryText) {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KID_IDENTITY_ARG), "You must provide a kid identity value");

        if (isViewAttached() && getView() != null)
            getView().onShowLoading();

        getAllAppInstalledByKidInteract.execute(new GetAllAppInstalledObservable(GetAllAppInstalledByKidInteract.GetAppRulesApiErrors.class),
                GetAllAppInstalledByKidInteract.Params.create(
                        args.getString(KID_IDENTITY_ARG), queryText));
    }

        /**
         * Get All App Installed Observable
         */
    public class GetAllAppInstalledObservable extends CommandCallBackWrapper<List<AppInstalledEntity>,
            GetAllAppInstalledByKidInteract.GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor,
                GetAllAppInstalledByKidInteract.GetAppRulesApiErrors>
            implements GetAllAppInstalledByKidInteract.GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor {


            public GetAllAppInstalledObservable(Class<GetAllAppInstalledByKidInteract.GetAppRulesApiErrors> apiErrors) {
                super(apiErrors);
            }

            /**
             *
             * @param response
             */
            @Override
            protected void onSuccess(List<AppInstalledEntity> response) {
                Preconditions.checkNotNull(response, "Response can not be null");
                Preconditions.checkState(!response.isEmpty(), "Response can not be empty");

                if (isViewAttached() && getView() != null){
                    getView().hideProgressDialog();
                    getView().onDataLoaded(response);
                }

            }

            /**
             *
             * @param apiErrorsVisitor
             */
            @Override
            public void visitNoAppRulesFound(GetAllAppInstalledByKidInteract.GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor apiErrorsVisitor) {
                if (isViewAttached() && getView() != null) {
                    getView().hideProgressDialog();
                    getView().onNoDataFound();
                }
            }
        }


}
