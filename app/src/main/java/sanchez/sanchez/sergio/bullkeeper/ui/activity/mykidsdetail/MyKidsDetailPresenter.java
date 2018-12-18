package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetKidByIdInteract;
import sanchez.sanchez.sergio.domain.models.KidEntity;

/**
 * My Kids Detail Presenter
 */
public final class MyKidsDetailPresenter extends SupportPresenter<IMyKidsDetailView> {

    public final static String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Kid By Id Interact
     */
    private final GetKidByIdInteract getKidByIdInteract;

    @Inject
    public MyKidsDetailPresenter(final GetKidByIdInteract getKidByIdInteract) {
        this.getKidByIdInteract = getKidByIdInteract;
    }

    /**
     * Load Son Data
     */
    public void loadKidData(final String kid){

        getKidByIdInteract.execute(new GetKidByIdObservable(),
                GetKidByIdInteract.Params.create(kid));
    }

    /**
     * Get Kid By Id Observable
     */
    public class GetKidByIdObservable extends BasicCommandCallBackWrapper<KidEntity> {

        /**
         * On Success
         * @param kidEntity
         */
        @Override
        protected void onSuccess(KidEntity kidEntity) {
            if (isViewAttached() && getView() != null)
                getView().onKidLoaded(kidEntity);
        }
    }

}
