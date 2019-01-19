package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.callslist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.CallsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;
import timber.log.Timber;

/**
 * Calls List Fragment
 */
public class CallsListMvpFragment extends SupportMvpLCEFragment<CallListFragmentPresenter,
        ICallsListFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, CallDetailEntity>
        implements ICallsListFragmentView, AdapterView.OnItemSelectedListener {

    /**
     * Kid Identity Arg
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Terminals Arg
     */
    private static final String TERMINALS_ARG = "TERMINALS_ARG";

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
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * Activity
     */
    @Inject
    protected Activity activity;


    /**
     * Views
     * ===================
     */


    /**
     * Terminals Spinner
     */
    @BindView(R.id.terminalsSpinner)
    protected AppCompatSpinner terminalsSpinner;


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
     * Terminal Identity
     *
     */
    @State
    protected String terminalIdentity;

    /**
     * Terminals List
     */
    @State
    protected ArrayList<TerminalItem> terminalItems = new ArrayList<>();

    /**
     * Current Terminal
     */
    @State
    protected TerminalItem currentTerminal;

    /**
     * Current Terminal Pos
     */
    @State
    protected int currentTerminalPos = 0;

    /**
     *
     */
    public CallsListMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static CallsListMvpFragment newInstance(final String kidIdentity, final ArrayList<TerminalItem> terminalItems) {
        CallsListMvpFragment fragment = new CallsListMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        args.putSerializable(TERMINALS_ARG, terminalItems);
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

        if (getArguments() == null ||
                !getArguments().containsKey(TERMINALS_ARG))
            throw new IllegalStateException("You must provide terminals list");

        terminalItems = (ArrayList<TerminalItem>) getArguments().getSerializable(TERMINALS_ARG);

        if(terminalItems == null || terminalItems.isEmpty())
            throw new IllegalStateException("Terminals list can not be empty");

        ArrayAdapter<TerminalItem> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item,
                terminalItems);
        terminalsSpinner.setAdapter(adapter);
        terminalsSpinner.setSelection(currentTerminalPos);
        terminalsSpinner.setOnItemSelectedListener(this);

        currentTerminal = terminalItems.get(currentTerminalPos);

        // Enable Nested Scrolling on Recycler View
        ViewCompat.setNestedScrollingEnabled(recyclerView, true);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<CallDetailEntity> getAdapter() {
        final CallsAdapter callsAdapter = new CallsAdapter(activity, new ArrayList<CallDetailEntity>());
        callsAdapter.setOnSupportRecyclerViewListener(this);
        return callsAdapter;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(CallListFragmentPresenter.KID_IDENTITY_ARG, kidIdentity);
        args.putSerializable(CallListFragmentPresenter.CURRENT_TERMINAL_ARG, currentTerminal);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_call_list;
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
     * @param callDetailEntity
     */
    @Override
    public void onItemClick(final CallDetailEntity callDetailEntity) {
        Preconditions.checkNotNull(callDetailEntity, "Call Detail Entity can not be null");
        Preconditions.checkState(!callDetailEntity.getIdentity().isEmpty(),
                "Call Detail Id can not be empty");
        // Navigate To Call Detail
        activityHandler.navigateToCallDetail(kidIdentity, terminalIdentity,
                callDetailEntity.getIdentity());
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
    public CallListFragmentPresenter providePresenter() {
        return component.callListFragmentPresenter();
    }

    /**
     * On Item Selected
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Timber.d("New Position Selected -> %d", position);
        currentTerminalPos = position;
        currentTerminal = terminalItems.get(currentTerminalPos);
        terminalIdentity = currentTerminal.getIdentity();
        loadData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}


    /**
     * On Phone Numbers Black List Clicked
     */
    @OnClick(R.id.phoneNumbersBlackList)
    protected void onPhoneNumbersBlackListClicked(){
        activityHandler.navigateToPhoneNumbersBlackList(kidIdentity, terminalIdentity);
    }
}

