package sanchez.sanchez.sergio.bullkeeper.core.ui;

import android.os.Bundle;

/**
 * Support LCE Presenter
 * @param <T>
 */
public abstract class SupportLCEPresenter<T extends ISupportLCEView>
        extends SupportPresenter<T> {

    /**
     * On Init
     */
    @Override
    protected void onInit() {
        super.onInit();
        loadData();
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);
        loadData(args);
    }

    /**
     * Load Data
     */
    public abstract void loadData();

    /**
     * Load Data
     */
    public abstract void loadData(final Bundle args);

}
