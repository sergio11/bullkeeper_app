package sanchez.sanchez.sergio.masom_app.ui.activity.userprofile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Past;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerUserProfileComponent;
import sanchez.sanchez.sergio.masom_app.di.components.UserProfileComponent;
import sanchez.sanchez.sergio.masom_app.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportToolbarApp;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportValidationActivity;

/**
 * User Profile Activity
 */
public class UserProfileActivity extends SupportValidationActivity<UserProfilePresenter, IUserProfileView>
        implements HasComponent<UserProfileComponent>, IUserProfileView  {


    /**
     * User Profile Component
     */
    private UserProfileComponent userProfileComponent;


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
    @Past(dateFormat = "yyyy/MM/dd")
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
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, UserProfileActivity.class);
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
        Picasso.with(getApplicationContext()).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                .placeholder(R.drawable.user_default)
                .error(R.drawable.user_default)
                .noFade()
                .into(profileImageView);
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
        final String tfno = tfnoInput.getText().toString();
        // Update Profile
        getPresenter().updateProfile(name, surname, birthday, email, tfno);
    }

    /**
     * On Reset Fields
     */
    @Override
    protected void onResetFields() {
        super.onResetFields();

        nameInput.setText("");
        surnameInput.setText("");
        birthdayInput.setText("");
        emailInput.setText("");
        tfnoInput.setText("");
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

                showNoticeDialog(R.string.user_profile_delete_account_check_email, new NoticeDialogFragment.NoticeDialogListener() {
                    @Override
                    public void onAccepted(DialogFragment dialog) {
                        closeActivity();
                    }
                });

            }

            @Override
            public void onRejected(DialogFragment dialog) {

            }
        });

    }

}
