package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.likes;

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
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;

/**
 * Likes By Social Media Dialog
 */
public final class LikesBySocialMediaDialog extends SupportDialogFragment {

    public static final String TAG = "LIKES_BY_SOCIAL_MEDIA_DIALOG";

    private static final String SOCIAL_MEDIA_TYPE_ARG = "SOCIAL_MEDIA_TYPE_ARG";
    private static final String TOTAL_LIKES_ARG = "TOTAL_LIKES_ARG";

    private final static int INSTAGRAM = 0 , FACEBOOK = 1, YOUTUBE = 2;

    /**
     * State
     * ==========
     */

    /**
     * Social Media Type
     */
    private int socialMediaType;

    /**
     * Total Likes Value
     */
    private String totalLikesValue;


    /**
     * Views
     * ==============
     */

    /**
     * Likes Social Media Title Text View
     */
    @BindView(R.id.likesSocialMediaTitle)
    protected TextView likesSocialMediaTitleTextView;

    /**
     * Likes Social Media Image
     */
    @BindView(R.id.likesSocialMediaImage)
    protected ImageView likesSocialMediaImageView;

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
     * Show Dialog
     * @param appCompatActivity
     * @param socialMedia
     * @param totalLikes
     */
    public static void show(final AppCompatActivity appCompatActivity, final int socialMedia, final String totalLikes) {
        final LikesBySocialMediaDialog fourDimensionsDialogFragment = new LikesBySocialMediaDialog();
        final Bundle args = new Bundle();
        args.putInt(SOCIAL_MEDIA_TYPE_ARG, socialMedia);
        args.putString(TOTAL_LIKES_ARG, totalLikes);
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
        return R.layout.likes_by_social_media_dialog_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() { }

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

        if(args == null || !args.containsKey(SOCIAL_MEDIA_TYPE_ARG) ||
                !args.containsKey(TOTAL_LIKES_ARG))
            throw new IllegalArgumentException("You must provide the required arguments");

        // Social Media Arg
        socialMediaType = args.getInt(SOCIAL_MEDIA_TYPE_ARG);
        // Total Likes Arg
        totalLikesValue = args.getString(TOTAL_LIKES_ARG);

        switch (socialMediaType) {

            case FACEBOOK:

                // Likes Social Media Title Text View
                likesSocialMediaTitleTextView.setText(R.string.facebook_brand_title);
                likesSocialMediaTitleTextView.setTextColor(ContextCompat.getColor(getContext() ,
                        R.color.facebook_color));

                // Likes Social Media Image
                likesSocialMediaImageView.setImageResource(R.drawable.likes_facebook);

                // Separator
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.facebook_color));

                // Content Detail Text View
                contentDetailTextView.setText(String.format(getString(R.string.kid_results_facebook_likes_count),
                        totalLikesValue));

                // Close Dialog Button
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.facebook_action_button_state));

                break;

            case INSTAGRAM:

                // Likes Social Media Title Text View
                likesSocialMediaTitleTextView.setText(R.string.instagram_brand_title);
                likesSocialMediaTitleTextView.setTextColor(ContextCompat.getColor(getContext() ,
                        R.color.instagram_color));

                // Likes Social Media Image
                likesSocialMediaImageView.setImageResource(R.drawable.likes_instagram);

                // Separator
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.instagram_color));

                // Content Detail Text View
                contentDetailTextView.setText(String.format(getString(R.string.kid_results_instagram_likes_count),
                        totalLikesValue));

                // Close Dialog Button
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.social_media_instagram_button_state));

                break;

            case YOUTUBE:

                // Likes Social Media Title Text View
                likesSocialMediaTitleTextView.setText(R.string.youtube_brand_title);
                likesSocialMediaTitleTextView.setTextColor(ContextCompat.getColor(getContext() ,
                        R.color.youtube_color));

                // Likes Social Media Image
                likesSocialMediaImageView.setImageResource(R.drawable.likes_youtube);

                // Separator
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.youtube_color));

                // Content Detail Text View
                contentDetailTextView.setText(String.format(getString(R.string.kid_results_youtube_likes_count),
                        totalLikesValue));

                // Close Dialog Button
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.social_media_youtube_button_state));

                break;
        }
    }

}
