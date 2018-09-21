package sanchez.sanchez.sergio.bullkeeper.ui.support;


/**
 * Support Search LCE Presenter
 * @param <T>
 */
public abstract class SupportSearchLCEPresenter<T extends ISupportLCEView>
        extends SupportLCEPresenter<T> {

    /**
     * Load Data
     * @param queryText
     */
    public abstract void loadData(final String queryText);

}
