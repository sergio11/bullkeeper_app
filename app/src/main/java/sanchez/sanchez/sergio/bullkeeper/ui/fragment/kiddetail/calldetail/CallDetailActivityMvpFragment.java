package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.calldetail;

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
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.di.components.CallDetailComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.calldetail.ICallDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Call Detail Activity Fragment
 */
public class CallDetailActivityMvpFragment extends SupportMvpFragment<CallDetailFragmentPresenter,
        ICallDetailView, ICallDetailActivityHandler, CallDetailComponent>
        implements ICallDetailView, CompoundButton.OnCheckedChangeListener {

    /**
     * Args
     */
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String CHILD_ID_ARG = "KID_ID_ARG";
    public static String CALL_ID_ARG = "CALL_ID_ARG";

    /**
     * Views
     * =============
     */

    /**
     * Call Detail Type
     */
    @BindView(R.id.callDetailType)
    protected ImageView callDetailTypeImageView;

    /**
     * Phone Number Text View
     */
    @BindView(R.id.phoneNumberTextView)
    protected TextView phoneNumberTextView;

    /**
     * Call Day Time Text View
     */
    @BindView(R.id.callDayTimeTextView)
    protected TextView callDayTimeTextView;

    /**
     * Call Duration Text View
     */
    @BindView(R.id.callDurationTextView)
    protected TextView callDurationTextView;

    /**
     * Phone Number Is Blocked Text View
     */
    @BindView(R.id.phoneNumberIsBlockedTextView)
    protected TextView phoneNumberIsBlockedTextView;

    /**
     * Switch Block Status Widget
     */
    @BindView(R.id.switchBlockStatusWidget)
    protected SupportSwitchCompat switchBlockStatusWidget;



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
     * Terminal Id
     */
    @State
    protected String terminalId;

    /**
     * Child ID
     */
    @State
    protected String childId;

    /**
     * Call ID
     */
    @State
    protected String callId;


    public CallDetailActivityMvpFragment() { }

    /**
     * New Instance
     * @param terminal
     */
    public static CallDetailActivityMvpFragment newInstance(final String terminal, final String kid, final String call) {
        final CallDetailActivityMvpFragment alertDetailActivityFragment =
                new CallDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(TERMINAL_ID_ARG, terminal);
        args.putString(CHILD_ID_ARG, kid);
        args.putString(CALL_ID_ARG, call);
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

        // Get Call Id
        if(!getArgs().containsKey(CALL_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(CALL_ID_ARG)))
            throw new IllegalStateException("You must provide a call id");

        callId = getArgs().getString(CALL_ID_ARG);

        /**
         * Set On Checked Change Listener
         */
        switchBlockStatusWidget.setOnCheckedChangeListener(this);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_call_detail;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(CallDetailComponent component) {
        component.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public CallDetailFragmentPresenter providePresenter() {
        return component.callDetailFragmentPresenter();
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
     * On Call Detail Loaded
     * @param callDetailEntity
     */
    @Override
    public void onCallDetailLoaded(CallDetailEntity callDetailEntity) {
        Preconditions.checkNotNull(callDetailEntity, "Call Detail can not be empty");


        // Set Call Type
        switch (callDetailEntity.getCallType()) {

            /**
             * Incoming
             */
            case INCOMING:
                callDetailTypeImageView
                        .setImageResource(R.drawable.incoming_calls);
                break;
            /**
             * Missed
             */
            case MISSED:
                callDetailTypeImageView
                        .setImageResource(R.drawable.missed_call_phone_icon);
                break;
            /**
             * Outgoing
             */
            case OUTGOING:
                callDetailTypeImageView
                        .setImageResource(R.drawable.outgoing_call);
                break;

            default:
                callDetailTypeImageView
                        .setImageResource(R.drawable.unknown_call);
        }

        // Set Phone Number
        phoneNumberTextView.setText(
                String.format(Locale.getDefault(), getString(R.string.call_phone_number),
                        callDetailEntity.getPhoneNumber()));


        // Call Duration
        final long totalSecs = Long.valueOf(callDetailEntity.getCallDuration());
        final long minutes = (totalSecs % 3600) / 60;
        final long seconds = totalSecs % 60;
        callDurationTextView.setText(
                String.format(Locale.getDefault(),
                        getString(R.string.call_duration), minutes, seconds));

        // Set Call Day Time
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                getString(R.string.date_time_format),
                Locale.getDefault());
        // Call Day Time
        callDayTimeTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.call_date_time),
                simpleDateFormat.format(callDetailEntity.getCallDayTime())));

        // Set Phone Number is blocked
        phoneNumberIsBlockedTextView.setText(callDetailEntity.isBlocked() ? R.string.call_detail_block_number :
                R.string.call_detail_unlock_number);

        switchBlockStatusWidget.setChecked(callDetailEntity.isBlocked());

    }

    /**
     * On Checked Changed
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked) {

            showConfirmationDialog(R.string.call_detail_block_number_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {

                }

                @Override
                public void onRejected(DialogFragment dialog) {
                    switchBlockStatusWidget.setChecked(false, false);
                }
            });

        } else {
            showConfirmationDialog(R.string.call_detail_unlock_number_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {

                }

                @Override
                public void onRejected(DialogFragment dialog) {
                    switchBlockStatusWidget.setChecked(true, false);
                }
            });
        }

    }
}
