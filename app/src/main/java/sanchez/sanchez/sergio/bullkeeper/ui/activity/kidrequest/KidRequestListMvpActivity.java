package sanchez.sanchez.sergio.bullkeeper.ui.activity.phonenumbersblocked;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerPhoneNumberComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.PhoneNumberComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.PhoneNumbersBlockedAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.AddPhoneNumberBlockedDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Phone Numbers Blocked List Activity
 */
public class PhoneNumbersBlockedListMvpActivity extends SupportMvpLCEActivity<PhoneNumbersBlockedListPresenter,
        IPhoneNumbersBlockedListView, PhoneNumberBlockedEntity>
        implements HasComponent<PhoneNumberComponent>, IPhoneNumbersBlockedListActivityHandler
        , IPhoneNumbersBlockedListView,
        SupportItemTouchHelper.ItemTouchHelperListener {

    private final String CONTENT_FULL_NAME = "PHONE_NUMBERS_BLOCKED_LIST";
    private final String CONTENT_TYPE_NAME = "PHONE_NUMBERS_BLOCKED";

    /**
     * Args
     */

    private final static String KID_ID_ARG = "KID_ID_ARG";
    private final static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";


    /**
     * Phone Number Component
     */
    private PhoneNumberComponent phoneNumberComponent;

    /**
     * State
     * ================
     *
     */


    /**
     * Kid
     */
    @State
    protected String kid;

    /**
     * Terminal
     */
    @State
    protected String terminal;


    /**
     * Views
     * =============
     */

    /**
     * Phone Numbers Blocked Text View
     */
    @BindView(R.id.phoneNumbersBlockedTitle)
    protected TextView phoneNumbersBlockedTitleTextView;

    /**
     * Delete All Phone Numbers
     */
    @BindView(R.id.deleteAllPhoneNumbersPhone)
    protected ImageView deleteAllPhoneNumbersPhoneImageView;

    /**
     * Get Calling Intent
     * @param context
     * @param kid
     * @param terminal
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid, final String terminal) {
        final Intent intent = new Intent(context, PhoneNumbersBlockedListMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        intent.putExtra(TERMINAL_ID_ARG, terminal);
        return intent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.phoneNumberComponent = DaggerPhoneNumberComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.phoneNumberComponent.inject(this);
    }


    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public PhoneNumbersBlockedListPresenter providePresenter() {
        return phoneNumberComponent.phoneNumbersBlockedListPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public PhoneNumberComponent getComponent() {
        return phoneNumberComponent;
    }


    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        super.getArgs();
        final Bundle args = new Bundle();
        args.putString(PhoneNumbersBlockedListPresenter.KID_ID_ARG, kid);
        args.putString(PhoneNumbersBlockedListPresenter.TERMINAL_ID_ARG, terminal);
        return args;
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * On Item Click
     * @param item
     */
    @Override
    public void onItemClick(PhoneNumberBlockedEntity item) {}

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}

    /**
     * On Swiped
     * @param viewHolder
     * @param direction
     * @param position
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof PhoneNumbersBlockedAdapter.PhoneNumberBlockedViewHolder) {

            final Integer deletedIndex = viewHolder.getAdapterPosition();
            final PhoneNumberBlockedEntity phoneNumberBlocked =
                    recyclerViewAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            recyclerViewAdapter.removeItem(deletedIndex);

            showLongSimpleSnackbar(content, getString(R.string.phone_number_item_removed), getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewAdapter.restoreItem(phoneNumberBlocked, deletedIndex);
                }
            }, new Snackbar.Callback(){
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    if(event == DISMISS_EVENT_TIMEOUT) {
                        // Delete Invitation
                        getPresenter().deletePhoneNumberBlocked(
                                phoneNumberBlocked.getPhoneNumber());
                    }
                }
            });

        }
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return RETURN_TOOLBAR;
    }

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_phone_numbers_blocked;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        if (getIntent().getExtras() != null) {

            final Bundle extras = getIntent().getExtras();

            if (!extras.containsKey(KID_ID_ARG))
                throw new IllegalArgumentException("You must provide kid id");

            kid = extras.getString(KID_ID_ARG);

            if (!extras.containsKey(TERMINAL_ID_ARG))
                throw new IllegalArgumentException("You must provide terminal id");

            terminal = extras.getString(TERMINAL_ID_ARG);

        } else {

            throw new IllegalArgumentException("You must provide args");
        }

        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<PhoneNumbersBlockedAdapter.PhoneNumberBlockedViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<PhoneNumberBlockedEntity> getAdapter() {
        return new PhoneNumbersBlockedAdapter(this, new ArrayList<PhoneNumberBlockedEntity>());
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_4;
    }

    /**
     * On Delete All Phone Numbers Blocked
     */
    @OnClick(R.id.deleteAllPhoneNumbersPhone)
    protected void onDeleteAllPhoneNumbersBlocked(){
        showConfirmationDialog(R.string.phone_numbers_blocked_delete_all_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                getPresenter().deleteAllPhoneNumbersBlocked();
            }

            @Override
            public void onRejected(DialogFragment dialog) {}
        });

    }

    /**
     * On Add Phone Number
     */
    @OnClick(R.id.addPhoneNumber)
    protected void onAddPhoneNumber(){
        AddPhoneNumberBlockedDialogFragment.showDialog(this,
                getString(R.string.phone_numbers_specify_number_lock), new AddPhoneNumberBlockedDialogFragment.OnPhoneNumberSelectedDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog, String phoneNumber) {
                Preconditions.checkNotNull(dialog, "Dialog can not be null");
                Preconditions.checkNotNull(phoneNumber, "Phone Number can not be null");
                getPresenter().addPhoneNumberToBlocked(phoneNumber);
            }

            @Override
            public void onRejected(DialogFragment dialog) {}
        });
    }

    /**
     * On All Invitations Cleared
     */
    @Override
    public void onAllPhoneNumbersDeleted() {
        showNoticeDialog(R.string.phone_numbers_deleted_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                recyclerViewAdapter.removeAll();
                closeActivity();
            }
        });
    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<PhoneNumberBlockedEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);
        deleteAllPhoneNumbersPhoneImageView.setVisibility(View.VISIBLE);
        deleteAllPhoneNumbersPhoneImageView.setEnabled(true);
        phoneNumbersBlockedTitleTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.phone_numbers_blocked_title_count),dataLoaded.size()));

    }

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        super.onNoDataFound();
        deleteAllPhoneNumbersPhoneImageView.setVisibility(View.GONE);
        deleteAllPhoneNumbersPhoneImageView.setEnabled(false);
        phoneNumbersBlockedTitleTextView
                .setText(getString(R.string.phone_numbers_blocked_title_default));

    }

    /**
     * On Phone Number Deleted
     */
    @Override
    public void onPhoneNumberDeleted() {

        final int itemCount = recyclerView.getAdapter().getItemCount();

        if(itemCount > 0)
            phoneNumbersBlockedTitleTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.phone_numbers_blocked_title_count),
                    recyclerView.getAdapter().getItemCount()));
        else {
            deleteAllPhoneNumbersPhoneImageView.setVisibility(View.GONE);
            deleteAllPhoneNumbersPhoneImageView.setEnabled(false);
            phoneNumbersBlockedTitleTextView.setText(getString(R.string.phone_numbers_blocked_title_default));
            onShowNotFoundState();
        }
    }

    /**
     * On Phone Number Added
     * @param phoneNumberBlockedEntity
     */
    @Override
    public void onPhoneNumberAdded(PhoneNumberBlockedEntity phoneNumberBlockedEntity) {
        Preconditions.checkNotNull(phoneNumberBlockedEntity, "Phone number blocked entity");
        onShowDataFoundedState();
        recyclerViewAdapter.getData().add(phoneNumberBlockedEntity);
        recyclerViewAdapter.notifyDataSetChanged();
        showNoticeDialog(R.string.phone_number_blocked_successfully);
    }
}
