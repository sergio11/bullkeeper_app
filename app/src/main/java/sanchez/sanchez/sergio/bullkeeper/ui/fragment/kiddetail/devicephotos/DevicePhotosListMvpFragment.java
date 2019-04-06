package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.devicephotos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.decoration.SpacesItemDecoration;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.DevicePhotosAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;
import timber.log.Timber;

/**
 * Device Photos List Fragment
 */
public class DevicePhotosListMvpFragment extends SupportMvpLCEFragment<DevicePhotosFragmentPresenter,
        IDevicePhotosListFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, DevicePhotoEntity>
        implements IDevicePhotosListFragmentView, AdapterView.OnItemSelectedListener{

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
     * Disable Camera Switch
     */
    @BindView(R.id.disableCameraSwitch)
    protected SupportSwitchCompat disableCameraSwitch;

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
    public DevicePhotosListMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static DevicePhotosListMvpFragment newInstance(final String kidIdentity, final ArrayList<TerminalItem> terminalItems) {
        DevicePhotosListMvpFragment fragment = new DevicePhotosListMvpFragment();
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

        disableCameraSwitch.setEnabled(true);
        disableCameraSwitch.setChecked(true, false);
        disableCameraSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!activityHandler.isConnectivityAvailable()) {
                    showNoticeDialog(R.string.connectivity_not_available, false);
                    disableCameraSwitch.setChecked(!isChecked, false);
                } else {

                    if (isChecked) {

                        showConfirmationDialog(R.string.terminal_disable_lock_camera_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchLockCameraStatus(kidIdentity, terminalIdentity, false);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                disableCameraSwitch.setChecked(false, false);
                            }
                        });

                    } else {
                        showConfirmationDialog(R.string.terminal_enable_lock_camera_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchLockCameraStatus(kidIdentity, terminalIdentity, true);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                disableCameraSwitch.setChecked(true, false);
                            }
                        });
                    }
                }
            }
        });


    }

    /**
     * On Lock Camera Status Changed
     */
    @Override
    public void onLockCameraStatusChangedSuccessfully() {

        showNoticeDialog(R.string.terminal_lock_camera_changed_successfully);
    }

    /**
     * On Lock Camera Status Changed Failed
     */
    @Override
    public void onLockCameraStatusChangedFailed() {
        disableCameraSwitch.setChecked(!disableCameraSwitch.isChecked(),
                false);
        showNoticeDialog(R.string.terminal_lock_camera_changed_failed, false);
    }

    /**
     * Get Layout Manager
     * @return
     */
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    /**
     * Get Item Decoration
     * @return
     */
    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new SpacesItemDecoration(16);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<DevicePhotoEntity> getAdapter() {
        return new DevicePhotosAdapter(activity, new ArrayList<DevicePhotoEntity>(),
                picasso);
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(DevicePhotosFragmentPresenter.KID_IDENTITY_ARG, kidIdentity);
        args.putSerializable(DevicePhotosFragmentPresenter.CURRENT_TERMINAL_ARG, currentTerminalItem);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_device_photos_list;
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
     *
     * @param devicePhotoEntity
     */
    @Override
    public void onItemClick(final DevicePhotoEntity devicePhotoEntity) {
        Preconditions.checkNotNull(devicePhotoEntity, "Device Photo Entity can not be null");
        Preconditions.checkNotNull(devicePhotoEntity.getIdentity(), "Device Identity can not be null");
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
    public DevicePhotosFragmentPresenter providePresenter() {
        return component.devicePhotosFragmentPresenter();
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

}

