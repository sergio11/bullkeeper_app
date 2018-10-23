package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.activity;

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
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;

/**
 * Activity By Social Media Dialog
 */
public final class ActivityBySocialMediaDialog extends SupportDialogFragment {

    public static final String TAG = "ACTIVITY_BY_SOCIAL_MEDIA_DIALOG";

    private static final String SOCIAL_MEDIA_TYPE_ARG = "SOCIAL_MEDIA_TYPE_ARG";
    private static final String SOCIAL_MEDIA_VALUE_ARG = "SOCIAL_MEDIA_VALUE_ARG";

    /**
     * Social Media Enum
     */
    private SocialMediaEnum socialMediaEnum;

    /**
     * Social Media Value
     */
    private String socialMediaValue;

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
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity, final SocialMediaEnum socialMediaEnum, final String socialMediaValue) {
        final ActivityBySocialMediaDialog fourDimensionsDialogFragment = new ActivityBySocialMediaDialog();
        final Bundle args = new Bundle();
        args.putSerializable(SOCIAL_MEDIA_TYPE_ARG, socialMediaEnum);
        args.putString(SOCIAL_MEDIA_VALUE_ARG, socialMediaValue);
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
        return R.layout.activity_by_social_media_dialog;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {

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

        if(args == null || !args.containsKey(SOCIAL_MEDIA_TYPE_ARG) ||
                !args.containsKey(SOCIAL_MEDIA_VALUE_ARG))
            throw new IllegalArgumentException("You must provide Social Media Idx and Social Media Value and Kid Identity");

        // Social Media Enum
        socialMediaEnum = (SocialMediaEnum) args.getSerializable(SOCIAL_MEDIA_TYPE_ARG);
        // Social Media Value
        socialMediaValue = args.getString(SOCIAL_MEDIA_VALUE_ARG);


        switch (socialMediaEnum) {

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
                contentDetailTextView.setText(String.format(getString(R.string.kid_results_activity_social_instagram),
                        socialMediaValue));
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
                contentDetailTextView.setText(String.format(getString(R.string.kid_results_activity_social_facebook),
                        socialMediaValue));
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
                contentDetailTextView.setText(String.format(getString(R.string.kid_results_activity_social_youtube),
                        socialMediaValue));
                break;
        }
    }
}
