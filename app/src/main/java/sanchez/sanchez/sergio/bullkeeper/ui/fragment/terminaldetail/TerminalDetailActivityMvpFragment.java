package sanchez.sanchez.sergio.bullkeeper.ui.fragment.terminaldetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.TerminalComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertdetail.IAlertDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Terminal Detail Activity Fragment
 */
public class TerminalDetailActivityMvpFragment extends SupportMvpFragment<TerminalDetailFragmentPresenter,
        ITerminalDetailView, IAlertDetailActivityHandler, TerminalComponent>
        implements ITerminalDetailView {

    public static String ALERT_ID_ARG = "TERMINAL_ID_ARG";
    public static String SON_ID_ARG = "SON_ID_ARG";

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

    public TerminalDetailActivityMvpFragment() { }

    /**
     * New Instance
     * @param alertId
     */
    public static TerminalDetailActivityMvpFragment newInstance(final String alertId, final String sonId) {
        final TerminalDetailActivityMvpFragment alertDetailActivityFragment =
                new TerminalDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(ALERT_ID_ARG, alertId);
        args.putString(SON_ID_ARG, sonId);
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

}
