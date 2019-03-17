package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminals;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.fernandocejas.arrow.checks.Preconditions;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.TerminalsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;

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
     * Delete All Image View
     */
    @BindView(R.id.deleteAll)
    protected ImageView deleteAllImageView;

    /**
     * Lock Screen Switch Compat
     */
    @BindView(R.id.lockScreenSwitch)
    protected SupportSwitchCompat lockScreenSwitchCompat;


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

        lockScreenSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!activityHandler.isConnectivityAvailable()) {
                    showNoticeDialog(R.string.connectivity_not_available, false);
                    lockScreenSwitchCompat.setChecked(!isChecked, false);
                } else {

                    if(isChecked) {

                        showConfirmationDialog(R.string.lock_screen_of_all_linked_devices_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchLockScreenStatus(kidIdentity, true);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                lockScreenSwitchCompat.setChecked(false, false);
                            }
                        });

                    } else {

                        showConfirmationDialog(R.string.unlock_screen_of_all_linked_devices_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchLockScreenStatus(kidIdentity, false);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                lockScreenSwitchCompat.setChecked(true, false);
                            }
                        });
                    }
                }
            }
        });

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
        Preconditions.checkNotNull(terminalEntity, "Terminal Entity can not be null");
        Preconditions.checkState(!terminalEntity.getIdentity().isEmpty(), "Terminal Identity can not be empty");
        activityHandler.navigateToTerminalDetail(kidIdentity, terminalEntity.getIdentity());
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

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<TerminalEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);
        lockScreenSwitchCompat.setVisibility(View.VISIBLE);
        deleteAllImageView.setVisibility(View.VISIBLE);
        activityHandler.configureTerminalList(dataLoaded);
    }

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        super.onNoDataFound();
        lockScreenSwitchCompat.setVisibility(View.GONE);
        deleteAllImageView.setVisibility(View.GONE);
        activityHandler.showTerminalNotFoundDialog();
    }

    /**
     * On Delete All Clicked
     */
    @OnClick(R.id.deleteAll)
    protected void onDeleteAllClicked(){
        if(!activityHandler.isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            showConfirmationDialog(R.string.delete_all_kid_terminals, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    getPresenter().deleteAll(kidIdentity);
                }

                @Override
                public void onRejected(DialogFragment dialog) {

                }
            });
        }

    }

    /**
     * On Lock Screen Status Changed SuccessFully
     */
    @Override
    public void onLockScreenStatusChangedSuccessfully() {

        showNoticeDialog(R.string.terminal_lock_screen_changed_successfully, true);

        ((TerminalsAdapter)recyclerViewAdapter)
                .changeScreenStatus(!lockScreenSwitchCompat.isChecked());
    }

    /**
     * On Lock Screen Status Changed Failed
     */
    @Override
    public void onLockScreenStatusChangedFailed() {

        lockScreenSwitchCompat.setChecked(
                !lockScreenSwitchCompat.isChecked(), false);

        showNoticeDialog(R.string.terminal_lock_screen_changed_failed, false);
    }
}

