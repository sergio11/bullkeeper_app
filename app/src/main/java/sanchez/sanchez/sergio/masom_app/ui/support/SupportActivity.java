package sanchez.sanchez.sergio.masom_app.ui.support;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import java.util.Map;
import javax.inject.Inject;
import icepick.Icepick;
import sanchez.sanchez.sergio.masom_app.AndroidApplication;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.navigation.Navigator;
import sanchez.sanchez.sergio.utils.ResourceUtils;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Support Activity
 */
public abstract class SupportActivity extends AppCompatActivity implements IBasicActivityHandler {

    @Inject
    protected Navigator navigator;

    /**
     * Permissions Resource
     */
    private Map<String, Map<String, String>> permissionsResource = null;

    /**
     * Check Permission Listener
     */
    private OnCheckPermissionListener checkPermissionListener;

    public void setCheckPermissionListener(OnCheckPermissionListener checkPermissionListener) {
        this.checkPermissionListener = checkPermissionListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        this.getApplicationComponent().inject(this);
        permissionsResource = ResourceUtils.getPermissionsResource(this, R.xml.permissions_data);
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
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected abstract void initializeInjector();


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

    protected void replaceFragment(int containerViewId, Fragment fragment, String tag, int enterAnim, int exitAnim) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim);
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

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

    @Override
    public void showShortMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLongMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }

    private PermissionListener buildPermissionListener(final String permission, String title, String text) {
        PermissionListener dialogOnDeniedPermissionListener =  DialogOnDeniedPermissionListener.Builder
                .withContext(this)
                .withTitle(title)
                .withMessage(text)
                .withButtonText(android.R.string.ok)
                .build();

        PermissionListener basicPermissionListener = new BasePermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                super.onPermissionGranted(response);
                if (checkPermissionListener != null)
                    checkPermissionListener.onSinglePermissionGranted(permission);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                super.onPermissionDenied(response);
                if (checkPermissionListener != null)
                    checkPermissionListener.onSinglePermissionRejected(permission);
            }
        };
        return new CompositePermissionListener(basicPermissionListener, dialogOnDeniedPermissionListener);
    }

    @Override
    public boolean shouldAskPermission(String permission){
        if (shouldAskPermission()) {
            int permissionResult = ActivityCompat.checkSelfPermission(this, permission);
            if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void checkSinglePermission(String permission) {

        if(permissionsResource.containsKey(permission)) {
            Map<String, String> permissionResourcesValues = permissionsResource.get(permission);
            String title = permissionResourcesValues.get("title"), text = permissionResourcesValues.get("text");
            PermissionListener permissionListener = buildPermissionListener(permission, title, text);
            Dexter.withActivity(this)
                    .withPermission(permission)
                    .withListener(permissionListener)
                    .check();
        } else {
            Timber.d("Recursos del Permiso no configurados");
            if(checkPermissionListener != null)
                checkPermissionListener.onErrorOcurred(permission);
        }
    }

    /**
     * On Check Permission Listener
     */
    public interface OnCheckPermissionListener {
        void onSinglePermissionGranted(String permission);
        void onSinglePermissionRejected(String permission);
        void onErrorOcurred(String permission);
    }

}
