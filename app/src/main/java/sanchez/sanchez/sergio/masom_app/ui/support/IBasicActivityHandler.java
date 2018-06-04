package sanchez.sanchez.sergio.masom_app.ui.support;

public interface IBasicActivityHandler {
    /**
     * Show Short Message
     * @param message
     */
    void showShortMessage(final String message);

    /**
     * Show Long Message
     * @param message
     */
    void showLongMessage(final String message);

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
