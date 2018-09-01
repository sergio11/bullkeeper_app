package sanchez.sanchez.sergio.bullkeeper.ui.support;


import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.fernandocejas.arrow.checks.Preconditions;
import net.grandcentrix.thirtyinch.TiActivity;
import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.TiView;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.navigation.impl.NavigatorImpl;
import sanchez.sanchez.sergio.bullkeeper.notification.local.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.notification.local.ILocalSystemNotificationVisitor;
import sanchez.sanchez.sergio.bullkeeper.notification.model.impl.BasicNotification;
import sanchez.sanchez.sergio.bullkeeper.permission.impl.PermissionManagerImpl;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ProgressDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.notification.INotificationHelper;
import sanchez.sanchez.sergio.bullkeeper.utils.PreferencesManager;
import timber.log.Timber;

/**
 * Support Activity
 */
public abstract class SupportMvpActivity<T extends TiPresenter<E>, E extends TiView>
        extends TiActivity<T, E>
        implements IBasicActivityHandler, PermissionManagerImpl.OnCheckPermissionListener,
        ILocalSystemNotificationVisitor, ISupportView{

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
    protected PreferencesManager preferencesManager;


    /**
     * Optional App Bar Layout
     */
    @Nullable
    @BindView(R.id.appToolbarInclude)
    protected View appbarLayout;


    /**
     * Support Toolbar App
     */
    private SupportToolbarApp supportToolbarApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/HelveticaNeueLTStd-Roman.otf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        Icepick.restoreInstanceState(this, savedInstanceState);

        if(supportToolbarApp == null && appbarLayout != null ) {
            supportToolbarApp = new SupportToolbarApp(getToolbarType(), appbarLayout);
            supportToolbarApp.bind(this);
        }

        // On View Ready
        onViewReady(savedInstanceState);
    }

    /**
     * On Resume
     */
    @Override
    protected void onResume() {
        super.onResume();
        localSystemNotification.registerVisitor(this);
    }

    /**
     * On Resume
     */
    @Override
    protected void onPause() {
        super.onPause();
        localSystemNotification.unregisterVisitor();
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
        showNoticeDialog(R.string.network_error_ocurred);
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        showNoticeDialog(R.string.unexpected_error_ocurred);
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
        // Create an instance of the dialog fragment and show it
        NoticeDialogFragment.showDialog(this, title);
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
        NoticeDialogFragment.showDialog(this, title, noticeDialogListener);
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
     * Show Simple Snackbar
     * @param actionText
     * @param onClickListener
     */
    @Override
    public void showLongSimpleSnackbar(final ViewGroup viewRoot, final String description,  final String actionText, final View.OnClickListener onClickListener) {
        Preconditions.checkNotNull(viewRoot, "View Root can not be null");
        Preconditions.checkNotNull(actionText, "Action Text can not be null");
        Preconditions.checkNotNull(onClickListener, "Click Listener can not be null");

        Snackbar snackbar = Snackbar.make(viewRoot,description , Snackbar.LENGTH_LONG);

        snackbar.setAction(actionText, onClickListener);

        snackbar.show();
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
        navigatorImpl.navigateToHome();
    }

    /**
     * Basic Notification
     * @param basicNotification
     */
    @Override
    public void visit(BasicNotification basicNotification) {
        Timber.d("Basic Notification");
        notificationHelper.createBasicNotification(basicNotification.getTitle(),
                basicNotification.getContent());
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
     * Get Layout Res
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * On View Ready
     */
    protected void onViewReady(final Bundle savedInstanceState){}


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
        preferencesManager.setAuthToken(PreferencesManager.AUTH_TOKEN_DEFAULT_VALUE);
        preferencesManager.setPrefCurrentUserIdentity(PreferencesManager.CURRENT_USER_IDENTITY_DEFAULT_VALUE);
        navigatorImpl.navigateToIntro(true);
    }
}
