package sanchez.sanchez.sergio.bullkeeper.core.ui;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Support Mvp Search LCE Activity
 * @param <T>
 * @param <E>
 * @param <F>
 */
public abstract class SupportMvpSearchLCEActivity<T extends SupportSearchLCEPresenter<E>, E extends ISupportLCEView, F>
        extends SupportMvpLCEActivity<T, E, F> implements SearchView.OnQueryTextListener {

    public static String TAG = "SUPPORT_MVP_SEARCH_LCE_ACTIVITY";

    /**
     * Search View
     */
    @BindView(R.id.searchView)
    protected SearchView searchView;

    /**
     * Search Header Image
     */
    @BindView(R.id.searchHeaderImage)
    protected ImageView searchHeaderImage;

    /**
     * Search Header Title
     */
    @BindView(R.id.searchHeaderTitle)
    protected TextView searchHeaderTitle;

    /**
     * Init Search
     */
    @BindView(R.id.initSearch)
    protected View initSearchView;

    /**
     * Init Search Layout
     */
    protected InitSearchLayout initSearchLayout;

    /**
     * Current Query Text
     */
    private String currentQueryText = "";

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {


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
                searchHeaderImage.setVisibility(View.GONE);
                searchHeaderTitle.setVisibility(View.GONE);
                searchView.setBackgroundResource(R.drawable.searchbar_background);
                searchView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            }
        });

        // On Close Listener
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchHeaderImage.setVisibility(View.VISIBLE);
                searchHeaderTitle.setVisibility(View.VISIBLE);
                searchView.setBackgroundResource(android.R.color.transparent);
                searchView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                return false;
            }
        });

        initSearchLayout = new InitSearchLayout();
        initSearchLayout.bind(initSearchView);

        super.onViewReady(savedInstanceState);

        onShowInitSearch();
    }

    @Override
    public void onDataLoaded(List<F> dataLoaded) {
        super.onDataLoaded(dataLoaded);
        recyclerViewAdapter.setHighlightText(currentQueryText);
    }

    /**
     * On Show Init Search
     */
    protected void onShowInitSearch() {
        errorOccurredLayout.hide();
        notDataFoundLayout.hide();
        content.setVisibility(View.GONE);
        content.setEnabled(false);
        loadingLayout.hide();
        initSearchLayout.show(getString(R.string.make_a_search_to_see_results));
    }

    /**
     * On Show Loading State
     */
    @Override
    protected void onShowLoadingState() {
        super.onShowLoadingState();
        initSearchLayout.hide();
    }

    /**
     * On Show Not Found State
     */
    @Override
    protected void onShowNotFoundState() {
        super.onShowNotFoundState();
        initSearchLayout.hide();
    }

    /**
     * On Show Error State
     * @param message
     */
    @Override
    protected void onShowErrorState(String message) {
        super.onShowErrorState(message);
        initSearchLayout.hide();
    }

    /**
     * On Show Data Founded State
     */
    @Override
    protected void onShowDataFoundedState() {
        super.onShowDataFoundedState();
        initSearchLayout.hide();
    }

    /**
     * On Query Text Submit
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        onSearch(query);
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
        onSearch(newText);
        return true;
    }

    /**
     * On Search
     * @param queryText
     */
    protected void onSearch(final String queryText) {
        this.currentQueryText = queryText;
        onShowLoadingState();
        loadData();
    }

    /**
     * Load Data
     */
    @Override
    protected void loadData() {
        if(currentQueryText != null && !currentQueryText.isEmpty())
            getPresenter().loadData(currentQueryText);
        else
            onShowInitSearch();
    }

    /**
     * Init Search
     */
    public class InitSearchLayout {

        /**
         * Search Icon
         */
        @BindView(R.id.searchIcon)
        protected ImageView searchIcon;

        /**
         * Search Message
         */
        @BindView(R.id.message)
        protected TextView searchMessage;

        /**
         * UnBinder
         */
        private Unbinder unbinder;

        private View source;

        /**
         * Bind
         */
        public void bind(final View source) {
            Preconditions.checkNotNull(source, "Source can not be null");
            this.source = source;
            unbinder = ButterKnife.bind(this, source);

        }

        /**
         * Show
         * @param message
         */
        public void show(final String message) {
            searchMessage.setText(message);
            source.setVisibility(View.VISIBLE);
        }

        /**
         * Hide
         */
        public void hide() {
            searchMessage.setText("-");
            source.setVisibility(View.GONE);
        }


        /**
         * Un Bind
         */
        public void unbind(){

            if(unbinder != null)
                unbinder.unbind();
        }

        public ImageView getSearchIcon() {
            return searchIcon;
        }

        public TextView getSearchMessage() {
            return searchMessage;
        }
    }

}
