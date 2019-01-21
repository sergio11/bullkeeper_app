package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.appstats;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;

/**
 * App Stats Dialog
 */
public final class AppStatsDialog extends SupportDialogFragment {

    public static final String TAG = "APP_STATS_DIALOG";

    /**
     * Args
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";
    private static final String TERMINAL_IDENTITY_ARG = "TERMINAL_IDENTITY_ARG";
    private static final String APP_IDENTITY = "APP_IDENTITY";
    private static final String APP_ICON_ENCODED_ARG = "APP_ICON_ENCODED_ARG";
    private static final String APP_NAME_ARG = "APP_NAME_ARG";
    private static final String PACKAGE_NAME_ARG = "PACKAGE_NAME_ARG";
    private static final String TOTAL_TIME_IN_FOREGROUND_ARG = "TOTAL_TIME_IN_FOREGROUND_ARG";
    private static final String FIRST_TIME_ARG = "FIRST_TIME_ARG";
    private static final String LAST_TIME_ARG = "LAST_TIME_ARG";
    private static final String LAST_TIME_USED = "LAST_TIME_USED";

    /**
     * Kid
     */
    private String kid;

    /**
     * Terminal
     */
    private String terminal;

    /**
     * App
     */
    private String app;

    /**
     * App Icon Encoded
     */
    private String appIconEncoded;

    /**
     * App Name
     */
    private String appName;

    /**
     * Package Name
     */
    private String packageName;

    /**
     * Total Time In Foreground
     */
    private Long totalTimeInForeground;

    /**
     * First Time
     */
    private Date firstTime;

    /**
     * Last Time
     */
    private Date lastTime;

    /**
     * Last Time Used
     */
    private Date lastTimeUsed;

    /**
     * Views
     * ===========
     */

    /**
     * App Stats Icon Image View
     */
    @BindView(R.id.appStatsIcon)
    protected ImageView appStatsIconImageView;

    /**
     * App Stats Name Text View
     */
    @BindView(R.id.appStatsName)
    protected TextView appStatsNameTextView;

    /**
     * Total Time In Foreground Text View
     */
    @BindView(R.id.totalTimeInForegroundDetail)
    protected TextView totalTimeInForegroundDetailTextView;

    /**
     * First Time Text View
     */
    @BindView(R.id.firstTimeTextView)
    protected TextView firstTimeTextView;

    /**
     * Last Time Text View
     */
    @BindView(R.id.lastTimeTextView)
    protected TextView lastTimeTextView;

    /**
     * Last Time Used Text View
     */
    @BindView(R.id.lastTimeUsedTextView)
    protected TextView lastTimeUsedTextView;

    /**
     * Dependencies
     * =================
     */

    /**
     * Navigator
     */
    @Inject
    protected INavigator navigator;


    /**
     * Show Dialog
     * @param appCompatActivity
     * @param appIconEncoded
     * @param appName
     * @param packageName
     * @param totalTimeInForeground
     * @param firstTime
     * @param lastTime
     * @param lastTimeUsed
     * @param app
     */
    public static void show(final AppCompatActivity appCompatActivity,
                            final String kid, final String terminal, final String app,
                            final String appIconEncoded,
                            final String appName, final String packageName, final Long totalTimeInForeground,
                            final Date firstTime, final Date lastTime, final Date lastTimeUsed) {
        final AppStatsDialog appStatsDialogFragment = new AppStatsDialog();
        final Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kid);
        args.putString(APP_IDENTITY, app);
        args.putString(TERMINAL_IDENTITY_ARG, terminal);
        args.putString(APP_ICON_ENCODED_ARG, appIconEncoded);
        args.putString(APP_NAME_ARG, appName);
        args.putString(PACKAGE_NAME_ARG, packageName);
        args.putLong(TOTAL_TIME_IN_FOREGROUND_ARG, totalTimeInForeground);
        args.putSerializable(FIRST_TIME_ARG, firstTime);
        args.putSerializable(LAST_TIME_ARG, lastTime);
        args.putSerializable(LAST_TIME_USED, lastTimeUsed);
        appStatsDialogFragment.setArguments(args);
        appStatsDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        appStatsDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.app_stats_dialog_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        getApplicationComponent().inject(this);
    }


    /**
     * On Close Dialog
     */
    @OnClick(R.id.closeDialog)
    protected void onCloseDialog(){
        this.dismiss();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle args = getArguments();

        if(args == null)
            throw new IllegalArgumentException("You must provide args");

        if(!args.containsKey(KID_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide Kid Identity");

        kid = args.getString(KID_IDENTITY_ARG);

        if(!args.containsKey(APP_IDENTITY))
            throw new IllegalArgumentException("You must provide App Identity");

        app = args.getString(APP_IDENTITY);

        if(!args.containsKey(TERMINAL_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide Terminal Identity");

        terminal = args.getString(TERMINAL_IDENTITY_ARG);

        if(!args.containsKey(APP_ICON_ENCODED_ARG))
            throw new IllegalArgumentException("You must provide App Icon Encoded");

        appIconEncoded = args.getString(APP_ICON_ENCODED_ARG);

        if(!args.containsKey(APP_NAME_ARG))
            throw new IllegalArgumentException("You must provide App Name");

        appName = args.getString(APP_NAME_ARG);

        if(!args.containsKey(PACKAGE_NAME_ARG))
            throw new IllegalArgumentException("You must provide Package Name");

        packageName = args.getString(PACKAGE_NAME_ARG);

        if(!args.containsKey(TOTAL_TIME_IN_FOREGROUND_ARG))
            throw new IllegalArgumentException("You must provide Total Time In Foreground");

        totalTimeInForeground = args.getLong(TOTAL_TIME_IN_FOREGROUND_ARG);

        if(!args.containsKey(FIRST_TIME_ARG))
            throw new IllegalArgumentException("You must provide First Time");

        firstTime = (Date) args.getSerializable(FIRST_TIME_ARG);

        if(!args.containsKey(LAST_TIME_ARG))
            throw new IllegalArgumentException("You must provide Las Time Arg");

        lastTime = (Date) args.getSerializable(LAST_TIME_ARG);

        if(!args.containsKey(LAST_TIME_USED))
            throw new IllegalArgumentException("You must provide App Identity");

        lastTimeUsed = (Date) args.getSerializable(LAST_TIME_USED);


        if(appIconEncoded != null && !appIconEncoded.isEmpty()) {
            byte[] decodedString = Base64.decode(appIconEncoded,
                    Base64.DEFAULT);
            final Bitmap decodedByte =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            appStatsIconImageView.setImageBitmap(decodedByte);
        } else {
            appStatsIconImageView.setImageResource(R.drawable.app_rules_tab_cyan);
        }

        appStatsNameTextView.setText(appName);

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                getString(R.string.date_format),
                Locale.getDefault());

        // Total Time In Foreground
        totalTimeInForegroundDetailTextView.setText(
                String.format(Locale.getDefault(),
                        getString(R.string.app_stats_detail_total_time_in_foreground),
                        String.valueOf(totalTimeInForeground/1000/60))
        );

        // First Time
        firstTimeTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.app_stats_detail_first_time),
                simpleDateFormat.format(firstTime)));

        // Las Time
        lastTimeTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.app_stats_detail_last_time),
                simpleDateFormat.format(lastTime)));
        // Last Time Used
        lastTimeUsedTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.app_stats_detail_last_time_used),
                simpleDateFormat.format(lastTimeUsed)));
    }

    /**
     * On Show App Detail Clicked
     */
    @OnClick(R.id.showAppDetail)
    protected void onShowAppDetailClicked(){
        navigator.navigateToAppDetailActivity(getActivity(), kid, terminal, app);
    }
}
