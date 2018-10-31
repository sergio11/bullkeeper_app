package sanchez.sanchez.sergio.bullkeeper.ui.activity.savescheduledblock;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.LinearLayout;

import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.squareup.picasso.Picasso;

import org.joda.time.LocalDateTime;

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
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.PhotoViewerDialog;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import timber.log.Timber;

/**
 * Save Scheduled Block Mvp Activity
 */
public class SaveScheduledBlockMvpActivity extends SupportMvpValidationMvpActivity<SaveScheduledBlockPresenter,
        ISaveScheduledBlockView> implements HasComponent<ScheduledBlockComponent>, ISaveScheduledBlockView,
        PhotoViewerDialog.IPhotoViewerListener {

    /**
     * Content and Content Type Name
     */
    private final String CONTENT_FULL_NAME = "SAVE_SCHEDULED_BLOCK";
    private final String CONTENT_TYPE_NAME = "SCHEDULED_BLOCK";

    /**
     * Args
     */
    private static final String SCHEDULED_BLOCK_IDENTITY_ARG = "SCHEDULED_BLOCK_IDENTITY";

    /**
     * Fields
     */
    private final static String SCHEDULED_BLOCK_NAME_FIELD_NAME = "name";
    private final static String SCHEDULED_BLOCK_START_AT_FIELD_NAME = "start_at";
    private final static String SCHEDULED_BLOCK_END_AT_FIELD_NAME = "end_at";
    private final static String SCHEDULED_BLOCK_WEEKLY_FREQUENCY_FIELD_NAME = "weekly_frequency";

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
    @NotEmpty(messageResId = R.string.name_not_empty_error)
    @Length(min = 3, max = 15)
    protected AppCompatEditText nameInput;


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
     * Delete Scheduled Block
     */
    @BindView(R.id.deleteScheduledBlock)
    protected LinearLayout deleteScheduledBlock;

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
    protected LocalDateTime startAt;

    /**
     * End At
     */
    @State
    protected LocalDateTime endAt;

    /**
     * Recurring Weekly Enabled
     */
    @State
    protected boolean scheduledBlockRecurringWeeklyEnabled;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity) {
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");
        final Intent intent = new Intent(context, SaveScheduledBlockMvpActivity.class);
        intent.putExtra(SCHEDULED_BLOCK_IDENTITY_ARG, identity);
        return intent;
    }


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, SaveScheduledBlockMvpActivity.class);
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
        scheduledBlockWeeklyFrequencyInput.setEnabled(isEnable);
    }

    /**
     * On New Instance
     */
    @Override
    protected void onNewViewInstance() {
        super.onNewViewInstance();

        if(getIntent().hasExtra(SCHEDULED_BLOCK_IDENTITY_ARG) &&
                appUtils.isValidString(getIntent().getStringExtra(SCHEDULED_BLOCK_IDENTITY_ARG))) {
            scheduledBlockIdentity = getIntent().getStringExtra(SCHEDULED_BLOCK_IDENTITY_ARG);
            // Toggle All Components
            toggleAllComponents(false);
        }

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
        }

    }

    /**
     * ON Validation Succeded
     */
    @Override
    public void onValidationSucceeded() {
        super.onValidationSucceeded();

        final String name = nameInput.getText().toString();
        final LocalDateTime startAt = startAtInput.getCurrentLocalDateTime();
        final LocalDateTime endAt = endAtInput.getCurrentLocalDateTime();
        final int[] weeklyFrequency =
                scheduledBlockWeeklyFrequencyInput.getDaysOfWeekStatus();
        final boolean recurringWeeklyEnabled = recurringWeeklySwitch.isChecked();

        // Save Scheduled Block
        getPresenter().saveScheduledBlock(scheduledBlockIdentity, name, startAt, endAt,
                weeklyFrequency, recurringWeeklyEnabled);

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
        nameInputLayout.setError("");
        startAtInput.setError("");
        endAtInput.setError("");
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
                getPresenter().deleteScheduledById(scheduledBlockIdentity);
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

        }
    }

    /**
     * Update Form
     */
    private void drawCurrentState() {

        if(appUtils.isValidString(currentImagePath)) {
            if(URLUtil.isNetworkUrl(currentImagePath)) {
                picasso.load(currentImagePath)
                        .placeholder(R.drawable.scheduled_block_default)
                        .error(R.drawable.scheduled_block_default)
                        .noFade()
                        .into(scheduledBlockImageView);
            }
        } else {
            scheduledBlockImageView.setImageResource(R.drawable.scheduled_block_default);
        }

        nameInput.setText(scheduledBlockName);
        startAtInput.setCurrentLocalDateTime(startAt, true);
        endAtInput.setCurrentLocalDateTime(endAt, true);
        scheduledBlockWeeklyFrequencyInput.setDaysOfWeekStatus(scheduledBlocksWeeklyFrequency);
        recurringWeeklySwitch.setChecked(scheduledBlockRecurringWeeklyEnabled);
    }

    /**
     * On Scheduled Block Loaded
     * @param scheduledBlockEntity
     */
    @Override
    public void onScheduledBlockLoaded(ScheduledBlockEntity scheduledBlockEntity) {
        Preconditions.checkNotNull(scheduledBlockEntity, "Scheduled Block Entity can not be null");

        currentImagePath = scheduledBlockEntity.getImage();
        scheduledBlockIdentity = scheduledBlockEntity.getIdentity();
        scheduledBlockName = scheduledBlockEntity.getName();
        scheduledBlocksWeeklyFrequency = scheduledBlockEntity.getWeeklyFrequency();
        scheduledBlockRecurringWeeklyEnabled = scheduledBlockEntity.isRepeatable();
        profileMode = ScheduledBlockMode.EDIT_SCHEDULED_BLOCK_MODE;
        // Draw State
        drawCurrentState();

        // Enable All Components
        toggleAllComponents(true);
        // Enable Delete Scheduled Block
        deleteScheduledBlock.setVisibility(View.VISIBLE);
    }

    /**
     * On Scheduled Block Image Click
     */
    @OnClick(R.id.scheduledBlockImage)
    protected void onClickScheduledBlockImage() {

        if(currentImagePath != null) {
            navigatorImpl.showPhotoViewerDialog(this,
                    currentImagePath);
        } else {
            navigatorImpl.showPhotoViewerDialog(this,
                    R.drawable.scheduled_block_default);
        }
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

}
