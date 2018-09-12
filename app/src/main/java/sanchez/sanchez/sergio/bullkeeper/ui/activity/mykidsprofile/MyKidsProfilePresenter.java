package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.LinkedHashMap;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.children.GetInformationAboutTheChildAndTheirSocialMediaInteract;
import sanchez.sanchez.sergio.domain.interactor.children.SaveChildrenInteract;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import static sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile.MyKidsProfileMvpActivity.KIDS_IDENTITY_ARG;

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
     * Attach Observable
     */
    protected void attachObservables(){
        getInformationAboutTheChildAndTheirSocialMediaInteract.attachDisposablesTo(compositeDisposable);
        saveChildrenInteract.attachDisposablesTo(compositeDisposable);
    }

    /**
     * On Init
     */
    @Override
    protected void onInit() {
        super.onInit();
        attachObservables();
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);
        Preconditions.checkNotNull(args, "Args can not be null");
        Preconditions.checkState(args.containsKey(KIDS_IDENTITY_ARG), "Args no contain son identity");
        attachObservables();
        // Load Son Data
        loadSonData(args.getString(KIDS_IDENTITY_ARG));

    }

    /**
     * Load Son Data
     * @param sonId
     */
    public void loadSonData(final String sonId) {
        Preconditions.checkNotNull(sonId, "Son Id can not be null");
        Preconditions.checkState(!sonId.isEmpty(), "Son Id can not be empty");

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_son_information);

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
                        final String birthday, final String school, final String profileImage) {

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.loading_son_information);

        saveChildrenInteract.execute(new SaveChildrenObservable(SaveChildrenInteract.SaveChildrenApiErrors.class),
                SaveChildrenInteract.Params.create(identity, firstname, surname, birthday, school,
                        profileImage));

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
                getView().onSonProfileLoaded(response.getSonEntity());
                getView().onSocialMediaLoaded(response.getSocialMediaEntities());
            }
        }
    }

    /**
     * Save Children Observable
     */
    public class SaveChildrenObservable  extends CommandCallBackWrapper<SonEntity, SaveChildrenInteract.SaveChildrenApiErrors.ISaveChildrenApiErrorVisitor,
            SaveChildrenInteract.SaveChildrenApiErrors> implements SaveChildrenInteract.SaveChildrenApiErrors.ISaveChildrenApiErrorVisitor {


        public SaveChildrenObservable(Class<SaveChildrenInteract.SaveChildrenApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         *
         * @param sonEntity
         */
        @Override
        protected void onSuccess(SonEntity sonEntity) {
            if (isViewAttached() && getView() != null)
                getView().onSonProfileLoaded(sonEntity);
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
