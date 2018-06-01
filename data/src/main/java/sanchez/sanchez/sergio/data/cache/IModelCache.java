package sanchez.sanchez.sergio.data.cache;

import io.reactivex.Observable;

/**
 *  An interface representing a user Cache.
 */
public interface IModelCache<T> {

    /**
     * Gets an {@link Observable} which will emit a {@link T}.
     *
     * @param id The user id to retrieve data.
     */
    Observable<T> get(final Integer id);

    /**
     * Puts and element into the cache.
     *
     * @param model Element to insert in the cache.
     */
    void put(T model);

    /**
     * Checks if an element (User) exists in the cache.
     *
     * @param id The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final Integer id);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
