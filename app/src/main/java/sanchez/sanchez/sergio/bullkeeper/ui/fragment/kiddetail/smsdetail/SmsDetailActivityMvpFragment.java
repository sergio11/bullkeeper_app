package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smsdetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.fernandocejas.arrow.checks.Preconditions;

import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.SmsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.smsdetail.ISmsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.SmsEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Sms Detail Activity Mvp Fragment
 */
public class SmsDetailActivityMvpFragment extends SupportMvpFragment<SmsDetailFragmentPresenter,
        ISmsDetailView, ISmsDetailActivityHandler, SmsComponent>
        implements ISmsDetailView {

    /**
     * Args
     */
    public static String KID_ID_ARG = "KID_ID_ARG";
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String SMS_ID_ARG = "SMS_ID_ARG";


    /**
     * Views
     * =============
     */


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
     * Sms
     */
    @State
    protected String sms;

    public SmsDetailActivityMvpFragment() { }

    /**
     * New Instance
     * @param kid
     * @param terminal
     * @param sms
     * @return
     */
    public static SmsDetailActivityMvpFragment newInstance(final String kid, final String terminal, final String sms) {
        final SmsDetailActivityMvpFragment fragment =
                new SmsDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(KID_ID_ARG, kid);
        args.putString(TERMINAL_ID_ARG, terminal);
        args.putString(SMS_ID_ARG, sms);
        fragment.setArguments(args);
        return fragment;
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
        if(!getArgs().containsKey(SMS_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(SMS_ID_ARG)))
            throw new IllegalStateException("You must provide a sms id");

        sms = getArgs().getString(SMS_ID_ARG);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_sms_detail;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(SmsComponent component) {
        component.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public SmsDetailFragmentPresenter providePresenter() {
        return component.smsDetailFragmentPresenter();
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
     * On Sms Detail Loaded
     * @param smsEntity
     */
    @Override
    public void onSmsDetailLoaded(SmsEntity smsEntity) {
        Preconditions.checkNotNull(smsEntity, "Sms Entitity can not be null");
    }
}
