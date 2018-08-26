package sanchez.sanchez.sergio.bullkeeper.permission.impl;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import java.util.Map;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.permission.IPermissionManager;
import sanchez.sanchez.sergio.utils.ResourceUtils;
import timber.log.Timber;

/**
 * PermissionManagerImpl
 */
public final class PermissionManagerImpl implements IPermissionManager {

    private final Activity activity;

    /**
     * Permissions Resource
     */
    private Map<String, Map<String, String>> permissionsResource = null;

    /**
     * Check Permission Listener
     */
    private OnCheckPermissionListener checkPermissionListener;

    @Inject
    public PermissionManagerImpl(final Activity activity) {
        this.activity = activity;
        permissionsResource = ResourceUtils.getPermissionsResource(activity, R.xml.permissions_data);
    }

    /**
     * Set Check Permission Listener
     * @param checkPermissionListener
     */
    public void setCheckPermissionListener(final OnCheckPermissionListener
                                                   checkPermissionListener) {
        this.checkPermissionListener = checkPermissionListener;
    }

    /**
     * Should Ask Permission
     * @return
     */
    private boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }


    /**
     * Build Permission Listener
     * @param permission
     * @param title
     * @param text
     * @return
     */
    private PermissionListener buildPermissionListener(final String permission,
                                                       String title, String text) {
        PermissionListener dialogOnDeniedPermissionListener =  DialogOnDeniedPermissionListener.Builder
                .withContext(activity)
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

    /**
     * Check Single Permission
     * @param permission
     */
    @Override
    public void checkSinglePermission(final String permission) {
        if(permissionsResource.containsKey(permission)) {
            Map<String, String> permissionResourcesValues = permissionsResource.get(permission);
            String title = permissionResourcesValues.get("title"), text = permissionResourcesValues.get("text");
            PermissionListener permissionListener = buildPermissionListener(permission, title, text);
            Dexter.withActivity(activity)
                    .withPermission(permission)
                    .withListener(permissionListener)
                    .check();
        } else {
            Timber.d("Recursos del Permiso no configurados");
            if(checkPermissionListener != null)
                checkPermissionListener.onErrorOccurred(permission);
        }
    }

    /**
     * Should Ask Permission
     * @param permission
     * @return
     */
    @Override
    public boolean shouldAskPermission(final String permission) {
        if (shouldAskPermission()) {
            int permissionResult = ActivityCompat.checkSelfPermission(activity, permission);
            if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }



    /**
     * On Check Permission Listener
     */
    public interface OnCheckPermissionListener {
        void onSinglePermissionGranted(final String permission);
        void onSinglePermissionRejected(final String permission);
        void onErrorOccurred(final String permission);
    }
}
