package sanchez.sanchez.sergio.bullkeeper.core.ui.components;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

/**
 * Support Check Box
 */
public class SupportCheckBox extends AppCompatCheckBox {

    private OnCheckedChangeListener mListener;

    public SupportCheckBox(final Context context) {
        super(context);
    }

    public SupportCheckBox(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public SupportCheckBox(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnCheckedChangeListener(final OnCheckedChangeListener listener) {
        mListener = listener;
        super.setOnCheckedChangeListener(listener);
    }

    /**
     * Set Checked
     * @param checked
     * @param alsoNotify
     */
    public void setChecked(final boolean checked, final boolean alsoNotify) {
        if (!alsoNotify) {
            super.setOnCheckedChangeListener(null);
            super.setChecked(checked);
            super.setOnCheckedChangeListener(mListener);
            return;
        }
        super.setChecked(checked);
    }

    /**
     * Toogle
     * @param alsoNotify
     */
    public void toggle(boolean alsoNotify) {
        if (!alsoNotify) {
            super.setOnCheckedChangeListener(null);
            super.toggle();
            super.setOnCheckedChangeListener(mListener);
        }
        super.toggle();
    }
}
