package sanchez.sanchez.sergio.bullkeeper.core.ui.components;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import icepick.Icepick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Support Edit Text Time Picker
 */
public final class SupportEditTextTimePicker extends AppCompatEditText
        implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private final static int INITIAL_HOUR_DEFAULT = 0;
    private final static int INITIAL_MINUTE_DEFAULT = 0;

    /**
     * Date Time Formatter
     */
    private final DateTimeFormatter fmt = DateTimeFormat.forPattern("hh:mm a");

    /**
     * Time Picker Dialog
     */
    private TimePickerDialog timePickerDialog;

    /**
     * State
     */

    /**
     * Initial Hour
     */
    @State
    protected int initialHour = INITIAL_HOUR_DEFAULT;

    /**
     * Initial Minute
     */
    @State
    protected int initialMinute  = INITIAL_MINUTE_DEFAULT;

    /**
     * Current Local Date Time
     */
    @State
    protected LocalDateTime currentLocalDateTime;

    /**
     * Is Dirty
     */
    @State
    protected boolean isDirty = false;


    public SupportEditTextTimePicker(Context context) {
        super(context);
        init();
    }

    public SupportEditTextTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SupportEditTextTimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * On Save Instance State
     * @return
     */
    @Override public Parcelable onSaveInstanceState() {
        return Icepick.saveInstanceState(this, super.onSaveInstanceState());
    }

    /**
     * On Restore Instance State
     * @param state
     */
    @Override public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Icepick.restoreInstanceState(this, state));
    }

    /**
     * Init
     */
    private void init(){
        this.setClickable(true);
        this.setLines(1);
        this.setFocusable(false);
        this.setOnClickListener(this);

    }

    public int getInitialHour() {
        return initialHour;
    }

    public void setInitialHour(int initialHour) {
        this.initialHour = initialHour;
    }

    public int getInitialMinute() {
        return initialMinute;
    }

    public void setInitialMinute(int initialMinute) {
        this.initialMinute = initialMinute;
    }

    public LocalDateTime getCurrentLocalDateTime() {
        return currentLocalDateTime;
    }

    /**
     * @param localDateTimeSelected
     * @param cleanCurrentSelected
     */
    public void setCurrentLocalDateTime(final LocalDateTime localDateTimeSelected, boolean cleanCurrentSelected) {
        if(!isDirty || cleanCurrentSelected) {
            this.currentLocalDateTime = localDateTimeSelected;
            updateText();
        }
    }


    /**
     * Update Text
     */
    private void updateText(){
        setText(currentLocalDateTime.toString(fmt));
    }

    /**
     * Create Time Picker Dialog
     * @return
     */
    private TimePickerDialog createTimePickerDialog(final int initialHour, final int initialMinute,
                                                    final TimePickerDialog.OnTimeSetListener timeSetListener) {

        final TimePickerDialog timePickerDialog = new TimePickerDialog(
                getContext(), R.style.CommonDatePickerStyle, timeSetListener,initialHour , initialMinute, true);

        return timePickerDialog;
    }

    /**
     * Show Date Picker Dialog
     */
    private void showTimePickerDialog(){
        timePickerDialog = createTimePickerDialog(initialHour, initialMinute, this);
        timePickerDialog.show();
    }

    /**
     * On Click
     * @param v
     */
    @Override
    public void onClick(View v) {
        showTimePickerDialog();
    }


    /**
     * On Time Set
     * @param view
     * @param hourOfDay
     * @param minute
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        currentLocalDateTime =  new LocalDateTime()
                .withHourOfDay(hourOfDay)
                .withMinuteOfHour(minute);
        isDirty = true;
        updateText();
    }
}
