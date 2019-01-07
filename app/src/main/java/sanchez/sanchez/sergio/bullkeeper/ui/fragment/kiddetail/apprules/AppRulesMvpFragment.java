package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.apprules;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpSearchLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IAppEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.events.impl.AppUninstalledEvent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.NewAppInstalledEvent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.AppRulesAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.models.TerminalItem;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;
import timber.log.Timber;

/**
 * App Rules List Fragment
 */
public class AppRulesMvpFragment extends SupportMvpSearchLCEFragment<AppRulesFragmentPresenter,
        IAppRulesFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, AppInstalledEntity>
        implements IAppRulesFragmentView, AppRulesAdapter.OnAppRulesListener, AdapterView.OnItemSelectedListener {

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
     * Local System Notification
     */
    @Inject
    protected ILocalSystemNotification localSystemNotification;


    /**
     * Views
     * ===================
     */

    @BindView(R.id.appRulesActions)
    protected ViewGroup appRulesActions;

    /**
     * Save Changes Btn
     */
    @BindView(R.id.saveChanges)
    protected Button saveChangesBtn;

    /**
     * Discard Changes
     */
    @BindView(R.id.discardChanges)
    protected Button discardChangesBtn;


    /**
     * App Rules Description
     */
    @BindView(R.id.appRulesDescription)
    protected ViewGroup appRulesDescriptionView;

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
     * App Rules Changes
     */
    @State
    protected HashSet<AppRuleChange> appRulesChanges = new HashSet<>();

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
     * New App Installed Event Register Key
     */
    @State
    protected int newAppInstalledEventRegisterKey;

    /**
     * App Uninstalled Event Register Key
     */
    @State
    protected int appUninstalledEventRegisterKey;


    /**
     * App Event Visitor
     */
    private IAppEventVisitor appEventVisitor = new IAppEventVisitor() {

        /**
         *
         * @param newAppInstalledEvent
         */
        @Override
        public void visit(final NewAppInstalledEvent newAppInstalledEvent) {
            Preconditions.checkNotNull(newAppInstalledEvent, "App Installed Event can not be null");

            if(appUtils.isValidString(newAppInstalledEvent.getTerminal()) &&
                    newAppInstalledEvent.getTerminal().equals(terminalIdentity)) {

                final AppInstalledEntity appInstalledEntity = new AppInstalledEntity();
                appInstalledEntity.setIdentity(newAppInstalledEvent.getIdentity());
                appInstalledEntity.setDisabled(newAppInstalledEvent.getDisabled());
                appInstalledEntity.setIconEncodedString(newAppInstalledEvent.getIconEncodedString());
                appInstalledEntity.setVersionName(newAppInstalledEvent.getVersionName());
                appInstalledEntity.setVersionCode(newAppInstalledEvent.getVersionCode());
                appInstalledEntity.setLastUpdateTime(newAppInstalledEvent.getLastUpdateTime());
                appInstalledEntity.setFirstInstallTime(newAppInstalledEvent.getFirstInstallTime());
                appInstalledEntity.setPackageName(newAppInstalledEvent.getPackageName());
                appInstalledEntity.setAppName(newAppInstalledEvent.getAppName());
                try {
                    appInstalledEntity.setAppRuleEnum(AppRuleEnum.valueOf(newAppInstalledEvent.getAppRule()));
                }catch(final Exception ex) {
                    appInstalledEntity.setAppRuleEnum(AppRuleEnum.PER_SCHEDULER);
                }

                recyclerViewAdapter.getData().add(appInstalledEntity);
                recyclerViewAdapter.notifyDataSetChanged();

                showNoticeDialog(String.format(Locale.getDefault(),
                        getString(R.string.new_app_installed_event_dialog),
                        appInstalledEntity.getAppName()));
            }

        }

        /**
         *
         * @param appUninstalledEvent
         */
        @Override
        public void visit(AppUninstalledEvent appUninstalledEvent) {
            Preconditions.checkNotNull(appUninstalledEvent, "App Uninstalled Event can not be null");

            if(appUtils.isValidString(appUninstalledEvent.getTerminal()) &&
                    appUninstalledEvent.getTerminal().equals(terminalIdentity)) {

                if (appUtils.isValidString(appUninstalledEvent.getIdentity())) {

                    for (int i = 0; i < recyclerViewAdapter.getData().size(); i++) {
                        final AppInstalledEntity appInstalledEntity = recyclerViewAdapter.getData().get(i);

                        if (appInstalledEntity.getIdentity().equals(appUninstalledEvent.getIdentity())) {
                            recyclerViewAdapter.removeItem(i);
                            recyclerViewAdapter.notifyItemRemoved(i);
                            showNoticeDialog(String.format(Locale.getDefault(),
                                    getString(R.string.app_uninstalled_event_dialog),
                                    appInstalledEntity.getAppName()));
                            break;
                        }

                    }


                }
            }

        }
    };

    /**
     *
     */
    public AppRulesMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static AppRulesMvpFragment newInstance(final String kidIdentity, final ArrayList<TerminalItem> terminalItems) {
        AppRulesMvpFragment fragment = new AppRulesMvpFragment();
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


        ArrayAdapter<TerminalItem> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                terminalItems);
        terminalsSpinner.setAdapter(adapter);
        terminalsSpinner.setSelection(currentTerminalPos);
        terminalsSpinner.setOnItemSelectedListener(this);

        // Enable Nested Scrolling on Recycler View
        ViewCompat.setNestedScrollingEnabled(recyclerView, true);
    }

    /**
     * On Start
     */
    @Override
    public void onStart() {
        super.onStart();

        newAppInstalledEventRegisterKey = localSystemNotification
                .registerEventListener(NewAppInstalledEvent.class, appEventVisitor);

        appUninstalledEventRegisterKey = localSystemNotification
                .registerEventListener(AppUninstalledEvent.class, appEventVisitor);
    }

    /**
     * On Stop
     */
    @Override
    public void onStop() {
        super.onStop();
        localSystemNotification.unregisterEventListener(newAppInstalledEventRegisterKey);
        localSystemNotification.unregisterEventListener(appUninstalledEventRegisterKey);
    }

    /**
     * Get Search Hint Res
     * @return
     */
    @Override
    protected int getSearchHintRes() {
        return R.string.search_app_rules;
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<AppInstalledEntity> getAdapter() {
        final AppRulesAdapter appRulesAdapter =
                new AppRulesAdapter(activity, new ArrayList<AppInstalledEntity>(), picasso);
        appRulesAdapter.setOnSupportRecyclerViewListener(this);
        appRulesAdapter.setOnAppRulesListener(this);
        return appRulesAdapter;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(AppRulesFragmentPresenter.KID_IDENTITY_ARG, kidIdentity);
        args.putSerializable(AppRulesFragmentPresenter.TERMINALS_ARG, terminalItems);
        args.putInt(AppRulesFragmentPresenter.CURRENT_TERMINAL_POS_ARG, currentTerminalPos);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_app_rules;
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
     * @param appInstalled
     */
    @Override
    public void onItemClick(AppInstalledEntity appInstalled) {
        Preconditions.checkNotNull(appInstalled, "App Installed can not be null");
        Preconditions.checkNotNull(appInstalled.getIdentity(), "App Installed Identity can not be null");
        Preconditions.checkState(!appInstalled.getIdentity().isEmpty(), "App Installed Identity can not be empty");
        activityHandler.navigateToAppInstalledDetail(kidIdentity, terminalIdentity,
                appInstalled.getIdentity());
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
    public AppRulesFragmentPresenter providePresenter() {
        return component.appRulesFragmentPresenter();
    }


    /**
     * Update Header Status
     */
    private void updateHeaderStatus() {
        if(appRulesChanges.isEmpty()) {
            appRulesActions.setVisibility(View.GONE);
            appRulesDescriptionView.setVisibility(View.VISIBLE);
        } else {
            appRulesActions.setVisibility(View.VISIBLE);
            appRulesDescriptionView.setVisibility(View.GONE);
        }
    }


    /**
     * On Discard Changes CLicked
     */
    @OnClick(R.id.discardChanges)
    protected void onDiscardChangesClicked(){

        final AppRulesAdapter appRulesAdapter = (AppRulesAdapter)recyclerViewAdapter;
        final Map<String, AppRuleEnum> initRules = new HashMap<>();
        for(final AppRuleChange appRuleChange: appRulesChanges) {
            initRules.put(appRuleChange.getAppIdentity(), appRuleChange.getOldAppRule());
        }
        appRulesAdapter.applyRules(initRules);
        appRulesChanges.clear();
        updateHeaderStatus();
    }

    /**
     * On Saved Changes Clicked
     */
    @OnClick(R.id.saveChanges)
    protected void onSaveChangesClicked(){

        final Map<String, AppRuleEnum> newRules = new HashMap<>();
        for(final AppRuleChange appRuleChange: appRulesChanges) {
            newRules.put(appRuleChange.getAppIdentity(), appRuleChange.getNewAppRule());
        }

        getPresenter().applyRules(kidIdentity, terminalIdentity, newRules);
        appRulesChanges.clear();
        updateHeaderStatus();


    }

    /**
     * On App Rule Changed
     * @param appInstalledIdentity
     * @param oldAppRule
     * @param newAppRule
     */
    @Override
    public void onAppRuleChanged(final String appInstalledIdentity, final AppRuleEnum oldAppRule,
                                 final AppRuleEnum newAppRule) {


        final AppRuleChange appRuleChange = new AppRuleChange(appInstalledIdentity, oldAppRule , newAppRule);

        if(!appRulesChanges.contains(appRuleChange)) {
            appRulesChanges.add(appRuleChange);
        } else {
            final Iterator<AppRuleChange> ite = appRulesChanges.iterator();
            while(ite.hasNext()) {
                final AppRuleChange currentAppRuleChange = ite.next();
                if(currentAppRuleChange.equals(appRuleChange)) {
                    if(currentAppRuleChange.getOldAppRule().equals(appRuleChange.getNewAppRule())) {
                        ite.remove();
                    } else {
                        currentAppRuleChange.setNewAppRule(appRuleChange.getNewAppRule());
                    }
                }
            }
        }

        updateHeaderStatus();
    }


    /**
     * On Show App Rules Dialog
     */
    @OnClick(R.id.showAppRulesInfo)
    public void onShowAppRulesDialog(){
        activityHandler.showAppRulesDialog();
    }

    /**
     * On App Rules Update Successfully
     */
    @Override
    public void onAppRulesUpdatedSuccessfully() {
        showNoticeDialog(R.string.app_rules_applied_successfully);
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
        terminalIdentity = terminalItems.get(currentTerminalPos).getIdentity();
        loadData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    /**
     * On Refresh App Data Clicked
     */
    @OnClick(R.id.refreshAppData)
    protected void onRefreshAppDataClicked() {
        getPresenter().loadData();
    }

    /**
     * App Rule Change
     */
    private class AppRuleChange implements Serializable {

        private String appIdentity;
        private AppRuleEnum oldAppRule;
        private AppRuleEnum newAppRule;

        public AppRuleChange(final String appIdentity, final AppRuleEnum oldAppRule,
                             final AppRuleEnum newAppRule) {
            this.appIdentity = appIdentity;
            this.oldAppRule = oldAppRule;
            this.newAppRule = newAppRule;
        }

        public String getAppIdentity() {
            return appIdentity;
        }

        public void setAppIdentity(String appIdentity) {
            this.appIdentity = appIdentity;
        }

        public AppRuleEnum getOldAppRule() {
            return oldAppRule;
        }

        public void setOldAppRule(AppRuleEnum oldAppRule) {
            this.oldAppRule = oldAppRule;
        }

        public AppRuleEnum getNewAppRule() {
            return newAppRule;
        }

        public void setNewAppRule(AppRuleEnum newAppRule) {
            this.newAppRule = newAppRule;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AppRuleChange that = (AppRuleChange) o;

            return getAppIdentity() != null ? getAppIdentity().equals(that.getAppIdentity()) : that.getAppIdentity() == null;
        }

        @Override
        public int hashCode() {
            return getAppIdentity() != null ? getAppIdentity().hashCode() : 0;
        }
    }


}

