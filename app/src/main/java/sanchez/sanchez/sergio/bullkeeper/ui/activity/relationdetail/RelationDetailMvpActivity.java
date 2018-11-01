package sanchez.sanchez.sergio.bullkeeper.ui.activity.relationdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;
import com.crashlytics.android.answers.ContentViewEvent;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.CommentsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerCommentsComponent;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaFriendEntity;

/**
 * Relation Detail Activity
 */
public class RelationDetailMvpActivity extends SupportMvpActivity<RelationDetailPresenter, IRelationDetailView>
        implements HasComponent<CommentsComponent> , IRelationDetailView {

    private final String CONTENT_FULL_NAME = "RELATION_DETAIL";
    private final String CONTENT_TYPE_NAME = "KIDS";

    public static final String SOCIAL_RELATION_NAME_ARG = "SOCIAL_RELATION_NAME_ARG";
    public static final String SOCIAL_RELATION_PROFILE_IMAGE_ARG = "SOCIAL_RELATION_PROFILE_IMAGE_ARG";
    public static final String SOCIAL_RELATION_LABEL_ARG = "SOCIAL_RELATION_LABEL_ARG";
    public static final String SOCIAL_RELATION_SOCIAL_MEDIA_ARG = "SOCIAL_RELATION_SOCIAL_MEDIA_ARG";


    /**
     * Comments Component
     */
    private CommentsComponent commentsComponent;

    /**
     * State
     * =================
     */

    @State
    protected String friendProfileImage;

    @State
    protected String friendName;

    @State
    protected SocialMediaEnum socialMediaEnum;

    @State
    protected String friendLabel;


    /**
     * Views
     * ====================
     */

    @BindView(R.id.friendImage)
    protected ImageView friendImageView;

    @BindView(R.id.friendName)
    protected TextView friendTextView;

    @BindView(R.id.socialMediaImage)
    protected ImageView socialMediaImage;

    @BindView(R.id.socialMediaDesc)
    protected TextView socialMediaDescTextView;

    @BindView(R.id.results)
    protected TextView resultsTextView;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final SocialMediaFriendEntity socialMediaFriendEntity) {
        final Intent callingIntent = new Intent(context, RelationDetailMvpActivity.class);
        callingIntent.putExtra(SOCIAL_RELATION_NAME_ARG,
                socialMediaFriendEntity.getName());
        callingIntent.putExtra(SOCIAL_RELATION_PROFILE_IMAGE_ARG,
                socialMediaFriendEntity.getProfileImage());
        callingIntent.putExtra(SOCIAL_RELATION_LABEL_ARG,
                socialMediaFriendEntity.getLabel());
        callingIntent.putExtra(SOCIAL_RELATION_SOCIAL_MEDIA_ARG,
                socialMediaFriendEntity.getSocialMediaEnum());
        return callingIntent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {

        this.commentsComponent = DaggerCommentsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

        this.commentsComponent.inject(this);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.social_relation_detail;
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public CommentsComponent getComponent() {
        return commentsComponent;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public RelationDetailPresenter providePresenter() {
        return commentsComponent.relationDetailPresenter();
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return SupportToolbarApp.RETURN_TOOLBAR;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        if(getIntent().getExtras() == null || getIntent().getExtras().isEmpty())
            throw new IllegalStateException("You must provide args");


        if(getIntent().hasExtra(SOCIAL_RELATION_PROFILE_IMAGE_ARG)) {

            friendProfileImage = getIntent().getStringExtra(SOCIAL_RELATION_PROFILE_IMAGE_ARG);

            if(appUtils.isValidString(friendProfileImage)) {

                Picasso.with(this)
                        .load(friendProfileImage)
                        .error(R.drawable.user_default)
                        .placeholder(R.drawable.user_default)
                        .into(friendImageView);
            } else {
                friendImageView.setImageResource(R.drawable.user_default);
            }
        }

        if(getIntent().hasExtra(SOCIAL_RELATION_NAME_ARG)) {
            friendName = getIntent().getStringExtra(SOCIAL_RELATION_NAME_ARG);
            friendTextView.setText(friendName);
        }

        if(getIntent().hasExtra(SOCIAL_RELATION_SOCIAL_MEDIA_ARG)) {
            socialMediaEnum = (SocialMediaEnum) getIntent().getSerializableExtra(SOCIAL_RELATION_SOCIAL_MEDIA_ARG);

            switch (socialMediaEnum){
                case INSTAGRAM:
                    socialMediaImage.setImageResource(R.drawable.instagram_brands_solid_cyan);
                    socialMediaDescTextView.setText(R.string.kid_relations_friend_from_instagram);
                    break;
                case YOUTUBE:
                    socialMediaImage.setImageResource(R.drawable.youtube_brands_solid_cyan);
                    socialMediaDescTextView.setText(R.string.kid_relations_friend_from_youtube);
                    break;
                case FACEBOOK:
                    socialMediaImage.setImageResource(R.drawable.facebook_brand_solid_cyan);
                    socialMediaDescTextView.setText(R.string.kid_relations_friend_from_facebook);
                    break;
            }
        }

        if(getIntent().hasExtra(SOCIAL_RELATION_LABEL_ARG)){
            friendLabel = getIntent().getStringExtra(SOCIAL_RELATION_LABEL_ARG);
            resultsTextView.setText(String.format(getString(R.string.kid_relations_results),
                    friendLabel));
        }

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

}
