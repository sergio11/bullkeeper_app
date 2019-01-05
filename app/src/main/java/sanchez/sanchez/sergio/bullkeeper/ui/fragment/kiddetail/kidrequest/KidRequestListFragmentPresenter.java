package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.kidrequest;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.DeleteAllKidRequestInteract;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.DeleteKidRequestInteract;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.GetKidRequestInteract;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;

/**
 * Kid Request List Fragment Presenter
 */
public final class KidRequestListFragmentPresenter
        extends SupportLCEPresenter<IKidRequestListFragmentView> {

    /**
     * Args
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


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
     * Is Loading Data
     */
    private boolean isLoadingData = false;


    /**
     *
     * @param getKidRequestInteract
     * @param deleteAllKidRequestInteract
     * @param deleteKidRequestInteract
     */
    @Inject
    public KidRequestListFragmentPresenter(
            final GetKidRequestInteract getKidRequestInteract,
            final DeleteAllKidRequestInteract deleteAllKidRequestInteract,
            final DeleteKidRequestInteract deleteKidRequestInteract
    ){
        this.getKidRequestInteract = getKidRequestInteract;
        this.deleteAllKidRequestInteract = deleteAllKidRequestInteract;
        this.deleteKidRequestInteract = deleteKidRequestInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() { throw  new IllegalStateException("Args can not be empty"); }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KID_IDENTITY_ARG), "You must provide a son identity value");

        if(isLoadingData)
            return;

        isLoadingData = true;

        if (isViewAttached() && getView() != null)
            getView().onShowLoading();


        getKidRequestInteract.execute(new GetKidRequestObservable(
                GetKidRequestInteract.GetKidRequestApiErrors.class
        ), GetKidRequestInteract.Params.create(args.getString(KID_IDENTITY_ARG)));


    }

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
         * On Network Error
         */
        @Override
        protected void onNetworkError() {
            super.onNetworkError();
            isLoadingData = false;
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            super.onOtherException(ex);
            isLoadingData = false;
        }

        /**
         * On API Exception
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            super.onApiException(response);
            isLoadingData = false;
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
            isLoadingData = false;
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
            isLoadingData = false;
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
                getView().onNoDataFound();
                getView().showNoticeDialog(R.string.all_kid_request_eliminated_successfully);
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
