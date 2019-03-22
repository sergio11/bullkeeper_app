package sanchez.sanchez.sergio.bullkeeper.core.ui;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import net.grandcentrix.thirtyinch.TiActivity;
import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.TiView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.navigation.impl.NavigatorImpl;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.events.visitor.INoticeEventVisitor;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.impl.NoticeEvent;
import sanchez.sanchez.sergio.bullkeeper.core.permission.IPermissionManager;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ProgressDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.core.notification.INotificationHelper;
import sanchez.sanchez.sergio.bullkeeper.core.utils.UiUtils;
import sanchez.sanchez.sergio.bullkeeper.ui.services.NotificationHandlerService;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import timber.log.Timber;

/**
 * Support Activity
 */
public abstract class SupportMvpActivity<T extends TiPresenter<E>, E extends TiView>
        extends TiActivity<T, E>
        implements IBasicActivityHandler, IPermissionManager.OnCheckPermissionListener
        , ISupportView, IDataManagement {

    /**
     * NavigatorImpl
     */
    @Inject
    protected NavigatorImpl navigatorImpl;

    /**
     * Local System Notification
     */
    @Inject
    protected ILocalSystemNotification localSystemNotification;

    /**
     * Notification Helper
     */
    @Inject
    protected INotificationHelper notificationHelper;

    /**
     * Preferences Manager
     */
    @Inject
    protected IPreferenceRepository preferencesRepositoryImpl;

    /**
     * Permission Manager
     */
    @Inject
    protected IPermissionManager permissionManager;

    /**
     * Ui Utils
     */
    @Inject
    protected UiUtils uiUtils;

    /**
     * App utils
     */
    @Inject
    protected IAppUtils appUtils;

    /**
     * Activity
     */
    @Inject
    protected Activity activity;


    /**
     * Optional App Bar Layout
     */
    @Nullable
    @BindView(R.id.appToolbarInclude)
    protected View appbarLayout;

    /**
     * Service Binder
     */
    private IBinder serviceBinder;

    /**
     * Bound
     */
    private boolean mBound;


    /**
     * Last Connectivity
     */
    private Connectivity lastConnectivity = null;

    /**
     * Service Connection
     */
    public ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder binder) {
           Timber.d("Service Connection Open");
            serviceBinder = binder;
            mBound = true;
        }
        public void onServiceDisconnected(ComponentName className) {
            Timber.d("Service Connection Close");
            serviceBinder = null;
            mBound = false;
        }
    };

    /**
     * Notice Event Visitor
     */
    private INoticeEventVisitor noticeEventVisitor;


    /**
     * Support Toolbar App
     */
    private SupportToolbarApp supportToolbarApp;

    /**
     * Create At
     */
    private Date createAt;

    /**
     * Enable Notice Event Listener
     */
    protected boolean enableNoticeEventListener = true;

    /**
     * Notice Event Register Key
     */
    private int noticeEventRegisterKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawableResource(getBackgroundResource());

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/HelveticaNeueLTStd-Roman.otf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        Icepick.restoreInstanceState(this, savedInstanceState);

        if(supportToolbarApp == null && appbarLayout != null ) {
            supportToolbarApp = new SupportToolbarApp(getToolbarType(), appbarLayout, getAppIconMode());
            supportToolbarApp.bind(this);
        }

        permissionManager.setCheckPermissionListener(this);


        // On View Ready
        onViewReady(savedInstanceState);

        final ContentViewEvent contentViewEvent = onCreateContentViewEvent();
        if(contentViewEvent == null)
            throw new IllegalStateException("Content View can not be null - you must track the content int he sms");
        // track content views in the sms
        Answers.getInstance().logContentView(contentViewEvent);

        createAt = new Date();

        ReactiveNetwork
                .observeNetworkConnectivity(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Connectivity>() {
                    @Override
                    public void accept(Connectivity connectivity) throws Exception {
                        lastConnectivity = connectivity;
                        if(lastConnectivity.available())
                            onConnectivityAvailable();
                        else
                            onConnectivityNotAvailable();
                    }
                });

    }


    /**
     * On Start
     */
    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, NotificationHandlerService.class),
                serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * On Resume
     */
    @Override
    protected void onResume() {
        super.onResume();

        if(enableNoticeEventListener)
            noticeEventRegisterKey = createNoticeEventListener();

        Timber.d("Preferences Update At -> %d, Create At -> %d",
                preferencesRepositoryImpl.getPreferencesUpdateAt(), createAt.getTime());

        if(preferencesRepositoryImpl.getPreferencesUpdateAt() > 0) {

            final long updateAt = preferencesRepositoryImpl.getPreferencesUpdateAt();

            if(new Date(updateAt).after(createAt)) {
                onPreferencesUpdated();
            }
        }
    }

    /**
     * On Stop
     */
    @Override
    protected void onStop() {
        super.onStop();
        localSystemNotification.unregisterEventListener(noticeEventRegisterKey);
        if (mBound) {
            unbindService(serviceConnection);
            mBound = false;
        }
    }

    /**
     * On Destroy
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(supportToolbarApp != null)
            supportToolbarApp.unbind();
    }

    /**
     * On Save Instance State
     * @param outState
     */
    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    /**
     * Attach Base Context
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        final ContextWrapper contextWrapper = ViewPumpContextWrapper.wrap(newBase);
        super.attachBaseContext(contextWrapper);
    }

    /**
     * Add Fragment
     * @param containerViewId
     * @param fragment
     * @param addToBackStack
     */
    protected void addFragment(int containerViewId, Fragment fragment, boolean addToBackStack) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        if(addToBackStack)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     *
     * @param containerViewId
     * @param fragment
     * @param addToBackStack
     * @param tag
     */
    protected void addFragment(int containerViewId, Fragment fragment, boolean addToBackStack, String tag) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        if(addToBackStack)
            fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * Get Visible Fragments
     * @return
     */
    protected List<Fragment> getVisibleFragments() {
        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
        if (allFragments.isEmpty()) {
            return Collections.emptyList();
        }
        List<Fragment> visibleFragments = new ArrayList<>();
        for (Fragment fragment : allFragments) {
            if (fragment.isVisible()) {
                visibleFragments.add(fragment);
            }
        }
        return visibleFragments;
    }

    /**
     * Replace Fragment
     * @param containerViewId
     * @param fragment
     * @param addToBackStack
     * @param tag
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, boolean addToBackStack, String tag) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        if(addToBackStack)
            fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }


    /**
     * Replace Fragment
     * @param containerViewId
     * @param fragment
     * @param addToBackStack
     * @param tag
     * @param enterAnim
     * @param exitAnim
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, boolean addToBackStack,
                                   String tag, int enterAnim, int exitAnim) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim);
        fragmentTransaction.replace(containerViewId, fragment);
        if(addToBackStack)
            fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * Get Fragment By Tag
     * @param tag
     * @param <T>
     * @return
     */
    protected <T> T getFragmentByTag(String tag){
        return (T) this.getSupportFragmentManager().findFragmentByTag(tag);
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
            */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        showNoticeDialog(R.string.network_error_ocurred, false);
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        showNoticeDialog(R.string.unexpected_error_ocurred, false);
    }

    /**
     * Show Short Message
     * @param message
     */
    @Override
    public void showShortMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show Short Message
     * @param messageResId
     */
    @Override
    public void showShortMessage(@StringRes int messageResId) {
        showShortMessage(getString(messageResId));
    }

    /**
     * Show Long Message
     * @param message
     */
    @Override
    public void showLongMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Show Notice Dialog
     * @param title
     */
    @Override
    public void showNoticeDialog(final String title) {
        navigatorImpl.showNoticeDialog(this, title);
    }

    /**
     * Show Notice Dialog
     * @param stringResId
     */
    @Override
    public void showNoticeDialog(final Integer stringResId) {
        showNoticeDialog(getString(stringResId));
    }

    /**
     * Show Notice Dialog
     * @param title
     * @param noticeDialogListener
     */
    @Override
    public void showNoticeDialog(final String title,
                                 final NoticeDialogFragment.NoticeDialogListener noticeDialogListener) {
        navigatorImpl.showNoticeDialog(this, title, noticeDialogListener);
    }

    /**
     * Show Notice
     * @param title
     * @param isSuccess
     * @param noticeDialogListener
     */
    @Override
    public void showNoticeDialog(String title, boolean isSuccess, NoticeDialogFragment.NoticeDialogListener noticeDialogListener) {
        navigatorImpl.showNoticeDialog(this, title, isSuccess, noticeDialogListener);
    }

    /**
     * Show Norice Dialog
     * @param stringResId
     * @param isSuccess
     */
    @Override
    public void showNoticeDialog(@StringRes Integer stringResId, boolean isSuccess) {
        navigatorImpl.showNoticeDialog(this, getString(stringResId), isSuccess);
    }

    /**
     * Show Notice Dialog
     * @param string
     * @param isSuccess
     */
    @Override
    public void showNoticeDialog(String string, boolean isSuccess) {
        navigatorImpl.showNoticeDialog(this, string, isSuccess);
    }

    /**
     * Show Notice Dialog
     * @param stringResId
     * @param noticeDialogListener
     */
    @Override
    public void showNoticeDialog(final Integer stringResId,
                                 final NoticeDialogFragment.NoticeDialogListener noticeDialogListener) {
        showNoticeDialog(getString(stringResId), noticeDialogListener);
    }

    /**
     * Show Progress Dialog
     * @param title
     */
    @Override
    public void showProgressDialog(String title) {
        ProgressDialogFragment.showDialog(this, title);
    }

    /**
     * Show Progress Dialog
     * @param stringResId
     */
    @Override
    public void showProgressDialog(Integer stringResId) {
        showProgressDialog(getString(stringResId));
    }

    /**
     * Hide Progress Dialog
     */
    @Override
    public void hideProgressDialog() {
        ProgressDialogFragment.cancelCurrent();
    }

    /**
     * Show Confirmation Dialog
     * @param title
     */
    @Override
    public void showConfirmationDialog(String title) {
        ConfirmationDialogFragment.showDialog(this, title);
    }

    /**
     * Show Confirmation Dialog
     * @param stringResId
     */
    @Override
    public void showConfirmationDialog(Integer stringResId) {
        showConfirmationDialog(getString(stringResId));
    }

    /**
     * Show Confirmation Dialog
     * @param title
     * @param confirmationDialogListener
     */
    @Override
    public void showConfirmationDialog(String title, ConfirmationDialogFragment.ConfirmationDialogListener confirmationDialogListener) {
        ConfirmationDialogFragment.showDialog(this, title, confirmationDialogListener);
    }

    /**
     * Show Confirmation Dialog
     * @param stringResId
     * @param confirmationDialogListener
     */
    @Override
    public void showConfirmationDialog(Integer stringResId, ConfirmationDialogFragment.ConfirmationDialogListener confirmationDialogListener) {
        showConfirmationDialog(getString(stringResId), confirmationDialogListener);
    }

    /**
     * On Single Permission Granted
     * @param permission
     */
    @Override
    public void onSinglePermissionGranted(String permission) {
        Timber.d("On Single Permission Granted: %s", permission);
    }

    /**
     * On Single Permission Rejected
     * @param permission
     */
    @Override
    public void onSinglePermissionRejected(String permission) {
        Timber.d("On Single Permission Rejected: %s", permission);
    }

    /**
     * On Single Permission Granted
     * @param permission
     * @param callbackArgs
     */
    @Override
    public void onSinglePermissionGranted(final String permission, final Bundle callbackArgs) {
        Timber.d("On Single Permission Granted: %s", permission);
    }

    /**
     * On Single Permission Rejected
     * @param permission
     * @param callbackArgs
     */
    @Override
    public void onSinglePermissionRejected(final String permission, final Bundle callbackArgs) {
        Timber.d("On Single Permission Rejected: %s", permission);
    }

    /**
     * Show Long Simple Snackbar
     * @param viewRoot
     * @param description
     * @param actionText
     * @param onClickListener
     */
    @Override
    public void showLongSimpleSnackbar(final ViewGroup viewRoot, final String description,  final String actionText,
                                       final View.OnClickListener onClickListener){
        showLongSimpleSnackbar(viewRoot, description, actionText, onClickListener, null);
    }

    /**
     * Show Simple Snackbar
     * @param actionText
     * @param onClickListener
     */
    @Override
    public void showLongSimpleSnackbar(final ViewGroup viewRoot, final String description,  final String actionText,
                                       final View.OnClickListener onClickListener, final Snackbar.Callback snackbarCallback) {
        Preconditions.checkNotNull(viewRoot, "View Root can not be null");
        Preconditions.checkNotNull(actionText, "Action Text can not be null");
        Preconditions.checkNotNull(onClickListener, "Click Listener can not be null");

        Snackbar snackbar = Snackbar.make(viewRoot,description , Snackbar.LENGTH_LONG);

        snackbar.setAction(actionText, onClickListener);

        if(snackbarCallback != null)
            snackbar.addCallback(snackbarCallback);

        snackbar.show();
    }

    /**
     * Set Dimensions
     * @param view
     * @param widthInDp
     * @param heightInDp
     */
    @Override
    public void setDimensions(final View view, int widthInDp, int heightInDp){
        final Resources resources = getResources();
        int widthInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthInDp, resources.getDisplayMetrics());
        int heightInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightInDp, resources.getDisplayMetrics());
        Timber.d("Set Dimensions: width  -> %d dp (%d px), height -> %d dp (%d px)", widthInDp,
                widthInPx, heightInDp, heightInPx);
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = widthInPx;
        layoutParams.height = heightInPx;
        view.setLayoutParams(layoutParams);
    }

    /**
     * Set Dimensions To Match Parent
     * @param view
     */
    @Override
    public void setDimensionsToMatchParent(final View view) {
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(layoutParams);
    }

    /**
     * Show App Menu
     */
    @Override
    public void showAppMenu() {
        navigatorImpl.showAppMenuDialog(this);
    }

    /**
     * On Error Occurred
     * @param permission
     */
    @Override
    public void onErrorOccurred(String permission) {
        Timber.d("On Error Ocurred: %s", permission);
    }

    /**
     * Close Activity
     */
    @Override
    public void closeActivity() {
        finish();
    }

    /**
     * Safe Close Activity
     */
    @Override
    public void safeCloseActivity() {

        if(hasPendingChanges())

            showConfirmationDialog(R.string.has_pending_changes, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {
                    onSavedPendingChanges();
                    closeActivity();
                }

                @Override
                public void onRejected(DialogFragment dialog) {
                    onDiscardPendingChanges();
                    closeActivity();
                }
            });

        else
            super.onBackPressed();

    }

    /**
     * Has Pending Changes
     * @return
     */
    @Override
    public Boolean hasPendingChanges() {
        return Boolean.FALSE;
    }

    /**
     * On Saved Pending Changes
     */
    @Override
    public void onSavedPendingChanges() {}

    /**
     * On Discard Pending Changes
     */
    @Override
    public void onDiscardPendingChanges() {}

    /**
     * Show Question Dialog
     */
    @Override
    public void showAppHelpDialog() {
        navigatorImpl.showAppHelpDialog(this, getString(R.string.how_can_bullkeeper_help_you),
                getString(R.string.youtube_video_cue));
    }

    /**
     * Navigate To Home
     */
    @Override
    public void navigateToHome() {
        navigatorImpl.navigateToHome(activity);
    }


    /**
     * Initialize Injector
     */
    protected abstract void initializeInjector();

    /**
     * Toolbar Type
     * @return
     */
    protected int getToolbarType(){
        return SupportToolbarApp.INFORMATIVE_TOOLBAR;
    }

    /**
     * App Icon Mode
     * @return
     */
    protected int getAppIconMode(){
        return SupportToolbarApp.ENABLE_GO_TO_HOME;
    }

    /**
     * Get Layout Res
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * Create Notice Event Listener
     * @return
     */
    private int createNoticeEventListener() {
        noticeEventVisitor = new INoticeEventVisitor() {
            @Override
            public void visit(NoticeEvent noticeEvent) {
                Preconditions.checkNotNull(noticeEvent, "Notice Event can not be null");
                onNoticeEventFired(noticeEvent);
            }
        };
       return localSystemNotification.registerEventListener(NoticeEvent.class, noticeEventVisitor);
    }

    /**
     * On Notice Event Fired
     * @param noticeEvent
     */
    protected void onNoticeEventFired(final NoticeEvent noticeEvent){
        Preconditions.checkNotNull(noticeEvent, "Notification Event can not be null");
        Timber.d("Notice Event Fired");
        showNoticeDialog(noticeEvent.getTitle() + " " + noticeEvent.getContent());
    }

    /**
     * On View Ready
     */
    protected void onViewReady(final Bundle savedInstanceState){
        if (savedInstanceState == null)
            onNewViewInstance();
        else
            onSavedViewInstance();

    }

    /**
     * On New View Instance
     */
    protected void onNewViewInstance(){}

    /**
     * On Saved View Instance
     */
    protected void onSavedViewInstance(){}

    /**
     * On Preferences Updated
     */
    protected void onPreferencesUpdated(){}


    /**
     * Set Support Toolbar App
     * @param supportToolbarApp
     */
    @Override
    public void setSupportToolbarApp(SupportToolbarApp supportToolbarApp) {
        this.supportToolbarApp = supportToolbarApp;
    }

    /**
     * Open Mail
     */
    @Override
    public void openMailApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_EMAIL);
        startActivity(Intent.createChooser(intent, ""));
    }


    /**
     * Close Session
     */
    @Override
    public void closeSession() {
        preferencesRepositoryImpl.setAuthToken(IPreferenceRepository.AUTH_TOKEN_DEFAULT_VALUE);
        preferencesRepositoryImpl.setPrefCurrentUserIdentity(IPreferenceRepository.CURRENT_USER_IDENTITY_DEFAULT_VALUE);
        navigatorImpl.navigateToIntro(activity, true);
    }

    /**
     * On Result Ok
     * @param result
     */
    @Override
    public void onResultOk(final Intent result) {
        Preconditions.checkNotNull(result, "Result can not be null");
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    /**
     * On Result Canceled
     */
    @Override
    public void onResultCanceled() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        return null;
    }

    /**
     * On Back Pressed
     */
    @Override
    public void onBackPressed() {
        safeCloseActivity();
    }

    /**
     * On Create Content View Event
     * @return
     */
    protected abstract ContentViewEvent onCreateContentViewEvent();

    /**
     * Get Background Res
     * @return
     */
    @DrawableRes
    protected abstract int getBackgroundResource();

    /**
     * On Connectivity Available
     */
    protected void onConnectivityAvailable(){}

    /**
     * On Connectivity Not Available
     */
    protected void onConnectivityNotAvailable(){
        showLongMessage(getString(R.string.connectivity_not_available));
    }

    /**
     * Is Connectivity Available
     * @return
     */
    @Override
    public boolean isConnectivityAvailable(){
        return lastConnectivity != null && lastConnectivity.available();
    }

}
