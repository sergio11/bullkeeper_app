package sanchez.sanchez.sergio.bullkeeper.ui.activity.searchguardian;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportSearchLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.guardians.SearchGuardiansInteract;
import sanchez.sanchez.sergio.domain.interactor.school.SearchSchoolsInteract;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;

/**
 * Search Guardians Activity Presenter
 */
public final class SearchGuardiansActivityPresenter
        extends SupportSearchLCEPresenter<ISearchGuardiansActivityView> {

    /**
     * Search Guardians
     */
    private final SearchGuardiansInteract searchGuardiansInteract;

    /**
     * Search Guardians
     * @param searchGuardiansInteract
     */
    @Inject
    public SearchGuardiansActivityPresenter(final SearchGuardiansInteract searchGuardiansInteract) {
        super();
        this.searchGuardiansInteract = searchGuardiansInteract;
    }

    /**
     * Load Data
     * @param queryText
     */
    @Override
    public void loadData(String queryText) {
        searchGuardiansInteract.execute(new SearchGuardiansActivityObservable(SearchGuardiansInteract.SearchGuardiansApiErrors.class),
                SearchGuardiansInteract.Params.create(queryText));
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() { }

    /**
     * Load Data
     * @param args
     */
    @Override
    public void loadData(Bundle args) {
        loadData();
    }


    /**
     * Search Guardians Activity Observable
     */
    public class SearchGuardiansActivityObservable extends CommandCallBackWrapper<List<GuardianEntity>,
            SearchGuardiansInteract.SearchGuardiansApiErrors.ISearchGuardiansApiErrorVisitor,
            SearchGuardiansInteract.SearchGuardiansApiErrors>
            implements SearchGuardiansInteract.SearchGuardiansApiErrors.ISearchGuardiansApiErrorVisitor {


        /**
         * Search School Activity
         * @param apiErrors
         */
        public SearchGuardiansActivityObservable(Class<SearchGuardiansInteract.SearchGuardiansApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(List<GuardianEntity> response) {
            Preconditions.checkNotNull(response, "Response can not be null");
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }
        }

        /**
         * Visit No Guardians Found
         * @param error
         */
        @Override
        public void visitNoGuardiansFound(SearchGuardiansInteract.SearchGuardiansApiErrors error) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }

}
