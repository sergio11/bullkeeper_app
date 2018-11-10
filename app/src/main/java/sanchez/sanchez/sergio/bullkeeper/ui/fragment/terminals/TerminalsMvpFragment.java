package sanchez.sanchez.sergio.bullkeeper.ui.fragment.terminals;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import javax.inject.Inject;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.TerminalsAdapter;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import timber.log.Timber;

/**
 * Terminals Fragment
 */
public class TerminalsMvpFragment extends SupportMvpLCEFragment<TerminalsFragmentPresenter,
        ITerminalsFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, TerminalEntity>
        implements ITerminalsFragmentView {

    /**
     * Kid Identity
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


    /**
     * Dependencies
     * =================
     */

    /**
     * App Context
     */
    @Inject
    protected Context appContext;

    /**
     * Activity
     */
    @Inject
    protected Activity activity;

    /**
     * State
     * ===================
     */

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;


    /**
     * Views
     * =========
     */


    /**
     *
     */
    public TerminalsMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static TerminalsMvpFragment newInstance(final String kidIdentity) {
        TerminalsMvpFragment fragment = new TerminalsMvpFragment();
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

        if(getArguments() == null ||
                !getArguments().containsKey(KID_IDENTITY_ARG))
            throw new IllegalStateException("You must provide son identity - Illegal State");

        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);

        ViewCompat.setNestedScrollingEnabled(recyclerView, true);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<TerminalEntity> getAdapter() {
        final TerminalsAdapter terminalsAdapter = new TerminalsAdapter(activity, new ArrayList<TerminalEntity>());
        terminalsAdapter.setOnSupportRecyclerViewListener(this);
        return terminalsAdapter;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(TerminalsFragmentPresenter.SON_IDENTITY_ARG, kidIdentity);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_terminals_list;
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
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * On Item Click
     * @param terminalEntity
     */
    @Override
    public void onItemClick(TerminalEntity terminalEntity) {
        Timber.d("Terminal clicked");
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public TerminalsFragmentPresenter providePresenter() {
        return component.terminalsFragmentPresenter();
    }
}

