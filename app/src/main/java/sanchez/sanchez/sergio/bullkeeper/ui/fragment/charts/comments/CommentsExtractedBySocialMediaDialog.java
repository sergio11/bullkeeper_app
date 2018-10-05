package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.comments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;

/**
 * Comments Extracted Dialog
 */
public final class CommentsExtractedBySocialMediaDialog extends SupportDialogFragment {

    public static final String TAG = "COMMENTS_EXTRACTED_BY_SOCIAL_MEDIA_DIALOG";

    private static final String SOCIAL_MEDIA_IDX_ARG = "SOCIAL_MEDIA_IDX_ARG";
    private static final String SOCIAL_MEDIA_VALUE_ARG = "SOCIAL_MEDIA_VALUE_ARG";
    private static final String KID_IDENTITY_VALUE_ARG = "KID_IDENTITY_VALUE_ARG";

    private final static int INSTAGRAM = 0, FACEBOOK = 1, YOUTUBE = 2;

    /**
     * Social Media Idx
     */
    private int socialMediaIdx = 0;

    /**
     * Social Media Value
     */
    private String socialMediaValue;

    /**
     * Kid Identity Value
     */
    private String kidIdentityValue;

    /**
     * Social Media Title Text View
     */
    @BindView(R.id.socialMediaTitle)
    protected TextView socialMediaTitleTextView;

    /**
     * Social Media Image View
     */
    @BindView(R.id.socialMediaImage)
    protected ImageView socialMediaImageView;

    /**
     * Separator
     */
    @BindView(R.id.separator)
    protected View separator;

    /**
     * Close Dialog
     */
    @BindView(R.id.closeDialog)
    protected Button closeDialog;

    /**
     * Content Detail Text View
     */
    @BindView(R.id.contentDetailText)
    protected TextView contentDetailTextView;

    /**
     * Show Comments Extracted Text View
     */
    @BindView(R.id.showCommentsExtractedTextView)
    protected TextView showCommentsExtractedTextView;

    /**
     * Show Comments Extracted Image View
     */
    @BindView(R.id.showCommentsExtractedImageView)
    protected ImageView showCommentsExtractedImageView;

    /**
     * Navigator
     */
    @Inject
    protected INavigator navigator;

    /**
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity, int socialMediaIdx, final String socialMediaValue,
        final String kidIdentity) {
        final CommentsExtractedBySocialMediaDialog fourDimensionsDialogFragment = new CommentsExtractedBySocialMediaDialog();
        final Bundle args = new Bundle();
        args.putInt(SOCIAL_MEDIA_IDX_ARG, socialMediaIdx);
        args.putString(SOCIAL_MEDIA_VALUE_ARG, socialMediaValue);
        args.putString(KID_IDENTITY_VALUE_ARG, kidIdentity);
        fourDimensionsDialogFragment.setArguments(args);
        fourDimensionsDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        fourDimensionsDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.comments_extracted_by_social_media_dialog_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        getApplicationComponent().inject(this);
    }

    /**
     * On Close Dialog
     */
    @OnClick(R.id.closeDialog)
    protected void onCloseDialog(){
        this.dismiss();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle args = getArguments();

        if(args == null || !args.containsKey(SOCIAL_MEDIA_IDX_ARG) ||
                !args.containsKey(SOCIAL_MEDIA_VALUE_ARG) || !args.containsKey(KID_IDENTITY_VALUE_ARG))
            throw new IllegalArgumentException("You must provide Social Media Idx and Social Media Value and Kid Identity");

        // Social Media Idx
        socialMediaIdx = args.getInt(SOCIAL_MEDIA_IDX_ARG);
        // Social Media Value
        socialMediaValue = args.getString(SOCIAL_MEDIA_VALUE_ARG);
        // Kid Identity Value
        kidIdentityValue = args.getString(KID_IDENTITY_VALUE_ARG);

        switch (socialMediaIdx) {

            case INSTAGRAM:

                socialMediaTitleTextView.setText(R.string.instagram_brand_title);
                socialMediaTitleTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.instagram_color));
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.instagram_color));
                socialMediaImageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        R.drawable.instagram_brands_color));
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.social_media_instagram_button_state));
                contentDetailTextView.setText(String.format(getString(R.string.instagram_comments_extracted),
                        socialMediaValue));
                showCommentsExtractedTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.instagram_color));
                showCommentsExtractedImageView.setImageResource(R.drawable.instagram_arrow_circle_right_solid);

                break;

            case FACEBOOK:

                socialMediaTitleTextView.setText(R.string.facebook_brand_title);
                socialMediaTitleTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.facebook_color));
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.facebook_color));
                socialMediaImageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        R.drawable.facebook_square_brands_color));
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.social_media_facebook_button_state));
                contentDetailTextView.setText(String.format(getString(R.string.facebook_comments_extracted),
                        socialMediaValue));
                showCommentsExtractedTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.facebook_color));
                showCommentsExtractedImageView.setImageResource(R.drawable.facebook_arrow_circle_right_solid);

                break;

            case YOUTUBE:

                socialMediaTitleTextView.setText(R.string.youtube_brand_title);
                socialMediaTitleTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.youtube_color));
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.youtube_color));
                socialMediaImageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        R.drawable.youtube_brands_solid_color));
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.social_media_youtube_button_state));
                contentDetailTextView.setText(String.format(getString(R.string.youtube_comments_extracted),
                        socialMediaValue));
                showCommentsExtractedTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.youtube_color));
                showCommentsExtractedImageView.setImageResource(R.drawable.youtube_arrow_circle_right_solid);
                break;
        }
    }

    /**
     * On Show Comments Extracted
     */
    @OnClick(R.id.showCommentsExtracted)
    protected void onShowCommentsExtracted(){

        final SocialMediaEnum socialMediaEnum;
        switch (socialMediaIdx) {

            case INSTAGRAM:
                socialMediaEnum = SocialMediaEnum.INSTAGRAM;
                break;
            case YOUTUBE:
                socialMediaEnum = SocialMediaEnum.YOUTUBE;
                break;
            default:
                socialMediaEnum = SocialMediaEnum.FACEBOOK;
        }
        // Show Comments By Kid Identity and Social Media
        navigator.navigateToComments(kidIdentityValue, socialMediaEnum);
    }
}
