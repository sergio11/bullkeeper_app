package sanchez.sanchez.sergio.bullkeeper.ui.activity.comments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageButton;
import android.widget.TextView;

import com.crashlytics.android.answers.ContentViewEvent;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEActivity;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.CommentsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerCommentsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.CommentsAdapter;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;

import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Comments Activity
 */
public class CommentsMvpActivity extends SupportMvpLCEActivity<CommentsMvpPresenter, ICommentsView, CommentEntity>
        implements HasComponent<CommentsComponent>, ICommentsView, SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<CommentEntity> {

    private final String CONTENT_FULL_NAME = "COMMENTS_LIST";
    private final String CONTENT_TYPE_NAME = "COMMENTS";

    /**
     * Kid Identity
     */
    public static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Social Media Enum
     */
    public static final String SOCIAL_MEDIA_ARG = "SOCIAL_MEDIA_ARG";

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
     * ===================
     */
    /**
     * Comments Filter Image Button
     */
    @BindView(R.id.commentsFilter)
    protected ImageButton commentsFilterImageButton;

    /**
     * Comments Header Text View
     */
    @BindView(R.id.commentsHeaderTitle)
    protected TextView commentsHeaderTitleTextView;

    /**
     * State
     * ==================
     */

    /**
     * Kid Identity
     */
    @State
    protected String myKidIdentity;

    @State
    protected SocialMediaEnum socialMediaEnum;


    /**
     * Get Calling Intent
     * @param context
     * @param identity
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity) {
        final Intent callingIntent = new Intent(context, CommentsMvpActivity.class);
        callingIntent.putExtra(KID_IDENTITY_ARG, identity);
        return callingIntent;
    }

    /**
     * Get Calling Intent
     * @param context
     * @param identity
     * @param socialMediaEnum
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity,
                                          final SocialMediaEnum socialMediaEnum) {
        final Intent callingIntent = new Intent(context, CommentsMvpActivity.class);
        callingIntent.putExtra(KID_IDENTITY_ARG, identity);
        callingIntent.putExtra(SOCIAL_MEDIA_ARG, socialMediaEnum);
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
    public CommentsMvpPresenter providePresenter() {
        return commentsComponent.commentsPresenter();
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

        if (getIntent().hasExtra(SOCIAL_MEDIA_ARG))
            socialMediaEnum = (SocialMediaEnum) getIntent().getSerializableExtra(SOCIAL_MEDIA_ARG);

    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        if(appUtils.isValidString(myKidIdentity))
            args.putString(CommentsMvpPresenter.KIDS_INDENTITIES_ARG,
                    myKidIdentity);
        if (socialMediaEnum != null)
            args.putSerializable(CommentsMvpPresenter.SOCIAL_MEDIAS_TYPES_ARG,
                    socialMediaEnum);
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
    protected SupportRecyclerViewAdapter<CommentEntity> getAdapter() {
        return new CommentsAdapter(getApplicationContext(),
                new ArrayList<CommentEntity>(), picasso);
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
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        super.onNoDataFound();

        commentsHeaderTitleTextView.setText(R.string.comments_by_child_title_default);

    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<CommentEntity> dataLoaded) {
        super.onDataLoaded(dataLoaded);

        commentsHeaderTitleTextView.setText(String.format(
                getString(R.string.comments_by_child_title),
                dataLoaded.size()));

    }
}
