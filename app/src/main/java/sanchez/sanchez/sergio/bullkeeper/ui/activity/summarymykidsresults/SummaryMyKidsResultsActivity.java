package sanchez.sanchez.sergio.bullkeeper.ui.activity.summarymykidsresults;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSummaryMyKidsResultsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SummaryMyKidsResultsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.SummaryMyKidsResultAdapter;
import sanchez.sanchez.sergio.domain.models.SummaryMyKidResultEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Summary My Kids Results Activity
 */
public class SummaryMyKidsResultsActivity extends SupportMvpLCEActivity<SummaryMyKidsResultsPresenter, ISummaryMyKidsResultsView, SummaryMyKidResultEntity>
        implements HasComponent<SummaryMyKidsResultsComponent>, ISummaryMyKidsResultsActivityHandler
        , ISummaryMyKidsResultsView {

    private final String CONTENT_FULL_NAME = "SUMMARY_MY_KIDS_RESULTS";
    private final String CONTENT_TYPE_NAME = "KIDS_RESULTS";


    /**
     * Dependencies
     * =======================
     */

    /**
     * Activity
     */
    @Inject
    protected Activity activity;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * Views
     * ========
     */

    /**
     * Summary My Kids Results Component
     */
    private SummaryMyKidsResultsComponent summaryMyKidsResultsComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, SummaryMyKidsResultsActivity.class);
    }


    /**
     * initialize Injector
     */
    @Override
    protected void initializeInjector() {
        summaryMyKidsResultsComponent = DaggerSummaryMyKidsResultsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        summaryMyKidsResultsComponent.inject(this);
    }


    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_summary_my_kids_results;
    }


    /**
     * on View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public SummaryMyKidsResultsPresenter providePresenter() {
        return summaryMyKidsResultsComponent.summaryMyKidsResultsPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public SummaryMyKidsResultsComponent getComponent() {
        return summaryMyKidsResultsComponent;
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return RETURN_TOOLBAR;
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
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.intro_background_cyan;
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<SummaryMyKidResultEntity> getAdapter() {
        final SummaryMyKidsResultAdapter summaryMyKidsResultAdapter =
                new SummaryMyKidsResultAdapter(this, new ArrayList<SummaryMyKidResultEntity>(), picasso);
        summaryMyKidsResultAdapter.setOnSupportRecyclerViewListener(this);
        return summaryMyKidsResultAdapter;
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * On Item click
     * @param summaryMyKidResultEntity
     */
    @Override
    public void onItemClick(final SummaryMyKidResultEntity summaryMyKidResultEntity) {
        Preconditions.checkNotNull(summaryMyKidResultEntity, "Summary My Kid Result can not be null");
        Preconditions.checkNotNull(summaryMyKidResultEntity.getKidEntity(), "Kid entity can not be null");
        navigatorImpl.navigateToKidsResultsActivity(this, summaryMyKidResultEntity.getKidEntity().getIdentity());
    }

    @Override
    public void onFooterClick() {}
}
