package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidrequestdetail;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.DeleteKidRequestInteract;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.GetKidRequestDetailInteract;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;

/**
 * Kid Request Detail Fragment Presenter
 */
public final class KidRequestDetailFragmentPresenter
        extends SupportPresenter<IKidRequestDetailView> {

    /**
     * Get Kid Request Detail Interact
     */
    private final GetKidRequestDetailInteract getKidRequestDetailInteract;

    /**
     * Delete Kid Request Interact
     */
    private final DeleteKidRequestInteract deleteKidRequestInteract;

    /**
     * @param getKidRequestDetailInteract
     * @param deleteKidRequestInteract
     */
    @Inject
    public KidRequestDetailFragmentPresenter(
            final GetKidRequestDetailInteract getKidRequestDetailInteract,
            final DeleteKidRequestInteract deleteKidRequestInteract){
        this.getKidRequestDetailInteract = getKidRequestDetailInteract;
        this.deleteKidRequestInteract = deleteKidRequestInteract;
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

        final String kid = args.getString(KidRequestDetailActivityMvpFragment.CHILD_ID_ARG);
        final String id = args.getString(KidRequestDetailActivityMvpFragment.ID_ARG);

        // Get Kid Request
        getKidRequestDetailInteract.execute(new GetKidRequestDetailObservable(
                GetKidRequestDetailInteract.GetKidRequestDetailApiErrors.class),
                GetKidRequestDetailInteract.Params.create(kid, id));

    }

    /**
     * Delete Kid Request
     * @param kid
     * @param id
     */
    public void deleteKidRequest(final String kid, final String id) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(id, "Id can not be null");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        // Execute delete
        deleteKidRequestInteract.execute(new DeleteKidRequestObserver(),
                DeleteKidRequestInteract.Params.create(kid, id));

    }


    /**
     * Get Kid Request Detail Observable
     */
    public class GetKidRequestDetailObservable extends CommandCallBackWrapper<KidRequestEntity,
            GetKidRequestDetailInteract.GetKidRequestDetailApiErrors.IGetKidRequestDetailApiErrorsVisitor,
            GetKidRequestDetailInteract.GetKidRequestDetailApiErrors>
            implements GetKidRequestDetailInteract.GetKidRequestDetailApiErrors.IGetKidRequestDetailApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetKidRequestDetailObservable(Class<GetKidRequestDetailInteract.GetKidRequestDetailApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param kidRequestEntity
         */
        @Override
        protected void onSuccess(final KidRequestEntity kidRequestEntity) {
            Preconditions.checkNotNull(kidRequestEntity, "Kid Request Entity can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onKidRequestLoaded(kidRequestEntity);
            }
        }

        /**
         * Visit Kid Request Not Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitKidRequestNotFound(GetKidRequestDetailInteract.GetKidRequestDetailApiErrors.IGetKidRequestDetailApiErrorsVisitor apiErrorsVisitor) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onKidRequestNotFound();
            }
        }
    }


    /**
     * Delete Kid Request Observer
     */
    public class DeleteKidRequestObserver extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            Preconditions.checkState(!response.isEmpty(), "Response can not be empty");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onKidRequestDeleted();
            }
        }
    }


}
