package sanchez.sanchez.sergio.data.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

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

    /**
     *
     * @return
     */
    @Override
    public Boolean isValidString(final String text) {
        return text != null && !text.isEmpty();
    }

    /**
     * Get Device Id
     * @return
     */
    @Override
    public String getDeviceId() {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

}
