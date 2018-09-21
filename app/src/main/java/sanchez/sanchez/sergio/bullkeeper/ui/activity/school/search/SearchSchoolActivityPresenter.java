package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search;

import java.util.List;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportLCEPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportSearchLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.school.SearchSchoolsInteract;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;

/**
 * Search School Activity Presenter
 */
public final class SearchSchoolActivityPresenter
        extends SupportSearchLCEPresenter<ISearchSchoolActivityView> {

    /**
     * Search Schools Interact
     */
    private final SearchSchoolsInteract searchSchoolsInteract;

    /**
     *
     * @param searchSchoolsInteract
     */
    @Inject
    public SearchSchoolActivityPresenter(final SearchSchoolsInteract searchSchoolsInteract) {
        super();
        this.searchSchoolsInteract = searchSchoolsInteract;
    }

    /**
     * Load Data
     * @param queryText
     */
    @Override
    public void loadData(String queryText) {
        searchSchoolsInteract.execute(new SearchSchoolActivityObservable(SearchSchoolsInteract.SearchSchoolApiErrors.class),
                SearchSchoolsInteract.Params.create(queryText));
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {}


    /**
     * Search School Activity Observable
     */
    public class SearchSchoolActivityObservable extends CommandCallBackWrapper<List<SchoolEntity>,
            SearchSchoolsInteract.SearchSchoolApiErrors.ISearchSchoolApiErrorVisitor,
            SearchSchoolsInteract.SearchSchoolApiErrors>
            implements SearchSchoolsInteract.SearchSchoolApiErrors.ISearchSchoolApiErrorVisitor {


        public SearchSchoolActivityObservable(Class<SearchSchoolsInteract.SearchSchoolApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param schoolEntities
         */
        @Override
        protected void onSuccess(final List<SchoolEntity> schoolEntities) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(schoolEntities);
            }
        }

        /**
         * Visit School Not Found
         * @param searchSchoolApiErrors
         */
        @Override
        public void visitSchoolNotFound(final SearchSchoolsInteract.SearchSchoolApiErrors searchSchoolApiErrors) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }

}
