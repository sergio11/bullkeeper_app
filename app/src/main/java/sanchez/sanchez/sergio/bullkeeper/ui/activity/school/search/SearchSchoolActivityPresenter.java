package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search;

import android.os.Bundle;

import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportSearchLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.school.GetTotalSchoolsInteract;
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
     * Get Total Schools Interact
     */
    private final GetTotalSchoolsInteract getTotalSchoolsInteract;

    /**
     *
     * @param searchSchoolsInteract
     * @param getTotalSchoolsInteract
     */
    @Inject
    public SearchSchoolActivityPresenter(final SearchSchoolsInteract searchSchoolsInteract,
                                         final GetTotalSchoolsInteract getTotalSchoolsInteract) {
        super();
        this.searchSchoolsInteract = searchSchoolsInteract;
        this.getTotalSchoolsInteract = getTotalSchoolsInteract;
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
    public void loadData() {

        getTotalSchoolsInteract.execute(new GetTotalSchoolsObservable(), null);

    }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        loadData();
    }


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

    /**
     * Get Total Schools Observable
     */
    public class GetTotalSchoolsObservable extends BasicCommandCallBackWrapper<Integer> {

        /**
         * On Success
         * @param totalSchools
         */
        @Override
        protected void onSuccess(Integer totalSchools) {
            if (isViewAttached() && getView() != null)
                if (totalSchools == 0)
                    getView().noRegisteredSchool();

        }
    }

}
