package sanchez.sanchez.sergio.bullkeeper.ui.fragment.apprules;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.apprules.GetAppRulesInteract;
import sanchez.sanchez.sergio.domain.interactor.apprules.UpdateAppInstalledRulesByChildInteract;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledRuleEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;

/**
 * App Rules Fragment Presenter
 */
public final class AppRulesFragmentPresenter extends SupportLCEPresenter<IAppRulesFragmentView> {

    public static final String SON_IDENTITY_ARG = "SON_IDENTITY_ARG";

    /**
     * Get App Rules Interact
     */
    private final GetAppRulesInteract getAppRulesInteract;

    /**
     * Update App Installed Rules By Child Interact
     */
    private final UpdateAppInstalledRulesByChildInteract updateAppInstalledRulesByChildInteract;

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

        getAppRulesInteract.execute(new GetAppRulesObservable(GetAppRulesInteract.GetAppRulesApiErrors.class),
                GetAppRulesInteract.Params.create(args.getString(SON_IDENTITY_ARG), (args.getString(SON_IDENTITY_ARG))));

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
        public void visitNoAppRulesFound(GetAppRulesInteract.GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor apiErrorsVisitor) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
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
