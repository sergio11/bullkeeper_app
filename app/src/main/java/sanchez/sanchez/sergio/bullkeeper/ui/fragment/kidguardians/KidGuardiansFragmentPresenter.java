package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidguardians;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetKidGuardiansInteract;

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
    public void loadData() { }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KID_IDENTITY_ARG), "You must provide a kid identity value");

        final String kid = args.getString(KID_IDENTITY_ARG);;
    }




}
