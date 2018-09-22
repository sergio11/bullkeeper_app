package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.school.AddSchoolInteract;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;

/**
 * Add School Presenter
 */
public final class AddSchoolPresenter extends SupportPresenter<IAddSchoolView> {

    /**
     * Add School
     */
    private final AddSchoolInteract addSchoolInteract;

    /**
     * Add School Presenter
     */
    @Inject
    public AddSchoolPresenter(final AddSchoolInteract addSchoolInteract) {
        this.addSchoolInteract = addSchoolInteract;
    }


    /**
     * Save School
     * @param name
     * @param residence
     * @param province
     * @param latitude
     * @param longitude
     * @param tfno
     * @param email
     */
    public void saveSchool(final String name, final String residence, final String province,
                           final Double latitude, final Double longitude, final String tfno, final String email) {

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.saving_school_information);

        addSchoolInteract.execute(new AddSchoolObserver(AddSchoolInteract.AddSchoolApiErrors.class),
                AddSchoolInteract.Params.create(name, residence, province, latitude, longitude, tfno, email));
    }


    /**
     * Add School Observer
     */
    private final class AddSchoolObserver extends CommandCallBackWrapper<SchoolEntity, AddSchoolInteract.AddSchoolApiErrors.IAddSchoolApiErrorVisitor,
            AddSchoolInteract.AddSchoolApiErrors> implements AddSchoolInteract.AddSchoolApiErrors.IAddSchoolApiErrorVisitor {


        public AddSchoolObserver(Class<AddSchoolInteract.AddSchoolApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param schoolEntity
         */
        @Override
        protected void onSuccess(SchoolEntity schoolEntity) {
            Preconditions.checkNotNull(schoolEntity, "School can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSchoolAdded(schoolEntity);
            }
        }

        /**
         * Visit Validation Error
         * @param apiErrors
         * @param errors
         */
        @Override
        public void visitValidationError(AddSchoolInteract.AddSchoolApiErrors apiErrors, LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                if(errors != null && !errors.isEmpty() && errors.containsKey("field_errors")) {
                    getView().onValidationErrors(errors.get("field_errors"));
                } else {
                    getView().showNoticeDialog(R.string.forms_is_not_valid);
                }
            }
        }
    }


}
