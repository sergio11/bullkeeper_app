package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.LinkedHashMap;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetInformationAboutTheChildAndTheirSocialMediaInteract;
import sanchez.sanchez.sergio.domain.interactor.children.SaveChildrenInteract;
import sanchez.sanchez.sergio.domain.models.GuardianRolesEnum;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;

/**
 * My Kids Profile Presenter
 */
public final class MyKidsProfilePresenter
        extends SupportPresenter<IMyKidsProfileView> {

    /**
     * Get Information About The Child And Their Social Media
     */
    private final GetInformationAboutTheChildAndTheirSocialMediaInteract
            getInformationAboutTheChildAndTheirSocialMediaInteract;

    /**
     * Save Children Interact
     */
    private final SaveChildrenInteract saveChildrenInteract;


    /**
     * My Kids Profile Presenter
     * @param getInformationAboutTheChildAndTheirSocialMediaInteract
     * @param saveChildrenInteract
     */
    @Inject
    public MyKidsProfilePresenter(final GetInformationAboutTheChildAndTheirSocialMediaInteract getInformationAboutTheChildAndTheirSocialMediaInteract,
                                  final SaveChildrenInteract saveChildrenInteract) {
        super();
        this.getInformationAboutTheChildAndTheirSocialMediaInteract = getInformationAboutTheChildAndTheirSocialMediaInteract;
        this.saveChildrenInteract = saveChildrenInteract;
    }


    /**
     * Load Kid Data
     * @param kid
     */
    public void loadKidData(final String kid) {
        Preconditions.checkNotNull(kid, "Kid Id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid Id can not be empty");

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_kid_information);

        getInformationAboutTheChildAndTheirSocialMediaInteract.execute(new GetInformationAboutTheChildAndTheirSocialMediaObservable(),
                GetInformationAboutTheChildAndTheirSocialMediaInteract.Params.create(kid));

    }

    /**
     * Save Kid
     * @param identity
     * @param firstname
     * @param surname
     * @param birthday
     * @param school
     */
    public void saveKid(final String identity, final String firstname, final String surname,
                        final String birthday, final String school, final String profileImage,
                        final List<SocialMediaEntity> socialMediaEntities,
                        final List<KidGuardianEntity> kidGuardianEntities) {

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        saveChildrenInteract.execute(new SaveChildrenObservable(SaveChildrenInteract.SaveChildrenApiErrors.class),
                SaveChildrenInteract.Params.create(identity, firstname, surname, birthday, school,
                        profileImage, socialMediaEntities, kidGuardianEntities));

    }


    /**
     * Get Information About The Child And Their Social Media Observable
     */
    public class GetInformationAboutTheChildAndTheirSocialMediaObservable extends BasicCommandCallBackWrapper<GetInformationAboutTheChildAndTheirSocialMediaInteract.Result> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(GetInformationAboutTheChildAndTheirSocialMediaInteract.Result response) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                if(response.getKidGuardianEntity().getRole().equals(GuardianRolesEnum.ADMIN)) {
                    getView().onKidLoaded(response.getKidGuardianEntity().getKid());
                    getView().onSocialMediaLoaded(response.getSocialMediaEntities());
                } else {
                    getView().onEditionNotAllowed();
                }
            }
        }
    }

    /**
     * Save Children Observable
     */
    public class SaveChildrenObservable  extends CommandCallBackWrapper<SaveChildrenInteract.Result, SaveChildrenInteract.SaveChildrenApiErrors.ISaveChildrenApiErrorVisitor,
            SaveChildrenInteract.SaveChildrenApiErrors> implements SaveChildrenInteract.SaveChildrenApiErrors.ISaveChildrenApiErrorVisitor {


        public SaveChildrenObservable(Class<SaveChildrenInteract.SaveChildrenApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         *
         * @param result
         */
        @Override
        protected void onSuccess(SaveChildrenInteract.Result result) {
            Preconditions.checkNotNull(result, "Result can not be null");
            Preconditions.checkNotNull(result.getKidEntity(), "Son Entity can not be null");
            Preconditions.checkNotNull(result.getSocialMediaEntities(), "Social Media can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().showNoticeDialog(R.string.child_information_saved);
                getView().onKidLoaded(result.getKidEntity());
                getView().onSocialMediaLoaded(result.getSocialMediaEntities());
            }
        }

        /**
         * Visit Validation Error
         *
         * @param apiErrors
         * @param errors
         */
        @Override
        public void visitValidationError(SaveChildrenInteract.SaveChildrenApiErrors apiErrors, LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors) {
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
