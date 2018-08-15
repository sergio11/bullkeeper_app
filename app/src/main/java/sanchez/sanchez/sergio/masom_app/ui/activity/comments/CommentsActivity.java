package sanchez.sanchez.sergio.masom_app.ui.activity.comments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.CommentsComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerCommentsComponent;
import sanchez.sanchez.sergio.masom_app.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.masom_app.ui.adapter.decoration.ItemOffsetDecoration;
import sanchez.sanchez.sergio.masom_app.ui.adapter.impl.CommentsAdapter;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;

import static sanchez.sanchez.sergio.masom_app.ui.support.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Comments Activity
 */
public class CommentsActivity extends SupportActivity<CommentsPresenter, ICommentsView>
        implements HasComponent<CommentsComponent>, ICommentsView, SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<CommentEntity>,CommentsAdapter.OnCommentsViewListener {

    public static final String KIDS_IDENTITY_ARG = "KIDS_IDENTITY_ARG";

    private CommentsComponent commentsComponent;

    /**
     * Main Container
     */
    @BindView(R.id.mainContainer)
    protected ViewGroup mainContainer;

    /**
     * Refresh Layout
     */
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout refreshLayout;

    /**
     * Comments List
     */
    @BindView(R.id.commentsList)
    protected RecyclerView commentsList;

    /**
     * Kid Identity
     */
    private String myKidIdentity;

    /**
     * Comments Adapter
     */
    private CommentsAdapter commentsAdapter;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity) {
        final Intent callingIntent = new Intent(context, CommentsActivity.class);
        callingIntent.putExtra(KIDS_IDENTITY_ARG, identity);
        return callingIntent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {

        commentsComponent = DaggerCommentsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

        commentsComponent.inject(this);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_comments;
    }

    /**
     * Get Comment
     * @return
     */
    @Override
    public CommentsComponent getComponent() {
        return commentsComponent;
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return TOOLBAR_WITH_MENU;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public CommentsPresenter providePresenter() {
        return commentsComponent.commentsPresenter();
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        if (getIntent() != null && getIntent().hasExtra(KIDS_IDENTITY_ARG)) {
            // Get Kid identity
            myKidIdentity = getIntent().getStringExtra(KIDS_IDENTITY_ARG);
        }

        refreshLayout.setColorSchemeResources(R.color.commonWhite);
        refreshLayout.setProgressBackgroundColorSchemeResource(R.color.cyanBrilliant);

        commentsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        commentsList.setNestedScrollingEnabled(false);

        commentsAdapter = new CommentsAdapter(getApplicationContext(), new ArrayList<CommentEntity>());
        commentsAdapter.setOnSupportRecyclerViewListener(this);
        commentsAdapter.setOnCommentsViewListener(this);

        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getApplicationContext(), R.dimen.item_offset);
        commentsList.addItemDecoration(itemOffsetDecoration);
        // Set Animator
        commentsList.setItemAnimator(new DefaultItemAnimator());
        commentsList.setAdapter(commentsAdapter);
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * On Item Click
     * @param commentEntity
     */
    @Override
    public void onItemClick(CommentEntity commentEntity) {
        // Navigate to comment detail
        navigatorImpl.navigateToCommentDetail(commentEntity.getIdentity());
    }

    /**
     * ON Footer Click
     */
    @Override
    public void onFooterClick() {}

    /**
     * On Filter Alerts
     */
    @Override
    public void onFilterAlerts() {}

    /**
     * On Comments Loaded
     * @param commentsList
     */
    @Override
    public void onCommentsLoaded(List<CommentEntity> commentsList) {
        commentsAdapter.setData(new ArrayList<>(commentsList));
        commentsAdapter.notifyDataSetChanged();
    }
}
