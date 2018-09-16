package sanchez.sanchez.sergio.bullkeeper.ui.activity.userprofile;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Past;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerUserProfileComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.UserProfileComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.PhotoViewerDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpValidationMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.utils.SupportImagePicker;
import sanchez.sanchez.sergio.bullkeeper.utils.SupportImagePickerOld;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import timber.log.Timber;

/**
 * User Profile Activity
 */
public class UserProfileMvpActivity extends SupportMvpValidationMvpActivity<UserProfilePresenter, IUserProfileView>
        implements HasComponent<UserProfileComponent>, IUserProfileView,
        PhotoViewerDialog.IPhotoViewerListener, DatePickerDialog.OnDateSetListener{

    private final static String FIRST_NAME_FIELD_NAME = "first_name";
    private final static String LAST_NAME_FIELD_NAME = "last_name";
    private final static String BIRTHDATE_FIELD_NAME = "birthdate";
    private final static String EMAIL_FIELD_NAME = "email";
    private final static String TELEPHONE_FIELD_NAME = "telephone";

    private static final int MIN_AGE_ALLOWED = 18;
    private static final int MAX_AGE_ALLOWED = 90;

    /**
     * User Profile Component
     */
    private UserProfileComponent userProfileComponent;


    private String currentImagePath;

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
     * Birthday Input
     */
    @BindView(R.id.birthdayInput)
    @NotEmpty(messageResId = R.string.birthday_not_empty_error)
    @Past(dateFormatResId = R.string.date_format)
    protected AppCompatEditText birthdayInput;

    /**
     * Email Input Layout
     */
    @BindView(R.id.emailInputLayout)
    protected TextInputLayout emailInputLayout;

    /**
     * EMail Input
     */
    @BindView(R.id.emailInput)
    @NotEmpty(messageResId = R.string.email_not_empty_error)
    @Email(messageResId = R.string.email_invalid_error)
    protected AppCompatEditText emailInput;

    /**
     * Tfno Input Layout
     */
    @BindView(R.id.tfnoInputLayout)
    protected TextInputLayout tfnoInputLayout;

    /**
     * Tfno Input
     */
    @BindView(R.id.tfnoInput)
    protected AppCompatEditText tfnoInput;


    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    @Inject
    protected SupportImagePicker supportImagePicker;

    /**
     * Date Picker Dialog
     */
    private DatePickerDialog datePickerDialog;


    private ParentEntity parentEntity;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, UserProfileMvpActivity.class);
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {

        userProfileComponent = DaggerUserProfileComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

        userProfileComponent.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public UserProfilePresenter providePresenter() {
        return userProfileComponent.userProfilePresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public UserProfileComponent getComponent() {
        return userProfileComponent;
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
        return R.layout.activity_user_profile;
    }

    /**
     * On View Ready
     */
    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        // width and height will be at least 300px long (optional).
        SupportImagePickerOld.setMinQuality(300, 300);

        datePickerDialog = uiUtils.createBirthdayDataPickerDialog(this,
                MIN_AGE_ALLOWED, MAX_AGE_ALLOWED, this);
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

        // Load Profile Info
        getPresenter().loadProfileInfo();
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
        } else if(viewId.equals(R.id.emailInput)) {
            emailInputLayout.setError(message);
        } else if(viewId.equals(R.id.tfnoInput)) {
            tfnoInputLayout.setError(message);
        }
    }

    /**
     * ON Validation Succeded
     */
    @Override
    public void onValidationSucceeded() {
        super.onValidationSucceeded();

        final String name = nameInput.getText().toString();
        final String surname = surnameInput.getText().toString();
        final String birthday = birthdayInput.getText().toString();
        final String email = emailInput.getText().toString();
        final String tfno = getString(R.string.tfno_prefix).concat(tfnoInput.getText().toString());


        // Update Profile
        if(currentImagePath != null) {
            getPresenter().updateProfile(name, surname, birthday, email, tfno, currentImagePath);
        } else {
            getPresenter().updateProfile(name, surname, birthday, email, tfno);
        }


    }

    /**
     * On Reset Fields
     */
    @Override
    protected void onResetFields() {
        super.onResetFields();

        nameInput.setText(parentEntity.getFirstName());
        surnameInput.setText(parentEntity.getLastName());
        SimpleDateFormat format = new SimpleDateFormat(getString(R.string.date_format));
        final String birthdateformated =  format.format(parentEntity.getBirthdate());
        birthdayInput.setText(birthdateformated);
        emailInput.setText(parentEntity.getEmail());
        tfnoInput.setText(parentEntity.getPhone());
    }

    /**
     * On Reset Errors
     */
    @Override
    protected void onResetErrors() {

        nameInputLayout.setError("");
        surnameInputLayout.setError("");
        birthdayInputLayout.setError("");
        emailInputLayout.setError("");
        tfnoInputLayout.setError("");
    }

    /**
     * On Save Changes
     */
    @OnClick(R.id.saveChanges)
    protected void onSaveChanges(){
        validate();
    }

    /**
     * On Delete account
     */
    @OnClick(R.id.deleteAccount)
    protected void onDeleteAccount(){


        showConfirmationDialog(R.string.user_profile_delete_account_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {

            @Override
            public void onAccepted(DialogFragment dialog) {

                getPresenter().deleteAccount();

            }

            @Override
            public void onRejected(DialogFragment dialog) {

            }
        });

    }

    /**
     * On Click Profile Image
     */
    @OnClick(R.id.profileImage)
    protected void onClickProfileImage() {
        navigatorImpl.showPhotoViewerDialog(this,
                parentEntity.getProfileImage());
    }


    /**
     * On Long Profile Image Clicked
     */
    @OnLongClick(R.id.profileImage)
    protected boolean onLongProfileImageClicked(){
        supportImagePicker.pickImage(this, String.format(Locale.getDefault(),
                getString(R.string.change_profile_picture), parentEntity.getFullName()));
        return true;
    }

    /**
     * On Show Detail
     */
    @Override
    public void onShowDetail() {
        navigatorImpl.navigateToHome();
    }

    /**
     * On Change Photo
     */
    @Override
    public void onChangePhoto() {

        if(permissionManager.shouldAskPermission(Manifest.permission.CAMERA))
            permissionManager.checkSinglePermission(Manifest.permission.CAMERA, "Title", "Description");
        else
            supportImagePicker.pickImage(this, String.format(Locale.getDefault(),
                    getString(R.string.change_profile_picture), parentEntity.getFullName()));

    }

    /**
     * On Single Permission Granted
     * @param permission
     */
    @Override
    public void onSinglePermissionGranted(String permission) {
        super.onSinglePermissionGranted(permission);

        if(permission.equalsIgnoreCase(Manifest.permission.CAMERA)) {
            supportImagePicker.pickImage(this, String.format(Locale.getDefault(),
                    getString(R.string.change_profile_picture), parentEntity.getFullName()));
        }

    }

    /**
     * On Single Permission Rejected
     * @param permission
     */
    @Override
    public void onSinglePermissionRejected(String permission) {
        super.onSinglePermissionRejected(permission);

        if(permission.equalsIgnoreCase(Manifest.permission.CAMERA)) {
            supportImagePicker.pickImage(this, String.format(Locale.getDefault(),
                    getString(R.string.change_profile_picture), parentEntity.getFullName()), true);
        }

    }

    /**
     * On Activity Result
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final String imagePathFromResult = supportImagePicker.getImagePathFromResult(requestCode, resultCode, data);
        if(imagePathFromResult != null) {
            Timber.d("Image Path -> %s", imagePathFromResult);
            currentImagePath = imagePathFromResult;
            profileImageView.setImageURI(Uri.parse(imagePathFromResult));
        }
    }

    /**
     * Update Profile Form
     */
    private void updateProfileForm() {

        if(parentEntity.getFirstName() != null &&
                !parentEntity.getFirstName().isEmpty())
            nameInput.setText(parentEntity.getFirstName());

        if(parentEntity.getLastName() != null &&
                !parentEntity.getLastName().isEmpty())
            surnameInput.setText(parentEntity.getLastName());

        if (parentEntity.getEmail() != null &&
                !parentEntity.getEmail().isEmpty())
            emailInput.setText(parentEntity.getEmail());


        if(parentEntity.getPhone() != null && !parentEntity.getPhone().isEmpty())
            tfnoInput.setText(parentEntity.getPhoneNumber());

        if(parentEntity.getBirthdate() != null) {
            SimpleDateFormat format = new SimpleDateFormat(getString(R.string.date_format));
            final String birthdateFormated =  format.format(parentEntity.getBirthdate());
            birthdayInput.setText(birthdateFormated);

            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(parentEntity.getBirthdate());
            datePickerDialog.getDatePicker().updateDate(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }

        // Reset Current Image Path
        currentImagePath = null;

        Timber.d("Profile Image -> %s", parentEntity.getProfileImage());

        picasso.load(parentEntity.getProfileImage())
                .placeholder(R.drawable.parent_default)
                .error(R.drawable.parent_default)
                .noFade()
                .into(profileImageView);
    }

    /**
     * On Self Information Loaded
     * @param parentEntity
     */
    @Override
    public void onSelfInformationLoaded(final ParentEntity parentEntity) {
        this.parentEntity = parentEntity;
        updateProfileForm();
    }

    /**
     * On Self Information Update
     * @param parentEntity
     */
    @Override
    public void onSelfInformationUpdate(final ParentEntity parentEntity) {
        this.parentEntity = parentEntity;
        updateProfileForm();

        showNoticeDialog(R.string.profile_information_updated_successfully);
    }

    /**
     * On Validations Errors
     * @param errors
     */
    @Override
    public void onValidationErrors(List<LinkedHashMap<String, String>> errors) {
        for (LinkedHashMap<String, String> error : errors) {

            Timber.d("Field -> %s, Message -> %s", error.get("field"), error.get("message"));

            switch (error.get("field")) {
                case EMAIL_FIELD_NAME:
                    emailInputLayout.setError(error.get("message"));
                    break;
                case FIRST_NAME_FIELD_NAME:
                    nameInputLayout.setError(error.get("message"));
                    break;
                case LAST_NAME_FIELD_NAME:
                    surnameInputLayout.setError(error.get("message"));
                    break;
                case BIRTHDATE_FIELD_NAME:
                    birthdayInputLayout.setError(error.get("message"));
                    break;
                case TELEPHONE_FIELD_NAME:
                    tfnoInputLayout.setError(error.get("message"));
                    break;
            }

        }

        showNoticeDialog(R.string.forms_is_not_valid);
    }

    /**
     * On Account Deleted
     */
    @Override
    public void onAccountDeleted() {

        showNoticeDialog(R.string.user_profile_delete_account_check_email, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeSession();
            }
        });

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

        SimpleDateFormat format = new SimpleDateFormat(getString(R.string.date_format),
                Locale.getDefault());
        String strDate = format.format(calendar.getTime());
        // Set Data format
        birthdayInput.setText(strDate);
    }
}
