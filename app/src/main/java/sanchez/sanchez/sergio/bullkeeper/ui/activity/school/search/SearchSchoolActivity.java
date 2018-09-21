package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.fernandocejas.arrow.checks.Preconditions;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSchoolComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SchoolComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.SchoolAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpSearchLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import timber.log.Timber;

/**
 * Search School Activity
 */
public class SearchSchoolActivity extends SupportMvpSearchLCEActivity<SearchSchoolActivityPresenter,
        ISearchSchoolActivityView, SchoolEntity>
        implements HasComponent<SchoolComponent>, ISearchSchoolActivityView, SchoolAdapter.OnSchoolListener {

    public final static String SCHOOL_SELECTED_ARG = "SCHOOL_SELECTED_ARG";

    private SchoolComponent schoolComponent;


    /**
     * Get Calling Intent
     *
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context){
        return new Intent(context, SearchSchoolActivity.class);
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(){

        schoolComponent = DaggerSchoolComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build();

        schoolComponent.inject(this);
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        noDataFoundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.d("On Not Data Founded");
            }
        });

        // Set Search Layout Message
        initSearchLayout.getSearchMessage().setText(R.string.search_school_message);
        loadingLayout.getMessage().setText(R.string.search_school_loading_message);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes(){
        return R.layout.activity_search_school;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public SearchSchoolActivityPresenter providePresenter(){
        return schoolComponent.searchSchoolActivityPresenter();
    }


    /**
     * Get Component
     * @return
     */
    @Override
    public SchoolComponent getComponent(){
        return schoolComponent;
    }


    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<SchoolEntity> getAdapter() {
        final SchoolAdapter schoolAdapter = new SchoolAdapter(appContext, new ArrayList<SchoolEntity>());
        schoolAdapter.setSchoolListener(this);
        return schoolAdapter;
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() { }

    /**
     * On Item Click
     * @param schoolEntity
     */
    @Override
    public void onItemClick(final SchoolEntity schoolEntity) {
        Timber.d("School Entity Selected -> %s", schoolEntity.getIdentity());
        final Intent schoolSelectedItent = new Intent();
        schoolSelectedItent.putExtra(SCHOOL_SELECTED_ARG, schoolEntity);
        onResultOk(schoolSelectedItent);
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}


    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return SupportToolbarApp.RETURN_TOOLBAR;
    }

    /**
     * On Show School Detail
     * @param schoolEntity
     */
    @Override
    public void onShowSchoolDetail(SchoolEntity schoolEntity) {
        Preconditions.checkNotNull(schoolEntity, "School Entity can not be null");
        navigatorImpl.showSchoolDetail(this, schoolEntity);
    }
}
