package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactlist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpSearchLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.ContactsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.domain.models.ContactEntity;
import timber.log.Timber;

/**
 * Contact List Fragment
 */
public class ContactListMvpFragment extends SupportMvpSearchLCEFragment<ContactFragmentPresenter,
        IContactListFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, ContactEntity>
        implements IContactListFragmentView, AdapterView.OnItemSelectedListener ,
        SupportItemTouchHelper.ItemTouchHelperListener{

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
    protected TerminalItem currentTerminalItem;

    /**
     *
     */
    public ContactListMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static ContactListMvpFragment newInstance(final String kidIdentity, final ArrayList<TerminalItem> terminalItems) {
        ContactListMvpFragment fragment = new ContactListMvpFragment();
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

        currentTerminalItem = terminalItems.get(currentTerminalPos);

        // Enable Nested Scrolling on Recycler View
        ViewCompat.setNestedScrollingEnabled(recyclerView, true);


        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<ContactsAdapter.ContactDetailViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    /**
     * Get Search Hint Res
     * @return
     */
    @Override
    protected int getSearchHintRes() {
        return R.string.search_contacts_hint;
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<ContactEntity> getAdapter() {
        final ContactsAdapter contactsAdapter = new ContactsAdapter(activity,
                new ArrayList<ContactEntity>());
        contactsAdapter.setOnSupportRecyclerViewListener(this);
        return contactsAdapter;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(ContactFragmentPresenter.KID_IDENTITY_ARG, kidIdentity);
        args.putSerializable(ContactFragmentPresenter.CURRENT_TERMINAL_ARG, currentTerminalItem);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_contacts_list;
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
     * @param contactEntity
     */
    @Override
    public void onItemClick(final ContactEntity contactEntity) {
        Preconditions.checkNotNull(contactEntity, "Contact Entity can not be null");
        Preconditions.checkNotNull(contactEntity.getIdentity(), "Contact Identity can not be null");
        Preconditions.checkState(!contactEntity.getIdentity().isEmpty(), "Contact Identity can not be empty");

        activityHandler.navigateToContactDetail(
                kidIdentity,
                terminalIdentity,
                contactEntity.getIdentity()
        );
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
    public ContactFragmentPresenter providePresenter() {
        return component.contactFragmentPresenter();
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
        currentTerminalItem = terminalItems.get(currentTerminalPos);
        terminalIdentity = currentTerminalItem.getIdentity();
        loadData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    /**
     *
     * @param viewHolder
     * @param direction
     * @param position
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ContactsAdapter.ContactDetailViewHolder) {

            final Integer deletedIndex = viewHolder.getAdapterPosition();
            final ContactEntity contactEntity = recyclerViewAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            recyclerViewAdapter.removeItem(deletedIndex);

            if(!activityHandler.isConnectivityAvailable()) {
                showNoticeDialog(R.string.connectivity_not_available, false);
                recyclerViewAdapter.restoreItem(contactEntity, deletedIndex);
            } else {
                showLongSimpleSnackbar(content,
                        getString(R.string.contact_item_disabled),
                        getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerViewAdapter.restoreItem(contactEntity, deletedIndex);
                    }
                }, new Snackbar.Callback(){
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        if(event == DISMISS_EVENT_TIMEOUT) {
                            getPresenter().disableContact(
                                    contactEntity.getKid(),
                                    contactEntity.getTerminal(),
                                    contactEntity.getIdentity());
                        }
                    }
                });
            }

        }
    }

    /**
     * on Contact Disabled Successfully
     */
    @Override
    public void onContactDisabledSuccessfully() {
        if(getAdapter().getData().isEmpty())
            onNoDataFound();
    }

    /**
     * On Error Disabling Contact
     */
    @Override
    public void onErrorDisablingContact() {
        showNoticeDialog(getString(R.string.error_disabling_contact), false, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                loadData();
            }
        });
    }
}

