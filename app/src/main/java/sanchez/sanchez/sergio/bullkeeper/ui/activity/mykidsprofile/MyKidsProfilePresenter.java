package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile;

import android.os.Bundle;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetSonByIdInteract;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import static sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile.MyKidsProfileMvpActivity.KIDS_IDENTITY_ARG;

/**
 * My Kids Profile Presenter
 */
public final class MyKidsProfilePresenter extends SupportPresenter<IMyKidsProfileView> {

    /**
     * Get Son By Id Interact
     */
    private final GetSonByIdInteract getSonByIdInteract;

    /**
     *
     */
    @Inject
    public MyKidsProfilePresenter(final GetSonByIdInteract getSonByIdInteract) {
        super();
        this.getSonByIdInteract = getSonByIdInteract;

    }

    /**
     * Attach Observable
     */
    protected void attachObservables(){
        getSonByIdInteract.attachDisposablesTo(compositeDisposable);
    }

    /**
     * On Init
     */
    @Override
    protected void onInit() {
        super.onInit();
        attachObservables();
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KIDS_IDENTITY_ARG), "Args no contain son identity");
        attachObservables();
        // Load Son Data
        loadSonData(args.getString(KIDS_IDENTITY_ARG));

    }

    /**
     * Load Son Data
     * @param sonId
     */
    public void loadSonData(final String sonId) {
        Preconditions.checkNotNull(sonId, "Son Id can not be null");
        Preconditions.checkState(!sonId.isEmpty(), "Son Id can not be empty");

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_son_information);

        getSonByIdInteract.execute(new GetSonByIdObservable(), GetSonByIdInteract.Params.create(sonId));

    }

    /**
     * Get Son By Id Observable
     */
    public class GetSonByIdObservable extends BasicCommandCallBackWrapper<SonEntity> {

        /**
         * ON Success
         * @param sonEntity
         */
        @Override
        protected void onSuccess(SonEntity sonEntity) {
            if(isViewAttached() && getView() != null)
                getView().onSonProfileLoaded(sonEntity);
        }
    }
}
