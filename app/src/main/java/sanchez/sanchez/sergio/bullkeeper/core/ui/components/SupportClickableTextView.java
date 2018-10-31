package sanchez.sanchez.sergio.bullkeeper.core.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.support.v7.widget.AppCompatTextView;

/**
 * Support Clickable Text View
 */
public final class SupportClickableTextView  extends AppCompatTextView implements View.OnTouchListener {

    public SupportClickableTextView(Context context, AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    public SupportClickableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public SupportClickableTextView(Context context, int checkableId) {
        super(context);
        setup();
    }

    public SupportClickableTextView(Context context) {
        super(context);
        setup();
    }

    private void setup() {
        setOnTouchListener(this);
    }

    /**
     * On Touch
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setSelected(true);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setSelected(false);
                break;
        }

        // allow target view to handle click
        return false;
    }
}
