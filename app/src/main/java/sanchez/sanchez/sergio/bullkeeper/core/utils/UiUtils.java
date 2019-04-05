package sanchez.sanchez.sergio.bullkeeper.core.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Calendar;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.anim.CommonBounceInterpolator;

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

    /**
     * Start Bounce Animation For View
     * @param view
     */
    public void startBounceAnimationForView(final View view){
        final Animation bounceAnimation = AnimationUtils.loadAnimation(appContext, R.anim.bounce);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        CommonBounceInterpolator interpolator = new CommonBounceInterpolator(0.2, 20);
        bounceAnimation.setInterpolator(interpolator);
        view.startAnimation(bounceAnimation);
    }

}
