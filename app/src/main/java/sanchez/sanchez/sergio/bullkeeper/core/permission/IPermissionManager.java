package sanchez.sanchez.sergio.bullkeeper.core.permission;

public interface IPermissionManager {

    /**
     * Check Single Permission
     * @param permission
     * @param reasonText
     */
    void checkSinglePermission(final String permission, final String reasonText);

    /**
     * Should Ask Permission
     * @param permission
     * @return
     */
    boolean shouldAskPermission(final String permission);

    /**
     * Set Check Permission Listener
     * @param checkPermissionListener
     */
    void setCheckPermissionListener(final OnCheckPermissionListener checkPermissionListener);


    /**
     * On Check Permission Listener
     */
    interface OnCheckPermissionListener {
        void onSinglePermissionGranted(final String permission);
        void onSinglePermissionRejected(final String permission);
        void onErrorOccurred(final String permission);
    }
}
