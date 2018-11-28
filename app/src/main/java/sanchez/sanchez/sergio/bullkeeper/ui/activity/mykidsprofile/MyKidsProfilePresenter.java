package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.LinkedHashMap;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetInformationAboutTheChildAndTheirSocialMediaInteract;
import sanchez.sanchez.sergio.domain.interactor.children.SaveChildrenInteract;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;

/**
 * My Kids Profile Presenter
 */
public final class MyKidsProfilePresenter extends SupportPresenter<IMyKidsProfileView> {

    /**
     * Get Information About The Child And Their Social Media Interact
     */
    private final GetInformationAboutTheChildAndTheirSocialMediaInteract getInformationAboutTheChildAndTheirSocialMediaInteract;

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
     * Load Son Data
     * @param sonId
     */
    public void loadSonData(final String sonId) {
        Preconditions.checkNotNull(sonId, "Son Id can not be null");
        Preconditions.checkState(!sonId.isEmpty(), "Son Id can not be empty");

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_kid_information);

        getInformationAboutTheChildAndTheirSocialMediaInteract.execute(new GetInformationAboutTheChildAndTheirSocialMediaObservable(),
                GetInformationAboutTheChildAndTheirSocialMediaInteract.Params.create(sonId));

    }

    /**
     * Save Son
     * @param identity
     * @param firstname
     * @param surname
     * @param birthday
     * @param school
     */
    public void saveSon(final String identity, final String firstname, final String surname,
                        final String birthday, final String school, final String profileImage,
                        final List<SocialMediaEntity> socialMediaEntities) {

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_kid_information);

        saveChildrenInteract.execute(new SaveChildrenObservable(SaveChildrenInteract.SaveChildrenApiErrors.class),
                SaveChildrenInteract.Params.create(identity, firstname, surname, birthday, school,
                        profileImage, socialMediaEntities));

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
                getView().onSonProfileLoaded(response.getKidEntity());
                getView().onSocialMediaLoaded(response.getSocialMediaEntities());
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
                getView().onSonProfileLoaded(result.getKidEntity());
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
