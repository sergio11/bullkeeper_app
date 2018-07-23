package sanchez.sanchez.sergio.masom_app.permission;

public interface IPermissionManager {

    /**
     * Check Single Permission
     * @param permission
     */
    void checkSinglePermission(final String permission);

    /**
     * Should Ask Permission
     * @param permission
     * @return
     */
    boolean shouldAskPermission(final String permission);
}
