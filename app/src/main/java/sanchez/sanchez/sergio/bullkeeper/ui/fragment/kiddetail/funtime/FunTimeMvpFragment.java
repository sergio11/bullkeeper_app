package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.funtime;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.FunTimeDayScheduledAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;

/**
 * Fun Time Fragment
 */
public class FunTimeMvpFragment extends SupportMvpLCEFragment<FunTimeFragmentPresenter,
        IFunTimeFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, DayScheduledEntity>
        implements IFunTimeFragmentView, FunTimeDayScheduledAdapter.onFunTimeDayScheduledChangedListener {

    /**
     * Kid Arg
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
     * State
     * ===================
     */

    /**
     * Kid
     */
    @State
    protected String kid;

    /**
     * Day Scheduled Entities
     */
    @State
    protected ArrayList<DayScheduledEntity> dayScheduledEntities;


    /**
     *
     * Views
     * =================
     *
     */

    /**
     * Fun Time Enable Switch
     */
    @BindView(R.id.funTimeEnableSwitch)
    protected SupportSwitchCompat funTimeEnableSwitch;

    /**
     * Fun Time Description View Group
     */
    @BindView(R.id.funTimeDescription)
    protected ViewGroup funTimeDescriptionViewGroup;

    /**
     * Fun Time Actions View Group
     */
    @BindView(R.id.funTimeActions)
    protected ViewGroup funTimeActionsViewGroup;


    /**
     *
     */
    public FunTimeMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static FunTimeMvpFragment newInstance(final String kidIdentity) {
        FunTimeMvpFragment fragment = new FunTimeMvpFragment();
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

        kid = getArguments().getString(KID_IDENTITY_ARG);

        // Enable Nested Scrolling on Recycler View
        ViewCompat.setNestedScrollingEnabled(recyclerView, true);

        // Fun Time Enable Switch Status
        funTimeEnableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

                if(isChecked) {

                    showConfirmationDialog(R.string.confirm_enable_fun_time_mode, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                        @Override
                        public void onAccepted(DialogFragment dialog) {
                            // Save Fun Time
                            showNoticeDialog(R.string.fun_time_mode_enabled);
                            ((FunTimeDayScheduledAdapter)recyclerViewAdapter)
                                    .switchEditableStatusForAllDaysOfWeek(true);
                        }

                        @Override
                        public void onRejected(DialogFragment dialog) {
                            funTimeEnableSwitch.setChecked(false, false);
                        }
                    });

                } else {

                    showConfirmationDialog(R.string.confirm_disable_fun_time_mode, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                        @Override
                        public void onAccepted(DialogFragment dialog) {
                            // Save Fun Time
                            showNoticeDialog(R.string.fun_time_mode_disabled);
                            ((FunTimeDayScheduledAdapter) recyclerViewAdapter)
                                    .switchEditableStatusForAllDaysOfWeek(false);
                        }

                        @Override
                        public void onRejected(DialogFragment dialog) {
                            funTimeEnableSwitch.setChecked(true, false);
                        }
                    });

                }

            }
        });

    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<DayScheduledEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);
        this.dayScheduledEntities = new ArrayList<>(dataLoaded);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<DayScheduledEntity> getAdapter() {
        final FunTimeDayScheduledAdapter funTimeDayScheduledAdapter =
                new FunTimeDayScheduledAdapter(activity, new ArrayList<DayScheduledEntity>());
        funTimeDayScheduledAdapter.setOnSupportRecyclerViewListener(this);
        funTimeDayScheduledAdapter.setListener(this);
        return funTimeDayScheduledAdapter;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(FunTimeFragmentPresenter.KID_IDENTITY_ARG, kid);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_fun_time;
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
     * @param item
     */
    @Override
    public void onItemClick(final DayScheduledEntity item) {
        Preconditions.checkNotNull(item, "Item can not be null");
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
    public FunTimeFragmentPresenter providePresenter() {
        return component.funTimeFragmentPresenter();
    }

    /**
     * Set Fun Time Status
     * @param isEnabled
     */
    @Override
    public void setFunTimeStatus(boolean isEnabled) {
        funTimeEnableSwitch.setChecked(isEnabled, false);
    }

    /**
     * On Day Scheduled Changed
     * @param dayScheduledEntity
     */
    @Override
    public void onDayScheduledChanged(final DayScheduledEntity dayScheduledEntity) {
        Preconditions.checkNotNull(dayScheduledEntity, "Day Scheduled Entity can not be null");
        funTimeDescriptionViewGroup.setVisibility(View.GONE);
        funTimeActionsViewGroup.setVisibility(View.VISIBLE);
    }
}

