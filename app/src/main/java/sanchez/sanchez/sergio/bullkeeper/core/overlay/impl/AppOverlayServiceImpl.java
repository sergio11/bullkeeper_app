package sanchez.sanchez.sergio.bullkeeper.core.overlay.impl;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.overlay.IAppOverlayService;
import timber.log.Timber;
import static android.content.Context.WINDOW_SERVICE;

/**
 * App Overlay Service
 **/
public final class AppOverlayServiceImpl implements IAppOverlayService {

    private final static int DEFAULT_OVERLAY_DIALOG_WIDTH = 900;
    private final static int DEFAULT_OVERLAY_DIALOG_HEIGHT = 800;

    private final Context appContext;
    private final Handler uiHandler;
    private View currentViewShowed;

    /**
     *
     * @param appContext
     */
    public AppOverlayServiceImpl(
            final Context appContext) {
        this.appContext = appContext;
        this.uiHandler = new Handler(appContext.getMainLooper());
    }

    /**
     * Create
     * @param appOverlayLayout
     * @return
     */
    @Override
    public View create(@LayoutRes int appOverlayLayout) {
        View appOverlayView = null;
        try {
            final LayoutInflater layoutInflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (layoutInflater != null) {
                appOverlayView = layoutInflater.inflate(appOverlayLayout, null);
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return appOverlayView;
    }

    /**
     * @param view
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.M)
    @Override
    public void show(final View view) {
        Preconditions.checkState(canDrawOverlays(), "Can not draw overlays");
        Preconditions.checkNotNull(view, "View can not be null");
        show(view, DEFAULT_OVERLAY_DIALOG_WIDTH, DEFAULT_OVERLAY_DIALOG_HEIGHT);

    }

    /**
     *
     * @param view
     * @param width
     * @param height
     */
    @Override
    public void show(final View view, final int width, final int height) {
        Preconditions.checkState(canDrawOverlays(), "Can not draw overlays");
        Preconditions.checkNotNull(view, "View can not be null");
        try {
            final WindowManager mWindowManager = (WindowManager)appContext.getSystemService(WINDOW_SERVICE);
            if(mWindowManager != null) {
                if(currentViewShowed != null)
                    hide(currentViewShowed);
                currentViewShowed = view;
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Timber.d("Thread Name -> %s", Thread.currentThread().getName());
                        mWindowManager.addView(view, createCommonLayoutParams(width, height));
                    }
                });
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param view
     */
    @Override
    public void hide(final View view) {
        Preconditions.checkNotNull(view, "View can not be null");

        currentViewShowed = null;
        try {
            final WindowManager mWindowManager = (WindowManager)appContext.getSystemService(WINDOW_SERVICE);
            if(mWindowManager != null) {
                mWindowManager.removeView(view);
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Can Draw Overlays
     * @return
     */
    @Override
    public boolean canDrawOverlays() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && Settings.canDrawOverlays(appContext);
    }

    /**
     * Private Methods
     */

    /**
     * Create Common Layout Params
     * @return
     */
    private WindowManager.LayoutParams createCommonLayoutParams(final int width, final int height){

        final WindowManager.LayoutParams params = Build.VERSION.SDK_INT < Build.VERSION_CODES.O ?
                new WindowManager.LayoutParams(
                        width,
                        height,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                    | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    PixelFormat.TRANSLUCENT) :  new WindowManager.LayoutParams(
                        width,
                        height,
                            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                                    | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                                    | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                            PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
        params.x = 0;
        params.y = 100;
        params.windowAnimations = R.anim.anim_in;

        return params;

    }

}
