package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.appdetail;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.apprules.GetAppInstalledDetailInteract;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
/**
 * App Installed Detail Presenter
 */
public final class AppInstalledDetailFragmentPresenter extends SupportPresenter<IAppInstalledDetailView> {

    /**
     * Get App Installed Detail
     */
    private final GetAppInstalledDetailInteract getAppInstalledDetailInteract;

    /**
     *
     * @param getAppInstalledDetailInteract
     */
    @Inject
    public AppInstalledDetailFragmentPresenter(
            final GetAppInstalledDetailInteract getAppInstalledDetailInteract
    ){
        this.getAppInstalledDetailInteract = getAppInstalledDetailInteract;
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
     * Get App Installed Detail Observer
     */
    public class GetAppInstalledDetailObserver extends BasicCommandCallBackWrapper<AppInstalledEntity> {

        /**
         * On Success
         * @param appInstalled
         */
        @Override
        protected void onSuccess(AppInstalledEntity appInstalled) {
            Preconditions.checkNotNull(appInstalled, "Response can not be null");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAppInstalledDetailLoaded(appInstalled);
            }
        }
    }


}
