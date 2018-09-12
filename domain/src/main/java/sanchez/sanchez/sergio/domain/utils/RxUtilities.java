package sanchez.sanchez.sergio.domain.utils;


import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * RxUtils
 */
public class RxUtilities {

    public static <T, R> Function<T, Observable<R>> ternaryOperator(
            final Function<T, Boolean> predicate,
            final Function<T, Observable<R>> ifTrue,
            final Function<T, Observable<R>> ifFalse) {
        return new Function<T, Observable<R>>() {
            @Override
            public Observable<R> apply(T item) throws Exception {
                return predicate.apply(item)
                        ? ifTrue.apply(item)
                        : ifFalse.apply(item);
            }
        };
    }
}
