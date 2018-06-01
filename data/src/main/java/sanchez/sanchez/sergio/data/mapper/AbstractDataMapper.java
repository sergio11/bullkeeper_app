package sanchez.sanchez.sergio.data.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract Data Mapper
 */
public abstract class AbstractDataMapper<T, E> {

    /**
     * Transform a {@link T} into an {@link E}.
     *
     * @return {@link E} if valid {@link T} otherwise null.
     */
    public abstract E transform(final T originModel);

    /**
     * Transform a List of {@link T} into a Collection of {@link E}.
     *
     * @param originModelCollection Object Collection to be transformed.
     * @return {@link E} if valid {@link T} otherwise null.
     */
    public List<E> transform(final Collection<T> originModelCollection) {
        final List<E> targetModelList = new ArrayList<>(20);
        for (final T originModel : originModelCollection) {
            final E targetModel = transform(originModel);
            if (targetModel != null) {
                targetModelList.add(targetModel);
            }
        }
        return targetModelList;
    }
}
