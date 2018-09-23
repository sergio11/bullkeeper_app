package sanchez.sanchez.sergio.bullkeeper.core.ui;

import java.util.List;

/**
 * Support LCE View
 * @param <T>
 */
public interface ISupportLCEView<T> extends ISupportView {

    /**
     * On No Data Found
     */
    void onNoDataFound();

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    void onDataLoaded(final List<T> dataLoaded);

}
