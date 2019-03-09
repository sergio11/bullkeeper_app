package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.funtime;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
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
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.FunTimeDayScheduledAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;
import sanchez.sanchez.sergio.domain.models.FunTimeScheduledEntity;
import timber.log.Timber;

/**
 * Fun Time Fragment
 */
public class FunTimeMvpFragment extends SupportMvpLCEFragment<FunTimeFragmentPresenter,
        IFunTimeFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, DayScheduledEntity>
        implements IFunTimeFragmentView, FunTimeDayScheduledAdapter.onFunTimeDayScheduledChangedListener,
        AdapterView.OnItemSelectedListener {

    /**
     * Kid Arg
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
     * State
     * ===================
     */

    /**
     * Kid
     */
    @State
    protected String kid;

    /**
     * Is Fun Time Enabled
     */
    @State
    protected boolean isFunTimeEnabled;

    /**
     * Day Scheduled Entities
     */
    @State
    protected ArrayList<DayScheduledEntity> dayScheduledEntities;

    /**
     * Current Terminal Pos
     */
    @State
    protected int currentTerminalPos = 0;

    /**
     * Terminals List
     */
    @State
    protected ArrayList<TerminalItem> terminalItems = new ArrayList<>();

    /**
     * Terminal
     */
    @State
    protected TerminalItem currentTerminalItem;

    /**
     * Terminal Identity
     *
     */
    @State
    protected String terminalIdentity;

    /**
     * Day Scheduled Entities Modified
     */
    protected ArrayList<Object> dayScheduledEntitiesModified = new ArrayList<>();


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
     * Terminals Spinner
     */
    @BindView(R.id.terminalsSpinner)
    protected AppCompatSpinner terminalsSpinner;

    /**
     *
     */
    public FunTimeMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kid
     * @return
     */
    public static FunTimeMvpFragment newInstance(final String kid, final ArrayList<TerminalItem> terminalItems) {
        FunTimeMvpFragment fragment = new FunTimeMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kid);
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

        kid = getArguments().getString(KID_IDENTITY_ARG);

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

        // Fun Time Enable Switch Status
        funTimeEnableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

                if(isChecked) {

                    showConfirmationDialog(R.string.confirm_enable_fun_time_mode, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                        @Override
                        public void onAccepted(DialogFragment dialog) {
                            isFunTimeEnabled = true;
                            // Save Fun Time
                            saveFunTime();
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
                            isFunTimeEnabled = false;
                            // Save Fun Time
                            saveFunTime();
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
        final FunTimeDayScheduledAdapter funTimeDayScheduledAdapter = new FunTimeDayScheduledAdapter(activity, new ArrayList<DayScheduledEntity>());
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
        args.putSerializable(FunTimeFragmentPresenter.CURRENT_TERMINAL_ARG, currentTerminalItem);
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
        activityHandler.navigateToDayScheduledDetail(kid, terminalIdentity, item.getDay(),
                isFunTimeEnabled);
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
        isFunTimeEnabled = isEnabled;
        funTimeEnableSwitch.setChecked(isEnabled, false);
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
        // Reset Current Changes
        resetModifications();
        // Load Data for new terminal selected
        loadData();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {}


    /**
     * On Discard Changes CLicked
     */
    @OnClick(R.id.discardChanges)
    protected void onDiscardChangesClicked(){
        for(final Object change: dayScheduledEntitiesModified) {
            if(change instanceof DayScheduledStatusChanged) {
                final DayScheduledStatusChanged dayScheduledStatusChanged =
                        (DayScheduledStatusChanged)change;

                for(final DayScheduledEntity dayScheduledEntity: dayScheduledEntities) {
                    if(dayScheduledEntity.getDay()
                            .equals(dayScheduledStatusChanged.getDay())) {
                        dayScheduledEntity
                                .setEnabled(dayScheduledStatusChanged.oldEnabledValue);
                        break;
                    }
                }

            } else if(change instanceof  DayScheduledTotalHoursChanged) {
                final DayScheduledTotalHoursChanged dayScheduledTotalHoursChanged =
                        (DayScheduledTotalHoursChanged)change;

                for(final DayScheduledEntity dayScheduledEntity: dayScheduledEntities) {
                    if(dayScheduledEntity.getDay()
                            .equals(dayScheduledTotalHoursChanged.getDay())) {
                        dayScheduledEntity
                                .setTotalHours(dayScheduledTotalHoursChanged.oldTotalHoursValue);
                        break;
                    }
                }

            }
        }

        // Reset Modifications
        resetModifications();

        recyclerViewAdapter.notifyDataSetChanged();

    }

    /**
     * Save Fun Time
     */
    private void saveFunTime(){

        final FunTimeScheduledEntity funTimeScheduledEntity =
                new FunTimeScheduledEntity();
        funTimeScheduledEntity.setEnabled(isFunTimeEnabled);
        funTimeScheduledEntity.setMonday(dayScheduledEntities.get(0));
        funTimeScheduledEntity.setTuesday(dayScheduledEntities.get(1));
        funTimeScheduledEntity.setWednesday(dayScheduledEntities.get(2));
        funTimeScheduledEntity.setThursday(dayScheduledEntities.get(3));
        funTimeScheduledEntity.setFriday(dayScheduledEntities.get(4));
        funTimeScheduledEntity.setSaturday(dayScheduledEntities.get(5));
        funTimeScheduledEntity.setSunday(dayScheduledEntities.get(6));

        // Reset Changes
        resetModifications();
        // Save Fun Time
        getPresenter().saveFunTime(kid, terminalIdentity, funTimeScheduledEntity);


    }


    /**
     * On Saved Changes Clicked
     */
    @OnClick(R.id.saveChanges)
    protected void onSaveChangesClicked(){
        if(!activityHandler.isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            saveFunTime();
        }
    }

    /**
     * Update Header Status
     */
    private void updateHeaderStatus() {
        if(dayScheduledEntitiesModified.isEmpty()) {
            funTimeActionsViewGroup.setVisibility(View.GONE);
            funTimeDescriptionViewGroup.setVisibility(View.VISIBLE);
        } else {
            funTimeActionsViewGroup.setVisibility(View.VISIBLE);
            funTimeDescriptionViewGroup.setVisibility(View.GONE);
        }
    }

    /**
     * Reset Modifications
     */
    private void resetModifications(){
        dayScheduledEntitiesModified = new ArrayList<>();
        updateHeaderStatus();
    }

    /**
     * On Day Scheduled Status Changed
     * @param day
     * @param newEnabledValue
     * @param oldEnabledValue
     */
    @Override
    public void onDayScheduledStatusChanged(final String day, final boolean newEnabledValue,
                                            final boolean oldEnabledValue) {

        if(!dayScheduledEntitiesModified.isEmpty()) {
            final Iterator ite = dayScheduledEntitiesModified.iterator();
            boolean isFound = false;
            while(ite.hasNext()) {
                final Object change = ite.next();
                if(change instanceof DayScheduledStatusChanged) {
                    final DayScheduledStatusChanged dayScheduledChanged =
                            (DayScheduledStatusChanged) change;
                    if(dayScheduledChanged.getDay().equals(day)) {
                        isFound = true;
                        ite.remove();
                        break;
                    }
                }
            }
            if(!isFound) {
                final DayScheduledStatusChanged dayScheduledStatusChanged = new DayScheduledStatusChanged();
                dayScheduledStatusChanged.setDay(day);
                dayScheduledStatusChanged.setNewEnabledValue(newEnabledValue);
                dayScheduledStatusChanged.setOldEnabledValue(oldEnabledValue);
                dayScheduledEntitiesModified.add(dayScheduledStatusChanged);
            }
        } else {
            final DayScheduledStatusChanged dayScheduledStatusChanged = new DayScheduledStatusChanged();
            dayScheduledStatusChanged.setDay(day);
            dayScheduledStatusChanged.setNewEnabledValue(newEnabledValue);
            dayScheduledStatusChanged.setOldEnabledValue(oldEnabledValue);
            dayScheduledEntitiesModified.add(dayScheduledStatusChanged);
        }

        updateHeaderStatus();

    }

    /**
     * On Day Scheduled Total Hours Changed
     * @param day
     * @param newTotalHoursValue
     * @param oldTotalHoursValue
     */
    @Override
    public void onDayScheduledTotalHoursChanged(final String day,
                                                final int newTotalHoursValue,
                                                final int oldTotalHoursValue) {

        if(newTotalHoursValue != oldTotalHoursValue) {

            if(!dayScheduledEntitiesModified.isEmpty()) {
                final Iterator ite = dayScheduledEntitiesModified.iterator();
                boolean isFound = false;
                while(ite.hasNext()) {
                    final Object change = ite.next();
                    if(change instanceof DayScheduledTotalHoursChanged) {
                        final DayScheduledTotalHoursChanged dayScheduledChanged =
                                (DayScheduledTotalHoursChanged) change;
                        if(dayScheduledChanged.getDay().equals(day)) {
                            isFound = true;
                            if(dayScheduledChanged.getOldTotalHoursValue() == newTotalHoursValue
                                    && dayScheduledChanged.getNewTotalHoursValue() == oldTotalHoursValue)
                                ite.remove();
                            break;
                        }
                    }
                }

                if(!isFound) {
                    final DayScheduledTotalHoursChanged dayScheduledTotalHoursChanged = new DayScheduledTotalHoursChanged();
                    dayScheduledTotalHoursChanged.setDay(day);
                    dayScheduledTotalHoursChanged.setNewTotalHoursValue(newTotalHoursValue);
                    dayScheduledTotalHoursChanged.setOldTotalHoursValue(oldTotalHoursValue);
                    dayScheduledEntitiesModified.add(dayScheduledTotalHoursChanged);
                }
            } else {
                final DayScheduledTotalHoursChanged dayScheduledTotalHoursChanged = new DayScheduledTotalHoursChanged();
                dayScheduledTotalHoursChanged.setDay(day);
                dayScheduledTotalHoursChanged.setNewTotalHoursValue(newTotalHoursValue);
                dayScheduledTotalHoursChanged.setOldTotalHoursValue(oldTotalHoursValue);
                dayScheduledEntitiesModified.add(dayScheduledTotalHoursChanged);
            }

            updateHeaderStatus();
        }


    }


    /**
     * Day Scheduled Total Hours
     */
    public static class DayScheduledTotalHoursChanged {

        /**
         * Day
         */
        private String day;

        /**
         * New Total Hours Value
         */
        private int newTotalHoursValue;

        /**
         * Old Total Hours Value
         */
        private int oldTotalHoursValue;

        public DayScheduledTotalHoursChanged(){}

        public DayScheduledTotalHoursChanged(String day, int newTotalHoursValue, int oldTotalHoursValue) {
            this.day = day;
            this.newTotalHoursValue = newTotalHoursValue;
            this.oldTotalHoursValue = oldTotalHoursValue;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getNewTotalHoursValue() {
            return newTotalHoursValue;
        }

        public void setNewTotalHoursValue(int newTotalHoursValue) {
            this.newTotalHoursValue = newTotalHoursValue;
        }

        public int getOldTotalHoursValue() {
            return oldTotalHoursValue;
        }

        public void setOldTotalHoursValue(int oldTotalHoursValue) {
            this.oldTotalHoursValue = oldTotalHoursValue;
        }
    }

    /**
     * Day Scheduled Status Changed
     */
    public static class DayScheduledStatusChanged {

        /**
         * Day
         */
        private String day;

        /**
         * New Enabled Value
         */
        private boolean newEnabledValue;

        /**
         * Old Enabled Value
         */
        private boolean oldEnabledValue;

        public DayScheduledStatusChanged(){}

        /**
         *
         * @param day
         * @param newEnabledValue
         * @param oldEnabledValue
         */
        public DayScheduledStatusChanged(String day, boolean newEnabledValue, boolean oldEnabledValue) {
            this.day = day;
            this.newEnabledValue = newEnabledValue;
            this.oldEnabledValue = oldEnabledValue;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public boolean isNewEnabledValue() {
            return newEnabledValue;
        }

        public void setNewEnabledValue(boolean newEnabledValue) {
            this.newEnabledValue = newEnabledValue;
        }

        public boolean isOldEnabledValue() {
            return oldEnabledValue;
        }

        public void setOldEnabledValue(boolean oldEnabledValue) {
            this.oldEnabledValue = oldEnabledValue;
        }
    }
}

