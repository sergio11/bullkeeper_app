package sanchez.sanchez.sergio.masom_app.ui.activity.mykidsprofile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Past;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerMyKidsComponent;
import sanchez.sanchez.sergio.masom_app.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportToolbarApp;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportValidationActivity;

/**
 * My Kids Profile Activity
 */
public class MyKidsProfileActivity extends SupportValidationActivity<MyKidsProfilePresenter, IMyKidsProfileView>
        implements HasComponent<MyKidsComponent>, IMyKidsProfileView, DatePickerDialog.OnDateSetListener {

    protected MyKidsComponent myKidsComponent;

    public static final String KIDS_IDENTITY_ARG = "KIDS_IDENTITY_ARG";

    /**
     * My Kid Identity
     */
    private String myKidIdentity;

    /**
     * My Kids Profile Title
     */
    @BindView(R.id.myKidsProfileTitle)
    protected TextView myKidsProfileTitle;

    /**
     * Profile Image View
     */
    @BindView(R.id.profileImage)
    protected CircleImageView profileImageView;

    /**
     * Name Input Layout
     */
    @BindView(R.id.nameInputLayout)
    protected TextInputLayout nameInputLayout;

    /**
     * Name Input
     */
    @BindView(R.id.nameInput)
    @NotEmpty(messageResId = R.string.name_not_empty_error)
    @Length(min = 3, max = 15)
    protected AppCompatEditText nameInput;

    /**
     * Surname Input Layout
     */
    @BindView(R.id.surnameInputLayout)
    protected TextInputLayout surnameInputLayout;

    /**
     * Surname Input
     */
    @BindView(R.id.surnameInput)
    @NotEmpty(messageResId = R.string.surname_not_empty_error)
    @Length(min = 3, max = 15)
    protected AppCompatEditText surnameInput;

    /**
     * Birthday Input Layout
     */
    @BindView(R.id.birthdayInputLayout)
    protected TextInputLayout birthdayInputLayout;

    /**
     * BirthdayInput
     */
    @BindView(R.id.birthdayInput)
    @NotEmpty(messageResId = R.string.birthday_not_empty_error)
    @Past(dateFormat = "yyyy/MM/dd")
    protected AppCompatEditText birthdayInput;

    /**
     * Instagram Switch Widget
     */
    @BindView(R.id.instagramSwitchWidget)
    protected SwitchCompat instagramSwitchWidget;

    /**
     * Facebook Switch Widget
     */
    @BindView(R.id.facebookSwitchWidget)
    protected SwitchCompat facebookSwitchWidget;


    /**
     * YoutubeSwitchWidget
     */
    @BindView(R.id.youtubeSwitchWidget)
    protected SwitchCompat youtubeSwitchWidget;


    /**
     * Date Picker Dialog
     */
    private DatePickerDialog datePickerDialog;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity) {
        final Intent callingIntent = new Intent(context, MyKidsProfileActivity.class);
        callingIntent.putExtra(KIDS_IDENTITY_ARG, identity);
        return callingIntent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.myKidsComponent = DaggerMyKidsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.myKidsComponent.inject(this);
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return SupportToolbarApp.RETURN_TOOLBAR;
    }

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_kids_profile;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        if(getIntent() != null && getIntent().hasExtra(KIDS_IDENTITY_ARG)) {
            // Get Kid identity
            myKidIdentity = getIntent().getStringExtra(KIDS_IDENTITY_ARG);
        }


        myKidsProfileTitle.setText(String.format(getString(R.string.my_kids_profile_name), "Sergio Sánchez"));


        Integer START_YEAR = 1970;
        Integer START_MONTH = 1;
        Integer START_DAY = 1;

        datePickerDialog = new DatePickerDialog(
                this, R.style.CommonDatePickerStyle, this, START_YEAR,
                START_MONTH, START_DAY);

        // On Focus Listener
        birthdayInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if(datePickerDialog != null) {
                    if(hasFocus)
                        datePickerDialog.show();
                    else
                        datePickerDialog.dismiss();
                }
            }
        });

        // On Click Listener
        birthdayInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(datePickerDialog != null)
                    datePickerDialog.show();
            }
        });

        Picasso.with(getApplicationContext()).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                .placeholder(R.drawable.user_default)
                .error(R.drawable.user_default)
                .noFade()
                .into(profileImageView);


    }

    /**
     * Get Component
     * @return
     */
    @Override
    public MyKidsComponent getComponent() {
        return myKidsComponent;
    }

    /**
     * On Validation Failed
     */
    @Override
    protected void onValidationFailed() {
        showNoticeDialog(R.string.forms_is_not_valid);
    }


    /**
     * On Field Invalid
     * @param viewId
     * @param message
     */
    @Override
    protected void onFieldInvalid(Integer viewId, String message) {

        if (viewId.equals(R.id.nameInput)) {
            nameInputLayout.setError(message);
        } else if(viewId.equals(R.id.surnameInput)) {
            surnameInputLayout.setError(message);
        } else if(viewId.equals(R.id.birthdayInput)) {
            birthdayInputLayout.setError(message);
        }

    }

    @Override
    protected void onResetErrors() {

        nameInputLayout.setError("");
        surnameInputLayout.setError("");
        birthdayInputLayout.setError("");
    }

    @Override
    protected void onResetFields() {
        super.onResetFields();

        nameInput.setText("");
        surnameInput.setText("");
        birthdayInput.setText("");

    }

    /**
     * My Kids Profile Presenter
     * @return
     */
    @NonNull
    @Override
    public MyKidsProfilePresenter providePresenter() {
        return myKidsComponent.myKidsProfilePresenter();
    }

    /**
     * On Save Changes
     */
    @OnClick(R.id.saveChanges)
    protected void onSaveChanges(){
        validate();
    }


    /**
     * On Data Set
     * @param datePicker
     * @param year
     * @param month
     * @param day
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd",
                Locale.getDefault());
        String strDate = format.format(calendar.getTime());
        // Set Data format
        birthdayInput.setText(strDate);
    }

    /**
     * On Validation Succeeded
     */
    @Override
    public void onValidationSucceeded() {
        super.onValidationSucceeded();

        final String name = nameInput.getText().toString();
        final String surname = surnameInput.getText().toString();
        final String birthday = birthdayInput.getText().toString();
    }
}
