package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidsresults;

import android.os.Bundle;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetKidByIdInteract;
import sanchez.sanchez.sergio.domain.models.KidEntity;

/**
 * Kids Results Activity
 */
public final class KidsResultsActivityPresenter extends SupportPresenter<IKidsResultsView> {

    public final static String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Son By Id Interact
     */
    private final GetKidByIdInteract getKidByIdInteract;

    /**
     * Kids Results Activity Presenter
     * @param getKidByIdInteract
     */
    @Inject
    public KidsResultsActivityPresenter(final GetKidByIdInteract getKidByIdInteract) {
        this.getKidByIdInteract = getKidByIdInteract;
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
            getView().showProgressDialog(R.string.loading_kid_information);

        getKidByIdInteract.execute(new GetSonByIdObservable(),
                GetKidByIdInteract.Params.create(sonId));
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
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSonLoaded(kidEntity);
            }
        }
    }


}
