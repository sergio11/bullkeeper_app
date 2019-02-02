package sanchez.sanchez.sergio.bullkeeper.ui.activity.savescheduledblock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.squareup.picasso.Picasso;
import org.joda.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import de.hdodenhof.circleimageview.CircleImageView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpValidationMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportEditTextTimePicker;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportEditWeeklyFrequency;
import sanchez.sanchez.sergio.bullkeeper.core.utils.SupportImagePicker;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerScheduledBlockComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.ScheduledBlockComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.appsearch.AppSearchListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.list.GeofencesListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.PhotoViewerDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.appallowedbyscheduled.AppAllowedByScheduledMvpFragment;
import sanchez.sanchez.sergio.domain.models.AppAllowedByScheduledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledByTerminalEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import timber.log.Timber;

/**
 * Save Scheduled Block Mvp Activity
 */
public class SaveScheduledBlockMvpActivity extends SupportMvpValidationMvpActivity<SaveScheduledBlockPresenter,
        ISaveScheduledBlockView> implements HasComponent<ScheduledBlockComponent>, ISaveScheduledBlockView,
        PhotoViewerDialog.IPhotoViewerListener, ISaveScheduledBlockActivityHandler, OnMapReadyCallback {

    /**
     * Content and Content Type Name
     */
    private final String CONTENT_FULL_NAME = "SAVE_SCHEDULED_BLOCK";
    private final String CONTENT_TYPE_NAME = "SCHEDULED_BLOCK";

    /**
     * Args
     */
    private static final String SCHEDULED_BLOCK_IDENTITY_ARG = "SCHEDULED_BLOCK_IDENTITY";
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Request Code
     */
    private static final int SEARCH_APP_REQUEST_CODE = 1234;
    private static final int SELECT_GEOFENCE_REQUEST_CODE = 1235;

    /**
     * Fields
     */
    private final static String SCHEDULED_BLOCK_NAME_FIELD_NAME = "name";
    private final static String SCHEDULED_BLOCK_START_AT_FIELD_NAME = "start_at";
    private final static String SCHEDULED_BLOCK_END_AT_FIELD_NAME = "end_at";
    private final static String SCHEDULED_BLOCK_WEEKLY_FREQUENCY_FIELD_NAME = "weekly_frequency";
    private final static String SCHEDULED_BLOCK_DESCRIPTION_FIELD_NAME = "description";
    private final static String SCHEDULED_BLOCK_GEOFENCE_FIELD_NAME = "geofence";

    /**
     * Constants
     */
    private static final int TARGET_ZOOM = 19;


    /**
     * Modes Enum
     */
    public enum ScheduledBlockMode { ADD_NEW_SCHEDULED_BLOCK_MODE, EDIT_SCHEDULED_BLOCK_MODE }


    /**
     * Scheduled Block Component
     */
    private ScheduledBlockComponent scheduledBlockComponent;


    /**
     * Views
     * ====================
     */

    /**
     * Scheduled Block Image View
     */
    @BindView(R.id.scheduledBlockImage)
    protected CircleImageView scheduledBlockImageView;

    /**
     * Name Input Layout
     */
    @BindView(R.id.nameInputLayout)
    protected TextInputLayout nameInputLayout;

    /**
     * Name Input
     */
    @BindView(R.id.nameInput)
    @NotEmpty(messageResId = R.string.scheduled_block_name_field_error_not_blank)
    @Length(min = 3, max = 30)
    protected AppCompatEditText nameInput;

    /**
     * Name Input Layout
     */
    @BindView(R.id.descriptionLayout)
    protected TextInputLayout descriptionInputLayout;

    /**
     * Name Input
     */
    @BindView(R.id.descriptionInput)
    protected AppCompatEditText descriptionInput;


    /**
     * Start At Input Layout
     */
    @BindView(R.id.startAtInputLayout)
    protected TextInputLayout startAtInputLayout;

    /**
     * Start At Input
     */
    @BindView(R.id.startAtInput)
    protected SupportEditTextTimePicker startAtInput;


    /**
     * End At Input Layout
     */
    @BindView(R.id.endAtInputLayout)
    protected TextInputLayout endAtInputLayout;

    /**
     * End At Input
     */
    @BindView(R.id.endAtInput)
    protected SupportEditTextTimePicker endAtInput;

    /**
     * Save Changes View
     */
    @BindView(R.id.saveChanges)
    protected View saveChangesView;

    /**
     * Scheduled Block Weekly Frequency Input
     */
    @BindView(R.id.scheduledBlockWeeklyFrequencyInput)
    protected SupportEditWeeklyFrequency scheduledBlockWeeklyFrequencyInput;

    /**
     * Recurring Weekly Switch
     */
    @BindView(R.id.recurringWeeklySwitch)
    protected SwitchCompat recurringWeeklySwitch;


    /**
     * Is Enable Switch
     */
    @BindView(R.id.enableSwitch)
    protected SwitchCompat enableSwitch;


    /**
     * Delete Scheduled Block
     */
    @BindView(R.id.deleteScheduledBlock)
    protected LinearLayout deleteScheduledBlock;


    /**
     * Allow Calls Switch
     */
    @BindView(R.id.allowCallsSwitch)
    protected SwitchCompat allowCallsSwitch;


    /**
     * No Geofence Configured
     */
    @BindView(R.id.noGeofenceConfigured)
    protected ViewGroup noGeofenceConfiguredViewGroup;

    /**
     * Geofence Configured
     */
    @BindView(R.id.geofenceConfigured)
    protected ViewGroup geofenceConfiguredViewGroup;

    /**
     * Geofence Address
     */
    @BindView(R.id.geofenceAddress)
    protected TextView geofenceAddressTextView;

    /**
     * Remove Geofence Selected Image View
     */
    @BindView(R.id.removeGeofenceConfigured)
    protected ImageView removeGeofenceSelectedImageView;


    /**
     * Dependencies
     * =====================
     */

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
     * ===================
     */

    /**
     * Current Image Path
     */
    @State
    protected String currentImagePath;


    /**
     * Scheduled Block Image
     */
    @State
    protected String scheduledBlockImage;

    /**
     * Scheduled Block Identity
     */
    @State
    protected String scheduledBlockIdentity;

    /**
     * Profile Mode
     */
    @State
    protected ScheduledBlockMode profileMode = ScheduledBlockMode.ADD_NEW_SCHEDULED_BLOCK_MODE;

    /**
     * Scheduled Block Name
     */
    @State
    protected String scheduledBlockName;

    /**
     * Scheduled Blocks Weekly Frequency
     */
    @State
    protected int[] scheduledBlocksWeeklyFrequency = new int[]{ 0,0,0,0,0,0,0 };

    /**
     * Start At
     */
    @State
    protected LocalTime startAt;

    /**
     * End At
     */
    @State
    protected LocalTime endAt;

    /**
     * Recurring Weekly Enabled
     */
    @State
    protected boolean scheduledBlockRecurringWeeklyEnabled;

    /**
     * Is Enabled
     */
    @State
    protected boolean isEnabled;

    /**
     * Kid
     */
    @State
    protected String kid;

    /**
     * Description
     */
    @State
    protected String description;

    /**
     * Allow Calls
     */
    @State
    protected boolean allowCalls;

    /**
     * Geofence Configured
     */
    @State
    protected GeofenceEntity geofenceEntity;

    /**
     * App Allowed By Scheduled
     */
    protected AppAllowedByScheduledMvpFragment appAllowedByScheduledMvpFragment;

    /**
     * Google Map
     */
    private GoogleMap googleMap;

    /**
     * Current Circle Indicator
     */
    private Circle currentCircleIndicator;

    /**
     * Curretn Marker Indicator
     */
    private Marker currentMarkerIndicator;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity,
                                          final String kid) {
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        final Intent intent = new Intent(context, SaveScheduledBlockMvpActivity.class);
        intent.putExtra(SCHEDULED_BLOCK_IDENTITY_ARG, identity);
        intent.putExtra(KID_IDENTITY_ARG, kid);
        return intent;
    }


    /**
     * Get Calling Intent
     * @param context
     * @param kid
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        final Intent intent =  new Intent(context, SaveScheduledBlockMvpActivity.class);
        intent.putExtra(KID_IDENTITY_ARG, kid);
        return intent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {

        scheduledBlockComponent = DaggerScheduledBlockComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

        scheduledBlockComponent.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public SaveScheduledBlockPresenter providePresenter() {
        return scheduledBlockComponent.saveScheduledBlockPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public ScheduledBlockComponent getComponent() {
        return scheduledBlockComponent;
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
        return R.layout.save_scheduled_block;
    }

    /**
     * Toggle All Components
     */
    private void toggleAllComponents(final boolean isEnable){
        saveChangesView.setEnabled(isEnable);
        nameInput.setEnabled(isEnable);
        startAtInput.setEnabled(isEnable);
        endAtInput.setEnabled(isEnable);
        recurringWeeklySwitch.setEnabled(isEnable);
        enableSwitch.setEnabled(isEnable);
        scheduledBlockWeeklyFrequencyInput.setEnabled(isEnable);
        descriptionInputLayout.setEnabled(isEnabled);
        noGeofenceConfiguredViewGroup.setEnabled(isEnabled);
    }

    /**
     * On New Instance
     */
    @Override
    protected void onNewViewInstance() {
        super.onNewViewInstance();


        if(!getIntent().hasExtra(KID_IDENTITY_ARG) ||
                !appUtils.isValidString(getIntent().getStringExtra(KID_IDENTITY_ARG)))
            throw new IllegalArgumentException("Kid can not be null");

        appAllowedByScheduledMvpFragment = AppAllowedByScheduledMvpFragment.newInstance();
        // Add Fragment
        addFragment(R.id.appsAllowedByScheduledContainer, appAllowedByScheduledMvpFragment,
                false, "");

        // Get Kid Identity
        kid = getIntent().getStringExtra(KID_IDENTITY_ARG);

        if(getIntent().hasExtra(SCHEDULED_BLOCK_IDENTITY_ARG) &&
                appUtils.isValidString(getIntent().getStringExtra(SCHEDULED_BLOCK_IDENTITY_ARG))) {

            // Get Scheduled Block Identity
            scheduledBlockIdentity = getIntent().getStringExtra(SCHEDULED_BLOCK_IDENTITY_ARG);
            // Toggle All Components
            toggleAllComponents(false);

            getPresenter().loadScheduledBlock(kid, scheduledBlockIdentity);
        }

    }

    /**
     * On Destroy
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Destroy Map
        destroyMap();
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        if(scheduledBlockIdentity != null && !scheduledBlockIdentity.isEmpty())
            args.putString(SCHEDULED_BLOCK_IDENTITY_ARG, scheduledBlockIdentity);
        if(kid != null && !kid.isEmpty())
           args.putString(KID_IDENTITY_ARG, kid);
        return args;
    }

    /**
     * On Saved View Instance
     */
    @Override
    protected void onSavedViewInstance() {
        super.onSavedViewInstance();
        // Update Profile Form with state information
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
        }  else if(viewId.equals(R.id.startAtInput)) {
            startAtInputLayout.setError(message);
        } else if(viewId.equals(R.id.endAtInput)) {
            endAtInputLayout.setError(message);
        }

    }

    /**
     * ON Validation Succeded
     */
    @Override
    public void onValidationSucceeded() {
        super.onValidationSucceeded();

        scheduledBlockName = nameInput.getText().toString();
        startAt = startAtInput.getCurrentLocalTime();
        endAt = endAtInput.getCurrentLocalTime();
        scheduledBlocksWeeklyFrequency =
                scheduledBlockWeeklyFrequencyInput.getDaysOfWeekStatus();
        scheduledBlockRecurringWeeklyEnabled = recurringWeeklySwitch.isChecked();
        description = descriptionInput.getText().toString();
        isEnabled = enableSwitch.isChecked();
        allowCalls = allowCallsSwitch.isChecked();
        final List<AppAllowedByScheduledEntity> appAllowedByScheduledEntities =
                appAllowedByScheduledMvpFragment.getAppAllowedByScheduledEntities();

        if(startAt.isAfter(endAt)) {
            startAtInputLayout.setError(getString(R.string.scheduled_block_start_input_validation_error));
            endAtInputLayout.setError(getString(R.string.scheduled_block_end_input_validation_error));
            return;
        }

        boolean atLeastOneDayOfWeekConfigured = false;
        for(int i = 0; i < scheduledBlocksWeeklyFrequency.length; i++)
            if(scheduledBlocksWeeklyFrequency[i] == 1) {
                atLeastOneDayOfWeekConfigured = true;
                break;
            }

        if(!atLeastOneDayOfWeekConfigured) {
            scheduledBlockWeeklyFrequencyInput.setError(getString(R.string.scheduled_block_weekly_frequency_not_valid));
            return;
        }

        // Geofence Configured
        final String geofence = geofenceEntity != null ?
                geofenceEntity.getIdentity(): "";


        // Save Scheduled Block
        getPresenter().saveScheduledBlock(scheduledBlockIdentity, scheduledBlockName, isEnabled, startAt, endAt,
                scheduledBlocksWeeklyFrequency, scheduledBlockRecurringWeeklyEnabled, kid,
                 description, allowCalls, currentImagePath, appAllowedByScheduledEntities, geofence);
    }

    /**
     * On Reset Fields
     */
    @Override
    protected void onResetFields() {
        super.onResetFields();
        drawCurrentState();
    }

    /**
     * On Reset Errors
     */
    @Override
    protected void onResetErrors() {
        nameInputLayout.setError(null);
        startAtInput.setError(null);
        endAtInput.setError(null);
        scheduledBlockWeeklyFrequencyInput.clearError();
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
    @OnClick(R.id.deleteScheduledBlock)
    protected void onDeleteScheduled(){

        showConfirmationDialog(R.string.delete_scheduled_block_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                getPresenter().deleteScheduledById(kid, scheduledBlockIdentity);
            }

            @Override
            public void onRejected(DialogFragment dialog) {}
        });
    }


    /**
     * On Single Permission Granted
     * @param permission
     */
    @Override
    public void onSinglePermissionGranted(String permission) {
        super.onSinglePermissionGranted(permission);
    }

    /**
     * On Single Permission Rejected
     * @param permission
     */
    @Override
    public void onSinglePermissionRejected(String permission) {
        super.onSinglePermissionRejected(permission);

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
                scheduledBlockImageView.setImageURI(Uri.parse(currentImagePath));
            }

        } else if(SEARCH_APP_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK){
            final AppInstalledByTerminalEntity appSelected = (AppInstalledByTerminalEntity)
                    data.getSerializableExtra(AppSearchListMvpActivity.APP_SELECTED_ARG);

            if(appAllowedByScheduledMvpFragment != null)
                appAllowedByScheduledMvpFragment.addAppSelected(appSelected);

        } else if(SELECT_GEOFENCE_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK) {

            geofenceEntity = (GeofenceEntity)
                    data.getSerializableExtra(GeofencesListMvpActivity.GEOFENCE_SELECTED_ARG);

            drawGeofenceConfiguredState();
        }
    }

    /**
     * Update Form
     */
    private void drawCurrentState() {

        if(appUtils.isValidString(currentImagePath)) {
            scheduledBlockImageView.setImageURI(Uri.parse(currentImagePath));
        } else {
            if(appUtils.isValidString(scheduledBlockImage))
                picasso.load(scheduledBlockImage)
                        .placeholder(R.drawable.scheduled_block_default)
                        .error(R.drawable.scheduled_block_default)
                        .noFade()
                        .into(scheduledBlockImageView);
            else
                scheduledBlockImageView.setImageResource(R.drawable.scheduled_block_default);
        }

        nameInput.setText(scheduledBlockName);
        startAtInput.setCurrentLocalTime(startAt, true);
        endAtInput.setCurrentLocalTime(endAt, true);
        scheduledBlockWeeklyFrequencyInput.setDaysOfWeekStatus(scheduledBlocksWeeklyFrequency);
        recurringWeeklySwitch.setChecked(scheduledBlockRecurringWeeklyEnabled);
        enableSwitch.setChecked(isEnabled);
        descriptionInput.setText(description);

        drawGeofenceConfiguredState();
    }

    /**
     * Draw Geofence Configured State
     */
    private void drawGeofenceConfiguredState(){
        if(geofenceEntity != null) {
            noGeofenceConfiguredViewGroup.setVisibility(View.GONE);
            geofenceConfiguredViewGroup.setVisibility(View.VISIBLE);
            geofenceAddressTextView.setText(geofenceEntity.getAddress());
            loadMapAsync();
        } else {
            noGeofenceConfiguredViewGroup.setVisibility(View.VISIBLE);
            geofenceConfiguredViewGroup.setVisibility(View.GONE);
            geofenceAddressTextView.setText("");
            if(currentCircleIndicator != null)
                currentCircleIndicator.remove();
            if (currentMarkerIndicator != null)
                currentMarkerIndicator.remove();
        }
    }

    /**
     * On Scheduled Block Loaded
     * @param scheduledBlockEntity
     */
    @Override
    public void onScheduledBlockLoaded(ScheduledBlockEntity scheduledBlockEntity) {
        Preconditions.checkNotNull(scheduledBlockEntity, "Scheduled Block Entity can not be null");

        scheduledBlockImage = scheduledBlockEntity.getImage();
        scheduledBlockIdentity = scheduledBlockEntity.getIdentity();
        scheduledBlockName = scheduledBlockEntity.getName();
        scheduledBlocksWeeklyFrequency = scheduledBlockEntity.getWeeklyFrequency();
        scheduledBlockRecurringWeeklyEnabled = scheduledBlockEntity.isRepeatable();
        isEnabled = scheduledBlockEntity.isEnable();
        profileMode = ScheduledBlockMode.EDIT_SCHEDULED_BLOCK_MODE;
        startAt = scheduledBlockEntity.getStartAt();
        endAt = scheduledBlockEntity.getEndAt();
        geofenceEntity = scheduledBlockEntity.getGeofence();
        description = scheduledBlockEntity.getDescription();

        // Draw State
        drawCurrentState();
        // Enable All Components
        toggleAllComponents(true);
        // Enable Delete Scheduled Block
        deleteScheduledBlock.setVisibility(View.VISIBLE);
        // Set App Allowed By Scheduled
        appAllowedByScheduledMvpFragment.setAppAllowedByScheduledEntities(scheduledBlockEntity.getAppsAllowed());

    }

    /**
     * On Scheduled Block Image Click
     */
    @OnClick(R.id.scheduledBlockImage)
    protected void onClickScheduledBlockImage() {
        navigatorImpl.showPhotoViewerDialog(this,
                currentImagePath != null ? currentImagePath :
                        scheduledBlockImage);
    }


    /**
     * On Long Profile Image Scheduled Block Image Clicked
     */
    @OnLongClick(R.id.scheduledBlockImage)
    protected boolean onLongScheduledBlockImage(){
        supportImagePicker.pickImage(this,
                profileMode.equals(ScheduledBlockMode.EDIT_SCHEDULED_BLOCK_MODE) ?
                        String.format(Locale.getDefault(),
                                getString(R.string.change_scheduled_block_picture), scheduledBlockName) :
                        getString(R.string.change_scheduled_block_picture_default));
        return true;
    }

    /**
     * On Change Photo
     */
    @Override
    public void onChangePhoto() {
        supportImagePicker.pickImage(this,
                profileMode.equals(ScheduledBlockMode.EDIT_SCHEDULED_BLOCK_MODE) ?
                        String.format(Locale.getDefault(), getString(R.string.change_scheduled_block_picture), scheduledBlockName) :
                        getString(R.string.change_scheduled_block_picture_default));
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
                case SCHEDULED_BLOCK_NAME_FIELD_NAME:
                    nameInputLayout.setError(error.get("message"));
                    break;
                case SCHEDULED_BLOCK_START_AT_FIELD_NAME:
                    startAtInputLayout.setError(error.get("message"));
                    break;
                case SCHEDULED_BLOCK_END_AT_FIELD_NAME:
                    endAtInputLayout.setError(error.get("message"));
                    break;
                case SCHEDULED_BLOCK_WEEKLY_FREQUENCY_FIELD_NAME:
                    scheduledBlockWeeklyFrequencyInput.setError(error.get("message"));
                    break;
                case SCHEDULED_BLOCK_DESCRIPTION_FIELD_NAME:
                    descriptionInputLayout.setError(error.get("message"));
                    break;
                case SCHEDULED_BLOCK_GEOFENCE_FIELD_NAME:
                    geofenceEntity = null;
                    drawGeofenceConfiguredState();
                    break;
            }

        }

        showNoticeDialog(R.string.forms_is_not_valid);
        // Enable All Components
        toggleAllComponents(true);
    }

    /**
     * On Scheduled Block Deleted
     */
    @Override
    public void onScheduledBlockDeleted() {

        showNoticeDialog(R.string.scheduled_block_deleted_success, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });

    }

    /**
     *  On Saved Block Saved
     * @param scheduledBlockEntity
     */
    @Override
    public void onScheduledBlockSaved(ScheduledBlockEntity scheduledBlockEntity) {
        Preconditions.checkNotNull(scheduledBlockEntity, "Scheduled Block can not be empty");
        onResetFields();
        showNoticeDialog(R.string.scheduled_block_saved_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });
    }

    /**
     * On Scheduled Block Not Valid
     */
    @Override
    public void onScheduledBlockNotValid() {
        showNoticeDialog(R.string.scheduled_block_not_valid);
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_6;
    }

    /**
     * Navigate To App Search
     */
    @Override
    public void navigateToAppSearch() {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        navigatorImpl.navigateToAppSearchListMvpActivity(this, kid, SEARCH_APP_REQUEST_CODE);
    }

    /**
     * On No Geofence Configured Clicked
     */
    @OnClick(R.id.noGeofenceConfigured)
    protected void onNoGeofenceConfiguredClicked(){
        navigatorImpl.navigateToSelectGeofence(this, kid,
                SELECT_GEOFENCE_REQUEST_CODE);
    }

    /**
     * On Remove Geofence Selected Clicked
     */
    @OnClick(R.id.removeGeofenceConfigured)
    protected void onRemoveGeofenceConfiguredClicked(){
        geofenceEntity = null;
        drawGeofenceConfiguredState();
    }

    /**
     * On Map Ready
     * @param googleMap
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        this.googleMap.setIndoorEnabled(false);
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);
        this.googleMap.getUiSettings().setCompassEnabled(false);
        this.googleMap.getUiSettings().setMapToolbarEnabled(false);
        this.googleMap.getUiSettings().setZoomGesturesEnabled(false);
        this.googleMap.getUiSettings().setScrollGesturesEnabled(false);

        if (geofenceEntity != null) {

            // Show Geofence in map
            createGeofenceArea(new LatLng(geofenceEntity.getLat(),
                    geofenceEntity.getLog()), geofenceEntity.getRadius());

        }

    }

    /**
     * Load Map Async
     */
    private void loadMapAsync(){
        if(getFragmentManager() != null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            if(mapFragment != null) {
                mapFragment.getMapAsync(this);
            }
        }
    }

    /**
     * Destroy Map
     */
    private void destroyMap(){
        FragmentManager fm = getSupportFragmentManager();
        if(fm != null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            if (mapFragment != null) {
                fm.beginTransaction().remove(mapFragment).commit();
                mapFragment.onDestroyView();
            }
        }
    }

    /**
     * Create Geofence Area
     * @param latLng
     * @param radius
     */
    private void createGeofenceArea(final LatLng latLng, final double radius){


        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(TARGET_ZOOM));
        googleMap.getUiSettings().setZoomControlsEnabled(true);


        if(currentMarkerIndicator != null)
            currentMarkerIndicator.remove();

        currentMarkerIndicator = googleMap.addMarker(
                new MarkerOptions()
                        .position(latLng)
                        .draggable(false));


        if(currentCircleIndicator != null)
            currentCircleIndicator.remove();

        final CircleOptions circleOptions = new CircleOptions()
                .center(latLng)
                .radius(radius)
                .strokeColor(ContextCompat.getColor(this, R.color.darkModerateBlue))
                .fillColor(ContextCompat.getColor(this, R.color.translucentCyanBrilliant));

        // Create Current Circle Indicator
        currentCircleIndicator = googleMap.addCircle(circleOptions);

    }

}
