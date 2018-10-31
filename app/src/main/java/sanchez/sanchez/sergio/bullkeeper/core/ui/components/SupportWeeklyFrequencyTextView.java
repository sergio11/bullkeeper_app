package sanchez.sanchez.sergio.bullkeeper.core.ui.components;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatTextView;

import com.fernandocejas.arrow.checks.Preconditions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.utils.SimpleSpanBuilder;

/**
 * Support Weekly Frequency Text View
 */
public final class SupportWeeklyFrequencyTextView extends AppCompatTextView {


    /**
     * Days Of Week
     */
    private String[] daysOfWeek;

    /**
     *
     * @param context
     */
    public SupportWeeklyFrequencyTextView(final Context context) {
        super(context);
        init(context);
    }

    /**
     *
     * @param context
     * @param attrs
     */
    public SupportWeeklyFrequencyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SupportWeeklyFrequencyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * Get Days of Current Week
     * @return
     */
    private String[] getDaysOfCurrentWeek(){
        final DateFormat format = new SimpleDateFormat("EEEE", Locale.getDefault());
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

        // Get Days of Current Week
        daysOfWeek = getDaysOfCurrentWeek();

        final SimpleSpanBuilder ssb = new SimpleSpanBuilder();
        for(final String dayOfWeek: daysOfWeek) {
            ssb.appendWithSpace(dayOfWeek, new ForegroundColorSpan(ContextCompat.getColor(context,
                    R.color.commonWhite)));
        }

        setText(ssb.build());
        setHighlightColor(Color.TRANSPARENT);
    }


    /**
     *
     * @param daysOfWeekSelected
     */
    public void setDaysOfWeekSelected(final int[] daysOfWeekSelected) {
        Preconditions.checkNotNull(daysOfWeekSelected, "Days of week can not be null");
        Preconditions.checkState(daysOfWeekSelected.length == 7, "You must specify 7 values");

        final SimpleSpanBuilder ssb = new SimpleSpanBuilder();
        for(int i = 0; i < daysOfWeek.length; i++) {
            final @ColorRes int dayColor =
                    daysOfWeekSelected[i] == 1 ? R.color.darkModerateBlue: R.color.commonWhite;
            ssb.appendWithSpace(daysOfWeek[i], new ForegroundColorSpan(ContextCompat.getColor(getContext(),
                    dayColor)));
        }

        setText(ssb.build());
        setHighlightColor(Color.TRANSPARENT);

    }
}
