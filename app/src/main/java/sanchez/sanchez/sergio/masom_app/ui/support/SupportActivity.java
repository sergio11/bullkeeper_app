package sanchez.sanchez.sergio.masom_app.ui.support;


import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import net.grandcentrix.thirtyinch.TiActivity;
import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.TiView;
import javax.inject.Inject;

import butterknife.ButterKnife;
import icepick.Icepick;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import sanchez.sanchez.sergio.masom_app.AndroidApplication;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.navigation.impl.NavigatorImpl;
import sanchez.sanchez.sergio.masom_app.notification.local.ILocalSystemNotification;
import sanchez.sanchez.sergio.masom_app.notification.local.ILocalSystemNotificationVisitor;
import sanchez.sanchez.sergio.masom_app.notification.model.impl.BasicNotification;
import sanchez.sanchez.sergio.masom_app.permission.impl.PermissionManagerImpl;
import timber.log.Timber;

/**
 * Support Activity
 */
public abstract class SupportActivity<T extends TiPresenter<E>, E extends TiView>
        extends TiActivity<T, E>
        implements IBasicActivityHandler, PermissionManagerImpl.OnCheckPermissionListener,
        ILocalSystemNotificationVisitor {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/HelveticaNeueLTStd-Roman.otf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        localSystemNotification.registerVisitor(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        localSystemNotification.unregisterVisitor();
    }


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
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }


    /**
     * Replace Fragment
     * @param containerViewId
     * @param fragment
     * @param tag
     * @param enterAnim
     * @param exitAnim
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag, int enterAnim, int exitAnim) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim);
        fragmentTransaction.replace(containerViewId, fragment);
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
     * Show Short Message
     * @param message
     */
    @Override
    public void showShortMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show Long Message
     * @param message
     */
    @Override
    public void showLongMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected abstract void initializeInjector();

    @Override
    public void onSinglePermissionGranted(String permission) {
        Timber.d("On Single Permission Granted: %s", permission);
    }

    @Override
    public void onSinglePermissionRejected(String permission) {
        Timber.d("On Single Permission Rejected: %s", permission);
    }

    @Override
    public void onErrorOccurred(String permission) {
        Timber.d("On Error Ocurred: %s", permission);
    }


    /**
     * Basic Notification
     * @param basicNotification
     */
    @Override
    public void visit(BasicNotification basicNotification) {
        Timber.d("Basic Notification");

    }
}
