package sanchez.sanchez.sergio.bullkeeper.core.ui.components;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Support Touchable Map Fragment
 */
public final class SupportTouchableMapFragment extends SupportMapFragment {

    /**
     * Listener
     */
    private OnTouchListener mListener;

    /**
     *
     * @param layoutInflater
     * @param viewGroup
     * @param savedInstance
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
        View layout = super.onCreateView(layoutInflater, viewGroup, savedInstance);
        TouchableWrapper frameLayout = new TouchableWrapper(getActivity());
        frameLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        ((ViewGroup) layout).addView(frameLayout,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return layout;
    }

    /**
     *
     * @param listener
     */
    public void setListener(final OnTouchListener listener) {
        mListener = listener;
    }

    /**
     * On Touch Listener
     */
    public interface OnTouchListener {
        void onTouch();
    }

    /**
     * Touchable Wrapper
     */
    public class TouchableWrapper extends FrameLayout {

        /**
         *
         * @param context
         */
        public TouchableWrapper(Context context) {
            super(context);
        }

        /**
         *
         * @param event
         * @return
         */
        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mListener.onTouch();
                    break;
                case MotionEvent.ACTION_UP:
                    mListener.onTouch();
                    break;
            }
            return super.dispatchTouchEvent(event);
        }
    }
}
