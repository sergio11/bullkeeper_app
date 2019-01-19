package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smslist;

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
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.SmsListAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.domain.models.SmsEntity;
import timber.log.Timber;

/**
 * Sms List Fragment
 */
public class SmsListMvpFragment extends SupportMvpLCEFragment<SmsListFragmentPresenter,
        ISmsListFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, SmsEntity>
        implements ISmsListFragmentView, AdapterView.OnItemSelectedListener {

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
     * Current Terminal Pos
     */
    @State
    protected int currentTerminalPos = 0;

    /**
     * Current Terminal
     */
    @State
    protected TerminalItem currentTerminal;

    /**
     *
     */
    public SmsListMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static SmsListMvpFragment newInstance(final String kidIdentity, final ArrayList<TerminalItem> terminalItems) {
        SmsListMvpFragment fragment = new SmsListMvpFragment();
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
    protected SupportRecyclerViewAdapter<SmsEntity> getAdapter() {
        final SmsListAdapter smsListAdapter = new SmsListAdapter(activity, new ArrayList<SmsEntity>());
        smsListAdapter.setOnSupportRecyclerViewListener(this);
        return smsListAdapter;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(SmsListFragmentPresenter.KID_IDENTITY_ARG, kidIdentity);
        args.putSerializable(SmsListFragmentPresenter.CURRENT_TERMINAL_ARG, currentTerminal);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_sms_list;
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
     * @param smsEntity
     */
    @Override
    public void onItemClick(SmsEntity smsEntity) {
        Preconditions.checkNotNull(smsEntity, "Sms Entity can not be null");
        Preconditions.checkNotNull(smsEntity.getIdentity(), "Sms Identity can not be null");
        Preconditions.checkState(!smsEntity.getIdentity().isEmpty(), "SMS Identity can not be empty");
        activityHandler.navigateToSmsDetail(kidIdentity, terminalIdentity, smsEntity.getIdentity());
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
    public SmsListFragmentPresenter providePresenter() {
        return component.smsListFragmentPresenter();
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
        terminalIdentity =  currentTerminal.getIdentity();
        loadData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}

