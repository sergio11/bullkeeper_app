package sanchez.sanchez.sergio.domain.exception;

/**
 * Interface to represent a wrapper around an {@link java.lang.Exception} to manage errors.
 */
public interface ErrorBundle {

    /**
     * Get Exception
     * @return
     */
    Exception getException();

    /**
     * Get Error Messages
     * @return
     */
    String getErrorMessage();
}
