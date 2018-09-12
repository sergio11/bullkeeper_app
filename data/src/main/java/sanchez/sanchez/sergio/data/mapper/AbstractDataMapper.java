package sanchez.sanchez.sergio.data.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract Data Mapper
 */
public abstract class AbstractDataMapper<T, E> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    public abstract E transform(final T originModel);

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    public abstract T transformInverse(final E originModel);

    /**
     * Transform
     * @param originModelCollection
     * @return
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

    /**
     * Transform Inverse
     * @param originModelCollection
     * @return
     */
    public List<T> transformInverse(final Collection<E> originModelCollection) {
        final List<T> targetModelList = new ArrayList<>(20);
        for (final E originModel : originModelCollection) {
            final T targetModel = transformInverse(originModel);
            if (targetModel != null) {
                targetModelList.add(targetModel);
            }
        }
        return targetModelList;
    }
}
