package sanchez.sanchez.sergio.masom_app.ui.fragment.dimensions;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.masom_app.ui.support.IBasicActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportFragment;

/**
 * Four Dimensions Fragment
 */
public class FourDimensionsFragment
        extends SupportFragment<FourDimensionsFragmentPresenter,
        IFourDimensionsFragmentView, IBasicActivityHandler, MyKidsComponent> implements IFourDimensionsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    private String kidIdentity;


    public FourDimensionsFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static FourDimensionsFragment newInstance(final String kidIdentity) {
        FourDimensionsFragment fragment = new FourDimensionsFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_four_dimensions;
    }

    /**
     * Initialize Injector
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
    public FourDimensionsFragmentPresenter providePresenter() {
        return component.fourDimensionsFragmentPresenter();
    }
}
