package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.apprules;


import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportSearchLCEPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.apprules.GetAppRulesInteract;
import sanchez.sanchez.sergio.domain.interactor.apprules.UpdateAppInstalledRulesByChildInteract;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledRuleEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;
import timber.log.Timber;

/**
 * App Rules Fragment Presenter
 */
public final class AppRulesFragmentPresenter extends SupportSearchLCEPresenter<IAppRulesFragmentView> {

    /**
     * Args
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    public static final String TERMINALS_ARG = "TERMINALS_ARG";
    public static final String CURRENT_TERMINAL_POS_ARG = "CURRENT_TERMINAL_POS_ARG";

    /**
     * Get App Rules Interact
     */
    private final GetAppRulesInteract getAppRulesInteract;

    /**
     * Update App Installed Rules By Child Interact
     */
    private final UpdateAppInstalledRulesByChildInteract updateAppInstalledRulesByChildInteract;

    /**
     * Is Loading Data
     */
    private boolean isLoadingData = false;

    /**
     * @param getAppRulesInteract
     * @param updateAppInstalledRulesByChildInteract
     */
    @Inject
    public AppRulesFragmentPresenter(final GetAppRulesInteract getAppRulesInteract,
                                     final UpdateAppInstalledRulesByChildInteract updateAppInstalledRulesByChildInteract){
        this.getAppRulesInteract = getAppRulesInteract;
        this.updateAppInstalledRulesByChildInteract = updateAppInstalledRulesByChildInteract;
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
        Preconditions.checkState(args.containsKey(TERMINALS_ARG), "You must provide terminals list");
        final ArrayList<TerminalItem> terminalItems = (ArrayList<TerminalItem>) args.getSerializable(TERMINALS_ARG);
        Preconditions.checkNotNull(terminalItems, "Terminal list can not be null");
        Preconditions.checkState(!terminalItems.isEmpty(), "Terminal list can not be empty");
        Preconditions.checkState(args.containsKey(CURRENT_TERMINAL_POS_ARG), "You must provide a terminal pos");

        if (isLoadingData)
            return;

        isLoadingData = true;

        final TerminalItem terminalItem = terminalItems.get(args.getInt(CURRENT_TERMINAL_POS_ARG));

        if(terminalItem != null) {

            if (isViewAttached() && getView() != null)
                getView().onShowLoading();

            getAppRulesInteract.execute(new GetAppRulesObservable(GetAppRulesInteract.GetAppRulesApiErrors.class),
                    GetAppRulesInteract.Params.create(
                            args.getString(KID_IDENTITY_ARG), terminalItem.getIdentity(), queryText));
        }
    }

    /**
     * Change App Rule
     * @param newRulesMap
     */
    public void applyRules(final String childId, final String terminalId, final Map<String, AppRuleEnum> newRulesMap) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminalId, "Terminal id can not be null");
        Preconditions.checkState(!terminalId.isEmpty(), "Terminal Id can not be empty");
        Preconditions.checkNotNull(newRulesMap, "New Rules Map can not be null");
        Preconditions.checkState(!newRulesMap.isEmpty(), "New Rules Map Installed identity can not be empty");


        if (isViewAttached() && getView() != null) {
            getView().showProgressDialog(R.string.applying_configured_app_rules);
        }

        final List<AppInstalledRuleEntity> appInstalledRuleEntities = new ArrayList<>();
        for(Map.Entry<String, AppRuleEnum> appRule : newRulesMap.entrySet())
            appInstalledRuleEntities.add(new AppInstalledRuleEntity(appRule.getKey(), appRule.getValue()));


        updateAppInstalledRulesByChildInteract.execute(new UpdateAppRulesObservable(),
                UpdateAppInstalledRulesByChildInteract.Params.create(childId, terminalId, appInstalledRuleEntities));


    }



    /**
     * Get App Rules Observable
     */
    public class GetAppRulesObservable extends CommandCallBackWrapper<List<AppInstalledEntity>,
            GetAppRulesInteract.GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor,
            GetAppRulesInteract.GetAppRulesApiErrors>
            implements GetAppRulesInteract.GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor {


        /**
         *
         * @param apiErrors
         */
        public GetAppRulesObservable(Class<GetAppRulesInteract.GetAppRulesApiErrors> apiErrors) {
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
         *
         * @param response
         */
        @Override
        protected void onSuccess(List<AppInstalledEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            Preconditions.checkState(!response.isEmpty(), "Response can not be empty");

            Timber.d("App Rules On Success");

            if (isViewAttached() && getView() != null){
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }

            isLoadingData = false;
        }

        /**
         *
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoAppRulesFound(GetAppRulesInteract.GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor apiErrorsVisitor) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
            isLoadingData = false;
        }

    }

    /**
     * Update App Rules Observable
     */
    public class UpdateAppRulesObservable extends BasicCommandCallBackWrapper<String> {

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAppRulesUpdatedSuccessfully();
            }

        }
    }


}
