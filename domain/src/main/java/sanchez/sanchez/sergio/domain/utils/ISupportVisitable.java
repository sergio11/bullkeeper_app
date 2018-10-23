package sanchez.sanchez.sergio.domain.utils;

/**
 * Support Visitable
 */
public interface ISupportVisitable<T extends ISupportVisitor> {

    /**
     * Accept
     * @param visitor
     */
    <E> void accept(T visitor, final E data);

}
