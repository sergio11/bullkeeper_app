package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.appdetail;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.apprules.ChangeAppStatusInteract;
import sanchez.sanchez.sergio.domain.interactor.apprules.GetAppInstalledDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.apprules.UpdateSingleAppInstalledRulesByChildInteract;
import sanchez.sanchez.sergio.domain.models.AppInstalledDetailEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledRuleEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;

/**
 * App Installed Detail Presenter
 */
public final class AppInstalledDetailFragmentPresenter extends SupportPresenter<IAppInstalledDetailView> {

    /**
     * Get App Installed Detail
     */
    private final GetAppInstalledDetailInteract getAppInstalledDetailInteract;

    /**
     * Update Single App Installed Rules By Child
     */
    private final UpdateSingleAppInstalledRulesByChildInteract updateSingleAppInstalledRulesByChildInteract;

    /**
     * Disable App Interact
     */
    private final ChangeAppStatusInteract changeAppStatusInteract;

    /**
     *
     * @param getAppInstalledDetailInteract
     * @param updateSingleAppInstalledRulesByChildInteract
     * @param changeAppStatusInteract
     */
    @Inject
    public AppInstalledDetailFragmentPresenter(
            final GetAppInstalledDetailInteract getAppInstalledDetailInteract,
            final UpdateSingleAppInstalledRulesByChildInteract updateSingleAppInstalledRulesByChildInteract,
            final ChangeAppStatusInteract changeAppStatusInteract
    ){
        this.getAppInstalledDetailInteract = getAppInstalledDetailInteract;
        this.updateSingleAppInstalledRulesByChildInteract = updateSingleAppInstalledRulesByChildInteract;
        this.changeAppStatusInteract = changeAppStatusInteract;
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        final String kid = args.getString(AppInstalledDetailActivityMvpFragment.KID_ID_ARG);
        final String terminal = args.getString(AppInstalledDetailActivityMvpFragment.TERMINAL_ID_ARG);
        final String app = args.getString(AppInstalledDetailActivityMvpFragment.APP_ID_ARG);


        getAppInstalledDetailInteract.execute(new GetAppInstalledDetailObserver(),
                GetAppInstalledDetailInteract.Params.create(kid, terminal, app));

    }

    /**
     * Update App Rule
     * @param appRuleEnum
     */
    public void updateAppRule(final AppRuleEnum appRuleEnum) {
        Preconditions.checkNotNull(appRuleEnum, "App Rule can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        final String kid = args.getString(AppInstalledDetailActivityMvpFragment.KID_ID_ARG);
        final String terminal = args.getString(AppInstalledDetailActivityMvpFragment.TERMINAL_ID_ARG);
        final String app = args.getString(AppInstalledDetailActivityMvpFragment.APP_ID_ARG);

        updateSingleAppInstalledRulesByChildInteract.execute(new UpdateAppRuleObserver(),
                UpdateSingleAppInstalledRulesByChildInteract.Params.create(kid, terminal, app,
                        new AppInstalledRuleEntity(app, appRuleEnum)));
    }

    /**
     * Switch App Status
     * @param kid
     * @param terminal
     * @param app
     * @param status
     */
    public void switchAppStatus(final String kid, final String terminal,
                                final String app, final boolean status) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkNotNull(app, "App can not be null");
        Preconditions.checkNotNull(status, "Status can not be null");


        changeAppStatusInteract.execute(new SwitchAppStatusObserver(),
                ChangeAppStatusInteract.Params.create(kid, terminal, app, status));
    }

    /**
     * Get App Installed Detail Observer
     */
    public class GetAppInstalledDetailObserver extends BasicCommandCallBackWrapper<AppInstalledDetailEntity> {

        /**
         * On Success
         * @param appInstalled
         */
        @Override
        protected void onSuccess(AppInstalledDetailEntity appInstalled) {
            Preconditions.checkNotNull(appInstalled, "Response can not be null");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAppInstalledDetailLoaded(appInstalled);
            }
        }
    }


    /**
     * Update App Rule Observer
     */
    public class UpdateAppRuleObserver extends BasicCommandCallBackWrapper<String> {

        /**
         * On Network Error
         */
        @Override
        protected void onNetworkError() {
            if (isViewAttached() && getView() != null){
                getView().hideProgressDialog();
                getView().onUpdateAppRuleFail();
            }
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            if (isViewAttached() && getView() != null){
                getView().hideProgressDialog();
                getView().onUpdateAppRuleFail();
            }
        }

        /**
         * On Api Excetion
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            if (isViewAttached() && getView() != null){
                getView().hideProgressDialog();
                getView().onUpdateAppRuleFail();
            }
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAppRuleUpdateSuccessfully();
            }
        }
    }


    /**
     * Switch App Status Observer
     */
    public class SwitchAppStatusObserver extends BasicCommandCallBackWrapper<String> {

        /**
         * On Network Error
         */
        @Override
        protected void onNetworkError() {
            if (isViewAttached() && getView() != null){
                getView().hideProgressDialog();
                getView().onAppStatusChangedFailed();
            }
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            if (isViewAttached() && getView() != null){
                getView().hideProgressDialog();
                getView().onAppStatusChangedFailed();
            }
        }

        /**
         * On Api Excetion
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            if (isViewAttached() && getView() != null){
                getView().hideProgressDialog();
                getView().onAppStatusChangedFailed();
            }
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAppStatusChangedSuccessfully();
            }
        }
    }


}
