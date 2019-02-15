package sanchez.sanchez.sergio.bullkeeper.ui.activity.searchguardian;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import com.crashlytics.android.answers.ContentViewEvent;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpSearchLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSearchGuardianComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SearchGuardianComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.GuardiansAdapter;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import timber.log.Timber;

/**
 * Search Guardian Activity
 */
public class SearchGuardiansMvpActivity extends SupportMvpSearchLCEActivity<SearchGuardiansActivityPresenter,
        ISearchGuardiansActivityView, GuardianEntity>
        implements HasComponent<SearchGuardianComponent>, ISearchGuardiansActivityView {


    private final String CONTENT_FULL_NAME = "SEARCH_GUARDIANS";
    private final String CONTENT_TYPE_NAME = "GUARDIANS";

    /**
     * Args
     */
    public final static String GUARDIAN_SELECTED_ARG = "GUARDIAN_SELECTED_ARG";

    /**
     * Dependencies
     */

    @Inject
    protected Picasso picasso;

    /**
     * Search Guardian Component
     */
    private SearchGuardianComponent searchGuardianComponent;


    /**
     * Get Calling Intent
     *
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context){
        return new Intent(context, SearchGuardiansMvpActivity.class);
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(){

        searchGuardianComponent = DaggerSearchGuardianComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build();

        searchGuardianComponent.inject(this);
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
        initSearchLayout.getSearchMessage().setText(R.string.search_guardians_message);
        loadingLayout.getMessage().setText(R.string.search_guardians_loading_message);

    }

    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes(){
        return R.layout.activity_search_guardians;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public SearchGuardiansActivityPresenter providePresenter(){
        return searchGuardianComponent.searchGuardianActivityPresenter();
    }


    /**
     * Get Component
     * @return
     */
    @Override
    public SearchGuardianComponent getComponent(){
        return searchGuardianComponent;
    }


    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<GuardianEntity> getAdapter() {
        final GuardiansAdapter guardiansAdapter = new GuardiansAdapter(this,
                new ArrayList<GuardianEntity>(), picasso);
        guardiansAdapter.setOnSupportRecyclerViewListener(this);
        return guardiansAdapter;
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() { }

    /**
     * On Item Click
     * @param guardianEntity
     */
    @Override
    public void onItemClick(final GuardianEntity guardianEntity) {
        final Intent guardianSelectedIntent = new Intent();
        guardianSelectedIntent.putExtra(GUARDIAN_SELECTED_ARG, guardianEntity);
        onResultOk(guardianSelectedIntent);
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
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_9;
    }

}
