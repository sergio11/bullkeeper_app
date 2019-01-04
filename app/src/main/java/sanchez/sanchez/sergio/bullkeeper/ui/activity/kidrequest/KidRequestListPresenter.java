package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequest;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.DeleteAllKidRequestInteract;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.DeleteKidRequestInteract;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.GetKidRequestInteract;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;

/**
 * Kid Request List Presenter
 */
public final class KidRequestListPresenter
        extends SupportLCEPresenter<IKidRequestListView> {

    /**
     * Args
     */
    public final static String KID_ID_ARG = "KID_ID_ARG";

    /**
     * Get Kid Request Interact
     */
    private final GetKidRequestInteract getKidRequestInteract;

    /**
     * Delete All Kid Request Interact
     */
    private final DeleteAllKidRequestInteract deleteAllKidRequestInteract;

    /**
     * Delete Kid Request Interact
     */
    private final DeleteKidRequestInteract deleteKidRequestInteract;

    /**
     * @param getKidRequestInteract
     * @param deleteAllKidRequestInteract
     * @param deleteKidRequestInteract
     */
    @Inject
    public KidRequestListPresenter(
            final GetKidRequestInteract getKidRequestInteract,
            final DeleteAllKidRequestInteract deleteAllKidRequestInteract,
            final DeleteKidRequestInteract deleteKidRequestInteract) {
        this.getKidRequestInteract = getKidRequestInteract;
        this.deleteAllKidRequestInteract = deleteAllKidRequestInteract;
        this.deleteKidRequestInteract = deleteKidRequestInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {
        Preconditions.checkState(!args.getString(KID_ID_ARG, "").isEmpty(),
                "Kid id can not be empty");

        getKidRequestInteract.execute(new GetKidRequestObservable(
                GetKidRequestInteract.GetKidRequestApiErrors.class
        ), GetKidRequestInteract.Params.create(args.getString(KID_ID_ARG)));
    }

    /**
     * Load Data
     */
    @Override
    public void loadData(final Bundle args) { loadData(); }

    /**
     * Delete All Kid Request For Kid
     * @param kid
     */
    public void deleteAllKidRequestForKid(final String kid) {
        Preconditions.checkNotNull(kid, "kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "kid can not be empty");

        deleteAllKidRequestInteract.execute(new DeleteAllKidRequestObservable(),
                DeleteAllKidRequestInteract.Params.create(kid));
    }

    /**
     * Delete Kid Request
     * @param kid
     * @param id
     */
    public void deleteKidRequest(final String kid, final String id) {
        Preconditions.checkNotNull(kid, "kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "kid can not be empty");
        Preconditions.checkNotNull(id, "id can not be null");
        Preconditions.checkState(!id.isEmpty(), "id can not be empty");

        deleteKidRequestInteract.execute(new DeleteKidRequestObservable(),
                DeleteKidRequestInteract.Params.create(kid, id));
    }


    /**
     * Get Kid Request Observable
     */
    public class GetKidRequestObservable extends CommandCallBackWrapper<List<KidRequestEntity>,
            GetKidRequestInteract.GetKidRequestApiErrors.IGetKidRequestApiErrorsVisitor,
            GetKidRequestInteract.GetKidRequestApiErrors>
            implements GetKidRequestInteract.GetKidRequestApiErrors.IGetKidRequestApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetKidRequestObservable(final Class<GetKidRequestInteract.GetKidRequestApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final List<KidRequestEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            Preconditions.checkState(!response.isEmpty(), "Response can not be empty");

            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }

        }

        /**
         * Visit No Kid Request Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoKidRequestFound(GetKidRequestInteract.GetKidRequestApiErrors.IGetKidRequestApiErrorsVisitor apiErrorsVisitor) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }


    /**
     * Delete All Kid Request Observable
     */
    public class DeleteAllKidRequestObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final String response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAllKidRequestDeleted();
            }

        }
    }

    /**
     * Delete Kid Request Observable
     */
    public class DeleteKidRequestObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final String response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onKidRequestDeleted();
            }

        }
    }

}
