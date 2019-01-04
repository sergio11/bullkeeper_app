package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequest;

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
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerKidRequestComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.KidRequestComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.KidRequestAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Kid Request List Activity
 */
public class KidRequestListMvpActivity extends SupportMvpLCEActivity<KidRequestListPresenter,
        IKidRequestListView, KidRequestEntity>
        implements HasComponent<KidRequestComponent>, IKidRequestListActivityHandler
        , IKidRequestListView,
        SupportItemTouchHelper.ItemTouchHelperListener {

    /**
     *
     */
    private final String CONTENT_FULL_NAME = "KID_REQUEST_LIST";
    private final String CONTENT_TYPE_NAME = "KID_REQUEST";

    /**
     * Args
     */
    private final static String KID_ID_ARG = "KID_ID_ARG";

    /**
     * Kid Request Component
     */
    private KidRequestComponent kidRequestComponent;

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
     * Views
     * =============
     */

    /**
     * Delete All Kid Request Image View
     */
    @BindView(R.id.deleteAllKidRequest)
    protected ImageView deleteAllKidRequestImageView;

    /**
     * Kid Request Title Text View
     */
    @BindView(R.id.kidRequestTitle)
    protected TextView kidRequestTitleTextView;

    /**
     * Get Calling Intent
     * @param context
     * @param kid
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid) {
        final Intent intent = new Intent(context, KidRequestListMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        return intent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.kidRequestComponent = DaggerKidRequestComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.kidRequestComponent.inject(this);
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
    public KidRequestListPresenter providePresenter() {
        return kidRequestComponent.kidRequestListPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public KidRequestComponent getComponent() {
        return kidRequestComponent;
    }


    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        super.getArgs();
        final Bundle args = new Bundle();
        args.putString(KidRequestListPresenter.KID_ID_ARG, kid);
        return args;
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * on Item Click
     * @param item
     */
    @Override
    public void onItemClick(final KidRequestEntity item) {
        Preconditions.checkNotNull(item, "Item can not be null");
        Preconditions.checkNotNull(item.getIdentity(), "Identity can not be null");
        navigatorImpl.navigateToKidRequestDetail(this, kid, item.getIdentity());
    }

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
        if (viewHolder instanceof KidRequestAdapter.KidRequestViewHolder) {

            final Integer deletedIndex = viewHolder.getAdapterPosition();
            final KidRequestEntity kidRequestEntity =
                    recyclerViewAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            recyclerViewAdapter.removeItem(deletedIndex);

            showLongSimpleSnackbar(content, getString(R.string.kid_request_removed), getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewAdapter.restoreItem(kidRequestEntity, deletedIndex);
                }
            }, new Snackbar.Callback(){
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    if(event == DISMISS_EVENT_TIMEOUT) {
                        // Delete Kid Request
                        getPresenter().deleteKidRequest(
                                kidRequestEntity.getKid().getIdentity(),
                                kidRequestEntity.getIdentity()
                        );
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
        return R.layout.activity_kid_request_list;
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

        } else {

            throw new IllegalArgumentException("You must provide args");
        }

        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<KidRequestAdapter.KidRequestViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<KidRequestEntity> getAdapter() {
        return new KidRequestAdapter(appContext, new ArrayList<KidRequestEntity>());
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
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<KidRequestEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);
        deleteAllKidRequestImageView.setVisibility(View.VISIBLE);
        deleteAllKidRequestImageView.setEnabled(true);
        kidRequestTitleTextView.setText(
                String.format(Locale.getDefault(),
                        getString(R.string.kid_request_title), dataLoaded.size()));
    }

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        super.onNoDataFound();
        deleteAllKidRequestImageView.setVisibility(View.GONE);
        deleteAllKidRequestImageView.setEnabled(false);
        kidRequestTitleTextView.setText(getString(R.string.kid_request_title_default));
        showNoticeDialog(R.string.no_registered_request, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });

    }

    /**
     * On All Kid Request Deleted
     */
    @Override
    public void onAllKidRequestDeleted() {
        showNoticeDialog(R.string.all_kid_request_eliminated_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });
    }

    /**
     * On Kid Request Deleted
     */
    @Override
    public void onKidRequestDeleted() {
        if(recyclerViewAdapter.getData().isEmpty()) {
            kidRequestTitleTextView.setText(getString(R.string.kid_request_title_default));
            showNoticeDialog(R.string.all_kid_request_eliminated_successfully, new NoticeDialogFragment.NoticeDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    closeActivity();
                }
            });
        } else {
            kidRequestTitleTextView.setText(
                    String.format(Locale.getDefault(),
                            getString(R.string.kid_request_title), recyclerViewAdapter.getData().size()));
        }
    }

    /**
     * On Delete All Kid Request Clicked
     */
    @OnClick(R.id.deleteAllKidRequest)
    protected void onDeleteAllKidRequestClicked(){
        showConfirmationDialog(R.string.delete_all_kid_request_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                getPresenter().deleteAllKidRequestForKid(kid);
            }

            @Override
            public void onRejected(DialogFragment dialog) {

            }
        });
    }
}
