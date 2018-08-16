package sanchez.sanchez.sergio.masom_app.ui.fragment.relations;

import android.os.Bundle;
import android.support.annotation.NonNull;

import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.masom_app.ui.support.IBasicActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportFragment;

/**
 * Kid Relations Fragment
 */
public class KidRelationsFragment extends SupportFragment<KidRelationFragmentPresenter,
        IKidRelationsFragmentView, IBasicActivityHandler, MyKidsComponent>
        implements IKidRelationsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


    /**
     * Kid Identity
     */
    private String kidIdentity;


    public KidRelationsFragment() {
        // Required empty public constructor
    }


    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static KidRelationsFragment newInstance(final String kidIdentity) {
        KidRelationsFragment fragment = new KidRelationsFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_kid_relations;
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
    public KidRelationFragmentPresenter providePresenter() {
        return component.kidRelationFragmentPresenter();
    }
}
