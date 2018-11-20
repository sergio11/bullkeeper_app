package sanchez.sanchez.sergio.bullkeeper.ui.fragment.terminaldetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
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
import sanchez.sanchez.sergio.bullkeeper.di.components.TerminalComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.terminaldetail.ITerminalDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.TOOLBAR_WITH_MENU;

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
    public static String CHILD_ID_ARG = "CHILD_ID_ARG";

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
     * Lat Time Used Text View
     */
    @BindView(R.id.lastTimeUsed)
    protected TextView lastTimeUsedTextView;

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
     * @param alertId
     */
    public static TerminalDetailActivityMvpFragment newInstance(final String alertId, final String sonId) {
        final TerminalDetailActivityMvpFragment alertDetailActivityFragment =
                new TerminalDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(TERMINAL_ID_ARG, alertId);
        args.putString(CHILD_ID_ARG, sonId);
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
        return TOOLBAR_WITH_MENU;
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


        final String totalAppsInstalledText = terminalDetailEntity.getTotalApps() > 0 ?
                String.format(Locale.getDefault(),
                        getString(R.string.has_registered_applications),
                        terminalDetailEntity.getTotalApps()) : getString(R.string.has_not_registered_applications);

        totalAppsInstalledTextView.setText(totalAppsInstalledText);

        // Last Time Used
        lastTimeUsedTextView.setText(terminalDetailEntity.getLastTimeUsed());

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
