package sanchez.sanchez.sergio.utils;

import android.content.Context;
import android.support.annotation.ArrayRes;

import java.util.HashMap;
import java.util.Map;

/**
 * Resource Utils
 */
public final class ResourceUtils {

    /**
     * Get String Resource By Name
     * @param context
     * @param identifier
     * @return
     */
    public static String getStringResourceByName(final Context context, final String identifier) {
        String packageName = context.getPackageName();
        int resId = context.getResources()
                .getIdentifier(identifier, "string", packageName);
        return context.getString(resId);
    }

    /**
     * To Map
     * @param keys
     * @param values
     * @return
     */
    public static Map<String, String> toMap(final Context context, @ArrayRes int keys, @ArrayRes int values) {
        final String[] keysArray = context.getResources().getStringArray(keys);
        final String[] valuesArray = context.getResources().getStringArray(values);
        return toMap(keysArray, valuesArray);
    }

    /**
     * Creates a mutable map out of two arrays with keys and values.
     *
     */
    public static <T, E> Map toMap(T[] keys, E[] values) {
        int keysSize = (keys != null) ? keys.length : 0;
        int valuesSize = (values != null) ? values.length : 0;

        if (keysSize == 0 && valuesSize == 0) {
            // return mutable map
            return new HashMap();
        }

        if (keysSize != valuesSize) {
            throw new IllegalArgumentException(
                    "The number of keys doesn't match the number of values.");
        }

        Map<T,E> map = new HashMap<>();
        for (int i = 0; i < keysSize; i++) {
            map.put(keys[i], values[i]);
        }

        return map;
    }

}
