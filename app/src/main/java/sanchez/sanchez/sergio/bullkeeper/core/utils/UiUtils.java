package sanchez.sanchez.sergio.bullkeeper.core.utils;

import android.app.DatePickerDialog;
import android.content.Context;

import java.util.Calendar;

import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Ui Utils
 */
public final class UiUtils {

    private final Context appContext;

    public UiUtils(Context appContext) {
        this.appContext = appContext;
    }

    /**
     * Create Birthday Data Picker Dialog
     * @param minAge
     * @param maxAge
     * @return
     */
    public DatePickerDialog createBirthdayDataPickerDialog(final Context activityContext, int minAge, int maxAge, final DatePickerDialog.OnDateSetListener dateSetListener) {

        final Calendar maxAgeCalendar = Calendar.getInstance();
        maxAgeCalendar.add(Calendar.YEAR, -minAge);
        final long maxDate = maxAgeCalendar.getTimeInMillis();

        final Calendar minAgeCalendar = Calendar.getInstance();
        minAgeCalendar.add(Calendar.YEAR, -maxAge);
        final long minDate = minAgeCalendar.getTimeInMillis();

        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                activityContext, R.style.CommonDatePickerStyle, dateSetListener,
                maxAgeCalendar.get(Calendar.YEAR), maxAgeCalendar.get(Calendar.MONTH),
                maxAgeCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setMinDate(minDate);
        datePickerDialog.getDatePicker().setMaxDate(maxDate);

        return datePickerDialog;
    }

}
