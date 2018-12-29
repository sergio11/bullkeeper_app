package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminaldetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.CompoundButton;
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
        bedTimeStatusWidget.setChecked(!terminalDetailEntity.isBedTimeEnabled(), false);
        bedTimeStatusWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    showConfirmationDialog(R.string.terminal_disable_bed_time_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                        @Override
                        public void onAccepted(DialogFragment dialog) {
                            showNoticeDialog(R.string.terminal_bed_time_disable_successfully);
                            bedTimeTextView.setText(getString(R.string.terminal_bed_time_disabled));
                        }

                        @Override
                        public void onRejected(DialogFragment dialog) {
                            bedTimeStatusWidget.setChecked(false, false);
                        }
                    });

                } else {

                    showConfirmationDialog(R.string.terminal_enable_bed_time_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                        @Override
                        public void onAccepted(DialogFragment dialog) {
                            showNoticeDialog(R.string.terminal_bed_time_enable_successfully);
                            bedTimeTextView.setText(getString(R.string.terminal_bed_time_enable));
                        }

                        @Override
                        public void onRejected(DialogFragment dialog) {
                            bedTimeStatusWidget.setChecked(true, false);
                        }
                    });
                }
            }
        });

        // Lock Screen
        lockScreenTextView.setText(terminalDetailEntity.isLockScreenEnabled() ?
                getString(R.string.terminal_lock_screen_enable) :
                getString(R.string.terminal_lock_screen_disabled));

        lockScreenStatusWidget.setEnabled(true);
        lockScreenStatusWidget.setChecked(!terminalDetailEntity.isLockScreenEnabled(), false);
        lockScreenStatusWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    showConfirmationDialog(R.string.terminal_disable_lock_screen_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                        @Override
                        public void onAccepted(DialogFragment dialog) {
                            showNoticeDialog(R.string.terminal_lock_screen_disabled_successfully);
                            lockScreenTextView.setText(getString(R.string.terminal_lock_screen_disabled));
                        }

                        @Override
                        public void onRejected(DialogFragment dialog) {
                            lockScreenStatusWidget.setChecked(false, false);
                        }
                    });

                } else {
                    showConfirmationDialog(R.string.terminal_enable_lock_screen_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                        @Override
                        public void onAccepted(DialogFragment dialog) {
                            showNoticeDialog(R.string.terminal_lock_screen_enable_successfully);
                            lockScreenTextView.setText(getString(R.string.terminal_lock_screen_enable));
                        }

                        @Override
                        public void onRejected(DialogFragment dialog) {
                            lockScreenStatusWidget.setChecked(true, false);
                        }
                    });
                }
            }
        });
        // Lock Camera
        lockCameraTextView.setText(terminalDetailEntity.isLockCameraEnabled() ?
                getString(R.string.terminal_lock_camera_enable) :
                getString(R.string.terminal_lock_camera_disabled));

        lockCameraStatusWidget.setEnabled(true);
        lockCameraStatusWidget.setChecked(!terminalDetailEntity.isLockScreenEnabled(), false);
        lockCameraStatusWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    showConfirmationDialog(R.string.terminal_disable_lock_camera_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                        @Override
                        public void onAccepted(DialogFragment dialog) {
                            showNoticeDialog(R.string.terminal_lock_camera_disabled_successfully);
                            lockCameraTextView.setText(getString(R.string.terminal_lock_camera_disabled));
                        }

                        @Override
                        public void onRejected(DialogFragment dialog) {
                            lockCameraStatusWidget.setChecked(false, false);
                        }
                    });

                } else {
                    showConfirmationDialog(R.string.terminal_enable_lock_camera_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                        @Override
                        public void onAccepted(DialogFragment dialog) {
                            showNoticeDialog(R.string.terminal_lock_camera_enable_successfully);
                            lockCameraTextView.setText(getString(R.string.terminal_lock_camera_enable));
                        }

                        @Override
                        public void onRejected(DialogFragment dialog) {
                            lockCameraStatusWidget.setChecked(true, false);
                        }
                    });
                }
            }
        });
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
     * On Delete Terminal
     */
    @OnClick(R.id.deleteTerminal)
    protected void onDeleteTerminal(){
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
