package sanchez.sanchez.sergio.utils;

/**
 * IVisitable
 * @param <T>
 */
public interface IVisitable<T extends IVisitor> {
    /**
     * Accept
     * @param visitor
     */
    void accept(T visitor);
}
