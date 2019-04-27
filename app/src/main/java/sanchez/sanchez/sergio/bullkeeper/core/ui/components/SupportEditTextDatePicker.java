package sanchez.sanchez.sergio.bullkeeper.core.ui.components;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import icepick.Icepick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import timber.log.Timber;

/**
 * Support Edit Text Date Picker
 */
public final class SupportEditTextDatePicker extends AppCompatEditText
        implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private final static int MIN_AGE_DEFAULT = 5;
    private final static int MAX_AGE_DEFAULT = 18;

    private Calendar calendar;
    private SimpleDateFormat sdf;

    private DatePickerDialog datePickerDialog;

    /**
     * State
     */

    /**
     * Date Selected
     */
    @State
    protected Date dateSelected;

    /**
     * Min Age
     */
    @State
    protected int minAge = MIN_AGE_DEFAULT;

    /**
     * Max Age
     */
    @State
    protected int maxAge  = MAX_AGE_DEFAULT;

    @State
    protected boolean isDirty = false;


    public SupportEditTextDatePicker(Context context) {
        super(context);
        init();
    }

    public SupportEditTextDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SupportEditTextDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
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

        sdf = new SimpleDateFormat(getContext().getString(R.string.date_format),
                Locale.getDefault());
        this.setClickable(true);
        this.setLines(1);
        this.setFocusable(false);
        this.setOnClickListener(this);
        updateCurrentDateSelected();

    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
        updateCurrentDateSelected();
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public Date getDateSelected() {
        return dateSelected;
    }

    public String getDateSelectedAsText() {
        return sdf.format(dateSelected);
    }

    /**
     *
     * @param dateSelected
     */
    public void setDateSelected(Date dateSelected, boolean cleanCurrentSelected) {
        if(!isDirty || cleanCurrentSelected) {
            Timber.d("Clean Current Selected");
            this.dateSelected = dateSelected;
            calendar.setTime(this.dateSelected);
            updateText();
        } else {
            Timber.d("Not Clean Current Selected");
        }
    }

    /**
     *
     * @param dateSelected
     */
    public void setDateSelected(Date dateSelected) {
        setDateSelected(dateSelected, false);
    }

    /**
     * Reset Date Selected
     */
    public void resetDateSelected(){
        dateSelected = null;
    }

    /**
     * Update Current Date Selected
     */
    private void updateCurrentDateSelected(){
        calendar = Calendar.getInstance();
        if(dateSelected != null) {
            calendar.setTime(dateSelected);
        } else {
            calendar.add(Calendar.YEAR, -minAge);
            dateSelected = calendar.getTime();
        }
        updateText();
    }

    /**
     * Update Text
     */
    private void updateText(){
        setText(String.format(getContext().getString(R.string.birthday_selected),
                sdf.format(dateSelected), getAge()));
    }

    /**
     * Create Date Picker Dialog
     * @param minAge
     * @param maxAge
     * @param dateSetListener
     * @return
     */
    private DatePickerDialog createDatePickerDialog(int minAge, int maxAge, final DatePickerDialog.OnDateSetListener dateSetListener) {

        final Calendar maxAgeCalendar = Calendar.getInstance();
        maxAgeCalendar.add(Calendar.YEAR, -minAge);
        final long maxDate = maxAgeCalendar.getTimeInMillis();

        final Calendar minAgeCalendar = Calendar.getInstance();
        minAgeCalendar.add(Calendar.YEAR, -maxAge);
        final long minDate = minAgeCalendar.getTimeInMillis();

        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(), R.style.CommonDatePickerStyle, dateSetListener,
                maxAgeCalendar.get(Calendar.YEAR), maxAgeCalendar.get(Calendar.MONTH),
                maxAgeCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setMinDate(minDate);
        datePickerDialog.getDatePicker().setMaxDate(maxDate);

        return datePickerDialog;
    }

    /**
     * Show Date Picker Dialog
     */
    private void showDatePickerDialog(){
        datePickerDialog = createDatePickerDialog(minAge, maxAge, this);
        datePickerDialog.updateDate(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     * Get Age
     * @return
     */
    private int getAge(){
        final Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < calendar.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                        today.get(Calendar.DAY_OF_MONTH) < calendar.get(Calendar.DAY_OF_MONTH))){
            age--;
        }
        return age;
    }

    /**
     * On Click
     * @param v
     */
    @Override
    public void onClick(View v) {
        showDatePickerDialog();
    }

    /**
     * On Date Set
     * @param view
     * @param year
     * @param month
     * @param dayOfMonth
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dateSelected = calendar.getTime();
        isDirty = true;
        updateText();
    }

}
