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
import sanchez.sanchez.sergio.domain.interactor.apprules.GetAppInstalledInteract;
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
    public static final String CURRENT_TERMINAL_ARG = "CURRENT_TERMINAL_ARG";

    /**
     * Get App Rules Interact
     */
    private final GetAppInstalledInteract getAppInstalledInteract;

    /**
     * Update App Installed Rules By Child Interact
     */
    private final UpdateAppInstalledRulesByChildInteract updateAppInstalledRulesByChildInteract;

    /**
     * Is Loading Data
     */
    private boolean isLoadingData = false;

    /**
     * @param getAppInstalledInteract
     * @param updateAppInstalledRulesByChildInteract
     */
    @Inject
    public AppRulesFragmentPresenter(final GetAppInstalledInteract getAppInstalledInteract,
                                     final UpdateAppInstalledRulesByChildInteract updateAppInstalledRulesByChildInteract){
        this.getAppInstalledInteract = getAppInstalledInteract;
        this.updateAppInstalledRulesByChildInteract = updateAppInstalledRulesByChildInteract;
    }

    /**
     * Load Data
     * @param args
     * @param queryText
     */
    @Override
    public void loadData(Bundle args, final String queryText) {
        super.loadData(args, queryText);
        loadApps(args, queryText);
    }


    /**
     * Load Apps
     * @param args
     * @param queryText
     */
    private void loadApps(final Bundle args, final String queryText) {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KID_IDENTITY_ARG), "You must provide a kid identity value");
        Preconditions.checkState(args.containsKey(CURRENT_TERMINAL_ARG), "You must provide Current Terminal");

        if (isLoadingData)
            return;

        isLoadingData = true;

        final TerminalItem terminalItem = (TerminalItem) args.getSerializable(CURRENT_TERMINAL_ARG);

        if(terminalItem != null) {

            if (isViewAttached() && getView() != null)
                getView().onShowLoading();

            getAppInstalledInteract.execute(new GetAppRulesObservable(GetAppInstalledInteract.GetAppRulesApiErrors.class),
                    GetAppInstalledInteract.Params.create(
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
            GetAppInstalledInteract.GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor,
            GetAppInstalledInteract.GetAppRulesApiErrors>
            implements GetAppInstalledInteract.GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor {


        /**
         *
         * @param apiErrors
         */
        public GetAppRulesObservable(Class<GetAppInstalledInteract.GetAppRulesApiErrors> apiErrors) {
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
        public void visitNoAppRulesFound(GetAppInstalledInteract.GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor apiErrorsVisitor) {
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
