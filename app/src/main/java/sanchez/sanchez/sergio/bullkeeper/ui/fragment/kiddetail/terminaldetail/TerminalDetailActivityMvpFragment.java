package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminaldetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.di.components.TerminalComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.terminaldetail.ITerminalDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import sanchez.sanchez.sergio.domain.models.TerminalStatusEnum;
import timber.log.Timber;

import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Terminal Detail Activity Fragment
 */
public class TerminalDetailActivityMvpFragment extends SupportMvpFragment<TerminalDetailFragmentPresenter,
        ITerminalDetailView, ITerminalDetailActivityHandler, TerminalComponent>
        implements ITerminalDetailView {

    /**
     * Args
     */
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String CHILD_ID_ARG = "KID_ID_ARG";

    /**
     * Views
     * =============
     */

    /**
     * Device Name Text View
     */
    @BindView(R.id.deviceNameTextView)
    protected TextView deviceNameTextView;

    /**
     * App Version Name Text View
     */
    @BindView(R.id.appVersionNameTextView)
    protected TextView appVersionNameTextView;

    /**
     * Battery Status Icon Image View
     */
    @BindView(R.id.batteryStatusIcon)
    protected ImageView batteryStatusIconImageView;

    /**
     * Battery Status Text
     */
    @BindView(R.id.batteryStatusText)
    protected TextView batteryStatusText;

    /**
     * Terminal Status Icon Image View
     */
    @BindView(R.id.terminalStatusIcon)
    protected ImageView terminalStatusIconImageView;

    /**
     * Terminal Status Text View
     */
    @BindView(R.id.terminalStatusText)
    protected TextView terminalStatusTextView;

    /**
     * Os And SDK Version Text View
     */
    @BindView(R.id.osAndSdkVersionTextView)
    protected TextView osAndSdkVersionTextView;

    /**
     * Total Apps Installed TextView
     */
    @BindView(R.id.totalAppsInstalledTextView)
    protected TextView totalAppsInstalledTextView;

    /**
     * Total Calls Text View
     */
    @BindView(R.id.totalCallsTextView)
    protected TextView totalCallsTextView;

    /**
     * Total SMS Text View
     */
    @BindView(R.id.totalSmsTextView)
    protected TextView totalSmsTextView;

    /**
     * Total Contacts Text View
     */
    @BindView(R.id.totalContactsTextView)
    protected TextView totalContactsTextView;


    /**
     * Lat Time Used Text View
     */
    @BindView(R.id.lastTimeUsed)
    protected TextView lastTimeUsedTextView;

    /**
     * Bed Time Text View
     */
    @BindView(R.id.bedTimeTextView)
    protected TextView bedTimeTextView;

    /**
     * Bed Time Status Widget
     */
    @BindView(R.id.bedTimeStatusWidget)
    protected SupportSwitchCompat bedTimeStatusWidget;

    /**
     * Lock Screen Text View
     */
    @BindView(R.id.lockScreenTextView)
    protected TextView lockScreenTextView;

    /**
     * Lock Screen Status Widget
     */
    @BindView(R.id.lockScreenStatusWidget)
    protected SupportSwitchCompat lockScreenStatusWidget;

    /**
     * Lock Camera Text View
     */
    @BindView(R.id.lockCameraTextView)
    protected TextView lockCameraTextView;

    /**
     * Lock Camera Status Widget
     */
    @BindView(R.id.lockCameraStatusWidget)
    protected SupportSwitchCompat lockCameraStatusWidget;


    /**
     * Settings Text View
     */
    @BindView(R.id.settingsTextView)
    protected TextView settingsTextView;

    /**
     * Settings Status Widget
     */
    @BindView(R.id.settingsStatusWidget)
    protected SupportSwitchCompat settingsStatusWidget;


    /**
     * Location Permission Status Widget
     */
    @BindView(R.id.locationPermissionStatusWidget)
    protected SupportSwitchCompat locationPermissionStatusWidget;

    /**
     * Location Permission Text View
     */
    @BindView(R.id.locationPermissionTextView)
    protected TextView locationPermissionTextView;

    /**
     * Call History Permission Status Widget
     */
    @BindView(R.id.callHistoryPermissionStatusWidget)
    protected SupportSwitchCompat callHistoryPermissionStatusWidget;

    /**
     * Call History Permission Text View
     */
    @BindView(R.id.callHistoryPermissionTextView)
    protected TextView callHistoryPermissionTextView;

    /**
     * Contacts List Permission Status Widget
     */
    @BindView(R.id.contactsListPermissionStatusWidget)
    protected SupportSwitchCompat contactsListPermissionStatusWidget;

    /**
     * Contacts List Permission Text View
     */
    @BindView(R.id.contactsListPermissionTextView)
    protected TextView contactsListPermissionTextView;

    /**
     * Text Message Permission Status Widget
     */
    @BindView(R.id.textMessagePermissionStatusWidget)
    protected SupportSwitchCompat textMessagePermissionStatusWidget;

    /**
     * Text Message Permission Text View
     */
    @BindView(R.id.textMessagePermissionTextView)
    protected TextView textMessagePermissionTextView;

    /**
     * Storage Permission Status Widget
     */
    @BindView(R.id.storagePermissionStatusWidget)
    protected SupportSwitchCompat storagePermissionStatusWidget;

    /**
     * Storage Permission Text View
     */
    @BindView(R.id.storagePermissionTextView)
    protected TextView storagePermissionTextView;


    /**
     * Usage Stats Status Widget
     */
    @BindView(R.id.usageStatsStatusWidget)
    protected SupportSwitchCompat usageStatsStatusWidget;

    /**
     * Usage Stats Text View
     */
    @BindView(R.id.usageStatsTextView)
    protected TextView usageStatsTextView;

    /**
     * Admin Access Status Widget
     */
    @BindView(R.id.adminAccessStatusWidget)
    protected SupportSwitchCompat adminAccessStatusWidget;

    /**
     * Admin Access Text View
     */
    @BindView(R.id.adminAccessTextView)
    protected TextView adminAccessTextView;

    /**
     * Dependencies
     * ===============
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
     * State
     * =============
     */


    /**
     * Child ID
     */
    @State
    protected String childId;

    /**
     * Terminal Id
     */
    @State
    protected String terminalId;

    public TerminalDetailActivityMvpFragment() { }

    /**
     * New Instance
     * @param terminal
     */
    public static TerminalDetailActivityMvpFragment newInstance(final String terminal, final String kid) {
        final TerminalDetailActivityMvpFragment alertDetailActivityFragment =
                new TerminalDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(TERMINAL_ID_ARG, terminal);
        args.putString(CHILD_ID_ARG, kid);
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
        if(!getArgs().containsKey(CHILD_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(CHILD_ID_ARG)))
            throw new IllegalArgumentException("You must provide a child id");

        childId = getArgs().getString(CHILD_ID_ARG);

        // Get Terminal Id
        if(!getArgs().containsKey(TERMINAL_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(TERMINAL_ID_ARG)))
            throw new IllegalStateException("You must provide a terminal id");

        terminalId = getArgs().getString(TERMINAL_ID_ARG);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_terminal_detail;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(TerminalComponent component) {
        component.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public TerminalDetailFragmentPresenter providePresenter() {
        return component.terminalDetailFragmentPresenter();
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
     * On Terminal Detail Loaded
     * @param terminalDetailEntity
     */
    @Override
    public void onTerminalDetailLoaded(TerminalDetailEntity terminalDetailEntity) {
        Preconditions.checkNotNull(terminalDetailEntity, "Terminal Detail Entity");

        // Device Name
        deviceNameTextView.setText(String.format(Locale.getDefault(), getString(R.string.device_name_text),
                terminalDetailEntity.getManufacturer(), terminalDetailEntity.getDeviceName()));


        // App Version
        appVersionNameTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.app_version_text), terminalDetailEntity.getAppVersionName(),
                terminalDetailEntity.getAppVersionCode()));

        if(terminalDetailEntity.isBatteryCharging()) {
            batteryStatusIconImageView.setImageResource(R.drawable.battery_is_charging);

        } else {

            if(terminalDetailEntity.getBatteryLevel() <= 100 && terminalDetailEntity.getBatteryLevel() >= 80 ) {
                batteryStatusIconImageView.setImageResource(R.drawable.battery_fully_charged);
            } else if(terminalDetailEntity.getBatteryLevel() < 80 && terminalDetailEntity.getBatteryLevel() >= 30) {
                batteryStatusIconImageView.setImageResource(R.drawable.normal_charge_battery);
            } else {
                batteryStatusIconImageView.setImageResource(R.drawable.battery_about_to_run_out);
            }

        }

        batteryStatusText.setText(String.format(Locale.getDefault(),
                getString(R.string.battery_level_value), terminalDetailEntity.getBatteryLevel()));


        if(terminalDetailEntity.getStatus().equals(TerminalStatusEnum.STATE_OFF)) {
            terminalStatusIconImageView.setImageResource(R.drawable.mobile_turn_off);
            terminalStatusTextView.setText(getString(R.string.device_id_off));
        } else {
            terminalStatusIconImageView.setImageResource(R.drawable.mobile_turn_on);
            terminalStatusTextView.setText(getString(R.string.device_id_on));
        }

        // Os And Sdk Version
        osAndSdkVersionTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.terminal_os_sdk) ,
                terminalDetailEntity.getOsVersion(), terminalDetailEntity.getSdkVersion()));


        // Set Total Apps

        final String totalAppsInstalledText = terminalDetailEntity.getTotalApps() > 0 ?
                String.format(Locale.getDefault(),
                        getString(R.string.has_registered_applications),
                        terminalDetailEntity.getTotalApps()) : getString(R.string.has_not_registered_applications);


        totalAppsInstalledTextView.setText(totalAppsInstalledText);

        // Set Total Calls

        final String totalCallsText = terminalDetailEntity.getTotalCalls() > 0 ?
                String.format(Locale.getDefault(),
                        getString(R.string.has_registered_calls),
                        terminalDetailEntity.getTotalCalls()) : getString(R.string.has_not_registered_calls);

        totalCallsTextView.setText(totalCallsText);

        // Set Total SMS

        final String totalSmsText = terminalDetailEntity.getTotalSms() > 0 ?
                String.format(Locale.getDefault(),
                        getString(R.string.has_registered_sms),
                        terminalDetailEntity.getTotalSms()) : getString(R.string.has_not_registered_sms);

        totalSmsTextView.setText(totalSmsText);

        // Set Total Contacts

        final String totalContactsText = terminalDetailEntity.getTotalContacts() > 0 ?
                String.format(Locale.getDefault(),
                        getString(R.string.has_registered_contacts),
                        terminalDetailEntity.getTotalContacts()) : getString(R.string.has_not_registered_contacts);

        totalContactsTextView.setText(totalContactsText);
        // Last Time Used
        lastTimeUsedTextView.setText(terminalDetailEntity.getLastTimeUsed());

        // Bed Time
        bedTimeTextView.setText(terminalDetailEntity.isBedTimeEnabled() ?
            getString(R.string.terminal_bed_time_enable) :
            getString(R.string.terminal_bed_time_disabled));

        bedTimeStatusWidget.setEnabled(true);
        bedTimeStatusWidget.setChecked(terminalDetailEntity.isBedTimeEnabled(), false);
        bedTimeStatusWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!activityHandler.isConnectivityAvailable()) {
                    showNoticeDialog(R.string.connectivity_not_available, false);
                    bedTimeStatusWidget.setChecked(!isChecked, false);
                } else {

                    if(isChecked) {

                        showConfirmationDialog(R.string.terminal_enable_bed_time_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchBedTimeStatus(childId, terminalId, true);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                bedTimeStatusWidget.setChecked(false, false);
                            }
                        });

                    } else {

                        showConfirmationDialog(R.string.terminal_disable_bed_time_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchBedTimeStatus(childId, terminalId, false);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                bedTimeStatusWidget.setChecked(true, false);
                            }
                        });
                    }
                }
            }
        });

        // Lock Screen
        lockScreenTextView.setText(terminalDetailEntity.isScreenEnabled() ?
                getString(R.string.terminal_lock_screen_enable) :
                getString(R.string.terminal_lock_screen_disabled));

        lockScreenStatusWidget.setEnabled(true);
        lockScreenStatusWidget.setChecked(terminalDetailEntity.isScreenEnabled(), false);
        lockScreenStatusWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!activityHandler.isConnectivityAvailable()) {
                    showNoticeDialog(R.string.connectivity_not_available, false);
                    lockScreenStatusWidget.setChecked(!isChecked, false);
                } else {
                    if(isChecked) {

                        showConfirmationDialog(R.string.terminal_enable_lock_screen_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchLockScreenStatus(childId, terminalId, true);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                lockScreenStatusWidget.setChecked(false, false);
                            }
                        });

                    } else {

                        showConfirmationDialog(R.string.terminal_disable_lock_screen_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchLockScreenStatus(childId, terminalId, false);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                lockScreenStatusWidget.setChecked(true, false);
                            }
                        });
                    }
                }
            }
        });
        // Lock Camera
        lockCameraTextView.setText(terminalDetailEntity.isCameraEnabled() ?
                getString(R.string.terminal_lock_camera_enable) :
                getString(R.string.terminal_lock_camera_disabled));

        lockCameraStatusWidget.setEnabled(true);
        lockCameraStatusWidget.setChecked(terminalDetailEntity.isScreenEnabled(), false);
        lockCameraStatusWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!activityHandler.isConnectivityAvailable()) {
                    showNoticeDialog(R.string.connectivity_not_available, false);
                    lockCameraStatusWidget.setChecked(!isChecked, false);
                } else {

                    if (isChecked) {

                        showConfirmationDialog(R.string.terminal_enable_lock_camera_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchLockCameraStatus(childId, terminalId, true);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                lockCameraStatusWidget.setChecked(false, false);
                            }
                        });

                    } else {
                        showConfirmationDialog(R.string.terminal_disable_lock_camera_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchLockCameraStatus(childId, terminalId, false);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                lockCameraStatusWidget.setChecked(true, false);
                            }
                        });
                    }
                }
            }
        });

        Timber.d("Terminal Detail: terminalDetailEntity.isSettingsEnabled() -> %b",
                terminalDetailEntity.isSettingsEnabled());

        // Settings
        settingsTextView.setText(terminalDetailEntity.isSettingsEnabled() ?
                getString(R.string.terminal_settings_enable) :
                getString(R.string.terminal_settings_disabled));

        settingsStatusWidget.setEnabled(true);
        settingsStatusWidget.setChecked(terminalDetailEntity.isSettingsEnabled(), false);
        settingsStatusWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!activityHandler.isConnectivityAvailable()) {
                    showNoticeDialog(R.string.connectivity_not_available, false);
                    settingsStatusWidget.setChecked(!isChecked, false);
                } else {

                    if(isChecked) {
                        showConfirmationDialog(R.string.terminal_enable_settings_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchSettingsScreenStatus(childId, terminalId, true);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                settingsStatusWidget.setChecked(false, false);
                            }
                        });

                    } else {
                        showConfirmationDialog(R.string.terminal_disable_settings_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                getPresenter().switchSettingsScreenStatus(childId, terminalId, false);
                            }

                            @Override
                            public void onRejected(DialogFragment dialog) {
                                settingsStatusWidget.setChecked(true, false);
                            }
                        });
                    }
                }
            }
        });


        // Location Permission
        locationPermissionStatusWidget.setChecked(terminalDetailEntity.isLocationPermissionEnabled() , false);

        locationPermissionTextView.setText(
                terminalDetailEntity.isLocationPermissionEnabled() ?
                        getString(R.string.terminal_permission_location_enabled) :
                        getString(R.string.terminal_permission_location_disabled)
        );

        // Call History
        callHistoryPermissionStatusWidget.setChecked(terminalDetailEntity.isCallsHistoryPermissionEnabled() , false);

        callHistoryPermissionTextView.setText(
                terminalDetailEntity.isCallsHistoryPermissionEnabled() ?
                        getString(R.string.terminal_permission_call_history_enabled) :
                        getString(R.string.terminal_permission_call_history_disabled)

        );
        // Contacts List
        contactsListPermissionStatusWidget.setChecked(terminalDetailEntity.isContactsListPermissionEnabled() , false);

        contactsListPermissionTextView.setText(
                terminalDetailEntity.isContactsListPermissionEnabled() ?
                        getString(R.string.terminal_permission_contacts_list_enabled) :
                        getString(R.string.terminal_permission_contacts_list_disabled)
        );
        // Text Message Permission
        textMessagePermissionStatusWidget.setChecked(terminalDetailEntity.isTextMessagePermissionEnabled() , false);

        textMessagePermissionTextView.setText(
                terminalDetailEntity.isTextMessagePermissionEnabled() ?
                        getString(R.string.terminal_permission_text_message_enabled) :
                        getString(R.string.terminal_permission_text_message_disabled)
        );
        // Storage Permission
        storagePermissionStatusWidget.setChecked(terminalDetailEntity.isStoragePermissionEnabled(), false);

        storagePermissionTextView.setText(
                terminalDetailEntity.isStoragePermissionEnabled() ?
                        getString(R.string.terminal_permission_storage_enabled) :
                        getString(R.string.terminal_permission_storage_disabled)
        );

        // Usage Stats
        usageStatsStatusWidget.setChecked(terminalDetailEntity.isUsageStatsAllowed(), false);

        usageStatsTextView.setText(
                terminalDetailEntity.isUsageStatsAllowed() ?
                        getString(R.string.terminal_usage_stats_allowed) :
                        getString(R.string.terminal_usage_stats_not_allowed)
        );


        // Admin Access
        adminAccessStatusWidget.setChecked(terminalDetailEntity.isAdminAccessAllowed(), false);

        adminAccessTextView.setText(
                terminalDetailEntity.isAdminAccessAllowed() ?
                        getString(R.string.terminal_admin_access_allowed) :
                        getString(R.string.terminal_admin_access_not_allowed)
        );

    }

    /**
     * On Terminal Success Deleted
     */
    @Override
    public void onTerminalSuccessDeleted() {
        activityHandler.showNoticeDialog(R.string.terminal_success_deleted, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Bed Time Status Changed
     */
    @Override
    public void onBedTimeStatusChangedSuccessfully() {

        if(!bedTimeStatusWidget.isChecked())
            bedTimeTextView.setText(getString(R.string.terminal_bed_time_disabled));
        else
            bedTimeTextView.setText(getString(R.string.terminal_bed_time_enable));

        showNoticeDialog(R.string.terminal_bed_time_changed_successfully);
    }

    /**
     * On Bed Time Status Changed Failed
     */
    @Override
    public void onBedTimeStatusChangedFailed() {

        bedTimeStatusWidget.setChecked(
                !bedTimeStatusWidget.isChecked(), false);

        showNoticeDialog(R.string.terminal_bed_time_changed_failed, false);
    }

    /**
     * On Lock Screen Status Changed
     */
    @Override
    public void onLockScreenStatusChangedSuccessfully() {

        if(!lockScreenStatusWidget.isChecked())
            lockScreenTextView.setText(getString(R.string.terminal_lock_screen_disabled));
        else
            lockScreenTextView.setText(getString(R.string.terminal_lock_screen_enable));

        showNoticeDialog(R.string.terminal_lock_screen_changed_successfully);
    }

    /**
     * On Lock Screen Status Changed Failed
     */
    @Override
    public void onLockScreenStatusChangedFailed() {

        lockScreenStatusWidget.setChecked(
                !lockScreenStatusWidget.isChecked(), false);

        showNoticeDialog(R.string.terminal_lock_screen_changed_failed, false);
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
        lockCameraStatusWidget.setChecked(!lockCameraStatusWidget.isChecked(),
                false);
        showNoticeDialog(R.string.terminal_lock_camera_changed_failed, false);
    }

    /**
     * On Settings Screen Status Change Successfully
     */
    @Override
    public void onSettingsScreenStatusChangedSuccessfully() {
        showNoticeDialog(R.string.terminal_settings_changed_successfully);
    }

    /**
     * On Settings Screen Status Changed Failed
     */
    @Override
    public void onSettingsScreenStatusChangedFailed() {
        settingsStatusWidget.setChecked(!settingsStatusWidget.isChecked(),
                false);
        showNoticeDialog(R.string.terminal_settings_changed_failed, false);
    }

    /**
     * On Delete Terminal
     */
    @OnClick(R.id.deleteTerminal)
    protected void onDeleteTerminal(){

        if(!activityHandler.isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            activityHandler.showConfirmationDialog(R.string.delete_terminal_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    getPresenter().deleteTerminal(childId, terminalId);
                }

                @Override
                public void onRejected(DialogFragment dialog) {}
            });
        }

    }


}
