package sanchez.sanchez.sergio.bullkeeper.core.overlay;

import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.RequiresApi;
import android.view.View;

/**
 * App Overlay Service
 **/
public interface IAppOverlayService {

    /**
     * Create
     * @param appOverlayLayout
     * @return
     */
    View create(@LayoutRes int appOverlayLayout);

    /**
     *
     * @param view
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.M)
    void show(View view);

    /**
     *
     * @param view
     */
    void hide(final View view);

    /**
     * Can Draw Overlays
     * @return
     */
    boolean canDrawOverlays();

}
