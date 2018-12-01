package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidguardians;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetKidGuardiansInteract;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;

/**
 * Kid Guardians Fragment Presenter
 */
public final class KidGuardiansFragmentPresenter extends SupportLCEPresenter<IKidGuardiansFragmentView> {

    /**
     * Kid Identity Arg
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


    /**
     * Get Kid Guardians Interact
     */
    private final GetKidGuardiansInteract getKidGuardiansInteract;

    /**
     * @param getKidGuardiansInteract
     */
    @Inject
    public KidGuardiansFragmentPresenter(final GetKidGuardiansInteract getKidGuardiansInteract){
        this.getKidGuardiansInteract = getKidGuardiansInteract;
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {
        throw new IllegalArgumentException("You must provide a kid identity value");
    }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KID_IDENTITY_ARG), "You must provide a kid identity value");

        final String kid = args.getString(KID_IDENTITY_ARG);

        // Get Kid Guardians
        getKidGuardiansInteract.execute(new GetKidGuardiansObservable(GetKidGuardiansInteract.GetKidGuardiansApiErrors.class),
                GetKidGuardiansInteract.Params.create(kid));

    }


    /**
     * Get Kid Guardians Observable
     */
    public class GetKidGuardiansObservable extends CommandCallBackWrapper<List<KidGuardianEntity>,
            GetKidGuardiansInteract.GetKidGuardiansApiErrors.IGetKidGuardiansApiErrorsVisitor,
            GetKidGuardiansInteract.GetKidGuardiansApiErrors>
            implements GetKidGuardiansInteract.GetKidGuardiansApiErrors
                .IGetKidGuardiansApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetKidGuardiansObservable(Class<GetKidGuardiansInteract.GetKidGuardiansApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         *
         * @param response
         */
        @Override
        protected void onSuccess(final List<KidGuardianEntity> response) {
            if(isViewAttached() && getView() != null){
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }

        }

        /**
         *
         * @param error
         */
        @Override
        public void visitKidGuardianFound(GetKidGuardiansInteract.GetKidGuardiansApiErrors error) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }

}
