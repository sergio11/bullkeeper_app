package sanchez.sanchez.sergio.utils;

import android.content.Context;

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
}
