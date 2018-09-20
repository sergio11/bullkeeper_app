package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import butterknife.BindView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSchoolComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SchoolComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.SchoolAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import timber.log.Timber;

/**
 * Search School Activity
 */
public class SearchSchoolActivity extends SupportMvpLCEActivity<SearchSchoolActivityPresenter,
        ISearchSchoolActivityView, SchoolEntity>
        implements HasComponent<SchoolComponent>, ISearchSchoolActivityView,
        SearchView.OnQueryTextListener{

    private SchoolComponent schoolComponent;

    /**
     * Search View
     */
    @BindView(R.id.searchView)
    protected SearchView searchView;

    /**
     * School Header Image
     */
    @BindView(R.id.schoolHeaderImage)
    protected ImageView schoolHeaderImage;

    /**
     * School Header Title
     */
    @BindView(R.id.schoolHeaderTitle)
    protected TextView schoolHeaderTitle;


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

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        searchView.setSubmitButtonEnabled(false);

        // Set Hint and Text Color
        EditText txtSearch = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        txtSearch.setHint(R.string.search_school_query_hint);
        txtSearch.setHintTextColor(ContextCompat.getColor(appContext, R.color.cyanBrilliant));
        txtSearch.setTextColor(getResources().getColor(R.color.cyanBrilliant));

        searchView.setOnQueryTextListener(this);

        // On Search Click Listener
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schoolHeaderImage.setVisibility(View.GONE);
                schoolHeaderTitle.setVisibility(View.GONE);
                searchView.setBackgroundResource(R.drawable.searchbar_background);
                searchView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            }
        });

        // On Close Listener
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                schoolHeaderImage.setVisibility(View.VISIBLE);
                schoolHeaderTitle.setVisibility(View.VISIBLE);
                searchView.setBackgroundResource(android.R.color.transparent);
                searchView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                return false;
            }
        });

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
        return new SchoolAdapter(appContext, new ArrayList<SchoolEntity>());
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {

    }

    /**
     * On Item Click
     * @param schoolEntity
     */
    @Override
    public void onItemClick(final SchoolEntity schoolEntity) {

    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {

    }

    /**
     * On Query Text Submit
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        callSearch(query);
        searchView.clearFocus();
        return true;
    }

    /**
     * On Query Text Change
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        callSearch(newText);
        return true;
    }

    /**
     * Call Search
     * @param queryText
     */
    private void callSearch(final String queryText) {
        Timber.d("Query Text -> %s", queryText);
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return SupportToolbarApp.RETURN_TOOLBAR;
    }
}
