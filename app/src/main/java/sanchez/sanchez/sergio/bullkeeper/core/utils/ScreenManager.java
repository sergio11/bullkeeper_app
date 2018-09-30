package sanchez.sanchez.sergio.bullkeeper.core.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Screen Manager
 */
public final class ScreenManager {

    private final Context appContext;

    public ScreenManager(Context appContext) {
        this.appContext = appContext;
    }

    /**
     * Get Screen Width In Dps
     * @return
     */
    public int getScreenWidthInDPs(){

         /*
            DisplayMetrics
                A structure describing general information about a display,
                such as its size, density, and font scaling.
        */
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) appContext.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);
        return widthInDP;
    }

    /**
     * Get Screen Height In Dps
     * @return
     */
    public int getScreenHeightInDPs(){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) appContext.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int heightInDP = Math.round(dm.heightPixels / dm.density);
        return heightInDP;
    }


}
