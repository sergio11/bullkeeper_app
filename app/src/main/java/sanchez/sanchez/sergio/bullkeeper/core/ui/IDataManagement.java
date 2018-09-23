package sanchez.sanchez.sergio.bullkeeper.core.ui;

/**
 * Data Management
 */
public interface IDataManagement {

    /**
     * Has Pending Changes
     * @return
     */
    Boolean hasPendingChanges();

    /**
     * On Saved Pending Changes
     */
    void onSavedPendingChanges();

    /**
     * On Discard Pending Changes
     */
    void onDiscardPendingChanges();

}
