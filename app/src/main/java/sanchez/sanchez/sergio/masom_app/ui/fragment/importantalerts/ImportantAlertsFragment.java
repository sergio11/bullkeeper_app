package sanchez.sanchez.sergio.masom_app.ui.fragment.importantalerts;

import android.os.Bundle;
import android.support.annotation.NonNull;

import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.masom_app.ui.support.IBasicActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportFragment;

/**
 * Important Alerts Fragment
 */
public class ImportantAlertsFragment extends SupportFragment<ImportantAlertsFragmentPresenter,
        IImportantAlertsFragmentView, IBasicActivityHandler, MyKidsComponent> implements IImportantAlertsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


    /**
     * Kid Identity
     */
    private String kidIdentity;


    public ImportantAlertsFragment() {
        // Required empty public constructor
    }


    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static ImportantAlertsFragment newInstance(final String kidIdentity) {
        ImportantAlertsFragment fragment = new ImportantAlertsFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_important_alerts;
    }

    /**
     * Initialize Injector
     * @param component
     */
    @Override
    protected void initializeInjector(MyKidsComponent component) {
        component.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public ImportantAlertsFragmentPresenter providePresenter() {
        return component.importantAlertsFragmentPresenter();
    }
}
