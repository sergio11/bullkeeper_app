package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Past;
import com.squareup.picasso.Picasso;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.bullkeeper.ui.support.components.SupportEditTextDatePicker;
import sanchez.sanchez.sergio.bullkeeper.utils.SupportImagePicker;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaStatusEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaTypeEnum;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerMyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.PhotoViewerDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpValidationMvpActivity;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import timber.log.Timber;

/**
 * My Kids Profile Activity
 */
public class MyKidsProfileMvpActivity extends SupportMvpValidationMvpActivity<MyKidsProfilePresenter, IMyKidsProfileView>
        implements HasComponent<MyKidsComponent>,
        IMyKidsProfileView, PhotoViewerDialog.IPhotoViewerListener {

    public enum KidProfileMode { ADD_NEW_SON_MODE, EDIT_CURRENT_SON_MODE }

    private final static String FIRST_NAME_FIELD_NAME = "first_name";
    private final static String LAST_NAME_FIELD_NAME = "last_name";
    private final static String BIRTHDATE_FIELD_NAME = "birthdate";
    private final static String SCHOOL_FIELD_NAME = "school";


    public static final String KIDS_IDENTITY_ARG = "KIDS_IDENTITY_ARG";

    private static final int MIN_AGE_ALLOWED = 8;
    private static final int MAX_AGE_ALLOWED = 18;

    protected MyKidsComponent myKidsComponent;

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
    @Past(dateFormatResId = R.string.date_format)
    protected SupportEditTextDatePicker birthdayInput;

    /**
     * School Input Layout
     */
    @BindView(R.id.schoolInputLayout)
    protected TextInputLayout schoolInputLayout;

    /**
     * School Input
     */
    @BindView(R.id.schoolInput)
    @NotEmpty(messageResId = R.string.school_not_empty_error)
    protected AppCompatEditText schoolInput;


    /**
     * Instagram Icon
     */
    @BindView(R.id.instagramIcon)
    protected ImageView instagramImageView;

    /**
     * Instagram Status
     */
    @BindView(R.id.instagramStatus)
    protected TextView instagramStatusTextView;

    /**
     * Instagram Switch Widget
     */
    @BindView(R.id.instagramSwitchWidget)
    protected SwitchCompat instagramSwitchWidget;

    /**
     * Facebook Icon
     */
    @BindView(R.id.facebookIcon)
    protected ImageView facebookIconImageView;

    /**
     * Facebook Status
     */
    @BindView(R.id.facebookStatus)
    protected TextView facebookStatusTextView;

    /**
     * Facebook Switch Widget
     */
    @BindView(R.id.facebookSwitchWidget)
    protected SwitchCompat facebookSwitchWidget;

    /**
     * Youtube Icon
     */
    @BindView(R.id.youtubeIcon)
    protected ImageView youtubeIconImageView;

    /**
     * Youtube Status
     */
    @BindView(R.id.youtubeStatus)
    protected TextView youtubeStatusTextView;

    /**
     * YoutubeSwitchWidget
     */
    @BindView(R.id.youtubeSwitchWidget)
    protected SwitchCompat youtubeSwitchWidget;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * Support Image Picker
     */
    @Inject
    protected SupportImagePicker supportImagePicker;


    /**
     * State
     */

    /**
     * My Kid Identity
     */
    private String myKidIdentity;

    /**
     * Current Image Path
     */
    private String currentImagePath;

    /**
     * First Name
     */
    private String firstName;

    /**
     * Last Name
     */
    private String lastName;

    /**
     * School
     */
    private String school;

    /**
     * Profile Mode
     */
    private KidProfileMode profileMode = KidProfileMode.ADD_NEW_SON_MODE;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity) {
        final Intent callingIntent = new Intent(context, MyKidsProfileMvpActivity.class);
        callingIntent.putExtra(KIDS_IDENTITY_ARG, identity);
        return callingIntent;
    }

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, MyKidsProfileMvpActivity.class);
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
     * Enable All Components
     */
    private void enableAllComponents(final boolean enable){
        nameInput.setEnabled(enable);
        surnameInput.setEnabled(enable);
        birthdayInput.setEnabled(enable);
        schoolInput.setEnabled(enable);
        instagramSwitchWidget.setEnabled(enable);
        facebookSwitchWidget.setEnabled(enable);
        youtubeSwitchWidget.setEnabled(enable);
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
            // Load Son Data
            getPresenter().loadSonData(myKidIdentity);
            // Disable All Components
            enableAllComponents(false);
        }

        myKidsProfileTitle.setText(getString(R.string.my_kids_profile_name_default));

        schoolInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    navigatorImpl.showSearchSchoolActivity();
            }
        });

    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        if(myKidIdentity != null && !myKidIdentity.isEmpty())
            args.putString(KIDS_IDENTITY_ARG, myKidIdentity);
        return args;
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
        String imagePathFromResult = supportImagePicker
                .getImagePathFromResult(requestCode, resultCode, data);
        if(imagePathFromResult != null) {
            currentImagePath = imagePathFromResult;
            Timber.d("Image Path -> %s", currentImagePath);
            profileImageView.setImageURI(Uri.parse(currentImagePath));
        }
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
        } else if(viewId.equals(R.id.schoolInput)) {
            schoolInputLayout.setError(message);
        }

    }

    /**
     * On Reset Errors
     */
    @Override
    protected void onResetErrors() {
        nameInputLayout.setError(null);
        surnameInputLayout.setError(null);
        birthdayInputLayout.setError(null);
        schoolInputLayout.setError(null);
    }

    /**
     * On Reset Fields
     */
    @Override
    protected void onResetFields() {
        super.onResetFields();
        nameInput.getText().clear();
        surnameInput.getText().clear();
        birthdayInput.setDateSelected(new Date());
        schoolInput.getText().clear();
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
     * On Click Profile Image
     */
    @OnClick(R.id.profileImage)
    protected void onClickProfileImage() {
        Timber.d("Show Photo -> %s", currentImagePath);

        if(currentImagePath != null) {
            navigatorImpl.showPhotoViewerDialog(this,
                    currentImagePath);
        } else {
            navigatorImpl.showPhotoViewerDialog(this,
                    R.drawable.kid_default_image);
        }
    }


    /**
     * On Long Profile Image Clicked
     */
    @OnLongClick(R.id.profileImage)
    protected boolean onLongProfileImageClicked(){
        supportImagePicker.pickImage(this,
                profileMode.equals(KidProfileMode.EDIT_CURRENT_SON_MODE) ?
                        String.format(Locale.getDefault(),
                                getString(R.string.change_profile_picture), firstName) :
                                getString(R.string.change_profile_picture_default));
        return true;
    }

    /**
     * On Validation Succeeded
     */
    @Override
    public void onValidationSucceeded() {
        super.onValidationSucceeded();

        final String name = nameInput.getText().toString();
        final String surname = surnameInput.getText().toString();
        final String birthday = birthdayInput.getDateSelectedAsText();

        getPresenter().saveSon(myKidIdentity, name, surname, birthday,
                "5b9e1db2082f6d1a400af3d5", currentImagePath);

    }

    /**
     * On Change Photo
     */
    @Override
    public void onChangePhoto() {
        supportImagePicker.pickImage(this,
                profileMode.equals(KidProfileMode.EDIT_CURRENT_SON_MODE) ?
                        String.format(Locale.getDefault(), getString(R.string.change_profile_picture), firstName) :
                                getString(R.string.change_profile_picture_default));
    }

    /**
     * On Instagram Row Clicked
     */
    @OnClick(R.id.instagramRow)
    protected void onInstagramRowClicked(){
        navigatorImpl.showSocialMediaStatusDialog(this, SocialMediaTypeEnum.INSTAGRAM,
                SocialMediaStatusEnum.DISABLED);
    }

    /**
     * On Facebook Row Clicked
     */
    @OnClick(R.id.facebookRow)
    protected void onFacebookRowClicked(){
        navigatorImpl.showSocialMediaStatusDialog(this, SocialMediaTypeEnum.FACEBOOK,
                SocialMediaStatusEnum.DISABLED);
    }


    /**
     * On Youtube Row Clicked
     */
    @OnClick(R.id.youtubeRow)
    protected void onYoutubeRowClicked(){
        navigatorImpl.showSocialMediaStatusDialog(this,
                SocialMediaTypeEnum.YOUTUBE,
                SocialMediaStatusEnum.DISABLED);
    }

    /**
     * On Son Profile Loaded
     * @param sonEntity
     */
    @Override
    public void onSonProfileLoaded(final SonEntity sonEntity) {
        Preconditions.checkNotNull(sonEntity, "Son Entity can not be null");

        Timber.d("Son Profile Image Url -> %s", sonEntity.getProfileImage());

        // Save Current State
        myKidIdentity = sonEntity.getIdentity();
        currentImagePath = sonEntity.getProfileImage();
        profileMode = KidProfileMode.EDIT_CURRENT_SON_MODE;

        myKidsProfileTitle.setText(String.format(getString(R.string.my_kids_profile_name), sonEntity.getFullName()));

        picasso.load(sonEntity.getProfileImage())
                .placeholder(R.drawable.kid_default_image)
                .error(R.drawable.kid_default_image)
                .noFade()
                .into(profileImageView);

        if(sonEntity.getFirstName() != null &&
                !sonEntity.getFirstName().isEmpty()) {
            firstName = sonEntity.getFirstName();
            nameInput.setText(sonEntity.getFirstName());
        }

        if(sonEntity.getLastName() != null &&
                !sonEntity.getLastName().isEmpty()) {
            lastName = sonEntity.getLastName();
            surnameInput.setText(sonEntity.getLastName());
        }

        if(sonEntity.getBirthdate() != null) {
            birthdayInput.setDateSelected(sonEntity.getBirthdate());
        }

        if(sonEntity.getSchool() != null) {
            school = sonEntity.getSchool().getIdentity();
            schoolInput.setText(sonEntity.getSchool().getName());
        }

        // Enable All Components
        enableAllComponents(true);
    }

    /**
     * On Social Media Loaded
     * @param socialMediaEntities
     */
    @Override
    public void onSocialMediaLoaded(List<SocialMediaEntity> socialMediaEntities) {
        Timber.d("On Social Media Loaded -> %d", socialMediaEntities.size());

        for(final SocialMediaTypeEnum socialMediaType: SocialMediaTypeEnum.values()) {

            SocialMediaEntity socialMedia = null;
            for(final SocialMediaEntity socialMediaEntity: socialMediaEntities) {
                if(socialMediaEntity.getType().equals(socialMediaType)){
                    socialMedia = socialMediaEntity;
                    break;
                }
            }

            int color; String socialMediaText;

            if(socialMedia != null) {

                if(socialMedia.hasInvalidToken()) {

                    color = ContextCompat.getColor(getApplicationContext(), R.color.redDanger);
                    socialMediaText = getString(R.string.social_media_is_not_valid);

                } else {

                    color = ContextCompat.getColor(getApplicationContext(), R.color.greenSuccess);
                    socialMediaText = getString(R.string.social_media_is_enabled);

                }


            } else {
                // Social Media not configured
                color = ContextCompat.getColor(getApplicationContext(), R.color.yellowWarning);
                socialMediaText = getString(R.string.social_media_is_not_enabled);

            }

            switch (socialMediaType) {

                case YOUTUBE:

                    youtubeStatusTextView.setText(socialMediaText);
                    youtubeStatusTextView.setTextColor(color);
                    break;

                case INSTAGRAM:

                    instagramStatusTextView.setText(socialMediaText);
                    instagramStatusTextView.setTextColor(color);
                    break;


                case FACEBOOK:

                    facebookStatusTextView.setText(socialMediaText);
                    facebookStatusTextView.setTextColor(color);
                    break;

            }


        }
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
                case FIRST_NAME_FIELD_NAME:
                    nameInputLayout.setError(error.get("message"));
                    break;
                case LAST_NAME_FIELD_NAME:
                    surnameInputLayout.setError(error.get("message"));
                    break;
                case BIRTHDATE_FIELD_NAME:
                    birthdayInputLayout.setError(error.get("message"));
                    break;
                case SCHOOL_FIELD_NAME:
                    schoolInputLayout.setError(error.get("message"));
                    break;
            }

        }

        showNoticeDialog(R.string.forms_is_not_valid);
    }


    @Override
    public Boolean hasPendingChanges() {
        return super.hasPendingChanges();
    }

    @Override
    public void onSavedPendingChanges() {
        super.onSavedPendingChanges();
    }

    @Override
    public void onDiscardPendingChanges() {
        super.onDiscardPendingChanges();
    }
}
