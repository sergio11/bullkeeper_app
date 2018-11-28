package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.jaychang.sa.AuthCallback;
import com.jaychang.sa.SocialUser;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Past;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import de.hdodenhof.circleimageview.CircleImageView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search.SearchSchoolMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportEditTextDatePicker;
import sanchez.sanchez.sergio.bullkeeper.core.utils.SupportImagePicker;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaStatusEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaTypeEnum;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerMyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.PhotoViewerDialog;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpValidationMvpActivity;
import timber.log.Timber;

/**
 * My Kids Profile Activity
 */
public class MyKidsProfileMvpActivity extends SupportMvpValidationMvpActivity<MyKidsProfilePresenter, IMyKidsProfileView>
        implements HasComponent<MyKidsComponent>,
        IMyKidsProfileView, PhotoViewerDialog.IPhotoViewerListener {

    private final String CONTENT_FULL_NAME = "MY_KIDS_PROFILE";
    private final String CONTENT_TYPE_NAME = "KIDS";

    public final static int SELECT_SCHOOL_REQUEST_CODE = 266;

    public enum KidProfileMode { ADD_NEW_SON_MODE, EDIT_CURRENT_SON_MODE }

    private final static String FIRST_NAME_FIELD_NAME = "first_name";
    private final static String LAST_NAME_FIELD_NAME = "last_name";
    private final static String BIRTHDATE_FIELD_NAME = "birthdate";
    private final static String SCHOOL_FIELD_NAME = "school";

    public static final String KIDS_IDENTITY_ARG = "KID_IDENTITY_ARG";

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
    protected SupportSwitchCompat instagramSwitchWidget;

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
    protected SupportSwitchCompat facebookSwitchWidget;

    /**
     * Google Icon
     */
    @BindView(R.id.googleIcon)
    protected ImageView googleIconImageView;

    /**
     * Google Status
     */
    @BindView(R.id.googleStatus)
    protected TextView googleStatusTextView;

    /**
     * Google Switch Widget
     */
    @BindView(R.id.googleSwitchWidget)
    protected SupportSwitchCompat googleSwitchWidget;

    /**
     * Show School Detail Image View
     */
    @BindView(R.id.showSchoolDetail)
    protected ImageView showSchoolDetailImageView;

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
    @State
    protected String myKidIdentity;

    /**
     * Current Image Path
     */
    @State
    protected String currentImagePath;

    /**
     * First Name
     */
    @State
    protected String firstName;

    /**
     * Last Name
     */
    @State
    protected String lastName;

    /**
     * School
     */
    @State
    protected SchoolEntity school;

    /**
     * Profile Mode
     */
    @State
    protected KidProfileMode profileMode = KidProfileMode.ADD_NEW_SON_MODE;

    /**
     * Social Medias
     */
    @State
    protected ArrayList<SocialMediaEntity> socialMedias = new ArrayList<>();

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
     * Toggle All Components
     */
    private void toggleAllComponents(final boolean isEnable){
        toggleAllProfileComponents(isEnable);
        toggleAllSocialMediaComponents(isEnable);
    }

    /**
     * Toggle All Profile Components
     * @param isEnable
     */
    private void toggleAllProfileComponents(final boolean isEnable) {
        myKidsProfileTitle.setEnabled(isEnable);
        profileImageView.setEnabled(isEnable);
        nameInputLayout.setEnabled(isEnable);
        nameInput.setEnabled(isEnable);
        surnameInputLayout.setEnabled(isEnable);
        surnameInput.setEnabled(isEnable);
        birthdayInputLayout.setEnabled(isEnable);
        birthdayInput.setEnabled(isEnable);
        schoolInput.setEnabled(isEnable);
        schoolInputLayout.setEnabled(isEnable);
        showSchoolDetailImageView.setEnabled(isEnable);
    }

    /**
     * Toogle All Social Media Components
     * @param isEnable
     */
    private void toggleAllSocialMediaComponents(final boolean isEnable) {
        instagramSwitchWidget.setEnabled(isEnable);
        instagramImageView.setEnabled(isEnable);
        instagramStatusTextView.setEnabled(isEnable);
        facebookSwitchWidget.setEnabled(isEnable);
        facebookIconImageView.setEnabled(isEnable);
        facebookStatusTextView.setEnabled(isEnable);
        googleSwitchWidget.setEnabled(isEnable);
        googleIconImageView.setEnabled(isEnable);
        googleStatusTextView.setEnabled(isEnable);
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        // Toggle All Components
        toggleAllComponents(false);

        if(getIntent() != null && getIntent().hasExtra(KIDS_IDENTITY_ARG)) {
            // Get Kid identity
            myKidIdentity = getIntent().getStringExtra(KIDS_IDENTITY_ARG);
            // Load Son Data
            getPresenter().loadSonData(myKidIdentity);
        }

        myKidsProfileTitle.setText(getString(R.string.my_kids_profile_name_default));

        schoolInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigatorImpl.showSearchSchoolActivity(MyKidsProfileMvpActivity.this,
                        SELECT_SCHOOL_REQUEST_CODE);
            }
        });

        birthdayInput.setMinAge(MIN_AGE_ALLOWED);
        birthdayInput.setMaxAge(MAX_AGE_ALLOWED);


        /**
         * Facebook Switch Widget
         */
        facebookSwitchWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    enableFacebookSocialMedia();
                } else {
                    disableSocialMedia(SocialMediaTypeEnum.FACEBOOK);
                }
            }
        });

        /**
         * Instagram Switch Widget
         */
        instagramSwitchWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    enableInstagramSocialMedia();
                } else {
                    disableSocialMedia(SocialMediaTypeEnum.INSTAGRAM);
                }
            }
        });

        /**
         * Google Switch Widget
         */
        googleSwitchWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    enableGoogleSocialMedia();
                } else {
                    disableSocialMedia(SocialMediaTypeEnum.YOUTUBE);
                }
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
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
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

        if(SupportImagePicker.DEFAULT_REQUEST_CODE == requestCode) {

            String imagePathFromResult = supportImagePicker
                    .getImagePathFromResult(requestCode, resultCode, data);
            if(imagePathFromResult != null) {
                currentImagePath = imagePathFromResult;
                Timber.d("Image Path -> %s", currentImagePath);
                profileImageView.setImageURI(Uri.parse(currentImagePath));
            }

        } else if(SELECT_SCHOOL_REQUEST_CODE == requestCode) {

            if(resultCode == Activity.RESULT_OK){
                final SchoolEntity schoolSelected =
                        (SchoolEntity) data.getSerializableExtra(SearchSchoolMvpActivity.SCHOOL_SELECTED_ARG);
                school = schoolSelected;
                schoolInput.setText(schoolSelected.getName());
            }

            if (resultCode == Activity.RESULT_CANCELED) {}

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

        toggleAllComponents(false);

        getPresenter().saveSon(myKidIdentity, name, surname, birthday,
                school != null ? school.getIdentity() : "", currentImagePath, socialMedias);

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
        showSocialMediaStatusDialog(SocialMediaTypeEnum.INSTAGRAM);
    }

    /**
     * On Facebook Row Clicked
     */
    @OnClick(R.id.facebookRow)
    protected void onFacebookRowClicked(){
        showSocialMediaStatusDialog(SocialMediaTypeEnum.FACEBOOK);
    }


    /**
     * On Youtube Row Clicked
     */
    @OnClick(R.id.googleRow)
    protected void onYoutubeRowClicked(){
        showSocialMediaStatusDialog(SocialMediaTypeEnum.YOUTUBE);
    }

    /**
     * Show School Detail
     */
    @OnClick(R.id.showSchoolDetail)
    protected void onShowSchoolDetail(){
        Preconditions.checkNotNull(school, "School can not be null");
        navigatorImpl.showSchoolDetail(this, school);
    }

    /**
     * On Son Profile Loaded
     * @param kidEntity
     */
    @Override
    public void onSonProfileLoaded(final KidEntity kidEntity) {
        Preconditions.checkNotNull(kidEntity, "Son Entity can not be null");

        Timber.d("Son Profile Image Url -> %s", kidEntity.getProfileImage());

        // Save Current State
        myKidIdentity = kidEntity.getIdentity();
        currentImagePath = kidEntity.getProfileImage();
        profileMode = KidProfileMode.EDIT_CURRENT_SON_MODE;

        myKidsProfileTitle.setText(String.format(getString(R.string.my_kids_profile_name), kidEntity.getFullName()));

        if(appUtils.isValidString(kidEntity.getProfileImage()))
            picasso.load(kidEntity.getProfileImage())
                    .placeholder(R.drawable.kid_default_image)
                    .error(R.drawable.kid_default_image)
                    .noFade()
                    .into(profileImageView);
        else
            profileImageView.setImageResource(R.drawable.kid_default_image);


        if(kidEntity.getFirstName() != null &&
                !kidEntity.getFirstName().isEmpty()) {
            firstName = kidEntity.getFirstName();
            nameInput.setText(kidEntity.getFirstName());
        }

        if(kidEntity.getLastName() != null &&
                !kidEntity.getLastName().isEmpty()) {
            lastName = kidEntity.getLastName();
            surnameInput.setText(kidEntity.getLastName());
        }

        if(kidEntity.getBirthdate() != null) {
            birthdayInput.setDateSelected(kidEntity.getBirthdate());
        }

        if(kidEntity.getSchool() != null) {
            school = kidEntity.getSchool();
            schoolInput.setText(kidEntity.getSchool().getName());
            showSchoolDetailImageView.setVisibility(View.VISIBLE);
        }

        // Enable All Profile Components
        toggleAllProfileComponents(true);
    }

    /**
     * On Social Media Loaded
     * @param socialMediaEntities
     */
    @Override
    public void onSocialMediaLoaded(List<SocialMediaEntity> socialMediaEntities) {
        Preconditions.checkNotNull(socialMediaEntities, "Social Media Entities can not be null");
        Timber.d("On Social Media Loaded -> %d", socialMediaEntities.size());

        socialMedias = new ArrayList<>(socialMediaEntities);

        for(final SocialMediaTypeEnum socialMediaType: SocialMediaTypeEnum.values()) {

            SocialMediaEntity socialMedia = null;
            for(final SocialMediaEntity socialMediaEntity: socialMedias) {
                if(socialMediaEntity.getType().equals(socialMediaType)){
                    socialMedia = socialMediaEntity;
                    break;
                }
            }
            // Update Social Row
            updateSocialMediaRow(socialMediaType, socialMedia);

        }

        // Toggle Social Media Components
        toggleAllSocialMediaComponents(true);
    }

    /**
     * Update Social Media Row
     */
    private void updateSocialMediaRow(final SocialMediaTypeEnum socialMediaTypeEnum, final SocialMediaEntity socialMedia){
        Preconditions.checkNotNull(socialMediaTypeEnum, "Social Media Type can not be null");


        @ColorRes int colorRes;
        @StringRes int socialMediaStringRes;
        @DrawableRes int socialIconRes;
        boolean isChecked;


        switch (socialMediaTypeEnum) {

            case YOUTUBE:

                if(socialMedia != null) {

                    if(socialMedia.hasInvalidToken()) {

                        colorRes = R.color.redDanger;
                        socialMediaStringRes = R.string.social_media_is_not_valid;
                        socialIconRes = R.drawable.google_plus_danger;
                        isChecked = false;

                    } else {

                        colorRes = R.color.greenSuccess;
                        socialMediaStringRes = R.string.social_media_is_enabled;
                        socialIconRes = R.drawable.google_plus_success;
                        isChecked = true;

                    }


                } else {
                    // Social Media not configured
                    colorRes = R.color.yellowWarning;
                    socialMediaStringRes = R.string.social_media_is_not_enabled;
                    socialIconRes = R.drawable.google_plus_yellow;
                    isChecked = false;

                }

                googleStatusTextView.setText(getString(socialMediaStringRes));
                googleStatusTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), colorRes));
                googleIconImageView.setImageResource(socialIconRes);
                googleSwitchWidget.setChecked(isChecked, false);

                break;

            case INSTAGRAM:

                if(socialMedia != null) {

                    if(socialMedia.hasInvalidToken()) {

                        colorRes = R.color.redDanger;
                        socialMediaStringRes = R.string.social_media_is_not_valid;
                        socialIconRes = R.drawable.instagram_danger;
                        isChecked = false;

                    } else {

                        colorRes = R.color.greenSuccess;
                        socialMediaStringRes = R.string.social_media_is_enabled;
                        socialIconRes = R.drawable.instagram_success;
                        isChecked = true;
                    }


                } else {
                    // Social Media not configured
                    colorRes = R.color.yellowWarning;
                    socialMediaStringRes = R.string.social_media_is_not_enabled;
                    socialIconRes = R.drawable.instagram_warning;
                    isChecked = false;
                }

                instagramStatusTextView.setText(socialMediaStringRes);
                instagramStatusTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), colorRes));
                instagramImageView.setImageResource(socialIconRes);
                instagramSwitchWidget.setChecked(isChecked, false);

                break;

            case FACEBOOK:

                if(socialMedia != null) {

                    if(socialMedia.hasInvalidToken()) {

                        colorRes = R.color.redDanger;
                        socialMediaStringRes = R.string.social_media_is_not_valid;
                        socialIconRes = R.drawable.facebook_danger;
                        isChecked = false;

                    } else {

                        colorRes = R.color.greenSuccess;
                        socialMediaStringRes = R.string.social_media_is_enabled;
                        socialIconRes = R.drawable.facebook_success;
                        isChecked = true;
                    }


                } else {
                    // Social Media not configured
                    colorRes = R.color.yellowWarning;
                    socialMediaStringRes = R.string.social_media_is_not_enabled;
                    socialIconRes = R.drawable.facebook_warning;
                    isChecked = false;
                }

                facebookStatusTextView.setText(socialMediaStringRes);
                facebookStatusTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), colorRes));
                facebookIconImageView.setImageResource(socialIconRes);
                facebookSwitchWidget.setChecked(isChecked, false);

                break;

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
        toggleAllComponents(true);
    }

    /**
     * Find Configured Social Media
     * @param socialMediaTypeEnum
     * @return
     */
    private int findConfiguredSocialMedia(final SocialMediaTypeEnum socialMediaTypeEnum) {
        Preconditions.checkNotNull(socialMediaTypeEnum, "Social Media Type can not be null");
        Preconditions.checkNotNull(socialMedias, "Social Medias can not be null");

        int i = 0;
        for (; i < socialMedias.size(); i++ )
            if(socialMedias.get(i).getType().equals(socialMediaTypeEnum))
                break;

        return i < socialMedias.size() ? i : -1;

    }

    /**
     * Get Configured Social Media
     * @param socialMediaTypeEnum
     * @return
     */
    private int getConfiguredSocialMedia(final SocialMediaTypeEnum socialMediaTypeEnum) {
        Preconditions.checkNotNull(socialMediaTypeEnum, "Social Media Type can not be null");
        final int idx = findConfiguredSocialMedia(socialMediaTypeEnum);

        if(idx < 0)
            throw new IllegalStateException("Configured social media not found");

        return idx;
    }

    /**
     * Get Social Media Status
     * @param socialMediaTypeEnum
     * @return
     */
    private void showSocialMediaStatusDialog(final SocialMediaTypeEnum socialMediaTypeEnum) {
        Preconditions.checkNotNull(socialMediaTypeEnum, "Social Media Type ca not be null");

        SocialMediaStatusEnum socialMediaStatusEnum = SocialMediaStatusEnum.DISABLED;
        String userSocialFullName = null, userSocialProfilePicture = null;

        if(!socialMedias.isEmpty()) {
            
            int idx = findConfiguredSocialMedia(socialMediaTypeEnum);
            if (idx >= 0) {

                final SocialMediaEntity socialMediaEntity = socialMedias.get(idx);

                socialMediaStatusEnum = !socialMediaEntity.hasInvalidToken() ? SocialMediaStatusEnum.ENABLED
                        : SocialMediaStatusEnum.INVALID;

                userSocialFullName = socialMediaEntity.getUserFullName();
                userSocialProfilePicture = socialMediaEntity.getUserPicture();

            }

        }

        navigatorImpl.showSocialMediaStatusDialog(this,
                socialMediaTypeEnum, socialMediaStatusEnum, userSocialFullName, userSocialProfilePicture);
    }

    /**
     * On Access Token Obtainer
     * @param socialUser
     */
    private void onSocialUserObtained(final SocialMediaTypeEnum socialMediaTypeEnum,
                                      final SocialUser socialUser) {

        SocialMediaEntity socialMediaEntityObtained = new SocialMediaEntity();
        socialMediaEntityObtained.setUserSocialName(socialUser.username);
        socialMediaEntityObtained.setAccessToken(socialUser.accessToken);
        socialMediaEntityObtained.setInvalidToken(false);
        socialMediaEntityObtained.setUserFullName(socialUser.fullName);
        socialMediaEntityObtained.setUserPicture(socialUser.profilePictureUrl);
        socialMediaEntityObtained.setType(socialMediaTypeEnum);
        socialMediaEntityObtained.setKid(myKidIdentity);

        if(!socialMedias.isEmpty()) {

            // find configured social media
            final int idx = findConfiguredSocialMedia(socialMediaTypeEnum);

            if(idx >= 0) {
                // Replace current social media
                final SocialMediaEntity currentSocialMedia = socialMedias.get(idx);
                currentSocialMedia.setUserSocialName(socialUser.username);
                currentSocialMedia.setAccessToken(socialUser.accessToken);
                currentSocialMedia.setInvalidToken(false);
                currentSocialMedia.setUserFullName(socialUser.fullName);
                currentSocialMedia.setUserPicture(socialUser.profilePictureUrl);

            } else {
                // not found, add new social media
                socialMedias.add(socialMediaEntityObtained);
            }

        } else {
            // no social medias configured, add new social media
            socialMedias.add(socialMediaEntityObtained);
        }


        // Update Social Media
        updateSocialMediaRow(socialMediaTypeEnum, socialMediaEntityObtained);

        Timber.d("Social Media Entity %s - %s", socialMediaEntityObtained.getType().name(),
                socialMediaEntityObtained.getAccessToken());
    }

    /**
     * Disable Social Media
     * @param socialMediaTypeEnum
     */
    private void disableSocialMedia(final SocialMediaTypeEnum socialMediaTypeEnum) {
        Preconditions.checkNotNull(socialMediaTypeEnum, "Social Media Type can not be null");
        Preconditions.checkNotNull(socialMedias, "Social Medias can not be null");

        showConfirmationDialog(R.string.enable_social_media_disable_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {

                final int idx = getConfiguredSocialMedia(socialMediaTypeEnum);

                socialMedias.remove(idx);
                // Update Social Media Row
                updateSocialMediaRow(socialMediaTypeEnum, null);

            }

            @Override
            public void onRejected(DialogFragment dialog) {

                final int idx = getConfiguredSocialMedia(socialMediaTypeEnum);

                // Update Social Media Row
                updateSocialMediaRow(socialMediaTypeEnum, socialMedias.get(idx));

            }
        });

    }

    /**
     * Enable Facebook Social Media
     */
    private void enableFacebookSocialMedia(){

        // Get Kid Facebook scopes
        final List<String> userScopes =
                Arrays.asList(getResources().getStringArray(R.array.facebook_kid_scopes));

        // Clean connection
        com.jaychang.sa.facebook.SimpleAuth.disconnectFacebook();

        // Connect to Facebook
        com.jaychang.sa.facebook.SimpleAuth.connectFacebook(userScopes, new AuthCallback() {
            @Override
            public void onSuccess(SocialUser socialUser) {
                onSocialUserObtained(SocialMediaTypeEnum.FACEBOOK, socialUser);
            }

            @Override
            public void onError(Throwable error) {
                showLongMessage(getString(R.string.enable_social_media_error));
                facebookSwitchWidget.setChecked(false, false);
            }

            @Override
            public void onCancel() {
                facebookSwitchWidget.setChecked(false, false);
            }
        });
    }

    /**
     * Enable Instagram Social Media
     */
    private void enableInstagramSocialMedia(){

        // Get Kid Instagram scopes
        final List<String> userScopes =
                Arrays.asList(getResources().getStringArray(R.array.instagram_kid_scopes));

        // Clean Connection
        com.jaychang.sa.instagram.SimpleAuth.disconnectInstagram();

        // Connect to Instagram
        com.jaychang.sa.instagram.SimpleAuth.connectInstagram(userScopes, new AuthCallback() {
            @Override
            public void onSuccess(SocialUser socialUser) {
                onSocialUserObtained(SocialMediaTypeEnum.INSTAGRAM, socialUser);

            }

            @Override
            public void onError(Throwable error) {
                showLongMessage(getString(R.string.enable_social_media_error));
                instagramSwitchWidget.setChecked(false, false);
            }

            @Override
            public void onCancel() {
                instagramSwitchWidget.setChecked(false, false);
            }
        });
    }

    /**
     * Enable Google Social Media
     */
    private void enableGoogleSocialMedia(){

        // Get Kid Google scopes
        final List<String> userScopes =
                Arrays.asList(getResources().getStringArray(R.array.google_kid_scopes));


        // Clean Connection
        com.jaychang.sa.google.SimpleAuth.disconnectGoogle();

        // Connect to Google
        com.jaychang.sa.google.SimpleAuth.connectGoogle(userScopes, new AuthCallback() {
            @Override
            public void onSuccess(SocialUser socialUser) {
                onSocialUserObtained(SocialMediaTypeEnum.YOUTUBE, socialUser);
            }

            @Override
            public void onError(Throwable error) {
                showLongMessage(getString(R.string.enable_social_media_error));
                googleSwitchWidget.setChecked(false, false);
            }

            @Override
            public void onCancel() {
                googleSwitchWidget.setChecked(false, false);
            }
        });
    }

    /**
     * Has Pending Changes
     * @return
     */
    @Override
    public Boolean hasPendingChanges() {
        return super.hasPendingChanges();
    }

    /**
     * On Saved Pending Changes
     */
    @Override
    public void onSavedPendingChanges() {
        super.onSavedPendingChanges();
    }

    /**
     * On Discard Pending Changes
     */
    @Override
    public void onDiscardPendingChanges() {
        super.onDiscardPendingChanges();
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_9;
    }
}
