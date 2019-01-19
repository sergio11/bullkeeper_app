package sanchez.sanchez.sergio.bullkeeper.core.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.ActivityComponent;

/**
 * Support Mvp Search LCE Fragment
 */
public abstract class SupportMvpSearchLCEFragment<P extends SupportSearchLCEPresenter<V>,
        V extends ISupportLCEView, H extends IBasicActivityHandler, C extends ActivityComponent, F>
        extends SupportMvpLCEFragment<P,V, H, C, F> implements SearchView.OnQueryTextListener {

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
    @Nullable
    @BindView(R.id.initSearch)
    protected View initSearchView;

    /**
     * Init Search Layout
     */
    protected InitSearchLayout initSearchLayout;

    /**
     * Current Query Text
     */
    @State
    protected String currentQueryText = "";

    /**
     * Require Init Search
     */
    protected boolean requireInitSearch = false;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView.setSubmitButtonEnabled(false);

        // Set Hint and Text Color
        EditText txtSearch = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        txtSearch.setHint(getSearchHintRes());
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


        if(requireInitSearch) {
            initSearchLayout = new InitSearchLayout();
            initSearchLayout.bind(initSearchView);
            onShowInitSearch();
        }


    }

    /**
     * Get Search Hint Res
     * @return
     */
    @StringRes
    protected abstract int getSearchHintRes();

    /**
     * On Data Loaded
     * @param dataLoaded
     */
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
        loadingView.setVisibility(View.GONE);
        if(requireInitSearch)
            initSearchLayout.show(getString(R.string.make_a_search_to_see_results));
    }

    /**
     * On Show Loading State
     */
    @Override
    protected void onShowLoadingState() {
        super.onShowLoadingState();
        if(requireInitSearch)
            initSearchLayout.hide();
    }

    /**
     * On Show Not Found State
     */
    @Override
    protected void onShowNotFoundState() {
        super.onShowNotFoundState();
        if(requireInitSearch)
            initSearchLayout.hide();
    }

    /**
     * On Show Error State
     * @param message
     */
    @Override
    protected void onShowErrorState(String message) {
        super.onShowErrorState(message);
        if(requireInitSearch)
            initSearchLayout.hide();
    }

    /**
     * On Show Data Founded State
     */
    @Override
    protected void onShowDataFoundedState() {
        super.onShowDataFoundedState();
        if(requireInitSearch)
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

        if(currentQueryText != null && !currentQueryText.isEmpty()) {
            onShowLoadingState();
            final Bundle args = getArgs();
                        if(args != null)
                getPresenter().loadData(args, currentQueryText);
            else
                getPresenter().loadData(currentQueryText);
        } else {

            if(requireInitSearch)
                onShowInitSearch();
            else {
                getPresenter().resetQuery();
                super.loadData();
            }
        }
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
