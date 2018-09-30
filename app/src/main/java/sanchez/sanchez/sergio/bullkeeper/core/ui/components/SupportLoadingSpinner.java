package sanchez.sanchez.sergio.bullkeeper.core.ui.components;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
/**
 * Support Loading Spinner
 */
public class SupportLoadingSpinner extends android.support.v7.widget.AppCompatImageView {

    private static final int ROTATE_ANIMATION_DURATION = 800;

    private ObjectAnimator animation;

    public SupportLoadingSpinner(Context context) {
        super(context);
    }

    public SupportLoadingSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SupportLoadingSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        animation = ObjectAnimator.ofFloat(this, "rotationY", 0.0f, 360f);
        animation.setDuration(3600);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }


    /**
     * Set Visibility
     * @param visibility
     */
    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

        if (visibility == View.VISIBLE) {
            animation.start();
        } else {
            animation.cancel();
        }
    }

}
