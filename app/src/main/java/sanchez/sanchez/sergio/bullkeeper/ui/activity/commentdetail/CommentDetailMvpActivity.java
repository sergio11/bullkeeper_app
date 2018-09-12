package sanchez.sanchez.sergio.bullkeeper.ui.activity.commentdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.CommentsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerCommentsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp;

/**
 * Comment Detail Activity
 */
public class CommentDetailMvpActivity extends SupportMvpActivity<CommentDetailPresenter, ICommentDetailView>
        implements HasComponent<CommentsComponent> , ICommentDetailView {

    public static final String COMMENT_IDENTITY_ARG = "COMMENT_IDENTITY_ARG";

    /**
     * Comments Component
     */
    private CommentsComponent commentsComponent;

    private String commentIdentity;

    /**
     * Author Image
     */
    @BindView(R.id.authorImage)
    protected ImageView authorImage;

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
     * Comment Create At Title
     */
    @BindView(R.id.commentCreateAtTitle)
    protected TextView commentCreateAtTitle;

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

        if (getIntent() != null && getIntent().hasExtra(COMMENT_IDENTITY_ARG)) {
            // Comment Identity
            commentIdentity = getIntent().getStringExtra(COMMENT_IDENTITY_ARG);
        }

        // Set Author Image
        Picasso.with(getApplicationContext()).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                .placeholder(R.drawable.user_default)
                .error(R.drawable.user_default)
                .noFade()
                .into(authorImage);


        commentCreateAtTitle.setText(String.format(Locale.getDefault(),
                getString(R.string.comment_detail_create_at), "3 days ago"));


    }
}
