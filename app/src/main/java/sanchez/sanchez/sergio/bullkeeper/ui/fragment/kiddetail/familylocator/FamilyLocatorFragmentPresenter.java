package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.familylocator;

import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetKidLocationInteract;
import sanchez.sanchez.sergio.domain.models.LocationEntity;

/**
 * Family Locator  Fragment Presenter
 */
public final class FamilyLocatorFragmentPresenter extends SupportPresenter<IFamilyLocatorFragmentView> {

    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Get Kid Location Interact
     */
    private final GetKidLocationInteract getKidLocationInteract;

    /**
     *
     * @param getKidLocationInteract
     */
    @Inject
    public FamilyLocatorFragmentPresenter(final GetKidLocationInteract getKidLocationInteract) {
        this.getKidLocationInteract = getKidLocationInteract;
    }

    /**
     * Get Current Location
     */
    public void getCurrentLocation(){
        if (args == null || args.isEmpty() || !args.containsKey(KID_IDENTITY_ARG))
            throw new IllegalStateException("You must provide the child's identifier");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        final String kid = args.getString(KID_IDENTITY_ARG);

        getKidLocationInteract.execute(new GetKidLocationObservable(
                        GetKidLocationInteract.GetKidLocationApiErrors.class),
                GetKidLocationInteract.Params.create(kid));
    }

    /**
     * Get Kid Location Observable
     */
    public class GetKidLocationObservable extends CommandCallBackWrapper<LocationEntity,
            GetKidLocationInteract.GetKidLocationApiErrors.IGetKidLocationApiErrorsVisitor,
            GetKidLocationInteract.GetKidLocationApiErrors>
            implements GetKidLocationInteract.GetKidLocationApiErrors.IGetKidLocationApiErrorsVisitor {


        /**
         *
         * @param apiErrors
         */
        public GetKidLocationObservable(final Class<GetKidLocationInteract.GetKidLocationApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final LocationEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onCurrentLocationLoaded(response);
            }
        }

        /**
         * Visit No Location Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoLocationFound(GetKidLocationInteract.GetKidLocationApiErrors.IGetKidLocationApiErrorsVisitor apiErrorsVisitor) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().noCurrentLocationFound();
            }
        }
    }
}
