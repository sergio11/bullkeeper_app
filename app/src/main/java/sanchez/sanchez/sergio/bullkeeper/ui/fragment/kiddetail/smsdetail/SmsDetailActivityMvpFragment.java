package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smsdetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fernandocejas.arrow.checks.Preconditions;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.SmsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.smsdetail.ISmsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.SmsEntity;
import sanchez.sanchez.sergio.domain.models.SmsReadStateEnum;

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
     * Sms Folder Icon
     */
    @BindView(R.id.smsFolderIcon)
    protected ImageView smsFolderIconImageView;

    /**
     * Address Text
     */
    @BindView(R.id.addressText)
    protected TextView addressTextView;

    /**
     * Sms Date Text View
     */
    @BindView(R.id.smsDate)
    protected TextView smsDateTextView;

    /**
     * Sms Read State Text View
     */
    @BindView(R.id.smsReadState)
    protected TextView smsReadStateTextView;

    /**
     * SMS Message Text View
     */
    @BindView(R.id.smsMessage)
    protected TextView smsMessageTextView;


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
        Preconditions.checkNotNull(smsEntity, "Sms Entity can not be null");

        // Folder Name
        switch (smsEntity.getFolderName()) {
            case SENT:
                smsFolderIconImageView.setImageResource(R.drawable.sms_sent_icon);
                break;
            case INBOX:
                smsFolderIconImageView.setImageResource(R.drawable.sms_inbox_icon);
                break;
        }

        // Set Address
        addressTextView.setText(smsEntity.getAddress());


        // Set Sms Date
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                getString(R.string.date_time_format),
                Locale.getDefault());

        smsDateTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.sms_date_time),
                simpleDateFormat.format(smsEntity.getDate())));

        // Set Read State
        if(smsEntity.getReadState().equals(SmsReadStateEnum.VIEWED)) {
            smsReadStateTextView
                    .setText(getString(R.string.message_read));
        } else {
            smsReadStateTextView
                    .setText(getString(R.string.unread_message));
        }

        // Set Message
        smsMessageTextView.setText(smsEntity.getMessage());


    }
}
