package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.crashlytics.android.answers.ContentViewEvent;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSchoolComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SchoolComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpValidationMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import timber.log.Timber;

/**
 * Add School Activity
 */
public class AddSchoolMvpActivity extends SupportMvpValidationMvpActivity<AddSchoolPresenter, IAddSchoolView>
        implements HasComponent<SchoolComponent>, IAddSchoolView, SearchSchoolLocationDialog.ISearchSchoolListener {

    public static final String SCHOOL_ADDED_ARG = "SCHOOL_ADDED_ARG";

    private final String CONTENT_FULL_NAME = "ADD_SCHOOL";
    private final String CONTENT_TYPE_NAME = "SCHOOLS";

    private final static String NAME_FIELD_NAME = "name";
    private final static String RESIDENCE_FIELD_NAME = "residence";
    private final static String PROVINCE_FIELD_NAME = "province";
    private final static String LATITUDE_FIELD_NAME = "latitude";
    private final static String LONGITUDE_FIELD_NAME = "longitude";
    private final static String TFNO_FIELD_NAME = "tfno";
    private final static String EMAIL_FIELD_NAME = "email";

    /**
     * School Component
     */
    private SchoolComponent schoolComponent;

    /**
     * School Name Input Layout
     */
    @BindView(R.id.schoolNameInputLayout)
    protected TextInputLayout schoolNameInputLayout;

    /**
     * School Name Input
     */
    @BindView(R.id.schoolNameInput)
    @NotEmpty(messageResId = R.string.name_not_empty_error)
    @Length(min = 3, max = 30)
    protected AppCompatEditText schoolNameInput;

    /**
     * School Residence Input Layout
     */
    @BindView(R.id.schoolResidenceInputLayout)
    protected TextInputLayout schoolResidenceInputLayout;

    /**
     * School Residence Input
     */
    @BindView(R.id.schoolResidenceInput)
    @NotEmpty(messageResId = R.string.school_residence_empty_error)
    protected AppCompatEditText schoolResidenceInput;

    /**
     * School Email Input Layout
     */
    @BindView(R.id.schoolEmailInputLayout)
    protected TextInputLayout schoolEmailInputLayout;

    /**
     * School Email Input
     */
    @BindView(R.id.schoolEmailInput)
    @NotEmpty(messageResId = R.string.email_not_empty_error)
    @Email(messageResId = R.string.email_invalid_error)
    protected AppCompatEditText schoolEmailInput;

    /**
     * School Telephone Input Layout
     */
    @BindView(R.id.schoolTelephoneInputLayout)
    protected TextInputLayout schoolTelephoneInputLayout;

    /**
     * School Telephone Input
     */
    @BindView(R.id.schoolTelephoneInput)
    protected AppCompatEditText schoolTelephoneInput;


    /**
     * Location Selected Info
     */
    private LocationSelectedInfo locationSelectedInfo;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, AddSchoolMvpActivity.class);
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {

        schoolComponent = DaggerSchoolComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

        schoolComponent.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public AddSchoolPresenter providePresenter() {
        return schoolComponent.addSchoolPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public SchoolComponent getComponent() {
        return schoolComponent;
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
        return R.layout.activity_add_school;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        schoolResidenceInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(locationSelectedInfo != null) {

                    navigatorImpl.showSearchSchoolLocation(AddSchoolMvpActivity.this,
                            locationSelectedInfo.latitude, locationSelectedInfo.longitude);

                } else {

                    if(permissionManager.shouldAskPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        permissionManager.checkSinglePermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                                getString(R.string.location_permission_reason));
                    } else {
                        navigatorImpl.showSearchSchoolLocation(AddSchoolMvpActivity.this, true);
                    }
                }

            }
        });
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

        if(viewId.equals(R.id.schoolNameInput)) {
            schoolNameInputLayout.setError(message);
        } else if(viewId.equals(R.id.schoolEmailInput)) {
            schoolEmailInputLayout.setError(message);
        } else if(viewId.equals(R.id.schoolResidenceInput)) {
            schoolResidenceInputLayout.setError(message);
        } else if(viewId.equals(R.id.schoolTelephoneInput)) {
            schoolTelephoneInputLayout.setError(message);
        }
    }

    /**
     * On Validation Success
     */
    @Override
    public void onValidationSucceeded() {
        super.onValidationSucceeded();

        final String name = schoolNameInput.getText().toString();
        final String email = schoolEmailInput.getText().toString();
        final String telephone = getString(R.string.tfno_prefix).concat(schoolTelephoneInput.getText().toString());
        final String residence = locationSelectedInfo != null ? locationSelectedInfo.getResidence() : "";
        final String province = locationSelectedInfo != null ? locationSelectedInfo.getLocality() : "";
        final double latitude = locationSelectedInfo != null ? locationSelectedInfo.getLatitude() : 0.0;
        final double longitude = locationSelectedInfo != null ? locationSelectedInfo.getLongitude() : 0.0;

        getPresenter().saveSchool(name, residence, province, latitude, longitude, telephone, email);

    }

    /**
     * On Reset Fields
     */
    @Override
    protected void onResetFields() {
        super.onResetFields();

        schoolNameInput.getText().clear();
        schoolEmailInput.getText().clear();
        schoolTelephoneInput.getText().clear();
        schoolResidenceInput.getText().clear();
    }

    /**
     * On Reset Errors
     */
    @Override
    protected void onResetErrors() {
        schoolEmailInputLayout.setError(null);
        schoolTelephoneInputLayout.setError(null);
        schoolNameInputLayout.setError(null);
        schoolResidenceInputLayout.setError(null);
    }

    /**
     * On Save Changes
     */
    @OnClick(R.id.saveChanges)
    protected void onSaveChanges(){
        validate();
    }


    /**
     * On School Added
     * @param schoolEntity
     */
    @Override
    public void onSchoolAdded(final SchoolEntity schoolEntity) {

        showNoticeDialog(R.string.school_saved_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                final Intent schoolSavedIntent = new Intent();
                schoolSavedIntent.putExtra(SCHOOL_ADDED_ARG, schoolEntity);
                onResultOk(schoolSavedIntent);
            }
        });

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
                case NAME_FIELD_NAME:
                    schoolNameInputLayout.setError(error.get("message"));
                    break;
                case EMAIL_FIELD_NAME:
                    schoolEmailInputLayout.setError(error.get("message"));
                    break;
                case RESIDENCE_FIELD_NAME:
                    schoolResidenceInputLayout.setError(error.get("message"));
                    break;
                case PROVINCE_FIELD_NAME:
                    schoolResidenceInputLayout.setError(error.get("message"));
                    break;
                case LATITUDE_FIELD_NAME:
                    schoolResidenceInputLayout.setError(error.get("message"));
                    break;
                case LONGITUDE_FIELD_NAME:
                    schoolResidenceInputLayout.setError(error.get("message"));
                    break;
                case TFNO_FIELD_NAME:
                    schoolTelephoneInputLayout.setError(error.get("message"));
                    break;
            }

        }

        showNoticeDialog(R.string.forms_is_not_valid);
    }

    /**
     * On Show School Detail
     */
    @OnClick(R.id.showSchoolDetail)
    protected void onShowSchoolDetail(){
        navigatorImpl.showSchoolDetail(AddSchoolMvpActivity.this, null);
    }

    /**
     * On Select Location
     * @param latitude
     * @param longitude
     * @param residence
     * @param locality
     */
    @Override
    public void onSelectLocation(double latitude, double longitude, String residence, String locality) {
        locationSelectedInfo = new LocationSelectedInfo(latitude, longitude, residence, locality);
        schoolResidenceInput.setText(residence);
    }


    /**
     * Single Permission Granted
     * @param permission
     */
    @Override
    public void onSinglePermissionGranted(String permission) {
        super.onSinglePermissionGranted(permission);

        if(permission.equalsIgnoreCase(Manifest.permission.ACCESS_COARSE_LOCATION))
            navigatorImpl.showSearchSchoolLocation(AddSchoolMvpActivity.this, true);

    }

    /**
     * On Single Permission Rejected
     * @param permission
     */
    @Override
    public void onSinglePermissionRejected(String permission) {
        super.onSinglePermissionRejected(permission);

        if(permission.equalsIgnoreCase(Manifest.permission.ACCESS_COARSE_LOCATION))
            navigatorImpl.showSearchSchoolLocation(AddSchoolMvpActivity.this, false);

    }

    /**
     * Location Selected Info
     */
    private class LocationSelectedInfo implements Serializable {

        private final double latitude;
        private final double longitude;
        private final String residence;
        private final String locality;

        LocationSelectedInfo(double latitude, double longitude, String residence, String locality) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.residence = residence;
            this.locality = locality;
        }

        double getLatitude() {
            return latitude;
        }

        double getLongitude() {
            return longitude;
        }

        String getResidence() {
            return residence;
        }

        String getLocality() {
            return locality;
        }
    }
}
