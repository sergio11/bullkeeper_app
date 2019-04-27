package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.appdetail;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;
import com.sanchez.sergio.utils.AppUtils;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.di.components.AppInstalledComponent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IAppEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.events.impl.AppUninstalledEvent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.NewAppInstalledEvent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.appdetail.IAppDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.AppInstalledDetailEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppModelEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * App Installed Detail Activity Mvp Fragment
 */
public class AppInstalledDetailActivityMvpFragment extends SupportMvpFragment<AppInstalledDetailFragmentPresenter,
        IAppInstalledDetailView, IAppDetailActivityHandler, AppInstalledComponent>
        implements IAppInstalledDetailView, CompoundButton.OnCheckedChangeListener {

    /**
     * Args
     */
    public static String KID_ID_ARG = "KID_ID_ARG";
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String APP_ID_ARG = "APP_ID_ARG";


    /**
     * Dependencies
     * =========================
     */


    /**
     * Local System Notification
     */
    @Inject
    protected ILocalSystemNotification localSystemNotification;

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
     * =============
     */

    /**
     * App Installed Image View
     */
    @BindView(R.id.appInstalledImage)
    protected ImageView appInstalledImageView;

    /**
     * App Installed Name Text View
     */
    @BindView(R.id.appInstalledName)
    protected TextView appInstalledNameTextView;

    /**
     * App Installed Package Name Text View
     */
    @BindView(R.id.appInstalledPackageName)
    protected TextView appInstalledPackageNameTextView;

    /**
     * App First Install Time
     */
    @BindView(R.id.appfirstInstallTime)
    protected TextView appfirstInstallTimeTextView;

    /**
     * App Last Update Time
     */
    @BindView(R.id.appLastUpdateTime)
    protected TextView appLastUpdateTimeTextView;

    /**
     * App Version Code Text View
     */
    @BindView(R.id.appVersionCode)
    protected TextView appVersionCodeTextView;

    /**
     * App Version Name Text View
     */
    @BindView(R.id.appVersionName)
    protected TextView appVersionNameTextView;

    /**
     * App Not Allowed Image View
     */
    @BindView(R.id.appNotAllowed)
    protected ImageView appNotAllowedImageView;

    /**
     * App Per Scheduled Image View
     */
    @BindView(R.id.appPerScheduled)
    protected ImageView appPerScheduledImageView;

    /**
     * App Fun Time Image View
     */
    @BindView(R.id.appFunTime)
    protected ImageView appFunTimeImageView;

    /**
     * App Allowed Image View
     */
    @BindView(R.id.appAllowed)
    protected ImageView appAllowedImageView;

    /**
     * Switch App Status Widget
     */
    @BindView(R.id.switchAppDisabledStatusWidget)
    protected SupportSwitchCompat switchAppStatusWidget;

    /**
     * App More Info
     */
    @BindView(R.id.appMoreInfo)
    protected ViewGroup appMoreInfoContainer;

    /**
     * App Screen Shots
     */
    @BindView(R.id.appScreenShots)
    protected CarouselView appScreenShots;

    /**
     * App Model Title Text View
     */
    @BindView(R.id.appModelTitle)
    protected TextView appModelTitleTextView;

    /**
     * App Model Description
     */
    @BindView(R.id.appModelDescription)
    protected TextView appModelDescriptionTextView;

    /**
     * App Model Short Description
     */
    @BindView(R.id.appModelShortDescription)
    protected TextView appModelShortDescriptionTextView;

    /**
     * App Model Category
     */
    @BindView(R.id.appModelCategory)
    protected TextView appModelCategoryTextView;

    /**
     * App Model Downloads
     */
    @BindView(R.id.appModelDownloads)
    protected TextView appModelDownloadsTextView;


    /**
     * State
     * =============
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
     * App
     */
    @State
    protected String app;

    /**
     * App Rule Enum
     */
    @State
    protected AppRuleEnum appRuleEnum;

    /**
     * Request App Rule Enum
     */
    @State
    protected AppRuleEnum requestAppRuleEnum;

    /**
     * Package Name
     */
    @State
    protected String packageName;

    /**
     * App Uninstalled Event Register Key
     */
    @State
    protected int appUninstalledEventRegisterKey;


    /**
     * Kid Location Event Visitor
     */
    private IAppEventVisitor appEventVisitor = new IAppEventVisitor() {

        /**
         *
         * @param newAppInstalledEvent
         */
        @Override
        public void visit(NewAppInstalledEvent newAppInstalledEvent) { }

        /**
         *
         * @param appUninstalledEvent
         */
        @Override
        public void visit(AppUninstalledEvent appUninstalledEvent) {

            if(appUtils.isValidString(appUninstalledEvent.getIdentity()) &&
                    appUninstalledEvent.getIdentity().equals(app)) {

                showNoticeDialog(R.string.app_uninstalled_event_default_dialog, new NoticeDialogFragment.NoticeDialogListener() {
                    @Override
                    public void onAccepted(DialogFragment dialog) {
                        activityHandler.closeActivity();
                    }
                });
            }

        }
    };


    public AppInstalledDetailActivityMvpFragment() { }

    /**
     * New Instance
     * @param kid
     * @param terminal
     * @param app
     * @return
     */
    public static AppInstalledDetailActivityMvpFragment newInstance(final String kid, final String terminal, final String app) {
        final AppInstalledDetailActivityMvpFragment alertDetailActivityFragment =
                new AppInstalledDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(KID_ID_ARG, kid);
        args.putString(TERMINAL_ID_ARG, terminal);
        args.putString(APP_ID_ARG, app);
        alertDetailActivityFragment.setArguments(args);
        return alertDetailActivityFragment;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        return getArguments();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get Child Id
        if(!getArgs().containsKey(KID_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(KID_ID_ARG)))
            throw new IllegalArgumentException("You must provide a child id");

        kid = getArgs().getString(KID_ID_ARG);

        // Get Terminal Id
        if(!getArgs().containsKey(TERMINAL_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(TERMINAL_ID_ARG)))
            throw new IllegalStateException("You must provide a terminal id");

        terminal = getArgs().getString(TERMINAL_ID_ARG);

        // Get App Id
        if(!getArgs().containsKey(APP_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(APP_ID_ARG)))
            throw new IllegalStateException("You must provide a app id");

        app = getArgs().getString(APP_ID_ARG);

        switchAppStatusWidget.setEnabled(false);
        /**
         * Set On Checked Change Listener
         */
        switchAppStatusWidget.setOnCheckedChangeListener(this);
    }

    /**
     * On Start
     */
    @Override
    public void onStart() {
        super.onStart();
        appUninstalledEventRegisterKey = localSystemNotification
                .registerEventListener(AppUninstalledEvent.class, appEventVisitor);
    }

    /**
     * On Stop
     */
    @Override
    public void onStop() {
        super.onStop();
        localSystemNotification.unregisterEventListener(appUninstalledEventRegisterKey);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_app_installed_detail;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(AppInstalledComponent component) {
        component.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public AppInstalledDetailFragmentPresenter providePresenter() {
        return component.appInstalledDetailFragmentPresenter();
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
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        activityHandler.showNoticeDialog(R.string.network_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        activityHandler.showNoticeDialog(R.string.unexpected_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * Update App Rule
     * @param appRuleEnum
     */
    private void updateAppRule(final AppRuleEnum appRuleEnum) {
        switch (appRuleEnum) {

            // Never Allowed
            case NEVER_ALLOWED:

                appNotAllowedImageView.setImageResource(R.drawable.app_not_allowed_enabled);
                appAllowedImageView.setImageResource(R.drawable.app_allowed_disabled);
                appPerScheduledImageView.setImageResource(R.drawable.app_per_scheduled_disabled);
                appFunTimeImageView.setImageResource(R.drawable.app_fun_time_disabled);

                break;
            // Always Allowed
            case ALWAYS_ALLOWED:

                appNotAllowedImageView.setImageResource(R.drawable.app_not_allowed_disabled);
                appAllowedImageView.setImageResource(R.drawable.app_allowed_enabled);
                appPerScheduledImageView.setImageResource(R.drawable.app_per_scheduled_disabled);
                appFunTimeImageView.setImageResource(R.drawable.app_fun_time_disabled);

                break;
            // Per Scheduler
            case PER_SCHEDULER:

                appNotAllowedImageView.setImageResource(R.drawable.app_not_allowed_disabled);
                appAllowedImageView.setImageResource(R.drawable.app_allowed_disabled);
                appPerScheduledImageView.setImageResource(R.drawable.app_per_scheduled_enabled);
                appFunTimeImageView.setImageResource(R.drawable.app_fun_time_disabled);
                break;

                // Fun Time
            case FUN_TIME:

                appNotAllowedImageView.setImageResource(R.drawable.app_not_allowed_disabled);
                appAllowedImageView.setImageResource(R.drawable.app_allowed_disabled);
                appPerScheduledImageView.setImageResource(R.drawable.app_per_scheduled_disabled);
                appFunTimeImageView.setImageResource(R.drawable.app_fun_time_enabled);
                break;
        }
    }

    /**
     * On App Installed Detail Loaded
     * @param appInstalledEntity
     */
    @Override
    public void onAppInstalledDetailLoaded(final AppInstalledDetailEntity appInstalledEntity) {
        Preconditions.checkNotNull(appInstalledEntity, "App Installed Entity");

        // Set Image
        if(appUtils.isValidString(appInstalledEntity.getIconEncodedString())){
            byte[] decodedString = Base64.decode(appInstalledEntity.getIconEncodedString(),
                    Base64.DEFAULT);
            final Bitmap decodedByte =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            appInstalledImageView.setImageBitmap(decodedByte);
        }

        packageName = appInstalledEntity.getPackageName();

        // Set App Name
        appInstalledNameTextView.setText(appInstalledEntity.getAppName());

        // Set Package Name
        appInstalledPackageNameTextView.setText(appInstalledEntity.getPackageName());

        final SimpleDateFormat sdf = new SimpleDateFormat(getContext().getString(R.string.date_format));

        final Calendar firstInstallTime = Calendar.getInstance();
        firstInstallTime.setTimeInMillis(appInstalledEntity.getFirstInstallTime());

        appfirstInstallTimeTextView.setText(String.format(Locale.getDefault(),
                getContext().getString(R.string.app_first_install_time),
                sdf.format(firstInstallTime.getTime())));

        final Calendar lastUpdateTime = Calendar.getInstance();
        lastUpdateTime.setTimeInMillis(appInstalledEntity.getLastUpdateTime());

        appLastUpdateTimeTextView.setText(String.format(Locale.getDefault(),
                getContext().getString(R.string.app_last_update_time),
                sdf.format(lastUpdateTime.getTime())));

        // Set App Version Code
        appVersionCodeTextView.setText(String.format(Locale.getDefault(),
                getContext().getString(R.string.app_version_code),
                appInstalledEntity.getVersionCode()));

        // Set Version Name
        appVersionNameTextView.setText(String.format(Locale.getDefault(),
                getContext().getString(R.string.app_version_name),
                appInstalledEntity.getVersionName()));


        appRuleEnum = appInstalledEntity.getAppRuleEnum();

        // App Not Allowed Handler
        appNotAllowedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAppRuleEnum = AppRuleEnum.NEVER_ALLOWED;
                getPresenter().updateAppRule(requestAppRuleEnum);
            }
        });

        // App Allowed Handler
        appAllowedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAppRuleEnum = AppRuleEnum.ALWAYS_ALLOWED;
                getPresenter().updateAppRule(requestAppRuleEnum);
            }
        });

        // App Per Scheduled Handler
        appPerScheduledImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAppRuleEnum = AppRuleEnum.PER_SCHEDULER;
                getPresenter().updateAppRule(requestAppRuleEnum);
            }
        });

        appFunTimeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAppRuleEnum = AppRuleEnum.FUN_TIME;
                getPresenter().updateAppRule(requestAppRuleEnum);
            }
        });

        switchAppStatusWidget.setEnabled(true);
        switchAppStatusWidget.setChecked(!appInstalledEntity.getDisabled(),
                false);
        switchAppStatusWidget.setOnCheckedChangeListener(this);
        switchAppRulesStatus(appInstalledEntity.getDisabled());

        if(appInstalledEntity.getModel() != null) {
            appMoreInfoContainer.setVisibility(View.VISIBLE);

            final AppModelEntity appModelEntity = appInstalledEntity.getModel();

            // App Model Title
            appModelTitleTextView.setText(
                    String.format("%s - %s", appModelEntity.getTitle(),
                            appModelEntity.getDeveloper()));

            // App Model Description
            appModelDescriptionTextView.setText(appModelEntity.getDescription());

            // App Model Short Description
            appModelShortDescriptionTextView.setText(appModelEntity.getShortDesc());

            // App Model Category
            appModelCategoryTextView.setText(appModelEntity.getCategory().getName());

            // App Downloads
            appModelDownloadsTextView.setText(appModelEntity.getDownloads());

            // App Model Screen Shots
            if(appModelEntity.getScreenShots() != null &&
                !appModelEntity.getScreenShots().isEmpty()) {

                appScreenShots.setVisibility(View.VISIBLE);
                appScreenShots.setImageListener(new ImageListener() {
                    @Override
                    public void setImageForPosition(int position, ImageView imageView) {
                        picasso.load(appModelEntity.getScreenShots().get(position))
                                .into(imageView);
                    }
                });
                appScreenShots.setPageCount(appModelEntity.getScreenShots().size());

            }



        }
    }

    /**
     * Switch App Rules Status
     * @param disabled
     */
    private void switchAppRulesStatus(final boolean disabled) {
        if (disabled) {
            appNotAllowedImageView.setImageResource(R.drawable.app_not_allowed_disabled);
            appNotAllowedImageView.setEnabled(false);
            appAllowedImageView.setImageResource(R.drawable.app_allowed_disabled);
            appAllowedImageView.setEnabled(false);
            appPerScheduledImageView.setImageResource(R.drawable.app_per_scheduled_disabled);
            appPerScheduledImageView.setEnabled(false);
            appFunTimeImageView.setImageResource(R.drawable.app_fun_time_disabled);
            appFunTimeImageView.setEnabled(false);
        } else {
            appNotAllowedImageView.setEnabled(true);
            appAllowedImageView.setEnabled(true);
            appPerScheduledImageView.setEnabled(true);
            appFunTimeImageView.setEnabled(true);
            updateAppRule(appRuleEnum);
        }
    }

    /**
     * On App Rule Update Successfully
     */
    @Override
    public void onAppRuleUpdateSuccessfully() {
        appRuleEnum = requestAppRuleEnum;
        requestAppRuleEnum = null;
        updateAppRule(appRuleEnum);
        activityHandler.showNoticeDialog(R.string.app_installed_app_rules_updated_successfully);
    }

    /**
     * On Update App Rule Fail
     */
    @Override
    public void onUpdateAppRuleFail() {
        requestAppRuleEnum = null;
        activityHandler.showNoticeDialog(R.string.app_installed_app_rules_updated_fail);
    }

    /**
     * On App Status Changed Successfully
     */
    @Override
    public void onAppStatusChangedSuccessfully() {
        showNoticeDialog(R.string.app_installed_status_changed_successfully);
        final boolean isDisabled = !switchAppStatusWidget.isChecked();
        switchAppRulesStatus(isDisabled);
    }

    /**
     * On App Status Changed Failed
     */
    @Override
    public void onAppStatusChangedFailed() {
        switchAppStatusWidget.setChecked(!switchAppStatusWidget.isChecked(),
                false);
        showNoticeDialog(R.string.app_installed_status_changed_failed, false);
    }

    /**
     * On Checked Changed
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

        if(!activityHandler.isConnectivityAvailable()) {

            showNoticeDialog(R.string.connectivity_not_available, false);
            switchAppStatusWidget.setChecked(!isChecked, false);
        } else {

            if (isChecked) {

                // Enable App
                showConfirmationDialog(R.string.app_installed_enable_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {

                    /**
                     *
                     * @param dialog
                     */
                    @Override
                    public void onAccepted(DialogFragment dialog) {
                        getPresenter().switchAppStatus(kid, terminal, app, true);
                    }

                    @Override
                    public void onRejected(DialogFragment dialog) {
                        switchAppStatusWidget.setChecked(false, false);
                    }
                });



            } else {

                // Disable App
                showConfirmationDialog(R.string.app_installed_disable_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {

                    /**
                     *
                     * @param dialog
                     */
                    @Override
                    public void onAccepted(DialogFragment dialog) {
                        getPresenter().switchAppStatus(kid, terminal, app, false);
                    }

                    @Override
                    public void onRejected(DialogFragment dialog) {
                        switchAppStatusWidget.setChecked(true, false);

                    }
                });

            }

        }
    }

    /**
     * On Show App In Market Clicked
     */
    @OnClick(R.id.showAppInMarket)
    protected void onShowAppInMarketClicked(){
        Preconditions.checkNotNull(packageName, "Package Name can not be null");
        Preconditions.checkState(!packageName.isEmpty(), "Package Name can not be empty");
        AppUtils.goToPlayStore(activity, packageName);
    }

}
