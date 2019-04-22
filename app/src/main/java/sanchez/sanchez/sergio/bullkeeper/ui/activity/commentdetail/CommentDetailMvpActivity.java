package sanchez.sanchez.sergio.bullkeeper.ui.activity.commentdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Locale;
import butterknife.BindView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.CommentsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerCommentsComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.CommentEntity;

/**
 * Comment Detail Activity
 */
public class CommentDetailMvpActivity extends SupportMvpActivity<CommentDetailPresenter, ICommentDetailView>
        implements HasComponent<CommentsComponent> , ICommentDetailView {

    private final String CONTENT_FULL_NAME = "COMMENT_DETAIL";
    private final String CONTENT_TYPE_NAME = "COMMENTS";

    /**
     * Comment Identity Arg
     */
    public static final String COMMENT_IDENTITY_ARG = "COMMENT_IDENTITY_ARG";

    /**
     * Comments Component
     */
    private CommentsComponent commentsComponent;

    /**
     * State
     * =================
     */

    /**
     * Comment Identity
     */
    @State
    protected String commentIdentity;

    /**
     * Views
     * ====================
     */

    /**
     * Author Image
     */
    @BindView(R.id.authorImage)
    protected ImageView authorImage;

    /**
     * Author Name
     */
    @BindView(R.id.authorName)
    protected TextView authorNameTextView;

    /**
     * Comment Create At Title
     */
    @BindView(R.id.commentCreateAtTitle)
    protected TextView commentCreateAtTitleTextView;

    /**
     * Social Media Image View
     */
    @BindView(R.id.socialMediaImage)
    protected ImageView socialMediaImageView;

    /**
     * Likes Image View
     */
    @BindView(R.id.likesImage)
    protected ImageView likesImageView;

    /**
     * Likes Count Text View
     */
    @BindView(R.id.likesCount)
    protected TextView likesCountTextView;

    /**
     * Comment Sice Text View
     */
    @BindView(R.id.commentSince)
    protected TextView commentSinceTextView;

    /**
     * Comment Message
     */
    @BindView(R.id.commentMessage)
    protected TextView commentMessage;

    /**
     * Sentiment Result Text
     */
    @BindView(R.id.sentimentResultText)
    protected TextView sentimentResultText;

    /**
     * Violence Result Text
     */
    @BindView(R.id.violenceResultText)
    protected TextView violenceResultText;

    /**
     * Drugs Result Text
     */
    @BindView(R.id.drugsResultText)
    protected TextView drugsResultText;

    /**
     * Sex Result Text
     */
    @BindView(R.id.sexResultText)
    protected TextView sexResultText;

    /**
     * Bullying Result Text
     */
    @BindView(R.id.bullyingResultText)
    protected TextView bullyingResultText;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity) {
        final Intent callingIntent = new Intent(context, CommentDetailMvpActivity.class);
        callingIntent.putExtra(COMMENT_IDENTITY_ARG, identity);
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
        return R.layout.activity_comment_detail;
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
    public CommentDetailPresenter providePresenter() {
        return commentsComponent.commentDetailPresenter();
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

        if(getIntent() == null ||
                !getIntent().hasExtra(COMMENT_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide comment identity");

        // Comment Identity
        commentIdentity = getIntent().getStringExtra(COMMENT_IDENTITY_ARG);
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        if(appUtils.isValidString(commentIdentity))
            args.putString(CommentDetailPresenter.COMMENT_IDENTITY_ARG,
                    commentIdentity);
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
     * On Comment Loaded
     * @param commentEntity
     */
    @Override
    public void onCommentLoaded(CommentEntity commentEntity) {
        Preconditions.checkNotNull(commentEntity, "Comment Entity");

        if(appUtils.isValidString(commentEntity.getAuthorPhoto())) {
            Picasso.with(getApplicationContext()).load(commentEntity.getAuthorPhoto())
                    .placeholder(R.drawable.user_default)
                    .error(R.drawable.user_default)
                    .noFade()
                    .into(authorImage);
        } else {
            authorImage.setImageResource(R.drawable.user_default);
        }

        authorNameTextView.setText(commentEntity.getAuthorName());

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
        // Comment Create At
        commentCreateAtTitleTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.comment_detail_create_at),
                simpleDateFormat.format(commentEntity.getCreatedTime())));


        switch (commentEntity.getSocialMedia()) {

            case FACEBOOK:
                socialMediaImageView.setImageResource(R.drawable.facebook_brand_solid_cyan);
                break;

            case YOUTUBE:
                socialMediaImageView.setImageResource(R.drawable.youtube_brands_solid_cyan);
                break;

            case INSTAGRAM:
                socialMediaImageView.setImageResource(R.drawable.instagram_brands_solid_cyan);
                break;
        }

        // Likes Count
        likesCountTextView.setText(String.valueOf(commentEntity.getLikes()));
        // Comments Since
        commentSinceTextView.setText(commentEntity.getExtractedAtSince());
        // Comment Message
        commentMessage.setText(commentEntity.getMessage());

        // Sentiment
        switch (commentEntity.getSentimentLevelEnum()) {
            case NEUTRO:
                sentimentResultText.setText(R.string.comment_detail_sentiment_level_neutro);
                sentimentResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.silver_color));
                break;
            case NEGATIVE:
                sentimentResultText.setText(R.string.comment_detail_sentiment_level_negative);
                sentimentResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.redDanger));
                break;
            case POSITIVE:
                sentimentResultText.setText(R.string.comment_detail_sentiment_level_positive);
                sentimentResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.greenSuccess));
                break;
            case UNKNOWN:
                sentimentResultText.setText(R.string.comment_detail_sentiment_level_unknown);
                sentimentResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.silver_color));
                break;
        }


        // Adult
        switch (commentEntity.getAdultLevelEnum()) {
            case UNKNOWN:
                sexResultText.setText(R.string.comment_detail_adult_level_unknown);
                sexResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.silver_color));
                break;
            case POSITIVE:
                sexResultText.setText(R.string.comment_detail_adult_level_positive);
                sexResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.redDanger));
                break;
            case NEGATIVE:
                sexResultText.setText(R.string.comment_detail_adult_level_negative);
                sexResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.greenSuccess));
                break;
        }

        // Violence
        switch (commentEntity.getViolenceLevelEnum()) {
            case NEGATIVE:
                violenceResultText.setText(R.string.comment_detail_violence_level_negative);
                violenceResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.greenSuccess));
                break;
            case POSITIVE:
                violenceResultText.setText(R.string.comment_detail_violence_level_positive);
                violenceResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.redDanger));
                break;
            case UNKNOWN:
                violenceResultText.setText(R.string.comment_detail_violence_level_unknown);
                violenceResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.silver_color));
                break;
        }

        // Bullying
        switch (commentEntity.getBullyingLevelEnum()) {
            case UNKNOWN:
                bullyingResultText.setText(R.string.comment_detail_bullying_level_unknown);
                bullyingResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.silver_color));
                break;
            case POSITIVE:
                bullyingResultText.setText(R.string.comment_detail_bullying_level_positive);
                bullyingResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.redDanger));
                break;
            case NEGATIVE:
                bullyingResultText.setText(R.string.comment_detail_bullying_level_negative);
                bullyingResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.greenSuccess));
                break;
        }

        // Drugs
        switch (commentEntity.getDrugsLevelEnum()) {
            case NEGATIVE:
                drugsResultText.setText(R.string.comment_detail_drugs_level_negative);
                drugsResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.greenSuccess));
                break;
            case POSITIVE:
                drugsResultText.setText(R.string.comment_detail_drugs_level_positive);
                drugsResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.redDanger));
                break;
            case UNKNOWN:
                drugsResultText.setText(R.string.comment_detail_drugs_level_unknown);
                drugsResultText.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.silver_color));
                break;
        }



    }

    /**
     * On Comment Not Found
     */
    @Override
    public void onCommentNotFound() {

        showNoticeDialog(R.string.comment_detail_not_found, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });

    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_8;
    }
}
