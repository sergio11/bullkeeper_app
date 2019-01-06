package sanchez.sanchez.sergio.bullkeeper.sse;

/**
 * Sse Event Handler
 */
public interface ISseEventHandler {

    /**
     * Open Connection
     */
    void open();

    /**
     * Is Opened
     * @return
     */
    boolean isOpened();

    /**
     * Close
     */
    void close();


}
