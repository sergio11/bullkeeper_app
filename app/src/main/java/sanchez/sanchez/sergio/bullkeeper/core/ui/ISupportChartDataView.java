package sanchez.sanchez.sergio.bullkeeper.core.ui;

/**
 * Support Chart Data View
 */
public interface ISupportChartDataView<T> extends ISupportView {

    /**
     * On Data Avaliable
     * @param chartData
     */
    void onDataAvaliable(final T chartData);

    /**
     * On No Dimensions Data Avaliable
     */
    void onNoDataAvaliable();
}
