package sanchez.sanchez.sergio.bullkeeper.core.ui.components;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.utils.SimpleSpanBuilder;

/**
 * Support Edit Weekly Frequency
 */
public final class SupportEditWeeklyFrequency extends FrameLayout {

    /**
     * Days Of Week
     */
    private String[] daysOfWeek;
    /**
     * Days Selected
     */
    private final Map<String, Integer> daysSelected = new HashMap<>();

    /**
     * Weekly Frequency Text View
     */
    private SupportClickableTextView weeklyFrequencyTextView;

    /**
     * Weekly Frequency Error Text View
     */
    private TextView weeklyFrequencyErrorTextView;

    public SupportEditWeeklyFrequency(@NonNull Context context) {
        super(context);
        init(context);
    }

    public SupportEditWeeklyFrequency(@NonNull Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SupportEditWeeklyFrequency(@NonNull Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SupportEditWeeklyFrequency(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     * On Measure
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * On Layout
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * Get Days of Current Week
     * @return
     */
    private String[] getDaysOfCurrentWeek(){
        final DateFormat format = new SimpleDateFormat ("EEEE", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        String[] days = new String[7];
        for (int i = 0; i < 7; i++)
        {
            days[i] = format.format(calendar.getTime()).toUpperCase().substring(0, 2);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    /**
     * Init
     * @param context
     */
    private void init(Context context) {
        final View view = inflate(context, R.layout.edit_weekly_frequency_layout, this);
        weeklyFrequencyTextView = view.findViewById(R.id.weeklyFrequencyInput);
        weeklyFrequencyErrorTextView = view.findViewById(R.id.weeklyFrequencyError);

        // Get Days of Current Week
        daysOfWeek = getDaysOfCurrentWeek();

        final SimpleSpanBuilder ssb = new SimpleSpanBuilder();
        for(final String dayOfWeek: daysOfWeek) {
            ssb.appendWithSpace(dayOfWeek, new DayClickableSpan(weeklyFrequencyTextView, getContext(),
                    dayOfWeek, false) {
                @Override
                public void onClick(View widget) {
                    super.onClick(widget);
                }
            });
            daysSelected.put(dayOfWeek, 0);
        }

        weeklyFrequencyTextView.setText(ssb.build());
        weeklyFrequencyTextView.setHighlightColor(
                Color.TRANSPARENT);
        weeklyFrequencyTextView.setMovementMethod(LinkMovementMethod.getInstance());

    }

    /**
     * Get Days of Week Status
     * @return
     */
    public int[] getDaysOfWeekStatus() {
        final int[] daysOfWeekStatus = new int[daysOfWeek.length];
        for(int i = 0; i < daysOfWeek.length; i++)
            daysOfWeekStatus[i] = daysSelected.get(daysOfWeek[i]);
        return daysOfWeekStatus;
    }

    /**
     *
     * @param daysOfWeekStatus
     */
    public void setDaysOfWeekStatus(final int[] daysOfWeekStatus) {
        Preconditions.checkNotNull(daysOfWeekStatus, "Days of week can not be null");
        Preconditions.checkState(daysOfWeekStatus.length == 7, "You must specify 7 values");
        for(int i = 0; i < daysOfWeek.length; i++) {
            daysSelected.put(daysOfWeek[i], daysOfWeekStatus[i]);
        }
        weeklyFrequencyTextView.invalidate();
    }

    /**
     * Set Error
     * @param errorStringRes
     */
    public void setError(final @StringRes int errorStringRes) {
        setError(getContext().getString(errorStringRes));
    }

    /**
     * Set Error
     * @param errorString
     */
    public void setError(final String errorString) {
        weeklyFrequencyErrorTextView.setVisibility(VISIBLE);
        weeklyFrequencyErrorTextView.setText(errorString);
    }

    /**
     * Clear Error
     */
    public void clearError() {
        weeklyFrequencyErrorTextView.setVisibility(INVISIBLE);
        weeklyFrequencyErrorTextView.setText("");
    }

    /**
     * Day Clickable Span
     */
    private abstract class DayClickableSpan extends ClickableSpan {

        protected final SupportClickableTextView textView;
        protected final Context appContext;
        protected final String currentText;
        protected final boolean hasUnderline;

        private DayClickableSpan(final SupportClickableTextView textView,
                                 final Context appContext, final String currentText,
                                 final boolean hasUnderline) {
            this.textView = textView;
            this.appContext = appContext;
            this.currentText = currentText;
            this.hasUnderline = hasUnderline;
        }

        /**
         * On Click
         *
         * @param widget
         */
        @Override
        public void onClick(View widget) {

            // Prevent CheckBox state from being toggled when link is clicked
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                widget.cancelPendingInputEvents();
            }

            if (daysSelected.get(currentText) == 1) {
                daysSelected.put(currentText, 0);
            } else {
                daysSelected.put(currentText, 1);
            }
        }


        /**
         * Update Draw State
         * @param ds
         */
        @Override
        public void updateDrawState(TextPaint ds) {

            ds.setUnderlineText(hasUnderline);
            if(daysSelected.containsKey(currentText)) {
                boolean isSelected = daysSelected.get(currentText) == 1;
                ds.setColor(ContextCompat.getColor(appContext,
                        !isSelected ? R.color.cyanBrilliant : R.color.darkModerateBlue));

            }
        }

    }
}
