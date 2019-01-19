package sanchez.sanchez.sergio.bullkeeper.core.ui;


import android.os.Bundle;

/**
 * Support Search LCE Presenter
 * @param <T>
 */
public abstract class SupportSearchLCEPresenter<T extends ISupportLCEView>
        extends SupportLCEPresenter<T> {


    private String lastQueryText = "";

    /**
     * Load Data
     * @param queryText
     */
    public void loadData(final String queryText){
        lastQueryText = queryText;
    }


    /**
     * Load Data
     * @param queryText
     */
    public void loadData(final Bundle args, final String queryText) {
        lastQueryText = queryText;
    }

    /**
     * Load Date
     */
    @Override
    public void loadData() {
        loadData(lastQueryText);
    }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        loadData(args, lastQueryText);
    }

    /**
     * Reset Query
     */
    public void resetQuery(){
        lastQueryText = "";
    }
}
