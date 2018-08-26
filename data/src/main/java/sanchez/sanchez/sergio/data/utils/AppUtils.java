package sanchez.sanchez.sergio.data.utils;

import android.content.Context;
import android.os.Build;
import java.util.Locale;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * App Utils
 */
public final class AppUtils implements IAppUtils{

    private final Context context;

    public AppUtils(final Context context) {
        this.context = context;
    }

    /**
     * Get Current Locale
     */
    @Override
    public Locale getCurrentLocale(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return context.getResources().getConfiguration().getLocales().get(0);
        } else{
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }
}
