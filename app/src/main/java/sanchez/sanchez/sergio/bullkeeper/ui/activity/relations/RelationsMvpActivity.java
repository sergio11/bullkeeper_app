package sanchez.sanchez.sergio.bullkeeper.ui.activity.relations;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.crashlytics.android.answers.ContentViewEvent;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.CommentsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerCommentsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.SocialMediaFriendAdapter;
import sanchez.sanchez.sergio.domain.models.SocialMediaFriendEntity;

import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Relations Activity
 */
public class RelationsMvpActivity extends SupportMvpLCEActivity<RelationsMvpPresenter, IRelationsView, SocialMediaFriendEntity>
        implements HasComponent<CommentsComponent>, IRelationsView, SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<SocialMediaFriendEntity> {

    private final String CONTENT_FULL_NAME = "RELATIONS_LIST";
    private final String CONTENT_TYPE_NAME = "KIDS";

    /**
     * Kid Identity
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Comments Comment
     */
    private CommentsComponent commentsComponent;


    /**
     * Dependencies
     * ====================
     */

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * Views
     * ============
     *
     */

    @BindView(R.id.relationsHeaderTitle)
    protected TextView relationsHeaderTitle;


    /**
     * State
     * ==================
     */

    /**
     * Kid Identity
     */
    @State
    protected String myKidIdentity;

    /**
     * Get Calling Intent
     * @param context
     * @param identity
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity) {
        final Intent callingIntent = new Intent(context, RelationsMvpActivity.class);
        callingIntent.putExtra(KID_IDENTITY_ARG, identity);
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
        return R.layout.activity_relations;
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
    public RelationsMvpPresenter providePresenter() {
        return commentsComponent.relationsMvpPresenter();
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        if (getIntent() == null || !getIntent().hasExtra(KID_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide kid identity");

        myKidIdentity = getIntent().getStringExtra(KID_IDENTITY_ARG);
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        if(appUtils.isValidString(myKidIdentity))
            args.putString(RelationsMvpPresenter.KIDS_INDENTITIES_ARG,
                    myKidIdentity);
        return args;
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
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<SocialMediaFriendEntity> getAdapter() {
        return new SocialMediaFriendAdapter(this, new ArrayList<SocialMediaFriendEntity>());
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * On Item Cliked
     * @param socialMediaFriendEntity
     */
    @Override
    public void onItemClick(SocialMediaFriendEntity socialMediaFriendEntity) {
        navigatorImpl.navigateToRelationDetail(socialMediaFriendEntity);
    }

    /**
     * ON Footer Click
     */
    @Override
    public void onFooterClick() {}

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        super.onNoDataFound();
    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<SocialMediaFriendEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);
        relationsHeaderTitle.setText(String.format(getString(R.string.kid_relations_title),
                dataLoaded.size()));

    }

    /**
     * On Relations Filter Clicked
     */
    @OnClick(R.id.relationsFilter)
    protected void onRelationsFilterClicked(){
        navigatorImpl.navigateToRelationSettings();
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.intro_background_cyan;
    }
}
