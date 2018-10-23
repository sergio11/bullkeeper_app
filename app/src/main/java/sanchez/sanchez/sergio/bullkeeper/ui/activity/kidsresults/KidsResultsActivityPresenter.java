package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidsresults;

import android.os.Bundle;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetSonByIdInteract;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import timber.log.Timber;

/**
 * Kids Results Activity
 */
public final class KidsResultsActivityPresenter extends SupportPresenter<IKidsResultsView> {

    public final static String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Son By Id Interact
     */
    private final GetSonByIdInteract getSonByIdInteract;

    /**
     * Kids Results Activity Presenter
     * @param getSonByIdInteract
     */
    @Inject
    public KidsResultsActivityPresenter(final GetSonByIdInteract getSonByIdInteract) {
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
        Preconditions.checkNotNull(sonId, "Son Id can not be null");
        Preconditions.checkState(!sonId.isEmpty(), "Son Id can not be empty");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_son_information);

        getSonByIdInteract.execute(new GetSonByIdObservable(),
                GetSonByIdInteract.Params.create(sonId));
    }

    /**
     * Get Son By Id Observable
     */
    public class GetSonByIdObservable extends BasicCommandCallBackWrapper<SonEntity> {

        /**
         * On Success
         * @param sonEntity
         */
        @Override
        protected void onSuccess(SonEntity sonEntity) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSonLoaded(sonEntity);
            }
        }
    }


}
