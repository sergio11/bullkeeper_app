package sanchez.sanchez.sergio.bullkeeper.core.permission.impl;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.fernandocejas.arrow.checks.Preconditions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.bullkeeper.core.permission.IPermissionManager;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;

/**
 * PermissionManagerImpl
 */
public final class PermissionManagerImpl implements IPermissionManager {

    private final AppCompatActivity activity;

    private final INavigator navigator;

    /**
     * Check Permission Listener
     */
    private OnCheckPermissionListener checkPermissionListener;

    @Inject
    public PermissionManagerImpl(final AppCompatActivity activity, final INavigator navigator) {
        this.activity = activity;
        this.navigator = navigator;
    }

    /**
     * Set Check Permission Listener
     * @param checkPermissionListener
     */
    @Override
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
     * Checks if the androidmanifest.xml contains the given permission.
     * @param permission
     * @return
     */
    private boolean appManifestContainsPermission(final String permission) {

        final PackageManager pm = activity.getPackageManager();

        try {

            final PackageInfo packageInfo = pm.getPackageInfo(activity.getPackageName(),
                    PackageManager.GET_PERMISSIONS);

            String[] requestedPermissions = null;
            if (packageInfo != null) {
                requestedPermissions = packageInfo.requestedPermissions;
            }
            if (requestedPermissions == null) {
                return false;
            }
            if (requestedPermissions.length > 0) {
                List<String> requestedPermissionsList = Arrays.asList(requestedPermissions);
                return requestedPermissionsList.contains(permission);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Build Permission Listener
     * @param permission
     * @param reasonText
     * @return
     */
    private PermissionListener buildPermissionListener(final String permission, final String reasonText) {

        return  new BasePermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                super.onPermissionGranted(response);
                if (checkPermissionListener != null)
                    checkPermissionListener.onSinglePermissionGranted(permission);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                super.onPermissionDenied(response);
                navigator.showNoticeDialog(activity,
                        reasonText,
                        new NoticeDialogFragment.NoticeDialogListener() {
                            @Override
                            public void onAccepted(DialogFragment dialog) {
                                if (checkPermissionListener != null)
                                    checkPermissionListener.onSinglePermissionRejected(permission);
                            }
                        });

            }
        };
    }

    /**
     * Check Single Permission
     * @param permission
     * @param reasonText
     */
    @Override
    public void checkSinglePermission(final String permission, final String reasonText) {
        Preconditions.checkNotNull(permission, "Permission can not be null");
        Preconditions.checkNotNull(!permission.isEmpty(), "Permission can not be empty");
        Preconditions.checkNotNull(reasonText, "Reason can not be null");

        if(shouldAskPermission(permission)) {

            PermissionListener permissionListener = buildPermissionListener(permission, reasonText);

            Dexter.withActivity(activity)
                    .withPermission(permission)
                    .withListener(permissionListener)
                    .check();
        } else {
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
        if (shouldAskPermission() && appManifestContainsPermission(permission)) {
            int permissionResult = ActivityCompat.checkSelfPermission(activity, permission);
            if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}
