package sanchez.sanchez.sergio.bullkeeper.ui.support;

public abstract class SupportLCEPresenter<T extends ISupportLCEView> extends SupportPresenter<T> {

    /**
     * On Init
     */
    @Override
    protected void onInit() {
        super.onInit();
        loadData();
    }

    /**
     * Load Data
     */
    public abstract void loadData();

}
