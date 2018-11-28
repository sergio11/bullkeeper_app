package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail;

import android.os.Bundle;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetSonByIdInteract;
import sanchez.sanchez.sergio.domain.models.KidEntity;

/**
 * My Kids Detail Presenter
 */
public final class MyKidsDetailPresenter extends SupportPresenter<IMyKidsDetailView> {

    public final static String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Son By Id Interact
     */
    private final GetSonByIdInteract getSonByIdInteract;

    @Inject
    public MyKidsDetailPresenter(final GetSonByIdInteract getSonByIdInteract) {
        this.getSonByIdInteract = getSonByIdInteract;
    }

    /**
     * On Init
     */
    @Override
    protected void onInit(final Bundle args) {
        super.onInit();
        if(args != null && args.containsKey(KID_IDENTITY_ARG)) {
            // Load Son Data
            loadSonData(args.getString(KID_IDENTITY_ARG));
        }
    }


    /**
     * Load Son Data
     */
    private void loadSonData(final String sonId){

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_kid_information);

        getSonByIdInteract.execute(new GetSonByIdObservable(),
                GetSonByIdInteract.Params.create(sonId));
    }

    /**
     * Get Son By Id Observable
     */
    public class GetSonByIdObservable extends BasicCommandCallBackWrapper<KidEntity> {

        /**
         * On Success
         * @param kidEntity
         */
        @Override
        protected void onSuccess(KidEntity kidEntity) {
            if (isViewAttached() && getView() != null)
                getView().onSonLoaded(kidEntity);
        }
    }

}
