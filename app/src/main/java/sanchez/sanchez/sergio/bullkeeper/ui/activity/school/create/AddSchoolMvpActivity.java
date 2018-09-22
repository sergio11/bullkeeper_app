package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSchoolComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SchoolComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpValidationMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import timber.log.Timber;

/**
 * Add School Activity
 */
public class AddSchoolMvpActivity extends SupportMvpValidationMvpActivity<AddSchoolPresenter, IAddSchoolView>
        implements HasComponent<SchoolComponent>, IAddSchoolView, SearchSchoolLocationDialog.ISearchSchoolListener {

    public static final String SCHOOL_ADDED_ARG = "SCHOOL_ADDED_ARG";

    private final static String NAME_FIELD_NAME = "name";
    private final static String RESIDENCE_FIELD_NAME = "residence";
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
    @Length(min = 3, max = 15)
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
     * Latitude
     */
    private double latitude;

    /**
     * Longitude
     */
    private double longitude;

    /**
     * Residence
     */
    private String residence;

    /**
     * Locality
     */
    private String locality;

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
                navigatorImpl.showSearchSchoolLocation(AddSchoolMvpActivity.this);
            }
        });
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



    }

    /**
     * On Validation Success
     */
    @Override
    public void onValidationSucceeded() {
        super.onValidationSucceeded();

        final String name = schoolNameInput.getText().toString();
        final String email = schoolEmailInput.getText().toString();
        final String telephone = schoolTelephoneInput.getText().toString();


        //getPresenter().saveSchool();

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
        this.latitude = latitude;
        this.longitude = longitude;
        this.residence = residence;
        this.locality = locality;

        schoolResidenceInput.setText(residence);
    }
}
